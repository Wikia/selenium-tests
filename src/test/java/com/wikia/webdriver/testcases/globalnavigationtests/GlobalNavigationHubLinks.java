package com.wikia.webdriver.testcases.globalnavigationtests;

import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.globalnav.GlobalNavigationPageObject;

import org.testng.annotations.Test;

@Test(groups = {"HubLinksInGlobalNav"})
public class GlobalNavigationHubLinks extends NewTestTemplate {

  @Test(groups = {"TestHubLinksInGlobalNav_001", "GlobalNav"},
  enabled = false)
 public void TestHubLinksInGlobalNav_001_clickHubsLinks() {
    GlobalNavigationPageObject globalNav = new HomePageObject(driver).getGlobalNavigation();

    for (GlobalNavigationPageObject.Hub hubName : GlobalNavigationPageObject.Hub.values()) {
      globalNav.openHub(hubName);
    }
  }
}
