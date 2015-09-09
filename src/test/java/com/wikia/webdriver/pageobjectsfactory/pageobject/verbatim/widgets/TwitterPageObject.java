package com.wikia.webdriver.pageobjectsfactory.pageobject.verbatim.widgets;

import com.wikia.webdriver.pageobjectsfactory.pageobject.verbatim.VerbatimPageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @ownership: Content X-Wing
 */
public class TwitterPageObject extends VerbatimPageObject {

  // set proper css in future
  @FindBy(css = "div[data-tag=twitter] > div")
  private WebElement elementInVebatim;

  private String tagName = "twitter";

  public TwitterPageObject(WebDriver driver) {
    super(driver);

  }

  protected boolean isTagLoadedOnOasis() {
    // to be implemented
    return isElementVisible(elementInVebatim);
  }

  protected boolean isTagLoadedOnMercury() {
    // to be implemented
    return isElementVisible(elementInVebatim);
  }
}
