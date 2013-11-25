package com.wikia.webdriver.PageObjectsFactory.PageObject.ApiDocs;

import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiDocsPage extends WikiBasePageObject {
	private static Logger logger = LoggerFactory.getLogger(ApiDocsPage.class);

	public ApiDocsPage(WebDriver driver) {
		super(driver);
	}

    public void openApiDocsPage( String wikiUrl, String apiVersion ) {
        if ( apiVersion.equals("v1") ) {
            getUrl(wikiUrl + "api/v1/");
        } else {
            throw new IllegalArgumentException(String.format("invalid apiVersion parameter: '%s'.", apiVersion));
        }
    }
}
