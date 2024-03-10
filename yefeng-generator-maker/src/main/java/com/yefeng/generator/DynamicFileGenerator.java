package com.yefeng.generator;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 动态生成
 */
public class DynamicFileGenerator {
    public static void doGenerate(String inputPath, String outputPath, Object model) {
        try {
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
