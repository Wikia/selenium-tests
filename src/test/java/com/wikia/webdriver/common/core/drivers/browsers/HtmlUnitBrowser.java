package com.wikia.webdriver.common.core.drivers.browsers;

import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.drivers.BrowserAbstract;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class HtmlUnitBrowser extends BrowserAbstract {

  @Override
  public void setOptions() {
    // Here you should put options to set before browser instance creation
  }

  @Override
  public WikiaWebDriver create() {
    return new WikiaWebDriver(new HtmlUnitDriver(), false);
  }

  @Override
  public void addExtension(String extensionName) {
    // No extensions are applied to HtmlUnit browser
  }
}
