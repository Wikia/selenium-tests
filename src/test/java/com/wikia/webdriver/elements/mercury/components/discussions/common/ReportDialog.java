package com.wikia.webdriver.elements.mercury.components.discussions.common;

import org.openqa.selenium.WebElement;

public class ReportDialog extends ConfirmationDialog {

  public void confirmAndWait() {
    WebElement dialog = super.getActiveDialog();
    super.clickConfirm();
    waitForElementNotVisibleByElement(dialog);
  }

  public void cancelAndWait() {
    WebElement dialog = super.getActiveDialog();
    super.clickCancel();
    waitForElementNotVisibleByElement(dialog);
  }
}
