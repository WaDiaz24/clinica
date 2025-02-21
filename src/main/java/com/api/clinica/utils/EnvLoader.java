package com.api.clinica.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EnvLoader {
    public static void loadEnv() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(".env"));

            properties.forEach((key, value) ->
                    System.setProperty(key.toString(), value.toString()));
        }catch (IOException e){
            System.out.println("Error loading .env file");
        }
    }
}
