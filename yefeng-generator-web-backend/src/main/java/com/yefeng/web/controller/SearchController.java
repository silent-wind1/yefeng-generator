package com.yefeng.web.controller;

import com.yefeng.web.common.BaseResponse;
import com.yefeng.web.model.dto.generator.GeneratorQueryRequest;
import com.yefeng.web.model.es.GeneratorES;
import com.yefeng.web.service.impl.SearchServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

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

    @PostMapping("/names")
    public BaseResponse<List<GeneratorES>> SearchGenerator(@RequestBody GeneratorQueryRequest generatorQueryRequest) throws IOException {
        return searchService.SearchGenerator(generatorQueryRequest);
    }
}
