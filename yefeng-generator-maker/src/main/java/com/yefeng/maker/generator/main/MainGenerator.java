package com.yefeng.maker.generator.main;

public class MainGenerator extends GenerateTemplate{

    @Override
    protected String buildDist(String outputPath, String sourceCopyPath, String jarPath, String shellOutputFilePath) {
        System.out.println("I dont build dist");
        return "";
    }
}
