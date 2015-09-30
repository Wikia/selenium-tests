package com.wikia.webdriver.pageobjectsfactory.pageobject.widget;

import java.util.List;

import org.apache.commons.lang.NotImplementedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @ownership: Content X-Wing
 */
public class PollsnackWidgetPageObject extends WidgetPageObject {

  private static final String TAG_NAME = "pollsnack";
  private static final String ARTICLE_NAME = "PollsnackWidget";
  private static final String[] TAGS = {"<pollsnack hash=\"q7kiw9kz\"/>",
      "<pollsnack hash=\"q7kiw9kz\"/>",};
  private static final String INCORRECT_TAG = "<pollsnack />";
  private static final String ERROR_MESSAGE =
      "Failed to render the PollSnack widget. Please check if all required parameters are in place.";
  @FindBy(css = "iframe[data-wikia-widget=\"pollsnack\"]")
  private List<WebElement> widgetIFrameList;
  @FindBy(css = "body")
  private WebElement widgetBody;

  public PollsnackWidgetPageObject(WebDriver driver) {
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
    return INCORRECT_TAG;
  }

  protected String getErrorMessage() {
    return ERROR_MESSAGE;
  }

  protected List<WebElement> getWidgetWrapperList() {
    throw new NotImplementedException(
        "Pollsnack widgets are loaded directly as inline frames and have no wrapper.");
  }

  protected List<WebElement> getWidgetIFrameList() {
    return widgetIFrameList;
  }

  protected WebElement getWidgetBody() {
    return widgetBody;
  }
}
