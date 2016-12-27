package com.wikia.webdriver.elements.mercury.components.discussions.common;


import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ConfirmationDialog extends BasePageObject {

  private WebElement getActiveDialog() {
    WebElement dialog = null;

    List<WebElement> elements = driver.findElements(By.cssSelector(".discussion-dialog.is-visible"));
    if (elements.isEmpty()) {
      // allow selenium to throw exception
      dialog = driver.findElement(By.cssSelector(".discussion-dialog.is-visible.modal-dialog-approve"));
    } else if (1 < elements.size()) {
      // only one modal dialog should be visible
      throw new IllegalStateException("Only one modal dialog should be visible!");
    } else {
      dialog = elements.get(0);
    }

    return dialog;
  }

  public void clickCancel() {
    getActiveDialog().findElement(By.className("cancel-button")).click();
  }

  public void clickConfirm() {
    getActiveDialog().findElement(By.className("confirm-button")).click();
  }

}
