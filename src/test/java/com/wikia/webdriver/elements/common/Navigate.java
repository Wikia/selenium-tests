package com.wikia.webdriver.elements.common;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import org.joda.time.DateTime;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

public class Navigate extends BasePageObject {

  public Navigate toPage(String pageName) {
    String query = getQueryParams(pageName);

    driver.get(urlBuilder.getUrlForWiki() + URLsContent.WIKI_DIR + pageName + query);

    return this;
  }

  public Navigate toPageByPath(String path) {
    String query = getQueryParams(path);

    driver.get(urlBuilder.getUrlForWiki() + path + query);

    return this;
  }

  public Navigate toPageByPath(String path, String reference) {
    String query = getQueryParams(path);
    reference = "#" + reference;

    driver.get(urlBuilder.getUrlForPage(path) + query + reference);

    return this;
  }

  public Navigate toPageByPath(String path, String[] queryParams) {
    String query = getQueryParams(path, queryParams);

    driver.get(urlBuilder.getUrlForPage(path) + query);

    return this;
  }

  public Navigate toPageByPath(String host, String path, String[] queryParams) {
    String query = getQueryParams(path, queryParams);

    driver.get(urlBuilder.getUrlForPage(path) + query);

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
