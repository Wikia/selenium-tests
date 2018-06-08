package com.wikia.webdriver.testcases.mobilewikitests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.dataprovider.TrackingOptInDataProvider;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.pages.ArticlePage;
import com.wikia.webdriver.elements.oasis.pages.PrivacyPolicyPage;
import com.wikia.webdriver.pageobjectsfactory.componentobject.TrackingOptInModal;

import org.testng.annotations.Test;


@Test(groups = {"mobile-wiki-tracking-opt-in"})
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class TrackingOptInModalTests extends NewTestTemplate {

  @Execute(asUser = User.ANONYMOUS, trackingOptIn = false)
  @Test(groups = {"mobile-wiki-tracking-opt-in"},
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "GDPRCountriesForTest"
  )
  public void testModalVisibilityForAnon(String continent, String country, boolean shouldGetModal) {
    TrackingOptInModal.setGeoCookie(driver, continent, country);
    new ArticlePage().open();

    Log.info("Geo cookie: ", driver.manage().getCookieNamed("Geo").getValue());
    Assertion.assertEquals(new TrackingOptInModal().isVisible(), shouldGetModal);
  }

  @Execute(asUser = User.ANONYMOUS, trackingOptIn = false)
  @Test(groups = {"mobile-wiki-tracking-opt-in-all-countries"},
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "GDPRcountries"
  )
  public void testModalVisibilityForAnonAllCountries(String continent, String country, boolean shouldGetModal) {
    TrackingOptInModal.setGeoCookie(driver, continent, country);
    new ArticlePage().open();

    Log.info("Geo cookie: ", driver.manage().getCookieNamed("Geo").getValue());
    Assertion.assertEquals(new TrackingOptInModal().isVisible(), shouldGetModal);
  }

  @Execute(asUser = User.USER, trackingOptIn = false)
  @Test(groups = {"mobile-wiki-tracking-opt-in"},
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "GDPRCountriesForTest"
  )
  public void testModalVisibilityForLoggedInWhoNeverOptedIn(String continent, String country,
                                                            boolean shouldGetModal) {
    TrackingOptInModal.setGeoCookie(driver, continent, country);
    new ArticlePage().open();

    Log.info("Geo cookie: ", driver.manage().getCookieNamed("Geo").getValue());
    Assertion.assertEquals(new TrackingOptInModal().isVisible(), shouldGetModal);
  }

  @Execute(asUser = User.USER, trackingOptIn = false)
  @Test(groups = {"mobile-wiki-tracking-opt-in-all-countries"},
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "GDPRcountries"
  )
  public void testModalVisibilityForLoggedInWhoNeverOptedInAllCountries(String continent, String country,
                                                            boolean shouldGetModal) {
    TrackingOptInModal.setGeoCookie(driver, continent, country);
    new ArticlePage().open();

    Log.info("Geo cookie: ", driver.manage().getCookieNamed("Geo").getValue());
    Assertion.assertEquals(new TrackingOptInModal().isVisible(), shouldGetModal);
  }

  @Test(groups = {"mobile-wiki-tracking-opt-in"})
  @Execute(asUser = User.USER, trackingOptIn = true)
  public void loggedInUserInEUShouldNotGetModalIfOptedIn() {
    TrackingOptInModal.setGeoCookie(driver, "EU", "DE");
    new ArticlePage().open();

    Log.info("Geo cookie: ", driver.manage().getCookieNamed("Geo").getValue());
    Assertion.assertFalse(new TrackingOptInModal().isVisible());
  }

  @Test(groups = {"mobile-wiki-tracking-opt-in"})
  @Execute(asUser = User.ANONYMOUS, trackingOptIn = true)
  public void anonUserInEUShouldNotGetModalIfOptedIn() {
    TrackingOptInModal.setGeoCookie(driver, "EU", "DE");
    new ArticlePage().open();

    Log.info("Geo cookie: ", driver.manage().getCookieNamed("Geo").getValue());
    Assertion.assertFalse(new TrackingOptInModal().isVisible());
  }

  @Test(groups = {"mobile-wiki-tracking-opt-in"})
  @Execute(asUser = User.USER, trackingOptIn = false, trackingOptOut = true)
  public void loggedInUserInEUShouldNotGetModalIfOptedOut() {
    TrackingOptInModal.setGeoCookie(driver, "EU", "DE");
    new ArticlePage().open();

    Log.info("Geo cookie: ", driver.manage().getCookieNamed("Geo").getValue());
    Assertion.assertFalse(new TrackingOptInModal().isVisible());
  }

  @Test(groups = {"mobile-wiki-tracking-opt-in"})
  @Execute(asUser = User.ANONYMOUS, trackingOptIn = false, trackingOptOut = true)
  public void anonUserInEUShouldNotGetModalIfOptedOut() {
    TrackingOptInModal.setGeoCookie(driver, "EU", "DE");
    new ArticlePage().open();

    Log.info("Geo cookie: ", driver.manage().getCookieNamed("Geo").getValue());
    Assertion.assertFalse(new TrackingOptInModal().isVisible());
  }

  @Test(groups = {"mobile-wiki-tracking-opt-in"})
  @Execute(asUser = User.USER, trackingOptIn = true)
  public void loggedInUserInEUGetsModalBackWhenCookieExpires() {
    TrackingOptInModal.setGeoCookie(driver, "EU", "DE");
    new ArticlePage().open();

    Log.info("Geo cookie: ", driver.manage().getCookieNamed("Geo").getValue());
    Assertion.assertFalse(new TrackingOptInModal().isVisible());

    driver.manage().deleteAllCookies();

    new ArticlePage().open();

    Assertion.assertTrue(new TrackingOptInModal().isVisible());
  }

  @Test(groups = {"mobile-wiki-tracking-opt-in"})
  @Execute(asUser = User.ANONYMOUS, trackingOptIn = true)
  public void anonUserInEUGetsModalBackWhenCookieExpires() {
    TrackingOptInModal.setGeoCookie(driver, "EU", "DE");
    new ArticlePage().open();

    Log.info("Geo cookie: ", driver.manage().getCookieNamed("Geo").getValue());
    Assertion.assertFalse(new TrackingOptInModal().isVisible());

    driver.manage().deleteAllCookies();

    new ArticlePage().open();

    Assertion.assertTrue(new TrackingOptInModal().isVisible());
  }

  @Test(groups = {"mobile-wiki-tracking-opt-in"})
  @Execute(asUser = User.ANONYMOUS, trackingOptIn = true)
  public void anonUserInEUGetsModalBackWhenResetsCookiesViaPrivacyPolicyPage() {
    TrackingOptInModal.setGeoCookie(driver, "EU", "DE");
    new ArticlePage().open();

    Log.info("Geo cookie: ", driver.manage().getCookieNamed("Geo").getValue());
    Assertion.assertFalse(new TrackingOptInModal().isVisible());

    PrivacyPolicyPage privacyPolicy = new PrivacyPolicyPage();
    privacyPolicy.navigateToPrivacyPolicyPage();
    privacyPolicy.clickResetTrackingButton();

    Assertion.assertTrue(new TrackingOptInModal().isVisible());
  }

  @Test(groups = {"mobile-wiki-tracking-opt-in"})
  @Execute(asUser = User.USER, trackingOptIn = true)
  public void loggedInUserInEUGetsModalBackWhenResetsCookiesViaPrivacyPolicyPage() {
    TrackingOptInModal.setGeoCookie(driver, "EU", "DE");
    new ArticlePage().open();

    Log.info("Geo cookie: ", driver.manage().getCookieNamed("Geo").getValue());
    Assertion.assertFalse(new TrackingOptInModal().isVisible());

    PrivacyPolicyPage privacyPolicy = new PrivacyPolicyPage();
    privacyPolicy.navigateToPrivacyPolicyPage();
    privacyPolicy.clickResetTrackingButton();

    Assertion.assertTrue(new TrackingOptInModal().isVisible());
  }
}
