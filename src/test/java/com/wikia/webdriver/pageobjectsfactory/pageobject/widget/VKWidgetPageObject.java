package com.wikia.webdriver.pageobjectsfactory.pageobject.widget;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @ownership: Content X-Wing
 */
public class VKWidgetPageObject extends WidgetPageObject {

  @FindBy(css = ".widget-vk iframe")
  private WebElement vkIframe;
  @FindBy(css = ".widget-vk iframe")
  private List<WebElement> vkIframeList;
  @FindBy(css = ".widget_body")
  private WebElement vkBody;

  private static final String TAG_NAME = "vk";
  private static final String ARTICLE_NAME = "VKWidget";
  private static final String[] TAGS = {
      "<vk group-id=\"59925174\" />",
      "<vk group-id=\"12345\" />",
  };
  private static final String INCORRECT_TAG = "<vk />";
  private static final String ERROR_MESSAGE = "Failed to render the VK widget. Please check if all required parameters are in place.";

  public VKWidgetPageObject(WebDriver driver) {
    super(driver);
  }

  protected String getArticleName() {
    return ARTICLE_NAME;
  }

  protected String getTagName() {
    return TAG_NAME;
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

  protected boolean isTagLoadedOnMercury() {
    return isWidgetVisible(vkIframe, vkBody);
  }

  protected boolean isTagLoadedOnOasis() {
    return isWidgetVisible(vkIframe, vkBody);
  }

  protected boolean areTagsLoadedOnOasis() {
    for (WebElement iframe: vkIframeList) {
      if (!isWidgetVisible(iframe, vkBody)) {
        return false;
      }
    }
    return true;
  }

  protected boolean areTagsLoadedOnMercury() {
    for (WebElement iframe: vkIframeList) {
      if (!isWidgetVisible(iframe, vkBody)) {
        return false;
      }
    }
    return true;
  }
}
