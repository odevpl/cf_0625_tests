import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginPageTest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://localhost:5000/login");

    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testLoginPageLoads() {
        String title = driver.getTitle();
        assertEquals("Logowanie", title, "Tytuł strony powinien być 'Logowanie'");
    }

    @Test
    void testFormElementsPresence() {
        WebElement emailField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("loginBtn"));

        assertTrue(emailField.isDisplayed(), "Pole email powinno być widoczne");
        assertTrue(passwordField.isDisplayed(), "Pole hasło powinno być widoczne");
        assertTrue(loginButton.isDisplayed(), "Przycisk logowania powinien być widoczny");
    }

    @Test
    void testSubmitForm() {
        WebElement emailField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("loginBtn"));

        emailField.sendKeys("test@example.com");
        passwordField.sendKeys("haslo123");
        loginButton.click();

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/login"), "Po wysłaniu formularza URL powinien wskazywać na /login");
    }
}

