package com.thetestingacademy.tests.demoQA;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;
public class TestCheckBox{
	WebDriver driver = new ChromeDriver();

	@Test
	public void testCheckBox()  {
	try{
		driver.manage().window().maximize();
		driver.get("https://demoqa.com/");

		 JavascriptExecutor js = (JavascriptExecutor)driver;
		 Thread.sleep(5000);
		 js.executeScript("window.scrollBy(0,500)");
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		 WebElement elements = driver.findElement(By.xpath("//a[@href='/elements']"));
		 wait.until(ExpectedConditions.elementToBeClickable(elements));

		 elements.click();
		 Thread.sleep(2000);
		 
		WebElement checkBox = driver.findElement(By.xpath("//span[text()='Check Box']"));
		new Actions(driver).moveToElement(checkBox).perform();
		Thread.sleep(5000);
		checkBox.click();
		
		Thread.sleep(5000);

		WebElement chkSelectHome = driver.findElement(By.xpath("//div[@class='rc-tree']/div[@class='rc-tree-list']/div/div/div/div/span[@aria-label='Select Home']"));
//		WebElement chkSelectHome = driver.findElement(By.xpath("span[@aria-label='Select Home']"));
		Thread.sleep(2000);
		System.out.println("chkSelectHome "+ chkSelectHome.getAttribute("Aria-label"));//not happening
		if(chkSelectHome.isDisplayed()) {
			System.out.println("Home is display");
		}else {
			System.out.println("Home is not accessible");
		}
//		if(!chkSelectHome.isSelected()) {
			chkSelectHome.click();
//		}
			Thread.sleep(5000);
		//You have selected : home
		String home = driver.findElement(By.xpath("//span[text()='home']")).getText();
		System.out.println(home);
		Assert.assertEquals("home",home);
		Thread.sleep(5000);

}catch(Exception e){
	
}finally {
}
	driver.quit();
}
	}

