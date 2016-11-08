package com.wikia.webdriver.elements.fandom;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

public abstract class FandomPage<T> extends BasePageObject {

  public static final String SITE_URL = "http://qa.fandom.wikia.com/";

  public abstract T open();
}
