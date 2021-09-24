package com.qa.ecomm.tests;

import static org.testng.Assert.assertEquals;

import java.util.Map;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.BasePage;
import com.qa.ecomm.pages.AccountPage;
import com.qa.ecomm.pages.LoginPage;
import com.qa.ecomm.pages.ProductInfoPage;

public class ProductInfoTest {

	LoginPage loginPage;
	AccountPage accountPage;
	BasePage basePage;
	Properties prop;
	WebDriver driver;
	ProductInfoPage productInfoPage;
	
	@BeforeMethod
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.initializeProperty();
		driver = basePage.initializeDriver(prop);
		loginPage = new LoginPage(driver);
	}
	
	@Test (priority = 1)
	public void verifyProductInfoMacBookTest() {
		String product = "MACBOOK";
		accountPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		accountPage.doSearch(product);
		
		productInfoPage = accountPage.selectProductFromResults("MacBook Pro");
		
		String actualTitle = productInfoPage.getProductPageTitle("MacBook Pro");
		String expectedTitle = "MacBook Pro";
		Assert.assertTrue(actualTitle.contains(expectedTitle));
		
		Assert.assertTrue(productInfoPage.getProductImages() == 4);
		Map<String, String> productInfo = productInfoPage.getProductInformation();
		//Map<String, String> productInfo = productInfoPage.getProductInformation2();
		System.out.println("Product info map: "+productInfo);
		
		/*
		 * {Brand=Apple, 
		 * Availability=Out Of Stock, 
		 * Price=$2,000.00, 
		 * Product Name=MacBook Pro, 
		 * Product Code=Product 18, 
		 * Reward Points=800, 
		 * Excluding price=Product 18}
		 */
		assertEquals(productInfo.get("Brand"), "Apple");
		assertEquals(productInfo.get("Availability"), "Out Of Stock");
		assertEquals(productInfo.get("Product Name"), "MacBook Pro");
		assertEquals(productInfo.get("Product Code"), "Product 18");
		assertEquals(productInfo.get("Reward Points"), "800");
		assertEquals(productInfo.get("Price"), "$2,000.00");
		assertEquals(productInfo.get("Excluding price"), "$2,000.00");

		productInfoPage.selectQuantity("3");
		productInfoPage.addToCart();
		sleep(5000);
		
	}
	
	@Test (priority = 2)
	public void verifyImacInfoTest() {
		String product = "imac";
		accountPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		accountPage.doSearch(product);
		
		productInfoPage = accountPage.selectProductFromResults("iMac");
		
		String actualTitle = productInfoPage.getProductPageTitle("iMac");
		String expectedTitle = "iMac";
		Assert.assertTrue(actualTitle.contains(expectedTitle));
		
		Assert.assertTrue(productInfoPage.getProductImages() == 3);
		Map<String, String> productInfo = productInfoPage.getProductInformation();
		//Map<String, String> productInfo = productInfoPage.getProductInformation2();
		System.out.println("Product info map: "+productInfo);
		
		/*
		 * {Brand=Apple, 
		 * Availability=Out Of Stock, 
		 * Price=$2,000.00, 
		 * Product Name=MacBook Pro, 
		 * Product Code=Product 18, 
		 * Reward Points=800, 
		 * Excluding price=Product 18}
		 */
		assertEquals(productInfo.get("Brand"), "Apple");
		assertEquals(productInfo.get("Availability"), "Out Of Stock");
		assertEquals(productInfo.get("Product Name"), "iMac");
		assertEquals(productInfo.get("Product Code"), "Product 14");
		//assertEquals(productInfo.get("Reward Points"), "800");
		assertEquals(productInfo.get("Price"), "$100.00");
		assertEquals(productInfo.get("Excluding price"), "$100.00");

		productInfoPage.selectQuantity("3");
		productInfoPage.addToCart();
		sleep(10000);
		
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
