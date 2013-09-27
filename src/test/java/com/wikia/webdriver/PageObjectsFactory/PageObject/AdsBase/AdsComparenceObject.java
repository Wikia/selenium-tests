package com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase;

import com.wikia.webdriver.Common.ContentPatterns.AdsContent;
import com.wikia.webdriver.Common.Core.ImageUtilities.ImageComparer;
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
public class AdsComparenceObject extends AdsBaseObject {

	public AdsComparenceObject(WebDriver driver, String page) {
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
				+ AdsContent.getSlotSelector(presentLBName)
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
		WebElement iframe = (WebElement) js.executeScript(
			"return $(arguments[0] + ' iframe:visible:first-child')[0]",
			elementSelector
		);
		driver.switchTo().frame(iframe);
		js.executeScript(
			"document.getElementsByTagName('body')[0].style['display'] = 'none';"
		);
		PageObjectLogging.log(
			"HideElement", "Element is hidden; CSS " + elementSelector, true
		);
		driver.switchTo().defaultContent();
		File postSwitch = shooter.captureWebElement(element, driver);
		PageObjectLogging.log(
			"ScreenshotElement",
			"Screenshot of element off taken; CSS " + elementSelector,
			true
		);
		ImageComparer comparer = new ImageComparer();

		return comparer.compareImagesBasedOnBytes(preSwitch, postSwitch);
	}
}
