package com.wikia.webdriver.pageobjectsfactory.pageobject.widget;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @ownership: Content X-Wing
 */
public class VKWidgetPageObject extends WidgetPageObject {

  @FindBy(css = "[data-wikia-widget='vk'] iframe")
  private WebElement vkIframe;
  @FindBy(css = ".widget_body")
  private WebElement vkBody;

  private static final String TAG_NAME = "vk";
  private static final String ARTICLE_NAME = "VKWidget";
  private static final String TAG =
      "<vk group-id=\"12345\" />";

  public VKWidgetPageObject(WebDriver driver) {
    super(driver);
  }

  protected String getArticleName() {
    return ARTICLE_NAME;
  }

  protected String getTagName() {
    return TAG_NAME;
  }

  protected String getTag() {
    return TAG;
  }

  protected boolean isTagLoadedOnMercury() {
    if (!isElementVisible(vkIframe)) {
      return false;
    }

    driver.switchTo().frame(vkIframe);
    boolean result = isElementVisible(vkBody);
    driver.switchTo().parentFrame();

    return result;
  }

  protected boolean isTagLoadedOnOasis() {
    if (!isElementVisible(vkIframe)) {
      return false;
    }

    driver.switchTo().frame(vkIframe);
    boolean result = isElementVisible(vkBody);
    driver.switchTo().parentFrame();

    return result;
  }
}
