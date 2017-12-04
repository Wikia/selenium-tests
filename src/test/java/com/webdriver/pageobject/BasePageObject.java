package com.webdriver.pageobject;

import com.webdriver.common.core.Assertion;
import com.webdriver.common.core.PageWebDriver;
import com.webdriver.common.core.url.UrlBuilder;
import com.webdriver.common.driverprovider.DriverProvider;
import com.webdriver.common.logging.PageObjectLogging;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BasePageObject {

  private static final int TIMEOUT_PAGE_REGISTRATION = 3000;
  public WebDriverWait waitFor;
  public Actions builder;
  protected PageWebDriver driver = DriverProvider.getActiveDriver();
  protected int timeOut = 15;
  protected UrlBuilder urlBuilder = new UrlBuilder();

  public BasePageObject() {
    this.waitFor = new WebDriverWait(driver, timeOut);
    this.builder = new Actions(driver);

    PageFactory.initElements(driver, this);
  }

  protected boolean isElementDisplayed(WebElement element) {
    try {
      return element.isDisplayed();
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo(e.getMessage());
      return false;
    }
  }

  public void verifyURL(String givenURL) {
    Assertion.assertEquals(driver.getCurrentUrl(), givenURL);
  }

  public String getCurrentUrl() {
    return driver.getCurrentUrl();
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

  public int getURLStatus(String url) {
    try {
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


  protected void changeImplicitWait(int value, TimeUnit timeUnit) {
    driver.manage().timeouts().implicitlyWait(value, timeUnit);
  }

}
