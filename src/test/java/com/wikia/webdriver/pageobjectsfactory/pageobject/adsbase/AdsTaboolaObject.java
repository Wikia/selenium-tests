package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.core.Assertion;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdsTaboolaObject extends AdsBaseObject {

  public static final String BELOW_ARTICLE_CSS_SELECTOR = "#NATIVE_TABOOLA_ARTICLE";
  public static final String ABOVE_ARTICLE_CSS_SELECTOR = "#taboola-above-article-thumbnails";

  public AdsTaboolaObject(WebDriver driver) {
    super(driver);
  }

  public void verifyTaboolaContainer(String slotCssSelector) {
    Assertion.assertTrue(isElementOnPage(By.cssSelector(slotCssSelector)),
                         slotCssSelector + " taboola container is not present");
  }
}
