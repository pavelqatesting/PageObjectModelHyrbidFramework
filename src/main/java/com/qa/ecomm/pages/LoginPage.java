package com.qa.ecomm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.base.BasePage;
import com.qa.util.AppConstants;
import com.qa.util.GenericFunction;

/**
 * This class is for login page, will be difining all the locator then will create 
 * necessary methods for the login page 
 * @author pavel
 *
 */

public class LoginPage extends BasePage{

	private WebDriver driver;
	GenericFunction genericFunction;
	
	//1. By locators -Page Object 
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By loininFauled = By.xpath("//div[@class='alert alert-danger alert-dismissible']");
	
	//2. Constructor of page class 
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		genericFunction = new GenericFunction(driver);
	}
	
	//3. Page actions/methods
	
	/**
	 * This methods will get the page title 
	 * @return login page title
	 */
	public String getLoginPageTitle() {
		return genericFunction.getTitleExplicitly(AppConstants.LOGIN_PAGE_TITLE);
	}

	/**
	 * This methods will check if the Forgotten Password link display or not 
	 * @return true if link display otherwise false 
	 */
	public boolean isForgotPwdLinkExist() {
		genericFunction.waitForElementPresent(forgotPwdLink);
		return genericFunction.doIsDisplayed(forgotPwdLink);
	}

	/**
	 * This methods will be used to enter the username 
	 * @param username
	 */
	public void enterUserName(String username) {
		genericFunction.doSendKeys(emailId, username);
	}

	/**
	 * This methods will be used to enter the password
	 * @param pwd
	 */
	public void enterPassword(String pwd) {
		driver.findElement(password).sendKeys(pwd);
	}

	/**
	 * This methods will be user to click on login button 
	 */
	public void clickOnLogin() {
		driver.findElement(loginButton).click();
	}

	/**
	 * This methods will be used to lonin to the application and will be used for chaning process 
	 * @param un
	 * @param pwd
	 * @return the Home page  
	 */
	public AccountPage doLogin(String un, String pwd) {
		System.out.println("login with: " + un + " and " + pwd);
		driver.findElement(emailId).clear();
		driver.findElement(emailId).sendKeys(un);
		
		driver.findElement(password).clear();
		driver.findElement(password).sendKeys(pwd);
		
		driver.findElement(loginButton).click();
		return new AccountPage(driver);
	}
	
	/**
	 * This methods will be used to verify the login failed message with invalid credentials 
	 * @return true if the error meessage display otherwise false 
	 */
	public boolean verifyLoginFailedMessage() {
		genericFunction.waitForElementPresent(loininFauled);
		return driver.findElement(loininFauled).isDisplayed();
	}
	
	
}
