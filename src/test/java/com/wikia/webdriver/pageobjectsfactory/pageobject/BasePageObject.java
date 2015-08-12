package com.wikia.webdriver.pageobjectsfactory.pageobject;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.contentpatterns.XSSContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.CommonExpectedConditions;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.core.purge.PurgeMethod;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.logging.PageObjectLogging;

/**
 * @author Karol
 */
public class BasePageObject {

  public final Wait wait;
  public WebDriver driver;
  public WebDriverWait waitFor;
  public Actions builder;
  protected int timeOut = 30;
  protected UrlBuilder urlBuilder = new UrlBuilder();
  protected JavascriptActions jsActions;

  @FindBy(css = "#WallNotifications div.notification div.msg-title")
  protected WebElement notificationsLatestNotificationOnWiki;
  @FindBy(css = "#WallNotifications > li")
  protected WebElement notificationsShowNotificationsLogo;
  @FindBy(css = ".mw-htmlform-submit")
  protected WebElement followSubmit;
  @FindBy(css = "#ca-unwatch")
  protected WebElement followedButton;

  public BasePageObject(WebDriver driver) {
    this.driver = driver;
    this.waitFor = new WebDriverWait(driver, timeOut);
    this.builder = new Actions(driver);
    this.wait = new Wait(driver);
    this.jsActions = new JavascriptActions(driver);
    this.setWindowSizeAndroid();

    PageFactory.initElements(driver, this);
  }

  public static String getTimeStamp() {
    Date time = new Date();
    long timeCurrent = time.getTime();
    return String.valueOf(timeCurrent);
  }

  protected void setWindowSizeAndroid() {
    if (!Configuration.getBrowser().contains("ANDROID")) {
      driver.manage().window().maximize();
    }
  }

