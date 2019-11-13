package com.wikia.webdriver.pageobjectsfactory.componentobject;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.networktrafficinterceptor.NetworkTrafficInterceptor;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.elements.common.TrackingOptInModal;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import net.lightbody.bmp.core.har.HarEntry;
import org.openqa.selenium.Cookie;

import java.time.Duration;
import java.util.List;

public class TrackingOptInPage extends BasePageObject {

  private static final Duration WAITING_TIME_FOR_ALL_REQUESTS = Duration.ofSeconds(10);
  private static final Duration WAITING_TIME_FOR_EUCONSENT_TO_BE_STORED = Duration.ofSeconds(5);
  private static final Duration WAITING_TIME_FOR_MODAL = Duration.ofSeconds(3);
  private static final String MODAL_INSTANT_GLOBAL = "InstantGlobals.wgEnableTrackingOptInModal=1";
  private static final String EU_CONTINENT = "EU";
  private TrackingOptInModal modal = new TrackingOptInModal(this);

  public static void setGeoCookie(WikiaWebDriver driver, String continent, String country) {
    Cookie geoCookie = driver.manage().getCookieNamed("Geo");
    if (geoCookie != null) {
      driver.manage().deleteCookie(geoCookie);
    }
    driver.manage().addCookie(new Cookie("Geo",
                                         "{%22region%22:%22WP%22%2C%22country%22:%22" + country
                                         + "%22%2C%22continent%22:%22" + continent + "%22}",
                                         String.format(".%s",
                                                       Configuration.getEnvType().getDomain(driver.getCurrentUrl())
                                         ),
                                         null,
                                         null
    ));
  }

  public static void deleteTrackingOptInCookie(WikiaWebDriver driver) {
    Cookie optInCookie = driver.manage().getCookieNamed("tracking-opt-in-status");
    if (optInCookie != null) {
      driver.manage().deleteCookie(optInCookie);
    }
  }

  private static String appendTrackingOptOutParameters(String url, String[] instantGlobals) {
    UrlBuilder urlBuilder = UrlBuilder.createUrlBuilder();
    String newUrl = url;

    for (String instantGlobal : instantGlobals) {
      newUrl = urlBuilder.globallyEnableGeoInstantGlobalOnPage(newUrl, instantGlobal);
    }

    return newUrl;
  }

  public boolean isVisible() {
    return modal.isVisible();
  }

  private void acceptTracking() {
    wait.forX(WAITING_TIME_FOR_MODAL);
    modal.clickAcceptButton();
  }
  private void rejectTracking() {
    modal.clickLearnMoreButton();
    wait.forX(WAITING_TIME_FOR_MODAL);
    modal.rejectAllFeatures();
    modal.clickSaveChangesButton();
  }

  public void clickAcceptButton() {
    acceptTracking();
  }

  public void clickRejectButton() {
    rejectTracking();
  }

  public void acceptOptInModal(WikiaWebDriver driver, String country, Page page) {
    setGeoCookie(driver, EU_CONTINENT, country);
    getUrl(urlOptInModalDisplayedOasis(page));
    acceptTracking();
  }

  public void acceptOptInModal(
      WikiaWebDriver driver, String country, Page page, String[] instantGlobals
  ) {
    setGeoCookie(driver, EU_CONTINENT, country);
    getUrl(urlWithInstantGlobals(instantGlobals, page));
    acceptTracking();
  }

  public void rejectOptInModal(WikiaWebDriver driver, String country, Page page) {
    setGeoCookie(driver, EU_CONTINENT, country);
    getUrl(urlOptInModalDisplayedOasis(page));
    rejectTracking();
  }

  public void rejectOptInModal(
      WikiaWebDriver driver, String country, Page page, String[] instantGlobals
  ) {
    setGeoCookie(driver, EU_CONTINENT, country);
    getUrl(urlWithInstantGlobals(instantGlobals, page));
    rejectTracking();
  }

  public void verifyTrackingRequestsNotSend(
      List<String> urlPatterns, NetworkTrafficInterceptor networkTrafficInterceptor
  ) {
    isTrackingRequestsNotSend(urlPatterns, networkTrafficInterceptor);
  }

  public void verifyTrackingRequestsNotSendForAccepted(
      List<String> urlPatterns, NetworkTrafficInterceptor networkTrafficInterceptor
  ) {
    isTrackingRequestsNotSendForAccepted(urlPatterns, networkTrafficInterceptor);
  }

  public void verifyTrackingRequestsSendForRejected(
      List<String> urlPatterns, NetworkTrafficInterceptor networkTrafficInterceptor
  ) {
    isTrackingRequestsSendForRejected(urlPatterns, networkTrafficInterceptor);
  }

