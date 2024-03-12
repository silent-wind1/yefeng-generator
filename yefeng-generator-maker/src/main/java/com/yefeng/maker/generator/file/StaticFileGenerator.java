package com.yefeng.maker.generator.file;


import cn.hutool.core.io.FileUtil;

import java.io.File;

public class StaticFileGenerator {

    /**
     * 拷贝文件（实现方式1：Hutool实现）
     *
     * @param inputPath  输入目录
     * @param outputPath 输出目录
     */
    public static void copyFilesByHutool(String inputPath, String outputPath) {
        FileUtil.copy(inputPath, outputPath, false);
    }

}
