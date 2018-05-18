package com.wikia.webdriver.testcases.mobilewikitests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.dataprovider.TrackingOptInDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.TrackingOptInModal;
import com.wikia.webdriver.elements.mercury.pages.ArticlePage;

import org.openqa.selenium.Cookie;
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
    setGeoCookie(continent, country);
    new ArticlePage().open();
System.out.println(driver.manage().getCookieNamed("Geo").getValue());
    Assertion.assertEquals(new TrackingOptInModal().isVisible(), shouldGetModal);
  }

  @Test(groups = {"mobile-wiki-tracking-opt-in"})
  public void anonOutsideOfEUSHouldNotGetModal() {

  }

  @Test(groups = {"mobile-wiki-tracking-opt-in"})
  public void loggedInUserInEUShouldGetModalIfNeverOptedIn() {

  }

  @Test(groups = {"mobile-wiki-tracking-opt-in"})
  public void loggedInUserInEUShouldNotGetModalIfOptedIn() {

  }

  @Test(groups = {"mobile-wiki-tracking-opt-in"})
  public void loggedInUserOutsideEUShouldNotGetModal() {

  }

  private void setGeoCookie(String continent, String country) {
    Cookie geoCookie = driver.manage().getCookieNamed("Geo");
    driver.manage().deleteCookie(geoCookie);
    driver.manage().addCookie(new Cookie(
        "Geo",
        "{%22region%22:%22WP%22%2C%22country%22:%22" + country + "%22%2C%22continent%22:%22" + continent + "%22}",
        String.format(".%s", Configuration.getEnvType().getWikiaDomain()),
        null,
        null
    ));
  }
}
