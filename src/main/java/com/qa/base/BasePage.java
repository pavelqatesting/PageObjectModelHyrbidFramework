package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	public WebDriver driver;
	public Properties prop;

	/**
	 * This methods is used to initialize the WebDriver on the basis of browserName 
	 * @param browserName
	 * @return This methods will return the driver instance 
	 */
	public WebDriver initializeDriver(Properties prop) {
		
		String browserName = prop.getProperty("browser");
		boolean isHeadLess = Boolean.parseBoolean(prop.getProperty("headless"));
		System.out.println(browserName+" browser launched");
		
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			if(isHeadLess) {
				ChromeOptions chromeOption = new ChromeOptions();
				chromeOption.addArguments("--headless");
				driver = new ChromeDriver(chromeOption);
			} 
			else {
				driver = new ChromeDriver();
			}
			
		} 
		else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			
			if(isHeadLess) {
				FirefoxOptions firefoxOption = new FirefoxOptions();
				firefoxOption.addArguments("--headless");
				driver = new FirefoxDriver(firefoxOption);
			}
			else {
				driver = new FirefoxDriver();
			}

		} 
		else if (browserName.equalsIgnoreCase("safari")) {
			WebDriverManager.getInstance(SafariDriver.class).setup();
			driver = new FirefoxDriver();
		}
//		else if (browserName.equalsIgnoreCase("IE")) {
//			WebDriverManager.iedriver().setup();
//			driver = new Iedriver
//		}
		else {
			System.out.println(browserName + " is not fount, please pass the right browser");
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		return driver;
		
	}
	
	/**
	 * This method is used to the config.properties file 
	 * @return this methods will return the Properties prop available in config.properties file 
	 */
	public Properties initializeProperty() {
		
		String fileLocation = ".\\src\\main\\resources\\com\\qa\\configure\\config.properties";
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream(fileLocation);
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Config file not found....");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Wasen't able to load file.....");
		}
		
		return prop;
	}
}
