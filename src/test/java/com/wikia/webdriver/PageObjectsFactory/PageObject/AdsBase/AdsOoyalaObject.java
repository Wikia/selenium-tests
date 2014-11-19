package com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase;

import com.wikia.webdriver.Common.Core.CommonExpectedConditions;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.Helpers.AdsComparison;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.Color;
import java.util.concurrent.TimeUnit;

/**
 * @author Dmytro Rets
 * @ownership AdEngineering
 */
public class AdsOoyalaObject extends AdsBaseObject {
	@FindBy(css="object[data^='http://player.ooyala.com/player.swf']")
	private WebElement lightbox;

	public AdsOoyalaObject(WebDriver driver, String page) {
		super(driver, page);
	}

	public void verifyLightboxAd(Color lightboxAdColor, int adDurationSec) {
		verifyColorAd(lightbox, lightboxAdColor, adDurationSec);
		PageObjectLogging.log("LightboxAd",
			"Lightbox had " + lightboxAdColor + " during " + adDurationSec + " seconds", true);
	}

	public void verifyLightboxVideo(Color lightboxVideoColor, int videoDurationSec) {
		verifyColorAd(lightbox, lightboxVideoColor, videoDurationSec);
		PageObjectLogging.log("LightboxVideo",
			"Lightbox had " + lightboxVideoColor + " during " + videoDurationSec + " seconds", true);
	}

	private void verifyColorAd(WebElement element, Color color, int durationSec) {
		AdsComparison adsComparison = new AdsComparison();
		waitForColorAds(element, color);
		adsComparison.verifyColorAd(element, color, durationSec, driver);
	}

	private void waitForColorAds(WebElement element, Color color) {
		changeImplicitWait(500, TimeUnit.MILLISECONDS);
		try {
			wait.until(CommonExpectedConditions.elementToHaveColor(element, color, AdsComparison.IMAGE_ACCURACY_PERCENT));
		} finally {
			restoreDeaultImplicitWait();
		}
	}
}
