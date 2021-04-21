package com.example.selenide_demo.pages;

import com.codeborne.selenide.WebDriverRunner;
import com.example.selenide_demo.configs.TestEnvironment;
import com.example.selenide_demo.utils.Timer;
import io.qameta.allure.Step;


import static com.codeborne.selenide.Selenide.open;

public class MainPage {

    @Step("Open main page")
    public static void openMainPage() {
        String url = TestEnvironment.getTestConfig().getTestUrl();
        System.out.println("Trying to open main page with url: " + url);

        open("about:blank");

        WebDriverRunner.webdriverContainer.getWebDriver().manage().window().maximize();
        WebDriverRunner.webdriverContainer.getWebDriver().navigate().to(url);

        Timer.wait(2000, "Waiting for opening browser");
    }

}
