package com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticleActions;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author: Bogna 'bognix' Knychała
 */
public class DeleteArticlePageObject extends ArticlePageObject {

	@FindBy(css="#wpConfirmB")
	private WebElement submitButton;

	public DeleteArticlePageObject(WebDriver driver) {
		super(driver);
	}

	public WikiBasePageObject submitDeletion() {
		submitButton.click();
		PageObjectLogging.log("ArticleDeleted", "Article deleted", true);
		return new WikiBasePageObject(driver);
	}
}
