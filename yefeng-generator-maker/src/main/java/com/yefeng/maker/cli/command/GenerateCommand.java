package com.yefeng.maker.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.yefeng.maker.generator.file.FileGenerator;
import com.yefeng.maker.meta.Meta;
import lombok.Data;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "generate", description = "代码生成", mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Callable<Integer> {
    /**
     * 是否生成循环
     */
    @CommandLine.Option(names = {"-l", "--loop"}, arity = "0..1", description = "是否循环", interactive = true, echo = true)
    private boolean loop;
    /**
     * 作者注释
     */
    @CommandLine.Option(names = {"-a", "--author"}, arity = "0..1", description = "作者", interactive = true, echo = true)
    private String author;
    /**
     * 输出信息
     */
    @CommandLine.Option(names = {"-o", "--outputText"}, arity = "0..1", description = "输出", interactive = true, echo = true)
    private String outputText;

    @Override
    public Integer call() throws Exception {
        Meta meta = new Meta();
        BeanUtil.copyProperties(this, meta);
        System.out.println("配置信息" + meta);
        FileGenerator.doGenerate(meta);
        return 0;
    }
}
