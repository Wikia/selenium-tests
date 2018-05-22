package com.wikia.webdriver.testcases.trackingoptintests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.TrackingOptInDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.TrackingOptInModal;

import org.testng.annotations.Test;


@Test(groups = {"TrackingOptIn"})
public class TrackingOptInTestsOasis extends NewTestTemplate {

  private static final Page GTA_WIKI_HOME_PAGE = new Page("gta", "Main_Page");

  @Execute(asUser = User.ANONYMOUS)
  @Test(groups = {"oasis-tracking-opt-in"},
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "GDPRcountries"
  )
  public void testModalVisibilityForAnon(String continent, String country, boolean shouldGetModal) {
    TrackingOptInModal.setGeoCookie(driver, continent, country);
    TrackingOptInModal modal = new TrackingOptInModal();
    modal.getUrl(modal.urlOptInModalDisplayedOasis(GTA_WIKI_HOME_PAGE));

    Assertion.assertEquals(modal.isVisible(), shouldGetModal);
  }

  @Execute(asUser = User.USER)
  @Test(groups = {"oasis-tracking-opt-in"},
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "GDPRcountries"
  )
  public void testModalVisibilityForLoggedInWhoNeverOptedIn(String continent, String country,
                                                            boolean shouldGetModal) {
    TrackingOptInModal.setGeoCookie(driver, continent, country);
    TrackingOptInModal modal = new TrackingOptInModal();
    modal.getUrl(modal.urlOptInModalDisplayedOasis(GTA_WIKI_HOME_PAGE));

    Assertion.assertEquals(modal.isVisible(), shouldGetModal);
  }

  @Test(groups = {"oasis-tracking-opt-in"})
  @Execute(asUser = User.USER)
  public void loggedInUserInEUShouldNotGetModalIfOptInAccepted() {
    TrackingOptInModal.setGeoCookie(driver, "EU", "DE");
    TrackingOptInModal modal = new TrackingOptInModal();
    modal.getUrl(modal.urlOptInModalDisplayedOasis(GTA_WIKI_HOME_PAGE));

    modal.clickAcceptButton();
    modal.refreshPage();

    Assertion.assertFalse(modal.isVisible());
  }

  @Test(groups = {"oasis-tracking-opt-in"})
  @Execute(asUser = User.USER)
  public void loggedInUserInEUShouldNotGetModalIfOptInRejected() {
    TrackingOptInModal.setGeoCookie(driver, "EU", "DE");
    TrackingOptInModal modal = new TrackingOptInModal();
    modal.getUrl(modal.urlOptInModalDisplayedOasis(GTA_WIKI_HOME_PAGE));

    modal.clickRejectButton();
    modal.refreshPage();

    Assertion.assertFalse(modal.isVisible());
  }

  @Test(groups = {"oasis-tracking-opt-in"})
  public void anonUserInEUShouldNotGetModalIfOptInAccepted() {
    TrackingOptInModal.setGeoCookie(driver, "EU", "DE");
    TrackingOptInModal modal = new TrackingOptInModal();
    modal.getUrl(modal.urlOptInModalDisplayedOasis(GTA_WIKI_HOME_PAGE));

    modal.clickAcceptButton();
    modal.refreshPage();

    Assertion.assertFalse(modal.isVisible());
  }

  @Test(groups = {"oasis-tracking-opt-in"})
  public void anonUserInEUShouldNotGetModalIfOptInRejected() {
    TrackingOptInModal.setGeoCookie(driver, "EU", "DE");
    TrackingOptInModal modal = new TrackingOptInModal();
    modal.getUrl(modal.urlOptInModalDisplayedOasis(GTA_WIKI_HOME_PAGE));

    modal.clickRejectButton();
    modal.refreshPage();

    Assertion.assertFalse(modal.isVisible());
  }
}
