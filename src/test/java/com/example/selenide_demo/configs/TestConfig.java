package com.example.selenide_demo.configs;

import lombok.Data;

public class TestConfig {

    private String testUrl;

    public String getTestUrl() {
        return testUrl;
    }

    public void setTestUrl(String testUrl) {
        this.testUrl = testUrl;
    }
}
