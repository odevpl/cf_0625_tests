package com.chatbot.selenium.pom;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class LoginPagePomTest {

    private WebDriver driver;
    private ChatbotLoginPom loginPage;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://localhost:5000/login");
        loginPage = new ChatbotLoginPom(driver);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testLoginFormIsVisible() {
        Assertions.assertTrue(loginPage.findLoginForm(), "Formularz logowania powinien być widoczny");
    }

    @Test
    void testSubmitFormWithData() {
        loginPage.enterEmail("bozena");
        loginPage.enterPassword("hoho3");
        loginPage.submitForm();

        String currentUrl = driver.getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains("/login"), "Po wysłaniu formularza powinien nastąpić POST na /login");
    }
}
