package com.chatbot.selenium.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChatbotLoginPom extends AbstractWebDriverPom {

    @FindBy(xpath = "/html/body/form")
    WebElement loginForm;

    public ChatbotLoginPom(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public boolean findLoginForm(){
        return loginForm.isDisplayed();
    }
}
