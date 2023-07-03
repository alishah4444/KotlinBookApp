package com.iaminca.openai.utils.python;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Slf4j
public class PythonExecutor {

    public static String execute(String pythonUrl,String siteUrl) {

        String pythonScriptPath = pythonUrl;


        // 构建命令行参数
        String[] command = {"python3.9", pythonScriptPath,siteUrl};


        // 等待命令执行完毕
        try {
            // 执行命令
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
            Process process = processBuilder.start();

            // 读取命令输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder output = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }
            process.waitFor();
            // 输出结果
            String pythonResult = output.toString();
            log.info("Output: {}" , pythonResult);
            return pythonResult;
        } catch (Exception e) {
            log.error("PythonExecutor: error");
            e.printStackTrace();
        }
        return null;
    }
}
