package com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;

/**
 * @author Rodrigo 'RodriGomez' Molinero
 *
 */

public class CreateACustomMapComponentObjectStep1 extends BasePageObject{

	public CreateACustomMapComponentObjectStep1(WebDriver driver) {
		super(driver);
	}
	
	//UI Mapping
	@FindBy(css="")
	private WebElement browseForFileInput;
	
	public CreateACustomMapComponentObjectStep2 selectFileToUpload(String file) {
		browseForFileInput.sendKeys(
				getAbsolutePathForFile(PageContent.resourcesPath + file));
		PageObjectLogging.log("typeInFileToUploadPath", "type file " + file + " to upload it", true);
		return new CreateACustomMapComponentObjectStep2(driver);
	}
	
}
	