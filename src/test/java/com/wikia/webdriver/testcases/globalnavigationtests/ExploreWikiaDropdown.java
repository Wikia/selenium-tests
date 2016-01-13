package com.wikia.webdriver.testcases.globalnavigationtests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.globalnav.GlobalNavigationPageObject;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

@Test(groups = {"TestExploreWikiaDropdown", "GlobalNav"})
public class ExploreWikiaDropdown extends NewTestTemplate {

  private static final Dimension HUBS_IN_DROPDOWN_RESOLUTION = new Dimension(768, 1024);
  private static final Dimension HUBS_OUTSIDE_DROPDOWN_RESOLUTION = new Dimension(1024, 1024);
  private static final List<String> EXPECTED_LINKS_BIG_RESOLUTION =
          Arrays.asList("Top Communities", "Community Central", "START A WIKIA");
  private static final List<String> EXPECTED_LINKS_SMALL_RESOLUTION =
          Arrays.asList("Games", "Movies", "TV", "Top Communities", "Community Central", "START A WIKIA");

  @Test(groups = {"TestExploreWikiaDropdown_001"})
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
}
