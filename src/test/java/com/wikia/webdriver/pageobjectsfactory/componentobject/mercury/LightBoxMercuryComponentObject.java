package com.wikia.webdriver.pageobjectsfactory.componentobject.mercury;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;

/*
* @author: Rodrigo Gomez, Łukasz Nowak
* */

public class LightBoxMercuryComponentObject extends MercuryBasePageObject{

	@FindBy(css = ".lightbox-close-wrapper")
	private WebElement closeLightboxButton;
	@FindBy(css = ".lightbox-header-title")
	private WebElement imagesCounter;
	@FindBy(css = ".current")
	private WebElement currentImage;
	@FindBy(css = ".media-lightbox")
	private WebElement lightboxWrapper;
	@FindBy(css = ".lightbox-content-inner")
	private WebElement lightboxInner;
	@FindBy(css = ".lightbox-content")
	private WebElement lightboxContent;
	@FindBy(css = ".page-wrapper")
	private WebElement pageWrapper;

	public LightBoxMercuryComponentObject(WebDriver driver) {
		super(driver);
	}

	public MercuryBasePageObject clickCloseButton() {
		waitForElementVisibleByElement(closeLightboxButton);
		closeLightboxButton.click();
		return new MercuryBasePageObject(driver);
	}

	public String getCurrentImagePath() {
		return currentImage.getAttribute("src");
	}

	public void verifyCurrentImageIsVisible() {
		waitForElementVisibleByElement(currentImage);
		Assertion.assertTrue(checkIfElementOnPage(currentImage));
	}

	public void swipeImageLeft() {
		swipeLeft(lightboxContent);
		PageObjectLogging.log("swipeImageLeft", "Swipe left was simulated", true, driver);
	}

	public void swipeImageRight() {
		swipeRight(lightboxContent);
		PageObjectLogging.log("swipeImageRight", "Swipe right was simulated", true, driver);
	}

	public void testGestures() {
		doubleTapZoom(lightboxWrapper);
		doubleTapZoom(lightboxInner);
		doubleTapZoom(lightboxContent);
		doubleTapZoom(pageWrapper);
		PageObjectLogging.log("testGestures", "Double tap zoom was correctly simulated 4 times", true, driver);
	}

	public void verifyImageWasChanged(String imageOnePath, String imageTwoPath) {
		Assertion.assertFalse(imageOnePath.equals(imageTwoPath), "Image in lightbox was changed");
	}

	public void verifyLightboxClosed() {
		Assertion.assertFalse(checkIfElementOnPage(currentImage));
	}
}

