package com.wikia.webdriver.pageobjectsfactory.pageobject.widget;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @ownership: Content X-Wing
 */
public class WeiboWidgetPageObject extends WidgetPageObject {

  @FindBy(css = "iframe[data-wikia-widget='weibo']")
  private WebElement weiboIframe;
  @FindBy(css = "div.tsina_open")
  private WebElement weiboBody;

  private static final String TAG_NAME = "weibo";
  private static final String ARTICLE_NAME = "WeiboWidget";
  private static final String TAG = "<weibo uids=\"1642909335,1782515283\" />";

  public WeiboWidgetPageObject(WebDriver driver) {
    super(driver);
  }

  protected String getArticleName() {
    return ARTICLE_NAME;
  }

  protected String getTagName() {
    return TAG_NAME;
  }

  public String getTag() {
    return TAG;
  }

  protected boolean isTagLoadedOnMercury() {
    if (!isElementVisible(weiboIframe)) {
      return false;
    }

    driver.switchTo().frame(weiboIframe);
    boolean result = isElementVisible(weiboBody);
    driver.switchTo().parentFrame();

    return result;
  }

  protected boolean isTagLoadedOnOasis() {
    if (!isElementVisible(weiboIframe)) {
      return false;
    }

    driver.switchTo().frame(weiboIframe);
    boolean result = isElementVisible(weiboBody);
    driver.switchTo().parentFrame();

    return result;
  }
}
