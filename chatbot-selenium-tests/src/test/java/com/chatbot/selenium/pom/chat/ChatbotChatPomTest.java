package com.chatbot.selenium.pom.chat;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.util.Assert;

public class ChatbotChatPomTest {

    ChatbotChatPomApplication chatbotChatPomApplication;
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

        boolean chatFormAppears = chatbotChatPage.findChatForm();
        Assertions.assertTrue(chatFormAppears);
    }

    @Test
    void checkIfQuestionSubmittedAppearsInChatBoxTest() {
        boolean questionPresentInChatBox = chatbotChatPage.getQuestionSentToChatbot();
        Assertions.assertTrue(questionPresentInChatBox);
    }

    @Test
    void checkIfAutomaticChatbotResponseIsDisplayedInChatBox() {
        String automaticResponse = chatbotChatPage.getChatbotAutomaticResponse();
        Assertions.assertEquals("Brak odpowiedzi", automaticResponse);
    }
    @Test
    void sentEmptyMessageTest(){
        String alertText = chatbotChatPage.getAlertWhenNoQuestionSent();
        Assertions.assertEquals("Please fill out this field.", alertText);
    }

    @AfterEach
    void testDown() {
        driver.quit();
    }
}

