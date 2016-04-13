package com.wikia.webdriver.elements.common;

import com.wikia.webdriver.common.core.url.UrlBuilder;

import org.joda.time.DateTime;
import org.openqa.selenium.WebDriver;

public class Navigate {

  private WebDriver driver;

  /**
   * Adding default testing group is invoked explicitly in URL is temporary change until
   * the end of experiment (19.04.2016). Experiment is described in DAT-4052.
   */
  private static final String defaultTestingGroup = "ABTEST.FAN_KNOWLEDGE_MERCURY_GLOBAL_NAV=DEFAULT";

  public Navigate(WebDriver driver) {
    this.driver = driver;
  }

  public Navigate toPage(String pageName) {
    String host = UrlBuilder.getHostForWiki();
    String cacheBuster = pageName.equals("") || pageName.equals("/") ? "" : "?cb=" + DateTime.now().getMillis();

    driver.get("http://" + host + pageName + cacheBuster + "?" + defaultTestingGroup);

    return this;
  }

  public Navigate toPage(String pageName, String reference) {
    String host = UrlBuilder.getHostForWiki();
    String cacheBuster = "?cb=" + DateTime.now().getMillis();
    reference = "#" + reference;

    driver.get("http://" + host + pageName + cacheBuster + "&" + defaultTestingGroup + reference);

    return this;
  }

  public Navigate toPage(String pageName, String[] queryParams) {
    String host = UrlBuilder.getHostForWiki();
    String query = "?cb=" + DateTime.now().getMillis();

    for (String queryParam : queryParams) {
      query = query + "&" + queryParam;
    }

    driver.get("http://" + host + pageName + query + "&" + defaultTestingGroup);

    return this;
  }
}
