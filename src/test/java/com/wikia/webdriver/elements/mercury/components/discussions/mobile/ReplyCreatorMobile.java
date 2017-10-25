package com.wikia.webdriver.elements.mercury.components.discussions.mobile;

import com.wikia.webdriver.elements.mercury.components.discussions.common.contribution.ContributionEditor;
import com.wikia.webdriver.elements.mercury.components.discussions.common.contribution.StandaloneEditor;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class ReplyCreatorMobile extends ContributionEditor implements StandaloneEditor {

  @FindBy(css = ".discussion-standalone-content-wrapper")
  @Getter
  private WebElement editor;

  @Getter
  @FindBy(css = ".editor-close")
  private WebElement cancelButton;

  @Getter
  @FindBy(css = ".discussion-standalone-editor-save-button")
  private WebElement submitButton;


  public boolean isPresent() {
    return !driver.findElements(By.className("discussion-editor-entry-point-container")).isEmpty();
  }

  @Override
  protected WebElement getPostsCreator() {
    // TODO: remove this implementation when abstract method is moved
    return null;
  }

  @Override
  public ReplyCreatorMobile clickCancelButton() {
    waitAndClick(getCancelButton());
    return this;
  }

}
