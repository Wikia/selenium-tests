package com.wikia.webdriver.elements.mercury.components.discussions.common.contribution;


import org.openqa.selenium.WebElement;

public interface Editor {
  boolean isSubmitButtonActive();
  Editor clickSubmitButton();
  Editor addTextWith(final String text);
  Editor clearText();
  WebElement getTextArea();
  WebElement getSubmitButton();
}
