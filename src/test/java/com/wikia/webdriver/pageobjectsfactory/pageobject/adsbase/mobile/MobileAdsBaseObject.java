package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.mobile;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.imageutilities.ImageComparison;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.AdsComparison;

/**
 * Bogna 'bognix' Knychala
 */
public class MobileAdsBaseObject extends AdsBaseObject {

	private final String smartBannerSelector = ".smartbanner.android";
	private final String fliteMaskSelector = ".flite-mask";
	private AdsComparison adsComparison;
	private ImageComparison imageComparison;

	public MobileAdsBaseObject(WebDriver driver, String page) {
		super(driver, page);
		adsComparison = new AdsComparison();
		imageComparison = new ImageComparison();
		PageObjectLogging.log("", "Page screenshot", true, driver);
	}

	@Override
	protected void setWindowSize() {
		try {
			driver.manage().window().setSize(new Dimension(360, 640));
		} catch (WebDriverException ex) {
			PageObjectLogging.log(
				"ResizeWindowForMobile",
				"Resize window method not available - possibly running on real device",
				true
			);
		}
	}

	public void verifyMobileTopLeaderboard() {
		extractGptInfo(presentLeaderboardSelector);
		removeSmartBanner();
		if (checkIfElementOnPage(fliteMaskSelector)) {
			PageObjectLogging.log(
				"FliteAd", "Page contains the flite ad", true, driver
			);
			return;
		}
		if (!checkIfSlotExpanded(presentLeaderboard)) {
			throw new NoSuchElementException(
				String.format("Slot is not expanded - ad is not there; CSS selector: %s", presentLeaderboardSelector)
			);
		}
		if (areAdsEmpty(presentLeaderboardSelector, presentLeaderboard)) {
			throw new NoSuchElementException(
				"Screenshots of element on/off look the same."
					+ "Most probable ad is not present; CSS "
					+ presentLeaderboardSelector
			);
		}
	}

	public void verifyNoAdInSlot(String slotName) {
		scrollToSlotOnMobile(slotName);
		WebElement slot = driver.findElement(By.id(slotName));
		waitForSlotCollapsed(slot);
		PageObjectLogging.log("AdInSlot", "Ad is not found in slot as expected.", true);
	}

	public void verifyNoSlotPresent(String slotName) {
		if (checkIfElementOnPage("#" + slotName)) {
			throw new NoSuchElementException("Slot is added to the page");
		}
		PageObjectLogging.log("AdInSlot", "No slot found as expected", true);
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
			Assertion.assertStringContains(expectedImg, foundImg);
		} else {
			throw new NoSuchElementException("Slot is collapsed - should be expanded");
		}
		PageObjectLogging.log("AdInSlot", "Ad found in slot", true, driver);
	}

	private boolean areAdsEmpty(String slotSelector, WebElement slot) {
		PageObjectLogging.log(
			"CompareScreenshot", "Page before hidding ads", true, driver
		);
		File preSwitch = adsComparison.getMobileSlotScreenshot(slot, driver);
		adsComparison.hideSlot(slotSelector, driver);
		waitForElementNotVisibleByElement(slot);
		File postSwitch = adsComparison.getMobileSlotScreenshot(slot, driver);
		boolean imagesTheSame = imageComparison.areFilesTheSame(preSwitch, postSwitch);
		preSwitch.delete();
		postSwitch.delete();
		if (imagesTheSame) {
			PageObjectLogging.log(
				"CompareScreenshot", "Screenshots look the same", false
			);
		} else {
			PageObjectLogging.log(
				"CompareScreenshot", "Screenshots look different", true
			);
		}
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
