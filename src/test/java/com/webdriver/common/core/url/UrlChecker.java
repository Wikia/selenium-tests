package com.webdriver.common.core.url;

import com.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.WebDriver;

public class UrlChecker {

  /**
   * This method checks that current URL contains the path and logs result
   * The method is case-insensitive 
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

  public static boolean isUrlEqualToCurrentUrl(WebDriver driver, String url) {
    return driver.getCurrentUrl().equalsIgnoreCase(url);
  }
}
