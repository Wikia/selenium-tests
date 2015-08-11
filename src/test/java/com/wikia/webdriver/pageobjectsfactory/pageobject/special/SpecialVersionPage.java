package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebDriver;

/**
 * Created by Ludwik on 08.08.2015.
 */
public class SpecialVersionPage extends WikiBasePageObject {

  public SpecialVersionPage(WebDriver driver) {
    super(driver);
  }

  public SpecialVersionPage open() {
    driver.get(urlBuilder.getUrlForWiki(Configuration.getWikiName()) + URLsContent.SPECIAL_VERSION);

    return this;
  }
}
