package ${basePackage};

import ${basePackage}.cli.CommandExecutor;


/**
 * 全局调用入口
 */
public class Main {

    public static void main(String[] args) {
        CommandExecutor commandExecutor = new CommandExecutor();
        commandExecutor.doExecute(args);
    }
}