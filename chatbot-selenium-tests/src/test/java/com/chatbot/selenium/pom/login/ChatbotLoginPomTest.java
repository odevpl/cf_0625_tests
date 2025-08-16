package com.chatbot.selenium.pom.login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class ChatbotLoginPomTest {

    ChatbotLoginPom chatBotLoginPom;
    WebDriver driver;

    @BeforeEach
    void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://localhost:5000/login");
        chatBotLoginPom = new ChatbotLoginPom(driver);
    }

    @Test
    void chatbotLoginFormAppearsTest() {
        //when
        boolean loginFormAppears = chatBotLoginPom.findLoginForm();
        //then
        Assertions.assertTrue(loginFormAppears);

    }

    @AfterEach
    void testDown() {
        driver.quit();
    }
}
