package com.example.selenide_demo;

import com.codeborne.selenide.Configuration;
import com.example.selenide_demo.configs.TestEnvironment;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class UiDriver extends TestLogger {

    @BeforeEach
    public void setUpDriver() {
        Configuration.baseUrl = "about:blank";
        Configuration.proxyEnabled = false;
        Configuration.screenshots = true;

        if ("local".equals(TestEnvironment.testType)) {
            System.out.println("Create local web-driver");
            createLocalDriver();
        } else {
            System.out.println("Create remote web-driver");
            createRemoteDriver();
        }
    }

    private void createLocalDriver() {
        try {
            WebDriverManager
                    .chromedriver()
                    .arch64()
                    .setup();
        } catch (Exception e) {
            System.out.println("Got error during setup chrome-driver");
        }
        Configuration.browser = "Chrome";
        final DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--window-size=1920,1080");
        chromeOptions.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
        chromeOptions.setCapability(CapabilityType.PLATFORM_NAME, Platform.WIN10);
        chromeOptions.setCapability(CapabilityType.SUPPORTS_ALERTS, true);
        chromeOptions.setCapability("browserFocus", true);
        chromeOptions.setCapability("name", "SELENIDE_TEST | ".concat(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
        chromeOptions.setCapability("sessionTimeout", "5m");

        desiredCapabilities.setBrowserName("Chrome");
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

    }

    private void createRemoteDriver() {
        final DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--window-size=1920,1080");

        chromeOptions.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
        chromeOptions.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
        chromeOptions.setCapability("browserFocus", true);
        chromeOptions.setCapability("name", "SELENIDE_TEST | ".concat(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
        chromeOptions.setCapability("sessionTimeout", "5m");

        desiredCapabilities.setBrowserName("Chrome");
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        Configuration.remote = TestEnvironment.getTestConfig().getTestUrl();
        Configuration.browser = "Chrome";
        Configuration.browserCapabilities = desiredCapabilities;
    }

}
