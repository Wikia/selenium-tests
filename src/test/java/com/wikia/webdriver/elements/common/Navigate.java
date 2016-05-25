package com.wikia.webdriver.elements.common;

import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.joda.time.DateTime;

public class Navigate extends BasePageObject {

  public Navigate toPage(String pageName) {
    String host = UrlBuilder.getHostForWiki();
    String cacheBuster = pageName.equals("") ||
                         pageName.equals("/") ? "" : "?cb=" + DateTime.now().getMillis();

    driver.get("http://" + host + pageName + cacheBuster);

    return this;
  }

  public Navigate toPage(String pageName, String reference) {
    String host = UrlBuilder.getHostForWiki();
    String cacheBuster = "?cb=" + DateTime.now().getMillis();
    reference = "#" + reference;

    driver.get("http://" + host + pageName + cacheBuster + reference);

    return this;
  }

  public Navigate toPage(String pageName, String[] queryParams) {
    String host = UrlBuilder.getHostForWiki();
    String query = "?cb=" + DateTime.now().getMillis();

    for (String queryParam : queryParams) {
      query = query + "&" + queryParam;
    }

    driver.get("http://" + host + pageName + query);

    return this;
  }

  public Navigate toUrl(String url) {
    driver.get(url);
    return this;
  }
}
