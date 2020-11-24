package com.base;


import java.io.*;
import java.util.Properties;

public class Utilities {

    public static String getPropertyFromConfig(String key) {
        Properties properties = new Properties();
        String filePath = "src/test/resources/config.properties";
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(filePath);
            properties.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String value = properties.getProperty(key);
        return value;
    }

    public static String readTextFile(String filePath) {
        String container;
        String finalText = "";
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((container = bufferedReader.readLine()) != null) {
                finalText = finalText + '\n' + container;
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return finalText;
    }
}