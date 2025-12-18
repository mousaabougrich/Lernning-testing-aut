package com.emsi.TP;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class tp1Class {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver =new ChromeDriver();

        driver.get("http://127.0.0.1:5501/connexion.html");
        driver.findElement(By.id("username")).sendKeys("test.test@test.com");
        driver.findElement(By.id("password")).sendKeys("testallah");
        driver.findElement(By.id("btn")).click();
    }
}