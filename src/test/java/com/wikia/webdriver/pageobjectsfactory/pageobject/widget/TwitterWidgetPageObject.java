package com.wikia.webdriver.pageobjectsfactory.pageobject.widget;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @ownership: Content X-Wing
 */
public class TwitterWidgetPageObject extends WidgetPageObject {

  @FindBy(css = ".widget-twitter iframe")
  private WebElement twitterIframe;
  @FindBy(css = "div.timeline")
  private WebElement twitterBody;

  private static final String TAG_NAME = "twitter";
  private static final String ARTICLE_NAME = "TwitterWidget";
  private static final String TAG = "<twitter widget-id=\"522824386202447873\" screen-name=\"sfbart\" />";
  private static final String INCORRECT_TAG = "<twitter />";
  private static final String ERROR_MESSAGE =
    "Error: No Twitter Widget ID provided. Please see Help:Social media integration.";

  public TwitterWidgetPageObject(WebDriver driver) {
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

  protected String getIncorrectTag() {
    return INCORRECT_TAG;
  }

  protected String getErrorMessage() {
    return ERROR_MESSAGE;
  }

  protected boolean isTagLoadedOnMercury() {
    if(!isElementVisible(twitterIframe)) {
      return false;
    }

    driver.switchTo().frame(twitterIframe);
    boolean result = isElementVisible(twitterBody);
    driver.switchTo().parentFrame();

    return result;
  }

  protected boolean isTagLoadedOnOasis() {
    if(!isElementVisible(twitterIframe)) {
      return false;
    }

    driver.switchTo().frame(twitterIframe);
    boolean result = isElementVisible(twitterBody);
    driver.switchTo().parentFrame();

    return result;
  }
}
