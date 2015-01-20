package com.wikia.webdriver.pageobjectsfactory.pageobject.actions;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author: Bogna 'bognix' Knychała
 */
public class RenamePageObject extends ArticlePageObject {

	@FindBy(css = "#wpNewTitleMain")
	private WebElement newNameInput;
	@FindBy(css = ".mw-submit [name='wpMove']")
	private WebElement submitRename;
	@FindBy(css = "input#wpLeaveRedirect")
	private WebElement leaveRedirect;

	public RenamePageObject(WebDriver driver) {
		super(driver);
	}

	public ArticlePageObject rename(String newName) {
		newNameInput.clear();
		newNameInput.sendKeys(newName);
		leaveRedirect.click();
		scrollAndClick(submitRename);
		PageObjectLogging.log("ArticleRenamed", "Article renamed", true);
		return new ArticlePageObject(driver);
	}
}
