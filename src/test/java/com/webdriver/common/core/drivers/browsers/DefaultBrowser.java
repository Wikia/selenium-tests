package com.webdriver.common.core.drivers.browsers;

import com.webdriver.common.core.PageWebDriver;
import com.webdriver.common.core.configuration.Configuration;
import com.webdriver.common.core.drivers.BrowserAbstract;
import com.webdriver.common.core.drivers.Browser;
import com.webdriver.common.logging.PageObjectLogging;

public class DefaultBrowser extends BrowserAbstract {

  private BrowserAbstract browserClass;

  DefaultBrowser() {
    try {
      browserClass = Browser.lookup(Configuration.getBrowser()).getBrowserClass().newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      PageObjectLogging.logError("Could not initialize the browser", e);
    }
  }

  @Override
  public void setOptions() {
    browserClass.setOptions();
  }

  @Override
  public PageWebDriver create() {
    return browserClass.create();
  }

  @Override
  public void addExtension(String extensionName) {
    browserClass.addExtension(extensionName);
  }
}
