package com.wikia.webdriver.pageobjectsfactory.pageobject.messagewall;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class NewMessageWallCloseRemoveThreadPageObject extends WikiBasePageObject {

	@FindBy(css = ".wall-action-reason")
	private WebElement removeReasonField;
	@FindBy(css = "#WikiaConfirmOk")
	private WebElement removeConfirmButton;

	public NewMessageWallCloseRemoveThreadPageObject(WebDriver driver) {
		super(driver);
	}

	public NewMessageWall closeRemoveThread(String reason) {
		removeReasonField.sendKeys(reason);
		removeConfirmButton.click();
		waitForElementNotPresent(modalWrapper);
		PageObjectLogging.log("closeRemoveThread", "thread removed with reason " + reason, true);
		return new NewMessageWall(driver);
	}
}
