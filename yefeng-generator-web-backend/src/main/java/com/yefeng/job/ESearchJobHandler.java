package com.yefeng.job;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.yefeng.model.entity.Generator;
import com.yefeng.service.GeneratorService;
import com.yefeng.service.impl.SearchServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: 叶枫
 * @Date: 2024/06/25/12:59
 * @Description:
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ESearchJobHandler {

    private final GeneratorService generatorService;

    private final SearchServiceImpl searchService;

    /**
     * 每天执行
     */
    @XxlJob("loadGeneratorDocs")
    public void loadGeneratorDocs() {
        log.info("LoadGeneratorDocs start");
        // 当前当前序号索引
        int index = XxlJobHelper.getShardIndex();//0
        // 总共有多少台实例
        int total = XxlJobHelper.getShardTotal();
        int pageNo = index + 1;
        int pageSize = 10;
        // 查出来每一页的数据导入到es里面
        while (true) {
            int start = (pageNo - 1) * pageSize;
            int end = start + pageSize - 1;
            Page<Generator> page = new Page<>(start, end);
            Page<Generator> pages = generatorService.lambdaQuery().page(page);
            List<Generator> list = pages.getRecords();
            if (CollUtil.isEmpty(list)) {
                break;
            }
            searchService.importIndex(list);
            pageNo+=total;
        }
    }
}
