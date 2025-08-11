package com.chatbot.selenium.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChatbotPomApplication {

    public static void main(String[] args) {
        System.setProperty("WebDriver.chrome.driver", "C:\\selenium-drivers\\Chrome\\chromedriver-win32");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.navigate().to("http://localhost:5000");
//        ChatbotLoginPom loginPom = new ChatbotLoginPom(driver);
        driver.close();
    }
}
