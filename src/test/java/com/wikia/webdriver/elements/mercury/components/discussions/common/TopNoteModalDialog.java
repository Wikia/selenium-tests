package com.wikia.webdriver.elements.mercury.components.discussions.common;

import com.google.common.base.Predicate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;
import java.util.List;

/**
 * This class should be used only for post details page. On this page only post can be approved or deleted, thus
 * only on this page approve and remove modal dialogs will appear.
 */
public class TopNoteModalDialog extends BasePageObject {

  @FindBy(className = "post-detail")
  private WebElement post;

  public void clickCancel() {
    getActiveDialog().findElement(By.className("cancel-button")).click();
  }

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

  public void clickApprove() {
    getActiveDialog().findElement(By.className("confirm-button")).click();
    new WebDriverWait(driver, DiscussionsConstants.TIMEOUT).until(new Predicate<WebDriver>() {
      @Override
      public boolean apply(@Nullable WebDriver input) {
        return !post.getAttribute("class").contains("is-reported");
      }
    });
  }
}
