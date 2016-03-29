package com.wikia.webdriver.elements.mercury.pages;

import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

public class MainPage extends WikiBasePageObject {

  public MainPage() {
    super();
  }

  public MainPage open() {
    new Navigate(driver).toPage("/");

    return this;
  }
}
