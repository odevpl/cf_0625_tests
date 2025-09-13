package com.chatbot.selenium.pom.chat;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class ChatbotChatTest {

    ChatbotChatPage chatbotChatPage;
    WebDriver driver;

    @BeforeEach
    void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://localhost:5000/chat");
        chatbotChatPage = new ChatbotChatPage(driver);
    }

    @Test
    void chatbotChatFormAppearsTest() {
        Assertions.assertTrue(chatbotChatPage.findChatForm());
    }

    @Test
    void checkIfQuestionSubmittedAppearsInChatBoxTest() {
        Assertions.assertTrue(chatbotChatPage.getQuestionSentToChatbot());
    }

    @Test
    void checkIfAutomaticChatbotResponseIsDisplayedInChatBox() {
        Assertions.assertEquals("Brak odpowiedzi.", chatbotChatPage.getChatbotAutomaticResponse());
    }

    @AfterEach
    void testDown() {
        driver.quit();
    }
}

