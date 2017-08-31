package com.fan.kafka;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesUtils {

    public static Properties getProperties(String filePath) throws Exception {

        Properties props = new Properties();

        File configFile = new File(filePath);
        if (!configFile.exists()) {
            throw new Exception("未找到配置文件:" + configFile.getAbsolutePath());
        }
        FileInputStream in = new FileInputStream(configFile.getAbsolutePath());
        props.load(in);
        in.close();
        return props;
    }

    public static Properties getProperties(File configFile) throws Exception {
        Properties props = new Properties();
        if (!configFile.exists()) {
            throw new Exception("未找到配置文件:" + configFile.getAbsolutePath());
        }
        FileInputStream in = new FileInputStream(configFile.getAbsolutePath());
        props.load(in);
        in.close();
        return props;
    }

    public static long getLong(Properties properties, String key, long defaultValue) {
        try {
            defaultValue = Long.valueOf(properties.getProperty(key).trim()).longValue();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return defaultValue;
    }
    
    public static int getInt(Properties properties, String key, int defaultValue) {
        try {
            defaultValue = Integer.valueOf(properties.getProperty(key).trim()).intValue();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return defaultValue;
    }
    

    public static String getString(Properties properties, String key, String defaultValue) {
        String temp = properties.getProperty(key);
        if (temp != null && !temp.trim().equals("")) {
            defaultValue = temp.trim();
        }

        return defaultValue;
    }

}
