package com.wikia.webdriver.elements.mercury.components.discussions.common;


public interface Editor {

  boolean isSubmitButtonActive();
  Editor clickSubmitButton();
  Editor clickCancelButton();
  Editor addTextWith(final String text);
  Editor clearText();

}
