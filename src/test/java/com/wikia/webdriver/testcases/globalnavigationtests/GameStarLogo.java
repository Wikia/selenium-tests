package com.wikia.webdriver.testcases.globalnavigationtests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.globalnav.GlobalNavigationPageObject;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

public class GameStarLogo extends NewTestTemplate {

  private final static String deWikiName = "de.gta";
  private static final Dimension HIDE_LOGO_RESOLUTION = new Dimension(1200, 720);

  @Test(groups = {"TestGameStarLogo_001", "GameStarLogo", "GlobalNav"})
  public void TestGameStarLogo_002_gameStarLogoNotPresentOnSmallResolution() {
    HomePageObject homePage = new HomePageObject(driver);
    GlobalNavigationPageObject globalNav = new GlobalNavigationPageObject(driver);
    homePage.openWikiPage(urlBuilder.getUrlForWiki(deWikiName));
    homePage.resizeWindow(HIDE_LOGO_RESOLUTION);

    Assertion.assertFalse(globalNav.isGameStarLogoDisplayed(),
            "GameStar Logo shouldn't be visible");
  }
}
