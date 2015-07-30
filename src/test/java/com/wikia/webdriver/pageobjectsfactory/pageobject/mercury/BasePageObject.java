package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileBasePageObject;

import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @ownership: Content X-Wing
 */
public class BasePageObject extends MobileBasePageObject {

  @FindBy(css = LOADING_SPINNER_CSS)
  private WebElement loadingSpinner;

  private final static String LOADING_SPINNER_CSS = ".loading-overlay";


  public BasePageObject(WebDriver driver) {
    super(driver);
  }

  public ArticlePageObject openMercuryArticleByName(String wikiURL, String articleName) {
    getUrl(wikiURL + URLsContent.WIKI_DIR + articleName + "?cb=" + DateTime.now().getMillis());
    PageObjectLogging
        .log("openMercuryArticleByName", "Article" + articleName + " was opened", true);
    return new ArticlePageObject(driver);
  }

  public void tapOnElement(WebElement element) {
    JavascriptExecutor jsexec = (JavascriptExecutor) driver;
    jsexec.executeScript("arguments[0].click();", element);
  }

  /**
   * It will wait and log reason
   * @param time - in milliseconds
   * @param reason - i.e. Wait for message to disappear
   */
  public void waitMilliseconds(int time, String reason) {
    PageObjectLogging.logWarning("Wait for " + time + " ms", reason);
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      PageObjectLogging.log(reason, e.getMessage(), false);
    }
  }

  /**
   * First waits for spinner to be visible and then waits for spinner to be hidden Spinner presence
   * is optional, when it occurs it must be hidden later
   */
  public void waitForLoadingSpinnerToFinish() {
    boolean spinnerPresent = false;
    try {
      waitForElementByCss(LOADING_SPINNER_CSS);
      waitForElementVisibleByElement(loadingSpinner, 4, 1000);
      spinnerPresent = true;
    } catch (TimeoutException e) {
      PageObjectLogging.log("Loading spinner", "is not present", true);
    }
    if (spinnerPresent) {
      waitForElementPresenceByBy(By.cssSelector(".loading-overlay.hidden"));
    }
  }

  //TODO: Remove this and use combination from logUrl
  //Ticket: https://wikia-inc.atlassian.net/browse/CONCF-894
  public boolean isUrlPathEqualTo(String path) {
    String currentPath = new UrlBuilder().getUrlPath(driver);
    return currentPath.equals(path);
  }

  public CuratedMainPagePageObject openCuratedMainPage(String wikiURL, String mainPage) {
    getUrl(wikiURL + URLsContent.WIKI_DIR + mainPage + "?cb=" + DateTime.now().getMillis());
    PageObjectLogging
        .log("openCuratedMainPage", "Curated main page" + mainPage + " was opened", true);
    return new CuratedMainPagePageObject(driver);
  }


  public CuratedContentPageObject openCuratedContentPage(String wikiURL, String path) {
    String url;
    Long currentTime = DateTime.now().getMillis();
    UrlBuilder builder = new UrlBuilder();

    url = wikiURL + path;
    url = builder.appendQueryStringToURL(url, "cb=" + currentTime);
    getUrl(url);

    PageObjectLogging
        .log("openCuratedContentPage", "Curated content page" + path + " was opened", true);
    return new CuratedContentPageObject(driver);
  }

  public void navigateToUrlWithPath(String wikiURL, String path) {
    getUrl(wikiURL + path);
  }

  private enum Settings {
    TIME_OUT_IN_SEC(5),
    CHECK_OUT_IN_MILLI_SEC(1000);

    private int value;

    Settings(int value) {
      this.value = value;
    }
  }

  public boolean isElementVisible(WebElement element) {
    try {
      waitForElementVisibleByElement(element, Settings.TIME_OUT_IN_SEC.value,
                                     Settings.CHECK_OUT_IN_MILLI_SEC.value);
    } catch (TimeoutException e) {
      return false;
    }
    return true;
  }
}
