package com.wikia.webdriver.pageobjectsfactory.componentobject.monetizationmodule;

import java.util.List;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Saipetch Kongkatong
 */
public class MonetizationModuleComponentObject extends WikiBasePageObject {

	final String cookieFromsearchName = "fromsearch";
	final String cookieFromsearchValue = "1";
	final String attributeNameSlot = "data-mon-slot";
	final String attributeNameModuleType = "data-mon-type";
	final String adsenseHeaderValue = "advertisement";

	@FindBy(css=".monetization-module")
	private WebElement monetizationModuleContainer;
	@FindBy(css=".monetization-module[data-mon-slot='above_title']")
	private WebElement slotAboveTitle;
	@FindBy(css=".monetization-module[data-mon-slot='below_title']")
	private WebElement slotBelowTitle;
	@FindBy(css=".monetization-module[data-mon-slot='in_content']")
	private WebElement slotInContent;
	@FindBy(css=".monetization-module[data-mon-slot='below_category']")
	private WebElement slotBelowCategory;
	@FindBy(css=".monetization-module[data-mon-slot='above_footer']")
	private WebElement slotAboveFooter;
	@FindBy(css=".monetization-module[data-mon-type='adunit']")
	private WebElement adsenseContainer;
	@FindBy(css=".adsbygoogle")
	private WebElement adsenseIns;
	@FindBy(css=".adsbygoogle.ad-responsive-ic")
	private WebElement adsenseInsInContent;
	@FindBy(css=".adsbygoogle.ad-responsive")
	private WebElement adsenseInsOthers;
	@FindBy(css="#monetization-adunit-above_title")
	private WebElement slotAboveTitleAdsense;
	@FindBy(css="#monetization-adunit-below_title")
	private WebElement slotBelowTitleAdsense;
	@FindBy(css="#monetization-adunit-in_content")
	private WebElement slotInContentAdsense;
	@FindBy(css="#monetization-adunit-below_category")
	private WebElement slotBelowCategoryAdsense;
	@FindBy(css="#monetization-adunit-above_footer")
	private WebElement slotAboveFooterAdsense;
	@FindBy(css=".ad-header")
	private	WebElement adHeader;

