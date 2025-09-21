package com.apitest.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class Helper {
    public static Dotenv dotenv;

    public static Dotenv loadEnv() {
        if (dotenv == null){
            dotenv = Dotenv.load();
        }
        return dotenv;
    }

    public static String getEnv(String key) {
        return loadEnv().get(key);
    }
}
