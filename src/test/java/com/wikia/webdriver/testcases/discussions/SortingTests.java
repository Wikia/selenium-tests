package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.SortOption;
import com.wikia.webdriver.elements.mercury.pages.discussions.FollowPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.PageWithPosts;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;

import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.DISCUSSIONS_3)
@Test(groups = {"discussions-sorting"})
public class SortingTests extends NewTestTemplate {

  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanSortPostsList() {
    userCanSwitchBetweenLatestAndTrendingInDropdown(new PostsListPage().open());
  }

  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonUserOnDesktopCanSortPostList() {
    userCanSwitchBetweenLatestAndTrendingOptions(new PostsListPage().open());
  }

  @Execute(asUser = User.USER_3)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanSortPostsList() {
    userCanSwitchBetweenLatestAndTrendingInDropdown(new PostsListPage().open());
  }

  @Execute(asUser = User.USER_3)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopCanSwitchBetweenLatestAndTrendingSortOptions() {
    userCanSwitchBetweenLatestAndTrendingOptions(new PostsListPage().open());
  }

  @Execute(asUser = User.USER_4)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopCannotChangeSortingOnFollowingTab() {
    Assertion.assertFalse(new FollowPage()
      .open()
      .getSortingFilterDesktop()
      .isEnabled());
  }

  @Test(enabled = false)
  @RelatedIssue(issueID = "IRIS-4995")
  @Execute(asUser = User.USER_3)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCannotChangeSortingOnFollowingTab() {
    Assertion.assertFalse(new FollowPage()
      .open()
      .getDiscussionsHeader()
      .openFilterMenu()
      .isSortingFilterEnabled());
  }

  private void userCanSwitchBetweenLatestAndTrendingInDropdown(PageWithPosts page) {
    performSortingCheckOnMobile(page, SortOption.LATEST);
    performSortingCheckOnMobile(page, SortOption.TRENDING);
  }

  private void userCanSwitchBetweenLatestAndTrendingOptions(PageWithPosts page) {
    performSortingCheckOnDesktop(page, SortOption.LATEST);
    performSortingCheckOnDesktop(page, SortOption.TRENDING);
  }

  private void performSortingCheckOnMobile(PageWithPosts page, SortOption sortOption) {
    page
      .getDiscussionsHeader()
      .openFilterMenu()
      .chooseSortingOption(sortOption)
      .clickApplyButton()
      .waitForPageReload();
    Assertion.assertTrue(page.getCurrentUrl().contains(sortOption.getQuery()));
  }

  private void performSortingCheckOnDesktop(PageWithPosts page, SortOption sortOption) {
    page
      .getSortingFilterDesktop()
      .chooseSortingOption(sortOption)
      .waitForPageReload();
    Assertion.assertTrue(page.getCurrentUrl().contains(sortOption.getQuery()));
  }

}
