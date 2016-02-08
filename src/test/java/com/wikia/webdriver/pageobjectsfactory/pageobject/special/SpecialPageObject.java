package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SpecialPageObject extends WikiBasePageObject {

  @FindBy(css = ".header-column.header-title>h2")
  protected WebElement specialPageHeader;

  private static final String HEADER_TEXT_SELECTOR = "//h1[contains(text(), '%s')]";

  public SpecialPageObject(WebDriver driver) {
    super();
  }

  public void verifySpecialPage() {
    wait.forTextInElement(
        specialPageHeader,
        "Special page"
    );
    PageObjectLogging.log(
        "SpecialPageLoaded",
        "Special Page is loaded",
        true,
        driver
    );
  }

  public void verifyPageHeader(String expectedHeader) {
    wait.forElementVisible(By.xpath(String.format(HEADER_TEXT_SELECTOR, expectedHeader)));
    PageObjectLogging.log(
        "SpecialPageHeader",
        "Special Page Header is the same as expected",
        true,
        driver
    );
  }
}
