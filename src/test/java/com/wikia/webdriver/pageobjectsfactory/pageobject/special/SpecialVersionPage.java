package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

public class SpecialVersionPage extends WikiBasePageObject {

  public SpecialVersionPage() {
    super();
  }

  public SpecialVersionPage open() {
    driver.get(urlBuilder.getUrlForWiki(Configuration.getWikiName()) + URLsContent.SPECIAL_VERSION);

    return this;
  }
}
