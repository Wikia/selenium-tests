package com.wikia.webdriver.pageobjectsfactory.pageobject.special.preferences;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.wikia.webdriver.common.logging.PageObjectLogging;

public class EditingPreferencesPageObject extends PreferencesPageObject {

	public EditingPreferencesPageObject(WebDriver driver) {
		super(driver);
	}

	@FindBy(css="select#mw-input-wpeditor")
	private WebElement preferredEditorDropdown;

	public void selectPreferredEditor(String value) {
		waitForElementClickableByElement(preferredEditorDropdown);
		Select select = new Select(preferredEditorDropdown);
		select.selectByValue(value);
		PageObjectLogging.log("selectPreferredEditor", "Selected " + value + " from preference", true);
	}

}
