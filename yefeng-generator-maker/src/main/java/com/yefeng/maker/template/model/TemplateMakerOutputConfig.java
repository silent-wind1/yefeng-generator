package com.yefeng.maker.template.model;

import lombok.Data;

/**
 * 模版制作输出配置
 */
@Data
public class TemplateMakerOutputConfig {

    // 从未分组文件中移除组内的同名文件
    private boolean removeGroupFilesFromRoot = true;
}