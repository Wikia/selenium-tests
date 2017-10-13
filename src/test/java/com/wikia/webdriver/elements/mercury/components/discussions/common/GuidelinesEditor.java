package com.wikia.webdriver.elements.mercury.components.discussions.common;


import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

public class GuidelinesEditor extends BasePageObject implements Editor {

  @Override
  public boolean isSubmitButtonActive() {
    return isElementEnabled(getSubmitButton());
  }

  @Override
  public Editor clickSubmitButton() {
    waitAndClick(getSubmitButton());
    return this;
  }

  @Override
  public Editor clickCancelButton() {
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
