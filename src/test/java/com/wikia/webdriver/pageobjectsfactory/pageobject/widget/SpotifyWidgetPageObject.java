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
  private WebElement spotifyIframe;
  @FindBy(css = "iframe[data-wikia-widget='spotify']")
  private List<WebElement> spotifyIframeList;
  @FindBy(css = "#widgetContainer")
  private WebElement spotifyBody;

  private static final String TAG_NAME = "spotify";
  private static final String ARTICLE_NAME = "spotifyWidget";
  private static final String TAGS[] = {
      "<spotify uri=\"spotify:track:5JunxkcjfCYcY7xJ29tLai\" />",
      "<spotify uri=\"spotify:track:5JunxkcjfCYcY7xJ29tLai\" />",
  };

  public SpotifyWidgetPageObject(WebDriver driver) {
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
    return isWidgetVisible(spotifyIframe, spotifyBody);
  }

  protected boolean isTagLoadedOnOasis() {
    return isWidgetVisible(spotifyIframe, spotifyBody);
  }

  protected boolean areTagsLoadedOnOasis() {
    for (WebElement iframe: spotifyIframeList) {
      if (!isWidgetVisible(iframe, spotifyBody)) {
        return false;
      }
    }
    return true;
  }

  protected boolean areTagsLoadedOnMercury() {
    for (WebElement iframe: spotifyIframeList) {
      if (!isWidgetVisible(iframe, spotifyBody)) {
        return false;
      }
    }
    return true;
  }
}
