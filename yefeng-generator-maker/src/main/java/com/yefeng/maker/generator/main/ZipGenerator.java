package com.yefeng.maker.generator.main;

/**
 *  生成代码生成器压缩包
 */
public class ZipGenerator extends GenerateTemplate {
    @Override
    protected String buildDist(String outputPath, String sourceCopyPath, String jarPath, String shellOutputFilePath) {
        String distPath = super.buildDist(outputPath, sourceCopyPath, jarPath, shellOutputFilePath);
        return super.buildZip(distPath);
    }
}
