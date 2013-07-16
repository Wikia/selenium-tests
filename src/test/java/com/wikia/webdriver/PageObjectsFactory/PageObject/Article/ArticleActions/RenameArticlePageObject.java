package com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticleActions;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author: Bogna 'bognix' Knychała
 */
public class RenameArticlePageObject extends ArticlePageObject {

	@FindBy(css="#wpNewTitleMain")
	private WebElement newNameInput;
	@FindBy(css=".mw-submit [name='wpMove']")
	private WebElement submitRename;

	public RenameArticlePageObject(WebDriver driver) {
		super(driver);
	}

	public ArticlePageObject rename(String newName) {
		newNameInput.clear();
		newNameInput.sendKeys(newName);
		submitRename.click();
		PageObjectLogging.log("ArticleRenamed", "Article renamed", true);
		return new ArticlePageObject(driver);
	}
}
