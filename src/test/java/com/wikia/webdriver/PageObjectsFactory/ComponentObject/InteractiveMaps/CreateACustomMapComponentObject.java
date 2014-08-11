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
 * @author Lukasz Jedrzejczak
 * @author Lukasz Nowak (Dyktus)
 *
 */

public class CreateACustomMapComponentObject extends BasePageObject{

	public CreateACustomMapComponentObject(WebDriver driver) {
		super(driver);
	}
	
	//UI Mapping
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

	
	String beforeImageName = "116x116-";
	
	public TemplateComponentObject selectFileToUpload(String file) {
		browseForFileInput.sendKeys(
				getAbsolutePathForFile(PageContent.resourcesPath + file));
		PageObjectLogging.log("typeInFileToUploadPath", "type file " + file + " to upload it", true);
		return new TemplateComponentObject(driver);
	}
	
	public void typeSearchTile(String templateName) {
		waitForElementByElement(searchField);
		searchField.sendKeys(templateName);
		PageObjectLogging.log("typeTilesetName", "title ("+templateName+") for template is typed in", true);
	}

	public TemplateComponentObject selectTemplate(int templateId) {
		waitForElementByElement(templateList.get(templateId));
		templateList.get(templateId).click();
		return new TemplateComponentObject(driver);
	}
	
	public String getSelectedTemplateImageName(int selectedImageIndex) {
		int imageNameIndex = templateList.get(selectedImageIndex).getAttribute("src").indexOf(beforeImageName);
		String selectedTemplateImageName = templateList.get(selectedImageIndex)
			.getAttribute("src").substring(imageNameIndex + beforeImageName.length());
		return selectedTemplateImageName;
	}
	
	public void verifyThereIsError() {
		waitForElementByElement(errorField);
		if(errorField.getText().isEmpty()){
			PageObjectLogging.log("verifyThereIsError", "Template was found. Error was not showed.",false,driver);
		}else{
			PageObjectLogging.log("verifyThereIsError", "Template was not found. Error was showed",true,driver);
		}
	}
	
	public void verifyTemplateWasFound(String query) { 
		waitForElementByElement(templatesBox);
		waitForElementByElement(thumbTitleCollection.get(0));
		if(thumbCollection.size()>0){			
			PageObjectLogging.log("verifyTemplateWasFound", "Some template was found but not this which you searched",true,driver);
		}else{
			PageObjectLogging.log("verifyTemplateWasFound", "Template was not found",false,driver);
		}
	}
	
	public void clearSearchTitle() { 
		waitForElementByElement(searchField);
		searchField.clear();
	}
	
	public void verifyChangeContent(Integer firstAmount, Integer secondAmount) { 
		if(firstAmount==secondAmount){
			PageObjectLogging.log("verifyChangeContent", "Content was not changed",false,driver);
		}else{
			PageObjectLogging.log("verifyChangeContent", "Content was changed",true,driver);	
		}
	}
	
}