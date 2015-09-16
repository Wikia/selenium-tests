package com.wikia.webdriver.testcases.globalnavigationtests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.globalnav.VenusGlobalNavPageObject;

/**
 * @author Bogna 'bognix' Knychała
 * @ownership Content X-Wing
 */
@Test(groups = {"HubLinksInGlobalNav"})
public class GlobalNavigationHubLinks extends NewTestTemplate {

  @Test(groups = {"TestHubLinksInGlobalNav_001", "GlobalNav"})
  @RelatedIssue(issueID = "QAART-672", comment = "Please test this manually")
  public void TestHubLinksInGlobalNav_001_clickHubsLinks() {
    VenusGlobalNavPageObject globalNav = new HomePageObject(driver).getVenusGlobalNav();

    for (VenusGlobalNavPageObject.Hub hubName : VenusGlobalNavPageObject.Hub.values()) {
      globalNav.openHub(hubName);
    }
  }
}
