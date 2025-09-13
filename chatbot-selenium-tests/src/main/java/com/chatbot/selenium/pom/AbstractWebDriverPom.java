package com.chatbot.selenium.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractWebDriverPom {

    protected WebDriver driver;

    public AbstractWebDriverPom(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
