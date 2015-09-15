package com.wikia.webdriver.pageobjectsfactory.pageobject.widget;

import org.apache.commons.lang.NotImplementedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @ownership: Content X-Wing
 */
public class SoundCloudWidgetPageObject extends WidgetPageObject {

  @FindBy(css = "iframe[data-wikia-widget=\"soundcloud\"]")
  private WebElement soundCloudIframe;
  @FindBy(css = "iframe[data-wikia-widget=\"soundcloud\"]")
  private List<WebElement> soundCloudIframeList;
  @FindBy(css = "div.widget")
  private WebElement soundCloudBody;

  private static final String TAG_NAME = "soundcloud";
  private static final String ARTICLE_NAME = "SoundCloudWidget";
  private static final String[] TAGS = {
      "<soundcloud width=\"100%\" height=\"166\" scrolling=\"no\" frameborder=\"no\" " +
      "url=\"https://api.soundcloud.com/tracks/34019569\" color=\"0066cc\" />",
      "<soundcloud width=\"100%\" height=\"166\" scrolling=\"no\" frameborder=\"no\" " +
      "url=\"https://api.soundcloud.com/tracks/34019569\" color=\"0066cc\" />",
  };

  public SoundCloudWidgetPageObject(WebDriver driver) {
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

  /**
   * There is no such thing as incorrect tag in soundcloud
   * @throws NotImplementedException
   */
  protected String getIncorrectTag() {
    throw new NotImplementedException();
  }

  /**
   * There is no such thing as incorrect tag in soundcloud
   * @throws NotImplementedException
   */
  protected String getErrorMessage() {
      throw new NotImplementedException();
  }

  protected boolean isTagLoadedOnMercury() {
    return isWidgetVisible(soundCloudIframe, soundCloudBody);
  }

  protected boolean isTagLoadedOnOasis() {
    return isWidgetVisible(soundCloudIframe, soundCloudBody);
  }

  protected boolean areTagsLoadedOnOasis() {
    for (WebElement iframe: soundCloudIframeList) {
      if (!isWidgetVisible(iframe, soundCloudBody)) {
        return false;
      }
    }
    return true;
  }

  protected boolean areTagsLoadedOnMercury() {
    for (WebElement iframe: soundCloudIframeList) {
      if (!isWidgetVisible(iframe, soundCloudBody)) {
        return false;
      }
    }
    return true;
  }
}
