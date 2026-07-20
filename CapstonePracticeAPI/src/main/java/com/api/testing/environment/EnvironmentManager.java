package com.api.testing.environment;

import com.api.testing.utils.ConfigReader;

public class EnvironmentManager {

    private EnvironmentManager() {
        // Prevent object creation
    }

    public static String getBaseUrl() {
        return ConfigReader.getProperty("base.url");
    }

    public static String getEnvironment() {
        return ConfigReader.getProperty("environment");
    }
}