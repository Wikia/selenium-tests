package com.wikia.webdriver.PageObjectsFactory.PageObject.Special;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;

public class SpecialFBConnectPageObject extends SpecialPageObject {

	@FindBy(css = "#WikiaArticle iframe[src^=\"http://www.facebook.com/plugins/login_button\"]")
	private WebElement fbConnectButton;

	public SpecialFBConnectPageObject(WebDriver driver) {
		super(driver);
	}

	/**
	 * verify that the Facebook button was properly loaded on the special page
	 */
	public void verifyFacebookButtonAppeared() {
		waitForElementByElement(fbConnectButton);
		PageObjectLogging.log("verifyFacebookButtonAppeared",
				"Verify that facebook button appeared", true, driver);

	}

}
