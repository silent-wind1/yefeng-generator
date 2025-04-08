package com.yefeng.model.dto.generator;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 更新请求
 */
@Data
public class GeneratorUseRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 数据模型
     */
    Map<String, Object> dataModel;
    /**
     * id
     */
    private Long id;
}