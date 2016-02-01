package com.wikia.webdriver.testcases.globalnavigationbar;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.globalnav.GlobalNavigationPageObject;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(groups = {"globalnavigationbar", "globalnavigationbarNavigating"})
public class Navigating extends NewTestTemplate {

  private final String EN_COMMUNITY = "muppet";
  private final String FANDOM_URL = "http://fandom.wikia.com/";

  @DataProvider
  public Object[][] getCentralWikiaUrlForWiki() {
    return new Object[][]{{"de.gta", "de.wikia"},
            {"ru.elderscrolls", "ru.wikia"}, {"zh.pad", "zh-tw.wikia"}};
  }

  @Test(groups = {"wikiaLogoClickOpensCentralWiki"},
          dataProvider = "getCentralWikiaUrlForWiki")
  public void wikiaLogoClickOpensCentralWiki(String wikiName,
                                             String expectedCentralUrl) {
    HomePageObject homePage = new HomePageObject(driver);
    homePage.getUrl(urlBuilder.getUrlForWiki(wikiName));
    homePage.getGlobalNavigation().clickWikiaLogo();

    PageObjectLogging.log("CHECK URL", "Expected: " + urlBuilder.getUrlForWiki(expectedCentralUrl),
            new WebDriverWait(driver, 10).until(ExpectedConditions.urlContains(urlBuilder
                    .getUrlForWiki(expectedCentralUrl))));
  }

  @Test(groups = {"fandomLogoClickOnEnCommunityOpensFandomWikia"})
  public void fandomLogoClickOnEnCommunityOpensFandomWikia() {
    HomePageObject homePage = new HomePageObject(driver);
    homePage.getUrl(urlBuilder.getUrlForWiki(EN_COMMUNITY));
    GlobalNavigationPageObject globalNav = homePage.getGlobalNavigation();

    Assertion.assertTrue(globalNav.isFandomLogoVisible(), "Fandom logo not visible");

    globalNav.clickWikiaLogo();

    PageObjectLogging.log("CHECK URL", "Expected: " + FANDOM_URL,
            new WebDriverWait(driver, 10).until(ExpectedConditions.urlContains(FANDOM_URL)));
  }

  @Test(groups = {"clickOnHubsLinkOpensExpectedHub"},
          enabled = false)
  public void clickOnHubsLinkOpensExpectedHub() {
    GlobalNavigationPageObject globalNav = new HomePageObject(driver).getGlobalNavigation();

    for (GlobalNavigationPageObject.Hub hubName : GlobalNavigationPageObject.Hub.values()) {
      globalNav.openHub(hubName);
    }
  }
}
