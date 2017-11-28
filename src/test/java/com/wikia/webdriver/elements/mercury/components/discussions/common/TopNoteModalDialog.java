package com.wikia.webdriver.elements.mercury.components.discussions.common;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * This class should be used only for post details page. On this page only post can be approved or deleted, thus
 * only on this page approve and remove modal dialogs will appear.
 */
public class TopNoteModalDialog extends ConfirmationDialog {

  @FindBy(className = "post-detail")
  private WebElement post;

  public void clickApprove() {
    super.clickConfirm();
    new WebDriverWait(driver, DiscussionsConstants.TIMEOUT).until(
      (Function<WebDriver, Boolean>) input -> !post.getAttribute("class").contains("is-reported")
    );
  }
}
