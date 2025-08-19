package com.chatbot.selenium.pom.chat;

import com.chatbot.selenium.pom.AbstractWebDriverPom;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ChatbotChatPage extends AbstractWebDriverPom {

    private static final String QUESTION_TO_CHATBOT = "Cześć, jak się masz?";

    @FindBy(xpath = "//*[@id=\"chat-form\"]")
    WebElement chatForm;

    @FindBy(xpath = "//*[@id=\"message\"]")
    WebElement questionField;

    @FindBy(xpath = "//*[@id=\"chat-box\"]/div[1]")
    WebElement firstQuestionInChatBox;

    @FindBy(css = "#chat-box > div")
    List<WebElement> conversationInChatBox;

    @FindBy(xpath = "//*[@id=\"chat-form\"]/button")
    WebElement submitButton;

    public ChatbotChatPage(WebDriver driver) {
        super(driver);
    }

    public boolean findChatForm() {
        return chatForm.isDisplayed();
    }

    public boolean getQuestionSentToChatbot() {
        sendMessageToChatbot();
        return firstQuestionInChatBox.isDisplayed();
    }

    public String getChatbotAutomaticResponse() {
        sendMessageToChatbot();
        int oldCount = conversationInChatBox.size(); //current number of messages in chabox

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));  //waiting for a new answer
        wait.until((ExpectedCondition<Boolean>) d -> {
            assert d != null;
            List<WebElement> updatedMessages = d.findElements(By.cssSelector("#chat-box > div"));
            return updatedMessages.size() > oldCount;
        });

        List<WebElement> updatedMessages = driver.findElements(By.cssSelector("#chat-box > div"));
        WebElement newMessage = updatedMessages.get(updatedMessages.size() - 1);
        System.out.println(newMessage.getText());
        return newMessage.getText();
    }

    public void sendMessageToChatbot() {
        questionField.sendKeys(QUESTION_TO_CHATBOT);
        submitButton.click();
    }

    void closeChatbotPage() {
        driver.quit();
    }
}



