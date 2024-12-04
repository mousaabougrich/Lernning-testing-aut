package com.emsi.TP;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class drop {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//label[@for='ctl00_mainContent_rbtnl_Trip_0']")).click();
        driver.findElement(By.xpath("//input[@id='ctl00_mainContent_rbtnl_Trip_0']")).click();
        driver.findElement(By.xpath("//a[@value='ATQ']")).click();
        driver.findElement(By.xpath("(//a[@value='BLR'][normalize-space()='Bengaluru (BLR)'])[2]")).click();
        Select dropdown = new Select(driver.findElement(By.id("dropdown")));
        dropdown.getFirstSelectedOption();
    }
}
