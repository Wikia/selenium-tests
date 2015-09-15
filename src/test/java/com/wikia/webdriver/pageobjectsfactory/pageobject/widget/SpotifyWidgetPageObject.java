package com.wikia.webdriver.pageobjectsfactory.pageobject.widget;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @ownership: Content X-Wing
 */
public class SpotifyWidgetPageObject extends WidgetPageObject {

  @FindBy(css = "iframe[data-wikia-widget='spotify']")
  private WebElement widgetIframe;
  @FindBy(css = "iframe[data-wikia-widget='spotify']")
  private List<WebElement> widgetIframeList;
  @FindBy(css = "#widgetContainer")
  private WebElement widgetBody;

  private static final String TAG_NAME = "spotify";
  private static final String ARTICLE_NAME = "spotifyWidget";
  private static final String[] TAGS = {
      "<spotify uri=\"spotify:track:5JunxkcjfCYcY7xJ29tLai\" />",
      "<spotify uri=\"spotify:track:5JunxkcjfCYcY7xJ29tLai\" />",
  };
  private static final String INCORRECT_TAG = "<spotify />";
  private static final String ERROR_MESSAGE = "Failed to render the Spotify widget. Please check if all required parameters are in place.";

  public SpotifyWidgetPageObject(WebDriver driver) {
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
