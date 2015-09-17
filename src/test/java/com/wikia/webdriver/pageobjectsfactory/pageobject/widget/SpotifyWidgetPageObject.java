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
  private static final String INCORRECT_TAG = "<spotify />";
  private static final String ERROR_MESSAGE =
    "Failed to render the Spotify widget. Please check if all required parameters are in place.";


  public SpotifyWidgetPageObject(WebDriver driver) {
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
