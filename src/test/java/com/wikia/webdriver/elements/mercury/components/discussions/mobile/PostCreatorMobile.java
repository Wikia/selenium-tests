package com.wikia.webdriver.elements.mercury.components.discussions.mobile;

import com.wikia.webdriver.elements.mercury.components.discussions.common.contribution.ContributionEditor;
import com.wikia.webdriver.elements.mercury.components.discussions.common.contribution.StandaloneEditor;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostCreatorMobile extends ContributionEditor implements StandaloneEditor {

  @Getter
  @FindBy(css = ".editor-close")
  private WebElement cancelButton;

  @FindBy(css = ".discussion-standalone-content-wrapper")
  @Getter
  private WebElement editor;

  @Getter
  @FindBy(css = ".discussion-standalone-editor-save-button")
  private WebElement submitButton;

  @Override
  protected WebElement getPostsCreator() {
    return null;
  }

  @Override
  public PostCreatorMobile clickCancelButton() {
    waitAndClick(getCancelButton());
    return this;
  }
}
