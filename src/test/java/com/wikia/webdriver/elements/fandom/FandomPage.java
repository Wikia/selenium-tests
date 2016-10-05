package com.wikia.webdriver.elements.fandom;

import com.wikia.webdriver.elements.fandom.components.Navigation;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import lombok.Getter;

public abstract class FandomPage<T> extends BasePageObject {

  @Getter(lazy = true)
  private final Navigation navigation = new Navigation();

  public abstract T open();
}
