package com.wikia.webdriver.PageObjectsFactory.PageObject.SignUp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Core.MailFunctions;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiPageObjectStep1;

public class ConfirmationPageObject extends BasePageObject{

	@FindBy(css="div.UserConfirm input[name='username']")
	private WebElement userNameField;
	@FindBy(css="div.UserConfirm input[name='password']")
	private WebElement passwordField;
	@FindBy(css="div.UserConfirm input[type='submit']")
	private WebElement confirmationButton;

	/**
	 * @author Karol Kujawiak
	 * @param driver
	 */
	public ConfirmationPageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	/**
	 * @author Karol Kujawiak
	 * @param userName
	 */
	public void typeInUserName(String userName)
	{
		userNameField.sendKeys(userName);
		PageObjectLogging.log("typeInUserName ", "user name field populated", true, driver);
	}

	/**
	 * @author Karol Kujawiak
	 * @param password
	 */
	public void typeInPassword(String password)
	{
		passwordField.sendKeys(password);
		PageObjectLogging.log("typeInUserPassword ", "password field populated", true, driver);
	}

	/**
	 * @author Karol Kujawiak
	 */
	public UserProfilePageObject clickSubmitButton(String email, String password)
	{
		MailFunctions.deleteAllMails(email, password);
		scrollAndClick(confirmationButton);
		PageObjectLogging.log("submit button clicked ", "submit button clicked", true, driver);
		return new UserProfilePageObject(driver);
	}

	public CreateNewWikiPageObjectStep1 CNWSubmitButton(String email, String password)
	{
		MailFunctions.deleteAllMails(email, password);
		scrollAndClick(confirmationButton);
		PageObjectLogging.log("submit button clicked ", "submit button clicked", true, driver);
		return new CreateNewWikiPageObjectStep1(driver);
	}
}
