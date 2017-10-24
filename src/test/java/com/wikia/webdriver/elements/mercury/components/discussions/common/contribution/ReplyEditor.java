package com.wikia.webdriver.elements.mercury.components.discussions.common.contribution;

import org.openqa.selenium.WebElement;

/**
 * ReplyEditor is a standalone editor used on both: desktop and mobile views
 */
public class ReplyEditor extends ContributionEditor implements StandaloneEditor {

  @Override
  protected WebElement getPostsCreator() {
    return null;
  }

  @Override
  protected WebElement getEditor() {
    return null;
  }

  @Override
  public WebElement getSubmitButton() {
    return null;
  }

  @Override
  public WebElement getCancelButton() {
    return null;
  }

  @Override
  public StandaloneEditor clickCancelButton() {
    return null;
  }
}
