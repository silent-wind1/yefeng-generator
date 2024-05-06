package com.yefeng.generator;

import com.yefeng.model.MainTemplateConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 动态生成
 */
public class DynamicGenerator {
    public static void main(String[] args) throws IOException, TemplateException {
        // 当前idea打开的窗口
        String projectPath = System.getProperty("user.dir") + File.separator + "yefeng-generator-basic";
        String inputPath = projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String outputPath = projectPath + File.separator + "MainTemplate2.java";
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        // 这次使用循环
        mainTemplateConfig.setLoop(true);
        mainTemplateConfig.setAuthor("xiaoyu");
        mainTemplateConfig.setOutputText("求和结果：");
        doGenerate(inputPath, outputPath, mainTemplateConfig);
    }

    public static void doGenerate(String inputPath, String outputPath, Object model) throws IOException, TemplateException {
        // new 出 Configuration 对象，参数为 FreeMarker 版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

        File templateDir = new File(inputPath).getParentFile();
        // 指定模板文件所在的路径
        configuration.setDirectoryForTemplateLoading(templateDir);
        // 设置模板文件使用的字符集
        configuration.setDefaultEncoding("utf-8");
        // 创建模板对象，加载指定模板
        String templateName = new File(inputPath).getName();
        // 创建模板对象，加载指定模板, 解决中文乱码问题
        Template template = configuration.getTemplate(templateName, "utf-8");

        // 创建数据模型，从Main方法传递过来⏬

        // 生成
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(Paths.get(outputPath)), StandardCharsets.UTF_8));

        template.process(model, out);

        // 生成后关闭文件
        out.close();
    }


}
