package com.wikia.webdriver.PageObjectsFactory.PageObject.Special;

import java.util.List;
import java.util.Random;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Core.CommonExpectedConditions;
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
		@FindBy(css = ".css-publish-button")
		private WebElement cssPublishButton;
		@FindBy(css = ".css-edit-box .drop")
		private WebElement cssPublishButtonDropdown;
		@FindBy(css = "#editSummary")
		private WebElement editSummaryField;
		@FindBy(css = "#minorEdit")
		private WebElement minorEdit;
		@FindBy(css = "#showChanges")
		private WebElement showChanges;
		@FindBy(css = "#wikiDiff")
		private WebElement wikiDiff;
		@FindBy(css = ".global-notification.confirm")
		private WebElement notificationConfirm;
		@FindBy(css = ".css-side-bar .wikia-menu-button .WikiaMenuElement a[href*=\"action=history\"]")
		private WebElement historyButton;		
		@FindBy(css = ".css-side-bar .wikia-menu-button .WikiaMenuElement a[href*=\"action=delete\"]")
		private WebElement deleteButton;
		//@FindBy(css = ".css-editor .mw-warning-with-logexcerpt")
		//private WebElement removedWarning;
		@FindBy(css = ".css-side-bar .wikia-menu-button .WikiaMenuElement a[href*=\"Special:Undelete\"]")
		private WebElement undeleteButton;
		private By removedWarning = By.cssSelector(".css-editor .mw-warning-with-logexcerpt");
		
		public SpecialCssPageObject( WebDriver driver ) {
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

		public void saveCssContent(String randomText, WikiBasePageObject wiki) {
			verifyPublishButtonAppears();
			clearCssText();
			sendAceCssText(randomText);
			//specialCss.sendCssText(randomText);
			clickPublishButton();
			wiki.verifyUrl(URLsContent.specialCSS);
			verifySaveComplete();
		}

		public void clearCssText() {
			waitForElementByElement(aceLayerTextArea);
			executeScript("ace.edit('cssEditorContainer').setValue('');");
			PageObjectLogging.log("clearCssText", "ace editor was cleared", true, driver);
		}

		public void sendAceCssText(String cssText) {
			waitForElementByElement(aceLayerTextArea);
			executeScript("ace.edit('cssEditorContainer').setValue('" + cssText + "');");
			PageObjectLogging.log("clearCssText", "the following text was send to ace editor: "+cssText, true);
		}

		public void sendEditSummaryText(String summaryText) {
			waitForElementByElement(editSummaryField);
			editSummaryField.sendKeys(summaryText);
			PageObjectLogging.log("editSummaryField", "the following text was send to ace editor: "+summaryText, true);
		}

		public void sendCssText(String cssText) {
			waitForElementByElement(aceLayerTextArea);
			waitForElementByElement(aceInputTextArea);
			aceInputTextArea.sendKeys(cssText);
			PageObjectLogging.log("sendCssText", "the following text was send to ace editor: "+cssText, true, driver);
		}

		public void verifyAceError() {
			waitForElementByElement(aceError);
			PageObjectLogging.log("verifyAceError", "verify that highlightet ace shows an error", true);
		}

		public void verifyPublishButtonAppears() {
			waitForElementByElement(cssPublishButton);
			PageObjectLogging.log("cssPublishButton", "verify that publish button appears", true);
		}

		public void verifyMinorEditAppears() {
			waitForElementByElement(minorEdit);
			PageObjectLogging.log("minorEdit", "verify that minor edit checkbox appears", true);
		}

		public void clickPublishButton() {
			clickAndWait(cssPublishButton);
			PageObjectLogging.log("clickCssPublishButton", "click on publish button", true);
		}

		public void clickPublishButtonDropdown() {
			clickAndWait(cssPublishButtonDropdown);
			PageObjectLogging.log("clickCssPublishButton", "click on publish button dropdown", true);
		}

		public void clickMinorCheckbox() {
			clickAndWait(minorEdit);
			PageObjectLogging.log("minorEdit", "click on minor edit checkbox dropdown", true);
		}

		public void clickShowChanges() {
			clickAndWait(showChanges);
			PageObjectLogging.log("showChanges", "click on show changes from dropdown", true);
		}

		public void showModalChanges() {
			clickAndWait(wikiDiff);
			PageObjectLogging.log("wikiDiff", "modal with changes is displayed", true);
		}

		public void verifySaveComplete() {
			waitForElementByElement(notificationConfirm);
			PageObjectLogging.log("notificationConfirm", "css content saved", true);
		}

		public String generateRandomString() {
			char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
			Random random = new Random();
			StringBuilder randomText = new StringBuilder();
			for(int i = 0; i < 30; i++) {
				char c = chars[random.nextInt(chars.length)];
				randomText.append(c);
			}
			return randomText.toString();
		}
		
		public void clickHistoryButton() {
			clickAndWait(historyButton);
			PageObjectLogging.log("historyButton", "click on history button", true);			
		}
		
		public void clickDeleteButton() {
			clickAndWait(deleteButton);
			verifyUrl("action=delete");
			PageObjectLogging.log("deleteButton", "click on delete button", true);
		}
		
		public void confirmDelete() {
			clickArticleDeleteConfirmationButton("MediaWiki:Wikia.css");
		}
		
		public void verifyArticleIsRemoved() {
			waitForElementByBy(removedWarning);
		}
		
		public void verifyArticleIsNotRemoved() {
			waitForElementNotPresent(removedWarning);
		}
		
		public void clickUndeleteButton() {
			clickAndWait(undeleteButton);
			verifyUrl("Special:Undelete?target=MediaWiki%3AWikia.css");
			PageObjectLogging.log("undeleteButton", "click on undelete button", true);			
		}
		
		public void confirmUndelete() {
			clickRestoreArticleButton();
		}
}
