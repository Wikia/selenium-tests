package com.wikia.webdriver.pageobjectsfactory.pageobject.widget;

import java.util.List;

import org.apache.commons.lang.NotImplementedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @ownership: Content X-Wing
 */
public class SoundCloudWidgetPageObject extends WidgetPageObject {

  private static final String TAG_NAME = "soundcloud";
  private static final String ARTICLE_NAME = "SoundCloudWidget";
  private static final String[] TAGS = {
      "<soundcloud width=\"100%\" height=\"166\" scrolling=\"no\" frameborder=\"no\" "
          + "url=\"https://api.soundcloud.com/tracks/34019569\" color=\"0066cc\" />",
      "<soundcloud width=\"100%\" height=\"166\" scrolling=\"no\" frameborder=\"no\" "
          + "url=\"https://api.soundcloud.com/tracks/34019569\" color=\"0066cc\" />",};
  @FindBy(css = "iframe[data-wikia-widget=\"soundcloud\"]")
  private List<WebElement> widgetIFrameList;
  @FindBy(css = "div.widget")
  private WebElement widgetBody;

  public SoundCloudWidgetPageObject(WebDriver driver) {
    super(driver);
  }

  protected String getArticleName() {
    return ARTICLE_NAME;
  }

  protected String getTagName() {
    return TAG_NAME;
  }

  public String getTag() {
    return TAGS[0];
  }

  protected String[] getTags() {
    return TAGS;
  }

  protected String getIncorrectTag() {
    throw new NotImplementedException(
        "There is no such thing as incorrect tag in SoundCloud. SoundCloud parser tag with no attributes is still valid.");
  }

  protected String getErrorMessage() {
    throw new NotImplementedException(
        "There is no such thing as incorrect tag in SoundCloud. SoundCloud parser tag with no attributes is still valid.");
  }

  protected List<WebElement> getWidgetWrapperList() {
    throw new NotImplementedException(
        "SoundCloud widgets are loaded directly as inline frames and have no wrapper.");
  }

  protected List<WebElement> getWidgetIFrameList() {
    return widgetIFrameList;
  }

  protected WebElement getWidgetBody() {
    return widgetBody;
  }
}
