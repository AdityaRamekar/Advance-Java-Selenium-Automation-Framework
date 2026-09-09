package com.thetestingacademy.tests.demoQA;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class TestRadioButton {
	WebDriver driver = new ChromeDriver();

	@BeforeMethod
	public void setup() {
	driver.manage().window().maximize();
	driver.get("https://demoqa.com/");
	
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	js.executeScript("window.scrollBy(0,500)");
	WebElement elements = driver.findElement(By.xpath("//a[@href='/elements']"));
	elements.click();
	}
	
	@Test
	public void testRadioButton() throws InterruptedException{
		try {
		
		
//		Thread.sleep(2000);
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
//		wait.until(ExpectedConditions.elementToBeClickable(elements));

		Thread.sleep(2000);
//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(4));
		WebElement radioBtn = driver.findElement(By.xpath("//li[@id='item-2']/a"));
		new Actions(driver).moveToElement(radioBtn).perform();
		radioBtn.click();
		
		Thread.sleep(2000);
		WebElement yesBtn = driver.findElement(By.xpath("//input[@id='yesRadio']"));
		
		if(!yesBtn.isSelected())
			yesBtn.click();
		Thread.sleep(2000);
		String yesTxt = driver.findElement(By.xpath("//label[text()='Yes']")).getText();
		System.out.println(yesTxt);
		Thread.sleep(4000);
		Assert.assertEquals("You have selected Yes", "You have selected "+ yesTxt,"Yes radio button is selected!!");
		
		}catch(Exception e) {
			
	}
	}
	public void tearDown(){
		driver.quit();
		}
	}
