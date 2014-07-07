package com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;

/**
 * @author Rodrigo 'RodriGomez' Molinero
 *
 */

public class CreateACustomMapComponentObjectStep2 extends BasePageObject{

	public CreateACustomMapComponentObjectStep2(WebDriver driver) {
		super(driver);
	}
	
	//UI Mapping
	@FindBy(css = "#intMapTitle")
	private WebElement mapTitleField;
	@FindBy(css = "#intMapNext")
	private WebElement nextButton;
	@FindBy(css = "#intMapPreviewImage")
	private WebElement templateImagePreview;
	@FindBy(css = "input[name='tile-set-title[]']")
	private WebElement templateImageField;
	
	
	public void clickMapTitleField() {
		waitForElementClickableByElement(mapTitleField);
		mapTitleField.click();
		PageObjectLogging.log("clickMapTitleField", "clicked Map Title field", true);
	}
	
	public void typeMapName(String mapName) {
		waitForElementByElement(mapTitleField);
		mapTitleField.sendKeys(mapName);
		PageObjectLogging.log("typeMapName", mapName+" title for map typed in", true);
	}
	
	public CreatePinTypesComponentObject clickNext() {
		waitForElementClickableByElement(nextButton);
		nextButton.click();
		PageObjectLogging.log("clickNext", "clicked next button in naming map modal", true);
		return new CreatePinTypesComponentObject(driver);
	}

	public void verifyTemplateImagePreview() {
		waitForElementByElement(templateImagePreview);
		
	}

	public void typeTemplateName(String templateName) {
		waitForElementByElement(templateImageField);
		mapTitleField.sendKeys(templateName);
		PageObjectLogging.log("typeTemplateName", templateName+" title for map typed in", true);
	}
}