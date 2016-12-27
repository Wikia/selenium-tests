package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.Discussions;
import com.wikia.webdriver.common.remote.context.CategoryContext;
import com.wikia.webdriver.common.remote.operations.DiscussionsCategoryOperations;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.DiscussionsConstants;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostsCreator;
import com.wikia.webdriver.elements.mercury.components.discussions.common.TextGenerator;
import com.wikia.webdriver.elements.mercury.components.discussions.common.category.CategoriesFieldset;
import com.wikia.webdriver.elements.mercury.components.discussions.common.category.CategoryPill;
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

  // Category name: Category 3
  private static final int CATEGORY_POSITION = 4;

  // Category name: Editable Category
  private static final int EDITABLE_CATEGORY_POSITION = 5;

  private static final int MAX_NUMBER_OF_CATEGORIES = 10;

  private static final String EDITABLE_CATEGORY_ORIGINAL_NAME = "Editable Category";

  private static final String CATEGORY_SHOULD_BE_VISIBLE_MESSAGE = "Only \"%s\" category should be visible.";

  private static final String CATEGORIES_NOT_EDITABLE_MESSAGE = "Should not be able to edit categories.";

  private static final String SHOULD_EDIT_CATEGORIES_MESSAGE = "Should be able to edit categories.";

  private static final String GENERAL_CATEGORY_SHOULD_BE_NOT_EDITABLE_MESSAGE = "General category should be not editable";

  private static final String CATEGORY_SHOULD_BE_VISILBE_IN_LIST_MESSAGE = "Category %s should appear in categories list.";

  private static final String CATEGORY_SHOULD_BE_VISIBLE_IN_CREATOR_MESSAGE = "Category %s should be visible on post creator.";

  // Anonymous user on mobile

  @Test(groups = "discussions-anonUserOnMobileCategories")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonymousUserOnMobileCanChangeCategoryOnPostsListPage() {
    final PostsListPage page = new PostsListPage().open();
    final String categoryName = openPageAndSelectCategoryOnMobile(page);

    final boolean actual = postsOnPageAreOnlyFromOneCategory(page, categoryName);

    Assertion.assertTrue(actual, String.format(CATEGORY_SHOULD_BE_VISIBLE_MESSAGE, categoryName));
  }

  @Test(groups = "discussions-anonUserOnMobileCategories")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonymousUserOnMobileCanNotEditCategoryOnPostsListPage() {
    final PostsListPage page = new PostsListPage().open();
    final boolean actual = canEditCategoriesOnMobile(page);

    Assertion.assertFalse(actual, CATEGORIES_NOT_EDITABLE_MESSAGE);
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

  @Test(groups = "discussions-anonUserOnDesktopCategories")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DiscussionsConstants.DESKTOP_RESOLUTION)
  public void anonymousUserOnDesktopCanNotEditCategoryOnPostsListPage() {
    final PostsListPage page = new PostsListPage().open();
    final boolean actual = canEditCategoriesOnDesktop(page);

    Assertion.assertFalse(actual, CATEGORIES_NOT_EDITABLE_MESSAGE);
  }

  // User on mobile

  @Test(groups = "discussions-userOnMobileCategories")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanChangeCategoryOnPostsListPage() {
    final PostsListPage page = new PostsListPage().open();
    final String categoryName = openPageAndSelectCategoryOnMobile(page);

    final boolean actual = postsOnPageAreOnlyFromOneCategory(page, categoryName);

    Assertion.assertTrue(actual, String.format(CATEGORY_SHOULD_BE_VISIBLE_MESSAGE, categoryName));
  }

  @Test(groups = "discussions-userOnMobileCategories")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanNotEditCategoryOnPostsListPage() {
    final PostsListPage page = new PostsListPage().open();
    final boolean actual = canEditCategoriesOnMobile(page);

    Assertion.assertFalse(actual, CATEGORIES_NOT_EDITABLE_MESSAGE);
  }

  // User on desktop

  @Test(groups = "discussions-userOnDesktopCategories")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DiscussionsConstants.DESKTOP_RESOLUTION)
  public void userOnDesktopCanChangeCategory() {
    final PostsListPage page = new PostsListPage().open();
    final String categoryName = openPageAndSelectCategoryOnDesktop(page);

    final boolean actual = postsOnPageAreOnlyFromOneCategory(page, categoryName);

    Assertion.assertTrue(actual, String.format(CATEGORY_SHOULD_BE_VISIBLE_MESSAGE, categoryName));
  }

  @Test(groups = "discussions-userOnDesktopCategories")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DiscussionsConstants.DESKTOP_RESOLUTION)
  public void userOnDesktopCanNotEditCategoryOnPostsListPage() {
    final PostsListPage page = new PostsListPage().open();
    final boolean actual = canEditCategoriesOnDesktop(page);

    Assertion.assertFalse(actual, CATEGORIES_NOT_EDITABLE_MESSAGE);
  }

  // Discussions Administrator on mobile

  /**
   * Category "All" is not present on edit categories modal on mobile
   */
  @Test(groups = "discussions-discussionsAdministratorOnMobileCategories")
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void discussionsAdministratorOnMobileCanNotEditGeneralCategoryOnPostsListPage() {
    final PostsListPage page = new PostsListPage().open();

    CategoriesFieldset categoriesFieldset = page.getFiltersPopOver().click()
        .getCategoriesFieldset();

    Assertion.assertTrue(categoriesFieldset.canEdit(), SHOULD_EDIT_CATEGORIES_MESSAGE);
    Assertion.assertFalse(categoriesFieldset.clickEdit().canEditGeneralCategory(), GENERAL_CATEGORY_SHOULD_BE_NOT_EDITABLE_MESSAGE);
  }

  @Test(groups = "discussions-discussionsAdministratorOnMobileCategories")
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void discussionsAdministratorOnMobileCanAddCategoryOnPostsListPage() {
    final String siteId = Discussions.extractSiteIdFromMediaWikiUsing(driver);

    final PostsListPage page = new PostsListPage().open();
    final String categoryName = TextGenerator.createUniqueCategoryName();

    final CategoriesFieldset categoriesFieldset = addCategory(
        page.getFiltersPopOver().click().getCategoriesFieldset(),
        categoryName);

    page.waitForPageReload();

    CategoryPill.Data data = categoriesFieldset.findCategoryWith(categoryName).toData();

    Assertion.assertTrue(categoriesFieldset.hasCategory(categoryName), String.format(CATEGORY_SHOULD_BE_VISILBE_IN_LIST_MESSAGE, categoryName));
    Assertion.assertTrue(isCategoryIn(page.getPostsCreatorMobile(), categoryName), String.format(CATEGORY_SHOULD_BE_VISIBLE_IN_CREATOR_MESSAGE, categoryName));

    removeCategoryRemotely(siteId, data);
  }

  @Test(groups = "discussions-discussionsAdministratorOnMobileCategories")
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void discussionsAdministratorOnMobileCanEditCategoryOnPostsListPage() {
    final String siteId = Discussions.extractSiteIdFromMediaWikiUsing(driver);

    final PostsListPage page = new PostsListPage().open();
    final String categoryName = TextGenerator.createUniqueCategoryName();

    CategoriesFieldset categoriesFieldset = page.getFiltersPopOver().click()
        .getCategoriesFieldset().clickEdit()
        .rename(EDITABLE_CATEGORY_POSITION, categoryName)
        .clickApproveButton();

    page.waitForPageReload();

    CategoryPill.Data data = categoriesFieldset.findCategoryWith(categoryName).toData();

    Assertion.assertTrue(categoriesFieldset.hasCategory(categoryName), String.format(CATEGORY_SHOULD_BE_VISILBE_IN_LIST_MESSAGE, categoryName));
    Assertion.assertTrue(isCategoryIn(page.getPostsCreatorMobile(), categoryName), String.format(CATEGORY_SHOULD_BE_VISIBLE_IN_CREATOR_MESSAGE, categoryName));

    revertCategoryName(siteId, data);
  }

  @Test(groups = "discussions-discussionsAdministratorOnMobileCategories")
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void discussionsAdministratorOnMobileCanNotAddMoreThanTenCategoriesOnPostsListPage() {
    final PostsListPage page = new PostsListPage().open();

    CategoriesFieldset categoriesFieldset = page.getFiltersPopOver().click()
        .getCategoriesFieldset().clickEdit();

    int counter = 0;
    while (categoriesFieldset.canAddCategory() && counter < MAX_NUMBER_OF_CATEGORIES) {
      categoriesFieldset.addCategory(TextGenerator.createUniqueCategoryName());
      counter++;
    }

    if (counter == MAX_NUMBER_OF_CATEGORIES) {
      Assertion.fail("Discussions Administrator should not be able to add more than nine categories. ( + 1 \"General\")");
    }

    Assertion.assertEquals(
        categoriesFieldset.getInfoMessageText(),
        "You have reached the limit of allowed categories (10).",
        "Info message should appear when reached max categories limit.");
  }


  // Discussions Administrator on desktop

  @Test(groups = "discussions-discussionsAdministratorOnDesktopCategories")
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DiscussionsConstants.DESKTOP_RESOLUTION)
  public void discussionsAdministratorOnDesktopCanNotEditGeneralCategoryOnPostsListPage() {
    final PostsListPage page = new PostsListPage().open();

    CategoriesFieldset categoriesFieldset = page.getModeration()
        .getCategoriesFieldset();

    Assertion.assertTrue(categoriesFieldset.canEdit(), SHOULD_EDIT_CATEGORIES_MESSAGE);

    categoriesFieldset.clickEdit();

    Assertion.assertFalse(categoriesFieldset.canEditAllCategory(), "All category should be not editable");
    Assertion.assertFalse(categoriesFieldset.canEditGeneralCategory(), GENERAL_CATEGORY_SHOULD_BE_NOT_EDITABLE_MESSAGE);
  }

  @Test(groups = "discussions-discussionsAdministratorOnDesktopCategories")
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DiscussionsConstants.DESKTOP_RESOLUTION)
  public void discussionsAdministratorOnDesktopCanAddCategoryOnPostsListPage() {
    final String siteId = Discussions.extractSiteIdFromMediaWikiUsing(driver);

    final PostsListPage page = new PostsListPage().open();
    final String categoryName = TextGenerator.createUniqueCategoryName();

    final CategoriesFieldset categoriesFieldset = addCategory(
        page.getModeration().getCategoriesFieldset(),
        categoryName);

    page.waitForPageReload();

    final CategoryPill.Data data = categoriesFieldset.findCategoryWith(categoryName).toData();

    Assertion.assertTrue(categoriesFieldset.hasCategory(categoryName), String.format(CATEGORY_SHOULD_BE_VISILBE_IN_LIST_MESSAGE, categoryName));
    Assertion.assertTrue(isCategoryIn(page.getPostsCreatorDesktop(), categoryName), String.format(CATEGORY_SHOULD_BE_VISIBLE_IN_CREATOR_MESSAGE, categoryName));

    removeCategoryRemotely(siteId, data);
  }


  @Test(groups = "discussions-discussionsAdministratorOnDesktopCategories")
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DiscussionsConstants.DESKTOP_RESOLUTION)
  public void discussionsAdministratorOnDesktopCanEditCategoryOnPostsListPage() {
    final String siteId = Discussions.extractSiteIdFromMediaWikiUsing(driver);

    final PostsListPage page = new PostsListPage().open();
    final String categoryName = TextGenerator.createUniqueCategoryName();

    CategoriesFieldset categoriesFieldset = page.getModeration()
        .getCategoriesFieldset().clickEdit()
        .rename(EDITABLE_CATEGORY_POSITION, categoryName)
        .clickApproveButton();

    page.waitForPageReload();

    CategoryPill.Data data = categoriesFieldset.findCategoryWith(categoryName).toData();

    try {
      Assertion.assertTrue(categoriesFieldset.hasCategory(categoryName), String.format(CATEGORY_SHOULD_BE_VISILBE_IN_LIST_MESSAGE, categoryName));
      Assertion.assertTrue(isCategoryIn(page.getPostsCreatorDesktop(), categoryName), String.format(CATEGORY_SHOULD_BE_VISIBLE_IN_CREATOR_MESSAGE, categoryName));
    } finally {
      revertCategoryName(siteId, data);
    }
  }

  private String openPageAndSelectCategoryOnMobile(PostsListPage page) {
    final FiltersPopOver filtersPopOver = page.getFiltersPopOver().click();

    final String categoryName = filtersPopOver.getCategoriesFieldset()
        .clickCategoryAt(CATEGORY_POSITION)
        .getName();

    filtersPopOver.clickApplyButton();

    page.waitForPageReloadWith(categoryName);
    return categoryName;
  }

  private String openPageAndSelectCategoryOnDesktop(PostsListPage page) {
    final Moderation moderation = page.getModeration();

    final String categoryName = moderation.getCategoriesFieldset()
        .clickCategoryAt(CATEGORY_POSITION)
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

  private boolean canEditCategoriesOnMobile(PostsListPage page) {
    return page.getFiltersPopOver().click()
        .getCategoriesFieldset()
        .canEdit();
  }

  private boolean canEditCategoriesOnDesktop(PostsListPage page) {
    return page.getModeration()
        .getCategoriesFieldset()
        .canEdit();
  }

  private CategoriesFieldset addCategory(CategoriesFieldset categoriesFieldset, String categoryName) {
    return categoriesFieldset.clickEdit()
        .addCategory(categoryName)
        .clickApproveButton();
  }

  private boolean isCategoryIn(PostsCreator postsCreator, String categoryName) {
    return postsCreator.click()
        .closeGuidelinesMessage()
        .clickAddCategoryButton()
        .hasCategory(categoryName);
  }

  private void removeCategoryRemotely(String siteId, CategoryPill.Data data) {
    DiscussionsCategoryOperations.using(User.DISCUSSIONS_ADMINISTRATOR, driver).deleteCategory(siteId, data);
  }

  private void revertCategoryName(String siteId, CategoryPill.Data data) {
    final CategoryContext context = CategoryContext.builder()
        .siteId(siteId)
        .categoryId(data.getId())
        .categoryName(EDITABLE_CATEGORY_ORIGINAL_NAME)
        .build();
    DiscussionsCategoryOperations.using(User.DISCUSSIONS_ADMINISTRATOR, driver).renameCategory(context);
  }
}