  public void verifyTrackingRequestsSend(
      List<String> urlPatterns, NetworkTrafficInterceptor networkTrafficInterceptor
  ) {
    isTrackingRequestSend(urlPatterns, networkTrafficInterceptor);
  }

  public void verifyTrackingRequestsSendOutsideEU(
      List<String> urlPatterns, NetworkTrafficInterceptor networkTrafficInterceptor
  ) {
    isTrackingRequestSendOutsideEU(urlPatterns, networkTrafficInterceptor);
  }

  public String urlOptInModalDisplayedOasis(Page page) {
    return urlBuilder.appendQueryStringToURL(page.getUrl(), MODAL_INSTANT_GLOBAL);
  }

  private void isTrackingRequestsNotSend(
      List<String> listOfUrlsToVerify, NetworkTrafficInterceptor networkTrafficInterceptor
  ) {
    wait.forX(WAITING_TIME_FOR_ALL_REQUESTS);
    isConsentCookieSetToRejected();
    for (String url : listOfUrlsToVerify) {
      Assertion.assertFalse(isSuccessfulResponseByUrlPattern(networkTrafficInterceptor, url),
                            "Request to " + url + " services was found"
      );
    }
  }

  private void isTrackingRequestsNotSendForAccepted(
      List<String> listOfUrlsToVerify, NetworkTrafficInterceptor networkTrafficInterceptor
  ) {
    wait.forX(WAITING_TIME_FOR_ALL_REQUESTS);
    isConsentCookieSetToAccepted();
    for (String url : listOfUrlsToVerify) {
      Assertion.assertFalse(isSuccessfulResponseByUrlPattern(networkTrafficInterceptor, url),
                            "Request to " + url + " services was found"
      );
    }
  }

  private void isTrackingRequestsSendForRejected(
      List<String> listOfUrlsToVerify, NetworkTrafficInterceptor networkTrafficInterceptor
  ) {
    wait.forX(WAITING_TIME_FOR_ALL_REQUESTS);
    isConsentCookieSetToRejected();
    for (String url : listOfUrlsToVerify) {
      Assertion.assertTrue(isSuccessfulResponseByUrlPattern(networkTrafficInterceptor, url),
                           "Request to " + url + " services was not found"
      );
    }
  }

  private void isTrackingRequestSend(
      List<String> listOfUrlsToVerify, NetworkTrafficInterceptor networkTrafficInterceptor
  ) {
    wait.forX(WAITING_TIME_FOR_EUCONSENT_TO_BE_STORED); //
    refreshPage();
    wait.forX(WAITING_TIME_FOR_ALL_REQUESTS);
    isConsentCookieSetToAccepted();
    for (String url : listOfUrlsToVerify) {
      wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, url);
    }
  }

  private void isTrackingRequestSendOutsideEU(
      List<String> listOfUrlsToVerify, NetworkTrafficInterceptor networkTrafficInterceptor
  ) {
    wait.forX(WAITING_TIME_FOR_ALL_REQUESTS);
    for (String url : listOfUrlsToVerify) {
      wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, url);
    }
  }

  public boolean areResponsesByUrlPatternSuccessful(
      List<String> listOfUrlsToVerify, NetworkTrafficInterceptor networkTrafficInterceptor
  ) {
    boolean result = true;
    for (String url : listOfUrlsToVerify) {
      try {
        wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, url);
      } catch (Exception e) {
        Log.log("Did not get successful response with element: " + url, e, false);
        result = false;
      }
    }
    return result;
  }

  private boolean isSuccessfulResponseByUrlPattern(
      final NetworkTrafficInterceptor trafficInterceptor, final String pattern
  ) {
    try {
      HarEntry entry = trafficInterceptor.getEntryByUrlPattern(pattern);
      return entry.getResponse().getStatus() < 400;
    } catch (NullPointerException ex) {
      Log.info("Did not get successful response", ex);

      return false;
    }
  }

  private String urlWithInstantGlobals(String[] instantGlobals, Page page) {
    String url = urlOptInModalDisplayedOasis(page);
    return appendTrackingOptOutParameters(url, instantGlobals);
  }

  public void logTrackingCookieValue() {
    Log.info("Geo cookie: ", driver.manage().getCookieNamed("Geo").getValue());
  }

  private String getEuConsentCookieValue() {
    return driver.manage().getCookieNamed("euconsent").getValue();
  }

  private void isConsentCookieSetToAccepted() {
    Assertion.assertEquals(
        getEuConsentCookieValue().length(),
        148,
        "Incorrect length of accepted euconsent value"
    );
  }

  private void isConsentCookieSetToRejected() {
    Assertion.assertEquals(
        getEuConsentCookieValue().length(),
        148,
        "Incorrect length of rejected euconsent value"
    );
  }
}
