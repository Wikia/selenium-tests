package com.wikia.webdriver.testcases.globalnavigationbar;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.globalnav.GlobalNavigation;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialWikiActivityPageObject;

@Test(groups = {"globalnavigationbar", "globalnavigationbarLayout"})
public class Layout extends NewTestTemplate {

  /**
   * Additional 20 px are added to width dimension because of the scrollbar appearance. Dimension is
   * used to resize the window, while test is verifying viewport size.
   */
  private static final String HUBS_OUTSIDE_DROPDOWN_RESOLUTION = "1044x1024";
  private static final Dimension HUBS_IN_DROPDOWN_RESOLUTION = new Dimension(768, 1024);
  private static final List<String> EXPECTED_LINKS_BIG_RESOLUTION =
      Arrays.asList("Trending Wikias", "Community Central");
  private static final List<String> EXPECTED_LINKS_SMALL_RESOLUTION =
      Arrays.asList("Games", "Movies", "TV", "Trending Wikias", "Community Central");

  private final static String deWikiName = "de.gta";
  private static final Dimension HIDE_LOGO_RESOLUTION = new Dimension(1200, 720);

  @Test(groups = {"globalNavigationBarIsFixedOnScroll"})
  public void globalNavigationBarIsFixedOnScroll() {
    SpecialWikiActivityPageObject wikiActivity = new SpecialWikiActivityPageObject(driver).open();
    wikiActivity.verifyGlobalNavigation();
    wikiActivity.scrollToFooter();
    wikiActivity.verifyGlobalNavigation();
  }

  @InBrowser(browser = Browser.FIREFOX, browserSize = HUBS_OUTSIDE_DROPDOWN_RESOLUTION)
  @Test(groups = {"verifyDropdownLinksOn1024x1024Resolution"})
  public void verifyDropdownLinksOn1024x1024Resolution() {
    GlobalNavigation globalNav = new HomePage().open().getGlobalNavigation();

    globalNav.openExploreWikiaDropdown();
    Assertion.assertEquals(globalNav.getDropdownLinks(), EXPECTED_LINKS_BIG_RESOLUTION);
  }

  @Test(groups = {"verifyDropdownLinksOn768x1024Resolution"})
  public void verifyDropdownLinksOn768x1024Resolution() {
    HomePage homePage = new HomePage();
    GlobalNavigation globalNav = new GlobalNavigation();
    homePage.openWikiPage(this.wikiURL);

    driver.manage().window().setSize(HUBS_IN_DROPDOWN_RESOLUTION);
    globalNav.openExploreWikiaDropdown();
    Assertion.assertEquals(globalNav.getDropdownLinks(), EXPECTED_LINKS_SMALL_RESOLUTION);
  }

  @Test(groups = {"gameStarLogoIsNotPresentOn768x1024Resolution"})
  public void gameStarLogoIsNotPresentOn768x1024Resolution() {
    HomePage homePage = new HomePage();
    homePage.openWikiPage(urlBuilder.getUrlForWiki(deWikiName));
    homePage.resizeWindow(HIDE_LOGO_RESOLUTION);

    Assertion.assertFalse((new GlobalNavigation()).isGameStarLogoDisplayed(),
        "GameStar Logo shouldn't be visible");
  }

  @InBrowser(browser = Browser.FIREFOX, browserSize = HUBS_OUTSIDE_DROPDOWN_RESOLUTION)
  @Test(groups = {"linksArePresentOn1024x1024Resolution"})
  public void linksArePresentOn1024x1024Resolution() {
    Assertion.assertTrue(new HomePage().open().getGlobalNavigation().areHubsLinksVisible());
  }

  @Test(groups = {"linksArePresentOn768x1024Resolution"})
  public void linksAreNotPresentOn768x1024Resolution() {
    HomePage homePage = new HomePage();
    GlobalNavigation globalNav = new GlobalNavigation();
    homePage.openWikiPage(this.wikiURL);

    driver.manage().window().setSize(HUBS_IN_DROPDOWN_RESOLUTION);
    Assertion.assertFalse(globalNav.areHubsLinksVisible());
  }

  @DataProvider
  public Object[][] getWikisWithDisabledLocalSearch() {
    return new Object[][] {{"de.wikia"}, {"wikia"}};
  }

  @Test(groups = {"localSearchIsDisabled"}, dataProvider = "getWikisWithDisabledLocalSearch")
  public void localSearchIsDisabled(String wikiName) {
    HomePage homePage = new HomePage();
    homePage.getUrl(urlBuilder.getUrlForWiki(wikiName));
    GlobalNavigation globalNav = homePage.getGlobalNavigation();

    Assertion.assertTrue(globalNav.isLocalSearchDisabled());
  }
}
