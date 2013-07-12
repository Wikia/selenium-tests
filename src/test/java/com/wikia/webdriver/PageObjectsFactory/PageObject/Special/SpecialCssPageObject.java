package com.wikia.webdriver.PageObjectsFactory.PageObject.Special;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

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
		@FindBy(css = ".css-edit-box .wikia-menu-button .drop")
		private WebElement cssPublishButtonDropdown;
		@FindBy(css = "#editSummary")
		private WebElement editSummaryField;
		@FindBy(css = "#minorEdit")
		private WebElement minorEdit;
		@FindBy(css = "#showChanges")
		private WebElement showChanges;
		@FindBy(css = "#wikiDiff")
		private WebElement changesModal;
		@FindBy(css = "#wikiDiff .diff-addedline > div")
		private WebElement changesModalAddedLine;
		@FindBy(css = ".global-notification.confirm")
		private WebElement notificationConfirm;
		@FindBy(css = ".css-side-bar .wikia-menu-button .WikiaMenuElement a[href*=\"action=history\"]")
		private WebElement historyButton;
		@FindBy(css = ".css-side-bar .wikia-menu-button .WikiaMenuElement a[href*=\"action=delete\"]")
		private WebElement deleteButton;
		@FindBy(css = "#wikiDiff")
		private WebElement conflictArea;
		@FindBy(css = "td.diff-otitle")
		private WebElement oRevisionTitle;
		//@FindBy(css = ".css-editor .mw-warning-with-logexcerpt")
		//private WebElement removedWarning;
		@FindBy(css = ".css-side-bar .wikia-menu-button .WikiaMenuElement a[href*=\"Special:Undelete\"]")
		private WebElement undeleteButton;
		private By removedWarning = By.cssSelector(".css-editor .mw-warning-with-logexcerpt");
		@FindBy(css = ".css-edit-box a.talk .commentsbubble")
		private WebElement talkBubble;
		@FindBy(css = ".css-edit-box a.talk")
		private WebElement talkLink;
		@FindBy(css = "#WikiaMainContentContainer a.talk .commentsbubble")
		private WebElement mwTalkBubble;
		
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
			sendCssText(randomText);
			clickPublishButton();
			wiki.verifyUrl(URLsContent.specialCSS);
			verifySaveComplete();
		}

		public void clearCssText() {
			waitForElementByElement(aceLayerTextArea);
			executeScript("ace.edit('cssEditorContainer').setValue('');");
			PageObjectLogging.log("clearCssText", "ace editor was cleared", true, driver);
		}

		public void insertCssText(String cssText) {
			waitForElementByElement(aceLayerTextArea);
			executeScript("ace.edit('cssEditorContainer').navigateFileEnd();");
			sendCssText(cssText);
			PageObjectLogging.log("sendAceCssText", "the following text was send to ace editor: "+cssText, true);
		}

		public void sendEditSummaryText(String summaryText) {
			waitForElementByElement(editSummaryField);
			editSummaryField.sendKeys(summaryText);
			PageObjectLogging.log("editSummaryField", "the following text was send to ace editor: "+summaryText, true);
		}

		public void sendCssText(String cssText) {
			waitForElementByElement(aceLayerTextArea);
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
			scrollAndClick(cssPublishButton);
			PageObjectLogging.log("clickCssPublishButton", "click on publish button", true);
		}

		public void clickPublishButtonDropdown() {
			scrollAndClick(cssPublishButtonDropdown);
			PageObjectLogging.log("clickCssPublishButton", "click on publish button dropdown", true);
		}

		public void clickMinorCheckbox() {
			scrollAndClick(minorEdit);
			PageObjectLogging.log("minorEdit", "click on minor edit checkbox dropdown", true);
		}

		public void clickShowChanges() {
			scrollAndClick(showChanges);
			PageObjectLogging.log("showChanges", "click on show changes from dropdown", true);
		}

		public void showChangesModal() {
			scrollAndClick(changesModal);
			PageObjectLogging.log("changesModal", "modal with changes is displayed", true);
		}

		public void verifySaveComplete() {
			waitForElementByElement(notificationConfirm);
			PageObjectLogging.log("notificationConfirm", "css content saved", true);
		}

		public String getAddedLineText() {
			waitForElementByElement(changesModalAddedLine);
			String addedLine = changesModalAddedLine.getText();
			PageObjectLogging.log("changesModalAddedLine", "get added line content", true);
			return addedLine;
		}

		public void clickHistoryButton() {
			scrollAndClick(historyButton);
			PageObjectLogging.log("historyButton", "click on history button", true);
		}

		public void clickDeleteButton() {
			scrollAndClick(deleteButton);
			verifyUrl("action=delete");
			PageObjectLogging.log("deleteButton", "click on delete button", true);
		}

		public void confirmDelete() {
			clickArticleDeleteConfirmationButton(URLsContent.mediaWikiCss);
		}

		public void verifyArticleIsRemoved() {
			waitForElementByBy(removedWarning);
		}

		public void verifyArticleIsNotRemoved() {
			waitForElementNotPresent(removedWarning);
		}

		public void clickUndeleteButton() {
			scrollAndClick(undeleteButton);
			try {
				verifyUrl("Special:Undelete?target=" + URLEncoder.encode(URLsContent.mediaWikiCss, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// this should never happen
			}
			PageObjectLogging.log("undeleteButton", "click on undelete button", true);
		}

		public void confirmUndelete() {
			clickRestoreArticleButton();
		}

		public void verifyConflictArea() {
			waitForElementByElement(cssPublishButton);
			PageObjectLogging.log("verifyConflictArea", "verify that conflict area is present", true);
		}

		public void verifyLatestRevision() {
			waitForElementByElement(oRevisionTitle);
			waitForTextToBePresentInElementByElement(oRevisionTitle, "Latest revision");
			PageObjectLogging.log("verifyLatestRevision", "verify that latest revision is shown", true);
		}

		public void navigateToHistoryPage() {
			getUrl(Global.DOMAIN+"wiki/MediaWiki:Wikia.css?action=edit");
		}
		
		public void verifyTalkBubblePresence() {
			waitForElementByElement(talkBubble);
			PageObjectLogging.log("verifyTalkBubblePresence", "Talk bubble is present.", true);
		}
		
		/**
		 * Return the number of comments from talk button bubble
		 */
		public int getNumberFromCssTalkBubble() {
			return Integer.parseInt(talkBubble.getText());
		}
		
		public void clickTalkButton() {
			scrollAndClick(talkLink);
			verifyUrl("/MediaWiki_talk:Wikia.css");
		}
		
		/**
		 * go to mediawiki:wikia.css and return the number of comments from talk button bubble
		 */
		public int getNumberFromWikaiCssTalkBubble() {
			getUrl(Global.DOMAIN+"wiki/MediaWiki:Wikia.css");
			waitForElementByElement(mwTalkBubble);
			return Integer.parseInt(mwTalkBubble.getText());
		}
		
}
