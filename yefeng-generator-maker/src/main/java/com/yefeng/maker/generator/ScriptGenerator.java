package com.yefeng.maker.generator;


import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

public class ScriptGenerator {
    public static void main(String[] args) throws IOException {
        String outputPath = System.getProperty("user.dir") + File.separator + "generator";
        doGenerate(outputPath, "");
    }

    public static void doGenerate(String outputPath, String jarPath) {
        StringBuilder builder = new StringBuilder();
        // Linux
//        builder.append("#!/bin/bash").append("\n");
//        builder.append(String.format("java -jar %s \"$@\"", jarPath)).append("\n");
//        FileUtil.writeBytes(builder.toString().getBytes(StandardCharsets.UTF_8), outputPath);
//        // 添加可执行权限
//        try {
//            Set<PosixFilePermission> permissions = PosixFilePermissions.fromString("rwxrwxrwx");
//            Files.setPosixFilePermissions(Paths.get(outputPath), permissions);
//        } catch (IOException e) {
//            // windows 直接忽略
//        }
        // windows
        builder = new StringBuilder();
        builder.append("@echo off").append("\n");
        builder.append(String.format("java -jar %s %%*", jarPath)).append("\n");
        FileUtil.writeBytes(builder.toString().getBytes(StandardCharsets.UTF_8), outputPath + ".bat");
    }
}
