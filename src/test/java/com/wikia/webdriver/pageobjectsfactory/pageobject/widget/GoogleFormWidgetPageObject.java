package com.wikia.webdriver.pageobjectsfactory.pageobject.widget;

import java.util.List;

import org.apache.commons.lang.NotImplementedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleFormWidgetPageObject extends WidgetPageObject {

  private static final String TAG_NAME = "googleform";
  private static final String[] TAGS = {
      "<googleform url=\"https://docs.google.com/forms/d/e/1FAIpQLSf5EluNgyx1NDGDMcSrHhZUyMFctSjrY8yR1N3sg8INk3v7ew/viewform?embedded=true\" />",
      "<googleform url=\"https://docs.google.com/forms/d/e/1FAIpQLSdtT9ru9GeKbYk1uACHalivOIHR_mzEr4EfTqYXESXJhNznHw/viewform?embedded=true\" />",
      };
  private static final String INCORRECT_TAG = "<googleform />";
  private static final String ERROR_MESSAGE =
      "Failed to render the Google Form widget. Please check if \"url\" param"
          + " is properly copied from Embed dialog in Google.";
  @FindBy(css = "iframe[data-wikia-widget=\"googleform\"]")
  private List<WebElement> widgetIFrameList;
  @FindBy(css = "div.freebirdFormviewerViewFormContentWrapper")
  private WebElement widgetBody;

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
        "Google Form widgets are loaded directly as inline frames and have no wrapper.");
  }

  protected List<WebElement> getWidgetIFrameList() {
    return widgetIFrameList;
  }

  protected WebElement getWidgetBody() {
    return widgetBody;
  }
}
