package com.yefeng.maker.generator;

import java.io.*;
import java.util.Map;

public class JarGenerator {
    public static void main(String[] args) throws IOException, InterruptedException {
        // 测试用
        doGenerate("F:/yefeng-generator/yefeng-generator-basic");
    }

    public static void doGenerate(String projectDir) throws IOException, InterruptedException {
        // 调用 Process 类执行 Maven 打包命令
        String winMavenCommand = "mvn.cmd clean package -Dmaven.test.skip=true";
        String otherMavenCommand = "mvn clean package -Dmaven.test.skip=true";
        String mavenCommand = winMavenCommand;

        // 空格拆分
        ProcessBuilder processBuilder = new ProcessBuilder(mavenCommand.split(" "));
        processBuilder.directory(new File(projectDir));
        Map<String, String> environment = processBuilder.environment();
        System.out.println(environment);

        Process process = processBuilder.start();

        // 读取命令输出
        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        int exitCode = process.waitFor();
        System.out.println("命令执行结束，退出码" + exitCode);
    }
}
