package com.yefeng.web.service.impl;

import cn.hutool.json.JSONUtil;
import com.yefeng.web.model.entity.Generator;
import com.yefeng.web.model.es.GeneratorES;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 叶枫
 * @Date: 2024/06/25/16:00
 * @Description:
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SearchServiceImpl {
    private final RestHighLevelClient highLevelClient;

    /**
     * 导入数据
     * @param list
     */
    public void importIndex(List<Generator> list) {
        BulkRequest bulkRequest = new BulkRequest();
        for (Generator generator : list) {
            IndexRequest index = new IndexRequest("generator").id(generator.getId().toString());
            GeneratorES generatorES = new GeneratorES(generator);
            generatorES.setUserAvatar("https://avatars.githubusercontent.com/u/62734268?v=4");
            index.source(JSONUtil.toJsonStr(generatorES), XContentType.JSON);
            bulkRequest.add(index);
            if (generator.getId() > 300){
                break;
            }
        }
        try {
            highLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
