package com.wikia.webdriver.pageobjectsfactory.componentobject.mercury;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import io.appium.java_client.MobileDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;

import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.PerformTouchAction;

/*
* @author: Rodrigo Gomez, Łukasz Nowak, Tomasz Napieralski
* */

public class LightBoxMercuryComponentObject extends MercuryBasePageObject{

	public LightBoxMercuryComponentObject(WebDriver driver) {
		super(driver);
	}

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
	@FindBy(css = ".lightbox-header")
	private WebElement lightboxHeader;
	@FindBy(css = ".lightbox-footer")
	private WebElement lightboxFooter;

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
		PageObjectLogging.log("swipeImageRight", "Swipe left was simulated", true, driver);
	}

	public void testGestures() {
		doubleTapZoom(lightboxWrapper);
		System.out.println("Wrapper");
		doubleTapZoom(lightboxInner);
		System.out.println("Inner");
		doubleTapZoom(lightboxContent);
		System.out.println("Content");
		doubleTapZoom(pageWrapper);
		System.out.println("page wrapper");

	}

	public void verifyImageWasChanged(String imageOnePath, String imageTwoPath) {
		Assertion.assertFalse(imageOnePath.equals(imageTwoPath), "Image in lightbox was changed");
	}

	public void verifyLightboxClosed() {
		Assertion.assertFalse(checkIfElementOnPage(currentImage));
	}
	
	public String currentImageSrcPath () {
		return currentImage.getAttribute("src");
	}
	
	public void verifySwiping (PerformTouchAction touchAction, String direction, int attempts) {
		String currentImageSrc = getCurrentImagePath();
		String nextImageSrc;
		boolean imageChanged = false;
		int startX = 70;
		int endX = 20;
		int duration = 300;
		int waitAfter = 5000;
		if (direction == "right") {
			int temp = startX;
			startX = endX;
			endX = temp;
		} else {
			direction = "left";
		}
		for (int i = 0; i < attempts; ++i) {
			touchAction.SwipeFromPointToPoint(startX, 50, endX, 50, duration, waitAfter);
			nextImageSrc = getCurrentImagePath();
			if (!nextImageSrc.contains(currentImageSrc)) {
				imageChanged = true;
				break;
			}
		}
		if (imageChanged) {
			PageObjectLogging.log("verifySwiping", "Swiping to " + direction + " works", true);
		} else {
			PageObjectLogging.log("verifySwiping", "Swiping to " + direction + " doesn't work", false);
		}
	}
	
	public void verifyVisibilityUI (PerformTouchAction touchAction) {
		System.out.println(lightboxHeader.getAttribute("class"));
		//touchAction.TapOnPointXY(50, 50, 500, 1000);
		
	}

}
