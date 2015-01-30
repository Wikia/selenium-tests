package com.wikia.webdriver.pageobjectsfactory.pageobject.venus;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VenusArticlePageObject extends WikiBasePageObject {

  @FindBy(css = ".table-scrollable-wrapper")
  private WebElement scrollableTable;

  @FindBy(css = ".article-table")
  private WebElement articleTable;

  public VenusArticlePageObject(WebDriver driver) {
    super(driver);
  }

  public Boolean isScrollableTablePresent() {
    return checkIfElementOnPage(scrollableTable);
  }

  public Boolean isTablePresent() {
    return checkIfElementOnPage(articleTable);
  }
}
