package com.apitest.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.response.Response;

public class Helper {
    public static Dotenv dotenv;
    private static ObjectMapper objectMapper, mapper = new ObjectMapper();
    private static final String DATA_PATH = "src/test/resources/data/";

    public static Dotenv loadDotenv() {
        dotenv = Dotenv.load();
        return dotenv;
    }

    public static String getEnv(String key) {
        return loadDotenv().get(key);
    }

    public static <T> T convertResponseToObject(Response response, Class<T> clazz) {
        // ObjectMapper objectMapper = new ObjectMapper();
        // ResponseCreateBooking responseCreateBooking =
        // objectMapper.readValue(response.asString(), ResponseCreateBooking.class);
        objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(response.asString(), clazz);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert response to class: " + clazz.getSimpleName(), e);
        }
    }

    /**
     * Convert POJO object to JSON string
     */
    public static String convertToJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert object to JSON", e);
        }
    }

    /**
     * Convert raw JSON string to POJO class.
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Failed to map JSON to class " + clazz.getSimpleName(), e);
        }
    }

    /**
     * Read JSON file and convert to POJO class.
     */
    public static <T> T fromJsonFile(String filePath, Class<T> clazz) {
        try {
            System.out.println(DATA_PATH + filePath);
            return mapper.readValue(new File(DATA_PATH + filePath), clazz);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON file: " + filePath, e);
        }
    }

    /**
     * Read JSON file contain multiple data
     */
    public static <T> List<T> fromJsonFileArray(String filePath, Class<T> clazz) {
        try {
            return mapper.readValue(
                    new File(DATA_PATH + filePath),
                    mapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (Exception e) {
            throw new RuntimeException(DATA_PATH + filePath, e);
        }
    }

    /**
     * Get specific payload from JSON file contain multiple data
     * meaning <T> return type is generic
     * 
     * @param filePath
     */
    public static <T> T findByUseCase(String filePath, String usecase, Class<T> clazz) {
        try {
            String fullPath = DATA_PATH + filePath;
            System.out.println("Looking for file: " + fullPath);

            File file = new File(fullPath);
            System.out.println("File exists: " + file.exists());
            System.out.println("Absolute path: " + file.getAbsolutePath());

            if (!file.exists()) {
                throw new RuntimeException("File not found: " + fullPath);
            }

            JsonNode rootNode = mapper.readTree(new File(DATA_PATH + filePath));
            for (JsonNode node : rootNode) {
                if (node.get("usecase").asText().equals(usecase)) {
                    JsonNode payloadNode = node.get("payload");
                    return mapper.treeToValue(payloadNode, clazz);
                }
            }
            throw new RuntimeException("Usecase not found: " + usecase);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get payload from file: " + filePath, e);
        }
    }
}
