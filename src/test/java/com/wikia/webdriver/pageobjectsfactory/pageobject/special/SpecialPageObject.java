package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Bogna 'bognix' Knychala
 */
public class SpecialPageObject extends WikiBasePageObject {

  @FindBy(css = "#WikiaPageHeader > h2")
  protected WebElement specialPageHeader;

  private static final String HEADER_TEXT_SELECTOR = "//h1[contains(text(), '%s')]";

  public SpecialPageObject(WebDriver driver) {
    super(driver);
  }

  public void verifySpecialPage() {
    waitForTextToBePresentInElementByElement(
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
    waitForElementByXPath(String.format(HEADER_TEXT_SELECTOR, expectedHeader));
    PageObjectLogging.log(
        "SpecialPageHeader",
        "Special Page Header is the same as expected",
        true,
        driver
    );
  }
}
