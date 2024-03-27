package com.yefeng.maker;

import com.yefeng.maker.generator.main.MainGenerator;

/**
 * 全局调用入口
 */
public class Main {
    public static void main(String[] args) {
        MainGenerator mainGenerator = new MainGenerator();
        try {
//            args = new String[]{"generate", "--needGit=false"};
            mainGenerator.doGenerate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}