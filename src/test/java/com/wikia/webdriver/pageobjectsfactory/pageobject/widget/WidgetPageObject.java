package com.wikia.webdriver.pageobjectsfactory.pageobject.widget;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @ownership: Content X-Wing
 */
public abstract class WidgetPageObject extends BasePageObject {

  @FindBy(css = "strong.error")
  protected WebElement error;

  protected WidgetPageObject(WebDriver driver) {
    super(driver);
  }

  protected abstract String getArticleName();

  protected abstract String getTagName();

  public abstract String getTag();

  /**
   * Get all tags defined in the widget page object
   */
  protected abstract String[] getTags();

  protected abstract String getIncorrectTag();

  protected abstract String getErrorMessage();

  protected abstract List<WebElement> getWidgetWrapperList();

  protected abstract List<WebElement> getWidgetIFrameList();

  protected abstract WebElement getWidgetIFrame();

  protected abstract WebElement getWidgetBody();

  protected abstract Boolean isWidgetInIFrame();

  public WidgetPageObject create() {
    ArticleContent articleContent = new ArticleContent();
    articleContent.push(getTag(), getArticleName());
    return this;
  }

  /**
   * Create all tags as defined in widget page object tags field
   */
  public WidgetPageObject createMultiple() {
    String text = "";
    ArticleContent articleContent = new ArticleContent();

    articleContent.clear(getArticleName());

    for (String tag : getTags()) {
      text += tag + "\n";
    }

    articleContent.push(text, getArticleName());

    return this;
  }

  /**
   * Create incorrect widget tag
   */
  public WidgetPageObject createIncorrect() {
    ArticleContent articleContent = new ArticleContent();
    articleContent.clear(getArticleName());
    articleContent.push(getIncorrectTag(), getArticleName());
    return this;
  }

  public WidgetPageObject navigate(String wikiUrl) {
    openMercuryArticleByNameWithCbAndNoAds(wikiUrl, getArticleName());
    return this;
  }

  public WidgetPageObject createAndNavigate(String wikiUrl) {
    return create().navigate(wikiUrl);
  }

  public boolean isLoaded() {
    boolean result;
    if (isWidgetInIFrame()) {
      result = isWidgetVisible(getWidgetIFrame(), getWidgetBody());
    } else {
      result = isElementVisible(getWidgetBody());
    }

    logVisibility(result);
    return result;
  }

  public boolean areAllValidSwappedForIFrames() {
    boolean result = getWidgetWrapperList().size() == getWidgetIFrameList().size();
    PageObjectLogging.log(getTagName(), MercuryMessages.ALL_VALID_WIDGETS_ARE_SWAPPED_MSG, result);
    return result;
  }

  /**
   * Check if there are more than one widget loaded
   */
  public boolean areLoaded() {
    boolean result = true;
    int i = 1;
    List<WebElement> widgetIFrameList = getWidgetIFrameList();

    if (widgetIFrameList.isEmpty()) {
      result = false;
    } else {
      for (WebElement widgetIFrame : widgetIFrameList) {
        if (!isWidgetVisible(widgetIFrame, getWidgetBody())) {
          result = false;
          PageObjectLogging.log(getTagName() + " #" + i, MercuryMessages.VISIBLE_MSG, result);
          break;
        }
        PageObjectLogging.log(getTagName() + " #" + i++, MercuryMessages.VISIBLE_MSG, result);
      }
    }

    PageObjectLogging.log("all " + getTagName() + " widgets", MercuryMessages.VISIBLE_MSG, result);
    return result;
  }

  public boolean isErrorPresent() {
    boolean result = isElementVisible(error) && error.getText().equals(getErrorMessage());
    logVisibility(result);
    return result;
  }

  private void logVisibility(boolean result) {
    PageObjectLogging
        .log(getTagName(), result ? MercuryMessages.VISIBLE_MSG : MercuryMessages.INVISIBLE_MSG,
             result);
  }

  /**
   * If a widget is wrapped into IFrame, verify it's visibility
   */
  private boolean isWidgetVisible(WebElement widgetIFrame, WebElement widgetBody) {
    if (!isElementVisible(widgetIFrame)) {
      return false;
    }

    driver.switchTo().frame(widgetIFrame);
    boolean result = isElementVisible(widgetBody);
    driver.switchTo().parentFrame();

    return result;
  }
}
