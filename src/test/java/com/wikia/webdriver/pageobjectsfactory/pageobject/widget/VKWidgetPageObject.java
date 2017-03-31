package com.wikia.webdriver.pageobjectsfactory.pageobject.widget;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class VKWidgetPageObject extends WidgetPageObject {

  @FindBy(css = ".widget-vk")
  private List<WebElement> widgetWrapperList;
  @FindBy(css = ".widget-vk iframe")
  private List<WebElement> widgetIFrameList;
  @FindBy(css = ".widget_body")
  private WebElement widgetBody;

  private static final String TAG_NAME = "vk";
  private static final String[] TAGS = {
      "<vk group-id=\"59925174\" />",
      "<vk group-id=\"53477573\" />",
  };
  private static final String INCORRECT_TAG = "<vk />";
  private static final String ERROR_MESSAGE =
      "Failed to render the VK widget. Please check if all required parameters are in place.";

  public VKWidgetPageObject(WebDriver driver) {
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
    return widgetWrapperList;
  }

  protected List<WebElement> getWidgetIFrameList() {
    return widgetIFrameList;
  }

  protected WebElement getWidgetBody() {
    return widgetBody;
  }
}
