package com.wikia.webdriver.testcases.mobilewikitests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.dataprovider.TrackingOptInDataProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.pages.ArticlePage;
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
      dataProvider = "GDPRcountries"
  )
  public void testModalVisibilityForAnon(String continent, String country, boolean shouldGetModal) {
    TrackingOptInModal.setGeoCookie(driver, continent, country);
    new ArticlePage().open();

    PageObjectLogging.logInfo("Geo cookie: ", driver.manage().getCookieNamed("Geo").getValue());
    Assertion.assertEquals(new TrackingOptInModal().isVisible(), shouldGetModal);
  }

  @Execute(asUser = User.USER, trackingOptIn = false)
  @Test(groups = {"mobile-wiki-tracking-opt-in"},
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "GDPRcountries"
  )
  public void testModalVisibilityForLoggedInWhoNeverOptedIn(String continent, String country,
                                                            boolean shouldGetModal) {
    TrackingOptInModal.setGeoCookie(driver, continent, country);
    new ArticlePage().open();

    PageObjectLogging.logInfo("Geo cookie: ", driver.manage().getCookieNamed("Geo").getValue());
    Assertion.assertEquals(new TrackingOptInModal().isVisible(), shouldGetModal);
  }

  @Test(groups = {"mobile-wiki-tracking-opt-in"})
  @Execute(asUser = User.USER, trackingOptIn = true)
  public void loggedInUserInEUShouldNotGetModalIfOptedIn() {
    TrackingOptInModal.setGeoCookie(driver, "EU", "DE");
    new ArticlePage().open();

    PageObjectLogging.logInfo("Geo cookie: ", driver.manage().getCookieNamed("Geo").getValue());
    Assertion.assertFalse(new TrackingOptInModal().isVisible());
  }
}
