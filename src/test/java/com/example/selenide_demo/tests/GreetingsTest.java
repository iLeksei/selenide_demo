package com.example.selenide_demo.tests;

import com.codeborne.selenide.Condition;
import com.example.selenide_demo.UiDriver;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;


import static com.codeborne.selenide.Selenide.*;
import static com.example.selenide_demo.pages.MainPage.openMainPage;

@Tag("example")
@Epic("Selenide testing demo")
@Feature("Greetings user")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Execution(ExecutionMode.CONCURRENT)
public class GreetingsTest extends UiDriver {

    @Test
    @Story("Use prompt and greeting user")
    public void shouldGreetUser() {
        openMainPage();
        fillPrompt();
    }

    @Step("Filling prompt and checking greeting")
    private static void fillPrompt() {
        Alert alert = Wait().until(ExpectedConditions.alertIsPresent());
        alert.sendKeys("Selenide");
        alert.accept();
        $(By.id("greetings")).shouldHave(Condition.ownText("Hello dear Selenide ! I wish you a great day!"));
    }
}
