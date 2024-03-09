package com.yefeng.maker.cli;


import com.yefeng.maker.cli.command.ConfigCommand;
import com.yefeng.maker.cli.command.GenerateCommand;
import com.yefeng.maker.cli.command.ListCommand;
import picocli.CommandLine;

@CommandLine.Command(name = "yefeng", mixinStandardHelpOptions = true)
public class CommandExecutor implements Runnable {
    private final CommandLine commandLine;

    {
        commandLine = new CommandLine(this)
                .addSubcommand(new GenerateCommand())
                .addSubcommand(new ConfigCommand())
                .addSubcommand(new ListCommand());
    }

    @Override
    public void run() {
        System.out.println("请输入具体命令，或者输入--help");
    }

    public Integer doExecute(String[] args) {
        return commandLine.execute(args);
    }
}
