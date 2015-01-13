package com.wikia.webdriver.pageobjectsfactory.pageobject.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

/**
 * @author: Bogna 'bognix' Knychała
 */
public class RenamePageObject extends ArticlePageObject {

	@FindBy(css = "#wpNewTitleMain")
	private WebElement newNameInput;
	@FindBy(css = ".mw-submit [name='wpMove']")
	private WebElement submitRename;
	@FindBy(css = "input[name='wpConfirm']")
	private WebElement confirmRename;
	@FindBy(css = "input[name='wpDeleteAndMove']")
	private WebElement submitDeleteAndMove;

	public RenamePageObject(WebDriver driver) {
		super(driver);
	}

	public RenamePageObject rename(String newName) {
		newNameInput.clear();
		newNameInput.sendKeys(newName);
		scrollAndClick(submitRename);
		PageObjectLogging.log("ArticleRenamed", "Article renamed", true);
		return this;
	}

	public RenamePageObject confirmRename() {
		scrollAndClick(confirmRename);
		scrollAndClick(submitDeleteAndMove);
		PageObjectLogging.log("ConfirmArticleRename", "Confirmed article rename", true);
		return this;
	}
}
