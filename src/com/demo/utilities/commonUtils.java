package com.demo.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class commonUtils{
	public static String basePath = System.getProperty("user.dir");
	public static String configPath = basePath+"\\config\\config.properties";
	public static String driversPath = basePath+"\\drivers";
	public static String objectRepo = basePath+"\\objectRepo.properties";
	public static String reportsPath = basePath+"\\reports";
	public static String sreenshotsPath = basePath+"\\screenshots";
	public static String testdataPath = basePath+"\\testdata";
	public static String scriptPath = basePath+"\\scripts";
	public WebDriver driver;
	
	/************************************************************
	 * Description : Loads global configuration parameters 
	 * from config.properties file
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 ***********************************************************/
	public void loadConfig(String path,String key) throws FileNotFoundException, IOException {
		String scrShotPassed = null;
		Properties properties = new Properties();
		properties.load(readFile(path));
		properties.getProperty(key);
	}
	
	/***********************************************************
	 * Description :  Reads file contents of the path specified
	 * and makes available to the user
	 * @throws FileNotFoundException 
	 **********************************************************/
	public FileInputStream readFile(String path) throws FileNotFoundException {
		FileInputStream fis = new FileInputStream(path);
		return fis;
	}
	
	/************************************************************
	 * Description : Takes browser type (ie,chrome etc) as 
	 * argument and clears cache and cookies from commandline
	 ***********************************************************/
	
	public void clearCache(String browser) throws IOException {
		Process run;
		if(browser.equalsIgnoreCase("ie")||browser.equalsIgnoreCase("InternetExplorer")) {
			run = Runtime.getRuntime().exec("cmd.exe /c Start "+scriptPath+"\\IECacheClear.bat");
		}else if(browser.equalsIgnoreCase("gc")||browser.equalsIgnoreCase("chrome")||
				browser.equalsIgnoreCase("googlechrome")) {
			run = Runtime.getRuntime().exec("cmd.exe /c Start "+scriptPath+"\\chromeClearCache.bat");
		}else if(browser.equalsIgnoreCase("ff")||browser.equalsIgnoreCase("firefox")){
			run = Runtime.getRuntime().exec("cmd.exe /c Start "+scriptPath+"\\firefoxClearCache.bat");
		}else {
			System.out.println("ERROR : BROWSER NOT SUPPORTED BY FRAMEWORK");
		}
		
	}
	
	
	/***********************************************************
	 * Description : Quits active browser and closes session
	 * and webdriver server instance
	 **********************************************************/
	@AfterTest
	public void quitSession() {
		driver.close();
		driver.quit();
	}
	
	/***********************************************************
	 * Description : Return a webelement from Object repository
	 * with appropriate selector based on key-value pairs
	 **********************************************************/
	public WebElement getObject(String Key) throws FileNotFoundException, IOException {
		WebElement element;
		Properties properties = new Properties();
		properties.load(readFile(objectRepo));
		String LocatorType;
		String Locator;
		LocatorType = properties.getProperty(Key).split(":")[0].toLowerCase();
		Locator = properties.getProperty(Key).split(":")[1];
		switch (LocatorType) {
			case "name":
				element =  driver.findElement(By.name(Locator));
				break;
			case "id":
				element =  driver.findElement(By.id(Locator));
				break;
			case "class":
				element =  driver.findElement(By.className(Locator));
				break;
			case "linktext":
				element =  driver.findElement(By.linkText(Locator));
				break;
			case "partiallinktext":
				element =  driver.findElement(By.partialLinkText(Locator));
				break;
			case "xpath":
				element =  driver.findElement(By.xpath(Locator));
				break;
			case "tagname":
				element =  driver.findElement(By.tagName(Locator));
				break;
				default:
					return null;
		}
		return element;
	}
	
	/***************************************************************************
	 * Description : Clicks on specified webelement provided as argument
	 * within the function.
	 **************************************************************************/
	public void click(WebElement element) {
		element.click();
	}
	
	/****************************************************************************
	 * Description : Sends character sequence within a webelement
	 * provided as an argument within the function. 
	 ***************************************************************************/
	public void DFsendkeys(WebElement element,String inputdata) {
		element.sendKeys(inputdata);
	}
	
	/**********************************************************
	 * Description : creates an instance of a Webdriver server
	 * based on user's input parameter (ie,chrome,ff etc)
	 *********************************************************/
	public void setBrowser(String browserName) {
		if(browserName.equalsIgnoreCase("ie")||(browserName.equalsIgnoreCase("internetExplorer"))) {
			System.setProperty("webdriver.ie.driver", driversPath+"\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}else if(browserName.equalsIgnoreCase("gc")||(browserName.equalsIgnoreCase("chrome"))||
				browserName.equalsIgnoreCase("googlechrome")) {
			System.setProperty("webdriver.chrome.driver", driversPath+"\\chromedriver.exe");
			driver = new ChromeDriver();
		}else if (browserName.equalsIgnoreCase("ff")||browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", driversPath+"\\geckoDriver.exe");
			driver = new FirefoxDriver();
		}else {
			System.out.println("ERROR : BROWSER NOT SUPPORTED BY FRAMEWORK");
		}
	}
	
	/*********************************************************
	 * Description : Navigates to provided url in the argument
	 * within function
	 ********************************************************/
	public void navigate(String url) {
		driver.manage().window().maximize();
		driver.get(url);
	}
	
}
