package com.yefeng;

import com.yefeng.cli.CommandExecutor;

/**
 * 全局调用入口
 */
public class Main {
    public static void main(String[] args) {
//        args = new String[]{"generate", "-l", "-a", "-o"};
        args = new String[]{"json-generate", "--file=F:/Harem/yefeng-generator/yefeng-generator-basic/test.json"};
//        args = new String[]{"config"};
//        args = new String[]{"list"};
        CommandExecutor commandExecutor = new CommandExecutor();
        commandExecutor.doExecute(args);
    }
}