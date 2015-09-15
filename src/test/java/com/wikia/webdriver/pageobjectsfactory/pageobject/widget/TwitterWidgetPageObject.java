package com.wikia.webdriver.pageobjectsfactory.pageobject.widget;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @ownership: Content X-Wing
 */
public class TwitterWidgetPageObject extends WidgetPageObject {

  @FindBy(css = ".widget-twitter iframe")
  private WebElement widgetIframe;
  @FindBy(css = ".widget-twitter iframe")
  private List<WebElement> widgetIframeList;
  @FindBy(css = "div.timeline")
  private WebElement widgetBody;

  private static final String TAG_NAME = "twitter";
  private static final String ARTICLE_NAME = "TwitterWidget";
  private static final String[] TAGS = {
      "<twitter widget-id=\"522824386202447873\" screen-name=\"sfbart\" />",
      "<twitter widget-id=\"522824386202447873\" />",
  };
  private static final String INCORRECT_TAG = "<twitter />";
  private static final String ERROR_MESSAGE = "Error: No Twitter Widget ID provided. Please see Help:Social media integration.";

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
    return TAGS[0];
  }

  protected String[] getTags() {
    return TAGS;
  }

  protected String getIncorrectTag() {
    return INCORRECT_TAG;
  }

  protected String getErrorMessage() {
    return ERROR_MESSAGE;
  }

  protected boolean isTagLoadedOnMercury() {
    return isWidgetVisible(widgetIframe, widgetBody);
  }

  protected boolean isTagLoadedOnOasis() {
    return isWidgetVisible(widgetIframe, widgetBody);
  }

  protected boolean areTagsLoadedOnOasis() {
    for (WebElement iframe: widgetIframeList) {
      if (!isWidgetVisible(iframe, widgetBody)) {
        return false;
      }
    }
    return true;
  }

  protected boolean areTagsLoadedOnMercury() {
    for (WebElement iframe: widgetIframeList) {
      if (!isWidgetVisible(iframe, widgetBody)) {
        return false;
      }
    }
    return true;
  }
}
