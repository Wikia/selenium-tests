package com.wikia.webdriver.common.remote;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class Utils {

  public static final String ACCESS_TOKEN_HEADER = "X-Wikia-AccessToken";

  private Utils() {
    throw new AssertionError();
  }

  public static String extractSiteIdFromMediaWikiUsing(final WebDriver driver) {
    String text = driver.findElement(By.cssSelector(".wikitable tr:nth-child(5) td:nth-child(2)"))
        .getText();
    // Found text: "city_id: 1362702, cluster: c7, dc: sjc"
    return StringUtils.substringBetween(text, ": ", ",");
  }

  public static String extractSiteIdFromWikiName(String wikiName, String language) {
    return new SiteId(wikiName, language).getSiteId();
  }
}
