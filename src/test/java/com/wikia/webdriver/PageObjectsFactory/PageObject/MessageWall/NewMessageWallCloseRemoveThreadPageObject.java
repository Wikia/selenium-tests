package com.wikia.webdriver.PageObjectsFactory.PageObject.MessageWall;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class NewMessageWallCloseRemoveThreadPageObject extends WikiBasePageObject {

	@FindBy(css=".wall-action-reason")
	private WebElement removeReasonField;
	@FindBy(css="#WikiaConfirmOk")
	private WebElement removeConfirmButton;

	public NewMessageWallCloseRemoveThreadPageObject(WebDriver driver) {
		super(driver);
	}

	public NewMessageWall closeRemoveThread(String reason) {
		removeReasonField.sendKeys(reason);
		removeConfirmButton.click();
		PageObjectLogging.log("closeRemoveThread", "thread removed with reason " + reason, true);
		return new NewMessageWall(driver);
	}
}
