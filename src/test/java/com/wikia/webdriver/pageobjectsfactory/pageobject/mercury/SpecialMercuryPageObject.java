package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileBasePageObject;

/**
 * Created by lukasz on 2014-11-20.
 */
public class SpecialMercuryPageObject extends MobileBasePageObject {

	public SpecialMercuryPageObject(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "button[name=opt]")
	private WebElement optInButton;

	public void clickMercuryButton() {
		waitForElementVisibleByElement(optInButton);
		optInButton.click();
	}


}
