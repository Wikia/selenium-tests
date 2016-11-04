package com.wikia.webdriver.elements.fandom;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

public abstract class FandomPage<T> extends BasePageObject {

  public abstract T open();

  public static final String SITE_URL = "http://qa.fandom.wikia.com/";
}
