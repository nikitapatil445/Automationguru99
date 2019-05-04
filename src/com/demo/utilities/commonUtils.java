package com.demo.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class commonUtils{
	static String path = System.getProperty("user.dir");
	static String ScreenshotDir = path+"\\screenshots";
	static String ORPath = path+"\\objects\\objectRepo.properties";
	static String WebDriverPath = path+"\\driver\\chromedriver.exe";
	WebDriver driver;
	
	@Test
	public void test1() throws IOException {
		driver.findElement(By.id(getObject("loginPage_Password_Input"))).click();
	}
	
	public static void takeScreenPrint(String result) {
		if(result.equalsIgnoreCase("Fail")) {
			System.out.println("saved in location "+path+"\\screenshots\\"+result+".png");
		}
	}
	
	public static void click(WebElement element) {
		element.click();
	}
	
	public static void sendKeys(WebElement element,String data) {
		element.sendKeys(data);
	}
	
	public static String getObject(String Key) throws IOException {
		FileInputStream fis = new FileInputStream(ORPath);
		Properties properties = new Properties();
		properties.load(fis);
		return Key;
	}
}
