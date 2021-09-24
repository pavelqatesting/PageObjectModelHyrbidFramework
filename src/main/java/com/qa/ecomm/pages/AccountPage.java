package com.qa.ecomm.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.base.BasePage;
import com.qa.util.AppConstants;
import com.qa.util.GenericFunction;


public class AccountPage extends BasePage{

	private WebDriver driver;
	GenericFunction genericFunction;
	
	//1. By locators -Page Object 
	private By accountPageHeader = By.cssSelector("div#logo a");
	private By accountSectionPageHeaders = By.cssSelector("div#content h2");
	private By searchField = By.cssSelector("div#search input[name='search']");
	private By searchButton = By.cssSelector("div#search button[type='button']");
	private By searchResults = By.cssSelector("div#content div.product-thumb h4 a");
	
	
	//2. Constructor of page class 
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		genericFunction = new GenericFunction(driver);
	}
	
	//3. Page actions/method
	
	/**
	 * This methods will be used to get the title of the page 
	 * @return the Acccount page title 
	 */
	public String getAccountPagetitle() {
		return genericFunction.getTitleExplicitly(AppConstants.ACCOUNT_PAGE_TITLE);
		
	}
	
	/**
	 * This method will be used to verify the account page header
	 * @return the account page header text
	 */
	public String isHomePageHeaderDisplay() {
		if(genericFunction.doIsDisplayed(accountPageHeader)) {
			return genericFunction.doGetText(accountPageHeader);
		}
		return null;
	}
	
	/**
	 * This methods will be used to count the header section 
	 * @return the size of header section
	 */
	public int getHeaderSectionSize() {
		return genericFunction.getElements(accountSectionPageHeaders).size();
	}
	
	/**
	 * This methods will be used to get all the header text 
	 * @return this will return the list of string header text  
	 */
	public List<String> getAccountSectionTextValue() {
		List<String> accountListText = new ArrayList<String>();
		List<WebElement> accountSectionList = genericFunction.getElements(accountSectionPageHeaders);
		
		for(int i = 0; i < accountSectionList.size(); i++ ) {
			String text = accountSectionList.get(i).getText();
			accountListText.add(text);
			System.out.println(text);
		}
		return accountListText;
	}
	
//	/**
//	 * I can write this method also like this 
//	 * This methods will be used to get all the header text 
//	 * @return this will return the list of string header text  
//	 */
//	public List<String> getAccountSectionTextValue1() {
//		List<String> accountListText = new ArrayList<String>();
//		List<WebElement> accountSectionList = genericFunction.getElements(accountSectionPageHeaders);
//		
//		for(WebElement e : accountSectionList) {
//			accountListText.add(e.getText());
//		}
//		return accountListText;
//	}
	
	/**
	 * This methods will be used to search the product then will click on the search button 
	 * @param the search products
	 */
	public void doSearch(String product) {
		genericFunction.doSendKeys(searchField, product);
		genericFunction.doClick(searchButton);
	}
	
	/**
	 * This methods will be used to verify the search produch display or not 
	 * @return true if the product display otherwise false 
	 */
	public boolean isSearchProductDisplay() {
		List<WebElement> searchResultsList = genericFunction.getElements(searchResults);
		
		if(searchResultsList.size() > 0) {
			return true;
		}
		return false;
					
	}
	public ProductInfoPage selectProductFromResults(String product) {
		//doSearch(product);
		List<WebElement> resultsItemList = genericFunction.getElements(searchResults);
		System.out.println("Total number of item displayed: "+resultsItemList.size());
		
		for(WebElement e : resultsItemList) {
			if(e.getText().equals(product)) {
//				//String element = e.;
//				genericFunction.doClick(e);
				e.click();
				break;
			}
//			else {
//				System.out.println("Did not fine the element");
//			}
		}
		
		return new ProductInfoPage(driver);

	}
}
