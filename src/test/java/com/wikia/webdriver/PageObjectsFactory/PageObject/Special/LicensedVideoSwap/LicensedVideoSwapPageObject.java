package com.wikia.webdriver.PageObjectsFactory.PageObject.Special.LicensedVideoSwap;

/**
 * Created by kenkouot on 3/19/14.
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.LicensedVideoSwap.LicensedVideoSwapHistoryPageObject;

public class LicensedVideoSwapPageObject extends SpecialPageObject {
	@FindBy(css = ".lvs-history-btn")
	private WebElement lvsHistoryBtn;

	public LicensedVideoSwapPageObject(WebDriver driver) {
		super(driver);
	}

	public LicensedVideoSwapHistoryPageObject navigateToHistoryPage() {
		lvsHistoryBtn.click();
		PageObjectLogging.log("navigateToHistoryPage", "lvs history button navigates to right page", true);
		return new LicensedVideoSwapHistoryPageObject(driver);
	}

	public void verifyOnLvsPage() {
		String url = driver.getCurrentUrl();
		Assertion.assertTrue(url.contains(URLsContent.specialLicensedVideoSwap));
		PageObjectLogging.log("verifyOnLvsPage", "url is the correct one for LVS page", true);
	}
}

