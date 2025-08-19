package com.chatbot.selenium.pom.chat;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChatbotChatApplication {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:5000/chat");
        ChatbotChatPage chatbotChatPage = new ChatbotChatPage(driver);
        chatbotChatPage.sendMessageToChatbot();
        chatbotChatPage.getChatbotAutomaticResponse();
        driver.quit();
    }
}
