package com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Robert 'rochan' Chan
 */
public class VECreateArticleModalComponentObject extends WikiBasePageObject {

	@FindBy(css = ".close")
	private WebElement closeButton;
	@FindBy(css = ".normal.secondary")
	private WebElement cancelButton;
	@FindBy(css = ".normal.primary")
	private WebElement addAPageButton;

	public VECreateArticleModalComponentObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void createPage() {
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
