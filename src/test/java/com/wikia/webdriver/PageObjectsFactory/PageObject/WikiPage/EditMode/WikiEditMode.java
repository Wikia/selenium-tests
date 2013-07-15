package com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;

/**
 *
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class WikiEditMode extends WikiBasePageObject{

	public WikiEditMode(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		}

	@FindBy(css="#wpSave")
	private WebElement publishButtonGeneral;

	/**
	 * Click  on Publish button
	 *
	 * @author Michal Nowierski
	 */
	public WikiArticlePageObject clickOnPublishButton() {
		waitForElementByElement(publishButtonGeneral);
		waitForElementClickableByElement(publishButtonGeneral);
		publishButtonGeneral.click();
		waitForElementPresenceByBy(editButtonBy);

		PageObjectLogging.log("ClickOnPublishButton", "Click on 'Publish' button", true);
		return new WikiArticlePageObject(driver);
	}

	/**
	 * Click  on Publish button
	 *
	 * @author Michal Nowierski
	 */
	public WikiArticlePageObject clickOnPublishButtonAndCheckJSalertNotThere(String alertMessage) {
		waitForElementByElement(publishButtonGeneral);
		waitForElementClickableByElement(publishButtonGeneral);
		scrollAndClick(publishButtonGeneral);
		checkJSalertIsThere(alertMessage, false);
		waitForElementByElement(editButton);
		PageObjectLogging.log("clickOnPublishButtonAndCheckJSalertNotThere", "Click on 'Publish' button and check there is no JS alert", true, driver);

		return new WikiArticlePageObject(driver);
	}
}