	private By MonetizationModuleListBy = By.cssSelector(".monetization-module");
	private By MonetizationModuleAdsenseListBy = By.cssSelector(".monetization-module[data-mon-type='adunit']");

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
		waitForElementByElement(monetizationModuleContainer);
		Assertion.assertTrue(checkIfElementOnPage(monetizationModuleContainer));
		PageObjectLogging.log("verifyMonetizationModuleShown", "Monetization module is visible", true);
	}

	public void verifyMonetizationModuleNotShown() {
		Assertion.assertFalse(checkIfElementOnPage(monetizationModuleContainer));
		PageObjectLogging.log("verifyMonetizationModuleNotShown", "Monetization module is not shown", true);
	}

	public void verifyAdsenseUnitShown() {
		waitForElementByElement(adsenseContainer);
		Assertion.assertTrue(checkIfElementOnPage(adsenseContainer));
		Assertion.assertTrue(checkIfElementOnPage(adsenseIns));
		PageObjectLogging.log("verifyAdsenseUnitShown", "Adsense unit is visible", true);
	}

	public void verifyAdsenseUnitNotShown() {
		Assertion.assertFalse(checkIfElementOnPage(adsenseContainer));
		Assertion.assertFalse(checkIfElementOnPage(adsenseIns));
		PageObjectLogging.log("verifyAdsenseUnitNotShown", "Adsense unit is not shown", true);
	}

	public void verifyAdsenseUnitSlot() {
		List<WebElement> listWebElements = driver.findElements(MonetizationModuleAdsenseListBy);
		for (WebElement elem : listWebElements) {
			String slotName = elem.getAttribute(attributeNameSlot);
			switch (slotName) {
				case "above_title":
					verifyAdsenseUnitShownAboveTitle();
					break;
				case "below_title":
					verifyAdsenseUnitShownBelowTitle();
					break;
				case "in_content":
					verifyAdsenseUnitShownInContent();
					break;
				case "below_category":
					verifyAdsenseUnitShownBelowCategory();
					break;
				case "above_footer":
					verifyAdsenseUnitShownAboveFooter();
					break;
				default:
					PageObjectLogging.log("verifyAdsenseUnitSlot", "Invalid slot name (Name: " + slotName + ")", true);
					break;
			}
		}
	}

	public void verifyAdsenseUnitShownAboveTitle() {
		waitForElementByElement(slotAboveTitleAdsense);
		Assertion.assertTrue(checkIfElementOnPage(slotAboveTitleAdsense));
		Assertion.assertTrue(checkIfElementOnPage(adsenseInsOthers));
		PageObjectLogging.log("verifyAdsenseUnitShownAboveTitle", "Adsense unit is visible above the title", true);
	}

	public void verifyAdsenseUnitNotShownAboveTitle() {
		Assertion.assertFalse(checkIfElementOnPage(slotAboveTitleAdsense));
		PageObjectLogging.log("verifyAdsenseUnitNotShownAboveTitle", "Adsense unit is not shown above the title", true);
	}

	public void verifyAdsenseUnitShownBelowTitle() {
		waitForElementByElement(slotBelowTitleAdsense);
		Assertion.assertTrue(checkIfElementOnPage(slotBelowTitleAdsense));
		Assertion.assertTrue(checkIfElementOnPage(adsenseInsOthers));
		PageObjectLogging.log("verifyAdsenseUnitShownBelowTitle", "Adsense unit is visible below the title", true);
	}

	public void verifyAdsenseUnitNotShownBelowTitle() {
		Assertion.assertFalse(checkIfElementOnPage(slotBelowTitleAdsense));
		PageObjectLogging.log("verifyAdsenseUnitNotShownBelowTitle", "Adsense unit is not shown below the title", true);
	}

	public void verifyAdsenseUnitShownInContent() {
		waitForElementByElement(slotInContentAdsense);
		Assertion.assertTrue(checkIfElementOnPage(slotInContentAdsense));
		Assertion.assertTrue(checkIfElementOnPage(adsenseInsInContent));
		PageObjectLogging.log("verifyAdsenseUnitShownInContent", "Adsense unit is visible in content", true);
	}

	public void verifyAdsenseUnitNotShownInContent() {
		Assertion.assertFalse(checkIfElementOnPage(slotInContentAdsense));
		PageObjectLogging.log("verifyAdsenseUnitNotShownInContent", "Adsense unit is not shown in content", true);
	}

	public void verifyAdsenseUnitShownBelowCategory() {
		waitForElementByElement(slotBelowCategoryAdsense);
		Assertion.assertTrue(checkIfElementOnPage(slotBelowCategoryAdsense));
		Assertion.assertTrue(checkIfElementOnPage(adsenseInsOthers));
		PageObjectLogging.log("verifyAdsenseUnitShownBelowCategory", "Adsense unit is visible below category", true);
	}

	public void verifyAdsenseUnitNotShownBelowCategory() {
		Assertion.assertFalse(checkIfElementOnPage(slotBelowCategoryAdsense));
		PageObjectLogging.log("verifyAdsenseUnitNotShownBelowCategory", "Adsense unit is not shown below category", true);
	}

	public void verifyAdsenseUnitShownAboveFooter() {
		waitForElementByElement(slotAboveFooterAdsense);
		Assertion.assertTrue(checkIfElementOnPage(slotAboveFooterAdsense));
		Assertion.assertTrue(checkIfElementOnPage(adsenseInsOthers));
		PageObjectLogging.log("verifyAdsenseUnitShownAboveFooter", "Adsense unit is visible above footer", true);
	}

	public void verifyAdsenseUnitNotShownAboveFooter() {
		Assertion.assertFalse(checkIfElementOnPage(slotAboveFooterAdsense));
		PageObjectLogging.log("verifyAdsenseUnitNotShownAboveFooter", "Adsense unit is not shown above footer", true);
	}

	public void verifyAdsenseUnitWidth(int expectedInContent, int expectedOthers) {
		int width;
		List<WebElement> listWebElements = driver.findElements(MonetizationModuleAdsenseListBy);
		for (WebElement elem : listWebElements) {
			String slotName = elem.getAttribute(attributeNameSlot);
			width = elem.findElement(By.cssSelector(".adsbygoogle")).getSize().width;
			if (slotName.equals("in_content")) {
				Assertion.assertEquals(width, expectedInContent);
			} else {
				Assertion.assertEquals(width, expectedOthers);
			}
			PageObjectLogging.log("verifyAdsenseUnitWidth", "Verify the width of the adsense unit for " + slotName + " (width=" + width + ")", true);
		}
	}

	public void verifyAdsenseHeaderShown() {
		waitForElementByElement(adHeader);
		Assertion.assertTrue(checkIfElementOnPage(adHeader));
		Assertion.assertEquals(adsenseHeaderValue.toUpperCase(), adHeader.getText());
		PageObjectLogging.log("verifyAdsenseHeaderShown", "The header of adsense unit is visible", true);
	}


}
