package com.yefeng.maker.generator.file;

import java.io.File;

public class FileGenerator {

    /**
     * 生成静态代码和动态代码
     *
     * @param model 生成代码参数
     * @throws Exception 异常
     */
    public static void doGenerate(Object model) throws Exception {
        // 当前idea打开的窗口
        String projectPath = System.getProperty("user.dir");
        // 找到整个项目的根目录 yefeng-generator
        File parentFile = new File(projectPath).getParentFile();
        // 输入路径 ACM 的示例模板在 yefeng-generator-projects 目录下
        String inputPath = new File(parentFile + File.separator + "yefeng-generator-projects/acm-template").getAbsolutePath();
        // 生成静态文件
        StaticFileGenerator.copyFilesByHutool(inputPath, projectPath);
        // 生成动态文件，会覆盖部分已生成的静态文件
        String inputDynamicFilePath = projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String outputDynamicFilePath = projectPath + File.separator + "acm-template/src/com/yupi/acm/MainTemplate2.java";
        DynamicFileGenerator.doGenerate(inputDynamicFilePath, outputDynamicFilePath, model);
    }
}
