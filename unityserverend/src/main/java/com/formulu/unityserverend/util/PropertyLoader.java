package com.formulu.unityserverend.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
    public static String dbConf(String key) {
        String value = "";
        try {
            InputStream inStream = PropertyLoader.class.getClassLoader().getResourceAsStream("db.properties");
            Properties prop = new Properties();
            prop.load(inStream);
            value = prop.getProperty(key);
        } catch (IOException ex) {
        }
        return value;
    }

    public static void main(String[] args) {
        System.out.print(dbConf("dbName"));
    }
}
