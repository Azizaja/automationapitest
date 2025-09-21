package com.apitest.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class Helper {
    public static Dotenv dotenv;

    public static Dotenv loadEnv() {
        dotenv = Dotenv.load();
        return dotenv;
    }

    public static String getEnv(String key) {
        if (dotenv == null) {
            loadEnv();
        }
        return dotenv.get(key);
    }
}
