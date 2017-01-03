package com.wikia.webdriver.elements.mercury.components.discussions.common;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInToFollowModalDialog extends BasePageObject {

  private static final String DIALOG_CSS_CLASS = "discussion-dialog";

  private static final String POSTING_NOT_ALLOWED_CLASS = "modal-dialog-posting-not-allowed";

  private static final String SELECTOR_PREFIX = "." + DIALOG_CSS_CLASS + ".is-visible." + POSTING_NOT_ALLOWED_CLASS + " ";

  public static final String FOLLOW_DISCUSSION_TEXT = "Sign in to start following the discussion!";

  @FindBy(css = SELECTOR_PREFIX + ".modal-dialog-content")
  private WebElement content;

  @FindBy(css = SELECTOR_PREFIX + ".confirm-button")
  private WebElement okButton;

  @FindBy(css = SELECTOR_PREFIX + ".signin-button")
  private WebElement signInButton;

  public boolean isVisible() {
    return driver.findElements(By.className(DIALOG_CSS_CLASS)).stream()
        .anyMatch(element -> element.getAttribute("class").contains(POSTING_NOT_ALLOWED_CLASS));
  }

  public String getText() {
    return content.getText();
  }

  public void clickOkButton() {
    okButton.click();
  }

  public void clickSignInButton() {
    signInButton.click();
  }
}
