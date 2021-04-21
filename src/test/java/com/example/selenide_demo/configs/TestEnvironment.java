package com.example.selenide_demo.configs;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.springframework.boot.test.context.TestConfiguration;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

@TestConfiguration
public class TestEnvironment {

    private static TestConfig testConfig;

    public static String testType = System.getProperty("env", "local");
    public static String rootPath = Paths.get(".").normalize().toAbsolutePath().toString();
    private static String testResourcesPath = Paths.get(rootPath, "/src/test/java/resources/").toString();
    private static String testPropertiesPath = Paths.get(testResourcesPath, "local_properties.yml").toString();

    public static TestConfig getTestConfig() {
        if (testConfig == null) {
            final File file = new File(Paths.get(TestEnvironment.testResourcesPath + "/" + TestEnvironment.testType.toLowerCase() + "_properties.yml").toString());
            Yaml yaml = new Yaml(new Constructor(TestConfig.class));
            try {
                testConfig = yaml.load(new FileInputStream(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return testConfig;
    }
}
