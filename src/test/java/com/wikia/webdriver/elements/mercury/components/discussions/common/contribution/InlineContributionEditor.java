package com.wikia.webdriver.elements.mercury.components.discussions.common.contribution;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InlineContributionEditor extends ContributionEditor {

  @Getter
  @FindBy(className = "discussion-inline-editor-submit")
  private WebElement submitButton;

  @Getter
  @FindBy(className = "discussion-inline-editor")
  private WebElement editor;

}
