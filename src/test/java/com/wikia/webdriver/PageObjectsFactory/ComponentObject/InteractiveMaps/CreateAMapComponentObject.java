package com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.sun.tools.javac.util.List;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;

/**
 * @author Rodrigo 'RodriGomez' Molinero
 *
 */

public class CreateAMapComponentObject extends BasePageObject{

	public CreateAMapComponentObject(WebDriver driver) {
		super(driver);
	}
	
	//UI Mapping
	@FindBy(css = "#intMapCustom")
	private WebElement customMapLink;
	@FindBy(css = ".modalEvent")
	private List<WebElement> existingTemplate;
	
	public CreateACustomMapComponentObjectStep1 clickCustomMap() {
		waitForElementByElement(customMapLink);
		customMapLink.click();
		PageObjectLogging.log("clickCustomMap", "custom map link clicked",  true, driver);
		return new CreateACustomMapComponentObjectStep1(driver);
	}

	public CreateACustomMapComponentObjectStep1 clickExistingTemplateMap() {
		waitForElementByElement(existingTemplate.get(0));
		WebElement firstExistingTemplate = existingTemplate.get(0);
		firstExistingTemplate.click();
		PageObjectLogging.log("clickExistingTemplateMap", "custom existing template clicked",  true, driver);
		return new CreateACustomMapComponentObjectStep1(driver);
	}
	
}
