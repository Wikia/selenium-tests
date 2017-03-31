package com.wikia.webdriver.pageobjectsfactory.pageobject.widget;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by ryba on 30/03/2017.
 */
public class ApesterWidgetPageObject extends WidgetPageObject {

  @FindBy(css = ".apester-media iframe")
  private List<WebElement> widgetIFrameList;

  @FindBy(css = ".apester-media")
  private List<WebElement> widgetWrapperList;

  @FindBy(css = "body")
  private List<WebElement> widgetBody;

  private static final String[] TAGS = {
      "<apester data-media-id=\"58d3c0fa6d8f378c033d1d39\" />",
      "<apester data-media-id=\"58d3c0fa6d8f378c033d1d39\" />"
  };

  public ApesterWidgetPageObject(WebDriver driver) {
    super(driver);
  }

  @Override
  protected String getTagName() {
    return "apester";
  }

  @Override
  public String getSingleTag() {
    return TAGS[0];
  }

  @Override
  protected String[] getMultipleTags() {
    return TAGS;
  }

  @Override
  protected String getIncorrectTag() {
    return "<apester />";
  }

  @Override
  protected String getErrorMessage() {
    return "Failed to render the Apester widget. Please check if all required parameters are in place.";
  }

  @Override
  protected List<WebElement> getWidgetWrapperList() {
    return widgetWrapperList;
  }

  @Override
  protected List<WebElement> getWidgetIFrameList() {
    return widgetIFrameList;
  }

  @Override
  protected WebElement getWidgetBody() {
    return widgetBody.get(0);
  }
}
