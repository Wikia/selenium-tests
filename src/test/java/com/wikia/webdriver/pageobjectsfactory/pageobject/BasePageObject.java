package com.wikia.webdriver.pageobjectsfactory.pageobject;

import com.google.common.base.Predicate;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.contentpatterns.XSSContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.CommonExpectedConditions;
import com.wikia.webdriver.common.core.EmailUtils;
import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.core.purge.PurgeMethod;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.driverprovider.DriverProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class BasePageObject {

  private static final int TIMEOUT_PAGE_REGISTRATION = 3000;
  public final Wait wait;
  public WebDriverWait waitFor;
  public Actions builder;
  protected WikiaWebDriver driver = DriverProvider.getActiveDriver();
  protected int timeOut = 15;
  protected UrlBuilder urlBuilder = new UrlBuilder();
  protected JavascriptActions jsActions;

  public BasePageObject() {
    this.waitFor = new WebDriverWait(driver, timeOut);
    this.builder = new Actions(driver);
    this.wait = new Wait(driver);
    this.jsActions = new JavascriptActions(driver);

    PageFactory.initElements(driver, this);
  }

  public static String getTimeStamp() {
    Date time = new Date();
    long timeCurrent = time.getTime();
    return String.valueOf(timeCurrent);
  }

  private static String getEmailChangeConfirmationLink(String email, String password) {
    String mailSubject = "Confirm your email address change on FANDOM";
    String url = EmailUtils.getActivationLinkFromEmailContent(
        EmailUtils.getFirstEmailContent(email, password, mailSubject));
    PageObjectLogging.log("getActivationLinkFromMail",
        "activation link is visible in email content: " + url, true);
    return url;
  }

  public static String getPasswordResetLink(String email, String password) {
    String passwordResetEmail = EmailUtils
      .getFirstEmailContent(email, password, "Reset your FANDOM password");
    String resetLink = EmailUtils.getPasswordResetLinkFromEmailContent(passwordResetEmail);
    PageObjectLogging.log("Password reset link", "Password reset link received: " + resetLink,
        true);

    return resetLink;
  }

  public static String getEmailConfirmationLink(String email, String password) {
    String emailConfirmationMessage = EmailUtils.getFirstEmailContent(email, password,
        "Confirm your email and get started on FANDOM!");
    String confirmationLink = EmailUtils.getConfirmationLinkFromEmailContent(emailConfirmationMessage);
    PageObjectLogging.log("Email confirmation link", "Email confirmation link received: " + confirmationLink,
        true);

    return confirmationLink;
  }

  // wait for comscore to load
  public void waitForPageLoad() {
    wait.forElementPresent(
        By.cssSelector("script[src='http://b.scorecardresearch.com/beacon.js']"));
  }

  public BasePageObject waitForPageReload() {
    waitSafely(() -> wait.forElementVisible(By.className("loading-overlay"), Duration.ofSeconds(3)));
    waitSafely(() -> wait.forElementNotVisible(By.className("loading-overlay")),
      "Loading overlay still visible, page not loaded in expected time");
    return this;
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
      restoreDefaultImplicitWait();
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
      restoreDefaultImplicitWait();
    }
    return isElementOnPage;
  }

  /**
   * WebElement.isEnabled() method signature says that it returns true for anything except disabled
   * input fields. In order to check if non-input elements are disabled, "disabled" attribute value
   * must be checked and compared to "true" value
   * @param element WebElement on the page
   * @return true if value of "disabled" attribute is different than "true"
   */
  protected boolean isElementEnabled(WebElement element) {
    return !"true".equals(element.getAttribute("disabled"));
  }

  /**
   * Method to check if WebElement is displayed on the page
   *
   * @return true if element is displayed, otherwise return false
   */

  protected boolean isElementDisplayed(WebElement element) {
    try {
      return element.isDisplayed();
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo(e.getMessage());
      return false;
    }
  }

  /**
   * Method to check if WebElement is displayed on the page
   *
   * @return true if element is displayed, otherwise return false
   */

  protected boolean isElementDisplayed(WebElement element, int timeout) {
    try {
      wait.forElementVisible(element, timeout);
      return true;
    } catch (TimeoutException e) {
      return false;
    }
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
      restoreDefaultImplicitWait();
    }
    return numElementOnPage;
  }

  protected void waitSafely(Runnable o) {
    waitSafely(o, "");
  }

  void waitSafely(Runnable o, String message) {
    try {
      o.run();
    } catch (TimeoutException e) {
      PageObjectLogging.log("Timed out waiting", String.format("%s\n%s", message, e), true);
    }
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
      restoreDefaultImplicitWait();
    }
    return isElementInElement;
  }

  public void scrollTo(WebElement element) {
    jsActions.scrollElementIntoViewPort(element);
    wait.forElementClickable(element, 5);
  }

  protected void scrollAndClick(WebElement element) {
    jsActions.scrollElementIntoViewPort(element);
    wait.forElementClickable(element, 5);
    element.click();
  }

  protected void scrollAndClick(List<WebElement> elements, int index) {
    jsActions.scrollElementIntoViewPort(elements.get(index));
    wait.forElementClickable(elements, index, 5);
    elements.get(index).click();
  }

  protected void scrollAndClick(WebElement element, int offset) {
    jsActions.scrollToElement(element, offset);
    element.click();
  }

  public boolean isStringInURL(String givenString) {
    String currentURL = driver.getCurrentUrl();
    if (currentURL.toLowerCase().contains(givenString.toLowerCase())) {
      PageObjectLogging.log("isStringInURL",
        String.format("Current url: %s contains given string: %s", currentURL, givenString),
        true);
      return true;
    } else {
      PageObjectLogging.log("isStringInURL",
        String.format("Current url: %s does not contain given string: %s", currentURL, givenString),
        false);
      return false;
    }
  }

  public void verifyUrlContains(final String givenString, int timeOut) {
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      new WebDriverWait(driver, timeOut).until((ExpectedCondition<Boolean>) d -> d.getCurrentUrl()
          .toLowerCase().contains(givenString.toLowerCase()));
    } finally {
      restoreDefaultImplicitWait();
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
    driver.get(url);
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
      PageObjectLogging.log("refreshPage", "page loaded for more than 30 seconds after click",
          true);
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
        PageObjectLogging.log(windowName, e, false);
      }
      if (sumDelay > 5000) {
        PageObjectLogging.log(windowName, comment, false);
        break;
      }
    }
  }

  protected void hover(WebElement element) {
    new Actions(driver).moveToElement(element).perform();
  }

  protected void moveAway(WebElement element) {
    new Actions(driver).moveToElement(element, -200, 0).perform();
  }

  protected Boolean scrollToSelector(String selector) {
    if (isElementOnPage(By.cssSelector(selector))) {
      try {
        driver.executeScript(
            "var x = $(arguments[0]);" + "window.scroll(0,x.position()['top']+x.height()+100);"
                + "$(window).trigger('scroll');",
            selector);
      } catch (WebDriverException e) {
        if (e.getMessage().contains(XSSContent.NO_JQUERY_ERROR)) {
          PageObjectLogging.log("JSError", "JQuery is not defined", false);
        }
      }
      return true;
    } else {
      PageObjectLogging.log("SelectorNotFound", "Selector " + selector + " not found on page",
          true);
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
      restoreDefaultImplicitWait();
    }
  }

  public void waitForElementNotVisibleByElement(WebElement element, long timeout) {
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      new WebDriverWait(driver, timeout)
          .until(CommonExpectedConditions.invisibilityOfElementLocated(element));
    } finally {
      restoreDefaultImplicitWait();
    }
  }

  public void waitForValueToBePresentInElementsAttributeByCss(String selector, String attribute,
      String value) {
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      waitFor.until(CommonExpectedConditions
          .valueToBePresentInElementsAttribute(By.cssSelector(selector), attribute, value));
    } finally {
      restoreDefaultImplicitWait();
    }
  }

  public void waitForValueToBePresentInElementsCssByCss(String selector, String cssProperty,
      String expectedValue) {
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      waitFor.until(CommonExpectedConditions.cssValuePresentForElement(By.cssSelector(selector),
          cssProperty, expectedValue));
    } finally {
      restoreDefaultImplicitWait();
    }
  }

  public void waitForValueToBePresentInElementsAttributeByElement(WebElement element,
      String attribute, String value) {
    waitFor.until(
        CommonExpectedConditions.valueToBePresentInElementsAttribute(element, attribute, value));
  }

  public void waitForStringInURL(String givenString) {
    waitFor.until(CommonExpectedConditions.givenStringtoBePresentInURL(givenString));
    PageObjectLogging.log("waitForStringInURL", "verify that url contains " + givenString, true);
  }

  public String getRandomDigits(int length) {
    String timeStamp = getTimeStamp();
    int timeStampLenght = timeStamp.length();
    int timeStampCut = timeStampLenght - length;
    return timeStamp.substring(timeStampCut);
  }

  public void openWikiPage() {
    getUrl(getWikiUrl() + URLsContent.NOEXTERNALS);
    PageObjectLogging.log("WikiPageOpened", "Wiki page is opened", true);
  }

  public String getWikiUrl() {
    return urlBuilder.getUrlForWiki(Configuration.getWikiName());
  }

  public void fillInput(WebElement input, String value) {
    wait.forElementVisible(input).sendKeys(value);
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
    for (String queryString : queryStrings) {
      currentUrl = urlBuilder.appendQueryStringToURL(currentUrl, queryString);
    }
    driver.get(currentUrl);
    PageObjectLogging.log("appendToUrl", queryStrings + " have been appended to url", true);
  }

  public void pressDownArrow(WebElement element) {
    driver.executeScript(
        "var e = jQuery.Event(\"keydown\"); " + "e.which=40; $(arguments[0]).trigger(e);", element);
  }

  public void setDisplayStyle(String selector, String style) {
    driver.executeScript("document.querySelector(arguments[0]).style.display = arguments[1]",
        selector, style);
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
      throw new WebDriverException(e);
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
        throw new WebDriverException(e);
      }
    }
    Assertion.assertEquals(statusCode, desiredStatus);
    PageObjectLogging.log("verifyURLStatus", url + " has status " + statusCode, true);
  }

  protected void changeImplicitWait(int value, TimeUnit timeUnit) {
    driver.manage().timeouts().implicitlyWait(value, timeUnit);
  }

  protected void setShortImplicitWait() {
    changeImplicitWait(3, TimeUnit.SECONDS);
  }

  protected void restoreDefaultImplicitWait() {
    changeImplicitWait(timeOut, TimeUnit.SECONDS);
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

  public String switchToNewBrowserTab() {
    List<String> tabs = new ArrayList<String>(driver.getWindowHandles());
    driver.switchTo().window(tabs.get(tabs.size() - 1));

    return driver.getCurrentUrl();
  }

  private int getTabsCount() {
    return driver.getWindowHandles().size();
  }

  private String getNewTab(String parentTab) {
    Optional<String> newTab = driver.getWindowHandles().stream()
        .filter(handleName -> !handleName.equals(parentTab)).findFirst();
    return newTab.orElseThrow(() -> new NotFoundException("New tab not found!"));
  }

  private String switchToNewTab(String parentTab) {
    String newTab = getNewTab(parentTab);
    driver.switchTo().window(newTab);
    return newTab;
  }

  private String getTabWithTitle(String title) {
    return getTabWithCondition(nameToTitle -> nameToTitle.getValue().startsWith(title));
  }

  private String getOtherTab(String title) {
    return getTabWithCondition(nameToTitle -> !nameToTitle.getValue().startsWith(title));
  }

  private String getTabWithCondition(
      java.util.function.Predicate<? super Pair<String, String>> condition) {
    Optional<String> newTab = driver.getWindowHandles()
      .stream()
      .map(handleName -> Pair.of(handleName, driver.switchTo().window(handleName).getTitle()))
      .peek(handleTitle -> PageObjectLogging.log("Found window", String.format("Window with title %s", handleTitle), true))
      .filter(condition)
      .map(Pair::getKey)
      .findFirst();
    return newTab.orElseThrow(
        () -> new NotFoundException("Tab that satisfies the condition doesn't exist"));
  }

  public WebDriver switchToWindowWithTitle(String title) {
    PageObjectLogging.log("Switching windows",
        String.format("Switching to window with title: %s", title), true);
    return driver.switchTo().window(getTabWithTitle(title));
  }

  public WebDriver switchAwayFromWindowWithTitle(String title) {
    PageObjectLogging.log("Switching windows",
        String.format("Switching away from window with title: %s", title), true);
    return driver.switchTo().window(getOtherTab(title));
  }

  public WebDriver switchToMainWindow() {
    return driver.switchTo().defaultContent();
  }

  private void waitForLinkOpenedInNewTab(WebElement link) {
    int initialTabsNumber = driver.getWindowHandles().size();
    link.click();
    new WebDriverWait(driver, TIMEOUT_PAGE_REGISTRATION)
        .until((Predicate<WebDriver>) input -> getTabsCount() > initialTabsNumber);
  }

  protected void openLinkInNewTab(WebElement link) {
    String currentTab = driver.getWindowHandle();
    waitForLinkOpenedInNewTab(link);
    switchToNewTab(currentTab);
  }

  private List<String> getTabUrls() {
    String currentTab = driver.getWindowHandle();
    List<String> result = new ArrayList<>();
    for (String windowHandler : driver.getWindowHandles()) {
      driver.switchTo().window(windowHandler);
      result.add(driver.getCurrentUrl());
    }

    driver.switchTo().window(currentTab);
    return result;
  }

  public boolean tabContainsUrl(String url) {
    return getTabUrls().contains(url);
  }

  public int getElementBottomPositionByCssSelector(String elementName) {
    WebElement element = driver.findElement(By.cssSelector(elementName));

    return element.getLocation().getY() + element.getSize().getHeight();
  }

  public int getElementTopPositionByCssSelector(String elementName) {
    WebElement element = driver.findElement(By.cssSelector(elementName));

    return element.getLocation().getY();
  }

  public void enterEmailChangeLink(String email, String password) {
    getUrl(getEmailChangeConfirmationLink(email, password));
  }

}
