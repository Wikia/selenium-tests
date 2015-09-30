package com.wikia.webdriver.pageobjectsfactory.pageobject.widget;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @ownership: Content X-Wing
 */
public class VKWidgetPageObject extends WidgetPageObject {

  private static final String TAG_NAME = "vk";
  private static final String ARTICLE_NAME = "VKWidget";
  private static final String[] TAGS = {"<vk group-id=\"59925174\" />",
      "<vk group-id=\"53477573\" />",};
  private static final String INCORRECT_TAG = "<vk />";
  private static final String ERROR_MESSAGE =
      "Failed to render the VK widget. Please check if all required parameters are in place.";
  @FindBy(css = ".widget-vk")
  private List<WebElement> widgetWrapperList;
  @FindBy(css = ".widget-vk iframe")
  private List<WebElement> widgetIFrameList;
  @FindBy(css = ".widget_body")
  private WebElement widgetBody;

  public VKWidgetPageObject(WebDriver driver) {
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
    return widgetWrapperList;
  }

  protected List<WebElement> getWidgetIFrameList() {
    return widgetIFrameList;
  }

  protected WebElement getWidgetBody() {
    return widgetBody;
  }
}
