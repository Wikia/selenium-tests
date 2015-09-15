package com.wikia.webdriver.pageobjectsfactory.pageobject.widget;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @ownership: Content X-Wing
 */
public class WeiboWidgetPageObject extends WidgetPageObject {

  @FindBy(css = "iframe[data-wikia-widget='weibo']")
  private WebElement weiboIframe;
  @FindBy(css = "iframe[data-wikia-widget='weibo']")
  private List<WebElement> weiboIframeList;
  @FindBy(css = "div.tsina_open")
  private WebElement weiboBody;

  private static final String TAG_NAME = "weibo";
  private static final String ARTICLE_NAME = "WeiboWidget";
  private static final String[] TAGS = {
      "<weibo uids=\"1642909335,1782515283\" />",
      "<weibo uids=\"1642909335,1782515283\" />",
  };
  private static final String INCORRECT_TAG = "<weibo />";
  private static final String ERROR_MESSAGE = "Failed to render the Weibo widget. Please check if all required parameters are in place.";

  public WeiboWidgetPageObject(WebDriver driver) {
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
    return isWidgetVisible(weiboIframe, weiboBody);
  }

  protected boolean isTagLoadedOnOasis() {
    return isWidgetVisible(weiboIframe, weiboBody);
  }

  protected boolean areTagsLoadedOnOasis() {
    for (WebElement vkIframe: weiboIframeList) {
      if (!isWidgetVisible(vkIframe, weiboBody)) {
        return false;
      }
    }
    return true;
  }

  protected boolean areTagsLoadedOnMercury() {
    for (WebElement vkIframe: weiboIframeList) {
      if (!isWidgetVisible(vkIframe, weiboBody)) {
        return false;
      }
    }
    return true;
  }
}
