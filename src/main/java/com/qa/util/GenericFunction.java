package com.qa.util;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class will have all the wrapper generic methods 
 * @author pavel
 *
 */
public class GenericFunction {

	static WebDriver driver;
	static WebDriverWait wait;
	static Actions action;
	
	public GenericFunction(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, AppConstants.DEFAULT_EXPLICIT_TIME_OUT);
		action = new Actions(this.driver);
	}
	
	/**
	 * This method is used to create the webelement on the basis of By locators
	 * @param locator
	 * @return
	 */
	public static WebElement getElement(By locator) {
		WebElement element = null;
		try {
			element = driver.findElement(locator);
			//jsUtil.flash(element);
		} catch (Exception e) {
			System.out.println("WebElement could not be created " + locator);
		}
		return element;
	}
	
	
	//
	/**
	 * This method will be used to find all elements within the current page using the By locators
	 * @param locator
	 * @return
	 */
	public static List<WebElement> getElements(By locator) {
		List<WebElement> element = null;
		try {
			element = driver.findElements(locator);
			//jsUtil.flash(element);
		} catch (Exception e) {
			System.out.println("WebElement could not be created " + locator);
		}
		return element;
	}
	/**
	 * This is used to pass the values
	 * @param locator
	 * @param value
	 */
	public static void doSendKeys(By locator, String value) {
		getElement(locator).clear();
		getElement(locator).sendKeys(value);
	}
	
	/**
	 * This is used to pass the values by help of action class
	 * @param locator
	 * @param value
	 */
	public static void doActionsSendKeys(By locator, String value){
		WebElement element = getElement(locator);
		action.sendKeys(element,value).build().perform();
	}

	/**
	 * This is used for clicking on an element
	 * @param locator
	 */
	public static void doClick(By locator) {
		getElement(locator).click();
	}
	
	/**
	 * This is used for clicking on an element using action class
	 * @param locator
	 */
	public static void doActionsClick(By locator){
		WebElement element = getElement(locator);
		action.click(element).build().perform();
	}
	
	/**
	 * this is used for getting the text
	 * @param locator
	 * @return
	 */
	public static String doGetText(By locator) {
		return getElement(locator).getText();
	}
	
	/**
	 * This is used for checking element is displayed
	 * @param locator
	 * @return
	 */
	public static boolean doIsDisplayed(By locator){
		return getElement(locator).isDisplayed();
	}
	
	  /**
	   * this is used for checking the title contains substring
	   *
	   * @param expected title
	   * @return true when the title matches, false otherwise
	   */
	public static String getTitleExplicitly(String title){
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();
	}
	
	 /**
	   * this is used for checking that an element is present on the DOM of a page. This does not
	   * necessarily mean that the element is visible.
	   *
	   * @param locator used to find the element
	   * @return the WebElement once it is located
	   */
	public static WebElement waitForElementPresent(By locator){
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		
		return driver.findElement(locator);
	}
	
	/**
	   * An expectation for checking that an element, known to be present on the DOM of a page, is
	   * visible. Visibility means that the element is not only displayed but also has a height and
	   * width that is greater than 0.
	   *
	   * @param element the WebElement
	   * 
	   */
	public void waitForElementVisible(By locator) {
		WebElement webelement = getElement(locator);
		wait.until(ExpectedConditions.visibilityOf(webelement));
	}
	
	
	public static void selectContact(WebDriver driver, String name){
		Actions action = new Actions(driver);
		action.click(driver.findElement(By.xpath("//span[text()='"+name+"']"
				+ "//ancestor::td//preceding-sibling::td//input/.."))).perform();
		
	}
	
	public static String getTitleExplicitly(WebDriver driver, String title){
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();
	}
	
	public static WebElement waitForElementPresent(WebDriver driver, By locator, int timeout){
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		
		return driver.findElement(locator);
	}
	
	public static WebElement waitForElementWithFluentWait(WebDriver driver, final By locator) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class);

		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		return element;
	}
	
	
	
	public static void doMoveToElement(WebDriver driver, WebElement element) throws InterruptedException {
		Actions action = new Actions(driver);

		action.moveToElement(element).build().perform();

		Thread.sleep(3000);

	}
	
	public static WebElement openShadowRootElement(WebDriver driver, WebElement element) {
		WebElement ele = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot",
				element);
		return ele;
	}
	
	//Drop down without select class
	public static void getAllDropDownOptions(List<WebElement> optionsList) {

		int totalOptions = optionsList.size();

		System.out.println("total options: " + totalOptions);

		for (int i = 0; i < totalOptions; i++) {
			System.out.println(optionsList.get(i).getText());
		}

	}
	
	public static void selectDropDownValueByText(WebElement element, String value){
		Select select = new Select(element);
		select.selectByVisibleText(value);		
	}
	
	public static void selectDropDownValueByIndex(WebElement element, int index){
		Select select = new Select(element);
		select.selectByIndex(index);		
	}
	
	public static ArrayList<String> getAllOptionsList(WebElement element){
		Select select = new Select(element);
		ArrayList<String> ar = new ArrayList<String>();
		
		List<WebElement> optionsList = select.getOptions();
		System.out.println("total options : " + optionsList.size());
		
		for(int i=0; i<optionsList.size(); i++){
			String optionVal = optionsList.get(i).getText();
			//System.out.println(optionVal);
			ar.add(optionVal);
		}
		return ar;
	}
	
	
	
	
}
