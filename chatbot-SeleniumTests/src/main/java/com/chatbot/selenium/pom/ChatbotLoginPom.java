package com.chatbot.selenium.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ChatbotLoginPom extends AbstractWebDriverPom {


    public ChatbotLoginPom(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
