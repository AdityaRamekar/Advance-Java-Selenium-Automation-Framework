package com.thetestingacademy.tests.demoQA;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestButton {

	public class TestWebTable {
		WebDriver driver ;
		
		@BeforeMethod
		public void setup() throws InterruptedException, IOException {
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://demoqa.com/");
			
			Thread.sleep(5000);
			WebElement element = driver.findElement(By.xpath("//a[@href='/elements']"));
//			action.moveToElement(element);
			takeScreenshot();
			element.click();
			Thread.sleep(2000);
		}
		@Test
		public void testButton() throws InterruptedException, IOException {
			WebElement button = driver.findElement(By.xpath("//a[@href='/buttons']"));
			Actions ac = new Actions(driver);
			ac.moveToElement(button);
			Thread.sleep(2000);
			button.click();
			
			Thread.sleep(2000);
			WebElement rClick = driver.findElement(By.xpath("//button[@id='rightClickBtn']"));
			ac.moveToElement(rClick);
			Thread.sleep(2000);
			String s = rClick.getText();
			System.out.println(s);
			ac.contextClick(rClick).perform();
			Thread.sleep(5000);
			String rClickedVerify = driver.findElement(By.xpath("//p[@id='rightClickMessage']")).getText();
			Assert.assertEquals(rClickedVerify, "You have done a right click");
			takeScreenshot();
		}
		//take screenshot
		public void takeScreenshot() throws IOException{
			//1
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			//2
			Random rand = new Random();
			String name = "ss" + rand.nextInt(10000);
			FileHandler.copy(src,new File("./Screenshots/"+name+".png"));
		}
		
		@AfterMethod
		public void tearDown() {
			if(driver != null)
				driver.quit();
		}

}
}
