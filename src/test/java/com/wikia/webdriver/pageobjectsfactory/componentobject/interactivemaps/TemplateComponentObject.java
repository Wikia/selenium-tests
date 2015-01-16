package com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Rodrigo 'RodriGomez' Molinero
 * @author Lukasz Jedrzejczak
 */

public class TemplateComponentObject extends BasePageObject {

	public TemplateComponentObject(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "input[name='map-title']")
	private WebElement mapTitleField;
	@FindBy(css = "input[name='tile-set-title']")
	private WebElement nameTemplateField;
	@FindBy(css = "#intMapNext")
	private WebElement nextButton;
	@FindBy(css = "#intMapBack")
	private WebElement backButton;
	@FindBy(css = ".intMapPreviewImage")
	private WebElement templateImagePreview;
	@FindBy(css = "#intMapError")
	private WebElement mapError;

	public CreatePinTypesComponentObject clickNext() {
		waitForElementClickableByElement(nextButton);
		nextButton.click();
		PageObjectLogging.log("clickNext", "clicked next button in naming map modal", true, driver);
		return new CreatePinTypesComponentObject(driver);
	}

	public CreateACustomMapComponentObject clickBack() {
		waitForElementClickableByElement(backButton);
		backButton.click();
		PageObjectLogging.log("clickBack", "clicked back button in naming map modal", true, driver);
		return new CreateACustomMapComponentObject(driver);
	}

	public void typeMapName(String mapName) {
		waitForElementByElement(mapTitleField);
		mapTitleField.sendKeys(mapName);
		PageObjectLogging.log("typeMapName", mapName + " title for map typed in", true, driver);
	}

	public void typeTemplateName(String templateName) {
		waitForElementByElement(nameTemplateField);
		nameTemplateField.sendKeys(templateName);
		PageObjectLogging.log("typeTemplateName", templateName + " title for template typed in", true, driver);
	}

	public void verifyTemplateImagePreview() {
		waitForElementByElement(templateImagePreview);
	}

	public void verifyTemplateImage(String selectedTemplateName) {
		waitForElementByElement(templateImagePreview);
		Assertion.assertTrue(templateImagePreview.getAttribute("src").endsWith(selectedTemplateName));
	}

	public void verifyErrorExists() {
		waitForElementByElement(mapError);
		Assertion.assertEquals(checkIfElementOnPage(mapError), true);
	}
}
