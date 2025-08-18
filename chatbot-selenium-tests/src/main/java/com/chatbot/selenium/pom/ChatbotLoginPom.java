package com.chatbot.selenium.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChatbotLoginPom extends AbstractWebDriverPom {

    @FindBy(id = "email")
    WebElement emailInput;

    @FindBy(id = "password")
    WebElement passwordInput;

    @FindBy(xpath = "/html/body/form/button")
    WebElement loginButton;

    @FindBy(xpath = "/html/body/form")
    WebElement loginForm;

    public ChatbotLoginPom(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public boolean findLoginForm(){
        return loginForm.isDisplayed();
    }

    public void enterEmail(String email) {
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void submitForm() {
        loginButton.click();
    }
}
