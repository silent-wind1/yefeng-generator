package com.yefeng.generator;


import cn.hutool.core.io.FileUtil;

public class StaticFileGenerator {
    /**
     * 拷贝文件
     *
     * @param inputPath  输入目录
     * @param outputPath 输出目录
     */
    public static void copyFilesByHutool(String inputPath, String outputPath) {
        FileUtil.copy(inputPath, outputPath, false);
    }
}
