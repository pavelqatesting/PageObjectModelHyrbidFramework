package com.qa.ecomm.tests;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.base.BasePage;
import com.qa.ecomm.pages.AccountPage;
import com.qa.ecomm.pages.LoginPage;
import com.qa.util.AppConstants;

public class AccountPageTest {

	//My account - My Store
	
	LoginPage loginPage;
	AccountPage accountPage;
	BasePage basePage;
	Properties prop;
	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.initializeProperty();
		driver = basePage.initializeDriver(prop);
		loginPage = new LoginPage(driver);
	}
	
	@Test (priority = 1)
	public void verifyHomePageTest() throws InterruptedException {
		String username = prop.getProperty("username");
		String psw = prop.getProperty("password");
		accountPage = loginPage.doLogin(username, psw);
		//Thread.sleep(5000);
		String actualPageTitle = accountPage.getAccountPagetitle();
		String expectedPageTitle = AppConstants.ACCOUNT_PAGE_TITLE;
		System.out.println("Account page title is "+actualPageTitle);
		Assert.assertEquals(actualPageTitle, expectedPageTitle);
			
	}
	
	@Test (priority = 2)
	public void verifyPageHeaderTest() {
		String username = prop.getProperty("username");
		String psw = prop.getProperty("password");
		accountPage = loginPage.doLogin(username, psw);
		String actualText = accountPage.isHomePageHeaderDisplay();
		String expectedText = AppConstants.ACCOUNT_PAGE_HEADER;
		Assert.assertEquals(actualText, expectedText);
		sleep(2000);
	}
	
	@Test (priority = 3)
	public void verifyAccountSectionCountTest() {
		String username = prop.getProperty("username");
		String psw = prop.getProperty("password");
		accountPage = loginPage.doLogin(username, psw);
		int accountSectionSize = accountPage.getHeaderSectionSize();
		Assert.assertTrue(accountSectionSize == AppConstants.ACCOUNT_SECTION_COUNT);
	}
	
	@Test (priority = 4)
	public void verifyAccountSectionListTest() {
		String username = prop.getProperty("username");
		String psw = prop.getProperty("password");
		accountPage = loginPage.doLogin(username, psw);
		List<String> actualListText = accountPage.getAccountSectionTextValue();
		List<String> expectedListText = AppConstants.getAccountSectionList();
		Assert.assertEquals(actualListText, expectedListText);
	}
	
	@Test (priority = 5)
	public void verifySearchAccountTest() {
		String username = prop.getProperty("username");
		String psw = prop.getProperty("password");
		accountPage = loginPage.doLogin(username, psw);
		
		accountPage.doSearch("iMac");
		//sleep(5000);
		Assert.assertTrue(accountPage.isSearchProductDisplay());
	}
	
	
	
	public void sleep(long mil) {
		try {
			Thread.sleep(mil);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}
