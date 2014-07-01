package com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps;

import org.openqa.selenium.WebDriver;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapsPageObject;

/**
 * @author Rodrigo 'RodriGomez' Molinero
 *
 */

public class CreatePinTypesComponentObject extends BasePageObject{

	public CreatePinTypesComponentObject(WebDriver driver) {
		super(driver);
	}
	
	//UI Mapping
	@FindBy(css = "input[name='pinTypeNames[]']")
	private List<WebElement> namePinType;
	@FindBy(css = "#intMapNext")
	private WebElement nextButton;
	
	public void typePinTypeTitle(String pinTypeName) {
		WebElement firstPin = namePinType.get(0);
		waitForElementByElement(firstPin);
		namePinType.sendKeys(firstPin);
		PageObjectLogging.log("typePinTypeTitle", pinTypeName+" title for pin type is typed in", true);
	}
	
	public InteractiveMapsPageObject clickNext() {
		waitForElementClickableByElement(nextButton);
		nextButton.click();
		PageObjectLogging.log("clickNext", "clicked next button in naming pin types modal", true);
		return new InteractiveMapsPageObject(driver);
	}
	
		
}
