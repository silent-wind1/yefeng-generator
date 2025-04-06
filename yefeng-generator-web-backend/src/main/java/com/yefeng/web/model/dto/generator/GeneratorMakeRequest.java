package com.yefeng.web.model.dto.generator;

import com.yefeng.maker.meta.Meta;
import lombok.Data;

import java.io.Serializable;

/**
 * 更新请求
 */
@Data
public class GeneratorMakeRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 压缩文件路径
     */
    private String zipFilePath;
    /**
     * 元信息
     */
    private Meta meta;
}