package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import com.wikia.webdriver.common.contentpatterns.MercuryArticles;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileBasePageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePageObject extends MobileBasePageObject {

  public BasePageObject(WebDriver driver) {
    super(driver);
  }

  public SpecialMercuryPageObject openSpecialMercury(String wikiURL) {
    getUrl(wikiURL + MercuryArticles.MERCURY_SPECIAL_PAGE);
    PageObjectLogging
        .log("openSpecialMercury", MercuryArticles.MERCURY_SPECIAL_PAGE + " opened", true);
    return new SpecialMercuryPageObject(driver);
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
  
  public void waitMilliseconds(int time, String methodName) {
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      PageObjectLogging.log(methodName, e.getMessage(), false);
    }
  }

  public static void turnOnMercurySkin(WebDriver driver, String wikiURL) {
    BasePageObject base = new BasePageObject(driver);
    SpecialMercuryPageObject mercuryPage = base.openSpecialMercury(wikiURL);
    mercuryPage.clickMercuryButton();
  }
}
