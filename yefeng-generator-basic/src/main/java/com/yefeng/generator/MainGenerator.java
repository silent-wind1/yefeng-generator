package com.yefeng.generator;

import com.yefeng.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class MainGenerator {
    public static void main(String[] args) throws Exception {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("dexter");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("最终の求和结果：");
        doGenerate(mainTemplateConfig);
    }

    public static void doGenerate(Object model) throws Exception {
        // 生态静态文件路径
        String projectPath = System.getProperty("user.dir");
        String parentFile = new File(projectPath).getAbsolutePath();
        // 输入路径 ACM的示例模板 在 dexcode-generator-demo-projects 目录下
        String inputPath = new File(parentFile + File.separator + "yefeng-generator-projects/acm-template").getAbsolutePath();
        System.out.println(inputPath);
        // 输出路径
        String outputPath = projectPath;
        System.out.println(outputPath);
        StaticGenerator.copyFilesByHutool(inputPath, outputPath);


        // 生成动态文件，会覆盖部分已生成的静态文件
        String projectPaths = System.getProperty("user.dir") + File.separator + "yefeng-generator-basic";
        String inputDynamicFilePath = projectPaths + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String outputDynamicFilePath = projectPaths + File.separator + "MainTemplate2.java";
        try {
            DynamicGenerator.doGenerate(inputDynamicFilePath, outputDynamicFilePath, model);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
