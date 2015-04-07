package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

  public static void turnOnMercurySkin(WebDriver driver, String wikiURL) {
    BasePageObject base = new BasePageObject(driver);
    base.openArticleByName(wikiURL, "Special:Mercury");
    WebElement optInButton = driver.findElement(By.cssSelector("button[name=opt]"));
    base.waitForElementVisibleByElement(optInButton);
    if ("in".equals(optInButton.getAttribute("value"))) {
      optInButton.click();
    }
  }

  public void waitForLoadingSpinnerToFinishReloadingPage() {
    waitForElementByElement(loadingSpinner);
    waitForElementPresenceByBy(By.cssSelector(".loading-overlay.hidden"));
  }
}
