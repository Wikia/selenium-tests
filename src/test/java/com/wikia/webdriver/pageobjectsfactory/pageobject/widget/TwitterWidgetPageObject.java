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
  private WebElement twitterIframe;
  @FindBy(css = ".widget-twitter iframe")
  private List<WebElement> twitterIframeList;
  @FindBy(css = "div.timeline")
  private WebElement twitterBody;

  private static final String TAG_NAME = "twitter";
  private static final String ARTICLE_NAME = "TwitterWidget";
  private static final String TAGS[] = {
      "<twitter widget-id=\"522824386202447873\" screen-name=\"sfbart\" />",
      "<twitter widget-id=\"522824386202447873\" />",
  };

  public TwitterWidgetPageObject(WebDriver driver) {
    super(driver);
  }

  protected String getArticleName() {
    return ARTICLE_NAME;
  }

  protected String getTagName() {
    return TAG_NAME;
  }

  protected String[] getTags() {
    return TAGS;
  }

  protected boolean isTagLoadedOnMercury() {
    return isWidgetVisible(twitterIframe, twitterBody);
  }

  protected boolean isTagLoadedOnOasis() {
    return isWidgetVisible(twitterIframe, twitterBody);
  }

  protected boolean areTagsLoadedOnOasis() {
    for (WebElement iframe: twitterIframeList) {
      if (!isWidgetVisible(iframe, twitterBody)) {
        return false;
      }
    }
    return true;
  }

  protected boolean areTagsLoadedOnMercury() {
    for (WebElement iframe: twitterIframeList) {
      if (!isWidgetVisible(iframe, twitterBody)) {
        return false;
      }
    }
    return true;
  }
}
