package com.apitest.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class Helper {
    public static Dotenv dotenv;

    public static Dotenv loadDotenv() {
        dotenv = Dotenv.load();
        return dotenv;
    }

    public static String getEnv(String key) {
        return loadDotenv().get(key);
    }
}
