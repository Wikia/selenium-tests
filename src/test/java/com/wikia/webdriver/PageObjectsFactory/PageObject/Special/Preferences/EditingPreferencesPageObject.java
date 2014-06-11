package com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Preferences;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class EditingPreferencesPageObject extends PreferencesPageObject {

	public EditingPreferencesPageObject(WebDriver driver) {
		super(driver);
	}

	@FindBy(css="select#mw-input-wpeditor")
	private WebElement preferredEditorDropdown;
	@FindBy(css="select#mw-input-wpeditor option")
	private List<WebElement> preferredEditorOptions;
	@FindBy(css="#mw-htmlform-editing-experience .mw-htmlform-field-HTMLSelectField .mw-input")
	private WebElement dropdown;

	public void selectPreferredEditor(int option) {
		waitForElementByElement(preferredEditorDropdown);
		Select select = new Select(preferredEditorDropdown);
		select.selectByIndex(option);
//		waitForElementClickableByElement(preferredEditorDropdown);
//		WebElement selected = preferredEditorOptions.get(option);
//		dropdown.click();
//		preferredEditorDropdown.click();
//		selected.click();
//		Actions actions = new Actions(driver);
//		actions
//			.click(preferredEditorDropdown)
//			.click(selected)
//			.build()
//			.perform();
	}

}
