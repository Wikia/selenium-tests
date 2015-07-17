package com.wikia.webdriver.common.core.url;

import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.WebDriver;

public class UrlChecker {

  /**
   * This method check that path is contained in current Url and log result
   * Case sensitive is not important
   */
  public static void isPathContainedInCurrentUrl(WebDriver driver, String path) {
    String currentUrl = driver.getCurrentUrl().toLowerCase();
    path = path.toLowerCase();
    PageObjectLogging.log(
        "Log Url",
        "Path " + path + " is contained in " + currentUrl,
        "Path " + path + " isn't contained in " + currentUrl,
        currentUrl.contains(path)
    );
  }

  /**
   * This method check that Url equals current Url and log result
   * Case sensitive is not important
   */
  public static void isUrlEqualToCurrentUrl(WebDriver driver, String url) {
    String currentUrl = driver.getCurrentUrl().toLowerCase();
    url = url.toLowerCase();
    PageObjectLogging.log(
        "Log Url",
        "Url " + url + " is equal to current Url" + currentUrl,
        "Url " + url + " isn't equal to current Url" + currentUrl,
        currentUrl.equals(url)
    );
  }
}
