package com.wikia.webdriver.PageObjectsFactory.PageObject.Special;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;

public class SpecialCssPageObject extends SpecialPageObject {

		@FindBy(css = ".ace_editor")
		private WebElement aceEditor;

	    public SpecialCssPageObject(WebDriver driver) {
	        super(driver);
	    }

	    public void verifyAceEditorPresence() {
	        waitForElementByElement(aceEditor);
	        PageObjectLogging.log("verifyAceEditorPresence", "Ace Editor is present.", true);
	    }
}
