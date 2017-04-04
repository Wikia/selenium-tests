package com.wikia.webdriver.pageobjectsfactory.pageobject.widget;

import org.apache.commons.lang.NotImplementedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class WeiboWidgetPageObject extends WidgetPageObject {

  @FindBy(css = "iframe[data-wikia-widget='weibo']")
  private List<WebElement> widgetIFrameList;
  @FindBy(css = "div.tsina_open")
  private WebElement widgetBody;

  private static final String TAG_NAME = "weibo";
  private static final String[] TAGS = {
      "<weibo uids=\"1642909335,1782515283\" />",
      "<weibo uids=\"1642909335,1782515283\" />",
  };
  private static final String INCORRECT_TAG = "<weibo />";
  private static final String ERROR_MESSAGE =
      "Failed to render the Weibo widget. Please check if all required parameters are in place.";

  public WeiboWidgetPageObject(WebDriver driver) {
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
        "Weibo widgets are loaded directly as inline frames and have no wrapper."
    );
  }

  protected List<WebElement> getWidgetIFrameList() {
    return widgetIFrameList;
  }

  protected WebElement getWidgetBody() {
    return widgetBody;
  }
}
