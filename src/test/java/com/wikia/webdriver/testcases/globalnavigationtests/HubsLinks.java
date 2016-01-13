package com.wikia.webdriver.testcases.globalnavigationtests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.globalnav.GlobalNavigationPageObject;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;


@Test(groups = {"GlobalNav"})
public class HubsLinks extends NewTestTemplate {

  private Dimension HUBS_IN_DROPDOWN_RESOLUTION = new Dimension(768, 1024);
  private Dimension HUBS_OUTSIDE_DROPDOWN_RESOLUTION = new Dimension(1024, 1024);

  @Test(groups = {"TestHubsLinks_001"})
  public void TestHubsLinks_001_linksArePresent() {
    HomePageObject homePage = new HomePageObject(driver);
    GlobalNavigationPageObject globalNav = new GlobalNavigationPageObject(driver);
    homePage.openWikiPage(this.wikiURL);

    driver.manage().window().setSize(HUBS_OUTSIDE_DROPDOWN_RESOLUTION);
    Assertion.assertTrue(globalNav.areHubsLinksVisible());

    driver.manage().window().setSize(HUBS_IN_DROPDOWN_RESOLUTION);
    Assertion.assertFalse(globalNav.areHubsLinksVisible());
  }
}
