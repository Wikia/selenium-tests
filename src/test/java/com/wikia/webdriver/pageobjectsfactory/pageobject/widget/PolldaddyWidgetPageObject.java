package com.wikia.webdriver.pageobjectsfactory.pageobject.widget;

import org.apache.commons.lang.NotImplementedException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PolldaddyWidgetPageObject extends WidgetPageObject {

  @FindBy(css = ".PDS_Poll")
  private List<WebElement> polldaddyDivList;
  By polldaddyBody = By.cssSelector("*");

  private static final String TAG_NAME = "pollydaddy";
  private static final String[] TAGS = {
      "<polldaddy id=\"8956579\"/>",
      "<polldaddy id=\"9022741\"/>"
  };
  private static final String INCORRECT_TAG = "<polldaddy />";
  private static final String ERROR_MESSAGE =
      "Failed to render the Polldaddy widget. Please check if all required parameters are in place.";

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
        "There is currently no need to implement wrapper list for the widget, none of tests uses it");
  }

  protected List<WebElement> getWidgetIFrameList() {
    throw new NotImplementedException(
        "Polldaddy widget is not kept inside any IFrame");
  }

  protected WebElement getWidgetBody() {
    return polldaddyDivList.get(0).findElement(polldaddyBody);
  }

  protected boolean isWidgetVisible(int widgetIndex) {
    WebElement polldaddyWidget = polldaddyDivList.get(widgetIndex);
    wait.forElementVisible(polldaddyWidget);
    wait.forElementVisible(polldaddyWidget.findElement(polldaddyBody));

    return true;
  }
}
