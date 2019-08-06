package com.wikia.webdriver.testcases.mobile;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.dataprovider.TrackingOptInDataProvider;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.communities.mobile.pages.ArticlePage;
import com.wikia.webdriver.elements.communities.desktop.pages.PrivacyPolicyPage;
import com.wikia.webdriver.pageobjectsfactory.componentobject.TrackingOptInPage;

import org.testng.annotations.Test;

@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class TrackingOptInModalTests extends NewTestTemplate {

  @Execute(asUser = User.ANONYMOUS, trackingOptIn = false)
  @Test(groups = {
      "mobile-wiki-tracking-opt-in"}, dataProviderClass = TrackingOptInDataProvider.class, dataProvider = "GDPRCountriesForTest")
  public void testModalVisibilityForAnon(String continent, String country, boolean shouldGetModal) {
    TrackingOptInPage.setGeoCookie(driver, continent, country);
    new ArticlePage().open();

    Log.info("Geo cookie: ", driver.manage().getCookieNamed("Geo").getValue());
    Assertion.assertEquals(new TrackingOptInPage().isVisible(), shouldGetModal);
  }

  @Execute(asUser = User.ANONYMOUS, trackingOptIn = false)
  @Test(groups = {
      "mobile-wiki-tracking-opt-in-all-countries"}, dataProviderClass = TrackingOptInDataProvider.class, dataProvider = "GDPRcountries")
  public void testModalVisibilityForAnonAllCountries(
      String continent, String country, boolean shouldGetModal
  ) {
    TrackingOptInPage.setGeoCookie(driver, continent, country);
    new ArticlePage().open();

    Log.info("Geo cookie: ", driver.manage().getCookieNamed("Geo").getValue());
    Assertion.assertEquals(new TrackingOptInPage().isVisible(), shouldGetModal);
  }

  @Execute(asUser = User.USER, trackingOptIn = false)
  @Test(groups = {
      "mobile-wiki-tracking-opt-in"}, dataProviderClass = TrackingOptInDataProvider.class, dataProvider = "GDPRCountriesForTest")
  public void testModalVisibilityForLoggedInWhoNeverOptedIn(
      String continent, String country, boolean shouldGetModal
  ) {
    TrackingOptInPage.setGeoCookie(driver, continent, country);
    new ArticlePage().open();

    Log.info("Geo cookie: ", driver.manage().getCookieNamed("Geo").getValue());
    Assertion.assertEquals(new TrackingOptInPage().isVisible(), shouldGetModal);
  }

  @Execute(asUser = User.USER, trackingOptIn = false)
  @Test(groups = {
      "mobile-wiki-tracking-opt-in-all-countries"}, dataProviderClass = TrackingOptInDataProvider.class, dataProvider = "GDPRcountries")
  public void testModalVisibilityForLoggedInWhoNeverOptedInAllCountries(
      String continent, String country, boolean shouldGetModal
  ) {
    TrackingOptInPage.setGeoCookie(driver, continent, country);
    new ArticlePage().open();

    Log.info("Geo cookie: ", driver.manage().getCookieNamed("Geo").getValue());
    Assertion.assertEquals(new TrackingOptInPage().isVisible(), shouldGetModal);
  }

  @Test(groups = {"mobile-wiki-tracking-opt-in"})
  @Execute(asUser = User.USER, trackingOptIn = true)
  public void loggedInUserInEUShouldNotGetModalIfOptedIn() {
    TrackingOptInPage.setGeoCookie(driver, "EU", "DE");
    new ArticlePage().open();

    Log.info("Geo cookie: ", driver.manage().getCookieNamed("Geo").getValue());
    Assertion.assertFalse(new TrackingOptInPage().isVisible());
  }

  @Test(groups = {"mobile-wiki-tracking-opt-in"})
  @Execute(asUser = User.ANONYMOUS, trackingOptIn = true)
  public void anonUserInEUShouldNotGetModalIfOptedIn() {
    TrackingOptInPage.setGeoCookie(driver, "EU", "DE");
    new ArticlePage().open();

    Log.info("Geo cookie: ", driver.manage().getCookieNamed("Geo").getValue());
    Assertion.assertFalse(new TrackingOptInPage().isVisible());
  }

  @Test(groups = {"mobile-wiki-tracking-opt-in"})
  @Execute(asUser = User.USER, trackingOptIn = false, trackingOptOut = true)
  public void loggedInUserInEUShouldNotGetModalIfOptedOut() {
    TrackingOptInPage.setGeoCookie(driver, "EU", "DE");
    new ArticlePage().open();

    Log.info("Geo cookie: ", driver.manage().getCookieNamed("Geo").getValue());
    Assertion.assertFalse(new TrackingOptInPage().isVisible());
  }

  @Test(groups = {"mobile-wiki-tracking-opt-in"})
  @Execute(asUser = User.ANONYMOUS, trackingOptIn = false, trackingOptOut = true)
  public void anonUserInEUShouldNotGetModalIfOptedOut() {
    TrackingOptInPage.setGeoCookie(driver, "EU", "DE");
    new ArticlePage().open();

    Log.info("Geo cookie: ", driver.manage().getCookieNamed("Geo").getValue());
    Assertion.assertFalse(new TrackingOptInPage().isVisible());
  }

  @Test(groups = {"mobile-wiki-tracking-opt-in"})
  @Execute(asUser = User.USER, trackingOptIn = true)
  public void loggedInUserInEUGetsModalBackWhenCookieExpires() {
    TrackingOptInPage.setGeoCookie(driver, "EU", "DE");
    new ArticlePage().open();

    Log.info("Geo cookie: ", driver.manage().getCookieNamed("Geo").getValue());
    Assertion.assertFalse(new TrackingOptInPage().isVisible());

    TrackingOptInPage.deleteTrackingOptInCookie(driver);

    new ArticlePage().open();

    Assertion.assertTrue(new TrackingOptInPage().isVisible());
  }

  @Test(groups = {"mobile-wiki-tracking-opt-in"})
  @Execute(asUser = User.ANONYMOUS, trackingOptIn = true)
  public void anonUserInEUGetsModalBackWhenCookieExpires() {
    TrackingOptInPage.setGeoCookie(driver, "EU", "DE");
    new ArticlePage().open();

    Log.info("Geo cookie: ", driver.manage().getCookieNamed("Geo").getValue());
    Assertion.assertFalse(new TrackingOptInPage().isVisible());

    TrackingOptInPage.deleteTrackingOptInCookie(driver);

    new ArticlePage().open();

    Assertion.assertTrue(new TrackingOptInPage().isVisible());
  }

  @Test(groups = {"mobile-wiki-tracking-opt-in"})
  @Execute(asUser = User.ANONYMOUS, trackingOptIn = true)
  public void anonUserInEUGetsModalBackWhenResetsCookiesViaPrivacyPolicyPage() {
    TrackingOptInPage.setGeoCookie(driver, "EU", "DE");

    Log.info("Geo cookie: ", driver.manage().getCookieNamed("Geo").getValue());

    PrivacyPolicyPage privacyPolicy = new PrivacyPolicyPage();
    privacyPolicy.navigateToPrivacyPolicyPage();
    Assertion.assertFalse(new TrackingOptInPage().isVisible());

    privacyPolicy.clickResetTrackingButton();
    Assertion.assertTrue(new TrackingOptInPage().isVisible());
  }

  @Test(groups = {"mobile-wiki-tracking-opt-in"})
  @Execute(asUser = User.USER, trackingOptIn = true)
  public void loggedInUserInEUGetsModalBackWhenResetsCookiesViaPrivacyPolicyPage() {
    TrackingOptInPage.setGeoCookie(driver, "EU", "DE");

    Log.info("Geo cookie: ", driver.manage().getCookieNamed("Geo").getValue());

    PrivacyPolicyPage privacyPolicy = new PrivacyPolicyPage();
    privacyPolicy.navigateToPrivacyPolicyPage();
    Assertion.assertFalse(new TrackingOptInPage().isVisible());

    privacyPolicy.clickResetTrackingButton();
    Assertion.assertTrue(new TrackingOptInPage().isVisible());
  }
}
