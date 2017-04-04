package com.wikia.webdriver.pageobjectsfactory.pageobject.widget;

import org.apache.commons.lang.NotImplementedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SpotifyWidgetPageObject extends WidgetPageObject {

  @FindBy(css = "iframe[data-wikia-widget='spotify']")
  private List<WebElement> widgetIFrameList;
  @FindBy(css = "#container")
  private WebElement widgetBody;

  private static final String TAG_NAME = "spotify";
  private static final String[] TAGS = {
      "<spotify uri=\"spotify:track:5JunxkcjfCYcY7xJ29tLai\" />",
      "<spotify uri=\"spotify:track:5JunxkcjfCYcY7xJ29tLai\" />",
  };
  private static final String INCORRECT_TAG = "<spotify />";
  private static final String ERROR_MESSAGE =
      "Failed to render the Spotify widget. Please check if all required parameters are in place.";

  public SpotifyWidgetPageObject(WebDriver driver) {
    super(driver);
  }

  protected String getTagName() {
    return TAG_NAME;
  }

  public String getSingleTag() {
    return TAGS[0];
  }

  protected String[] getMultipleTags() {
    return TAGS;
  }

  protected String getIncorrectTag() {
    return INCORRECT_TAG;
  }

  protected String getErrorMessage() {
    return ERROR_MESSAGE;
  }

  protected List<WebElement> getWidgetWrapperList() {
    throw new NotImplementedException(
        "Spotify widgets are loaded directly as inline frames and have no wrapper."
    );
  }

  protected List<WebElement> getWidgetIFrameList() {
    return widgetIFrameList;
  }

  protected WebElement getWidgetBody() {
    return widgetBody;
  }
}
