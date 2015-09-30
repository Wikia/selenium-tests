package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import java.util.List;

import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.CuratedContentPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.CuratedMainPagePageObject;

/**
 * @ownership: Content X-Wing
 */
public class BasePageObject extends WikiBasePageObject {

  private final static By LOADING_SPINNER_BY = By.cssSelector(".loading-overlay");

  public BasePageObject(WebDriver driver) {
    super(driver);
  }

  public ArticlePageObject openMercuryArticleByName(String wikiURL, String articleName) {
    getUrl(wikiURL + URLsContent.WIKI_DIR + articleName + "?cb=" + DateTime.now().getMillis());
    LOG.result("openMercuryArticleByName", "Article" + articleName + " was opened", true);
    return new ArticlePageObject(driver);
  }

  public ArticlePageObject openMercuryArticleByNameWithCbAndNoAds(String wikiURL, String articleName) {
    getUrl(wikiURL + URLsContent.WIKI_DIR + articleName + "?cb=" + DateTime.now().getMillis()
        + "&noads=1");
    LOG.result("openMercuryArticleByName", "Article" + articleName + " was opened", true);
    return new ArticlePageObject(driver);
  }

  public ArticlePageObject openMercuryArticleByNameWithNoCacheBuster(String wikiURL,
      String articleName) {
    getUrl(wikiURL + URLsContent.WIKI_DIR + articleName);
    LOG.result("openMercuryArticleByName", "Article" + articleName + " was opened", true);
    return new ArticlePageObject(driver);
  }

  public ArticlePageObject openMercuryArticleByName(String wikiURL, String articleName,
      String hashId) {
    getUrl(wikiURL + URLsContent.WIKI_DIR + articleName + "?cb=" + DateTime.now().getMillis() + "#"
        + hashId);
    LOG.success("openMercuryArticleByName", "Article" + articleName + " with #" + hashId
        + " was opened");
    return new ArticlePageObject(driver);
  }

  public void tapOnElement(WebElement element) {
    JavascriptExecutor jsexec = (JavascriptExecutor) driver;
    jsexec.executeScript("arguments[0].click();", element);
  }

  /**
   * It will wait and log reason
   *
   * @param time - in milliseconds
   * @param reason - i.e. Wait for message to disappear
   */
  public void waitMilliseconds(int time, String reason) {
    LOG.warning("Wait for " + time + " ms", reason);
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      LOG.error(reason, e);
    }
  }

  /**
   * First waits for spinner to be visible and then waits for spinner to be hidden Spinner presence
   * is optional, when it occurs it must be hidden later
   */
  public void waitForLoadingSpinnerToFinish() {
    boolean spinnerPresent = false;
    try {
      wait.forElementVisible(LOADING_SPINNER_BY, 4, 1000);
      spinnerPresent = true;
    } catch (TimeoutException e) {
      LOG.success("Loading spinner", "is not present");
    }
    if (spinnerPresent) {
      wait.forElementNotVisible(LOADING_SPINNER_BY, 4, 3000);
    }
  }


  // TODO: Remove this and use combination from logUrl
  // Ticket: https://wikia-inc.atlassian.net/browse/CONCF-894
  public boolean isUrlPathEqualTo(String path) {
    String currentPath = new UrlBuilder().getUrlPath(driver);
    return currentPath.equals(path);
  }

  public CuratedMainPagePageObject openCuratedMainPage(String wikiURL, String mainPage) {
    getUrl(wikiURL + URLsContent.WIKI_DIR + mainPage + "?cb=" + DateTime.now().getMillis());
    LOG.result("openCuratedMainPage", "Curated main page" + mainPage + " was opened", true);
    return new CuratedMainPagePageObject(driver);
  }


  public CuratedContentPageObject openCuratedContentPage(String wikiURL, String path) {
    String url;
    Long currentTime = DateTime.now().getMillis();
    UrlBuilder builder = new UrlBuilder();

    url = wikiURL + path;
    url = builder.appendQueryStringToURL(url, "cb=" + currentTime);
    getUrl(url);

    LOG.result("openCuratedContentPage", "Curated content page" + path + " was opened", true);
    return new CuratedContentPageObject(driver);
  }

  public void navigateToUrlWithPath(String wikiURL, String path) {
    getUrl(wikiURL + path);
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

  /**
   * Make sure element is ready to be clicked and click on it The separation of this method has
   * particular reason. It allows global modification of such click usages. This way it is very easy
   * to control what criteria have to be met in order to click on element
   *
   * @param element to be clicked on
   */
  protected void waitAndClick(WebElement element) {
    wait.forElementClickable(element).click();
  }

  protected void waitAndSendKeys(WebElement element, String keys) {
    wait.forElementVisible(element);
    element.sendKeys(keys);
  }

  protected void verifyTextInElement(WebElement element, String text) {
    wait.forElementVisible(element);
    Assertion.assertEquals(element.getText(), text);
  }

  /**
   * Verify if element inside element of the provided list has given text. This method assumes list
   * element is not the final target of verification This method assumes list element is the parent
   * of target element
   *
   * @param list List that contains the parent element
   * @param elementLocator Locator of target element, that is child of its parent element
   * @param text Text to be compared
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

  /**
   * Verify if element of the provided list has given text.
   *
   * @param list List that contains the element
   * @param text Text to be compared
   */
  protected void verifyTextInListElements(List<WebElement> list, String text) {
    for (WebElement elem : list) {
      wait.forElementVisible(elem);
      if (elem.getText().equals(text)) {
        return;
      }
    }
    throw new WebDriverException(getNoTextInListErrorMessage(text));
  }

  /**
   * Verify if element of the provided list has given text.
   *
   * @param list List that contains the element
   * @param text Text to be compared
   */
  protected void clickByListElementText(List<WebElement> list, String text) {
    for (WebElement elem : list) {
      wait.forElementVisible(elem);
      if (elem.getText().equals(text)) {
        waitAndClick(elem);
        return;
      }
    }
    throw new WebDriverException(getNoTextInListErrorMessage(text));
  }

  private String getNoTextInListErrorMessage(String text) {
    return "element with text " + text + "not found in the list";
  }

  private enum Settings {
    TIME_OUT_IN_SEC(5), CHECK_OUT_IN_MILLI_SEC(1000);

    private int value;

    Settings(int value) {
      this.value = value;
    }
  }

}
