package com.yefeng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yefeng.model.entity.Generator;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * @author 29515
 * @description 针对表【generator(代码生成器)】的数据库操作Mapper
 * @createDate 2024-04-09 18:57:23
 * @Entity generator.domain.Generator
 */
public interface GeneratorMapper extends BaseMapper<Generator> {

    @Select("SELECT id, distPath FROM generator WHERE isDelete = 1")
    List<Generator> listDeletedGenerator();
}




