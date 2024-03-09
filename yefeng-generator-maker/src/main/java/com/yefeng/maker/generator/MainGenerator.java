package com.yefeng.maker.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import com.yefeng.maker.generator.file.DynamicFileGenerator;
import com.yefeng.maker.meta.Meta;
import com.yefeng.maker.meta.MetaManager;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class MainGenerator {
    public static void main(String[] args) throws TemplateException, IOException {
        Meta meta = MetaManager.getMetaObject();
//        System.out.println(meta);

        // 获取根路径
        String projectPath = System.getProperty("user.dir");
        String outputPath = projectPath + File.separator + "generator" + File.separator + meta.getName();
        if(!FileUtil.exist(outputPath)) {
            FileUtil.mkdir(outputPath);
        }
        // 读取resources目录
        ClassPathResource classPathResource = new ClassPathResource("");
        String inputResourcePath = classPathResource.getAbsolutePath();
        // Java包基础路径 com.yefeng
        String outputBasePackage = meta.getBasePackage();
        // com/yefeng
        String outputBasePackagePath = StrUtil.join("/", StrUtil.split(outputBasePackage, "."));
        String FinalPath = outputPath + File.separator + "src/main/java" + File.separator + outputBasePackagePath;

        String inputFilePath;
        String outputFilePath;

        // model.DataModel
        inputFilePath = inputResourcePath + File.separator + "templates/java/model/DataModel.java.ftl";
        outputFilePath = FinalPath + File.separator + "/model/DataModel.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);
    }
}
