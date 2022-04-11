package main;

import java.io.IOException;
import java.util.Properties;

public class WbConfig {
    public static final String INSTANCE = "config.properties";

    private final Properties config;

    public WbConfig(String path) {
        config = new Properties();
        try {
            config.load(getClass().getClassLoader().getResourceAsStream(path));
        } catch (NullPointerException | IOException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public String get(Key key) {
        return config.getProperty(key.toString());
    }

    public String get(Key key, String defaultValue) {
        return config.getProperty(key.toString(), defaultValue);
    }

    public enum Key {
        HOSTNAME("hostname"),
        GECKO_PATH("geckodriverPath");

        private final String name;

        Key(String name) { this.name = name; }

        public String toString() { return name; }
    }
}
