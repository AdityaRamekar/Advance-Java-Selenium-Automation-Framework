package com.thetestingacademy.tests.demoQA;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.*;
import java.util.Random;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.TakesScreenshot;

//for now ignore Thread.sleep use , i know explicit waits should be used

public class TestWebTable {
	WebDriver driver ;
	
	@BeforeMethod
	public void setup() throws InterruptedException, IOException {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demoqa.com/");
		
		Thread.sleep(5000);
		WebElement element = driver.findElement(By.xpath("//a[@href='/elements']"));
//		action.moveToElement(element);
		takeScreenshot();
		element.click();
		Thread.sleep(2000);
	}
	@Test
	public void testWebTable() throws InterruptedException, IOException {
		
		WebElement webTables = driver.findElement(By.xpath("//a[@href='/webtables']"));
		Actions ac = new Actions(driver);
		ac.moveToElement(webTables);
		ac.click(webTables);
		takeScreenshot();
		Thread.sleep(2000);
		webTables.click();
		Thread.sleep(2000);
		
		List<WebElement> rows = driver.findElements(By.xpath("//tbody/tr/td"));
		//type 1
		if(!rows.isEmpty()) {
			for(WebElement row:rows) {
				System.out.println(row.getText());
				
			}
		}
		//type 2
				for(int i = 0;i<rows.size();i++) {
					System.out.println(rows.get(i).getText());
					
				}
			
			Thread.sleep(2000);
	    //search and assert
		WebElement search = driver.findElement(By.xpath("//input[@id='searchBox']"));
		search.sendKeys("Cierra");
		takeScreenshot();

		Thread.sleep(3000);
		String celldata = driver.findElement(By.xpath("//tbody/tr/td")).getText();
		
		if(celldata.equalsIgnoreCase("Cierra")) {
			System.out.println("Passed");
		}
			Assert.assertEquals(celldata, "Cierra","Passed");
			takeScreenshot();

		
		
	}
	//take screenshot
	public void takeScreenshot() throws IOException{
		//1
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		//2
		Random rand = new Random();
		String name = "ss" + rand;
		FileHandler.copy(src,new File("./Screenshots/"+name+".png"));
	}
	
	@AfterMethod
	public void tearDown() {
		if(driver != null)
			driver.quit();
	}

}
