package com.wikia.webdriver.PageObjectsFactory.ComponentObject.ModalWindows;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

/**
 *
 * @author Robert 'rochan' Chan
 */
public class VECreateArticleModalComponentObject extends WikiBasePageObject {

	@FindBy (css = ".close")
	private WebElement closeButton;
	@FindBy (css = ".normal.secondary")
	private WebElement cancelButton;
	@FindBy (css = ".normal.primary")
	private WebElement addAPageButton;
	@FindBy (css = "#CreatePageModalDialog section div")
	private WebElement modalText;
	@FindBy (css = "#CreatePageModalDialog h3")
	private WebElement modalTitle;
	@FindBy (css = "#CreatePageModalDialog")
	private WebElement modal;

	public VECreateArticleModalComponentObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void createPage() {
		waitForElementVisibleByElement(modal);
		waitForElementClickableByElement(addAPageButton);
		addAPageButton.click();
		PageObjectLogging.log("createPage", "Add A Page button is clicked", true);
	}

	public void closeModal() {
		closeButton.click();
		PageObjectLogging.log("closeModal", "The 'X' button is clicked", true);
	}

	public void cancel() {
		cancelButton.click();
		PageObjectLogging.log("cancel", "The cancel button is clicked", true);
	}
}
