package com.yefeng.maker;

import com.yefeng.maker.generator.main.GenerateTemplate;
import com.yefeng.maker.generator.main.MainGenerator;
import com.yefeng.maker.generator.main.ZipGenerator;

/**
 * 全局调用入口
 */
public class Main {
    public static void main(String[] args) {
        GenerateTemplate mainGenerator = new MainGenerator();
        GenerateTemplate zipGenerator = new ZipGenerator();
        try {
//            args = new String[]{"generate", "--needGit=false"};
//            mainGenerator.doGenerate();
            zipGenerator.doGenerate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}