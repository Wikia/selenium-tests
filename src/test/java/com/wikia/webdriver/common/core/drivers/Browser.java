package com.wikia.webdriver.common.core.drivers;

import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.drivers.browsers.AndroidBrowser;
import com.wikia.webdriver.common.core.drivers.browsers.ChromeBrowser;
import com.wikia.webdriver.common.core.drivers.browsers.DefaultBrowser;
import com.wikia.webdriver.common.core.drivers.browsers.FirefoxBrowser;
import com.wikia.webdriver.common.core.drivers.browsers.GhostBrowser;
import com.wikia.webdriver.common.core.drivers.browsers.HtmlUnitBrowser;
import com.wikia.webdriver.common.logging.PageObjectLogging;

public enum Browser {
  CHROME(ChromeBrowser.class, "CHROME"),
  FIREFOX(FirefoxBrowser.class, "FF"),
  CHROME_MOBILE(ChromeBrowser.class, "CHROMEMOBILEMERCURY"),
  HTMLUNIT(HtmlUnitBrowser.class, "HTMLUNIT"),
  GHOST(GhostBrowser.class, "GHOST"),
  CHROME_ANDROID(AndroidBrowser.class, "ANDROID"),
  DEFAULT(DefaultBrowser.class, "");

  private Class<? extends BrowserAbstract> browserClass;
  private String name;

  Browser(Class<? extends BrowserAbstract> browserClass, String name) {
    this.name = name;
    this.browserClass = browserClass;
  }

  public static Browser lookup(String browserName) {
    for (Browser name : Browser.values()) {
      if (name.getName().equalsIgnoreCase(browserName)) {
        return name;
      }
    }
    return null;
  }

  public String getName() {
    return name;
  }

  public WikiaWebDriver getInstance() {
    try {
      return browserClass.newInstance().getInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      PageObjectLogging.logError("Could not initialize the browser", e);
    }
    return null;
  }

  public Class<? extends BrowserAbstract> getBrowserClass() {
    return browserClass;
  }
}
