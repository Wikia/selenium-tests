/**
 *
 */
package com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Watch;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

	public void confirm() {
		followUnfollowConfirmation.click();
	}



}
