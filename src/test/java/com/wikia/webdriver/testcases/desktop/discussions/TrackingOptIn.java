package com.wikia.webdriver.testcases.desktop.discussions;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.dataprovider.TrackingOptInDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.communities.mobile.pages.discussions.PostsListPage;
import com.wikia.webdriver.pageobjectsfactory.componentobject.TrackingOptInPage;

import org.testng.annotations.Test;

import java.util.List;

@Test(groups = {"discussions-tracking-opt-in"})
@Execute(onWikia = "qadiscussions", language = "de")
public class TrackingOptIn extends NewTestTemplate {

    /*
        DESKTOP TESTS
    */

  @Execute(asUser = User.ANONYMOUS, trackingOptIn = false)
  @Test(groups = {"discussions-tracking-opt-in-desktop"})
  public void testModalVisibilityForAnonOnDesktop() {
    TrackingOptInPage trackingModal = setGeoCookieToGermanyAndNavigate();
    Assertion.assertTrue(trackingModal.isVisible());
  }

  @Test(groups = {"discussions-tracking-opt-in-desktop"})
  @Execute(asUser = User.USER)
  public void loggedInEUShouldNotGetModalIfOptedInOnDesktop() {
    TrackingOptInPage trackingModal = setGeoCookieToGermanyAndNavigate();
    Assertion.assertFalse(trackingModal.isVisible());
  }

  @Test(groups = {"discussions-tracking-opt-in-desktop"})
  @Execute(asUser = User.ANONYMOUS)
  public void AnonInEUShouldNotGetModalIfOptedInOnDesktop() {
    TrackingOptInPage trackingModal = setGeoCookieToGermanyAndNavigate();
    Assertion.assertFalse(trackingModal.isVisible());
  }

  @Test(groups = {"discussions-tracking-opt-in-desktop"})
  @Execute(asUser = User.USER, trackingOptIn = false, trackingOptOut = true)
  public void loggedInEUShouldNotGetModalIfOptedOutOnDesktop() {
    TrackingOptInPage trackingModal = setGeoCookieToGermanyAndNavigate();
    Assertion.assertFalse(trackingModal.isVisible());
  }

  @Test(groups = {"discussions-tracking-opt-in-desktop"})
  @Execute(asUser = User.ANONYMOUS, trackingOptIn = false, trackingOptOut = true)
  public void AnonInEUShouldNotGetModalIfOptedOutOnDesktop() {
    TrackingOptInPage trackingModal = setGeoCookieToGermanyAndNavigate();
    Assertion.assertFalse(trackingModal.isVisible());
  }

  @NetworkTrafficDump(useMITM = true)
  @Test(groups = {
      "discussions-tracking-opt-in-desktop"}, dataProviderClass = TrackingOptInDataProvider.class, dataProvider = "googleAnalyticAnonymizedUser")
  @Execute(asUser = User.ANONYMOUS, trackingOptIn = false)
  public void anonInEUOnRejectShouldGetAnonymizedGoogleTracking(List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInPage trackingModal = setGeoCookieToGermanyAndNavigate();
    Assertion.assertTrue(trackingModal.isVisible());
    trackingModal.clickRejectButton();

    Assertion.assertTrue(trackingModal.areResponsesByUrlPatternSuccessful(urlPatterns,
                                                                          networkTrafficInterceptor
    ));
  }

    /*
        MOBILE TESTS
    */

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Execute(asUser = User.ANONYMOUS, trackingOptIn = false)
  @Test(groups = {"discussions-tracking-opt-in-mobile"})
  public void testModalVisibilityForAnonOnMobile() {
    TrackingOptInPage trackingModal = setGeoCookieToGermanyAndNavigate();
    Assertion.assertTrue(trackingModal.isVisible());
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = {"discussions-tracking-opt-in-mobile"})
  @Execute(asUser = User.USER)
  public void loggedInEUShouldNotGetModalIfOptedInOnMobile() {
    TrackingOptInPage trackingModal = setGeoCookieToGermanyAndNavigate();
    Assertion.assertFalse(trackingModal.isVisible());
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = {"discussions-tracking-opt-in-mobile"})
  @Execute(asUser = User.ANONYMOUS)
  public void AnonInEUShouldNotGetModalIfOptedInOnMobile() {
    TrackingOptInPage trackingModal = setGeoCookieToGermanyAndNavigate();
    Assertion.assertFalse(trackingModal.isVisible());
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = {"discussions-tracking-opt-in-mobile"})
  @Execute(asUser = User.USER, trackingOptIn = false, trackingOptOut = true)
  public void loggedInEUShouldNotGetModalIfOptedOutOnMobile() {
    TrackingOptInPage trackingModal = setGeoCookieToGermanyAndNavigate();
    Assertion.assertFalse(trackingModal.isVisible());
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = {"discussions-tracking-opt-in-mobile"})
  @Execute(asUser = User.ANONYMOUS, trackingOptIn = false, trackingOptOut = true)
  public void AnonInEUShouldNotGetModalIfOptedOutOnMobile() {
    TrackingOptInPage trackingModal = setGeoCookieToGermanyAndNavigate();
    Assertion.assertFalse(trackingModal.isVisible());
  }

    /*
        HELPERS
    */

  private TrackingOptInPage setGeoCookieToGermanyAndNavigate() {
    TrackingOptInPage trackingModal = new TrackingOptInPage();
    trackingModal.setGeoCookie(driver, "EU", "DE");
    new PostsListPage().open();
    trackingModal.logTrackingCookieValue();

    return trackingModal;
  }
}
