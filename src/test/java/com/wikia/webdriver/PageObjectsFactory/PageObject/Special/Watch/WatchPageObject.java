/**
 *
 */
package com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Watch;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class WatchPageObject extends BasePageObject{


	@FindBy(css="[value=OK]")
	private WebElement followUnfollowConfirmation;

	public WatchPageObject(WebDriver driver) {
		super(driver);
	}

	public void confirmWatchUnwatch() {
		followUnfollowConfirmation.click();
		PageObjectLogging.log("confirmWatchUnwatch", "follow/unfollow confirmation button clicked", true);
	}



}
