package com.wikia.webdriver.pageobjectsfactory.componentobject.monetizationmodule;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @author Saipetch Kongkatong
 */
public class MonetizationModuleComponentObject extends WikiBasePageObject {

	private static final String COOKIE_FROMSEARCH_NAME = "fromsearch";
	private static final String COOKIE_FROMSEARCH_VALUE = "1";
	private static final String ATTRIBUTE_NAME_SLOT = "data-mon-slot";

	@FindBy(css = ".monetization-module")
	private WebElement monetizationModuleContainer;
	@FindBy(css = ".monetization-module[data-mon-slot='above_title']")
	private WebElement slotAboveTitle;
	@FindBy(css = ".monetization-module[data-mon-slot='below_title']")
	private WebElement slotBelowTitle;
	@FindBy(css = ".monetization-module[data-mon-slot='in_content']")
	private WebElement slotInContent;
	@FindBy(css = ".monetization-module[data-mon-slot='below_category']")
	private WebElement slotBelowCategory;
	@FindBy(css = ".monetization-module[data-mon-slot='above_footer']")
	private WebElement slotAboveFooter;
	@FindBy(css = ".monetization-module,.adunit")
	private WebElement adsenseContainer;
	@FindBy(css = ".adsbygoogle")
	private WebElement adsenseIns;

	private By MonetizationModuleListBy = By.cssSelector(".monetization-module");

	public MonetizationModuleComponentObject(WebDriver driver) {
		super(driver);
	}

	public void setCookieFromSearch() {
		setCookie(COOKIE_FROMSEARCH_NAME, COOKIE_FROMSEARCH_VALUE);
	}

	public void deleteCookieFromSearch() {
		deleteCookie(COOKIE_FROMSEARCH_NAME);
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

	public void verifyMonetizationModuleSlot() {
		List<WebElement> listWebElements = driver.findElements(MonetizationModuleListBy);
		for (WebElement elem : listWebElements) {
			String slotName = elem.getAttribute(ATTRIBUTE_NAME_SLOT);
			switch (slotName) {
				case "above_title":
					verifyMonetizationModuleShownAboveTitle();
					break;
				case "below_title":
					verifyMonetizationModuleShownBelowTitle();
					break;
				case "in_content":
					verifyMonetizationModuleShownInContent();
					break;
				case "below_category":
					verifyMonetizationModuleShownBelowCategory();
					break;
				case "above_footer":
					verifyMonetizationModuleShownAboveFooter();
					break;
				default:
					PageObjectLogging.log("verifyMonetizationModuleSlot", "Invalid slot name (Name: " + slotName + ")", true);
					break;
			}
		}
	}

	public void verifyMonetizationModuleShownAboveTitle() {
		Assertion.assertTrue(checkIfElementOnPage(slotAboveTitle));
		waitForElementByElement(slotAboveTitle);
		PageObjectLogging.log("verifyMonetizationModuleSlotAboveTitleShown", "Monetization module is visible above the title", true);
	}

	public void verifyMonetizationModuleNotShownAboveTitle() {
		Assertion.assertFalse(checkIfElementOnPage(slotAboveTitle));
		PageObjectLogging.log("verifyMonetizationModuleSlotAboveTitleNotShown", "Monetization module is not shown above the title", true);
	}

	public void verifyMonetizationModuleShownBelowTitle() {
		Assertion.assertTrue(checkIfElementOnPage(slotBelowTitle));
		waitForElementByElement(slotBelowTitle);
		PageObjectLogging.log("verifyMonetizationModuleSlotAboveTitleShown", "Monetization module is visible below the title", true);
	}

	public void verifyMonetizationModuleNotShownBelowTitle() {
		Assertion.assertFalse(checkIfElementOnPage(slotBelowTitle));
		PageObjectLogging.log("verifyMonetizationModuleSlotAboveTitleNotShown", "Monetization module is not shown below the title", true);
	}

	public void verifyMonetizationModuleShownInContent() {
		Assertion.assertTrue(checkIfElementOnPage(slotInContent));
		waitForElementByElement(slotInContent);
		PageObjectLogging.log("verifyMonetizationModuleSlotAboveTitleShown", "Monetization module is visible in content", true);
	}

	public void verifyMonetizationModuleNotShownInContent() {
		Assertion.assertFalse(checkIfElementOnPage(slotInContent));
		PageObjectLogging.log("verifyMonetizationModuleSlotAboveTitleNotShown", "Monetization module is not shown in content", true);
	}

	public void verifyMonetizationModuleShownBelowCategory() {
		Assertion.assertTrue(checkIfElementOnPage(slotBelowCategory));
		waitForElementByElement(slotBelowCategory);
		PageObjectLogging.log("verifyMonetizationModuleSlotAboveTitleShown", "Monetization module is visible below category", true);
	}

	public void verifyMonetizationModuleNotShownBelowCategory() {
		Assertion.assertFalse(checkIfElementOnPage(slotBelowCategory));
		PageObjectLogging.log("verifyMonetizationModuleSlotAboveTitleNotShown", "Monetization module is not shown below category", true);
	}

	public void verifyMonetizationModuleShownAboveFooter() {
		Assertion.assertTrue(checkIfElementOnPage(slotAboveFooter));
		waitForElementByElement(slotAboveFooter);
		PageObjectLogging.log("verifyMonetizationModuleSlotAboveTitleShown", "Monetization module is visible above footer", true);
	}

	public void verifyMonetizationModuleNotShownAboveFooter() {
		Assertion.assertFalse(checkIfElementOnPage(slotAboveFooter));
		PageObjectLogging.log("verifyMonetizationModuleSlotAboveTitleNotShown", "Monetization module is not shown above footer", true);
	}

	public void verifyMonetizationModuleAdsenseWidth(int expected) {
		int width = adsenseIns.getSize().width;
		Assertion.assertEquals(width, expected);
		PageObjectLogging.log("verifyMonetizationModuleAdsenseWidth", "Verify the width of the adsense ad (width=" + width + ")", true);
	}
}
