package com.wikia.webdriver.elements.fandom;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

public abstract class FandomPage<T> extends BasePageObject {

  public static final String SITE_URL = "http://sandbox-qa.fandom.wikia.com/";
  public static final String WWW_SITE_URL = "http://www.wikia.com/fandom/";

  public abstract T open();
}
