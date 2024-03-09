package com.yefeng.maker.generator.file;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class StaticFileGenerator {
    public static void main(String[] args) {
        // 获取当前模块目录 F:\yefeng-generator 路径
        String projectPath = System.getProperty("user.dir");

        // 输入路径：ACM示例代码目录
        String inputPath = projectPath + File.separator + "yefeng-generator-projects" + File.separator + "acm-template";
        System.out.println(inputPath);

        // 输出路径：yefeng-generator-maker
        String outputPath = projectPath;
        copyFilesByHutool(inputPath, outputPath);
    }


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
