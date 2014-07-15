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
		File capturedScreen =  shooter.capturePageAndCrop(
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

	protected boolean checkIfScriptInsideScripts(List<WebElement> scripts, String script, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		for (WebElement scriptNode : scripts) {
			String result = (String) js.executeScript(
				"return arguments[0].innerHTML", scriptNode
			);
			String trimedResult = result.replaceAll("\\s", "");
			if (trimedResult.contains(script)) {
				return true;
			}
		}
		return false;
	}


	public boolean hasSkin(WebElement element, String elementSelector, WebDriver driver) {
		List<WebElement> scriptsInFrame = element.findElements(By.tagName("script"));
		String skinCallJS = "top.loadCustomAd";

		if (checkIfScriptInsideScripts(scriptsInFrame, skinCallJS, driver)) {
			PageObjectLogging.log(
				"Found skin call",
				"skin call found in " + elementSelector,
				true
			);
			return true;
		}
		return false;
	}
}
