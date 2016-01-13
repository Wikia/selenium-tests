package com.wikia.webdriver.testcases.globalnavigationtests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.globalnav.GlobalNavigationPageObject;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

@Test(groups = {"GameStarLogo", "GlobalNav"})
public class GameStarLogo extends NewTestTemplate {

  private final static String deWikiName = "de.gta";
  private static final Dimension HIDE_LOGO_RESOLUTION = new Dimension(1200, 720);

  @Test(groups = {"TestGameStarLogo_001", "GameStarLogo", "GlobalNav"})
  public void TestGameStarLogo_001_gameStarLogoNotPresentOnSmallResolution() {
    HomePageObject homePage = new HomePageObject(driver);
    homePage.openWikiPage(urlBuilder.getUrlForWiki(deWikiName));
    homePage.resizeWindow(HIDE_LOGO_RESOLUTION);

    Assertion.assertFalse((new GlobalNavigationPageObject(driver)).isGameStarLogoDisplayed(),
            "GameStar Logo shouldn't be visible");
  }
}
