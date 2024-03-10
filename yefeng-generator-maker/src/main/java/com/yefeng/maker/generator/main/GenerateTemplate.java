package com.yefeng.maker.generator.main;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import com.yefeng.maker.generator.JarGenerator;
import com.yefeng.maker.generator.ScriptGenerator;
import com.yefeng.maker.generator.file.DynamicFileGenerator;
import com.yefeng.maker.meta.Meta;
import com.yefeng.maker.meta.MetaManager;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public abstract class GenerateTemplate {
    public void doGenerate() throws TemplateException, IOException, InterruptedException {
        Meta meta = MetaManager.getMetaObject();
        // 获取根路径
        String projectPath = System.getProperty("user.dir");
        String outputPath = projectPath + File.separator + "generator" + File.separator + meta.getName();
        if (!FileUtil.exist(outputPath)) {
            FileUtil.mkdir(outputPath);
        }

        // 复制原始的文件
        String sourceRootPath = meta.getFileConfig().getSourceRootPath();
        String sourceCopyPath = outputPath + File.separator + "./source";
        FileUtil.copy(sourceRootPath, sourceCopyPath, false);

        // 代码生成
        generateCode(meta, outputPath);

        // 构建 jar 包
        String jarPath = BuildJar(outputPath, meta);

        // 封装脚本
        String shellOutputFilePath = getBuildScript(outputPath, jarPath);

        // 生成精简版的程序
        buildDist(outputPath, sourceCopyPath, jarPath, shellOutputFilePath);
    }

    /**
     * 生成精简版的程序方法
     * @param outputPath 输出路径
     * @param sourceCopyPath 资源拷贝路径
     * @param jarPath jar路径
     * @param shellOutputFilePath  脚本文件输出路径
     */
    protected void buildDist(String outputPath, String sourceCopyPath, String jarPath, String shellOutputFilePath) {
        String distOutputPath = outputPath + "-dist";
        // 拷贝jar包
        String targetAbsolutePath = distOutputPath + File.separator + "target";
        FileUtil.mkdir(targetAbsolutePath);
        String jarAbsolutePath = outputPath + File.separator + jarPath;
        FileUtil.copy(jarAbsolutePath, targetAbsolutePath, true);
        // 拷贝脚本文件
//        FileUtil.copy(shellOutputFilePath, distOutputPath, true); // 这文件没有生成
        FileUtil.copy(shellOutputFilePath + ".bat", distOutputPath, true);
        // 拷贝模板文件
        FileUtil.copy(sourceCopyPath, distOutputPath, true);
    }

    /**
     * 封装脚本
     * @param outputPath 输出路径
     * @param jarPath jar路径
     * @return 脚本文件输出路径
     */
    protected String getBuildScript(String outputPath, String jarPath) {
        String shellOutputFilePath = outputPath + File.separator + "generator";
        ScriptGenerator.doGenerate(shellOutputFilePath, jarPath);
        return shellOutputFilePath;
    }

    /**
     *
     * @param outputPath  输出路径
     * @param meta 元信息
     * @return jar路径
     * @throws IOException
     * @throws InterruptedException
     */
    protected String BuildJar(String outputPath, Meta meta) throws IOException, InterruptedException {
        JarGenerator.doGenerate(outputPath);
        String jarName = String.format("%s-%s-jar-with-dependencies.jar", meta.getName(), meta.getVersion());
        return "target/" + jarName;
    }

    /**
     *
     * @param meta 元信息
     * @param outputPath 输出路径
     * @throws IOException
     * @throws TemplateException
     */
    protected void generateCode(Meta meta, String outputPath) throws IOException, TemplateException {
        // 读取resources目录
        ClassPathResource classPathResource = new ClassPathResource("");
        String inputResourcePath = classPathResource.getAbsolutePath();
        // Java包基础路径 com.yefeng
        String outputBasePackage = meta.getBasePackage();
        // com/yefeng
        String outputBasePackagePath = StrUtil.join("/", StrUtil.split(outputBasePackage, "."));
        String outputBaseJavaPackagePath = outputPath + File.separator + "src/main/java" + File.separator + outputBasePackagePath;

        String inputFilePath;
        String outputFilePath;

        // model.DataModel
        inputFilePath = inputResourcePath + File.separator + "templates/java/model/DataModel.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + File.separator + "/model/DataModel.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // cli.command.GenerateCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/GenerateCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + File.separator + "/cli/command/GenerateCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // cli.command.ConfigCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/ConfigCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/ConfigCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // cli.command.GenerateCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/GenerateCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/GenerateCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // cli.command.ListCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/ListCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/ListCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // cli.CommandExecutor
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/CommandExecutor.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/CommandExecutor.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // Main
        inputFilePath = inputResourcePath + File.separator + "templates/java/Main.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/Main.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // generator.StaticFileGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/StaticFileGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/StaticFileGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // generator.DynamicFileGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/DynamicFileGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/DynamicFileGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // generator.MainGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/FileGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/FileGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // README.md
        inputFilePath = inputResourcePath + File.separator + "templates/README.md.ftl";
        outputFilePath = outputPath + File.separator + "README.md";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // .gitignore
        inputFilePath = inputResourcePath + File.separator + "templates/.gitignore.ftl";
        outputFilePath = outputPath + File.separator + ".gitignore";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);
        // pom.xml
        inputFilePath = inputResourcePath + File.separator + "templates/pom.xml.ftl";
        outputFilePath = outputPath + File.separator + "pom.xml";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);
    }
}