package com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase;

import com.wikia.webdriver.Common.ContentPatterns.AdsContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Core.ImageUtilities.ImageComparison;
import com.wikia.webdriver.Common.Core.ImageUtilities.Shooter;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class AdsComparisonObject extends AdsBaseObject {

	@FindBy(css="#WikiaPage")
	private WebElement wikiaArticle;
	@FindBy(css="body")
	private WebElement body;

	protected ImageComparison imageComparison;

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

	/**
	 * Check Ad skin on page with provided resolution.
	 * Compare left and right sides of skin with provided Base64.
	 * @param adSkinUrl - DFP link with ad skin image
	 * @param windowResolution - resolution
	 * @param skinWidth - skin width on the sides of the article
	 * @param expectedAdSkinLeftPart - path to file with expected skin encoded in Base64
	 * @param expectedAdSkinRightPart - path to file with expected skin encoded in Base64
	 * @throws IOException
	 */
	public void checkAdSkinPresenceOnGivenResolution(
		String adSkinUrl, Dimension windowResolution, int skinWidth,
		String expectedAdSkinLeftPart, String expectedAdSkinRightPart
	) throws IOException {
		Shooter shooter = new Shooter();

		String backgroundImageUrlAfter = getPseudoElementValue(
			body, ":after", "backgroundImage"
		);
		Assertion.assertStringContains(backgroundImageUrlAfter, adSkinUrl);

		String backgroundImageUrlBefore = getPseudoElementValue(
			body, ":before", "backgroundImage"
		);
		Assertion.assertStringContains(backgroundImageUrlBefore, adSkinUrl);


		driver.manage().window().setSize(windowResolution);

		PageObjectLogging.log(
			"ScreenshotPage",
			"Screenshot of the page taken",
			true, driver
		);

		String encodedExpectedLeft = readFileAsString(expectedAdSkinLeftPart);
		String encodedExpectedRight = readFileAsString(expectedAdSkinRightPart);

		Dimension adScreenSize = new Dimension(skinWidth, 500);
		int articleLocationX = wikiaArticle.getLocation().x;
		int articleWidth = wikiaArticle.getSize().width;

		File leftScreen =  shooter.capturePageAndCrop(
			new Point(articleLocationX - skinWidth,100), adScreenSize, driver
		);
		File rightScreen =  shooter.capturePageAndCrop(
			new Point(articleLocationX + articleWidth,100), adScreenSize, driver
		);
		String encodedLeftScreen = readFileAndEncodeToBase(leftScreen);
		String encodedRightScreen = readFileAndEncodeToBase(rightScreen);
		rightScreen.delete();
		leftScreen.delete();

		boolean success = true;

		if (
			imageComparison.comapareBaseEncodedImagesBasedOnBytes(
				encodedExpectedLeft, encodedExpectedLeft
			)
		) {
			PageObjectLogging.log(
				"ExpectedSkinFound", "Expected ad skin found on page - left side of skin", true
			);
		} else {
			PageObjectLogging.log(
				"ExpectedSkinNotFound", "Expected ad skin not found on page - left side of skin", false, driver
			);
			success = false;
		}

		if (
			imageComparison.comapareBaseEncodedImagesBasedOnBytes(
				encodedExpectedRight, encodedExpectedRight
			)
		) {
			PageObjectLogging.log(
				"ExpectedSkinFound", "Expected ad skin found on page - right side of skin", true
			);
		} else {
			PageObjectLogging.log(
				"ExpectedSkinNotFound", "Expected ad skin not found on page - right side of skin", false, driver
			);
			success = false;
		}

		if (!success) {
			throw new NoSuchElementException(
				"Expected ad skin not found on page"
			);
		}
	}

	public void checkToolbarAdBySize(String adBaseLocation, Dimension size) throws IOException {
		Shooter shooter = new Shooter();
		String encodedExpectedAd = readFileAsString(adBaseLocation);

		PageObjectLogging.log(
			"ScreenshotElement",
			"Screenshot of the element taken, Selector: " + AdsContent.wikiaBarSelector,
			true, driver
		);
		File toolbarScreen =  shooter.captureWebElementAndCrop(toolbar, size, driver);
		String encodedToolbarScreen = readFileAndEncodeToBase(toolbarScreen);
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
		hideSlot(elementSelector);
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
