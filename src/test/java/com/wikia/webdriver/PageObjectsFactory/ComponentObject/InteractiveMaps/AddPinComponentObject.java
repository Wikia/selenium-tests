package com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

/**
 * @author Rodrigo 'RodriGomez' Molinero
 *
 */

public class AddPinComponentObject extends BasePageObject{

	public AddPinComponentObject(WebDriver driver) {
		super(driver);
	}
	
	//UI Mapping
	@FindBy(css = "#intMapArticleTitle")
	private WebElement associatedArticleField;
	
	public void verifyAssociatedArticleFieldIsDisplayed() {
		waitForElementByElement(associatedArticleField);
		associatedArticleField.click();
	}
	
}
