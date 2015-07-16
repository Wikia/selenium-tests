package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.urlbuilder.UrlBuilder;
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

  @FindBy(css = ".loading-overlay")
  private WebElement loadingSpinner;

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
  public void waitForLoadingSpinnerToFinishReloadingPage() {
    boolean spinnerPresent = false;
    try {
      waitForElementVisibleByElement(loadingSpinner, 4, 1000);
      spinnerPresent = true;
    } catch (TimeoutException e) {
      PageObjectLogging.log("Loading spinner", "is not present", true);
    }
    if (spinnerPresent) {
      waitForElementPresenceByBy(By.cssSelector(".loading-overlay.hidden"));
    }
  }

  public boolean isUrlPathEqualTo(String path) {
    String currentPath = new UrlBuilder().getUrlPath(driver);
    return currentPath.equalsIgnoreCase(path);
  }

  public void isUrlPathEqualTo(String current, String expected) {
    boolean result = current.equalsIgnoreCase(expected);
    PageObjectLogging.log("Current URL", "is set on " + expected,
                          "is set on " + current + " instead of "+expected,
                          result);
  }

  public String getCurrentPath() {
    String currentPath = new UrlBuilder().getUrlPath(driver);
    return currentPath;
  }

  public String getCurrentUrlPath() {
    String currentPath = new UrlBuilder().getUrlPath(driver);
    return currentPath;
  }
}
