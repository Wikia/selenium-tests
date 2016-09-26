package com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs;

import com.wikia.webdriver.common.core.Assertion;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class VisualEditorReviewChangesDialog extends VisualEditorDialog {

  @FindBy(css = ".oo-ui-processDialog-actions-primary .oo-ui-labelElement-label")
  private WebElement returnToSaveFormButton;
  @FindBy(css = ".ve-ui-mwSaveDialog-viewer")
  private WebElement wikiaArticleReviewDialog;
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
    wait.forElementClickable(returnToSaveFormButton);
    returnToSaveFormButton.click();
    return new VisualEditorSaveChangesDialog(driver);
  }

  public void verifyDeletedDiffs(List<String> targets) {
    verifyArticleDiffs(targets, DELETE);
  }

  public void verifyAddedDiffs(List<String> targets) {
    waitForDialogVisible();
    wait.forElementVisible(wikiaArticleReviewDialog);
    if (isElementOnPage(wikiaAritlceFirstPreview)) {
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
      if (isElementInContext(DIFF_LINE_STRING, currentDiff)) {
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
    Assertion.assertNumber(count, expectedCount, "Number of diffs.");
    if (mode == INSERT) {
      Assertion.assertNumber(targets.size(), 0, "Number of diffs.");
    }
  }

  private void verifyNewArticleDiffs(List<String> targets) {
    String wikiText = wikiaAritlceFirstPreview.getText();
    for (String target : targets) {
      Assertion.assertStringContains(wikiText, target);
    }
  }

  private boolean isDiffFound(List<String> targets, String source) {
    for (String target : targets) {
      if (source.equals(target)) {
        return true;
      }
    }
    return false;
  }
}
