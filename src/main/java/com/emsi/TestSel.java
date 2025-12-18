package com.emsi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestSel {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev");
        driver.findElement(By.xpath("//button[@aria-label='Toggle navigation']")).click();
        driver.findElement(By.xpath("//nav[@class='js-navbar-scroll navbar navbar-expand-lg navbar-light bg-white flex-row td-navbar']")).click();
//driver.findElement(By.xpath("//a[@href='/downloads/']")).click();
        driver.quit();// Vous pouvez ajouter plus de code Selenium ici pour tester diverses fonctionnalités
    }
}