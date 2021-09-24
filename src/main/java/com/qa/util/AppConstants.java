package com.qa.util;

import java.util.ArrayList;
import java.util.List;

public class AppConstants {

	public static int DEFAULT_EXPLICIT_TIME_OUT = 20;
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String ACCOUNT_PAGE_TITLE = "My Account";
	public static final String ACCOUNT_PAGE_HEADER = "Your Store";
	public static final int ACCOUNT_SECTION_COUNT = 4;
	
	public static List<String> getAccountSectionList() {
		List<String> accountList = new ArrayList<String>();
		accountList.add("My Account");
		accountList.add("My Orders");
		accountList.add("My Affiliate Account");
		accountList.add("Newsletter");
		return accountList;
	}
	
	
}
