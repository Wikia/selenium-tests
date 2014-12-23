package com.wikia.webdriver.pageobjectsfactory.componentobject.mercury;

import com.wikia.webdriver.common.core.Assertion;
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

	public MercuryBasePageObject clickCloseButton() {
		waitForElementVisibleByElement(closeLightboxButton);
		closeLightboxButton.click();
		return new MercuryBasePageObject(driver);
	}

	public void verifyCurrentImageIsVisible() {
		waitForElementVisibleByElement(currentImage);
		Assertion.assertTrue(checkIfElementOnPage(currentImage));
	}

	public void verifyLightboxClosed() {
		Assertion.assertFalse(checkIfElementOnPage(currentImage));
	}

}
