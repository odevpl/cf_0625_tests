package com.chatbot.selenium.pom;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ConversationPomTest {
    private WebDriver driver;
    private ConversationPom conversationPom;

//    @BeforeAll
//    void setupDriver() {
//        WebDriverManager.chromedriver().setup();
//    }

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("http://localhost:8080/conversations");
        conversationPom = new ConversationPom(driver);
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testConversationsAreDisplayed() {
        List<ConversationPom.Conversation> conversations = conversationPom.getConversations();
        assertFalse(conversations.isEmpty(), "The conversation list should not be empty");
    }

    @Test
    void testConversationsHaveDataQuestionAnswer() {
        List<ConversationPom.Conversation> conversations = conversationPom.getConversations();

        for (ConversationPom.Conversation c : conversations) {
            assertNotNull(c.date, "Data should not be null");
            assertFalse(c.question.isEmpty(), "The question should not be empty");
            assertFalse(c.answer.isEmpty(), "The answer should not be empty");
        }
    }

    @Test
    void testConversationsAreSortedByDateDescending() {
        List<ConversationPom.Conversation> conversations = conversationPom.getConversations();

        for (int i = 0; i < conversations.size() - 1; i++) {
            LocalDateTime current = conversations.get(i).date;
            LocalDateTime next = conversations.get(i + 1).date;
            assertTrue(current.isAfter(next) || current.isEqual(next),
                    "Conversations should be sorted descending by date");
        }
    }
}
