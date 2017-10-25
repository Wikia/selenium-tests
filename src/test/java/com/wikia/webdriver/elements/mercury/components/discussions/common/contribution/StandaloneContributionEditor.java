package com.wikia.webdriver.elements.mercury.components.discussions.common.contribution;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StandaloneContributionEditor extends ContributionEditor implements StandaloneEditor {

  @Getter
  @FindBy(css = ".editor-close")
  private WebElement cancelButton;

  @Getter
  @FindBy (className = "discussion-standalone-editor")
  private WebElement editor;

  @Getter
  @FindBy(css = ".discussion-standalone-editor-save-button")
  private WebElement submitButton;

  @Override
  public StandaloneContributionEditor clickCancelButton() {
    waitAndClick(getCancelButton());
    return this;
  }

  @Override
  protected WebElement getPostsCreator() {
    // TODO: remove this method
    return null;
  }
}
