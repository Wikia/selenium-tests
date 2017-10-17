package com.wikia.webdriver.elements.mercury.components.discussions.desktop;

import com.wikia.webdriver.elements.mercury.components.discussions.common.contribution.ContributionEditor;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostCreatorDesktop extends ContributionEditor {

  @Getter
  @FindBy(className = "discussion-inline-editor-submit")
  private WebElement submitButton;

  @Getter
  @FindBy(className = "TODO: fixme")
  private WebElement postsCreator;

  @Getter
  @FindBy(className = "discussion-inline-editor")
  private WebElement editor;

}
