package com.wikia.webdriver.elements.common;

import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.joda.time.DateTime;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Navigate {

  private WebDriver driver;

  public Navigate(WebDriver driver) {
    this.driver = driver;
  }

  public Navigate toPage(String pageName) {
    try {
      URL url = new URL(driver.getCurrentUrl());
      String host = url.getHost();

      driver.get("http://" + host + pageName + "?cb=" + DateTime.now().getMillis());
    } catch (MalformedURLException e) {
      PageObjectLogging.logInfo("Url malformed");
    }

    return this;
  }
}
