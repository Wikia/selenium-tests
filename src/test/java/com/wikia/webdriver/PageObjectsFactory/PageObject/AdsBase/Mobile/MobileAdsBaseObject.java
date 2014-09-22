package com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.Mobile;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Core.ImageUtilities.ImageComparison;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.AdsBaseObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.Helpers.AdsComparison;

/**
 * Bogna 'bognix' Knychala
 */
public class MobileAdsBaseObject extends AdsBaseObject {

	private final String smartBannerSelector = ".smartbanner.android";
	private AdsComparison adsComparison;
	private ImageComparison imageComparison;
	private By gptIframeTopLeaderBoardSelector = By.cssSelector("#WikiaMainContent a[data-id='edit']");
	private By celtraAdSelector = By.cssSelector(".celtra-ad-v3 iframe");

	public MobileAdsBaseObject(WebDriver driver, String page) {
		super(driver, page);
		adsComparison = new AdsComparison();
		imageComparison = new ImageComparison();
		PageObjectLogging.log("", "Page screenshot", true, driver);
	}

	@Override
	protected void setWindowSize() {
		try {
			driver.manage().window().setSize(new Dimension(640, 1136));
		} catch (WebDriverException ex) {
			PageObjectLogging.log(
				"ResizeWindowForMobile",
				"Resize window method not available - possibly running on real device",
				true
			);
		}
	}

	public void verifyMobileTopLeaderboard() {
		removeSmartBanner();
		if (!checkIfSlotExpanded(presentLeaderboard)) {
			throw new NoSuchElementException(
				String.format("Slot is not expanded - ad is not there; CSS selector: %s", presentLeaderboardSelector)
			);
		}
		PageObjectLogging.log(
			"CompareScreenshot", "Page before hidding ads", true, driver
		);
		if (areAdsEmpty(presentLeaderboardSelector, presentLeaderboard)) {
			PageObjectLogging.log(
				"CompareScreenshot", "Screenshots look the same", false
			);
			throw new NoSuchElementException(
				"Screenshots of element on/off look the same."
				+ "Most probable ad is not present; CSS "
				+ presentLeaderboardSelector
			);
		} else {
			PageObjectLogging.log(
				"CompareScreenshot", "Screenshots look different", true
			);
		}
	}
	public void verifyCeltraMobileTopLeaderboard() {
		removeSmartBanner();
		if (!checkIfSlotExpanded(presentLeaderboard)) {
			throw new NoSuchElementException(
				String.format("Slot is not expanded - ad is not there; CSS selector: %s", presentLeaderboardSelector)
			);
		}
		PageObjectLogging.log(
			"CompareScreenshot", "Page before hidding ads", true, driver
		);

		waitForElementPresenceByBy(celtraAdSelector);
		waitForIframeLoaded(presentLeaderboard.findElement(celtraAdSelector));

		if (areAdsEmpty(presentLeaderboardSelector, presentLeaderboard)) {
			PageObjectLogging.log(
				"CompareScreenshot", "Screenshots look the same", false
			);
			throw new NoSuchElementException(
				"Screenshots of element on/off look the same."
				+ "Most probable ad is not present; CSS "
				+ presentLeaderboardSelector
			);
		} else {
			PageObjectLogging.log(
				"CompareScreenshot", "Screenshots look different", true
			);
		}
	}

	public void verifyNoAdInSlot(String slotName) {
		scrollToSlotOnMobile(slotName);
		WebElement slot = driver.findElement(By.id(slotName));
		if (!checkIfSlotExpanded(slot)) {
			if (!slot.isDisplayed()) {
				PageObjectLogging.log("AdInSlot", "Ad not found in slot as expected", true);
				return;
			} else {
				throw new NoSuchElementException("Slot is displayed, should be hidden");
			}
		}
		throw new NoSuchElementException("Slot expanded, should be collapsed");
	}

	public void verifyNoSlotPresent(String slotName) {
		if (!checkIfElementOnPage("#" + slotName)) {
			PageObjectLogging.log("AdInSlot", "No slot found as expected", true);
			return;
		} else {
			throw new NoSuchElementException("Slot is added to the page");
		}
	}

	public void verifySlotExpanded(String slotName) {
		scrollToSlotOnMobile(slotName);
		WebElement slot = driver.findElement(By.id(slotName));
		if (checkIfSlotExpanded(slot)) {
			PageObjectLogging.log("AdInSlot", "Slot expanded as expecting", true);
		} else {
			throw new NoSuchElementException("Slot is collapsed - should be expanded");
		}
	}

	public void verifyImgAdLoadedInSlot(String slotName, String expectedImg) {
		scrollToSlotOnMobile(slotName);
		WebElement slot = driver.findElement(By.id(slotName));
		if (checkIfSlotExpanded(slot)) {
			String foundImg = getSlotImageAd(slot);
			Assertion.assertEquals(foundImg, expectedImg);
		} else {
			throw new NoSuchElementException("Slot is collapsed - should be expanded");
		}
		PageObjectLogging.log("AdInSlot", "Ad found in slot", true);
	}

	private boolean areAdsEmpty(String slotSelector, WebElement slot) {
		File preSwitch = adsComparison.getMobileSlotScreenshot(slot, driver);
		adsComparison.hideSlot(slotSelector, driver);
		waitForElementNotVisibleByElement(slot);
		File postSwitch = adsComparison.getMobileSlotScreenshot(slot, driver);
		boolean imagesTheSame = imageComparison.areFilesTheSame(preSwitch, postSwitch);
		preSwitch.delete();
		postSwitch.delete();
		return imagesTheSame;
	}

	private void removeSmartBanner() {
		if (checkIfElementOnPage(smartBannerSelector)) {
			WebElement smartBanner = driver.findElement(By.cssSelector(smartBannerSelector));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("$(arguments[0]).css('display', 'none')", smartBanner);
			waitForElementNotVisibleByElement(smartBanner);
		}
	}

	private void scrollToSlotOnMobile(String slotName) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(
			"var elementY = document.getElementById(arguments[0]).offsetTop;" +
			"window.scrollTo(0, elementY);",
			slotName
		);
	}

}
