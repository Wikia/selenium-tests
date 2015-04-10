package com.wikia.webdriver.testcases.globalnavigationtests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePageObject;

/**
 * @author Bogna 'bognix' Knychała
 * @ownership Consumer
 */
public class GlobalNavigationWikiaLogo extends NewTestTemplate {

  @DataProvider
  public Object[][] getCentralWikiaUrlForWiki() {
    return new Object[][] { {"muppet", "wikia"}, {"de.gta", "de.wikia"},
        {"ru.elderscrolls", "ru.community"}, {"zh.pad", "wikia"}};
  }

  @RelatedIssue(issueID = "MAIN-4224")
  @Test(groups = {"TestWikiaLogoInGlobalNav_001", "GlobalNav"},
      dataProvider = "getCentralWikiaUrlForWiki")
  public void TestWikiaLogoInGlobalNav_001_centralWikiExists(String wikiName,
      String expectedCentralUrl) {
    HomePageObject homePage = new HomePageObject(driver);
    homePage.getUrl(urlBuilder.getUrlForWiki(wikiName));
    homePage.getVenusGlobalNav().clickWikiaLogo();
    Assertion.assertStringContains(urlBuilder.getUrlForWiki(expectedCentralUrl),
        driver.getCurrentUrl());
  }
}
