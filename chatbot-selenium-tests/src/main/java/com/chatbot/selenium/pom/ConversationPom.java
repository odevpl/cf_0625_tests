package com.chatbot.selenium.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ConversationPom {

    private WebDriver driver;

    private By conversationRows = By.cssSelector(".conversation-row");
    private By dateCell = By.cssSelector(".conversation-date");
    private By questionCell = By.cssSelector(".conversation-question");
    private By answerCell = By.cssSelector(".conversation-answer");

    public ConversationPom(WebDriver driver) {
        this.driver = driver;
    }

    public List<Conversation> getConversations() {
        List<WebElement> rows = driver.findElements(conversationRows);
        List<Conversation> conversations = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (WebElement row : rows) {
            String dateText = row.findElement(dateCell).getText();
            String question = row.findElement(questionCell).getText();
            String answer = row.findElement(answerCell).getText();

            LocalDateTime date = LocalDateTime.parse(dateText, formatter);

            conversations.add(new Conversation(date, question, answer));
        }
        return conversations;
    }

    public static class Conversation {
        public LocalDateTime date;
        public String question;
        public String answer;

        public Conversation(LocalDateTime date, String question, String answer) {
            this.date = date;
            this.question = question;
            this.answer = answer;
        }
    }
}
