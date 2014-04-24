package com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.AdsMobile;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.Helpers.AdsComparison;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileBasePageObject;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Bogna 'bognix' Knychala
 */
public class AdsMobileBaseObject extends MobileBasePageObject {

	@FindBy(css="#MOBILE_TOP_LEADERBOARD")
	private WebElement topLeaderboard;

	public AdsMobileBaseObject(WebDriver driver, String page) {
		super(driver);
		driver.get(page);
	}

	public void verifyTopLeaderboard() {
		AdsComparison adsComparison = new AdsComparison();
		boolean result = adsComparison.compareSlotOnOff(
				topLeaderboard, "#MOBILE_TOP_LEADERBOARD", driver
		);
		if(result) {
			PageObjectLogging.log(
					"CompareScreenshot", "Screenshots look the same", false
			);
			throw new NoSuchElementException(
					"Screenshots of element on/off look the same."
							+ "Most probable ad is not present; CSS "
							+ "#MOBILE_TOP_LEADERBOARD"
			);
		} else {
			PageObjectLogging.log(
					"CompareScreenshot", "Screenshots are different", true
			);
		}
	}
}
