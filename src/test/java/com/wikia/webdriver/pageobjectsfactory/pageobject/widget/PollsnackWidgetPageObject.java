package com.wikia.webdriver.pageobjectsfactory.pageobject.widget;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @ownership: Content X-Wing
 */
public class PollsnackWidgetPageObject extends WidgetPageObject {

  @FindBy(css = "iframe[data-wikia-widget=\"pollsnack\"]")
  private WebElement pollsnackIframe;
  @FindBy(css = "iframe")
  private WebElement pollsnackBody;

  private static final String TAG_NAME = "pollsnack";
  private static final String ARTICLE_NAME = "PollsnackWidget";
  private static final String TAG = "<pollsnack hash=\"q7kiw9kz\"/>";

  public PollsnackWidgetPageObject(WebDriver driver) {
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
    if (!isElementVisible(pollsnackIframe)) {
      return false;
    }

    driver.switchTo().frame(pollsnackIframe);
    boolean result = isElementVisible(pollsnackBody);
    driver.switchTo().parentFrame();

    return result;
  }

  protected boolean isTagLoadedOnOasis() {
    if (!isElementVisible(pollsnackIframe)) {
      return false;
    }

    driver.switchTo().frame(pollsnackIframe);
    boolean result = isElementVisible(pollsnackBody);
    driver.switchTo().parentFrame();

    return result;
  }
}
