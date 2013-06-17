package com.wikia.webdriver.PageObjectsFactory.PageObject.Special;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;

public class SpecialCssPageObject extends SpecialPageObject {

		@FindBy(css = ".ace_editor")
		private WebElement aceEditor;
		@FindBy(css = "textarea.ace_text-input")
		private WebElement aceInputTextArea;
		@FindBy(css = ".ace_text-layer")
		private WebElement aceLayerTextArea;
		@FindBys(@FindBy(css = ".ace_content div[class*='ace']"))
		private List<WebElement> aceElementsList;
		@FindBys(@FindBy(css = "div.ace_line span"))
		private List<WebElement> aceLinesList;
		@FindBy(css = ".ace_error")
		private WebElement aceError;

	    public SpecialCssPageObject(WebDriver driver) {
	        super(driver);
	    }

	    public void verifyAceEditorPresence() {
	        waitForElementByElement(aceEditor);
	        PageObjectLogging.log("verifyAceEditorPresence", "Ace Editor is present.", true);
	    }

		public void verifyHighlighting() {
			Assertion.assertNotEquals(aceElementsList.size(), 0);
			PageObjectLogging.log("verifyHighlighting", "There are elements highlighted by ace library", true);
		}

		public void sendCssText(String cssText) {
			waitForElementByElement(aceLayerTextArea);
			executeScript("ace.edit('cssEditorContainer').setValue('"+ cssText +"');");
			PageObjectLogging.log("sendCssText", "the following text was send to ace editor: "+cssText, true, driver);
		}

		public void verifyAceError() {
			waitForElementByElement(aceError);
			PageObjectLogging.log("verifyAceError", "verify that highlightet ace shows an error", true);
		}
}
