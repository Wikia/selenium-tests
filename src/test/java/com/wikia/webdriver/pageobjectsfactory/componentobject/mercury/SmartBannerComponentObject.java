package com.wikia.webdriver.pageobjectsfactory.componentobject.mercury;

/*
* @authors: Łukasz Nowak
* */

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SmartBannerComponentObject extends MercuryBasePageObject{

	@FindBy(css = ".sb-close")
	private WebElement closeButton;
	@FindBy(css = ".sb-button")
	private WebElement bannerButton;
	@FindBy(css = ".system-android")
	private WebElement androidBanner;
	@FindBy(css = ".smart-banner-visible")
	private WebElement smartBannerDiv;
	@FindBy(css = ".sb-title")
	private WebElement bannerTitle;

	public SmartBannerComponentObject(WebDriver driver) {
		super(driver);
	}

	public void clickCloseButton() {
		waitForElementVisibleByElement(closeButton);
		closeButton.click();
		PageObjectLogging.log("clickCloseButton", "Close button was clicked", true, driver);
	}

	public void clickInstallButton() {
		waitForElementVisibleByElement(bannerButton);
		if (bannerButton.getText().contains("Install")) {
			bannerButton.click();
			PageObjectLogging.log("clickInstallButton", "Install button was clicked", true);
		} else {
			PageObjectLogging.log("clickInstallButton", "Install button was not visible", false, driver);
		}
	}

	public void verifySmartBannerWasClosed() {
		Assertion.assertFalse(checkIfElementOnPage(smartBannerDiv));
	}

	public void verifyInstallButtonIsVisible() {
		waitForElementVisibleByElement(bannerButton);
		Assertion.assertTrue(bannerButton.getText().contains("Install"), "Install button was visible");
	}

	public void verifyViewButtonIsVisible() {
		waitForElementVisibleByElement(bannerButton);
		Assertion.assertTrue(bannerButton.getText().contains("View"), "View button was visible");
	}
 }
