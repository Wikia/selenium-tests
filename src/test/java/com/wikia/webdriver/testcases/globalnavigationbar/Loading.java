package com.wikia.webdriver.testcases.globalnavigationbar;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.globalnav.GlobalNavigationPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialWikiActivityPageObject;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

@Test(groups = {"globalnavigationbar", "Loading"})
public class Loading extends NewTestTemplate{

  private static final Dimension HUBS_IN_DROPDOWN_RESOLUTION = new Dimension(768, 1024);
  private static final Dimension HUBS_OUTSIDE_DROPDOWN_RESOLUTION = new Dimension(1024, 1024);
  private static final List<String> EXPECTED_LINKS_BIG_RESOLUTION =
      Arrays.asList("Top Communities", "Community Central", "START A WIKIA");
  private static final List<String> EXPECTED_LINKS_SMALL_RESOLUTION =
      Arrays.asList("Games", "Movies", "TV", "Top Communities", "Community Central", "START A WIKIA");

  private final static String deWikiName = "de.gta";
  private static final Dimension HIDE_LOGO_RESOLUTION = new Dimension(1200, 720);

  @Test(groups = {"Loading_001"})
	public void scrollDown() {
            WikiBasePageObject base = new WikiBasePageObject(driver);
	    SpecialWikiActivityPageObject wikiActivity = base.openSpecialWikiActivity();
	    wikiActivity.verifyGlobalNavigation();
	    wikiActivity.scrollToFooter();
	    wikiActivity.verifyGlobalNavigation();
	}

  @Test(groups = {"Loading_002"})
  public void TestExploreWikiaDropdown_001_dropdownIsPresent() {
    HomePageObject homePage = new HomePageObject(driver);
    GlobalNavigationPageObject globalNav = new GlobalNavigationPageObject(driver);
    homePage.openWikiPage(this.wikiURL);

    driver.manage().window().setSize(HUBS_OUTSIDE_DROPDOWN_RESOLUTION);

    globalNav.openExploreWikiaDropdown();
    Assertion.assertEquals(globalNav.getDropdownLinks(), EXPECTED_LINKS_BIG_RESOLUTION);
    globalNav.closeDropdown();

    driver.manage().window().setSize(HUBS_IN_DROPDOWN_RESOLUTION);
    globalNav.openExploreWikiaDropdown();
    Assertion.assertEquals(globalNav.getDropdownLinks(), EXPECTED_LINKS_SMALL_RESOLUTION);
  }

  @Test(groups = {"Loading_003"})
  public void TestGameStarLogo_001_gameStarLogoNotPresentOnSmallResolution() {
    HomePageObject homePage = new HomePageObject(driver);
    homePage.openWikiPage(urlBuilder.getUrlForWiki(deWikiName));
    homePage.resizeWindow(HIDE_LOGO_RESOLUTION);

    Assertion.assertFalse((new GlobalNavigationPageObject(driver)).isGameStarLogoDisplayed(),
                          "GameStar Logo shouldn't be visible");
  }

  @Test(groups = {"Loading_004"})
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
