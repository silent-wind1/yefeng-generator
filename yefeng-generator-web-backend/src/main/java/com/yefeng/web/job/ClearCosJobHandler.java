package com.yefeng.web.job;

import cn.hutool.core.util.StrUtil;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.yefeng.web.manager.CosManager;
import com.yefeng.web.mapper.GeneratorMapper;
import com.yefeng.web.model.entity.Generator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 叶枫
 * @Date: 2024/05/17/17:40
 * @Description: 清理对象存储文件类
 */
@Slf4j
@Component
public class ClearCosJobHandler {
    @Resource
    private CosManager cosManager;

    @Resource
    private GeneratorMapper generatorMapper;

    /**
     * 每天执行
     *
     * @throws InterruptedException
     */
    @XxlJob("clearCosJobHandler")
    public void clearCosJobHandler() {
        log.info("clearCosJobHandler start");
        // 1. 包括用户上传的模板制作文件（generator_make_template）
        cosManager.deleteDir("/generator_make_template/");

        // 2. 已删除的代码生成器对应的产物包文件（generator_dist）。
        List<Generator> generatorList = generatorMapper.listDeletedGenerator();
        List<String> keyList = generatorList.stream().map(Generator::getDistPath).filter(StrUtil::isNotBlank)
                // 移除 '/' 前缀
                .map(distPath -> distPath.substring(1)).collect(Collectors.toList());
        // 如果为空,就直接返回
        if (CollectionUtils.isEmpty(keyList)) {
            return;
        }
        // 删除对象存储文件
        cosManager.deleteObjects(keyList);
        log.info("clearCosJobHandler end");
    }
}
