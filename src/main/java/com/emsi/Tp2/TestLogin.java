package com.emsi.Tp2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class TestLogin {

    public static void main(String[] args) {
        WebDriver driver = null;

        try {
            // Configuration Chrome
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--disable-notifications");

            driver = new ChromeDriver(options);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // URL de la page de connexion (ajustez selon votre configuration)
            String pageUrl = "file:///C:/Users/mousaab/Desktop/Java/Sel-udem/connexion.html";

            System.out.println("=== DÉBUT DES TESTS DE CONNEXION ===\n");

            // TEST 1 : Connexion avec des identifiants incorrects
            System.out.println("📋 TEST 1 : Connexion avec identifiants incorrects");
            testInvalidLogin(driver, wait, pageUrl);

            Thread.sleep(2000); // Pause entre les tests

            // TEST 2 : Connexion avec des identifiants valides
            System.out.println("\n📋 TEST 2 : Connexion avec identifiants valides");
            testValidLogin(driver, wait, pageUrl);

            System.out.println("\n=== TOUS LES TESTS TERMINÉS ===");

        } catch (Exception e) {
            System.err.println("❌ Erreur lors de l'exécution des tests : " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Fermeture du navigateur
            if (driver != null) {
                try {
                    Thread.sleep(3000); // Pause pour voir le résultat
                    driver.quit();
                    System.out.println("✓ Navigateur fermé avec succès");
                } catch (InterruptedException e) {
                    System.err.println("Erreur lors de la fermeture : " + e.getMessage());
                }
            }
        }
    }

    /**
     * Test de connexion avec des identifiants incorrects
     */
    private static void testInvalidLogin(WebDriver driver, WebDriverWait wait, String pageUrl) {
        try {
            // Ouvrir la page de connexion
            driver.get(pageUrl);
            System.out.println("✓ Page de connexion ouverte");

            // Attendre que la page soit chargée
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));

            // Saisir des identifiants incorrects
            WebElement usernameField = driver.findElement(By.id("username"));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("btn"));

            usernameField.clear();
            usernameField.sendKeys("mauvais_user");
            System.out.println("✓ Nom d'utilisateur incorrect saisi : 'mauvais_user'");

            passwordField.clear();
            passwordField.sendKeys("mauvais_password");
            System.out.println("✓ Mot de passe incorrect saisi : 'mauvais_password'");

            // Cliquer sur le bouton de connexion
            loginButton.click();
            System.out.println("✓ Bouton de connexion cliqué");

            // Attendre et vérifier le message d'erreur
            WebElement messageElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginMessage")));
            String messageText = messageElement.getText();

            System.out.println("📄 Message reçu : '" + messageText + "'");

            // Vérifier que le message contient une indication d'erreur
            if (messageText.contains("incorrect") || messageText.contains("Erreur") ||
                messageText.contains("Invalid") || messageText.contains("Nom d'utilisateur ou mot de passe incorrect")) {
                System.out.println("✅ TEST 1 RÉUSSI : Message d'erreur détecté correctement");
            } else {
                System.out.println("❌ TEST 1 ÉCHOUÉ : Message d'erreur non détecté");
                System.out.println("   Message attendu : contenant 'incorrect' ou 'Erreur'");
                System.out.println("   Message reçu : '" + messageText + "'");
            }

        } catch (Exception e) {
            System.out.println("❌ TEST 1 ÉCHOUÉ : Erreur lors du test - " + e.getMessage());
        }
    }

    /**
     * Test de connexion avec des identifiants valides
     */
    private static void testValidLogin(WebDriver driver, WebDriverWait wait, String pageUrl) {
        try {
            // Ouvrir la page de connexion
            driver.get(pageUrl);
            System.out.println("✓ Page de connexion ouverte");

            // Attendre que la page soit chargée
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));

            // Saisir des identifiants valides (selon le script JavaScript)
            WebElement usernameField = driver.findElement(By.id("username"));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("btn"));

            usernameField.clear();
            usernameField.sendKeys("user");
            System.out.println("✓ Nom d'utilisateur valide saisi : 'user'");

            passwordField.clear();
            passwordField.sendKeys("password");
            System.out.println("✓ Mot de passe valide saisi : 'password'");

            // Cliquer sur le bouton de connexion
            loginButton.click();
            System.out.println("✓ Bouton de connexion cliqué");

            // Attendre et vérifier le message de succès
            WebElement messageElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginMessage")));
            String messageText = messageElement.getText();

            System.out.println("📄 Message reçu : '" + messageText + "'");

            // Vérifier que le message indique une connexion réussie
            if (messageText.contains("réussie") || messageText.contains("succès") ||
                messageText.contains("Connexion réussie") || messageText.contains("Success")) {
                System.out.println("✅ TEST 2 RÉUSSI : Message de succès détecté correctement");
            } else {
                System.out.println("❌ TEST 2 ÉCHOUÉ : Message de succès non détecté");
                System.out.println("   Message attendu : contenant 'réussie' ou 'succès'");
                System.out.println("   Message reçu : '" + messageText + "'");
            }

            // Vérifier la couleur du message (optionnel)
            try {
                String messageColor = messageElement.getCssValue("color");
                System.out.println("🎨 Couleur du message : " + messageColor);
            } catch (Exception e) {
                System.out.println("⚠️ Impossible de récupérer la couleur du message");
            }

        } catch (Exception e) {
            System.out.println("❌ TEST 2 ÉCHOUÉ : Erreur lors du test - " + e.getMessage());
        }
    }
}
