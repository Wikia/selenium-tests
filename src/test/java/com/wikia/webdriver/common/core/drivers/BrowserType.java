package com.wikia.webdriver.common.core.drivers;

import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.drivers.Browsers.AndroidBrowser;
import com.wikia.webdriver.common.core.drivers.Browsers.ChromeBrowser;
import com.wikia.webdriver.common.core.drivers.Browsers.ChromeMobileBrowser;
import com.wikia.webdriver.common.core.drivers.Browsers.DefaultBrowser;
import com.wikia.webdriver.common.core.drivers.Browsers.FirefoxBrowser;
import com.wikia.webdriver.common.core.drivers.Browsers.GhostBrowser;
import com.wikia.webdriver.common.core.drivers.Browsers.HtmlUnitBrowser;

import org.openqa.selenium.support.events.EventFiringWebDriver;

public enum BrowserType{
  CHROME(ChromeBrowser.class, "CHROME"),
  FIREFOX(FirefoxBrowser.class, "FF"),
  CHROME_MOBILE(ChromeMobileBrowser.class, "CHROMEMOBILEMERCURY"),
  HTMLUNIT(HtmlUnitBrowser.class, "HTMLUNIT"),
  GHOST(GhostBrowser.class, "GHOST"),
  CHROME_ANDROID(AndroidBrowser.class, "ANDROID"),
  DEFAULT(DefaultBrowser.class, "");

  private Class<? extends BrowserAbstract> browser;
  private String name;

  BrowserType(Class<? extends BrowserAbstract> browser, String name) {
    this.name = name;
    this.browser = browser;
  }

  public String getName(){
    return name;
  }

  public WikiaWebDriver getInstance() {
    try {
      return browser.newInstance().getInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      e.printStackTrace();
    }
    return null;
  }

  public WikiaWebDriver setInstance() {
    try {
      return browser.newInstance().setInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static BrowserType lookup(String browserName) {
    for (BrowserType name : BrowserType.values()) {
      if (name.getName().equalsIgnoreCase(browserName)) {
        return name;
      }
    }
    return null;
  }
}
