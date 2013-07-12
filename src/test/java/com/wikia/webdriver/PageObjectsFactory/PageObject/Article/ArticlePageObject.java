package com.wikia.webdriver.PageObjectsFactory.PageObject.Article;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author Bogna 'bognix' Knychała
 */
public class ArticlePageObject extends WikiBasePageObject {

	@FindBy(css="#WikiaPageHeader h1")
	protected WebElement articleHeader;
	@FindBy(css="#mw-content-text p")
	protected WebElement articleContent;
	@FindBy(css="#WikiHeader .drop")
	protected WebElement contributeDropdown;
	@FindBy(css=".WikiaMenuElement .createpage")
	protected WebElement addArticleInDropdown;
	@FindBy(css="#wpCreatePageDialogTitle")
	protected WebElement articleTitleInputModal;
	@FindBy(css="#CreatePageDialogButton .createpage")
	protected WebElement submitModal;
	@FindBy(css="#ca-edit")
	protected WebElement editDropdown;

	public ArticlePageObject(WebDriver driver) {
		super(driver);
	}

	public void compareTitle(String title) {
		waitForTextToBePresentInElementByElement(articleHeader, title);
		Assertion.assertEquals(articleHeader.getText(), title);
	}

	public void compareContent(String content) {
		waitForTextToBePresentInElementByElement(articleContent, content);
		Assertion.assertStringContains(articleContent.getText(), content);
	}

	public VisualEditModePageObject createArticleUsingDropdown(String articleTitle) {
		contributeDropdown.click();
		addArticleInDropdown.click();
		articleTitleInputModal.sendKeys(articleTitle);
		submitModal.click();
		return new VisualEditModePageObject(driver);
	}

	public VisualEditModePageObject editArticleUsingDropdown() {
		editDropdown.click();
		return new VisualEditModePageObject(driver);
	}

}
