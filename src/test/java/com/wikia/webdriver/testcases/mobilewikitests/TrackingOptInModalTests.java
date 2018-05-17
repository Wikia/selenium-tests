package com.wikia.webdriver.testcases.mobilewikitests;

import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;

import org.junit.Test;

@org.testng.annotations.Test(groups = "Mobile-Wiki-Tracking-Opt-In")
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class TrackingOptInModalTests extends NewTestTemplate {

  @Test
  public void anonInEUShouldGetModal() {

  }

  @Test
  public void anonOutsideOfEUSHouldNotGetModal() {

  }

  @Test
  public void loggedInUserInEUShouldGetModalIfNeverOptedIn() {

  }

  @Test
  public void loggedInUserInEUShouldNotGetModalIfOptedIn() {

  }

  @Test
  public void loggedInUserOutsideEUShouldNotGetModal() {

  }
}
