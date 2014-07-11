package com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;

/**
 * @author Robert 'rochan' Chan
 */
public class VisualEditorReviewChangesDialog extends WikiBasePageObject {

	@FindBy(css=".oo-ui-window-ready .oo-ui-frame")
	private WebElement reviewDialogIFrame;
	@FindBy(css=".secondary .oo-ui-labeledElement-label")
	private WebElement reviewChangesButton;
	@FindBy(css=
		".oo-ui-window-foot " +
		"div:not(.oo-ui-flaggableElement-secondary):not(.oo-ui-flaggableElement-constructive) " +
		".oo-ui-labeledElement-label")
	private WebElement returnToSaveFormButton;
	@FindBy(css=".oo-ui-window-head .oo-ui-icon-close")
	private WebElement closeButton;
	@FindBy(css=".ve-ui-mwSaveDialog-viewer.WikiaArticle")
	private WebElement wikiaArticle;
	@FindBy(css=".ve-ui-mwSaveDialog-viewer.WikiaArticle pre")
	private WebElement wikiaAritlceFirstPreview;
	@FindBy(css=".ve-ui-mwSaveDialog-viewer.WikiaArticle")
	private WebElement wikiaArticleDiffTable;
	@FindBy(css=".diff-addedline")
	private List<WebElement> addedLines;
	@FindBy(css=".diff-deletedline")
	private List<WebElement> deletedLines;

	private final int DELETE = 0;
	private final int INSERT = 1;

	public VisualEditorReviewChangesDialog(WebDriver driver) {
		super(driver);
	}

	public VisualEditorSaveChangesDialog clickReturnToSaveFormButton() {
		waitForElementVisibleByElement(reviewDialogIFrame);
		driver.switchTo().frame(reviewDialogIFrame);
		waitForElementClickableByElement(returnToSaveFormButton);
		returnToSaveFormButton.click();
		PageObjectLogging.log("clickReturnToSaveFormButton", "Return To Save Form button clicked", true);
		waitForElementNotVisibleByElement(reviewDialogIFrame);
		driver.switchTo().defaultContent();
		return new VisualEditorSaveChangesDialog(driver);
	}

	public VisualEditorPageObject clickCloseButton() {
		waitForElementVisibleByElement(reviewDialogIFrame);
		driver.switchTo().frame(reviewDialogIFrame);
		waitForElementClickableByElement(closeButton);
		closeButton.click();
		PageObjectLogging.log("clickCloseButton", "Close button clicked", true);
		waitForElementNotVisibleByElement(reviewDialogIFrame);
		driver.switchTo().defaultContent();
		return new VisualEditorPageObject(driver);
	}

	public void verifyDeletedDiffs(ArrayList<String> targets) {
		waitForElementVisibleByElement(reviewDialogIFrame);
		driver.switchTo().frame(reviewDialogIFrame);
		verifyArticleDiffs(targets, DELETE);
		waitForElementNotVisibleByElement(reviewDialogIFrame);
		driver.switchTo().defaultContent();
	}

	public void verifyAddedDiffs(ArrayList<String> targets) {
		waitForElementVisibleByElement(reviewDialogIFrame);
		driver.switchTo().frame(reviewDialogIFrame);
		if (checkIfElementOnPage(wikiaAritlceFirstPreview)) {
			verifyNewArticleDiffs(targets);
		} else {
			verifyArticleDiffs(targets, INSERT);
		}
		waitForElementNotVisibleByElement(reviewDialogIFrame);
		driver.switchTo().defaultContent();
	}

	private void verifyArticleDiffs(ArrayList<String> targets, int mode) {
		int count = 0;
		int expectedCount = 0;
		List<WebElement> lines = null;
		if (mode == DELETE) {
			expectedCount = deletedLines.size();
			lines = deletedLines;
		} else if (mode == INSERT) {
			expectedCount = addedLines.size();
			lines = addedLines;
		}
		for (WebElement current : lines) {
			String currentText = current.getText();
			if (!currentText.isEmpty()) {
				if(foundAddedDiff(targets, currentText)) {
					targets.remove(currentText);
					count++;
				}
			} else {
				expectedCount--;
			}
		}
		Assertion.assertNumber(expectedCount, count, "Number of diffs expected is incorrect.");
		if (mode == INSERT) {
			Assertion.assertNumber(0, targets.size(), "Number of diffs expected is incorrect.");
		}
	}

	private void verifyNewArticleDiffs(ArrayList<String> targets) {
		String wikiText = wikiaAritlceFirstPreview.getText();
		for (String target : targets) {
			Assertion.assertStringContains(wikiText, target);
		}
	}

	private boolean foundAddedDiff(ArrayList<String> targets, String source) {
		for (String target : targets) {
			if (source.contains(target))
				return true;
		}
		return false;
	}
}
