package com.wikia.webdriver.PageObjectsFactory.PageObject.Special;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class SpecialCreatePagePageObject extends SpecialPageObject {

	@FindBy (css = "#HiddenFieldsDialog input[name='wpTitle']")
	private WebElement titleInput;
	@FindBy (css = "#HiddenFieldsDialog #ok")
	private WebElement submitTitleInput;
	@FindBy (css = "#bodyContent")
	private WebElement contentInput;

	public SpecialCreatePagePageObject (WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public VisualEditModePageObject fillTitle(String title) {
		waitForElementByElement(titleInput);
		titleInput.sendKeys(title);
		waitForElementByElement(submitTitleInput);
		submitTitleInput.click();
		return new VisualEditModePageObject(driver);
	}

	public void addPageWithGivenTitleAndDefaultContent(String title) {
		fillTitle(title);
		WikiArticleEditMode article = new WikiArticleEditMode(driver);
		article.typeInContent(PageContent.articleText);
		article.clickOnPublish();
	}
}
