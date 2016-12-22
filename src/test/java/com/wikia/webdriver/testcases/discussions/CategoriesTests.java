package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.DiscussionsConstants;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.Moderation;
import com.wikia.webdriver.elements.mercury.components.discussions.mobile.FiltersPopOver;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import org.testng.annotations.Test;

/**
 * To avoid creating unnecessary data, some of these tests assume that on www.dauto.wikia.com exists category on 4th
 * position with posts.
 */
@Execute(onWikia = MercuryWikis.DISCUSSIONS_AUTO)
@Test(groups = "discussions-categories")
public class CategoriesTests extends NewTestTemplate {

  private static final int CATEGORY_POSITION = 4;

  private static final String CATEGORY_SHOULD_BE_VISIBLE_MESSAGE = "Only \"%s\" category should be visible.";

  // Anonymous user on mobile

  @Test(groups = "discussions-anonUserOnMobileCategories")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonymousUserOnMobileCanChangeCategory() {
    final PostsListPage page = new PostsListPage().open();
    final String categoryName = openPageAndSelectCategoryOnMobile(page);

    final boolean actual = postsOnPageAreOnlyFromOneCategory(page, categoryName);

    Assertion.assertTrue(actual, String.format(CATEGORY_SHOULD_BE_VISIBLE_MESSAGE, categoryName));
  }

  // Anonymous user on desktop

  @Test(groups = "discussions-anonUserOnDesktopCategories")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DiscussionsConstants.DESKTOP_RESOLUTION)
  public void anonymousUserOnDesktopCanChangeCategory() {
    final PostsListPage page = new PostsListPage().open();
    final String categoryName = openPageAndSelectCategoryOnDesktop(page);

    final boolean actual = postsOnPageAreOnlyFromOneCategory(page, categoryName);

    Assertion.assertTrue(actual, String.format(CATEGORY_SHOULD_BE_VISIBLE_MESSAGE, categoryName));
  }

  // User on mobile

  @Test(groups = "discussions-userOnMobileCategories")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanChangeCategory() {
    final PostsListPage page = new PostsListPage().open();
    final String categoryName = openPageAndSelectCategoryOnMobile(page);

    final boolean actual = postsOnPageAreOnlyFromOneCategory(page, categoryName);

    Assertion.assertTrue(actual, String.format(CATEGORY_SHOULD_BE_VISIBLE_MESSAGE, categoryName));
  }

  // User on mobile

  @Test(groups = "discussions-userOnDesktopCategories")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DiscussionsConstants.DESKTOP_RESOLUTION)
  public void userOnDesktopCanChangeCategory() {
    final PostsListPage page = new PostsListPage().open();
    final String categoryName = openPageAndSelectCategoryOnDesktop(page);

    final boolean actual = postsOnPageAreOnlyFromOneCategory(page, categoryName);

    Assertion.assertTrue(actual, String.format(CATEGORY_SHOULD_BE_VISIBLE_MESSAGE, categoryName));
  }

  private String openPageAndSelectCategoryOnMobile(PostsListPage page) {
    final FiltersPopOver filtersPopOver = page.getFiltersPopOver().click();

    final String categoryName = filtersPopOver.getCategoriesFieldset()
        .clickCategory(CATEGORY_POSITION)
        .getName();

    filtersPopOver.clickApplyButton();

    page.waitForPageReloadWith(categoryName);
    return categoryName;
  }

  private String openPageAndSelectCategoryOnDesktop(PostsListPage page) {
    final Moderation moderation = page.getModeration();

    final String categoryName = moderation.getCategoriesFieldset()
        .clickCategory(CATEGORY_POSITION)
        .getName();

    page.waitForPageReloadWith(categoryName);
    return categoryName;
  }

  private boolean postsOnPageAreOnlyFromOneCategory(PostsListPage page, String categoryName) {
    return page.getPost().getPosts().stream()
        .map(PostEntity::findCategory)
        .distinct()
        .allMatch(category -> category.endsWith(categoryName));
  }
}
