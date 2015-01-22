package com.wikia.webdriver.pageobjectsfactory.componentobject.mercury;

/*
* @authors: Łukasz Nowak
* */

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.PerformTouchAction;

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
	@FindBy(css = "div.smart-banner")
	private WebElement smartBanner;

	public static String BUTTON_NAME_FOR_ANDROID = "Install";
	public static String BUTTON_NAME_FOR_IOS = "GET";
	
	public SmartBannerComponentObject(WebDriver driver) {
		super(driver);
	}

	public void clickCloseButton() {
		waitForElementVisibleByElement(closeButton);
		System.out.println(smartBanner.getCssValue("display"));
		closeButton.click();
		System.out.println(smartBanner.getCssValue("display"));
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
		if (smartBanner.getCssValue("display").contains("none")) {
			PageObjectLogging.log("verifySmartBannerWasClosed", "Smart banner is hidden", true);
		} else {
			PageObjectLogging.log("verifySmartBannerWasClosed", "Smart banner is visible", false);
		}
	}
	
	public void verifyButtonName(String name) {
		waitForElementVisibleByElement(bannerButton);
		if (bannerButton.getText().contains(name)) {
			PageObjectLogging.log("verifyButtonName", "Smart banner is called "+name, true);
		} else {
			PageObjectLogging.log("verifyButtonName", "Smart banner has different name", false);
		}
	}
	
	public void verifyFixPositionOfSmartBanner(PerformTouchAction touchAction) {
		int firstPosition = smartBanner.getLocation().getY();
		touchAction.SwipeFromPointToPoint(50, 90, 50, 40, 500, 3000);
		int secondPosition = smartBanner.getLocation().getY();
		if (firstPosition == secondPosition) {
			PageObjectLogging.log("verifyFixPositionOfSmartBanner", "Smart banner is fixed", true);
		} else {
			PageObjectLogging.log("verifyFixPositionOfSmartBanner", "Smart banner is floating", false);
		}
	}
 }
