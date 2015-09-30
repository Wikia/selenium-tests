package com.wikia.webdriver.pageobjectsfactory.pageobject.widget;

import java.util.List;

import org.apache.commons.lang.NotImplementedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @ownership: Content X-Wing
 */
public class GoogleFormWidgetPageObject extends WidgetPageObject {

  private static final String TAG_NAME = "googleform";
  private static final String ARTICLE_NAME = "GoogleFormWidget";
  private static final String[] TAGS = {
      "<googleform url=\"https://docs.google.com/a/wikia-inc.com/forms/d/"
          + "1cwWn51i5vXFBy7c5VkRzapj6FXxbjZy48VkEZyP33R4/viewform?embedded=true\" />",

      "<googleform url=\"https://docs.google.com/forms/d/"
          + "1U4YFynXUCktrSAdm-w0oCULTT2YuEXMgLgUYApTwIwg/viewform?embedded=true\" />",};
  private static final String INCORRECT_TAG = "<googleform />";
  private static final String ERROR_MESSAGE =
      "Failed to render the Google Form widget. Please check if \"url\" param"
          + " is properly copied from Embed dialog in Google.";
  @FindBy(css = "iframe[data-wikia-widget=\"googleform\"]")
  private List<WebElement> widgetIFrameList;
  @FindBy(css = "div.ss-form-container")
  private WebElement widgetBody;

  public GoogleFormWidgetPageObject(WebDriver driver) {
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
        "Google Form widgets are loaded directly as inline frames and have no wrapper.");
  }

  protected List<WebElement> getWidgetIFrameList() {
    return widgetIFrameList;
  }

  protected WebElement getWidgetBody() {
    return widgetBody;
  }
}
