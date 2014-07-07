package com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.sun.tools.javac.util.List;
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
	
	
	
	public  selectTemplate(int templateId) {
		waitForElementClickableByElement(templateList);
		WebElement
	}
	
}
	