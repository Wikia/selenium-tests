package com.wikia.webdriver.PageObjectsFactory.ComponentObject.ModalWindows;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

/**
* @author Michal 'justnpT' Nowierski
*/
public class FacebookSignupModalComponentObject extends WikiBasePageObject {

	@FindBy(css = "button[name='__CONFIRM__']")
	private WebElement appTermsConfirmButton;
	@FindBy(css = "#u_1_7")
	private WebElement postingPolicyWindowIndicator;

	String winHandleBefore;

	public FacebookSignupModalComponentObject(WebDriver driver, String winHandleBeforeFBClick) {
		super(driver);
		winHandleBefore = winHandleBeforeFBClick;
	}

	public void acceptWikiaAppPolicy() {
		//Switch to new window opened
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		waitForElementByElement(appTermsConfirmButton);
		appTermsConfirmButton.click();
		PageObjectLogging.log("acceptWikiaAppPolicy", "confirmed wikia apps privacy policy", true);
		waitForElementByElement(postingPolicyWindowIndicator);
		waitForElementByElement(appTermsConfirmButton);
		appTermsConfirmButton.click();
		PageObjectLogging.log("acceptWikiaAppPolicy", "confirmed wikia apps posting policy", true);
		//Switch back to original browser (first window)
		driver.switchTo().window(winHandleBefore);
	}

}
