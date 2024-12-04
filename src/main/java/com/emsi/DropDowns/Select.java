package com.emsi.DropDowns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;


import java.util.List;

public class Select {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
  driver.get("https://www.spicejet.com" );
//        driver.findElement(By.cssSelector("div[class='css-1dbjc4n'] div div[class='css-1dbjc4n r-14lw9ot r-11u4nky r-z2wwpe r-1phboty r-rs99b7 r-1loqt21 r-13awgt0 r-ymttw5 r-5njf8e r-1otgn73'] div[class='css-1dbjc4n r-1awozwy r-18u37iz r-1wtj0ep']")).click();
//        Thread.sleep(2000);
//        for (int i=0 ;i<2;i++){
//        driver.findElement(By.xpath("//div[@data-testid='Adult-testID-plus-one-cta']")).click();
//        driver.findElement(By.id("ctl00_mainContent_chk_friendsandfamily")).click();
//        Assert.assertFalse( driver.findElement(By.id("ctl00_mainContent_chk_friendsandfamily")).isSelected());
//    List<WebElement> elements=driver.findElements(By.xpath("//div[@class='row1 padding-bottom-3 home-dis-checkboxes']//input[@type='checkbox']"));
//      for (WebElement i:elements){
//     System.out.println(i.isSelected());
//     }
        driver.findElement(By.xpath("(//div[@class='css-1dbjc4n r-1awozwy r-18u37iz r-1wtj0ep'])[3]")).click();
}

    }

