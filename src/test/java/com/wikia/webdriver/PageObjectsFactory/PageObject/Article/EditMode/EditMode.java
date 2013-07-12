package com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode;

import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author: Bogna 'bognix' Knychała
 */
public class EditMode extends WikiBasePageObject {

	@FindBy(css="#wpSave")
	private WebElement submitButton;

	public EditMode(WebDriver driver) {
		super(driver);
	}

	public ArticlePageObject submit() {
		driver.switchTo().defaultContent();
		submitButton.click();
		return new ArticlePageObject(driver);
	}
}
