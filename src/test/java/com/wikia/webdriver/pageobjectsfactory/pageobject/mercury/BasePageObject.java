package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
   */
  public void waitForLoadingSpinnerToFinishReloadingPage() {
    waitForElementByElement(loadingSpinner);
    waitForElementPresenceByBy(By.cssSelector(".loading-overlay.hidden"));
  }

  /**
   * Example: wait 10 sec for element and check each 0.5 for that element (10 / 0.5 = 20 attempts)
   * @param element WebElement
   * @param timeOutInSec int
   * @param checkOutInMilliSec int
   */
  public void waitForElementVisibleByElementCustomTimeOut(WebElement element, int timeOutInSec, int checkOutInMilliSec) {
    WebDriverWait wait = new WebDriverWait(driver, timeOutInSec);
    driver.manage().timeouts().implicitlyWait(checkOutInMilliSec, TimeUnit.MILLISECONDS);
    try {
      wait.until(ExpectedConditions.visibilityOf(element));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public void failTest(boolean fail) {
    Assertion.assertFalse(fail, "Test logged some errors");
  }
}
