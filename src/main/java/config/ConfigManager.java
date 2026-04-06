package config;

import io.restassured.config.Config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static final Properties properties = new Properties();

    static {
        try (InputStream is = Config.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (is == null) {
                throw new RuntimeException("config.properties file not found in resources");
            }
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String getBaseURL() {
        String envBaseURL = System.getenv("BASE_URL");
        if (envBaseURL != null && !envBaseURL.isEmpty()) {
            return envBaseURL;
        }
        return properties.getProperty("baseURL");
    }
}
