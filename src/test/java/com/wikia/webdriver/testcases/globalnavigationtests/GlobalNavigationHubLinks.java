package com.wikia.webdriver.testcases.globalnavigationtests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.ArticleContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.globalnav.VenusGlobalNavPageObject;

/**
 * @author Bogna 'bognix' Knychała
 * @ownership Content X-Wing
 */
@Test(groups = {"HubLinksInGlobalNav"})
public class GlobalNavigationHubLinks extends NewTestTemplate {

  @Test(groups = {"TestHubLinksInGlobalNav_001", "GlobalNav"})
  public void TestHubLinksInGlobalNav_001_clickHubsLinks() {
    ArticleContent.clear();

    VenusGlobalNavPageObject globalNav = new ArticlePageObject(driver).open().getVenusGlobalNav();
    globalNav.openHubsMenuViaHover();
    Assertion.assertTrue(globalNav.isHubsMenuOpened());

    for (VenusGlobalNavPageObject.Hub hubName : VenusGlobalNavPageObject.Hub.values()) {
      globalNav.openHub(hubName);
    }
  }
}
