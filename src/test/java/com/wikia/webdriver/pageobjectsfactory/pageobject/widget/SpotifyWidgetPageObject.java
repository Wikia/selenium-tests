package com.wikia.webdriver.pageobjectsfactory.pageobject.widget;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @ownership: Content X-Wing
 */
public class SpotifyWidgetPageObject extends WidgetPageObject {

  @FindBy(css = "iframe[data-wikia-widget='spotify']")
  private WebElement spotifyIframe;
  @FindBy(css = "#widgetContainer")
  private WebElement spotifyBody;

  private static final String TAG_NAME = "spotify";
  private static final String ARTICLE_NAME = "spotifyWidget";
  private static final String TAG = "<spotify uri=\"spotify:track:5JunxkcjfCYcY7xJ29tLai\" />";

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
    return TAG;
  }

  protected boolean isTagLoadedOnMercury() {
    if (!isElementVisible(spotifyIframe)) {
      return false;
    }

    driver.switchTo().frame(spotifyIframe);
    boolean result = isElementVisible(spotifyBody);
    driver.switchTo().parentFrame();

    return result;
  }

  protected boolean isTagLoadedOnOasis() {
    if (!isElementVisible(spotifyIframe)) {
      return false;
    }

    driver.switchTo().frame(spotifyIframe);
    boolean result = isElementVisible(spotifyBody);
    driver.switchTo().parentFrame();

    return result;
  }
}
