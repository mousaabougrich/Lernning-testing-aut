package com.emsi.Tp2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class RechercheGoogleTest {

    public static void main(String[] args) {
        WebDriver driver = null;

        try {
            // Configuration avancée Chrome pour éviter la détection
            ChromeOptions options = new ChromeOptions();

            // Désactive les indicateurs d'automation
            options.addArguments("--disable-blink-features=AutomationControlled");
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            options.setExperimentalOption("useAutomationExtension", false);

            // User-Agent réaliste (à jour)
            options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36 Edg/131.0.0.0");

            // Arguments supplémentaires pour éviter le CAPTCHA
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-gpu");
            options.addArguments("--disable-web-resources");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-plugins");
            options.addArguments("--disable-sync");
            options.addArguments("--disable-notifications");
            options.addArguments("--lang=en-US");

            // Fenêtre maximisée (plus naturel)
            options.addArguments("--start-maximized");

            // Désactive le message "Chrome est contrôlé par un logiciel automatisé"
            options.addArguments("--disable-infobars");

            // Préférences supplémentaires
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            prefs.put("profile.default_content_settings.popups", 0);
            prefs.put("profile.managed_default_content_settings.images", 2);
            options.setExperimentalOption("prefs", prefs);

            // Lance Chrome
            driver = new ChromeDriver(options);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Injecte des scripts pour masquer l'automation
            js.executeScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");
            js.executeScript("window.navigator.chromeHeadless = undefined");
            js.executeScript("window.navigator.plugins = [1, 2, 3, 4, 5]");
            js.executeScript("window.navigator.languages = ['en-US', 'en']");

            // Ajoute des comportements humains
            js.executeScript("window.chrome = {runtime: {}}");

            System.out.println("Navigateur lancé avec configuration anti-détection avancée");

            // 1. Ouvre Google
            driver.get("https://www.google.com");
            System.out.println("Page Google ouverte");
            Thread.sleep(2000 + (int)(Math.random() * 2000)); // Délai aléatoire 2-4 secondes

            // Gestion des cookies avec fallback
            try {
                Thread.sleep(1000); // Attendre le chargement du bouton
                WebElement acceptButton = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("//button[contains(text(), 'Tout accepter') or contains(text(), 'Accept all') or contains(text(), 'J'accepte') or contains(text(), 'Accept')]")
                        )
                );
                Thread.sleep(500 + (int)(Math.random() * 500)); // Délai aléatoire avant clic
                acceptButton.click();
                System.out.println("Cookies acceptés");
                Thread.sleep(1000 + (int)(Math.random() * 1000));
            } catch (Exception e) {
                System.out.println("Pas de popup cookies ou déjà acceptés");
            }

            // 2. Recherche la barre de recherche
            WebElement barreRecherche = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.name("q"))
            );

            // Ajoute un délai aléatoire avant d'interagir (comportement humain)
            Thread.sleep(800 + (int)(Math.random() * 700)); // Délai 800-1500ms

            // Clique d'abord sur la barre (comportement humain)
            barreRecherche.click();
            Thread.sleep(300 + (int)(Math.random() * 300)); // Délai 300-600ms

            // 3. Saisie très progressive (simulation humaine)
            String motCle = "Selenium WebDriver";
            System.out.println("Début de la saisie du mot-clé: " + motCle);

            for (char c : motCle.toCharArray()) {
                barreRecherche.sendKeys(String.valueOf(c));
                Thread.sleep(80 + (int)(Math.random() * 80)); // Délai variable 80-160ms entre chaque caractère
            }
            System.out.println("Mot clé saisi avec succès");
            Thread.sleep(1000 + (int)(Math.random() * 1000)); // Délai 1-2 secondes après saisie

            // 4. Lance la recherche
            barreRecherche.sendKeys(Keys.RETURN);
            System.out.println("Recherche lancée...");

            // Attendre le chargement des résultats
            Thread.sleep(3000 + (int)(Math.random() * 2000)); // Délai 3-5 secondes

            // Vérification renforcée du CAPTCHA
            String pageSource = driver.getPageSource();
            String currentUrl = driver.getCurrentUrl();

            boolean captchaDetected = false;
            if (pageSource.contains("captcha") ||
                pageSource.contains("unusual traffic") ||
                pageSource.contains("Please try again later") ||
                pageSource.contains("detected unusual traffic") ||
                currentUrl.contains("sorry/index")) {
                captchaDetected = true;
            }

            if (captchaDetected) {
                System.out.println("⚠️ CAPTCHA détecté ! Tentative de contournement en cours...");

                // Essai d'attendre quelques secondes
                System.out.println("Attente de 15 secondes avant nouvelle tentative...");
                Thread.sleep(15000);

                // Réessai avec URL directe
                String searchQuery = "Selenium WebDriver";
                try {
                    String encodedQuery = URLEncoder.encode(searchQuery, "UTF-8");
                    String googleSearchUrl = "https://www.google.com/search?q=" + encodedQuery;
                    System.out.println("Tentative avec recherche par URL: " + googleSearchUrl);
                    driver.get(googleSearchUrl);
                    Thread.sleep(3000);
                    pageSource = driver.getPageSource();

                    if (pageSource.contains("captcha") || pageSource.contains("unusual traffic")) {
                        System.out.println("❌ CAPTCHA toujours présent. Google bloque temporairement les requêtes.");
                        System.out.println("Solution recommandée : Utiliser un proxy ou VPN, ou attendre quelques heures.");
                    } else {
                        System.out.println("✓ CAPTCHA contourné avec succès !");
                    }
                } catch (Exception e) {
                    System.err.println("Erreur lors de la tentative de contournement: " + e.getMessage());
                }
            } else {
            }

            // 5. Récupère et vérifie le titre
            String titrePage = driver.getTitle();
            System.out.println("Titre de la page : " + titrePage);

            // Vérifie si c'est vraiment un titre et non une URL
            if (titrePage.startsWith("http")) {
                System.out.println("⚠️ Erreur : Le titre récupéré est une URL (possible CAPTCHA)");
                System.out.println("URL actuelle : " + driver.getCurrentUrl());
            } else if (titrePage.toLowerCase().contains("selenium") || titrePage.toLowerCase().contains("webdriver")) {
                System.out.println("✓ TEST RÉUSSI : Le titre contient des résultats de recherche");
            } else {
                System.out.println("⚠️ Titre : " + titrePage);
            }

            // Pause pour voir les résultats
            Thread.sleep(2000);

        } catch (Exception e) {
            System.err.println("Erreur lors de l'exécution : " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
                System.out.println("Navigateur fermé avec succès.");
            }
        }
    }
}