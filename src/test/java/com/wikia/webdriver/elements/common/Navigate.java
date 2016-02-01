package com.wikia.webdriver.elements.common;

import com.wikia.webdriver.common.core.url.UrlBuilder;

import org.joda.time.DateTime;
import org.openqa.selenium.WebDriver;

public class Navigate {

  private WebDriver driver;

  public Navigate(WebDriver driver) {
    this.driver = driver;
  }

  public Navigate toPage(String pageName) {
    String host = UrlBuilder.getHostForWiki();
    String cacheBuster = "?cb=" + DateTime.now().getMillis();

    driver.get(host + pageName + cacheBuster);

    return this;
  }

  public Navigate toPage(String pageName, String reference) {
    String host = UrlBuilder.getHostForWiki();
    String cacheBuster = "?cb=" + DateTime.now().getMillis();
    reference = "#" + reference;

    driver.get(host + pageName + cacheBuster + reference);

    return this;
  }
}
