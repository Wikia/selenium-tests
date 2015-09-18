package com.wikia.webdriver.pageobjectsfactory.pageobject.widget;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @ownership: Content X-Wing
 */
public class PolldaddyWidgetPageObject extends WidgetPageObject {

  @FindBy(css = ".PDS_Poll *")
  private WebElement polldaddyBody;

  private static final String TAG_NAME = "pollydaddy";
  private static final String ARTICLE_NAME = "PolldaddyWidget";
  private static final String TAG =
      "<polldaddy id=\"8956579\">";
  private static final String INCORRECT_TAG = "<polldaddy />";
  private static final String ERROR_MESSAGE =
      "Failed to render the Polldaddy widget. Please check if all required parameters are in place.";

  public PolldaddyWidgetPageObject(WebDriver driver) {
    super(driver);
  }

  protected String getArticleName() {
    return ARTICLE_NAME;
  }

  protected String getTagName() {
    return TAG_NAME;
  }

  public String getTag() {
    return TAG;
  }

  @Override
  protected String[] getTags() {
    return null;
  }

  protected String getIncorrectTag() {
    return INCORRECT_TAG;
  }

  protected String getErrorMessage() {
    return ERROR_MESSAGE;
  }

  @Override
  protected List<WebElement> getWidgetWrapperList() {
    return null;
  }

  @Override
  protected List<WebElement> getWidgetIFrameList() {
    return null;
  }

  @Override
  protected WebElement getWidgetIFrame() {
    return null;
  }

  @Override
  protected WebElement getWidgetBody() {
    return polldaddyBody;
  }

}
