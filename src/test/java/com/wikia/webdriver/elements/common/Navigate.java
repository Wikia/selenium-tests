package com.wikia.webdriver.elements.common;

import org.joda.time.DateTime;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

public class Navigate extends BasePageObject {

  public Navigate toPage(String pageName) {
    String query = getQueryParams(pageName);

    driver.get(urlBuilder.getUrlForWiki() + query);

    return this;
  }

  public Navigate toPage(String pageName, String reference) {
    String query = getQueryParams(pageName);
    reference = "#" + reference;

    driver.get(urlBuilder.getUrlForPage(pageName) + query + reference);

    return this;
  }

  public Navigate toPage(String pageName, String[] queryParams) {
    String query = getQueryParams(pageName, queryParams);

    driver.get(urlBuilder.getUrlForPage(pageName) + query);

    return this;
  }

  public Navigate toPage(String host, String pageName, String[] queryParams) {
    String query = getQueryParams(pageName, queryParams);

    driver.get(urlBuilder.getUrlForPage(pageName) + query);

    return this;
  }

  public Navigate toUrl(String url) {
    driver.get(url);
    return this;
  }

  private String getQueryParams(String pageName) {
    return pageName.equals("") || pageName.equals("/") ? "" : "?cb=" + DateTime.now().getMillis();
  }

  private String getQueryParams(String pageName, String[] queryParams) {
    String query = this.getQueryParams(pageName);

    if (!query.equals("")) {
      for (String queryParam : queryParams) {
        query = query + "&" + queryParam;
      }
    }

    return query;
  }
}
