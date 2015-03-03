package com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @author Robert 'rochan' Chan
 */
public class VisualEditorReviewChangesDialog extends VisualEditorDialog {

  @FindBy(css = ".oo-ui-processDialog-actions-primary .oo-ui-labelElement-label")
  private WebElement returnToSaveFormButton;
  @FindBy(css = ".ve-ui-mwSaveDialog-viewer pre")
  private WebElement wikiaAritlceFirstPreview;
  @FindBy(css = ".diff-addedline")
  private List<WebElement> addedLines;
  @FindBy(css = ".diff-deletedline")
  private List<WebElement> deletedLines;

  private static final String DIFF_LINE_STRING = ".diffchange-inline";

  private static final int DELETE = 0;
  private static final int INSERT = 1;

  public VisualEditorReviewChangesDialog(WebDriver driver) {
    super(driver);
  }

  public VisualEditorSaveChangesDialog clickReturnToSaveFormButton() {
    waitForElementClickableByElement(returnToSaveFormButton);
    returnToSaveFormButton.click();
    PageObjectLogging
        .log("clickReturnToSaveFormButton", "Return To Save Form button clicked", true);
    return new VisualEditorSaveChangesDialog(driver);
  }

  public void verifyDeletedDiffs(List<String> targets) {
    verifyArticleDiffs(targets, DELETE);
  }

  public void verifyAddedDiffs(List<String> targets) {
    if (checkIfElementOnPage(wikiaAritlceFirstPreview)) {
      verifyNewArticleDiffs(targets);
    } else {
      verifyArticleDiffs(targets, INSERT);
    }
  }

  private void verifyArticleDiffs(List<String> targets, int mode) {
    int count = 0;
    int expectedCount = 0;
    List<WebElement> diffLines = null;
    if (mode == DELETE) {
      expectedCount = deletedLines.size();
      diffLines = deletedLines;
    } else if (mode == INSERT) {
      expectedCount = addedLines.size();
      diffLines = addedLines;
    }
    for (WebElement currentDiff : diffLines) {
      String currentText;
      //Check to see if the current diff line has inline diff
      if (checkIfElementInElement(DIFF_LINE_STRING, currentDiff)) {
        List<WebElement> inlineDiffs = currentDiff.findElements(By.cssSelector(DIFF_LINE_STRING));
        //iterate through multiple inline diffs
        for (WebElement currentInlineDiff : inlineDiffs) {
          String currentInlineText = currentInlineDiff.getText();
          if (isDiffFound(targets, currentInlineText)) {
            targets.remove(currentInlineText);
            count++;
          }
        }
      } else {
        currentText = currentDiff.getText();
        if (currentText.isEmpty()) {
          expectedCount--;
        } else {
          if (isDiffFound(targets, currentText)) {
            targets.remove(currentText);
            count++;
          }
        }
      }
    }
    Assertion.assertNumber(expectedCount, count, "Number of diffs.");
    if (mode == INSERT) {
      Assertion.assertNumber(0, targets.size(), "Number of diffs.");
    }
  }

  private void verifyNewArticleDiffs(List<String> targets) {
    String wikiText = wikiaAritlceFirstPreview.getText();
    for (String target : targets) {
      verifyNewArticleDiff(target, wikiText);
    }
  }

  private void verifyNewArticleDiff(String target, String source) {
    Assertion.assertStringContains(target, source);
  }

  private boolean isDiffFound(List<String> targets, String source) {
    for (String target : targets) {
      if (source.contains(target)) {
        return true;
      }
    }
    return false;
  }
}
