package com.qa.ecomm.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.base.BasePage;
import com.qa.util.GenericFunction;

public class ProductInfoPage extends BasePage{
	
	private WebDriver driver;
	private GenericFunction genericFunction;

	private By productNameHeader = By.cssSelector("div#content h1");
	private By productMetaData = By.cssSelector("div#content ul[class='list-unstyled']:nth-of-type(1) li");
	private By productPrice = By.cssSelector("div#content ul[class='list-unstyled']:nth-of-type(2) li");
	private By quantity = By.cssSelector("#input-quantity");
	private By addToCartButton = By.cssSelector("div.form-group #button-cart");
	private By ProductImages = By.cssSelector("div#content .thumbnail img");
	
	// Constractor 
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		genericFunction = new GenericFunction(this.driver);
	}
	
	// Page Action/Methods 
	 
	/**
	 * This methods will return the product page title
	 * @param product page title 
	 * @return 
	 */
	public String getProductPageTitle(String product) {
		String title = genericFunction.getTitleExplicitly(product);
		System.out.println("Page title is: "+title);
		return title;
	}
	
	public Map<String, String> getProductInformation() {
		Map<String, String> productInfoMap = new HashMap<String, String>();
		
		String ProductName = genericFunction.getElement(productNameHeader).getText();
		productInfoMap.put("Product Name", ProductName);
		
		List<WebElement> productMataDataList = genericFunction.getElements(productMetaData);
		
		for(int i = 0; i < productMataDataList.size(); i++) {
			String key = productMataDataList.get(i).getText().split(":")[0].trim();
			String pairs = productMataDataList.get(i).getText().split(":")[1].trim();
			productInfoMap.put(key, pairs);
		}
		
		List<WebElement> productPriceList = genericFunction.getElements(productPrice);

		productInfoMap.put("Price", productPriceList.get(0).getText().trim());
		productInfoMap.put("Excluding price", productPriceList.get(1).getText().split(":")[1].trim());
		
		//System.out.println(productInfoMap);
		return productInfoMap;
	}

	/**
	 * I can write avoble methods like this way as well
	 */
	public Map<String, String> getProductInformation2() {
		Map<String, String> productInfoMap = new HashMap<String, String>();
		
		String ProductName = genericFunction.getElement(productNameHeader).getText();
		productInfoMap.put("Product Name", ProductName);
		
		List<WebElement> productMataDataList = genericFunction.getElements(productMetaData);
		
		for(WebElement e : productMataDataList) {
			productInfoMap.put(e.getText().split(":")[0].trim(), e.getText().split(":")[1].trim());
		}
		
		List<WebElement> productPriceList = genericFunction.getElements(productMetaData);

		productInfoMap.put("Price", productPriceList.get(0).getText());
		productInfoMap.put("Excluding price", productPriceList.get(1).getText().split(":")[1].trim());
		
		//System.out.println(productInfoMap);
		
		return productInfoMap;
	}
	
	/**
	 * This methods will count the total number of images
	 * @return the total count of images 
	 */
	public int getProductImages() {
		int size = genericFunction.getElements(ProductImages).size();
		System.out.println("Total number of images are "+size);
		return size;
	}
	
	//////////
	/**
	 * This methods will be used to enter the quentity 
	 * @param qty quantity
	 */
	public void selectQuantity(String qty) {
		
		genericFunction.doSendKeys(this.quantity, qty);
	}
	
	/**
	 * This methods will click on add to cart button 
	 */
	public void addToCart() {
		genericFunction.doClick(addToCartButton);
	}
	
	
}
