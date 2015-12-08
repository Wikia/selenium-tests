package com.wikia.webdriver.testcases.globalnavigationtests;

import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.globalnav.VenusGlobalNavPageObject;

import org.testng.annotations.Test;

/**
 * @ownership Content X-Wing Wikia
 */
@Test(groups = {"HubLinksInGlobalNav"})
public class GlobalNavigationHubLinks extends NewTestTemplate {

  @Test(groups = {"TestHubLinksInGlobalNav_001", "GlobalNav"})
 public void TestHubLinksInGlobalNav_001_clickHubsLinks() {
    VenusGlobalNavPageObject globalNav = new HomePageObject(driver).getVenusGlobalNav();

    for (VenusGlobalNavPageObject.Hub hubName : VenusGlobalNavPageObject.Hub.values()) {
      globalNav.openHub(hubName);
    }
  }
}
