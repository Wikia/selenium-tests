package com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapsPageObject;
import com.wikia.webdriver.Common.Core.Assertion;

/**
 * @author Rodrigo 'RodriGomez' Molinero
 * @author Lukasz Jedrzejczak
 *
 */

public class TemplateComponentObject extends BasePageObject {

	public TemplateComponentObject(WebDriver driver) {
		super(driver);
	}

	// UI Mapping
	@FindBy(css = "input[name='map-title']")
	private WebElement mapTitleField;
	@FindBy(css = "input[name='tile-set-title']")
	private WebElement nameTemplateField;
	@FindBy(css = "#intMapNext")
	private WebElement nextButton;
	@FindBy(css = "#intMapBack")
	private WebElement backButton;
	@FindBy(css = ".close")
	private WebElement closeButton;
	@FindBy(css = ".intMapPreviewImage")
	private WebElement templateImagePreview;
	@FindBy(css = ".modal.medium.int-map-modal")
	private WebElement newTemplateDialog;

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

	public InteractiveMapsPageObject closeTemplateComponentDialog() {
		waitForElementClickableByElement(closeButton);
		closeButton.click();
		PageObjectLogging.log("closeTemplateComponentDialog", "Template dialog was closed", true, driver);
		return new InteractiveMapsPageObject(driver);
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

	public void verifyTemplateDialog() {
		waitForElementByElement(newTemplateDialog);
		PageObjectLogging.log("verifyTemplateDialog", "Template dialog is visible", true);
	}

	public void verifyTemplateImagePreview() {
		waitForElementByElement(templateImagePreview);
	}

	public void verifyTemplateImage(String selectedTemplateName) {
		waitForElementByElement(templateImagePreview);
		Assertion.assertTrue(templateImagePreview.getAttribute("src").endsWith(selectedTemplateName));
	}
}
