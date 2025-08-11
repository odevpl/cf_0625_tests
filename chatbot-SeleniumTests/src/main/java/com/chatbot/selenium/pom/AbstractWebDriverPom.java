package com.chatbot.selenium.pom;

import org.openqa.selenium.WebDriver;

public class AbstractWebDriverPom {

    protected WebDriver driver;

    public AbstractWebDriverPom(WebDriver driver) {
        this.driver = driver;
    }
}
