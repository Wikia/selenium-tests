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
 *
 */

public class CreateACustomMapComponentObject extends BasePageObject{

	public CreateACustomMapComponentObject(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css = "#intMapUpload")
	private WebElement browseForFileInput;
	@FindBy(css = ".modalEvent>img")
	private List<WebElement> templateList;
	@FindBy(css = "#intMapBack")
	private WebElement backButton;
	
	String beforeImageName = "116x116-";
	
	public TemplateComponentObject selectFileToUpload(String file) {
		browseForFileInput.sendKeys(
				getAbsolutePathForFile(PageContent.resourcesPath + file));
		PageObjectLogging.log("typeInFileToUploadPath", "type file " + file + " to upload it", true);
		return new TemplateComponentObject(driver);
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
