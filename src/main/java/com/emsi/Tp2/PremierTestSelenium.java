package com.emsi.Tp2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PremierTestSelenium {

    public static void main(String[] args) {
        // Déclaration du WebDriver
        WebDriver driver = null;

        try {
            // 1. Lance le navigateur Chrome
            driver = new ChromeDriver();

            // 2. Ouvre la page d'accueil de Google
            driver.get("https://www.google.com");

            // 3. Affiche le titre de la page dans la console
            String titreePage = driver.getTitle();
            System.out.println("Titre de la page : " + titreePage);

            // Pause de 3 secondes pour voir le résultat
            Thread.sleep(3000);

        } catch (Exception e) {
            System.err.println("Erreur lors de l'exécution : " + e.getMessage());
            e.printStackTrace();
        } finally {
            // 4. Ferme le navigateur
            if (driver != null) {
                driver.quit();
                System.out.println("Navigateur fermé avec succès.");
            }
        }
    }
}