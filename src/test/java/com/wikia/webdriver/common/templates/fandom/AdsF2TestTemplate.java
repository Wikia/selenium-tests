package com.wikia.webdriver.common.templates.fandom;

import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFandomObject;

public class AdsF2TestTemplate extends FandomTestTemplate {

  public static final String PAGE_TYPE_ARTICLE = "f2/article";
  public static final String PAGE_TYPE_TOPIC = "topic";

  @Override
  protected void loadFirstPage() {
    // we want to avoid going to qa.fandom.com as logged in user
  }

  protected AdsFandomObject loadPage(String pageName, String pageType) {
    String pageUrl;

    switch (pageType) {
      case PAGE_TYPE_TOPIC:
        pageUrl = urlBuilder.getUrlForFandomTopic(pageName);
        break;
      case PAGE_TYPE_ARTICLE:
      default:
        pageUrl = urlBuilder.getUrlForF2ArticlePage(pageName);
        break;
    }

    return new AdsFandomObject(driver, pageUrl);
  }

  protected AdsFandomObject loadPage(String pageName) {
    return loadPage(pageName, PAGE_TYPE_ARTICLE);
  }
}
