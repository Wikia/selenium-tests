package com.wikia.webdriver.pageobjectsfactory.pageobject.verbatim;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;

import org.openqa.selenium.WebDriver;

/**
 * @ownership: Content X-Wing
 */
public abstract class VerbatimPageObject extends BasePageObject {

  protected String tagName;

  protected VerbatimPageObject(WebDriver driver) {
    super(driver);
  }

  protected abstract boolean isTagLoadedOnMercury();

  protected abstract boolean isTagLoadedOnOasis();

  protected String getTagName() {
    return tagName;
  }

  public boolean isLoadedOnMercury() {
    boolean result = isTagLoadedOnMercury();
    Assertion.assertTrue(result, MercuryMessages.INVISIBLE_MSG);
    PageObjectLogging.log(getTagName(), MercuryMessages.VISIBLE_MSG, result);
    return result;
  }

  public boolean isLoadedOnOasis() {
    boolean result = isTagLoadedOnOasis();
    PageObjectLogging.log(getTagName(), MercuryMessages.VISIBLE_MSG, result);
    return result;
  }
}
