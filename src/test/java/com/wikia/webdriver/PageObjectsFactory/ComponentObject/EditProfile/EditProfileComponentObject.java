package com.wikia.webdriver.PageObjectsFactory.ComponentObject.EditProfile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class EditProfileComponentObject extends WikiBasePageObject {

	@FindBy(css="li[data-tab='avatar']")
	protected WebElement avatarTab;
	@FindBy(css="li[data-tab='about']")
	protected WebElement aboutTab;

	public EditProfileComponentObject(WebDriver driver) {
		super(driver);
	}

	protected void clickAvatarTab() {
		avatarTab.click();
		PageObjectLogging.log("clickAvatarTab", "avatar tab clicked", true);
	}

	protected void clickAboutTab() {
		aboutTab.click();
		PageObjectLogging.log("clickAboutTab", "about tab clicked", true);
	}
}
