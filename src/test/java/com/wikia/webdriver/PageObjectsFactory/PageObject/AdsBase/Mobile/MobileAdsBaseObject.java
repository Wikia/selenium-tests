package com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.Mobile;

import com.wikia.webdriver.Common.Core.ImageUtilities.ImageComparison;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.AdsBaseObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.Helpers.AdsComparison;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.io.File;

/**
 * Bogna 'bognix' Knychala
 */
public class MobileAdsBaseObject extends AdsBaseObject {

	private final String smartBannerSelector = ".smartbanner.android";
	private AdsComparison adsComparison;
	private ImageComparison imageComparison;

	public MobileAdsBaseObject(WebDriver driver, String page) {
		super(driver, page);
		adsComparison = new AdsComparison();
		imageComparison = new ImageComparison();
	}

	@Override
	protected void setWindowSize() {
		try {
			driver.manage().window().setSize(new Dimension(768, 1280));
		} catch (WebDriverException ex) {
			PageObjectLogging.log(
				"ResizeWindowForMobile",
				"Resize window method not available - possibly running on real device",
				true
			);
		}
	}

	public void checkMobileTopLeaderboard() {
		removeSmartBanner();
		if (!checkIfSlotExpanded(presentLeaderboard)) {
			throw new NoSuchElementException(
				String.format("Slot is not expanded - ad is not there; CSS selector: %s", presentLeaderboardSelector)
			);
		}
		PageObjectLogging.log(
			"CompareScreenshot", "Page before hidding ads", true, driver
		);
		if (checkIfAdVisibleInSlot(presentLeaderboardSelector, presentLeaderboard)) {
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

	private boolean checkIfAdVisibleInSlot(String slotSelector, WebElement slot) {
		File preSwitch = adsComparison.getMobileSlotScreenshot(slot, driver);
		adsComparison.hideSlot(slotSelector, driver);
		waitForElementNotVisibleByElement(slot);
		File postSwitch = adsComparison.getMobileSlotScreenshot(slot, driver);
		boolean result = imageComparison.compareImagesBasedOnBytes(preSwitch, postSwitch);
		preSwitch.delete();
		postSwitch.delete();
		return result;
	}

	private void removeSmartBanner() {
		if (checkIfElementOnPage(smartBannerSelector)) {
			WebElement smartBanner = driver.findElement(By.cssSelector(smartBannerSelector));
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("$(arguments[0]).css('display', 'none')", smartBanner);
			waitForElementNotVisibleByElement(smartBanner);
		}
	}
}
