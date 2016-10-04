package com.wikia.webdriver.elements.mercury.components.discussions.common;

import com.google.common.base.Predicate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;

public class TopNoteModalDialog extends BasePageObject {

  private final long TIMEOUT = 10;

  @FindBy(css = ".discussion-dialog.is-visible.modal-dialog-approve")
  private WebElement modalDialogApprove;

  @FindBy(css = ".discussion-dialog.is-visible.modal-dialog-delete")
  private WebElement modalDialogDelete;

  @FindBy(className = "post-detail")
  private WebElement post;

  public void clickCancel() {
    getActiveDialog().findElement(By.className("cancel-button")).click();
  }

  private WebElement getActiveDialog() {
    return null == modalDialogApprove ? modalDialogDelete : modalDialogApprove;
  }

  public void clickApprove() {
    getActiveDialog().findElement(By.className("confirm-button")).click();
    new WebDriverWait(driver, TIMEOUT).until(new Predicate<WebDriver>() {
      @Override
      public boolean apply(@Nullable WebDriver input) {
        return !post.getAttribute("class").contains("is-reported");
      }
    });
  }
}
