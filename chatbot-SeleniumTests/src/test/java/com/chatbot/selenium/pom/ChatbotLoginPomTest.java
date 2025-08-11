package com.chatbot.selenium.pom;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

class ChatbotLoginPomTest {

    ChatbotLoginPom chatBotLoginPom;
    WebDriver driver;

    @BeforeEach
    void setup() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);
        driver.navigate().to("http://localhost:5000");
        chatBotLoginPom = new ChatbotLoginPom(driver);

    }

    @Test
    void chatbotRegistrationTest() {

    }

    @Test
    void chatbotLoginTest() {

    }

    @AfterEach
    void testDown() {
        driver.close();
    }
}
