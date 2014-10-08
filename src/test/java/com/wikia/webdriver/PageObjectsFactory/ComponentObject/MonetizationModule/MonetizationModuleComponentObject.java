package com.wikia.webdriver.PageObjectsFactory.ComponentObject.MonetizationModule;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Saipetch Kongkatong
 */
public class MonetizationModuleComponentObject extends WikiBasePageObject {

	final String cookieFromsearchName = "fromsearch";
	final String cookieFromsearchValue = "1";

	@FindBy(css=".monetization-module")
	private WebElement monetizationModuleContainer;
	@FindBy(css=".monetization-module,.adunit")
	private WebElement adsenseContainer;
	@FindBy(css=".adsbygoogle,.ad-responsive")
	private WebElement adsenseIns;

	public MonetizationModuleComponentObject (WebDriver driver) {
		super(driver);
	}

	public void setCookieFromSearch() {
		setCookie(cookieFromsearchName, cookieFromsearchValue);
	}

	public void deleteCookieFromSearch() {
		deleteCookie(cookieFromsearchName);
	}

	public void verifyMonetizationModuleShown() {
		Assertion.assertTrue(checkIfElementOnPage(monetizationModuleContainer));
		waitForElementByElement(monetizationModuleContainer);
		PageObjectLogging.log("verifyMonetizationModuleShown", "Monetization module is visible", true);
	}

	public void verifyMonetizationModuleNotShown() {
		Assertion.assertFalse(checkIfElementOnPage(monetizationModuleContainer));
		PageObjectLogging.log("verifyMonetizationModuleNotShown", "Monetization module is not shown", true);
	}

	public void verifyMonetizationModuleAdsenseShown() {
		Assertion.assertTrue(checkIfElementOnPage(adsenseContainer));
		Assertion.assertTrue(checkIfElementOnPage(adsenseIns));
		waitForElementByElement(adsenseContainer);
		PageObjectLogging.log("verifyMonetizationModuleAdsenseShown", "Monetization module is visible (adsense)", true);
	}

	public void verifyMonetizationModuleAdsenseNotShown() {
		Assertion.assertFalse(checkIfElementOnPage(adsenseContainer));
		Assertion.assertFalse(checkIfElementOnPage(adsenseIns));
		PageObjectLogging.log("verifyMonetizationModuleAdsenseNotShown", "Monetization module is not shown (adsense)", true);
	}

	public void verifyMonetizationModuleAdsenseWidth(int expected) {
		int width = adsenseIns.getSize().width;
		Assertion.assertEquals(width, expected);
		PageObjectLogging.log("verifyMonetizationModuleAdsenseWidth", "Verify the width of the adsense ad (width=" + width + ")", true);
	}
}
