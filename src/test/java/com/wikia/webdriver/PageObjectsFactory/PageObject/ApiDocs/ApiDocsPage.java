package com.wikia.webdriver.PageObjectsFactory.PageObject.ApiDocs;

import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiDocsPage extends BasePageObject {
	private static Logger logger = LoggerFactory.getLogger(ApiDocsPage.class);

	public ApiDocsPage(WebDriver driver) {
		super(driver);
	}
}
