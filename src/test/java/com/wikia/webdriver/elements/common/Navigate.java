package com.wikia.webdriver.elements.common;

import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.TimeoutException;

public class Navigate extends BasePageObject {

  public Navigate toPage(String pageName) {
    try {
      driver.get(urlBuilder.getUrlForWikiPage(pageName) + urlBuilder.getCacheBusterQuery(pageName));
    }catch (TimeoutException e){
      new JavascriptActions(driver).execute("window.stop()");
    Log.log("Externals loading timeout", e, false);
    }
    return this;
  }

  public Navigate toPageByPath(String path) {
    String query = urlBuilder.getCacheBusterQuery(path);

    driver.get(urlBuilder.getUrl() + path + query);

    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      Log.log("URL builder fail", e, false);
    }
    return this;
  }

  public Navigate toPageByPath(String path, String fragmentIdentifier) {
    String query = urlBuilder.getCacheBusterQuery(path);
    fragmentIdentifier = "#" + fragmentIdentifier;

    driver.get(urlBuilder.getUrlForPath(path) + query + fragmentIdentifier);

    return this;
  }

  public Navigate toPageByPath(String path, String[] queryParams) {
    String query = urlBuilder.getQueryParams(path, queryParams);

    driver.get(urlBuilder.appendQueryStringToURL(urlBuilder.getUrlForPath(path), query));

    return this;
  }

  public Navigate toPageByPath(String host, String path, String[] queryParams) {
    String query = urlBuilder.getQueryParams(path, queryParams);

    driver.get(urlBuilder.appendQueryStringToURL(urlBuilder.getUrlForPath(path), query));

    return this;
  }

  public Navigate toUrl(String url) {
    driver.get(url);
    return this;
  }
}
