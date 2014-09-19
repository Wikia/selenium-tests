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

public class LicensedVideoSwapPageObject extends SpecialPageObject {
	@FindBy(css = ".lvs-history-btn")
	private WebElement lvsHistoryBtn;

	@FindBy(xpath = "//a[@title='Special:LicensedVideoSwap']" )
	private WebElement backLink;

	@FindBy(css = ".swap-button")
	private WebElement firstSwapButton;

	@FindBy(css = ".count")
	private WebElement swapCount;

	public LicensedVideoSwapPageObject(WebDriver driver) {
		super(driver);
	}

	public LicensedVideoSwapHistoryPageObject navigateToHistoryPage() {
		// Make sure the button has been drawn
		this.waitForElementByElement(lvsHistoryBtn);
		lvsHistoryBtn.click();

		// Make sure the click above has happened and the browser has responded by looking for an element
		// on the history page
		this.waitForElementByElement(backLink);
		PageObjectLogging.log("navigateToHistoryPage", "lvs history button navigates to right page", true);

		return new LicensedVideoSwapHistoryPageObject(driver);
	}

	public void verifyOnLvsPage() {
		String url = driver.getCurrentUrl();
		Assertion.assertTrue(url.contains(URLsContent.specialLicensedVideoSwap));
		PageObjectLogging.log("verifyOnLvsPage", "url is the correct one for LVS page", true);
	}

	public void verifySwapVideo() {
		// Make sure the element is on page before trying to retrieve it
		this.waitForElementByElement(swapCount);
		int initialCount = Integer.parseInt(swapCount.getText());

		// Swap the first video
		this.waitForElementByElement(firstSwapButton);
		firstSwapButton.click();

		this.waitForTextToBePresentInElementByElement(swapCount,  String.valueOf(initialCount-1));

		PageObjectLogging.log("verifyClickSwap", "Swap button has been clicked", true);
	}
}

