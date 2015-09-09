package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @ownership: Content X-Wing
 */
public abstract class VerbatimPageObject extends BasePageObject {

  protected VerbatimPageObject(WebDriver driver) {
    super(driver);
  }

  protected abstract WebElement getElement();

  protected abstract String getTagName();

  public boolean isLoaded() {
    boolean result = isElementVisible(getElement());
    PageObjectLogging.log(getTagName(), MercuryMessages.VISIBLE_MSG, result);
    return result;
  }
}
