package ${basePackage}.generator;

import ${basePackage}.generator.StaticFileGenerator;
import ${basePackage}.generator.DynamicFileGenerator;
import ${basePackage}.model.DataModel;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

<#macro generateFile indent fileInfo>
    ${indent}inputPath = new File(inputRootPath, "${fileInfo.inputPath}").getAbsolutePath();
    ${indent}outputPath = new File(outputRootPath, "${fileInfo.outputPath}").getAbsolutePath();
    <#if fileInfo.generateType == "static">
        ${indent}StaticFileGenerator.copyFilesByHutool(inputPath, outputPath);
    <#else >
        ${indent}DynamicFileGenerator.doGenerate(inputPath, outputPath, model);
    </#if>
</#macro>

/**
* 核心生成器
*/
public class FileGenerator {

/**
* 生成
*
* @param model 数据模型
* @throws TemplateException
* @throws IOException
*/
public static void doGenerate(DataModel model) throws TemplateException, IOException {
String inputRootPath = "${fileConfig.inputRootPath}";
String outputRootPath = "${fileConfig.outputRootPath}";

String inputPath;
String outputPath;

<#-- 获取模型变量 -->
<#list modelConfig.models as modelInfo>
    <#-- 有分组 -->
    <#if modelInfo.groupKey??>
        <#list modelInfo.models as subModelInfo>
            ${subModelInfo.type} ${subModelInfo.fieldName} = model.${modelInfo.groupKey}.${subModelInfo.fieldName};
        </#list>
    <#else>
        ${modelInfo.type} ${modelInfo.fieldName} = model.${modelInfo.fieldName};
    </#if>
</#list>
<#list fileConfig.files as fileInfo>
    <#if fileInfo.groupKey??>

        <#if fileInfo.condition??>
            if (${fileInfo.condition}) {
            <#list fileInfo.files as fileInfo>

                <@generateFile indent="            " fileInfo=fileInfo />
            </#list>
            }
        <#else>

            <@generateFile indent="        " fileInfo=fileInfo />
        </#if>
    <#else>
        <#if fileInfo.condition??>
            if (${fileInfo.condition}) {

            <@generateFile indent="            " fileInfo=fileInfo />
            }
        <#else>

            <@generateFile indent="        " fileInfo=fileInfo />
        </#if>
    </#if>
</#list>
}
}