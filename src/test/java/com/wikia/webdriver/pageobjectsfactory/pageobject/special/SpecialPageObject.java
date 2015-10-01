package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

/**
 * @author Bogna 'bognix' Knychala
 */
public class SpecialPageObject extends WikiBasePageObject {

  private static final String HEADER_TEXT_SELECTOR = "//h1[contains(text(), '%s')]";
  @FindBy(css = ".header-column.header-title>h2")
  protected WebElement specialPageHeader;

  public SpecialPageObject(WebDriver driver) {
    super(driver);
  }

  public void verifySpecialPage() {
    wait.forTextInElement(specialPageHeader, "Special page");
    LOG.success("SpecialPageLoaded", "Special Page is loaded", true);
  }

  public void verifyPageHeader(String expectedHeader) {
    wait.forElementVisible(By.xpath(String.format(HEADER_TEXT_SELECTOR, expectedHeader)));
    LOG.success("SpecialPageHeader", "Special Page Header is the same as expected", true);
  }
}
