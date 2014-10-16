package com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.Helpers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.wikia.webdriver.Common.Core.ImageUtilities.ImageComparison;
import com.wikia.webdriver.Common.Core.ImageUtilities.ImageEditor;
import com.wikia.webdriver.Common.Core.ImageUtilities.Shooter;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;

/**
 * @author Bogna 'bognix' Knychala
 */
public class AdsComparison {

	protected ImageComparison imageComparison;
	//Chromedriver has an open issue and all screenshots made in chromedriver on mobile are scaled
	private final double chromeDriverScreenshotScale = 0.5;

	public AdsComparison() {
		imageComparison = new ImageComparison();
	}

	public void hideSlot(String slotSelector, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Check if we are using mobile skin. Since mobile skin uses different version of jQuery
		if (slotSelector.toUpperCase().contains("MOBILE")) {
			js.executeScript(
				"$(arguments[0]).css('visibility', 'hidden')",
				slotSelector
			);
		} else {
			// Find ad-containing element and set visibility = hidden on it
			// Example selector:
			// AD_SLOT iframe:visible:first, AD_SLOT img:visible:first, AD_SLOT object:visible:first
			js.executeScript(
				"var iframes = arguments[0] + ' iframe:visible, ';"
				+ "var objects = arguments[0] + ' object:visible, ';"
				+ "var imgs = arguments[0] + ' img:visible';"
				+ "var elements = $(iframes + objects + imgs);"
				+ "for (var i=0; i < elements.length; i++) { elements[i].style.visibility = 'hidden'; }",
				slotSelector
			);
		}
		PageObjectLogging.log(
			"AdInSlotHidden", "Ad in slot hidden; Slot CSS " + slotSelector, true
		);
	}


	public boolean compareImageWithScreenshot(
		String filePath, Dimension screenshotSize, Point startPoint, WebDriver driver
	) {
		String encodedExpectedScreen = null;
		try {
			encodedExpectedScreen = readFileAsString(filePath);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		Shooter shooter = new Shooter();
		File capturedScreen = shooter.capturePageAndCrop(
			startPoint, screenshotSize, driver
		);

		String encodedCapturedScreen = null;
		try {
			encodedCapturedScreen = readFileAndEncodeToBase(capturedScreen);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		capturedScreen.delete();
		boolean success = true;

		if (!(
			imageComparison.areBase64StringsTheSame(encodedExpectedScreen, encodedCapturedScreen))
		) {
			success = false;
		}
		return success;
	}

	public boolean isAdVisible(WebElement element, String elementSelector, WebDriver driver) {
		Shooter shooter = new Shooter();
		if (element.getSize().height <= 1 || element.getSize().width <= 1) {
			PageObjectLogging.log(
					"ScreenshotElement",
					"Element has size 1px x 1px or smaller. Most probable is not displayed; CSS " + elementSelector,
					false,
					driver
			);
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
		boolean imagesTheSame = imageComparison.areFilesTheSame(preSwitch, postSwitch);
		preSwitch.delete();
		postSwitch.delete();
		return !imagesTheSame;
	}

	public File getMobileSlotScreenshot(WebElement element, WebDriver driver) {
		ImageEditor imageEditor = new ImageEditor();
		Shooter shooter = new Shooter();
		File page = shooter.capturePage(driver);
		BufferedImage scaledPage = imageEditor.scaleImage(
			page, chromeDriverScreenshotScale, chromeDriverScreenshotScale
		);
		File slotScreenshot = imageEditor.cropImage(element.getLocation(), element.getSize(), scaledPage);
		return slotScreenshot;
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
