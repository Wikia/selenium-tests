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
  private static final String MODAL_INSTANT_GLOBAL = "InstantGlobals.wgEnableTrackingOptInModal=1";
  private static final String EU_CONTINENT = "EU";
  private TrackingOptInModal modal = new TrackingOptInModal(this);

  public static void setGeoCookie(WikiaWebDriver driver, String continent, String country) {
    Cookie geoCookie = driver.manage().getCookieNamed("Geo");
    driver.manage().deleteCookie(geoCookie);
    driver.manage().addCookie(new Cookie("Geo",
                                         "{%22region%22:%22WP%22%2C%22country%22:%22" + country
                                         + "%22%2C%22continent%22:%22" + continent + "%22}",
                                         String.format(".%s",
                                                       Configuration.getEnvType().getWikiaDomain()
                                         ),
                                         null,
                                         null
    ));
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

  public void clickAcceptButton() {
    modal.clickAcceptButton();
  }

  public void clickRejectButton() {
    modal.clickRejectButton();
  }

  public void acceptOptInModal(WikiaWebDriver driver, String country, Page page) {
    setGeoCookie(driver, EU_CONTINENT, country);
    getUrl(urlOptInModalDisplayedOasis(page));
    modal.clickAcceptButton();
  }

  public void acceptOptInModal(
      WikiaWebDriver driver, String country, Page page, String[] instantGlobals
  ) {
    setGeoCookie(driver, EU_CONTINENT, country);
    getUrl(urlWithInstantGlobals(instantGlobals, page));
    modal.clickAcceptButton();
  }

  public void rejectOptInModal(WikiaWebDriver driver, String country, Page page) {
    setGeoCookie(driver, EU_CONTINENT, country);
    getUrl(urlOptInModalDisplayedOasis(page));
    modal.clickRejectButton();
  }

  public void rejectOptInModal(
      WikiaWebDriver driver, String country, Page page, String[] instantGlobals
  ) {
    setGeoCookie(driver, EU_CONTINENT, country);
    getUrl(urlWithInstantGlobals(instantGlobals, page));
    modal.clickRejectButton();
  }

  public void verifyTrackingRequestsNotSend(
      List<String> urlPatterns, NetworkTrafficInterceptor networkTrafficInterceptor
  ) {
    isTrackingRequestsNotSend(urlPatterns, networkTrafficInterceptor);
  }

  public void verifyTrackingRequestsSend(
      List<String> urlPatterns, NetworkTrafficInterceptor networkTrafficInterceptor
  ) {
    isTrackingRequestSend(urlPatterns, networkTrafficInterceptor);
  }

  public String urlOptInModalDisplayedOasis(Page page) {
    return urlBuilder.appendQueryStringToURL(page.getUrl(), MODAL_INSTANT_GLOBAL);
  }

  private void isTrackingRequestsNotSend(
      List<String> elementsList, NetworkTrafficInterceptor networkTrafficInterceptor
  ) {
    wait.forX(WAITING_TIME_FOR_ALL_REQUESTS);
    for (String anElementsList : elementsList) {
      Assertion.assertFalse(isSuccessfulResponseByUrlPattern(networkTrafficInterceptor,
                                                             anElementsList
                            ),
                            "Request to " + anElementsList + " services was found"
      );
    }
  }

  private void isTrackingRequestSend(
      List<String> elementsList, NetworkTrafficInterceptor networkTrafficInterceptor
  ) {
    for (String anElementsList : elementsList) {
      wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, anElementsList);
    }
  }

  public boolean areResponsesByUrlPatternSuccessful(
      List<String> elementsList, NetworkTrafficInterceptor networkTrafficInterceptor
  ) {
    boolean result = true;
    for (String anElementsList : elementsList) {
      try {
        wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, anElementsList);
      } catch (Exception e) {
        Log.log("Did not get successful response with element: " + anElementsList, e, false);
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
}
