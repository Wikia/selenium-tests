package com.wikia.webdriver.pageobjectsfactory.componentobject.mercury;

import com.wikia.webdriver.common.core.Assertion;
import io.appium.java_client.MobileDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;

/*
* @author: Rodrigo Gomez, Łukasz Nowak
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

	public MercuryBasePageObject clickCloseButton() {
		waitForElementVisibleByElement(closeLightboxButton);
		closeLightboxButton.click();
		return new MercuryBasePageObject(driver);
	}

	public void verifyCurrentImageIsVisible() {
		waitForElementVisibleByElement(currentImage);
		Assertion.assertTrue(checkIfElementOnPage(currentImage));
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

	public void verifyLightboxClosed() {
		Assertion.assertFalse(checkIfElementOnPage(currentImage));
	}

}
