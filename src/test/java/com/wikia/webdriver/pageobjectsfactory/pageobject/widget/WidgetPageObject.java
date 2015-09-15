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

  protected abstract String[] getTags();

  protected abstract String getIncorrectTag();

  protected abstract String getErrorMessage();

  protected abstract WebElement getWidget();

  protected abstract List<WebElement> getWidgetList();

  protected abstract WebElement getWidgetBody();

  public WidgetPageObject create() {
    ArticleContent articleContent = new ArticleContent();
    articleContent.push(getTag(), getArticleName());
    return this;
  }

  public WidgetPageObject createMultiple() {
    String[] tags = getTags();
    String articleContentString = "";
    ArticleContent articleContent = new ArticleContent();

    articleContent.clear(getArticleName());

    for (String tag: tags) {
      articleContentString += tag + "\n";
    }

    articleContent.push(articleContentString, getArticleName());

    return this;
  }

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

  public boolean isLoaded() {
    boolean result = isWidgetVisible(getWidget(), getWidgetBody());
    PageObjectLogging.log(getTagName(), MercuryMessages.VISIBLE_MSG, result);
    return result;
  }

  public boolean areLoaded() {
    boolean result = true;

    for (WebElement iframe: getWidgetList()) {
      if (!isWidgetVisible(iframe, getWidgetBody())) {
        result = false;
        break;
      }
    }

    PageObjectLogging.log(getTagName(), MercuryMessages.VISIBLE_MSG, result);
    return result;
  }

  public boolean isErrorPresent() {
    boolean result = isElementVisible(error) && error.getText().equals(getErrorMessage());
    PageObjectLogging.log(getTagName(), MercuryMessages.VISIBLE_MSG, result);
    return result;
  }

  private boolean isWidgetVisible(WebElement widgetIframe, WebElement widgetBody) {
    if (!isElementVisible(widgetIframe)) {
      return false;
    }

    driver.switchTo().frame(widgetIframe);
    boolean result = isElementVisible(widgetBody);
    driver.switchTo().parentFrame();

    return result;
  }
}
