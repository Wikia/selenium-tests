package com.wikia.webdriver.common.core.drivers.browsers;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.drivers.BrowserAbstract;

public class HtmlUnitBrowser extends BrowserAbstract {

  @Override
  public void setOptions() {

  }

  @Override
  public WikiaWebDriver create() {
    return new WikiaWebDriver(new HtmlUnitDriver(), false);
  }

  @Override
  public void addExtension(String extensionName) {

  }
}
