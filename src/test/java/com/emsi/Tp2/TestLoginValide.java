package com.emsi.Tp2;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestLoginValide {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private static String pageUrl;

    @BeforeAll
    public static void setUpClass() {
        // Configuration Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--headless"); // Run in headless mode for CI/CD
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Get the absolute path to connexion.html
        File htmlFile = new File("connexion.html");
        pageUrl = "file://" + htmlFile.getAbsolutePath();
    }

    @AfterAll
    public static void tearDownClass() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeEach
    public void setUp() {
        // Navigate to the login page before each test
        driver.get(pageUrl);
    }

    @Test
    @Order(1)
    @DisplayName("[TC-LOGIN-001] Test connexion valide")
    public void testConnexionValide() {
        try {
            // Wait for page to load
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));

            // Find elements
            WebElement usernameField = driver.findElement(By.id("username"));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("btn"));

            // Enter valid credentials
            usernameField.clear();
            usernameField.sendKeys("user");
            passwordField.clear();
            passwordField.sendKeys("password");

            System.out.println("[TC-LOGIN-001] Test connexion valide");
            System.out.println("   ✓ Identifiants saisis : user / password");

            // Click login button
            loginButton.click();

            // Wait for the message element to be updated with text
            WebElement messageElement = driver.findElement(By.id("loginMessage"));
            
            // Wait for the message to contain text (JavaScript needs time to execute)
            wait.until(ExpectedConditions.textToBePresentInElement(messageElement, "Connexion"));

            // Get the message text and CSS class
            String messageText = messageElement.getText();
            String cssClass = messageElement.getAttribute("class");
            String messageColor = messageElement.getCssValue("color");

            System.out.println("   ✓ Message : " + messageText);
            System.out.println("   ✓ Classe CSS : " + cssClass);

            // Assertions
            assertNotNull(messageText, "Le message ne doit pas être null");
            assertFalse(messageText.isEmpty(), "Le message ne doit pas être vide");
            assertTrue(messageText.contains("réussie") || messageText.contains("Connexion réussie"), 
                      "Le message doit indiquer une connexion réussie");
            
            // Verify the color is green (can be in different formats)
            assertTrue(messageColor.contains("0, 128, 0") || messageColor.contains("green"), 
                      "Le message doit être affiché en vert");

        } catch (Exception e) {
            fail("Test échoué avec une exception : " + e.getMessage());
        }
    }

    @Test
    @Order(2)
    @DisplayName("[TC-LOGIN-002] Test connexion invalide")
    public void testConnexionInvalide() {
        try {
            // Wait for page to load
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));

            // Find elements
            WebElement usernameField = driver.findElement(By.id("username"));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("btn"));

            // Enter invalid credentials
            usernameField.clear();
            usernameField.sendKeys("mauvais_user");
            passwordField.clear();
            passwordField.sendKeys("mauvais_password");

            System.out.println("[TC-LOGIN-002] Test connexion invalide");
            System.out.println("   ✓ Identifiants saisis : mauvais_user / mauvais_password");

            // Click login button
            loginButton.click();

            // Wait for the message element to be updated with text
            WebElement messageElement = driver.findElement(By.id("loginMessage"));
            
            // Wait for the message to contain text
            wait.until(ExpectedConditions.textToBePresentInElement(messageElement, "incorrect"));

            // Get the message text
            String messageText = messageElement.getText();
            String messageColor = messageElement.getCssValue("color");

            System.out.println("   ✓ Message : " + messageText);

            // Assertions
            assertNotNull(messageText, "Le message ne doit pas être null");
            assertFalse(messageText.isEmpty(), "Le message ne doit pas être vide");
            assertTrue(messageText.contains("incorrect") || messageText.contains("Nom d'utilisateur ou mot de passe incorrect"), 
                      "Le message doit indiquer une erreur de connexion");
            
            // Verify the color is red
            assertTrue(messageColor.contains("255, 0, 0") || messageColor.contains("red"), 
                      "Le message doit être affiché en rouge");

        } catch (Exception e) {
            fail("Test échoué avec une exception : " + e.getMessage());
        }
    }
}
