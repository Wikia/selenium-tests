package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.editmode.WikiArticleEditMode;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Bogna 'bognix' Knychala
 */
public class SpecialCreatePagePageObject extends SpecialPageObject {

	@FindBy(css = "#HiddenFieldsDialog input[name='wpTitle']")
	private WebElement titleInput;
	@FindBy(css = "#HiddenFieldsDialog #ok")
	private WebElement submitTitleInput;

	public SpecialCreatePagePageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public VisualEditModePageObject populateTitleField(String title) {
		waitForElementByElement(titleInput);
		titleInput.sendKeys(title);
		waitForElementByElement(submitTitleInput);
		submitTitleInput.click();
		return new VisualEditModePageObject(driver);
	}

	public void addPageWithGivenTitleAndDefaultContent(String title) {
		populateTitleField(title);
		WikiArticleEditMode article = new WikiArticleEditMode(driver);
		article.typeInContent(PageContent.ARTICLE_TEXT);
		article.clickOnPublish();
	}
}
