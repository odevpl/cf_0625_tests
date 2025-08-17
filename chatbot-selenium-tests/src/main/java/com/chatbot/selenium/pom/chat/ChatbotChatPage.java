package com.chatbot.selenium.pom.chat;

import com.chatbot.selenium.pom.AbstractWebDriverPom;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Objects;

public class ChatbotChatPage extends AbstractWebDriverPom {

    private static final String QUESTION_TO_CHATBOT = "Cześć, jak się masz?";

    @FindBy(xpath = "//*[@id=\"chat-form\"]")
    WebElement chatForm;

    @FindBy(xpath = "//*[@id=\"message\"]")
    WebElement questionField;

    @FindBy(css = "#chat-box > div:nth-child(1)")
    WebElement firstQuestionInChatForm;

    @FindBy(css = "#chat-box div")
    List<WebElement> chatbotResponse;

    @FindBy(xpath = "//*[@id=\"chat-form\"]/button")
    WebElement sendQuestionButton;

    public ChatbotChatPage(WebDriver driver) {
        super(driver);
    }

    public boolean findChatForm() {
        return chatForm.isDisplayed();
    }

    public void sendMessageToChatbot() {
        questionField.sendKeys(QUESTION_TO_CHATBOT);
        sendQuestionButton.click();
    }

    public boolean getQuestionSentToChatbot() {
        sendMessageToChatbot();
        return firstQuestionInChatForm.isDisplayed();
    }

    public String getChatbotAutomaticResponse() {
        sendMessageToChatbot();
        String text = "Brak odpowiedzi.";
        for (WebElement botResponse : chatbotResponse) {
            text = botResponse.getText();
        }
        return text;
    }
    public String  getAlertWhenNoQuestionSent(){
        sendQuestionButton.click();
        Alert alert = driver.switchTo().alert();
        String message = alert.getText();
        alert.dismiss();
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ChatbotChatPage that = (ChatbotChatPage) o;
        return Objects.equals(chatbotResponse, that.chatbotResponse);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(chatbotResponse);
    }
}
