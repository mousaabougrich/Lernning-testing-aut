package com.emsi.Tp2;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PremierTestSeleniumJUnit {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeAll
    public void setupClass() {
        System.out.println("=== Initialisation de la suite de tests ===");
    }

    @BeforeEach
    public void setup() {
        // Configuration Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--start-maximized");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        // Initialisation du driver
        driver = new ChromeDriver(options);

        // Configuration des attentes explicites (10 secondes max)
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        System.out.println("Navigateur Chrome lancé");
    }

    @Test
    @DisplayName("Test 1 : Vérifier le titre de la page Google")
    public void testTitrePageGoogle() {
        // 1. Ouvrir Google
        driver.get("https://www.google.com");
        System.out.println("Page Google ouverte");

        // 2. Attente explicite que le titre contienne "Google"
        wait.until(ExpectedConditions.titleContains("Google"));

        // 3. Récupérer le titre
        String titreActuel = driver.getTitle();
        System.out.println("Titre actuel : " + titreActuel);

        // 4. Assertions JUnit
        assertNotNull(titreActuel, "Le titre ne doit pas être null");
        assertTrue(titreActuel.contains("Google"),
                "Le titre doit contenir 'Google'");
        assertEquals("Google", titreActuel,
                "Le titre doit être exactement 'Google'");

        System.out.println("✓ Test réussi : Le titre est correct");
    }

    @Test
    @DisplayName("Test 2 : Vérifier la présence de la barre de recherche")
    public void testPresenceBarreRecherche() {
        // Ouvrir Google
        driver.get("https://www.google.com");

        // Attente explicite de la présence de la barre de recherche
        WebElement barreRecherche = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.name("q"))
        );

        // Assertions
        assertNotNull(barreRecherche,
                "La barre de recherche doit être présente");
        assertTrue(barreRecherche.isDisplayed(),
                "La barre de recherche doit être visible");
        assertTrue(barreRecherche.isEnabled(),
                "La barre de recherche doit être activée");

        System.out.println("✓ Test réussi : La barre de recherche est présente et fonctionnelle");
    }

    @Test
    @DisplayName("Test 3 : Recherche Google avec validation du titre")
    public void testRechercheGoogle() throws InterruptedException {
        // 1. Ouvrir Google
        driver.get("https://www.google.com");

        // 2. Attendre et localiser la barre de recherche
        WebElement barreRecherche = wait.until(
                ExpectedConditions.elementToBeClickable(By.name("q"))
        );

        // 3. Saisir le mot-clé
        String motCle = "Selenium WebDriver";
        barreRecherche.sendKeys(motCle);
        System.out.println("Mot-clé saisi : " + motCle);

        // 4. Soumettre la recherche
        barreRecherche.submit();

        // 5. Attendre que le titre change et contienne le mot-clé
        wait.until(ExpectedConditions.titleContains(motCle));

        // 6. Récupérer le titre de la page de résultats
        String titreResultats = driver.getTitle();
        System.out.println("Titre des résultats : " + titreResultats);

        // 7. Assertions multiples
        assertNotNull(titreResultats, "Le titre ne doit pas être null");
        assertFalse(titreResultats.startsWith("http"),
                "Le titre ne doit pas être une URL");
        assertTrue(titreResultats.toLowerCase().contains(motCle.toLowerCase()),
                "Le titre doit contenir le mot-clé recherché : " + motCle);

        // 8. Vérifier la présence de résultats
        WebElement divResultats = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("search"))
        );
        assertNotNull(divResultats, "Les résultats de recherche doivent être présents");

        System.out.println("✓ Test réussi : La recherche fonctionne correctement");
    }

    @Test
    @DisplayName("Test 4 : Vérifier l'URL de Google")
    public void testURLGoogle() {
        // Ouvrir Google
        driver.get("https://www.google.com");

        // Attendre le chargement complet
        wait.until(ExpectedConditions.urlContains("google.com"));

        // Récupérer l'URL actuelle
        String urlActuelle = driver.getCurrentUrl();
        System.out.println("URL actuelle : " + urlActuelle);

        // Assertions
        assertNotNull(urlActuelle, "L'URL ne doit pas être null");
        assertTrue(urlActuelle.contains("google.com"),
                "L'URL doit contenir 'google.com'");
        assertTrue(urlActuelle.startsWith("https://"),
                "L'URL doit utiliser HTTPS");

        System.out.println("✓ Test réussi : L'URL est correcte et sécurisée");
    }

    @AfterEach
    public void tearDown() {
        // Fermer le navigateur après chaque test
        if (driver != null) {
            driver.quit();
            System.out.println("Navigateur fermé\n");
        }
    }

    @AfterAll
    public void tearDownClass() {
        System.out.println("=== Fin de la suite de tests ===");
    }
}