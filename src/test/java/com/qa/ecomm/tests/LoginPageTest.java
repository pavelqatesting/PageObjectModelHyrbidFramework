package com.qa.ecomm.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.base.BasePage;
import com.qa.ecomm.pages.LoginPage;
import com.qa.util.AppConstants;

public class LoginPageTest {
	
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	
	
	@BeforeMethod
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.initializeProperty();
		driver = basePage.initializeDriver(prop);
		loginPage = new LoginPage(driver);
	}
	
	@Test (priority = 1)
	public void verifyLoginPageTitleTest() throws InterruptedException {
		//Thread.sleep(10000);
		String actualtitle = loginPage.getLoginPageTitle();
		System.out.println("Login page title is "+actualtitle);
		String expectedtitle = AppConstants.LOGIN_PAGE_TITLE;
		Assert.assertEquals(actualtitle, expectedtitle);
	}
	
	@Test(priority = 2)
	public void verifyForgotPasswordLink() throws InterruptedException {
		boolean flag = loginPage.isForgotPwdLinkExist();
		Assert.assertTrue(flag);
	}
	
	
	@Test(priority = 3)
	public void VerifyLoginWithValidCredentialsTest() throws InterruptedException {
		String username = prop.getProperty("username");
		String psw = prop.getProperty("password");
		loginPage.enterUserName(username);
		loginPage.enterPassword(psw);
		loginPage.clickOnLogin();
		//Thread.sleep(5000);
	}
	
	@Test(priority = 4, dataProvider = "getInvalidLoginData")
	public void invalidLogintest(String username, String psw) throws InterruptedException {
		
		loginPage.doLogin(username, psw);
		//Thread.sleep(1000);
		boolean flag = loginPage.verifyLoginFailedMessage();
		Assert.assertTrue(flag);
	}
	
	@DataProvider
	public Object[][] getInvalidLoginData() {
		Object [][] data = {
				{"pavel@gmail.com", "pavel@Test"},
				{"", "pavel@Test"},
				{"pavel@gmail.com", ""},
				{"", ""}
		};
		return data;
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	

}
