package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.core.CommonExpectedConditions;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.AdsComparison;
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
	private String thumbnailPlayCircleSelector = ".WikiaArticle .play-circle";
	private String thumbnailDurationSelector = ".WikiaArticle .duration";
	@FindBy(css=".WikiaArticle img.thumbimage")
	private WebElement thumbnail;
	@FindBy(css="object[data^='http://player.ooyala.com/player.swf']")
	private WebElement lightbox;

	public AdsOoyalaObject(WebDriver driver, String page) {
		super(driver, page);
	}

	public void verifyVideoThumbnail(Color thumbnailColor) {
		hideElement(thumbnailPlayCircleSelector);
		hideElement(thumbnailDurationSelector);
		verifyColorAd(thumbnail, thumbnailColor);
	}

	public void openLightbox() {
		thumbnail.click();
	}

	public void verifyLightboxAd(Color lightboxAdColor, int adDurationSec) {
		verifyColorAd(lightbox, lightboxAdColor, adDurationSec);
	}

	public void verifyLightboxVideo(Color lightboxVideoColor, int videoDurationSec) {
		verifyColorAd(lightbox, lightboxVideoColor, videoDurationSec);
	}

	private void verifyColorAd(WebElement element, Color color) {
		AdsComparison adsComparison = new AdsComparison();
		adsComparison.verifyColorAd(element, color, driver);
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
