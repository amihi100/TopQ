package com.automation.infra;

import java.io.FileInputStream;
import java.util.Properties;

public class Configuration {
    private static Properties properties;
    private static final String CONFIG_FILE = "src/test/resources/config.properties";

    static {
        loadConfig();
    }

    private static void loadConfig() {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            properties.load(fis);
            LogUtils.info("Configuration loaded successfully");
        } catch (Exception e) {
            LogUtils.error("Failed to load configuration: " + e.getMessage());
            throw new RuntimeException("Failed to load config file", e);
        }
    }

    public static String getBaseUrl() {
        return getProperty("base.url");
    }

    public static String getProjectName() {
        return getProperty("project.name");
    }

    public static String getTaskName() {
        return getProperty("task.name");
    }

    public static String getUsername() {
        return getProperty("login.username");
    }

    public static String getPassword() {
        return getProperty("login.password");
    }

    public static int getWaitTimeoutSeconds() {
        return Integer.parseInt(getProperty("wait.timeout.seconds"));
    }

    private static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value.isEmpty()) {
            throw new RuntimeException("Property " + key + " not found in config file");
        }
        return value.trim();
    }
} 