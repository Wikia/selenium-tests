package com.wikia.webdriver.PageObjectsFactory.PageObject.Facebook;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

/**
* @author Michal 'justnpT' Nowierski
*/
public class FacebookUserPageObject extends WikiBasePageObject {

	@FindBy(css = "a[href$='?ref=logo']")
	private WebElement pageLogo;

	public FacebookUserPageObject(WebDriver driver) {
		super(driver);
	}

	public void verifyPageLogo() {
		waitForElementByElement(pageLogo);
		PageObjectLogging.log("verifyPageLogo", "Page logo is present", true);
	}

	public FacebookSettingsPageObject fbOpenSettings() {
		getUrl(URLsContent.facebookSettingsPage);
		return new FacebookSettingsPageObject(driver);
	}
}
