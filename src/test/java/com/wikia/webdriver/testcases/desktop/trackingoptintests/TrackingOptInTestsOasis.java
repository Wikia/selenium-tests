package com.wikia.webdriver.testcases.desktop.trackingoptintests;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.TrackingOptInDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.TrackingOptInPage;

import org.testng.annotations.Test;

@Test(groups = {"TrackingOptIn"})
public class TrackingOptInTestsOasis extends NewTestTemplate {

  private static final Page TEST_PAGE = new Page(URLsContent.MEDIAWIKI119_TEST_WIKI, "Main_Page");

  @Execute(asUser = User.ANONYMOUS, trackingOptIn = false)
  @Test(groups = {
      "desktop-tracking-opt-in"}, dataProviderClass = TrackingOptInDataProvider.class, dataProvider = "GDPRCountriesForTest")
  public void testModalVisibilityForAnon(String continent, String country, boolean shouldGetModal) {
    TrackingOptInPage.setGeoCookie(driver, continent, country);
    TrackingOptInPage modal = new TrackingOptInPage();
    modal.getUrl(modal.urlOptInModalDisplayedOasis(TEST_PAGE));

    Assertion.assertEquals(modal.isVisible(), shouldGetModal);
  }

  @Execute(asUser = User.USER, trackingOptIn = false)
  @Test(groups = {
      "desktop-tracking-opt-in"}, dataProviderClass = TrackingOptInDataProvider.class, dataProvider = "GDPRCountriesForTest")
  public void testModalVisibilityForLoggedInWhoNeverOptedIn(
      String continent, String country, boolean shouldGetModal
  ) {
    TrackingOptInPage.setGeoCookie(driver, continent, country);
    TrackingOptInPage modal = new TrackingOptInPage();
    modal.getUrl(modal.urlOptInModalDisplayedOasis(TEST_PAGE));

    Assertion.assertEquals(modal.isVisible(), shouldGetModal);
  }

  @Test(groups = {"desktop-tracking-opt-in"})
  @Execute(asUser = User.USER, trackingOptIn = false)
  public void loggedInUserInEUShouldNotGetModalIfOptInAccepted() {
    TrackingOptInPage.setGeoCookie(driver, "EU", "DE");
    TrackingOptInPage modal = new TrackingOptInPage();
    modal.getUrl(modal.urlOptInModalDisplayedOasis(TEST_PAGE));

    modal.clickAcceptButton();
    modal.refreshPage();

    Assertion.assertFalse(modal.isVisible());
  }

  @Test(groups = {"desktop-tracking-opt-in"})
  @Execute(asUser = User.USER, trackingOptIn = false)
  public void loggedInUserInEUShouldNotGetModalIfOptInRejected() {
    TrackingOptInPage.setGeoCookie(driver, "EU", "DE");
    TrackingOptInPage modal = new TrackingOptInPage();
    modal.getUrl(modal.urlOptInModalDisplayedOasis(TEST_PAGE));

    modal.clickRejectButton();
    modal.refreshPage();

    Assertion.assertFalse(modal.isVisible());
  }

  @Test(groups = {"desktop-tracking-opt-in"})
  @Execute(trackingOptIn = false)
  public void anonUserInEUShouldNotGetModalIfOptInAccepted() {
    TrackingOptInPage.setGeoCookie(driver, "EU", "DE");
    TrackingOptInPage modal = new TrackingOptInPage();
    modal.getUrl(modal.urlOptInModalDisplayedOasis(TEST_PAGE));

    modal.clickAcceptButton();
    modal.refreshPage();

    Assertion.assertFalse(modal.isVisible());
  }

  @Test(groups = {"desktop-tracking-opt-in"})
  @Execute(trackingOptIn = false)
  public void anonUserInEUShouldNotGetModalIfOptInRejected() {
    TrackingOptInPage.setGeoCookie(driver, "EU", "DE");
    TrackingOptInPage modal = new TrackingOptInPage();
    modal.getUrl(modal.urlOptInModalDisplayedOasis(TEST_PAGE));

    modal.clickRejectButton();
    modal.refreshPage();

    Assertion.assertFalse(modal.isVisible());
  }
}
