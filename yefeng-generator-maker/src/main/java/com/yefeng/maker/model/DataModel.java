package com.yefeng.maker.model;

import lombok.Data;

/**
 * 动态模板配置
 */
@Data
public class DataModel {
    /**
     * 是否生成循环
     */
    private boolean loop = false;
    /**
     * 作者注释
     */
    private String author = "yefeng";
    /**
     * 输出信息
     */
    private String outputText = "you can make it";
}