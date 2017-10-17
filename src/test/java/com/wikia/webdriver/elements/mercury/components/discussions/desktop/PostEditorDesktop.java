package com.wikia.webdriver.elements.mercury.components.discussions.desktop;

import com.wikia.webdriver.elements.mercury.components.discussions.common.contribution.ContributionEditor;
import com.wikia.webdriver.elements.mercury.components.discussions.common.contribution.StandaloneEditor;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class PostEditorDesktop extends ContributionEditor implements StandaloneEditor {

  @Getter
  @FindBy(css = ".discussion-inline-editor-textarea-wrapper .discussion-textarea-wrapper")
  private WebElement postsCreator;

  @Getter
  @FindBy(css = ".discussion-inline-editor")
  private WebElement editor;

  @Getter
  @FindBy(css = ".discussion-inline-editor-submit")
  private WebElement submitButton;

  @Getter
  @FindBy(css = ".editor-close")
  private WebElement cancelButton;

  @Getter
  @FindBy(css = "#categoryPickerButtonDesktop")
  private WebElement addCategoryButton;

  @Override
  public boolean isExpanded() {
    return editor.getAttribute("class").contains("is-active");
  }

  @Override
  public boolean isSticky() {
    return editor.getAttribute("class").contains("is-sticky");
  }

  @Override
  public PostEditorDesktop clickCancelButton() {
    waitAndClick(getCancelButton());
    return this;
  }
}
