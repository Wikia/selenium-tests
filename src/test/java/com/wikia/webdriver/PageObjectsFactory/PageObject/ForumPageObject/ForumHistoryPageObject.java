package com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

public class ForumHistoryPageObject extends WikiBasePageObject{

	@FindBy(css="#WallThreadHistory")
	private WebElement threadHistoryTable;
	@FindBy(css="#WallThreadHistory tr:nth-child(1) td:nth-child(3)")
	private WebElement creatorActionCell;
	
	public ForumHistoryPageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void verifyImportandPageElements() {
		waitForElementByElement(threadHistoryTable);
		waitForTextToBePresentInElementByElement(creatorActionCell, "created this thread");
		PageObjectLogging.log("verifyImportandPageElements", "thread history page basic content verified", true);
	}
}
