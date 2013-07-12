package com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author: Bogna 'bognix' Knychała
 */
public class VisualEditModePageObject extends EditMode {

	@FindBy(css="#cke_contents_wpTextbox1 iframe")
	private WebElement visualEditMode;
	@FindBy(css="#bodyContent")
	private WebElement contentInput;

	public VisualEditModePageObject(WebDriver driver) {
		super(driver);
		driver.switchTo().frame(visualEditMode);
	}

	public void addContent(String content) {
		contentInput.clear();
		contentInput.sendKeys(content);
	}
}
