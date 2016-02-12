package com.wikia.webdriver.common.core.drivers.browsers;

import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.drivers.BrowserAbstract;
import com.wikia.webdriver.common.core.drivers.BrowserType;

/**
 * Created by Ludwik on 2016-02-06.
 */
public class DefaultBrowser extends BrowserAbstract {

  @Override
  public WikiaWebDriver setInstance(){
    return BrowserType.lookup(Configuration.getBrowser()).setInstance();
  }
}
