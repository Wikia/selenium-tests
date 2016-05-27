package com.wikia.webdriver.elements.mercury.pages;

import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.components.Category;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

public class MainPage extends WikiBasePageObject {

  private Navigate navigate;

  public MainPage() {
    super();

    navigate = new Navigate();
  }

  public MainPage open() {
    navigate.toPage("/");

    return this;
  }

  public MainPage openRegularMainPage() {
    navigate.toPage("/wiki/Mercury_automation_testing_Wikia");

    return this;
  }

  public Category useCategoryComponent() {
    return new Category(driver);
  }
}
