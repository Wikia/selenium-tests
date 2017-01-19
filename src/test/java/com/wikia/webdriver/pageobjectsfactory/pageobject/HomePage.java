package com.wikia.webdriver.pageobjectsfactory.pageobject;

import com.wikia.webdriver.common.core.configuration.Configuration;

public class HomePage extends WikiBasePageObject {

  public HomePage open() {
    return open(Configuration.getWikiName());
  }

  public HomePage open(String wikiName) {
    getUrl(urlBuilder.getUrlForWiki(wikiName));
    waitForPageLoad();

    return this;
  }
}
