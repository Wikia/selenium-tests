package com.webdriver.common.core.drivers.browsers;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.webdriver.common.core.PageWebDriver;
import com.webdriver.common.core.drivers.BrowserAbstract;

public class HtmlUnitBrowser extends BrowserAbstract {

  @Override
  public void setOptions() {
    // Here you should put options to set before browser instance creation
  }

  @Override
  public PageWebDriver create() {
    return new PageWebDriver(new HtmlUnitDriver(), false);
  }

  @Override
  public void addExtension(String extensionName) {
    // No extensions are applied to HtmlUnit browser
  }
}
