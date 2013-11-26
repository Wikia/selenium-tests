package com.wikia.webdriver.PageObjectsFactory.PageObject.ApiDocs;

import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ApiDocsPage extends WikiBasePageObject {
	@FindBy(css=".response_code > pre")
	private WebElement responseCodeContainer;
	@FindBy(css="input.parameter[name='id']")
	private WebElement idInput;
	@FindBy(css="input.submit.bt_large_blue[name='commit']")
	private List<WebElement> submitButtons;

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
		waitForTextToBePresentInOneOfElementByBy(parameterNames, parameterName);
	}

	public void waitForModelPropertyDescription(String propertyName) {
		waitForTextToBePresentInOneOfElementByBy(modelPropertyNames, propertyName);
	}

	public void putParameter( String parameterName, String value ) {
		//WebElement element = By.cssSelector("input.parameter[name='" + parameterName + "']").findElement(driver);
		if ( parameterName.equals("id") ) {
			idInput.clear();
			sendKeys(idInput, value);
		} else {
			throw new UnsupportedOperationException("don't support parameter: " + parameterName);
		}
	}

	public void clickTryItOut() {
		//waitForElementByElement(submitButton);
		scrollAndClick(getSingleVisibleTryItOutButton());
	}

	public void validateResponseCode(int responseCode) {
		waitForTextToBePresentInElementByElement(responseCodeContainer, String.valueOf(responseCode));
	}

	private WebElement getSingleVisibleTryItOutButton() {
		WebElement button = null;
		for ( WebElement element: submitButtons ) {
			if ( element.isDisplayed() ) {
				if ( button == null ) {
					button = element;
				} else {
					throw new IllegalStateException("More than one button visible.");
				}
			}
		}
		return button;
	}
}
