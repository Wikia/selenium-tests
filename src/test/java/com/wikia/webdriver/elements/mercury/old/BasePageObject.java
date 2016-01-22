package com.wikia.webdriver.elements.mercury.old;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.elements.mercury.old.curatedcontent.CuratedContentPageObject;
import com.wikia.webdriver.elements.mercury.old.curatedcontent.CuratedMainPagePageObject;

import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BasePageObject extends WikiBasePageObject {

  private static final By LOADING_SPINNER_BY = By.cssSelector(".loading-overlay");
  private static final By WIKIA_MOBILE_WIKI_TITLE = By.cssSelector("#wkWrdMrk");

  public BasePageObject(WebDriver driver) {
    super(driver);
  }

  public ArticlePageObject openMercuryArticleByName(String wikiURL, String articleName) {
    getUrl(wikiURL + URLsContent.WIKI_DIR + articleName + "?cb=" + DateTime.now().getMillis());
    PageObjectLogging
        .log("openMercuryArticleByName", "Article" + articleName + " was opened", true);
    return new ArticlePageObject(driver);
  }

  public ArticlePageObject openArticleOnWikiByNameWithCbAndNoAds(String wikiURL,
                                                                 String articleName) {
    getUrl(
        wikiURL + URLsContent.WIKI_DIR + articleName +
        "?cb=" + DateTime.now().getMillis() +
        "&noads=1"
    );
    PageObjectLogging
        .log("openMercuryArticleByName", "Article" + articleName + " was opened", true);
    return new ArticlePageObject(driver);
  }

  public ArticlePageObject openMercuryArticleByNameWithNoCacheBuster(String wikiURL,
                                                                     String articleName) {
    getUrl(wikiURL + URLsContent.WIKI_DIR + articleName);
    PageObjectLogging
        .log("openMercuryArticleByName", "Article" + articleName + " was opened", true);
    return new ArticlePageObject(driver);
  }

  public ArticlePageObject openMercuryArticleByName(String wikiURL, String articleName,
                                                    String hashId) {
    getUrl(wikiURL + URLsContent.WIKI_DIR + articleName +
           "?cb=" + DateTime.now().getMillis() + "#" + hashId);
    PageObjectLogging.log("openMercuryArticleByName", "Article" + articleName + " with #" + hashId +
                                                      " was opened", true);
    return new ArticlePageObject(driver);
  }

  public void tapOnElement(WebElement element) {
    JavascriptExecutor jsexec = (JavascriptExecutor) driver;
    jsexec.executeScript("arguments[0].click();", element);
  }

  /**
   * It will wait and log reason
   *
   * @param time   - in milliseconds
   * @param reason - i.e. Wait for message to disappear
   */
  public void waitMilliseconds(int time, String reason) {
    PageObjectLogging.logWarning("Wait for " + time + " ms", reason);
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      PageObjectLogging.log(reason, e, false);
    }
  }

  public void waitForWikiaMobileToBeLoaded() {
    wait.forElementVisible(WIKIA_MOBILE_WIKI_TITLE);
  }

  /**
   * First waits for loading overlay (the overlay includes lodaing spinner) to be visible and then waits for spinner to be hidden Spinner presence
   * is optional, when it occurs it must be hidden later
   */
  public void waitForLoadingOverlayToDisappear() {
    boolean spinnerPresent = false;
    try {
      wait.forElementVisible(LOADING_SPINNER_BY, 4, 1000);
      spinnerPresent = true;
    } catch (TimeoutException e) {
      PageObjectLogging
          .log("waitForLoadingOverlayToDisappear", "Lodaing overlay is not visible ", true);
    }
    if (spinnerPresent) {
      wait.forElementNotVisible(LOADING_SPINNER_BY, 4, 3000);
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
      wait.forElementVisible(element, Settings.TIME_OUT_IN_SEC.value,
                             Settings.CHECK_OUT_IN_MILLI_SEC.value);
    } catch (TimeoutException e) {
      return false;
    }
    return true;
  }

  public void openHome(String wikiURL) {
    getUrl(wikiURL);
  }

  protected void waitAndSendKeys(WebElement element, String keys) {
    wait.forElementVisible(element);
    element.sendKeys(keys);
  }

  /**
   * Verify if element inside element of the provided list has given text. This method assumes list
   * element is not the final target of verification This method assumes list element is the parent
   * of target element
   *
   * @param list           List that contains the parent element
   * @param elementLocator Locator of target element, that is child of its parent element
   * @param text           Text to be compared
   */
  protected void verifyTextInListElements(List<WebElement> list, By elementLocator, String text) {
    WebElement innerElem;
    for (WebElement elem : list) {
      wait.forElementVisible(elem);
      innerElem = elem.findElement(elementLocator);
      if (innerElem.getText().equals(text)) {
        return;
      }
    }
    throw new WebDriverException(getNoTextInListErrorMessage(text));
  }
  
  private String getNoTextInListErrorMessage(String text) {
    return "element with text " + text + "not found in the list";
  }

}
