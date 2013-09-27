package com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase;

import com.wikia.webdriver.Common.ContentPatterns.AdsContent;
import com.wikia.webdriver.Common.Core.ImageUtilities.ImageComparison;
import com.wikia.webdriver.Common.Core.ImageUtilities.Shooter;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import java.io.File;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class AdsComparisonObject extends AdsBaseObject {

	public AdsComparisonObject(WebDriver driver, String page) {
		super(driver, page);
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

	private boolean compareSlotOnOff(WebElement element, String elementSelector) {
		Shooter shooter = new Shooter();
		File preSwitch = shooter.captureWebElement(element, driver);
		PageObjectLogging.log(
			"ScreenshotElement",
			"Screenshot of the element taken, Selector: " + elementSelector,
			true
		);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement adContainer = (WebElement) js.executeScript(
			"var iframe = arguments[0] + ' iframe:visible:first, ';"
			+ "var object = arguments[0] + ' object:visible:first, ';"
			+ "var img = arguments[0] + ' img:visible:first';"
			+ "return $(iframe + object + img)[0]",
			elementSelector
		);
		boolean isIframe = adContainer.getTagName().equals("iframe");
		if (isIframe) {
			driver.switchTo().frame(adContainer);
			js.executeScript(
				"document.getElementsByTagName('body')[0].style['display'] = 'none';"
			);
			driver.switchTo().defaultContent();
		} else {
			js.executeScript(
				"var object = arguments[0] + ' object:visible:first, ';"
				+ "var img = arguments[0] + ' img:visible:first';"
				+ "$(object + img)[0].style['display'] = 'none';",
				elementSelector
			);
		}
		PageObjectLogging.log(
			"HideElement", "Element is hidden; CSS " + elementSelector, true
		);
		File postSwitch = shooter.captureWebElement(element, driver);
		PageObjectLogging.log(
			"ScreenshotElement",
			"Screenshot of element off taken; CSS " + elementSelector,
			true
		);
		ImageComparison comparer = new ImageComparison();
		boolean result = comparer.compareImagesBasedOnBytes(preSwitch, postSwitch);
		preSwitch.delete();
		postSwitch.delete();
		return result;
	}
}
