package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.Loading;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.SortingFiltersOnDesktop;
import com.wikia.webdriver.elements.mercury.components.discussions.mobile.DiscussionsHeader;
import com.wikia.webdriver.elements.mercury.components.discussions.mobile.FiltersPopOver;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;

import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.DISCUSSIONS_3)
@Test(groups = {"discussions-sorting"})
public class SortingTests extends NewTestTemplate {

  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanSortPostsList() {
    userCanSwitchBetweenLatestAndTrendingInDropdown();
  }

  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonUserOnDesktopCanSortPostList() {
    userCanSwitchBetweenLatestAndTrendingTab();
  }

  @Execute(asUser = User.USER_3)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void loggedInUserOnMobileCanSortPostsList() {
    userCanSwitchBetweenLatestAndTrendingInDropdown();
  }

  @Execute(asUser = User.USER_3)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void loggedUserOnDesktopCanSwitchBetweenLatestAndTrendingTab() {
    userCanSwitchBetweenLatestAndTrendingTab();
  }

  private void userCanSwitchBetweenLatestAndTrendingInDropdown() {
    PostsListPage page = new PostsListPage().open();
    FiltersPopOver filtersPopOver = page.getFiltersPopOver();
    DiscussionsHeader discussionsHeader = page.getDiscussionsHeader();
    discussionsHeader.clickSortButtonOnMobile();

    Assertion.assertTrue(filtersPopOver.isSortListVisibleMobile());

    filtersPopOver.clickLatestLinkOnMobile();
    filtersPopOver.clickApplyButton();
    new Loading(driver).handleAsyncPageReload();
    discussionsHeader.clickSortButtonOnMobile();
    filtersPopOver.clickTrendingOptionInSortMenu();
    filtersPopOver.clickApplyButton();
    new Loading(driver).handleAsyncPageReload();
  }

  private void userCanSwitchBetweenLatestAndTrendingTab() {
    SortingFiltersOnDesktop filters = new PostsListPage().open().getSortingFiltersOnDesktop();
    filters.clickLatestOption();
    new Loading(driver).handleAsyncPageReload();

    Assertion.assertTrue(driver.getCurrentUrl().contains("latest"));

    filters.clickTrendingOption();
    new Loading(driver).handleAsyncPageReload();

    Assertion.assertTrue(driver.getCurrentUrl().contains("trending"));
  }
}
