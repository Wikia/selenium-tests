package com.wikia.webdriver.elements.communities.mobile.pages;

import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.communities.mobile.components.Category;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

public class MainPage extends WikiBasePageObject {

  private Navigate navigate;

  public MainPage() {
    super();

    navigate = new Navigate();
  }

  public MainPage open() {
    navigate.toPageByPath("/");

    return this;
  }

  public MainPage openRegularMainPage() {
    navigate.toPageByPath("/wiki/Mercury_automation_testing_Wiki");

    return this;
  }

  public Category useCategoryComponent() {
    return new Category();
  }
}
