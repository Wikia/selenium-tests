package com.wikia.webdriver.elements.mercury.components.discussions.common.contribution;


import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GuidelinesEditor extends BasePageObject implements Editor, StandaloneEditor {

  @Getter
  @FindBy(css = ".discussion-standalone-editor-save-button")
  private WebElement submitButton;

  @Getter
  @FindBy(css = ".editor-close")
  private WebElement cancelButton;

  @Getter
  @FindBy(css = ".discussion-standalone-editor-textarea")
  private WebElement textArea;

  @Override
  public boolean isSubmitButtonActive() {
    return isElementEnabled(getSubmitButton());
  }

  @Override
  public GuidelinesEditor clickSubmitButton() {
    waitAndClick(getSubmitButton());
    return this;
  }

  @Override
  public GuidelinesEditor clickCancelButton() {
    waitAndClick(getCancelButton());
    return this;
  }

  @Override
  public Editor addTextWith(String text) {
    clearText();
    getTextArea().sendKeys(text);
    clickSubmitButton();
    waitForPageReload();
    return this;
  }

  @Override
  public Editor clearText() {
    getTextArea().clear();
    return this;
  }

}
