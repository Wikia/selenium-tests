package com.wikia.webdriver.common.core.drivers.Browsers;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.drivers.BrowserAbstract;

public class HtmlUnitBrowser extends BrowserAbstract{

  @Override
  public WikiaWebDriver setInstance(){
    return new WikiaWebDriver(new HtmlUnitDriver(), false);
  }
}
