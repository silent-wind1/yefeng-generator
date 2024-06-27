package com.yefeng.web.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yefeng.web.model.dto.generator.GeneratorQueryRequest;
import com.yefeng.web.model.entity.Generator;
import com.yefeng.web.model.es.GeneratorES;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
     *
     * @param list
     */
    public void importIndex(List<Generator> list) {
        BulkRequest bulkRequest = new BulkRequest();
        for (Generator generator : list) {
            IndexRequest index = new IndexRequest("generator").id(generator.getId().toString());
            GeneratorES generatorES = new GeneratorES();
            BeanUtils.copyProperties(generator, generatorES);
            generatorES.setUserAvatar("https://avatars.githubusercontent.com/u/62734268?v=4");
            index.source(JSONUtil.toJsonStr(generatorES), XContentType.JSON);
            bulkRequest.add(index);
            if (generator.getId() > 300) {
                break;
            }
        }
        try {
            highLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public Page<GeneratorES> SearchGenerator(@RequestBody GeneratorQueryRequest generatorQueryRequest) throws IOException {

        int current = generatorQueryRequest.getCurrent();
        int size = generatorQueryRequest.getPageSize();

        // 设置查询条件
        SearchRequest request = new SearchRequest("generator");
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        if (StringUtils.isNotBlank(generatorQueryRequest.getName())) {
            log.info("name = {}", generatorQueryRequest.getName());
            boolQuery.must(QueryBuilders.matchQuery("name", generatorQueryRequest.getName()));
        }
        // 设置高亮
        request.source().highlighter(
                SearchSourceBuilder.highlight()
                        .field("name")
                        .preTags("<em>")
                        .postTags("</em>")
        );
        // 添加查询条件
        request.source().query(boolQuery);
        //设置分页
        request.source().from((current - 1) * size).size(size);
        Page<GeneratorES> page = new Page<>(current, size);
        // 3.发送请求
        SearchResponse response = highLevelClient.search(request, RequestOptions.DEFAULT);
        Page<GeneratorES> listBaseResponse = basicQuery(response, page);
        return listBaseResponse;
    }

    private Page<GeneratorES> basicQuery(SearchResponse response, Page<GeneratorES> page) {
        log.info("response = {}", response);
        List<GeneratorES> list = new ArrayList<>();
        SearchHits searchHits = response.getHits();
        long total = searchHits.getTotalHits().value;
        page.setTotal(total);
        if (total == 0) {
            return null;
        }
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            // 3.得到_source，也就是原始json文档
            String source = hit.getSourceAsString();
            log.info("source = {}", source);
            // 4.反序列化并打印
            GeneratorES item = JSONUtil.toBean(source, GeneratorES.class);
            // 获取高亮结果
            Map<String, HighlightField> fieldMap = hit.getHighlightFields();
            if(CollUtil.isNotEmpty(fieldMap)) {
                HighlightField highlightField = fieldMap.get("name");
                if (highlightField != null) {
                    String name = highlightField.getFragments()[0].string();
                    item.setName(name);
                }
            }
            list.add(item);
        }
        page.setRecords(list);
        return page;
    }
}

