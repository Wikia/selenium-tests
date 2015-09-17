package com.wikia.webdriver.pageobjectsfactory.pageobject.widget;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @ownership: Content X-Wing
 */
public class PolldaddyWidgetPageObject extends WidgetPageObject {

  @FindBy(css = "iframe[data-wikia-widget=\"googleform\"]")
  private WebElement polldaddyIframe;
  @FindBy(css = "div.widget")
  private WebElement polldaddyBody;

  private static final String TAG_NAME = "pollydaddy";
  private static final String ARTICLE_NAME = "PolldaddyWidget";
  private static final String TAG =
      "<polldaddy id=\"8956579\">";
  private static final String INCORRECT_TAG = "<pollydaddy />";
  private static final String ERROR_MESSAGE =
      "Failed to render the Polldaddy widget. Please check if \"url\" param" +
      " is properly coped from Embed dialog in Google.";

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

  protected String getIncorrectTag() {
    return INCORRECT_TAG;
  }

  protected String getErrorMessage() {
    return ERROR_MESSAGE;
  }

  protected boolean isTagLoadedOnMercury() {
    if (!isElementVisible(polldaddyIframe)) {
      return false;
    }

    driver.switchTo().frame(polldaddyIframe);
    boolean result = isElementVisible(polldaddyBody);
    driver.switchTo().parentFrame();

    return result;
  }

  protected boolean isTagLoadedOnOasis() {
    if (!isElementVisible(polldaddyIframe)) {
      return false;
    }

    driver.switchTo().frame(polldaddyIframe);
    boolean result = isElementVisible(polldaddyBody);
    driver.switchTo().parentFrame();

    return result;
  }
}
