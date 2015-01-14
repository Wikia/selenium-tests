package com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Bogna 'bognix' Knychala
 */
public class CreateArticleModalComponentObject extends WikiBasePageObject {

	@FindBy(css = "#wpCreatePageDialogTitle")
	private WebElement titleInput;
	@FindBy(css = ".button.normal.primary")
	private WebElement createPageButton;

	public CreateArticleModalComponentObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void createPage(String title) {
		waitForElementByElement(titleInput);
		sendKeys(titleInput, title);
		waitForElementByElement(createPageButton);
		scrollAndClick(createPageButton);
		PageObjectLogging.log(
			"PageCreated",
			"Page with given title created",
			true
		);
	}
}
