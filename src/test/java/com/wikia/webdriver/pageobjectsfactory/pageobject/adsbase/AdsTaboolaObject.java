package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdsTaboolaObject extends AdsBaseObject {

  public static final String BELOW_ARTICLE_CSS_SELECTOR = "#NATIVE_TABOOLA_ARTICLE";
  public static final String ABOVE_ARTICLE_CSS_SELECTOR = "#TOP_LEADERBOARD_AB";
  public static final String TABOOLA_LOADER_REQUEST
      = "http://cdn.taboola.com/libtrc/wikia-network/loader.js";

  public AdsTaboolaObject(WebDriver driver) {
    super(driver);
  }

  public void verifyTaboolaContainer(String slotCssSelector) {
    Assertion.assertTrue(isElementOnPage(By.cssSelector(slotCssSelector)),
                         slotCssSelector + " taboola container is not present");

    PageObjectLogging.logInfo(String.format("Taboola containers: %s and %s are present",
                                            ABOVE_ARTICLE_CSS_SELECTOR,
                                            BELOW_ARTICLE_CSS_SELECTOR));
  }
}
