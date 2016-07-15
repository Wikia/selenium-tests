package com.wikia.webdriver.elements.common;

import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.joda.time.DateTime;

public class Navigate extends BasePageObject {

  private static final String PROTOCOL = "http://";

  public Navigate toPage(String pageName) {
    String host = UrlBuilder.getHostForWiki();
    String query = getQueryParams(pageName);

    driver.get(PROTOCOL + host + pageName + query);

    return this;
  }

  public Navigate toPage(String pageName, String reference) {
    String host = UrlBuilder.getHostForWiki();
    String query = getQueryParams(pageName);
    reference = "#" + reference;

    driver.get(PROTOCOL + host + pageName + query + reference);

    return this;
  }

  public Navigate toPage(String pageName, String[] queryParams) {
    String host = UrlBuilder.getHostForWiki();
    String query = getQueryParams(pageName, queryParams);

    driver.get(PROTOCOL + host + pageName + query);

    return this;
  }

  public Navigate toPage(String host, String pageName, String[] queryParams) {
    String query = getQueryParams(pageName, queryParams);

    driver.get(PROTOCOL + host + pageName + query);

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
