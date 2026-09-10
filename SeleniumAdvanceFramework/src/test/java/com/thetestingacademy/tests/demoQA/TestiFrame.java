package com.thetestingacademy.tests.demoQA;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
public class TestiFrame {
	WebDriver driver ;
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));

	@BeforeMethod
	public void setUp() throws InterruptedException{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demoqa.com/");
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
		Thread.sleep(5000);
		
		WebElement alertiFrameWindows = driver.findElement(By.xpath("//a[@href='/alertsWindows']"));
		wait.until(ExpectedConditions.elementToBeClickable(alertiFrameWindows));
		Actions ac = new Actions(driver);
		ac.moveToElement(alertiFrameWindows);
		alertiFrameWindows.click();
		Thread.sleep(5000);
		
	}
	@Test
	public void testiFrame() throws InterruptedException, IOException {
		WebElement Frames = driver.findElement(By.xpath("//span[starts-with(text(),'Fra')]"));
		wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		Actions ac = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[starts-with(text(),'Fra')]")));
		js.executeScript("window.scrollTo(500,2000);");

//		ac.moveToElement(Frames);
		wait.until(ExpectedConditions.elementToBeClickable(Frames));
		js.executeScript("arguments[0].click();", Frames);
		Thread.sleep(10000);

//		Frames.click();
		WebElement frame1 = driver.findElement(By.xpath("//iframe[@id='frame1']"));
		js.executeScript("window.scrollTo(0,0)");
		Thread.sleep(10000);

//		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame1));
		driver.switchTo().frame(frame1);
//		
		Thread.sleep(10000);
		WebElement sampleHeading1 = driver.findElement(By.xpath("//h1[@id='sampleHeading']"));
		wait.until(ExpectedConditions.visibilityOf(sampleHeading1));
//		
		System.out.println("sampleHeading1"+sampleHeading1.getText());
		Assert.assertEquals(sampleHeading1.getText(), "This is a sample page");
		takeScreenshot();
		
		driver.switchTo().defaultContent();
//		
//		ac.moveToElement(Frames);
//		wait.until(ExpectedConditions.elementToBeClickable(Frames));
//		Frames.click();
		js.executeScript("window.scrollTo(500,2000);");
		
		Thread.sleep(10000);
		WebElement frame2 = driver.findElement(By.xpath("//iFrame[@id='frame2']"));
//		
//		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame2));
		driver.switchTo().frame(frame2);
		Thread.sleep(10000);

		WebElement sampleHeading2 = driver.findElement(By.xpath("//h1[@id='sampleHeading']"));
		wait.until(ExpectedConditions.elementToBeClickable(sampleHeading2));
		System.out.println("sampleHeading2"+sampleHeading2.getText());
		Thread.sleep(10000);

		Assert.assertEquals(sampleHeading2.getText(), "This is a sample page");
		takeScreenshot();
	}
	
	public void takeScreenshot() throws IOException {
		//1
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		//2
		Random rand = new Random();
//		DateTimeFormatter date = new DateTimeFormatter();
		String name = "ss"+ rand.nextInt() ;
		FileHandler.copy(src,new File("./Screenshots/"+ name +".png"));
	}
	@AfterMethod
	public void tearDown() {
		if(driver!= null) {
			driver.quit();
		}
	}
}
