package com.wikia.webdriver.testcases.mobilewikitests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.TrackingOptInModal;
import com.wikia.webdriver.elements.mercury.pages.ArticlePage;

import org.testng.annotations.Test;


@Test(groups = "mobile-wiki-tracking-opt-in")
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class TrackingOptInModalTests extends NewTestTemplate {

  @Execute(asUser = User.ANONYMOUS, trackingOptIn = false)
  @Test(groups = "mobile-wiki-tracking-opt-in")
  public void anonInEUShouldGetModal() {
    new ArticlePage().open();

    Assertion.assertTrue(new TrackingOptInModal().isVisible());
  }

  @Test(groups = "mobile-wiki-tracking-opt-in")
  public void anonOutsideOfEUSHouldNotGetModal() {

  }

  @Test(groups = "mobile-wiki-tracking-opt-in")
  public void loggedInUserInEUShouldGetModalIfNeverOptedIn() {

  }

  @Test(groups = "mobile-wiki-tracking-opt-in")
  public void loggedInUserInEUShouldNotGetModalIfOptedIn() {

  }

  @Test(groups = "mobile-wiki-tracking-opt-in")
  public void loggedInUserOutsideEUShouldNotGetModal() {

  }
}
