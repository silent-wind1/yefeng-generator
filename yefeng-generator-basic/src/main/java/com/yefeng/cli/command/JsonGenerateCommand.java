package com.yefeng.cli.command;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import com.yefeng.generator.MainGenerator;
import com.yefeng.model.MainTemplateConfig;
import lombok.Data;
import picocli.CommandLine;

import java.util.concurrent.Callable;

/**
 * 读取JSON文件生成代码
 */
@CommandLine.Command(name = "json-generate", description = "Json文件代码生成", mixinStandardHelpOptions = true)
@Data
public class JsonGenerateCommand implements Callable<Integer> {

    @CommandLine.Option(names = {"-f", "--file"}, arity = "0..1", description = "json 文件", interactive = true, echo = true)
    private String filePath;

    @Override
    public Integer call() throws Exception {
        String jsonStr = FileUtil.readUtf8String(filePath);
        MainTemplateConfig dataModel = JSONUtil.toBean(jsonStr, MainTemplateConfig.class);
        MainGenerator.doGenerate(dataModel);
        return 0;
    }
}
