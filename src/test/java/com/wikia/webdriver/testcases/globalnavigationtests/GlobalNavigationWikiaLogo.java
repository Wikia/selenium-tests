package com.wikia.webdriver.testcases.globalnavigationtests;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePageObject;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Bogna 'bognix' Knychała
 * @ownership Content X-Wing Wikia
 */
@Test(groups = {"WikiaLogo"})
public class GlobalNavigationWikiaLogo extends NewTestTemplate {

  @DataProvider
  public Object[][] getCentralWikiaUrlForWiki() {
    return new Object[][] { {"muppet", "wikia"}, {"de.gta", "de.wikia"},
        {"ru.elderscrolls", "ru.community"}, {"zh.pad", "wikia"}};
  }

  @Test(groups = {"TestWikiaLogoInGlobalNav_001", "GlobalNav"},
      dataProvider = "getCentralWikiaUrlForWiki")
  public void TestWikiaLogoInGlobalNav_001_centralWikiExists(String wikiName,
      String expectedCentralUrl) {
    HomePageObject homePage = new HomePageObject(driver);
    homePage.getUrl(urlBuilder.getUrlForWiki(wikiName));
    homePage.getVenusGlobalNav().clickWikiaLogo();

    PageObjectLogging.log("CHECK URL", "Expected: " + urlBuilder.getUrlForWiki(expectedCentralUrl),
        new WebDriverWait(driver, 10).until(ExpectedConditions.urlContains(urlBuilder
            .getUrlForWiki(expectedCentralUrl))));
  }
}
