package com.emsi.TP;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class drop {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//label[@for='ctl00_mainContent_rbtnl_Trip_0']")).click();
        driver.findElement(By.cssSelector("input[name='ctl00_mainContent_ddl_originStation1_CTXT']")).click();
        driver.findElement(By.xpath("//a[@value='ATQ']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//a[@value='BLR'][normalize-space()='Bengaluru (BLR)'])[2]")).click();
        driver.findElement(By.id("divpaxinfo")).click();

        Assert.assertEquals(driver.findElement(By.id("divAdult")).getText(), "5 Adult");

        System.out.println(driver.findElement(By.id("divpaxinfo")).getText());
    }
}
