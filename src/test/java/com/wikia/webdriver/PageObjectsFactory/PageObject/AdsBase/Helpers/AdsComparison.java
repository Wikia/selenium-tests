package com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.Helpers;

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
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class AdsComparison {

	@FindBy(css="#WikiaPage")
	private WebElement wikiaArticle;
	@FindBy(css="body")
	private WebElement body;

	protected ImageComparison imageComparison;

	public AdsComparison() {
		imageComparison = new ImageComparison();
	}

	protected void hideSlot(String slotSelector, WebDriver driver) {
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
			slotSelector
		);
		PageObjectLogging.log(
			"AdInSlotHidden", "Ad in slot hidden; Slot CSS " + slotSelector, true
		);
	}


	public boolean compareImageWithScreenshot(
		String filePath, Dimension screenshotSize, Point startPoint, WebDriver driver
	) throws IOException {
		String encodedExpectedScreen = readFileAsString(filePath);
		Shooter shooter = new Shooter();
		File capturedScreen =  shooter.capturePageAndCrop(
			startPoint, screenshotSize, driver
		);
		String encodedCapturedScreen = readFileAndEncodeToBase(capturedScreen);
		capturedScreen.delete();
		boolean success = true;

		if (!(
			imageComparison.comapareBaseEncodedImagesBasedOnBytes(
				encodedExpectedScreen, encodedCapturedScreen
			))
		) {
			success = false;
		}
		return success;
	}

	public boolean compareElementWithScreenshot(
		WebElement element, String expectedElementFilePath, Dimension expectedElementSize, WebDriver driver
	) throws IOException{
		Shooter shooter = new Shooter();
		String encodedExpectedAd = readFileAsString(expectedElementFilePath);
		File elementScreenshot = shooter.captureWebElementAndCrop(element, expectedElementSize, driver);
		String encodedElementScreenshot = readFileAndEncodeToBase(elementScreenshot);
		elementScreenshot.delete();
		if (
			imageComparison.comapareBaseEncodedImagesBasedOnBytes(encodedExpectedAd, encodedElementScreenshot)
		) {
			return true;
		} else {
			return false;
		}
	}

	public boolean compareSlotOnOff(WebElement element, String elementSelector, WebDriver driver) {
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
		hideSlot(elementSelector, driver);
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

	private String readFileAndEncodeToBase(File file) throws IOException {
		Base64 coder = new Base64();
		return IOUtils.toString(
			coder.encode(FileUtils.readFileToByteArray(file)), "UTF-8"
		);
	}

	private String readFileAsString(String filePath) throws IOException {
		return IOUtils.toString(new FileInputStream(new File(filePath)), "UTF-8");
	}
}
