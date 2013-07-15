package com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPageMonoBook;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BaseMonoBookPageObject;

/**
 * @author lukaszk
 */
public class WikiArticleUserMonoBookPageObject extends BaseMonoBookPageObject {

	@FindBy(css = "#firstHeading")
	private WebElement titleLocator;
	@FindBy(css = "#ca-edit a")
	private WebElement editLink;
	@FindBy(css = "#wpTextbox1")
	private WebElement editionArea;
	@FindBy(css = ".oasis-only-warning")
	private WebElement oasisOnly;


	private String articleTitle;
	private final String commentsSelector = ".comments > li > .speech-bubble-message";

	public WikiArticleUserMonoBookPageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		articleTitle = getArticleTitle();
	}

	public void verifyEditionArea() {
		waitForElementByElement(editionArea);
		PageObjectLogging.log("verifyEditArea", "verify that edition area is present", true);
	}

	public void openArticle(String articleName) {
		String articleTitleWithUnderscore = articleName.replace(" ", "_");
		String url = Global.DOMAIN + "wiki/" + articleTitleWithUnderscore;
		getUrl(url);
	}

	private String getArticleTitle() {
		waitForElementByElement(titleLocator);
		String title = titleLocator.getText();
		return title;
	}

	public void clickEdit() {
		scrollAndClick(editLink);
		PageObjectLogging.log("clickEdit", "click on Edit link", true, driver);
	}

	public void verifyOasisOnly() {
		waitForElementByElement(oasisOnly);
		PageObjectLogging.log("verifyOasisOnly", "Oasis only warning is present", true, driver);
	}

}
