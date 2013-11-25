package com.wikia.webdriver.PageObjectsFactory.PageObject.ApiDocs;

import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ApiDocsPage extends WikiBasePageObject {

	private By parameterNames = By.cssSelector(".operation-params > tr > td.code");
	private By modelPropertyNames = By.cssSelector(".propName");

	public ApiDocsPage(WebDriver driver) {
		super(driver);
	}

	public void openApiDocsPage(String wikiUrl, String apiVersion) {
		if (apiVersion.equals("v1")) {
			getUrl(wikiUrl + "api/v1/");
		} else {
			throw new IllegalArgumentException(String.format("invalid apiVersion parameter: '%s'.", apiVersion));
		}
	}

	public void waitForParameterDescription(String parameterName) {
		waitForTextToBePresentInElementByBy(parameterNames, parameterName);
	}

	public void waitForModelPropertyDescription(String propertyName) {
		waitForTextToBePresentInElementByBy(modelPropertyNames, propertyName);
	}
}