  public void mouseOverInArticleIframe(String cssSelecotr) {
    jsActions.execute("$($($('iframe[title*=\"Rich\"]')[0].contentDocument.body).find('"
        + cssSelecotr + "')).mouseenter()");
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      PageObjectLogging.log("mouseOverInArticleIframe", e.getMessage(), false);
    }
  }

  /**
   * Simple method for checking if element is on page or not. Changing the implicitWait value allows
   * us no need for waiting 30 seconds
   */
  protected boolean isElementOnPage(By by) {
    changeImplicitWait(500, TimeUnit.MILLISECONDS);
    try {
      return driver.findElements(by).size() > 0;
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  /**
   * Simple method for checking if element is on page or not. Changing the implicitWait value allows
   * us no need for waiting 30 seconds
   */
  protected boolean isElementOnPage(WebElement element) {
    changeImplicitWait(500, TimeUnit.MILLISECONDS);
    boolean isElementOnPage = true;
    try {
      // Get location on WebElement is rising exception when element is not present
      element.getLocation();
    } catch (WebDriverException ex) {
      isElementOnPage = false;
    } finally {
      restoreDeaultImplicitWait();
    }
    return isElementOnPage;
  }

  /**
   * Simple method for getting number of element on page. Changing the implicitWait value allows us
   * no need for waiting 30 seconds
   */
  protected int getNumOfElementOnPage(By cssSelectorBy) {
    changeImplicitWait(500, TimeUnit.MILLISECONDS);
    int numElementOnPage;
    try {
      numElementOnPage = driver.findElements(cssSelectorBy).size();
    } catch (WebDriverException ex) {
      numElementOnPage = 0;
    } finally {
      restoreDeaultImplicitWait();
    }
    return numElementOnPage;
  }

  protected boolean isElementInContext(String cssSelector, WebElement element) {
    changeImplicitWait(500, TimeUnit.MILLISECONDS);
    boolean isElementInElement = true;
    try {
      if (element.findElements(By.cssSelector(cssSelector)).size() < 1) {
        isElementInElement = false;
      }
    } catch (WebDriverException ex) {
      isElementInElement = false;
    } finally {
      restoreDeaultImplicitWait();
    }
    return isElementInElement;
  }

  protected void scrollAndClick(WebElement element) {
    scrollToElement(element);
    waitForElementClickableByElement(element, 5);
    element.click();
  }

  protected void scrollAndClick(WebElement element, int offset) {
    scrollToElement(element, offset);
    element.click();
  }

  protected void scrollToElement(WebElement element) {
    try {
      ((JavascriptExecutor) driver).executeScript("var x = $(arguments[0]);"
          + "window.scroll(0,parseInt(x.offset().top - 100));", element);
    } catch (WebDriverException e) {
      if (e.getMessage().contains(XSSContent.NO_JQUERY_ERROR)) {
        PageObjectLogging.log("JSError", "JQuery is not defined", false);
      }
    }
  }

  protected void scrollToElement(WebElement element, int offset) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    try {
      js.executeScript("var x = $(arguments[0]);"
          + "window.scroll(0,parseInt(x.offset().top - arguments[1]));", element, offset);
    } catch (WebDriverException e) {
      if (e.getMessage().contains(XSSContent.NO_JQUERY_ERROR)) {
        PageObjectLogging.log("JSError", "JQuery is not defined", false);
      }
    }
  }

  /*
   * Url helpers
   */

  protected void scrollToElement(By elementBy) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    try {
      js.executeScript("var x = $(arguments[0]);"
          + "window.scroll(0,parseInt(x.offset().top - 60));", driver.findElement(elementBy));
    } catch (WebDriverException e) {
      if (e.getMessage().contains(XSSContent.NO_JQUERY_ERROR)) {
        PageObjectLogging.log("JSError", "JQuery is not defined", false);
      }
    }
  }

  public boolean verifyTitle(String title) {
    String currentTitle = driver.getTitle();
    if (!currentTitle.equals(title)) {
      return false;
    }
    return true;
  }

  public void verifyURLcontains(String givenString) {
    String currentURL = driver.getCurrentUrl();
    Assertion.assertStringContains(currentURL.toLowerCase(), givenString.toLowerCase());
    PageObjectLogging.log("verifyURLcontains", "current url is the same as expetced url", true);
  }

  public void verifyURLcontains(final String givenString, int timeOut) {
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      new WebDriverWait(driver, timeOut).until(new ExpectedCondition<Boolean>() {
        @Override
        public Boolean apply(WebDriver driver) {
          return driver.getCurrentUrl().toLowerCase().contains(givenString.toLowerCase());
        }
      });
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public void verifyURL(String givenURL) {
    Assertion.assertEquals(driver.getCurrentUrl(), givenURL);
  }

  public String getCurrentUrl() {
    return driver.getCurrentUrl();
  }

  public void getUrl(String url) {
    getUrl(url, false);
  }

  public void getUrl(String url, boolean makeScreenshot) {
    try {
      driver.get(url);
    } catch (TimeoutException e) {
      PageObjectLogging.log("getUrl",
          "page %page% loaded for more than 30 seconds".replace("%page%", url), false);
      return;
    }
    if (makeScreenshot) {
      PageObjectLogging.log("Take screenshot",
          String.format("Screenshot After Navigation to: %s", url), true, driver);
    }
  }

  public void getUrl(Page page) {
    getUrl(urlBuilder.getUrlForPage(page));
  }

  public void getUrl(Page page, String queryString) {
    getUrl(urlBuilder.appendQueryStringToURL(urlBuilder.getUrlForPage(page), queryString));
  }

  public void refreshPage() {
    try {
      driver.navigate().refresh();
      PageObjectLogging.log("refreshPage", "page refreshed", true);
    } catch (TimeoutException e) {
      PageObjectLogging
          .log("refreshPage", "page loaded for more than 30 seconds after click", true);
    }
  }

  public void waitForWindow(String windowName, String comment) {
    Object[] windows = driver.getWindowHandles().toArray();
    int delay = 500;
    int sumDelay = 500;
    while (windows.length == 1) {
      try {
        Thread.sleep(delay);
        windows = driver.getWindowHandles().toArray();
        sumDelay += 500;
      } catch (InterruptedException e) {
        PageObjectLogging.log(windowName, e.getMessage(), false);
      }
      if (sumDelay > 5000) {
        PageObjectLogging.log(windowName, comment, false);
        break;
      }
    }
  }

  protected Boolean scrollToSelector(String selector) {
    if (isElementOnPage(By.cssSelector(selector))) {
      JavascriptExecutor js = (JavascriptExecutor) driver;
      try {
        js.executeScript("var x = $(arguments[0]);"
            + "window.scroll(0,x.position()['top']+x.height()+100);"
            + "$(window).trigger('scroll');", selector);
      } catch (WebDriverException e) {
        if (e.getMessage().contains(XSSContent.NO_JQUERY_ERROR)) {
          PageObjectLogging.log("JSError", "JQuery is not defined", false);
        }
      }
      return true;
    } else {
      PageObjectLogging
          .log("SelectorNotFound", "Selector " + selector + " not found on page", true);
      return false;
    }
  }

  // You can get access to hidden elements by changing class
  public void unhideElementByClassChange(String elementName, String classWithoutHidden,
      int... optionalIndex) {
    int numElem = optionalIndex.length == 0 ? 0 : optionalIndex[0];
    JavascriptExecutor jse = (JavascriptExecutor) driver;
    jse.executeScript("document.getElementsByName('" + elementName + "')[" + numElem
        + "].setAttribute('class', '" + classWithoutHidden + "');");
  }

  public void waitForElementNotVisibleByElement(WebElement element) {
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      waitFor.until(CommonExpectedConditions.invisibilityOfElementLocated(element));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public void waitForElementClickableByElement(WebElement element) {
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      waitFor.until(CommonExpectedConditions.elementToBeClickable(element));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public void waitForElementClickableByElement(WebElement element, int newTimeOut) {
    new WebDriverWait(driver, newTimeOut).until(CommonExpectedConditions
        .elementToBeClickable(element));
  }

  public void waitForElementNotClickableByElement(WebElement element) {
    waitFor.until(CommonExpectedConditions.elementNotToBeClickable(element));
  }

  public void waitForValueToBePresentInElementsAttributeByCss(String selector, String attribute,
      String value) {
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      waitFor.until(CommonExpectedConditions.valueToBePresentInElementsAttribute(
          By.cssSelector(selector), attribute, value));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public void waitForValueToBePresentInElementsCssByCss(String selector, String cssProperty,
      String expectedValue) {
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      waitFor.until(CommonExpectedConditions.cssValuePresentForElement(By.cssSelector(selector),
          cssProperty, expectedValue));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public void waitForValueToBePresentInElementsAttributeByElement(WebElement element,
      String attribute, String value) {
    waitFor.until(CommonExpectedConditions.valueToBePresentInElementsAttribute(element, attribute,
        value));
  }

  public void waitForStringInURL(String givenString) {
    waitFor.until(CommonExpectedConditions.givenStringtoBePresentInURL(givenString));
    PageObjectLogging.log("waitForStringInURL", "verify that url contains " + givenString, true);
  }

  public void waitForAlertAndAccept() {
    waitFor.until(ExpectedConditions.alertIsPresent());
    Alert alert = driver.switchTo().alert();
    String alertText = alert.getText();
    alert.accept();
    PageObjectLogging.log("waitForAlertAndAccept", "detected and closed alert with text "
        + alertText, true);
  }

  public String getRandomDigits(int length) {
    String timeStamp = getTimeStamp();
    int timeStampLenght = timeStamp.length();
    int timeStampCut = timeStampLenght - length;
    return timeStamp.substring(timeStampCut);
  }

  public String getRandomString(int length) {
    char[] alphabet =
        {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
            'j', 'l', 'k', 'l', 'm', 'n', 'o', 'p', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    Random rnd = new Random();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < length; i++) {
      sb.append(alphabet[rnd.nextInt(alphabet.length)]);
    }
    return sb.toString();
  }

  public void openWikiPage() {
    getUrl(urlBuilder.getUrlForWiki(Configuration.getWikiName()) + URLsContent.NOEXTERNALS);
    PageObjectLogging.log("WikiPageOpened", "Wiki page is opened", true);
  }

  /*
   * notifications methods - will be moved to other class
   */
  public void notifications_verifyLatestNotificationTitle(String title) {
    notifications_showNotifications();
    // the below method is native click which is the only way to load
    // notification
    notifications_clickOnNotificationsLogo();
    wait.forElementVisible(notificationsLatestNotificationOnWiki);
    wait.forTextInElement(notificationsLatestNotificationOnWiki, title);
    PageObjectLogging.log("notifications_verifyNotificationTitle",
        "Verify that the latest notification has the following title: " + title, true, driver);
  }

  public void notifications_clickOnNotificationsLogo() {
    wait.forElementVisible(notificationsShowNotificationsLogo);
    waitForElementClickableByElement(notificationsShowNotificationsLogo);
    notificationsShowNotificationsLogo.click();
    PageObjectLogging.log("notifications_clickOnNotificationsLogo",
        "click on notifications logo on the upper right corner", true, driver);
  }

  public void notifications_showNotifications() {
    wait.forElementVisible(notificationsShowNotificationsLogo);
    jsActions.execute("$('#WallNotifications ul.subnav').addClass('show')");
    PageObjectLogging.log("norifications_showNotifications",
        "show notifications by adding 'show' class to element", true, driver);
  }

  /**
   * Wait for new window present
   */
  public void waitForNewWindow() {
    waitFor.until(CommonExpectedConditions.newWindowPresent());
  }

  public void appendToUrl(String additionToUrl) {
    driver.get(urlBuilder.appendQueryStringToURL(driver.getCurrentUrl(), additionToUrl));
    PageObjectLogging.log("appendToUrl", additionToUrl + " has been appended to url", true);
  }

  public void appendMultipleQueryStringsToUrl(String[] queryStrings) {
    String currentUrl = getCurrentUrl();
    for (int i = 0; i < queryStrings.length; i++) {
      currentUrl = urlBuilder.appendQueryStringToURL(currentUrl, queryStrings[i]);
    }
    driver.get(currentUrl);
    PageObjectLogging.log("appendToUrl", queryStrings + " have been appended to url", true);
  }

  public void pressDownArrow(WebElement element) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("var e = jQuery.Event(\"keydown\"); "
        + "e.which=40; $(arguments[0]).trigger(e);", element);
  }

  public void setDisplayStyle(String selector, String style) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("document.querySelector(arguments[0]).style.display = arguments[1]", selector,
        style);
  }

  private void purge(String url) throws Exception {
    CloseableHttpClient client = HttpClients.createDefault();
    HttpUriRequest method = new PurgeMethod(url);
    try {
      int status = client.execute(method).getStatusLine().getStatusCode();
      if (status != HttpStatus.SC_OK && status != HttpStatus.SC_NOT_FOUND) {
        throw new Exception("HTTP PURGE failed for: " + url + "(" + status + ")");
      }
      PageObjectLogging.log("purge", url, true);
      return;
    } finally {
      client.close();
    }
  }

  /**
   * return status code of given URL
   */
  public int getURLStatus(String url) {
    try {
      purge(url);
      HttpURLConnection.setFollowRedirects(false);
      HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
      connection.disconnect();
      connection.setRequestMethod("GET");
      connection.setRequestProperty("User-Agent",
          "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) "
              + "Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
      int status = connection.getResponseCode();
      connection.disconnect();
      return status;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * check if current HTTP status of given URL is the same as expected
   */
  public void verifyURLStatus(int desiredStatus, String url) {
    int waitTime = 500;
    int statusCode = 0;
    boolean status = false;
    while (!status) {
      try {
        statusCode = getURLStatus(url);
        if (statusCode == desiredStatus) {
          status = true;
        } else {
          Thread.sleep(500);
          waitTime += 500;
        }
        if (waitTime > 20000) {
          break;
        }
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    Assertion.assertEquals(statusCode, desiredStatus);
    PageObjectLogging.log("verifyURLStatus", url + " has status " + statusCode, true);
  }

  protected void changeImplicitWait(int value, TimeUnit timeUnit) {
    driver.manage().timeouts().implicitlyWait(value, timeUnit);
  }

  protected void restoreDeaultImplicitWait() {
    changeImplicitWait(timeOut, TimeUnit.SECONDS);
  }

  protected String getAbsolutePathForFile(String relativePath) {
    File fileCheck = new File(relativePath);
    if (!fileCheck.isFile()) {
      throw new RuntimeException("file " + relativePath + " doesn't exists");
    }
    return fileCheck.getAbsolutePath();
  }

  public void verifyUrlInNewWindow(String url) {
    waitForWindow("", "");
    Object[] windows = driver.getWindowHandles().toArray();
    driver.switchTo().window(windows[1].toString());
    waitForStringInURL(url);
    driver.close();
    driver.switchTo().window(windows[0].toString());
    PageObjectLogging.log("verifyUrlInNewWindow", "url in new window verified", true);
  }

  public void verifyElementMoved(Point source, WebElement element) {
    Point target = element.getLocation();
    if (source.x == target.x && source.y == target.y) {
      Assertion.fail("Element did not move. Old coordinate (" + source.x + "," + source.y + ") "
          + "New coordinate (" + target.x + "," + target.y + ")");
    }
    PageObjectLogging.log("verifyElementMoved", "Element did move. From (" + source.x + ","
        + source.y + ") to (" + target.x + "," + target.y + ")", true, driver);
  }

  public void verifyElementResized(Dimension source, WebElement element) {
    Dimension target = element.getSize();
    int sourceWidth = source.width;
    int sourceHeight = source.height;
    int targetWidth = target.width;
    int targetHeight = target.height;

    if (sourceWidth == targetWidth && sourceHeight == targetHeight) {
      Assertion.fail("Element did not resize. Old dimension (" + sourceWidth + "," + sourceHeight
          + ") " + "New dimension (" + targetWidth + "," + targetHeight + ")");
    }
    PageObjectLogging.log("verifyElementMoved", "Element did resize. From (" + sourceWidth + ","
        + sourceHeight + ") to (" + targetWidth + "," + targetHeight + ")", true, driver);
  }

  public void switchToNewBrowserTab() {
    List<String> tabs = new ArrayList<String>(driver.getWindowHandles());
    driver.switchTo().window(tabs.get(tabs.size() - 1));
  }

  /**
   * @param optionalFilter Optional parameter for filtering logs by specific string.
   */
  public List<String> getBrowserLogs(String... optionalFilter) {
    String filter = optionalFilter.length > 0 ? optionalFilter[0] : "";
    List<String> result = new ArrayList<>();
    for (LogEntry logEntry : driver.manage().logs().get(LogType.BROWSER)) {
      if (filter.isEmpty() || logEntry.getMessage().contains(filter)) {
        result.add(logEntry.getMessage());
      }
    }
    return result;
  }
}
