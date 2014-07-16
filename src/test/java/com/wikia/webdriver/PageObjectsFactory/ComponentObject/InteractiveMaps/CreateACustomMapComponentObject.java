package com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

/**
 * @author Rodrigo 'RodriGomez' Molinero
 *
 */

public class CreateACustomMapComponentObject extends BasePageObject{

	public CreateACustomMapComponentObject(WebDriver driver) {
		super(driver);
	}
	
	//UI Mapping
	@FindBy(css = "#intMapUploadLink")
	private WebElement browseForFileInput;
	@FindBy(css = "#intMapTileSetSearch")
	private WebElement searchField;
	@FindBy(css = "#intMapBack")
	private WebElement backButton;
	@FindBy(css = ".tile-set-thumb")
	private List<WebElement> templateList;
	
	
	public NewTemplateComponentObject selectFileToUpload(String file) {
		browseForFileInput.sendKeys(
				getAbsolutePathForFile(PageContent.resourcesPath + file));
		PageObjectLogging.log("typeInFileToUploadPath", "type file " + file + " to upload it", true);
		return new NewTemplateComponentObject(driver);
	}
	
	public void typeTilesetName(String templateName) {
		waitForElementByElement(searchField);
		searchField.sendKeys(templateName);
		PageObjectLogging.log("typeTilesetName", templateName+" title for template is typed in", true);
	}

	public TemplateComponentObject selectTemplate(int templateId) {
		waitForElementByElement(browseForFileInput);
		WebElement templateSelected = templateList.get(templateId);
		templateSelected.click();
		return new TemplateComponentObject(driver);
	}
	
	
	
}
	