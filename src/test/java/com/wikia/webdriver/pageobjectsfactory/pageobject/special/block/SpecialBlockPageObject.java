package com.wikia.webdriver.pageobjectsfactory.pageobject.special.block;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SpecialBlockPageObject extends WikiBasePageObject {

	@FindBy(css = "input[name='wpTarget']")
	private WebElement userNameField;
	@FindBy(css = "select#mw-input-wpExpiry")
	private WebElement expiry;
	@FindBy(css = "#mw-input-wpExpiry-other")
	private WebElement expiryInput;
	@FindBy(css = "#mw-input-wpReason-other")
	private WebElement reasonInput;
	@FindBy(css = ".mw-htmlform-submit")
	private WebElement blockButton;
	@FindBy(css = ".mw-input [type='checkbox']")
	private List<WebElement> checkBoxes;

	public SpecialBlockPageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		waitForElementByElement(blockButton);
	}

	public void typeInUserName(String userName) {
		waitForElementByElement(userNameField);
		userNameField.sendKeys(userName);
	}

	public void selectExpiration(String period) {
		waitForElementByElement(expiry);
		Select exp = new Select(expiry);
		exp.selectByValue(period);
	}

	/**
	 * @param period you can type here '5 min', '10 year', ...
	 */
	public void typeExpiration(String period) {
		waitForElementByElement(expiryInput);
		expiryInput.sendKeys(period);
	}

	public void typeReason(String reason) {
		waitForElementByElement(reasonInput);
		reasonInput.sendKeys(reason);
	}

	public void clickBlockButton() {
		waitForElementByElement(blockButton);
		scrollAndClick(blockButton);
	}

	public void deselectAllSelections() {
		for (WebElement checkBox : checkBoxes) {
			if (checkBox.isSelected()) {
				checkBox.click();
			}
		}
		for (WebElement checkBox : checkBoxes) {
			Assertion.assertFalse(checkBox.isSelected());
		}
		PageObjectLogging.log("deselectAllSelections", "all selections deselected", true);
	}

	public void verifyBlockedUserSubmitPage(String userName, String password) {
		waitForElementByXPath("//p/a[contains(text(), '" + userName + "')]");
		waitForElementByXPath("//p[contains(text(), 'has been blocked')]");
		logOut(driver);
	}
}
