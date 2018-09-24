package com.wikia.webdriver.pageobjectsfactory.pageobject.widget;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TwitterWidgetPageObject extends WidgetPageObject {

  private static final String TAG_NAME = "twitter";
  private static final String[] TAGS = {
      //twitter.com/TwitterDev
      "<twitter screen-name=\"TwitterDev\" />",

      //twitter.com/Nukapedia
      "<twitter screen-name=\"nukapedia\" />",

      //twitter.com/Nukapedia overridden to getFANDOM
      "<twitter screen-name=\"nukapedia\" screen-name=\"getFANDOM\" />",};
  private static final String INCORRECT_TAG = "<twitter />";
  private static final String
      ERROR_MESSAGE
      = "Error: No Twitter Widget ID provided. Please see Help:Social media integration.";
  @FindBy(css = ".widget-twitter")
  private List<WebElement> widgetWrapperList;
  @FindBy(css = ".widget-twitter iframe")
  private List<WebElement> widgetIFrameList;
  @FindBy(css = "div.timeline-Widget")
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
    return widgetWrapperList;
  }

  protected List<WebElement> getWidgetIFrameList() {
    return widgetIFrameList;
  }

  protected WebElement getWidgetBody() {
    return widgetBody;
  }
}
