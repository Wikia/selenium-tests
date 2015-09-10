package com.wikia.webdriver.pageobjectsfactory.pageobject.widget;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
    throw new NotImplementedException();
    //return isElementVisible(elementInVebatim);
  }

  protected boolean isTagLoadedOnOasis() {
    throw new NotImplementedException();
    //return isElementVisible(elementInVebatim);
  }
}
