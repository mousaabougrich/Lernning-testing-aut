package com.emsi.TP;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tp1 {
    public static void main(String[] args) throws InterruptedException {
    WebDriver driver= new ChromeDriver();
    driver.get("https://rahulshettyacademy.com/locatorspractice/" );
   // driver.navigate().to("https://rahulshettyacademy.com/locatorspractice/");
        driver.findElement(By.xpath("//input[contains(@placeholder, 'Username')]")).sendKeys("rahulshettyacademy");
    driver.findElement(By.xpath("//input[contains(@placeholder, 'Password')]")).sendKeys("rahulshettyacademy");
    driver.findElement(By.xpath("//button[text()='Sign In']")).click();
    Thread.sleep(2000);
 WebElement h2 =driver.findElement(By.xpath("//div/div/div/div/h2"));
  WebElement h1  =driver.findElement(By.xpath("//div/div/div/div/h2/following-sibling::h1"));
  WebElement p  =driver.findElement(By.xpath("//div/div/div/div/h2/following-sibling::p"));
        System.out.println(p.getText()+" "+h1.getText()+" "+h2.getText());
    driver.findElement(By.xpath("//div/div/div/div/h2/following-sibling::button")).click();
}
}
