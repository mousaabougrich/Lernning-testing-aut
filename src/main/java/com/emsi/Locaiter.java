package com.emsi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Locaiter {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/angularpractice/");
        driver.findElement(By.name("name")).sendKeys("mousaab");
        driver.findElement(By.name("email")).sendKeys("mousaab@gmail.com")  ;
        driver.findElement(By.cssSelector("input[type=password]")).sendKeys("mousaab");
        System.out.println(driver.findElement(By.cssSelector("h1[align='center']")).getText());

        driver.findElement(By.cssSelector("input[value='Submit']")).click();
        System.out.println( driver.findElement(By.cssSelector("div.alert")).getText() );
driver.findElement(By.linkText("Shop")).click();
// xpath typeofel=[@class or any thing=" "]
driver.findElement(By.xpath("(//button[@class='btn btn-info'][normalize-space()='Add'])[2]")).click();
/*
for css selctores
---element.classname
---.className
or element element
form p
p.eroor
----or input=[type=""] :nth-child(n) n for number wher elememt existe child
---for xpath*
 //input=[@type=""] [n] n for number wher elememt existe child
//form/input[3]





 */
    }
}
