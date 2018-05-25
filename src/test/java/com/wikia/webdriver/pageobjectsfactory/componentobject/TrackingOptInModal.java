package com.wikia.webdriver.pageobjectsfactory.componentobject;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.networktrafficinterceptor.NetworkTrafficInterceptor;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.TimeoutException;
import net.lightbody.bmp.core.har.HarEntry;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.List;

public class TrackingOptInModal extends BasePageObject {

  @FindBy(css = "body > div:nth-child(5) > div > div")
  WebElement modal;

  @FindBy(css = "div._36mdTyonPU0bxbU70dDO4f")
  WebElement acceptButton;

  @FindBy(css = "div._3mGMvAUi8MtSBaB1BUvLtz")
  WebElement rejectButton;

  @FindBy(css = "div[data-tracking-opt-in-overlay]")
  private WebElement optInModalOverlay;

  private HarEntry entry;
  private static final Duration WAITNG_TIME_FOR_ALL_REQUESTS = Duration.ofSeconds(10);
  private static final String MODAL_INSTANT_GLOBAL = "InstantGlobals.wgEnableTrackingOptInModal=1";
  private static final String EU_CONTINENT = "EU";


  public static void setGeoCookie(WikiaWebDriver driver, String continent, String country) {
    Cookie geoCookie = driver.manage().getCookieNamed("Geo");
    driver.manage().deleteCookie(geoCookie);
    driver.manage().addCookie(new Cookie(
        "Geo",
        "{%22region%22:%22WP%22%2C%22country%22:%22" + country + "%22%2C%22continent%22:%22"
        + continent + "%22}",
        String.format(".%s", Configuration.getEnvType().getWikiaDomain()),
        null,
        null
    ));
  }

  public boolean isVisible() {
    try {
      wait.forElementVisible(optInModalOverlay);
      PageObjectLogging.logInfo("Tracking modal visible");

      return true;
    } catch (TimeoutException e) {
      PageObjectLogging.logInfo("Tracking modal not visible", e);

      return false;
    }
  }

  public void clickAcceptButton() {
    try {
      wait.forElementClickable(acceptButton);
      acceptButton.click();
    } catch (Exception e) {
      PageObjectLogging.log("Accept button clicked", e, false);
    }
  }

  public void clickRejectButton() {
    try {
      wait.forElementClickable(rejectButton);
      rejectButton.click();
    } catch (Exception e) {
      PageObjectLogging.log("Reject button clicked", e, false);
    }
  }

  public boolean isModalDisplayed() {
    boolean isVisible = false;
    try {
      wait.forElementVisible(modal);
      isVisible = true;
    } catch (Exception e) {
      PageObjectLogging.log("Modal is visible", e, false);
    }

    return isVisible;
  }

  public void acceptOptInModal(WikiaWebDriver driver, String country, Page page) {
    setGeoCookie(driver, EU_CONTINENT, country);
    getUrl(urlBuilder.appendQueryStringToURL(urlBuilder.getUrlForPage(page), MODAL_INSTANT_GLOBAL));
    clickAcceptButton();
  }

  public void acceptOptInModal(WikiaWebDriver driver, String country, Page page,
                               String[] instantGlobals) {
    setGeoCookie(driver, EU_CONTINENT, country);
    getUrl(urlWithInstantGlobals(instantGlobals, page));
    clickAcceptButton();
  }

  public void rejectOptInModal(WikiaWebDriver driver, String country, Page page) {
    setGeoCookie(driver, EU_CONTINENT, country);
    getUrl(urlBuilder.appendQueryStringToURL(urlBuilder.getUrlForPage(page), MODAL_INSTANT_GLOBAL));
    clickRejectButton();
  }

  public void rejectOptInModal(WikiaWebDriver driver, String country, Page page,
                               String[] instantGlobals) {
    setGeoCookie(driver, EU_CONTINENT, country);
    getUrl(urlWithInstantGlobals(instantGlobals, page));
    clickRejectButton();
  }

  public void verifyTrackingRequestsNotSend(List<String> urlPatterns,
                                            NetworkTrafficInterceptor networkTrafficInterceptor) {
    isTrackingRequestsNotSend(urlPatterns, networkTrafficInterceptor);
  }

  public void verifyTrackingRequestsSend(List<String> urlPatterns,
                                         NetworkTrafficInterceptor networkTrafficInterceptor) {
    isTrackingRequestSend(urlPatterns, networkTrafficInterceptor);
  }

  public String urlOptInModalDisplayedOasis(Page page) {
    return urlBuilder.appendQueryStringToURL(urlBuilder.getUrlForPage(page), MODAL_INSTANT_GLOBAL);
  }

  public void isTrackingRequestsNotSend(List<String> elementsList,
                                        NetworkTrafficInterceptor networkTrafficInterceptor) {
    wait.forX(WAITNG_TIME_FOR_ALL_REQUESTS);
    for (int i = 0; i < elementsList.size(); i++) {
      Assertion.assertFalse(
          isSuccessfulResponseByUrlPattern(networkTrafficInterceptor, elementsList.get(i)),
          "Request to " + elementsList.get(i) + " services was found");
    }
  }

  public void isTrackingRequestSend(List<String> elementsList,
                                    NetworkTrafficInterceptor networkTrafficInterceptor) {
    for (int i = 0; i < elementsList.size(); i++) {
      wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, elementsList.get(i));
    }
  }

  public boolean areResponsesByUrlPatternSuccessful(List<String> elementsList,
                                                    NetworkTrafficInterceptor networkTrafficInterceptor) {
    boolean result = true;
    for (int i = 0; i < elementsList.size(); i++) {
      try {
        wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, elementsList.get(i));
      } catch (Exception e) {
        PageObjectLogging
            .log("Did not get successful response with element: " + elementsList.get(i),
                 e, false);
        result = false;
      }
    }
    return result;
  }

  private boolean isSuccessfulResponseByUrlPattern(
      final NetworkTrafficInterceptor trafficInterceptor,
      final String pattern) {
    try {
      entry = trafficInterceptor.getEntryByUrlPattern(pattern);
      return entry.getResponse().getStatus() < 400;
    } catch (NullPointerException ex) {
      PageObjectLogging
          .logInfo("Did not get successful response",
               ex);

      return false;
    }
  }

  private static String appendTrackingOptOutParameters(String url, String[] instantGlobals) {
    UrlBuilder urlBuilder = new UrlBuilder();
    String newUrl = url;

    for (String instantGlobal : instantGlobals) {
      newUrl = urlBuilder.globallyEnableGeoInstantGlobalOnPage(newUrl, instantGlobal);
    }

    return newUrl;
  }

  private String urlWithInstantGlobals(String[] instantGlobals, Page page) {
    String url = urlOptInModalDisplayedOasis(page);
    String urlWithInstantGlobals = appendTrackingOptOutParameters(url, instantGlobals);
    return urlBuilder.appendQueryStringToURL(urlWithInstantGlobals, MODAL_INSTANT_GLOBAL);
  }

  public void logTrackingCookieValue() {
    PageObjectLogging.logInfo("Geo cookie: ", driver.manage().getCookieNamed("Geo").getValue());
  }
}
