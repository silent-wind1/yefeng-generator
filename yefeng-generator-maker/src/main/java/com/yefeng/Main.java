package com.yefeng;

import com.yefeng.maker.generator.main.MainGenerator;

/**
 * 全局调用入口
 */
public class Main {
    public static void main(String[] args) {
        MainGenerator mainGenerator = new MainGenerator();
        try {
            mainGenerator.doGenerate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}