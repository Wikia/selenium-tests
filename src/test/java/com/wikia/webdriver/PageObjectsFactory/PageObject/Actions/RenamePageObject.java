package com.wikia.webdriver.PageObjectsFactory.PageObject.Actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;

/**
 * @author: Bogna 'bognix' Knychała
 */
public class RenamePageObject extends ArticlePageObject {

	@FindBy(css="#wpNewTitleMain")
	private WebElement newNameInput;
	@FindBy(css=".mw-submit [name='wpMove']")
	private WebElement submitRename;

	public RenamePageObject(WebDriver driver) {
		super(driver);
	}

	public ArticlePageObject rename(String newName) {
		newNameInput.clear();
		newNameInput.sendKeys(newName);
		scrollAndClick(submitRename);
		PageObjectLogging.log("ArticleRenamed", "Article renamed", true);
		return new ArticlePageObject(driver);
	}
}
