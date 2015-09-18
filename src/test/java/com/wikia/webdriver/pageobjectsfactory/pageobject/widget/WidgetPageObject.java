package com.wikia.webdriver.pageobjectsfactory.pageobject.widget;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

  protected abstract String getIncorrectTag();

  protected abstract String getErrorMessage();

  protected abstract boolean isTagLoadedOnMercury();

  protected abstract boolean isTagLoadedOnOasis();

  public abstract String getTag();

  public WidgetPageObject create() {
    ArticleContent articleContent = new ArticleContent();
    articleContent.clear(getArticleName());
    articleContent.push(getTag(), getArticleName());
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

  public WidgetPageObject createAndNavigate(String wikiUrl) {
    return create().navigate(wikiUrl);
  }

  public boolean isLoadedOnMercury() {
    boolean result = isTagLoadedOnMercury();
    logVisibility(result);
    return result;
  }

  public boolean isLoadedOnOasis() {
    boolean result = isTagLoadedOnOasis();
    logVisibility(result);
    return result;
  }

  public WidgetPageObject createIncorrectAndNavigate(String wikiURL) {
    return createIncorrect().navigate(wikiURL);
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
}
