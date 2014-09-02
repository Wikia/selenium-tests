package com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapsPageObject;

/**
 * @author Rodrigo 'RodriGomez' Molinero
 * @author Lukasz Jedrzejczak
 * @author Lukasz Nowak (Dyktus)
 *
 */

public class CreateACustomMapComponentObject extends BasePageObject {

	public CreateACustomMapComponentObject(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css = "#intMapUpload")
	 private WebElement browseForFileInput;
	 @FindBy(css = "#intMapTileSetSearch")
	 private WebElement searchField;
	 @FindBy(css = "#intMapBack")
	 private WebElement backButton;
	 @FindBy(css = ".modalEvent>img")
	 private List<WebElement> templateList;
	 @FindBy(css = "#intMapError")
	 private WebElement errorField;
	 @FindBy(css = ".tile-set-thumb")
	 private List<WebElement> thumbCollection;
	 @FindBy(css = ".tile-set-thumb > strong")
	 private List<WebElement> thumbTitleCollection;
	 @FindBy(css = ".clear-search secondary")
	 private WebElement clearSearchTitleButton;
	 @FindBy(css = "#intMapTileSetsList")
	 private WebElement templatesBox;
	 @FindBy(css = ".close")
	 private WebElement closeButton;

	String beforeImageName = "116x116-";

	public InteractiveMapsPageObject clickCloseButton() {
		waitForElementByElement(closeButton);
		closeButton.click();
		return new InteractiveMapsPageObject(driver);
	}

	public TemplateComponentObject selectFileToUpload(String file) {
		browseForFileInput.sendKeys(getAbsolutePathForFile(PageContent.resourcesPath + file));
		PageObjectLogging.log("typeInFileToUploadPath", "type file " + file + " to upload it", true);
		return new TemplateComponentObject(driver);
	}

	public void typeSearchTile(String templateName) {
		waitForElementByElement(searchField);
		searchField.sendKeys(templateName);
		PageObjectLogging.log("typeTilesetName", "title (" + templateName + ") for template is typed in", true);
	}

	public TemplateComponentObject selectTemplate(int templateId) {
		waitForElementByElement(templateList.get(templateId));
		templateList.get(templateId).click();
		return new TemplateComponentObject(driver);
	}

	public String getSelectedTemplateImageName(int selectedImageIndex) {
		int imageNameIndex = templateList.get(selectedImageIndex).getAttribute("src").indexOf(beforeImageName);
		String selectedTemplateImageName = templateList.get(selectedImageIndex).getAttribute("src").substring(imageNameIndex
				+ beforeImageName.length());
		return selectedTemplateImageName;
	}

	public void verifyThereIsError() {
		waitForElementVisibleByElement(errorField);
		Assertion.assertEquals(checkIfElementOnPage(errorField), true);
	}

	public void verifyThereIsNoError() {
		Assertion.assertEquals(checkIfElementOnPage(errorField), false);
	}

	public void verifyTemplateWasFound() {
		waitForElementVisibleByElement(templatesBox);
		waitForElementByElement(thumbCollection.get(0));
		Assertion.assertEquals(checkIfElementOnPage(thumbCollection.get(0)), true);
	}

	public void clearSearchTitle() {
		waitForElementByElement(searchField);
		searchField.clear();
	}
	
	public CreateAMapComponentObject clickBack() {
		waitForElementByElement(backButton);
		backButton.click();
		PageObjectLogging.log("clickCustomMap", "custom map link clicked",  true, driver);
		return new CreateAMapComponentObject(driver);
	}

	public void verifyTemplateListElementVisible(int element) {
		waitForElementByElement(templateList.get(element));
	}
}
