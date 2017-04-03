package com.wikia.webdriver.pageobjectsfactory.pageobject.widget;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by ryba on 30/03/2017.
 */
public class PlaybuzzWidgetPageObject extends WidgetPageObject {

  @FindBy(css = ".pb_feed iframe")
  private List<WebElement> widgetIFrameList;

  @FindBy(css = ".pb_feed")
  private List<WebElement> widgetWrapperList;

  @FindBy(css = "body")
  private List<WebElement> widgetBody;

  private static final String[] TAGS = {
      "<playbuzz data-item=\"b534ce26-d47f-455f-9e80-7702ee4c5c2b\" />",
      "<playbuzz data-item=\"b534ce26-d47f-455f-9e80-7702ee4c5c2b\" />"
  };

  public PlaybuzzWidgetPageObject(WebDriver driver) {
    super(driver);
  }

  @Override
  protected String getTagName() {
    return "playbuzz";
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
    return "<playbuzz />";
  }

  @Override
  protected String getErrorMessage() {
    return "Failed to render the Playbuzz widget. Please check if all required parameters are in place.";
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
