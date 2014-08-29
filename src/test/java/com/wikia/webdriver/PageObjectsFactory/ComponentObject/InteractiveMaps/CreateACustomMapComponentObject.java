package com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

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

	// UI Mapping
	@FindBy(css = "#intMapUpload")
	private WebElement browseForFileInput;
	@FindBy(css = "#intMapTileSetSearch")
	private WebElement searchField;
	@FindBy(css = "#intMapBack")
	private WebElement backButton;
	@FindBy(css = ".modalEvent>img")
	private List<WebElement> templateList;
	@FindBy(css = ".map-modal-error")
	private WebElement errorField;
	@FindBy(css = "li[class=tile-set-thumb]")
	private List<WebElement> thumbCollection;
	@FindBy(css = ".clear-search secondary")
	private WebElement clearSearchTitleButton;
	@FindBy(css = "#intMapTileSetsList")
	private WebElement templatesBox;
	String beforeImageName = "116x116-";

	public void clearSearchTitle() {
		waitForElementByElement(searchField);
		searchField.clear();
	}
	
	public String getSelectedTemplateImageName(int selectedImageIndex) {
		int imageNameIndex = templateList.get(selectedImageIndex).getAttribute("src").indexOf(beforeImageName);
		String selectedTemplateImageName = templateList.get(selectedImageIndex).getAttribute("src").substring(imageNameIndex
				+ beforeImageName.length());
		return selectedTemplateImageName;
	}
	
	public TemplateComponentObject selectFileToUpload(String file) {
		browseForFileInput.sendKeys(getAbsolutePathForFile(PageContent.resourcesPath + file));
		PageObjectLogging.log("typeInFileToUploadPath", "type file " + file + " to upload it", true);
		return new TemplateComponentObject(driver);
	}

	public TemplateComponentObject selectTemplate(int templateId) {
		waitForElementByElement(templateList.get(templateId));
		templateList.get(templateId).click();
		return new TemplateComponentObject(driver);
	}

	public void typeSearchTile(String templateName) {
		waitForElementByElement(searchField);
		searchField.sendKeys(templateName);
		PageObjectLogging.log("typeTilesetName", "title (" + templateName + ") for template is typed in", true);
	}

	public void verifyThereIsError() {
		waitForElementVisibleByElement(errorField);
		Assertion.assertEquals(checkIfElementOnPage(errorField), true);
	}

	public void verifyTemplateWasFound() {
		waitForElementVisibleByElement(templatesBox);
		waitForElementByElement(thumbCollection.get(0));
		Assertion.assertEquals(checkIfElementOnPage(thumbCollection.get(0)), true);
	}

}
