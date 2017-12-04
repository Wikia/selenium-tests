package com.webdriver.common.core.helpers;

import com.webdriver.common.core.elemnt.JavascriptActions;

import org.openqa.selenium.WebDriver;

/**
 * Here you can find methods to get properties for a current Wikia
 */
public class WikiaProperties {

  /**
   * Check if current wikia for children
   */
  public static boolean isWikiForChildren(WebDriver webDriver) {
    return "ec".equals(new JavascriptActions(webDriver).execute("ads.context.targeting.esrbRating"));
  }
}
