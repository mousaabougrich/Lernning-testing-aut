package com.emsi.Tp2;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PlanTestRechercheGoogle {

    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    public static void setUpClass() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterAll
    public static void tearDownClass() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Order(1)
    @DisplayName("[TC-GOOGLE-001] Test recherche Google")
    public void testRechercheGoogle() {
        try {
            // Navigate to Google
            driver.get("https://www.google.com");

            // Accept cookies if present
            try {
                WebElement acceptButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(., 'Accept') or contains(., 'Accepter')]"))
                );
                acceptButton.click();
            } catch (Exception e) {
                // Cookies dialog may not appear
            }

            // Find search box
            WebElement searchBox = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.name("q"))
            );

            // Perform search
            String searchQuery = "Selenium WebDriver";
            searchBox.sendKeys(searchQuery);
            searchBox.sendKeys(Keys.RETURN);

            // Wait for results
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search")));

            // Verify results are displayed
            String pageTitle = driver.getTitle();
            assertTrue(pageTitle.contains(searchQuery), 
                      "Le titre de la page devrait contenir le terme de recherche");

            System.out.println("[TC-GOOGLE-001] Test recherche Google - RÉUSSI");

        } catch (Exception e) {
            fail("Test échoué avec une exception : " + e.getMessage());
        }
    }
}
