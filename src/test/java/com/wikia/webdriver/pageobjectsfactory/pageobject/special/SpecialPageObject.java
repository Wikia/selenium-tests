package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;

public class SpecialPageObject extends WikiBasePageObject {

  private static final String HEADER_TEXT_SELECTOR = "//h1[contains(text(), '%s')]";

  public void verifyPageHeader(String expectedHeader) {
    wait.forElementVisible(By.xpath(String.format(HEADER_TEXT_SELECTOR, expectedHeader)));
    Log.log("SpecialPageHeader", "Special Page Header is the same as expected", true, driver);
  }
}
