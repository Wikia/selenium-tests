package com.wikia.webdriver.PageObjectsFactory.PageObject.Special.EditAccount;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class EditAccount extends BasePageObject {

	@FindBy(css="[name=wpUserName]")
	private WebElement userNameField;
	@FindBy(css="[value='Close account']")
	private WebElement closeAccountButton;
	@FindBy(css="#wpReason")
	private WebElement closeResonField;
	@FindBy(css="#wpActionSetPass")
	private WebElement newPasswordRadio;
	@FindBy(css="[name=wpNewPass]")
	private WebElement newPasswordField;
	@FindBy(css="[value='Clear disable flag']")
	private WebElement clearDisableFlagButton;
	@FindBy(css="fieldset > span")
	private WebElement statusMessage;

	private final String userAccountReopenMessage = "Successfully removed disabled bit for account";
	private final String userAccountClosedMessage = "Successfully disabled account";

	public EditAccount(WebDriver driver, String communityWikiURL, String userName) {
		super(driver);
		driver.get(
				communityWikiURL +
				URLsContent.specialEditAccount
		);
		userNameField.sendKeys(userName);
		userNameField.submit();
		PageObjectLogging.log(
				"editAccount",
				URLsContent.specialEditAccount +" page opened",
				true
		);
	}

	public void closeAccount(String reason) {
		closeAccountButton.click();
		closeResonField.sendKeys(reason);
		closeResonField.submit();
		PageObjectLogging.log(
				"closeAccount",
				"account closed",
				true
		);
	}

	public void verifyAccountClosedMessage() {
		waitForTextToBePresentInElementByElement(
				statusMessage,
				userAccountClosedMessage
		);
		PageObjectLogging.log(
				"verifyAccountClosedMessage",
				"verified account closed",
				true
		);
	}

	public void reopenAccount(String newPassword) {
		newPasswordRadio.click();
		newPasswordField.sendKeys(newPassword);
		newPasswordField.submit();
		clearDisableFlagButton.click();
		PageObjectLogging.log(
				"reopenAccount",
				"account reopened",
				true
		);
	}

	public void verifyAccountReopenedMessage() {
		waitForTextToBePresentInElementByElement(
				statusMessage,
				userAccountReopenMessage
		);
		PageObjectLogging.log(
				"verifyAccountReopenedMessage",
				"verified account reopened",
				true
		);
	}
}
