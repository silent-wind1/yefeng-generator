package com.yefeng.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yefeng.model.dto.generator.GeneratorQueryRequest;
import com.yefeng.model.es.GeneratorES;
import com.yefeng.service.impl.SearchServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Author: 叶枫
 * @Date: 2024/06/25/15:46
 * @Description:
 */
@Slf4j
@RestController("/search")
@RequiredArgsConstructor
public class SearchController {
    private final SearchServiceImpl searchService;

    @PostMapping("/list/page/vo")
    public Page<GeneratorES> SearchGenerator(@RequestBody GeneratorQueryRequest generatorQueryRequest) throws IOException {
        return searchService.SearchGenerator(generatorQueryRequest);
    }
}
