package com.wikia.webdriver.common.core.drivers;

import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.drivers.browsers.AndroidBrowser;
import com.wikia.webdriver.common.core.drivers.browsers.ChromeBrowser;
import com.wikia.webdriver.common.core.drivers.browsers.DefaultBrowser;
import com.wikia.webdriver.common.core.drivers.browsers.FirefoxBrowser;
import com.wikia.webdriver.common.core.drivers.browsers.GhostBrowser;
import com.wikia.webdriver.common.core.drivers.browsers.HtmlUnitBrowser;

public enum BrowserType{
  CHROME(ChromeBrowser.class, "CHROME"),
  FIREFOX(FirefoxBrowser.class, "FF"),
  CHROME_MOBILE(ChromeBrowser.class, "CHROMEMOBILEMERCURY"),
  HTMLUNIT(HtmlUnitBrowser.class, "HTMLUNIT"),
  GHOST(GhostBrowser.class, "GHOST"),
  CHROME_ANDROID(AndroidBrowser.class, "ANDROID"),
  DEFAULT(DefaultBrowser.class, "");

  private Class<? extends BrowserAbstract> browserClass;
  private String name;

  BrowserType(Class<? extends BrowserAbstract> browserClass, String name) {
    this.name = name;
    this.browserClass = browserClass;
  }

  public String getName(){
    return name;
  }

  public WikiaWebDriver getInstance() {
    try {
      return browserClass.newInstance().getInstance();
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

  public Class<? extends BrowserAbstract> getBrowserClass(){
    return browserClass;
  }
}
