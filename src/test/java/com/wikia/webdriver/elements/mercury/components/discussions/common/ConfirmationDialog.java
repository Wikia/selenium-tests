package com.wikia.webdriver.elements.mercury.components.discussions.common;


import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ConfirmationDialog extends BasePageObject {

  private WebElement getActiveDialog() {

    List<WebElement> elements = driver.findElements(By.cssSelector(".discussion-dialog.is-visible"));
    if (elements.isEmpty()) {
      // allow selenium to throw exception
      return driver.findElement(By.cssSelector(".discussion-dialog.is-visible.modal-dialog-approve"));
    } else if (1 < elements.size()) {
      // only one modal dialog should be visible
      throw new IllegalStateException("Only one modal dialog should be visible!");
    } else {
      return elements.get(0);
    }
  }

  public boolean isVisible() {
    return getActiveDialog().isDisplayed();
  }

  public void clickCancel() {
    getActiveDialog().findElement(By.className("cancel-button")).click();
  }

  public void clickConfirm() {
    getActiveDialog().findElement(By.className("confirm-button")).click();
  }

}
