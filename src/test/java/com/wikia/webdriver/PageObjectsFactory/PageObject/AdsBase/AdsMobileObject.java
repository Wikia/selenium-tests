package com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase;

import com.wikia.webdriver.Common.ContentPatterns.AdsMobileContent;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author Bogna 'bognix' Knychała
 */
public class AdsMobileObject extends WikiBasePageObject {

	private final String adFooter = "#wkMdlFtr .wkAdLabel";

	@FindBy(css=".lazy.media.noSect.loaded")
	private WebElement galleryThumb;
	@FindBy(css="#wkMdlWrp")
	private WebElement modalWrapper;
	@FindBy(css="#nxtImg")
	private WebElement nextImg;

	public AdsMobileObject(WebDriver driver, String testedPage) {
		super(driver);
		getUrl(testedPage);
	}

	public boolean verifyCorrectAdInContent(
		String acceptableAd, String unacceptableAd
	) throws Exception {
		int refreshNumber = 0;
		while (refreshNumber < 5) {
			if (
				!scrollToSelectorNoJQ(AdsMobileContent.getSlotSelector(AdsMobileContent.adInContent))
				|| !verifyAd(AdsMobileContent.adInContent)
			) {
				throw new NoSuchElementException(
					"Selector: "
					+ AdsMobileContent.getSlotSelector(AdsMobileContent.adInContent)
					+ " not found!"
				);
			}
			WebElement adInContent = driver.findElement(
				By.cssSelector(AdsMobileContent.getSlotSelector(AdsMobileContent.adInContent))
			);
			if (checkIfElementOnPage("img[src='" + acceptableAd + "']")) {
				PageObjectLogging.log(
					"AdFound", "Expected ad found",
					true
				);
				return true;
			} else if (checkIfElementOnPage("img[src='" + unacceptableAd + "']")) {
				PageObjectLogging.log(
					"UnexpectedAdFound", "Found element that was not expected!",
					false, driver
				);
				throw new Exception("Found element that was not expected!");
			} else {
				PageObjectLogging.log(
					"AdNotFound", "Expected ad not found, refreshing the page",
					true, driver
				);
				driver.navigate().refresh();
				refreshNumber += 1;
			}
		}
		PageObjectLogging.log(
			"AdNotFound",
			"Expected ad not found, after refreshing 5 times",
			false, driver
		);
		throw new NoSuchElementException("Expected ad not found: " + acceptableAd);
	}

	public boolean verifyCorrectInterstitial(
		String acceptableAd, String unacceptableAd
	) throws Exception {
		int refreshNumber = 0;
		while (refreshNumber < 5) {
			galleryThumb.click();
			waitForElementVisibleByElement(modalWrapper);
			boolean adFoundInInterstital = false;
			while (!adFoundInInterstital) {
				nextImg.click();
				if (checkIfElementOnPage(adFooter)) {
					adFoundInInterstital = true;
				}
			}
			if (!verifyAd(AdsMobileContent.interstitial)) {
				throw new NoSuchElementException(
					"Selector: "
					+ AdsMobileContent.getSlotSelector(AdsMobileContent.interstitial)
					+ " not found!"
				);
			}
			WebElement intersitialAd = driver.findElement(
				By.cssSelector(
					AdsMobileContent.getSlotSelector(
						AdsMobileContent.interstitial
					)
				)
			);
			if (checkIfElementInElement("img[src='" + acceptableAd + "']", intersitialAd)) {
				PageObjectLogging.log(
					"AdFound", "Expected ad found",
					true
				);
				return true;
			} else if (
				checkIfElementInElement("img[src='" + unacceptableAd + "']", intersitialAd)
			) {
				PageObjectLogging.log(
					"UnexpectedAdFound", "Found element that was not expected!",
					false, driver
				);
				throw new Exception("Found element that was not expected!");
			} else {
				PageObjectLogging.log(
					"AdNotFound", "Expected ad not found, refreshing the page",
					true, driver
				);
				driver.navigate().refresh();
				refreshNumber += 1;
			}
		}
		PageObjectLogging.log(
			"AdNotFound",
			"Expected ad not found, after refreshing 5 times",
			false, driver
		);
		throw new NoSuchElementException("Expected ad not found: " + acceptableAd);
	}

	private boolean verifyAd(String slotName) {
		if (checkIfElementOnPage(AdsMobileContent.getSlotSelector(slotName))) {
			WebElement ad = driver.findElement(By.cssSelector(AdsMobileContent.getSlotSelector(slotName)));
			List<WebElement> scripts = ad.findElements(By.tagName("script"));
			for (WebElement script: scripts) {
				if (
					script.getAttribute("src").contains(AdsMobileContent.dartCallScript)
					&& script.getAttribute("src").contains(slotName)
				) {
					PageObjectLogging.log(
						"DartCall",
						"Dart call correct",
						true
					);
					return true;
				} else {
					PageObjectLogging.log(
						"DartCall",
						"Dart call incorrect: " + script.getAttribute("src"),
						false
					);
					return false;
				}
			}
		} else {
			PageObjectLogging.log(
				"ElementNotFound",
				"Expected element not found!",
				false, driver
			);
			return false;
		}
		return true;
	}
}
