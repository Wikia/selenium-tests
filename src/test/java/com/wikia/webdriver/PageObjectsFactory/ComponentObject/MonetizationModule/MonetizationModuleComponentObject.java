package com.wikia.webdriver.PageObjectsFactory.ComponentObject.MonetizationModule;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Saipetch Kongkatong
 */
public class MonetizationModuleComponentObject extends WikiBasePageObject {

	@FindBy(css=".monetization-module")
	private WebElement monetizationModuleContainer;

	public MonetizationModuleComponentObject (WebDriver driver) {
		super(driver);
	}

	public void setCookieFromSearch() {
		Cookie newCookie= new Cookie("fromsearch", "1");
		driver.manage().addCookie(newCookie);
		PageObjectLogging.log("setCookieFromSearch", "Set 'fromsearch' variable to 1 to cookie", true);
	}

	public void deleteCookieFromSearch() {
		driver.manage().deleteCookieNamed("fromsearch");
		PageObjectLogging.log("deleteCookieFromSearch", "Remove 'fromsearch' variable from cookie", true);
	}

	public void verifyMonetizationModuleShown() {
		Assertion.assertTrue(checkIfElementOnPage(monetizationModuleContainer));
		PageObjectLogging.log("verifyMonetizationModuleShown", "Monetization module is shown", true);
		waitForElementByElement(monetizationModuleContainer);
		PageObjectLogging.log("verifyMonetizationModuleShown", "Monetization module is visible", true);
	}

	public void verifyMonetizationModuleNotShown() {
		Assertion.assertTrue(!checkIfElementOnPage(monetizationModuleContainer));
		PageObjectLogging.log("verifyMonetizationModuleNotShown", "Monetization module is not shown", true);
	}

}
