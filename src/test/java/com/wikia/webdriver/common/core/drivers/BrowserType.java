package com.wikia.webdriver.common.core.drivers;

import com.wikia.webdriver.common.core.drivers.Browsers.AndroidBrowser;
import com.wikia.webdriver.common.core.drivers.Browsers.ChromeBrowser;
import com.wikia.webdriver.common.core.drivers.Browsers.FirefoxBrowser;
import com.wikia.webdriver.common.core.drivers.Browsers.GhostBrowser;
import com.wikia.webdriver.common.core.drivers.Browsers.HtmlUnitBrowser;

import org.openqa.selenium.support.events.EventFiringWebDriver;

public enum BrowserType{
  CHROME(ChromeBrowser.class),
  FF(FirefoxBrowser.class),
  CHROMEMOBILEMERCURY(ChromeBrowser.class),
  HTMLUNIT(HtmlUnitBrowser.class),
  GHOST(GhostBrowser.class),
  ANDROID(AndroidBrowser.class);

  private Class<? extends BrowserAbstract> browser;

  BrowserType(Class<? extends BrowserAbstract> browser) {
    this.browser = browser;
  }

  public EventFiringWebDriver getInstance() {
    try {
      return browser.newInstance().getInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static BrowserType lookup(String browserName) {
    for (BrowserType name : BrowserType.values()) {
      if (name.name().equalsIgnoreCase(browserName)) {
        return name;
      }
    }
    return null;
  }
}
