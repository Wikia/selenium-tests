package com.wikia.webdriver.common.core.drivers.Browsers;

import com.wikia.webdriver.common.core.drivers.BrowserAbstract;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Created by Ludwik on 2016-02-05.
 */
public class HtmlUnitBrowser extends BrowserAbstract{

  @Override
  public EventFiringWebDriver getInstance() {
    return new EventFiringWebDriver(new HtmlUnitDriver());
  }
}
