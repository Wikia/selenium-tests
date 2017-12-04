package com.webdriver.common.templates.fandom;

import com.webdriver.common.core.url.FandomUrlBuilder;
import com.webdriver.common.templates.core.CoreTestTemplate;

public class FandomTestTemplate extends CoreTestTemplate {

  private String mainURL = "http://sandbox-qa.fandom.wikia.com/";
  protected FandomUrlBuilder urlBuilder = new FandomUrlBuilder();

  @Override
  protected void prepareURLs() {

  }

  @Override
  protected void loadFirstPage() {
    driver.navigate().to(mainURL);
  }
}
