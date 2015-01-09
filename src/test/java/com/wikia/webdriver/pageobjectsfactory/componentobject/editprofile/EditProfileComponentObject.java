package com.wikia.webdriver.pageobjectsfactory.componentobject.editprofile;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class EditProfileComponentObject extends WikiBasePageObject {

	@FindBy(css = "li[data-tab='avatar']")
	protected WebElement avatarTab;
	@FindBy(css = "li[data-tab='about']")
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
