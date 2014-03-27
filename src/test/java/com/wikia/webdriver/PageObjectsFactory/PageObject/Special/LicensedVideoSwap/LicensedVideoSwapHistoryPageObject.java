package com.wikia.webdriver.PageObjectsFactory.PageObject.Special.LicensedVideoSwap;

/**
 * Created by kenkouot on 3/19/14.
 */

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.LicensedVideoSwap.LicensedVideoSwapPageObject;


public class LicensedVideoSwapHistoryPageObject extends SpecialPageObject {
	@FindBy(css = ".subtitle a")
	private WebElement backToLvsBtn;
	@FindBy(css = ".undo-link")
	private WebElement firstUndoLink;

	public LicensedVideoSwapHistoryPageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public LicensedVideoSwapPageObject navigateToLvsPage() {
		backToLvsBtn.click();
		PageObjectLogging.log("navigateToLvsPage", "verify that the lvs back button navigates to lvs page", true);
		return new LicensedVideoSwapPageObject(driver);

	}

	public void verifyOnHistoryPage() {
		String url = driver.getCurrentUrl();
		String[] urlParts = url.split("/");
		Assertion.assertEquals(urlParts[urlParts.length - 1], "History");
		PageObjectLogging.log("verifyOnHistoryPage", "verify that the url is the correct one for history page", true);
	}

	public void clickUndoSwapLink() {
		firstUndoLink.click();
		PageObjectLogging.log("undoSwap", "undo link clicked", true);
	}
	public void verifyUndoSucceeded() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			String notificationMsg = driver.findElement(By.cssSelector(".global-notification .msg")).getText();
			PageObjectLogging.log("verifyUndoSucceeded", "grabbing notification text", true);
			Assertion.assertEquals(notificationMsg, "This page has been restored.");
		} catch (NoSuchElementException e) {
			PageObjectLogging.log("verifyUndoSucceeded", "no notification present", true);
			driver.quit();
		}
	}
}

