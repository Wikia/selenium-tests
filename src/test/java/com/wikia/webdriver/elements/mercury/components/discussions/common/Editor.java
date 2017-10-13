package com.wikia.webdriver.elements.mercury.components.discussions.common;


import org.openqa.selenium.WebElement;

public interface Editor {

  boolean isSubmitButtonActive();
  Editor clickSubmitButton();
  Editor clickCancelButton();
  Editor addTextWith(final String text);
  Editor clearText();
  WebElement getTextArea();
  WebElement getCancelButton();
  WebElement getSubmitButton();
}
