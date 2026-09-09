package com.thetestingacademy.tests.demoQA;

import static org.testng.Assert.assertEquals;

import java.awt.Window;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.Assert;

public class TestTextBox {
 WebDriver driver = new ChromeDriver();
 
 @Test
 public void testTextbox() throws InterruptedException {
	 try {
	 driver.get("https://demoqa.com/");
	 JavascriptExecutor js = (JavascriptExecutor)driver;
	 Thread.sleep(5000);
	 js.executeScript("window.scrollBy(0,500)");
	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	 WebElement elements = driver.findElement(By.xpath("//a[@href='/elements']"));
	 wait.until(ExpectedConditions.elementToBeClickable(elements));

	 elements.click();

	 Thread.sleep(5000);
	 
	 WebElement innerElement = driver.findElement(By.xpath("//div[text()='Elements']"));
	 new Actions(driver).scrollToElement(innerElement).perform();
	 WebElement textbox = driver.findElement(By.xpath("//a[@href='/text-box']"));
	 wait.until(ExpectedConditions.elementToBeClickable(textbox));
	 Thread.sleep(5000);
	 textbox.click();
	 
	 Thread.sleep(2000);

	 WebElement email = driver.findElement(By.xpath("//input[@id='userEmail']"));
	 email.sendKeys("aditya@gm.com");
	 String emailval= email.getAttribute("value");
	 System.out.println("emailval = " + emailval);
	 Thread.sleep(2000);
	 
	 WebElement submit = driver.findElement(By.xpath("//button[@id='submit']"));
	 Thread.sleep(2000);
     js.executeScript("window.scrollBy(0,500)");
	 Thread.sleep(2000);
	 //js.executeScript("arguments[0].click();", submit);
	 submit.click();
	 Thread.sleep(2000);
	 String cssValue = email.getCssValue("border-color");
	 System.out.println(cssValue);
	 
	 if(cssValue.equalsIgnoreCase("rgb(255, 0, 0)")) {
		 assertEquals(cssValue, "rgb(255, 0, 0)","Wrong email!");

	 }else {
		 assertEquals(cssValue,"rgb(222, 226, 230)","Correct Email!");
		 Thread.sleep(3000);
		 String resultemail = driver.findElement(By.xpath("//div[@id='output']/div/p[@id='email']")).getText();
		 System.out.println("resultemail="+resultemail);
		 assertEquals(resultemail,"Email:"+emailval);
	 }
 }
	 finally {
		 driver.quit();
	 }
	 
	 
 }
}
