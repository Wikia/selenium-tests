package com.wikia.webdriver.testcases.globalnavigationbar;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.globalnav.GlobalNavigation;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialWikiActivityPageObject;

import org.openqa.selenium.Dimension;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

@Test(groups = {"globalnavigationbar", "globalnavigationbarLayout"})
public class Layout extends NewTestTemplate{

  private static final Dimension HUBS_IN_DROPDOWN_RESOLUTION = new Dimension(768, 1024);
  private static final Dimension HUBS_OUTSIDE_DROPDOWN_RESOLUTION = new Dimension(1024, 1024);
  private static final List<String> EXPECTED_LINKS_BIG_RESOLUTION =
      Arrays.asList("Top Communities", "Community CenFl", "START A WIKIA");
  private static final List<String> EXPECTED_LINKS_SMALL_RESOLUTION =
      Arrays.asList("Games", "Movies", "TV", "Top Communities", "Community Central", "START A WIKIA");

  private final static String deWikiName = "de.gta";
  private static final Dimension HIDE_LOGO_RESOLUTION = new Dimension(1200, 720);

  @Test(groups = {"globalNavigationBarIsFixedOnScroll"})
  public void globalNavigationBarIsFixedOnScroll() {
    SpecialWikiActivityPageObject wikiActivity = new SpecialWikiActivityPageObject(driver).open();
    wikiActivity.verifyGlobalNavigation();
    wikiActivity.scrollToFooter();
    wikiActivity.verifyGlobalNavigation();
  }

  @Test(groups = {"dropdownContainsExpectedLinksOnResolutionChange"})
  public void dropdownContainsExpectedLinksOnResolutionChange() {
    HomePage homePage = new HomePage();
    GlobalNavigation globalNav = new GlobalNavigation();
    homePage.openWikiPage(this.wikiURL);

    driver.manage().window().setSize(HUBS_OUTSIDE_DROPDOWN_RESOLUTION);

    globalNav.openExploreWikiaDropdown();
    Assertion.assertEquals(globalNav.getDropdownLinks(), EXPECTED_LINKS_BIG_RESOLUTION);
    globalNav.closeDropdown();

    driver.manage().window().setSize(HUBS_IN_DROPDOWN_RESOLUTION);
    globalNav.openExploreWikiaDropdown();
    Assertion.assertEquals(globalNav.getDropdownLinks(), EXPECTED_LINKS_SMALL_RESOLUTION);
  }

  @Test(groups = {"gameStarLogoIsNotPresentOn768x1024WidthResolution"})
  public void gameStarLogoIsNotPresentOn768x1024WidthResolution() {
    HomePage homePage = new HomePage();
    homePage.openWikiPage(urlBuilder.getUrlForWiki(deWikiName));
    homePage.resizeWindow(HIDE_LOGO_RESOLUTION);

    Assertion.assertFalse((new GlobalNavigation()).isGameStarLogoDisplayed(),
                          "GameStar Logo shouldn't be visible");
  }

  @Test(groups = {"linksArePresentOn1024x1024Resolution"})
  public void linksArePresentOn1024x1024Resolution() {
    HomePage homePage = new HomePage();
    GlobalNavigation globalNav = new GlobalNavigation();
    homePage.openWikiPage(this.wikiURL);

    driver.manage().window().setSize(HUBS_OUTSIDE_DROPDOWN_RESOLUTION);
    Assertion.assertTrue(globalNav.areHubsLinksVisible());

    driver.manage().window().setSize(HUBS_IN_DROPDOWN_RESOLUTION);
    Assertion.assertFalse(globalNav.areHubsLinksVisible());
  }

  @DataProvider
  public Object[][] getWikisWithDisabledLocalSearch() {
    return new Object[][]{
        {"de.wikia"},
        {"wikia"}
    };
  }

  @Test(
      groups = {"localSearchIsDisabled"},
      dataProvider = "getWikisWithDisabledLocalSearch"
  )
  public void localSearchIsDisabled(String wikiName) {
    HomePage homePage = new HomePage();
    homePage.getUrl(urlBuilder.getUrlForWiki(wikiName));
    GlobalNavigation globalNav = homePage.getGlobalNavigation();

    Assertion.assertTrue(globalNav.isLocalSearchDisabled());
  }
}
