package com.yefeng;

import com.yefeng.model.entity.Generator;
import com.yefeng.service.GeneratorService;
import com.yefeng.service.impl.SearchServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class GeneratorServiceTest {

    @Resource
    private GeneratorService generatorService;

    @Resource
    private SearchServiceImpl searchService;

    @Test
    public void testInsert() {
        Generator generator = generatorService.getById(18L);
        for (int i = 0; i < 100000; i++) {
            generator.setId(null);
            generatorService.save(generator);
        }
    }

    /**
     * 把数据导入Es
     */
    @Test
    public void import2Es() {
        List<Generator> list = generatorService.list();
        searchService.importIndex(list);
    }
}