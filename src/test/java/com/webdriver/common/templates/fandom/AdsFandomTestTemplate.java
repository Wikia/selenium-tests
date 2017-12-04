package com.webdriver.common.templates.fandom;

import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFandomObject;

import org.openqa.selenium.Dimension;

public class AdsFandomTestTemplate extends FandomTestTemplate {

  public static final String PAGE_TYPE_ARTICLE = "f2/article";
  public static final String PAGE_TYPE_TOPIC = "topic";

  @Override
  protected void loadFirstPage() {
    // we want to avoid going to qa.fandom.com as logged in user
  }

  protected AdsFandomObject loadPage(String pageName, String pageType) {
    String pageUrl = getFandomUrl(pageName, pageType);

    return new AdsFandomObject(driver, pageUrl);
  }

  protected AdsFandomObject loadPage(String pageName, String pageType, Dimension resolution) {
    String pageUrl = getFandomUrl(pageName, pageType);

    return new AdsFandomObject(driver, pageUrl, resolution);
  }

  public String getFandomUrl(String pageName, String pageType) {
    String pageUrl;

    switch (pageType) {
      case PAGE_TYPE_TOPIC:
        pageUrl = urlBuilder.getUrlForFandomTopic(pageName);
        break;
      case PAGE_TYPE_ARTICLE:
      default:
        pageUrl = urlBuilder.getUrlForFandomArticlePage(pageName);
        break;
    }

    return pageUrl;
  }

  protected AdsFandomObject loadPage(String pageName) {
    return loadPage(pageName, PAGE_TYPE_ARTICLE);
  }
}
