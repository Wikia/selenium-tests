package com.wikia.webdriver.common.templates.fandom;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFandomObject;

public class AdsFandomTestTemplate extends FandomTestTemplate {

  public static final String PAGE_TYPE_ARTICLE = "article";
  public static final String PAGE_TYPE_HUB = "hub";
  public static final String PAGE_TYPE_TOPIC = "topic";

  @Override
  protected void loadFirstPage() {
    // we want to avoid going to qa.fandom.com as logged in user
  }

  protected AdsFandomObject loadPage(String pageName, String pageType) {
    String pageUrl;
    String queryString = Configuration.getQS();
    Boolean isF2 = queryString.matches("/.*f2(?=\\=|&|$|\\n).*/");

    PageObjectLogging.log("queryString", queryString, true);
    PageObjectLogging.log("isF2", isF2.toString(), true);

    if(isF2) {
      pageUrl = urlBuilder.getF2Url(pageName, pageType);
    } else {
      switch (pageType) {
        case PAGE_TYPE_HUB:
          pageUrl = urlBuilder.getUrlForFandomHub(pageName);
          break;
        case PAGE_TYPE_ARTICLE:
        default:
          pageUrl = urlBuilder.getUrlForFandomPage(pageName);
          break;
      }
    }

    return new AdsFandomObject(driver, pageUrl);
  }

  protected AdsFandomObject loadPage(String pageName) {
    return loadPage(pageName, PAGE_TYPE_ARTICLE);
  }
}
