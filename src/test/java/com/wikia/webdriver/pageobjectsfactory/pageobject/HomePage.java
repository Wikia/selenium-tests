package com.wikia.webdriver.pageobjectsfactory.pageobject;

import com.wikia.webdriver.common.core.configuration.Configuration;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import org.openqa.selenium.WebDriver;

public class HomePage extends WikiBasePageObject {

  public HomePage open() {
    return open(Configuration.getWikiName());
  }

  public HomePage open(String wikiName) {
    getUrl(urlBuilder.getUrlForWiki(wikiName));
    waitForPageLoad();

    return this;
  }

  public HomePage openAndWaitForGlobalShortcuts() {
    open();
    waitFor.until((Function<WebDriver, Boolean>) arg0 -> driver.executeScript(
        "return typeof window.wgGlobalShortcutsLoaded !== 'undefined' && window.wgGlobalShortcutsLoaded")
                                                      .equals(true));

    return this;
  }
}
