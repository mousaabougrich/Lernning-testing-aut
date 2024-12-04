package com.emsi;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class loacatore2 {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver= new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/locatorspractice/");
   driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("rahulshettyacademy");
 driver.findElement(By.xpath("//input[@type='password']")).sendKeys("rahulshettyacademy");

        driver.findElement(By.id("chkboxOne")).click();
//driver.findElement(By.xpath("//div[@class='checkbox-container'][2] ")).click();
//driver.findElement(By.cssSelector("button[class='submit']")).click();
        driver.findElement(By.cssSelector("button[class='submit signInBtn']")).click();

       Thread.sleep(1000);

    System.out.println(driver.findElement(By.cssSelector("div div div p" )).getText());

driver.close();


    }


}
