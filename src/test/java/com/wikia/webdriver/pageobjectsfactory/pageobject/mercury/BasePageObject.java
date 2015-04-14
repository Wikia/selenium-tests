package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * @authors: Rodrigo Gomez, Łukasz Nowak, Tomasz Napieralski
 * @ownership: Content - Mercury mobile
 */
public class BasePageObject extends MobileBasePageObject {

  @FindBy(css = ".loading-overlay")
  private WebElement loadingSpinner;

  public BasePageObject(WebDriver driver) {
    super(driver);
  }

  public ArticlePageObject openMercuryArticleByName(String wikiURL, String articleName) {
    getUrl(wikiURL + URLsContent.WIKI_DIR + articleName);
    PageObjectLogging
        .log("openMercuryArticleByName", "Article" + articleName + " was opened", true);
    return new ArticlePageObject(driver);
  }

  public void openMercuryWiki(String wikiName) {
    String mercuryWiki = urlBuilder.getUrlForWiki(wikiName);
    getUrl(mercuryWiki);
  }

  public void tapOnElement(WebElement element) {
    JavascriptExecutor jsexec = (JavascriptExecutor) driver;
    jsexec.executeScript("arguments[0].click();", element);
  }

  public void waitMilliseconds(int time, String message) {
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      PageObjectLogging.log(message, e.getMessage(), false);
    }
  }

  /**
   * First waits for spinner to be visible and then waits for spinner to be hidden
   * Spinner presence is optional, when it occurs it must be hidden later
   */
  public void waitForLoadingSpinnerToFinishReloadingPage() {
    boolean spinnerPresent = false;
    try {
      waitForElementVisibleByElement(loadingSpinner, 4, 1000);
      spinnerPresent = true;
    } catch (TimeoutException e) {
      PageObjectLogging.log("Loading spinner", "is not present", true);
    }
    if(spinnerPresent) {
      waitForElementPresenceByBy(By.cssSelector(".loading-overlay.hidden"));
    }
  }

  //TODO: when QAART-576 will be solved, remove that method and all connections
  /**
   * It will fail TestNG if fail=true is set
   * It is temporary solution for QAART-576
   * @param fail
   */
  public void failTest(boolean fail) {
    Assertion.assertFalse(fail, "Test logged some errors");
  }
}
