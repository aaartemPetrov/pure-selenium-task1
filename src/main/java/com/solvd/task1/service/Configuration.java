package com.solvd.task1.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public abstract class Configuration {

    private static final Map<String, String> configuration = readConfiguration("config.properties");

    private static Map<String, String> readConfiguration(String configName) {
        Map<String, String> configuration = new HashMap<>();
        try {
            InputStream inputStream = Files.newInputStream(Paths.get("./src/main/resources/" + configName));
            Properties property = new Properties();
             property.load(inputStream);
            for(Object key : property.keySet()) {
                configuration.put((String)key, property.getProperty((String)key));
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return configuration;
    }

    public static String getProperty(String keyProperty) {
        return configuration.get(keyProperty);
    }

}
