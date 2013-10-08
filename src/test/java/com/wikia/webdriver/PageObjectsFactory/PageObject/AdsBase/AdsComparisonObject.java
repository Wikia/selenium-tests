package com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase;

import com.wikia.webdriver.Common.ContentPatterns.AdsContent;
import com.wikia.webdriver.Common.Core.ImageUtilities.ImageComparison;
import com.wikia.webdriver.Common.Core.ImageUtilities.Shooter;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class AdsComparisonObject extends AdsBaseObject {

	private ImageComparison imageComparison;

	public AdsComparisonObject(WebDriver driver, String page) {
		super(driver, page);
		imageComparison = new ImageComparison();
	}

	public void checkTopLeaderboard() {
		setPresentTopLeaderboard();
		WebElement leaderboard = getPresentTopLeaderBoard();
		boolean result = compareSlotOnOff(
			leaderboard, AdsContent.getSlotSelector(presentLBName)
		);
		if(result) {
			PageObjectLogging.log(
				"CompareScreenshot", "Screenshots look the same", false
			);
			throw new NoSuchElementException(
				"Screenshots of element on/off look the same."
				+ "Most probable ad is not present; CSS "
				+ AdsContent.getSlotSelector(presentLBName)
			);
		} else {
			PageObjectLogging.log(
				"CompareScreenshot", "Screenshots are different", true
			);
		}
	}

	public void checkMedrec() {
		setPresentMedrec();
		WebElement medrec = getPresentMedrec();
		boolean result = compareSlotOnOff(
			medrec, AdsContent.getSlotSelector(presentMDName)
		);
		if(result) {
			PageObjectLogging.log(
				"CompareScreenshot", "Screenshots look the same", false
			);
			throw new NoSuchElementException(
				"Screenshots of element on/off look the same."
				+ "Most probable ad is not present; CSS "
				+ AdsContent.getSlotSelector(presentMDName)
			);
		} else {
			PageObjectLogging.log(
				"CompareScreenshot", "Screenshots are different", true
			);
		}
	}

	public void checkToolbarAdBySize(String adBaseLocation, Dimension size) throws IOException {
		Shooter shooter = new Shooter();
		String encodedExpectedAd = IOUtils.toString(new FileInputStream(new File(adBaseLocation)), "UTF-8");
		Base64 coder = new Base64();
		PageObjectLogging.log(
			"ScreenshotElement",
			"Screenshot of the element taken, Selector: " + AdsContent.wikiaBarSelector,
			true, driver
		);
		File toolbarScreen =  shooter.captureWebElementWithSize(toolbar, driver, size);
		String encodedToolbarScreen = IOUtils.toString(
			coder.encode(FileUtils.readFileToByteArray(toolbarScreen)), "UTF-8"
		);
		toolbarScreen.delete();
		if (
			imageComparison.comapareBaseEncodedImagesBasedOnBytes(encodedExpectedAd, encodedToolbarScreen)
		) {
			PageObjectLogging.log(
				"ExpectedAdFound", "Expected ad found in toolbar", true
			);
		} else {
			PageObjectLogging.log(
				"ExpectedAdNotFound", "Expected ad not found in toolbar", false
			);
			throw new NoSuchElementException(
				"Expected ad not found on page"
				+ "CSS: "
				+ AdsContent.wikiaBarSelector
			);
		}
	}

	private boolean compareSlotOnOff(WebElement element, String elementSelector) {
		Shooter shooter = new Shooter();
		if (element.getSize().height <= 1 || element.getSize().width <= 1) {
			throw new NoSuchElementException(
				"Element has size 1px x 1px or smaller. Most probable is not displayed"
			);
		}
		PageObjectLogging.log(
			"ScreenshotElement",
			"Screenshot of the element taken, Selector: " + elementSelector,
			true, driver
		);
		File preSwitch = shooter.captureWebElement(element, driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Find ad-containing element and set visibility = hidden on it
		// Example selector:
		// AD_SLOT iframe:visible:first, AD_SLOT img:visible:first, AD_SLOT object:visible:first
		js.executeScript(
			"var iframe = arguments[0] + ' iframe:visible:first, ';"
			+ "var object = arguments[0] + ' object:visible:first, ';"
			+ "var img = arguments[0] + ' img:visible:first';"
			+ "var element = $(iframe + object + img)[0];"
			+ "if (element) element.style['visibility'] = 'hidden';",
			elementSelector
		);
		PageObjectLogging.log(
			"HideElement", "Element is hidden; CSS " + elementSelector, true
		);
		File postSwitch = shooter.captureWebElement(element, driver);
		PageObjectLogging.log(
			"ScreenshotElement",
			"Screenshot of element off taken; CSS " + elementSelector,
			true
		);
		boolean result = imageComparison.compareImagesBasedOnBytes(preSwitch, postSwitch);
		preSwitch.delete();
		postSwitch.delete();
		return result;
	}
}
