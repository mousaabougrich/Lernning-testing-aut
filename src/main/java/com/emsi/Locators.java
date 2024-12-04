package com.emsi;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Locators {

    public static void main(String[] args) throws InterruptedException {

        // Initialize the Chrome WebDriver
        WebDriver driver = new ChromeDriver();

        // Set an implicit wait timeout of 5 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Navigate to the practice page
        driver.get("https://rahulshettyacademy.com/locatorspractice/");

        // Select the username input field by its ID and input "rahul"
        driver.findElement(By.id("inputUsername")).sendKeys("rahul");

        // Select the password input field by its name attribute and input "hello123"
        driver.findElement(By.name("inputPassword")).sendKeys("hello123");

        // Select the sign-in button by its class name and click it
        driver.findElement(By.className("signInBtn")).click();

        // Select the error message paragraph by its CSS selector and print the error text
        System.out.println(driver.findElement(By.cssSelector("p.error")).getText());

        // Select the "Forgot your password?" link by its link text and click it
        driver.findElement(By.linkText("Forgot your password?")).click();

        // Pause execution for 1 second to allow elements to load
        Thread.sleep(1000);

        // Select the Name input field by its placeholder attribute using XPath and input "John"
        driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys("John");

        // Select the Email input field by its placeholder attribute using CSS selector and input "john@rsa.com"
        driver.findElement(By.cssSelector("input[placeholder='Email']")).sendKeys("john@rsa.com");

        // Select the second text input field by type and index (2nd field) using XPath and clear it
        driver.findElement(By.xpath("//input[@type='text'][2]")).clear();

        // Select the third child input field of type text using CSS nth-child pseudo-class and input "john@gmail.com"
        driver.findElement(By.cssSelector("input[type='text']:nth-child(3)")).sendKeys("john@gmail.com");

        // Select the third input field within the form using its position and input a phone number
        driver.findElement(By.xpath("//form/input[3]")).sendKeys("9864353253");

        // Select the reset password button by its class name and click it
        driver.findElement(By.cssSelector(".reset-pwd-btn")).click();

        // Select the paragraph within the form containing the reset instructions and print its text
        System.out.println(driver.findElement(By.cssSelector("form p")).getText());

        // Select the "Go back to login" button within a specific container using XPath and click it
        driver.findElement(By.xpath("//div[@class='forgot-pwd-btn-conainer']/button[1]")).click();

        // Pause execution for 1 second
        Thread.sleep(1000);

        // Select the username input field by its ID again and input "rahul"
        driver.findElement(By.cssSelector("#inputUsername")).sendKeys("rahul");

        // Select the password input field containing the substring "pass" using CSS selector and input the password
        driver.findElement(By.cssSelector("input[type*='pass']")).sendKeys("rahulshettyacademy");

        // Select the first checkbox by its ID and click it
        driver.findElement(By.id("chkboxOne")).click();

        // Select the submit button using an XPath expression that matches a partial class name and click it
        driver.findElement(By.xpath("//button[contains(@class,'submit')]")).click();
    }
}
