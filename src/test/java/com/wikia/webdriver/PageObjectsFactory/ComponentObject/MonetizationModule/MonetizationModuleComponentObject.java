package com.wikia.webdriver.PageObjectsFactory.ComponentObject.MonetizationModule;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Saipetch Kongkatong
 */
public class MonetizationModuleComponentObject extends WikiBasePageObject {

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
		Cookie newCookie= new Cookie("fromsearch", "1");
		driver.manage().addCookie(newCookie);
		PageObjectLogging.log("setCookieFromSearch", "Set cookie: 'fromsearch' to 1", true);
	}

	public void deleteCookieFromSearch() {
		driver.manage().deleteCookieNamed("fromsearch");
		PageObjectLogging.log("deleteCookieFromSearch", "Remove 'fromsearch' from cookie", true);
	}

	public void setCookieGeo(String countryCode) {
		String cookieName = "Geo";
		try {
			JSONObject geo = new JSONObject();
			geo.put("country", countryCode);
			String value = geo.toString();
			Cookie newCookie= new Cookie(cookieName, value);
			driver.manage().addCookie(newCookie);
			PageObjectLogging.log("setCookieFromSearch", "Set cookie: '"+cookieName+"' to '"+value+"'", true);
		} catch (JSONException ex) {
			PageObjectLogging.log("setCookieGeo", "Cannot set cookie ('"+cookieName+"')", true);
		}
	}

	public void resizeWindow(Integer width, Integer height) {
		try {
			driver.manage().window().setSize(new Dimension(width, height));
			PageObjectLogging.log("ResizeWindow", "Resize window (width="+width+", height="+height+")", true);
		} catch (WebDriverException ex) {
			PageObjectLogging.log("ResizeWindow", "Cannot resize window (width="+width+", height="+height+")", true);
		}
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

	public void verifyMonetizationModuleAdsenseShown() {
		Assertion.assertTrue(checkIfElementOnPage(adsenseContainer));
		Assertion.assertTrue(checkIfElementOnPage(adsenseIns));
		PageObjectLogging.log("verifyMonetizationModuleAdsenseShown", "Monetization module is shown (adsense)", true);
		waitForElementByElement(adsenseContainer);
		PageObjectLogging.log("verifyMonetizationModuleAdsenseShown", "Monetization module is visible (adsense)", true);
	}

	public void verifyMonetizationModuleAdsenseNotShown() {
		Assertion.assertTrue(!checkIfElementOnPage(adsenseContainer));
		Assertion.assertTrue(!checkIfElementOnPage(adsenseIns));
		PageObjectLogging.log("verifyMonetizationModuleAdsenseNotShown", "Monetization module is not shown (adsense)", true);
	}

	public void verifyMonetizationModuleAdsenseWidth(Integer expected) {
		Integer width = adsenseIns.getSize().width;
		Assertion.assertEquals(width, expected);
		PageObjectLogging.log("verifyMonetizationModuleAdsenseWidth", "Verify the width of the adsense ad (width="+width+")", true);
	}
}
