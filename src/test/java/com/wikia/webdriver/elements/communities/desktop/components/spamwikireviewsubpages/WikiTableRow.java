package com.wikia.webdriver.elements.communities.desktop.components.spamwikireviewsubpages;

import com.wikia.webdriver.elements.common.ViewOnlyWebElement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * This wraps WebElement containing a single entry in Wiki Table in Spam Wiki Review view.
 * Provides methods to get a specific column's value
 */
public class WikiTableRow {

  private WebElement rowWebElement;

  private static final String XPATH_LANGUAGE_COLUMN = "./td[4]";
  private static final String XPATH_STATUS_COLUMN = "./td[6]";

  public WikiTableRow(WebElement givenWebElement) {
    rowWebElement = givenWebElement;
  }

  public String getLanguageColumnValue() {
    return rowWebElement.findElement(By.xpath(XPATH_LANGUAGE_COLUMN)).getText();
  }

  public String getStatusColumnValue(){
    return rowWebElement.findElement(By.xpath(XPATH_STATUS_COLUMN)).getText();
  }

  /**
   * This returns a ViewOnlyWebElement containing the row
   * @return ViewOnlyWebElement, a WebElement that allows only for get methods
   */
  public ViewOnlyWebElement getRowViewOnlyWebElement(){
    return (ViewOnlyWebElement) rowWebElement;
  }
}
