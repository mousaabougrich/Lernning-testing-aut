package com.emsi;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Selintro {


    public static void main(String[] args) {
        // 2 WAYS TO INVOK GOOGLE ONE BY USING SELENIUM MANAGER AND OTHER BY USING
        //System.setProperty("webdriver.chrome.driver","pat here")
        WebDriver chromeDriver = new ChromeDriver();
      chromeDriver.get("https://rahulshettyacademy.com/practice-project");
        System.out.println(chromeDriver.getPageSource());
        System.out.println("///////////");
        System.out.println(chromeDriver.getTitle());
        System.out.println(chromeDriver.getCurrentUrl());
        //close every things even driver
    chromeDriver.quit();
    //close test only
// Using EdgeDriver to open the Selenium website

    }
}
