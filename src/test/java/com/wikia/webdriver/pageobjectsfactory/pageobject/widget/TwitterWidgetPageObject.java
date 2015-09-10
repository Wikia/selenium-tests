package com.wikia.webdriver.pageobjectsfactory.pageobject.widget;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @ownership: Content X-Wing
 */
public class TwitterWidgetPageObject extends WidgetPageObject {

  // set proper css in future
  @FindBy(css = "div[data-tag=twitter] > div")
  private WebElement elementInVebatim;

  private static final String TAG_NAME = "twitter";
  private static final String ARTICLE_NAME = "TwitterWidget";

  public TwitterWidgetPageObject(WebDriver driver) {
    super(driver);
  }

  protected String getArticleName() {
    return ARTICLE_NAME;
  }

  protected String getTagName() {
    return TAG_NAME;
  }

  protected String getTag() {
    return "<" + getTagName() + ">";
  }

  protected boolean isTagLoadedOnMercury() {
    // to be implemented
    return isElementVisible(elementInVebatim);
  }

  protected boolean isTagLoadedOnOasis() {
    // to be implemented
    return isElementVisible(elementInVebatim);
  }
}
