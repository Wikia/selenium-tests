package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.verbatim;

import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.VerbatimPageObject;

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

  public WebElement getElement() {
    return elementInVebatim;
  }

  public String getTagName() {
    return tagName;
  }
}
