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
      String cacheBuster = "?cb=" + DateTime.now().getMillis();

      driver.get("http://" + host + pageName + cacheBuster);
    } catch (MalformedURLException e) {
      PageObjectLogging.logInfo("Url malformed");
    }

    return this;
  }

  public Navigate toPage(String pageName, String reference) {
    try {
      URL url = new URL(driver.getCurrentUrl());
      String host = url.getHost();
      String cacheBuster = "?cb=" + DateTime.now().getMillis();
      reference = "#" + reference;

      driver.get("http://" + host + pageName + cacheBuster + reference);
    } catch (MalformedURLException e) {
      PageObjectLogging.logInfo("Url malformed");
    }

    return this;
  }

  public Navigate toPage_1(String pageName) {
    try {
      URL url = new URL(driver.getCurrentUrl());
      String host = url.getHost();
//      String cacheBuster = "?cb=" + DateTime.now().getMillis();

      driver.get("http://" + host + pageName);
    } catch (MalformedURLException e) {
      PageObjectLogging.logInfo("Url malformed");
    }

    return this;
  }
}
