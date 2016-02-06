package com.wikia.webdriver.common.core.drivers.Browsers;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.drivers.BrowserAbstract;
import com.wikia.webdriver.common.core.drivers.BrowserType;

import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Created by Ludwik on 2016-02-06.
 */
public class DefaultBrowser extends BrowserAbstract {

  @Override
  public EventFiringWebDriver getInstance() {
    return BrowserType.lookup(Configuration.getBrowser()).getInstance();
  }
}
