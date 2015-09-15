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

  protected abstract String getTag();

  protected abstract String getIncorrectTag();

  protected abstract String getErrorMessage();

  protected abstract boolean isTagLoadedOnMercury();

  protected abstract boolean isTagLoadedOnOasis();

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
    PageObjectLogging.log(getTagName(), MercuryMessages.VISIBLE_MSG, result);
    return result;
  }

  public boolean isLoadedOnOasis() {
    boolean result = isTagLoadedOnOasis();
    PageObjectLogging.log(getTagName(), MercuryMessages.VISIBLE_MSG, result);
    return result;
  }

  public WidgetPageObject createIncorrectAndNavigate(String wikiURL) {
    return createIncorrect().navigate(wikiURL);
  }

  public boolean isErrorPresentOnOasis() {
    boolean result = isElementVisible(error) && error.getText().equals(getErrorMessage());
    PageObjectLogging.log(getTagName(), MercuryMessages.VISIBLE_MSG, result);
    return result;
  }
}
