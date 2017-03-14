package com.wikia.webdriver.testcases.discussions;

import static com.wikia.webdriver.elements.mercury.components.discussions.common.DiscussionsConstants.DESKTOP_RESOLUTION;

import static com.wikia.webdriver.elements.mercury.components.discussions.common.TextGenerator.createUniqueCategoryName;
import static com.wikia.webdriver.common.core.Assertion.assertTrue;
import static com.wikia.webdriver.common.core.Assertion.assertFalse;
import static com.wikia.webdriver.common.core.Assertion.assertEquals;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.Discussions;
import com.wikia.webdriver.common.remote.operations.DiscussionsCategoryOperations;
import com.wikia.webdriver.common.remote.operations.DiscussionsOperations;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostsCreator;
import com.wikia.webdriver.elements.mercury.components.discussions.common.category.CategoriesFieldset;
import com.wikia.webdriver.elements.mercury.components.discussions.common.category.CategoryPill;
import com.wikia.webdriver.elements.mercury.components.discussions.mobile.FiltersPopOver;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;


@Execute(onWikia = MercuryWikis.DISCUSSIONS_1)
@Test(groups = "discussions-categories")
public class CategoriesTests extends NewTestTemplate {


  private String siteId;
  private static final int MAX_NUMBER_OF_CATEGORIES = 10;
  private static final String GENERAL_CATEGORY_NAME = "General";

  // assertion messages

  private static final String CATEGORY_SHOULD_BE_VISIBLE_MESSAGE =
    "Only \"%s\" category should be visible.";
  private static final String CATEGORIES_NOT_EDITABLE_MESSAGE =
    "Should not be able to edit categories.";
  private static final String SHOULD_EDIT_CATEGORIES_MESSAGE =
    "Should be able to edit categories.";
  private static final String GENERAL_CATEGORY_SHOULD_BE_NOT_EDITABLE_MESSAGE =
    "General category should not be editable";
  private static final String ALL_CATEGORY_SHOULD_NOT_BE_EDITABLE_MESSAGE =
    "\'All\' category should not be editable.";
  private static final String CATEGORY_SHOULD_BE_VISILBE_IN_LIST_MESSAGE =
    "Category %s should appear in categories list.";
  private static final String CATEGORY_SHOULD_BE_VISIBLE_IN_CREATOR_MESSAGE =
    "Category %s should be visible on post creator.";
  private static final String CATEGORIES_LIMIT_REACHED_INFO_MESSAGE =
    "You have reached the limit of allowed categories (10).";
  private static final String INFOR_MESSAGE_SHOULD_APPEAR_MESSAGE =
    "Info message should appear when reached max categories limit.";
  private static final String TEMPORARY_CATEGORY_SHOULD_NOT_BE_ADDED_MESSAGE =
    "Temporary category should not be added.";
  private static final String CATEGORY_SHOULD_BE_REMOVED_MESSAGE =
    "Category should be removed.";

  // test groups

  private static final String DESKTOP = "discussions-categories-desktop";
  private static final String MOBILE = "discussions-categories-mobile";

  // fixtures

  /**
   * Runs once before all tests in DESKTOP group, deletes all categories on DISCUSSIONS_1 wiki
   */
  @BeforeClass(groups = {DESKTOP})
  private void deleteCategoriesDesktop() {
    deleteCategories(MercuryWikis.DISCUSSIONS_1);
  }

  /**
   * Runs once before all tests in MOBILE group, deletes all categories on DISCUSSIONS_MOBILE wiki
   */
  @BeforeClass(groups = {MOBILE})
  private void deleteCategoriesMobile() {
    deleteCategories(MercuryWikis.DISCUSSIONS_MOBILE);
  }

  private void deleteCategories(String wikiName) {
    siteId = Discussions.excractSiteIdFromWikiName(wikiName);
    DiscussionsCategoryOperations.using(User.STAFF).deleteAllCategories(siteId, User.STAFF);
  }

  /**
   * Creates a unique post using DISCUSSIONS_ADMINISTRATOR account in new, unique category
   * @param wikiName wiki on which post is made
   * @return new category
   */
  private CategoryPill.Data setUp(String wikiName) {
    siteId = Discussions.excractSiteIdFromWikiName(wikiName);
    CategoryPill.Data category = addCategoryRemotely(siteId, createUniqueCategoryName());
    DiscussionsOperations
      .using(User.STAFF, driver)
      .createPostWithCategory(category.getId(), siteId);
    return category;
  }

  private CategoryPill.Data setUp() {
    return setUp(MercuryWikis.DISCUSSIONS_1);
  }

  /**
   *
   * @param wikiName to create categories on,
   * @param size number of posts with unique categories to create
   * @return list of categories
   */
  private ArrayList<CategoryPill.Data> setUp(final String wikiName, int size) {
    ArrayList<CategoryPill.Data> categories = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      categories.add(setUp(wikiName));
    }
    return categories;
  }

  private ArrayList<CategoryPill.Data> setUp(int size) {
    return setUp(MercuryWikis.DISCUSSIONS_1, size);
  }

  /**
   *
   * @param category category data to delete remotely
   */
  private void cleanUp(CategoryPill.Data category) {
    DiscussionsCategoryOperations.using(User.STAFF).deleteCategory(this.siteId, category);
  }

  /**
   *
   * @param categories list of categories to delete remotely
   */
  private void cleanUp(ArrayList<CategoryPill.Data> categories) {
    for (CategoryPill.Data category : categories) {
      cleanUp(category);
    }
  }


  // Anonymous user on mobile

  @Test(groups = {MOBILE})
  @Execute(asUser = User.ANONYMOUS, onWikia = MercuryWikis.DISCUSSIONS_MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonymousUserOnMobileCanChangeCategory() {
    changeCategoryMobile();
  }

  @Test(groups = {MOBILE})
  @Execute(asUser = User.ANONYMOUS, onWikia = MercuryWikis.DISCUSSIONS_MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonymousUserOnMobileCanNotEditCategory() {
    cannotEditCategoryMobile();
  }

  // Anonymous user on desktop

  @Test(groups = {DESKTOP})
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonymousUserOnDesktopCanChangeCategory() {
    canChangeCategoryDesktop();
  }

  @Test(groups = {DESKTOP})
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonymousUserOnDesktopCanNotEditCategory() {
    cannotEditCategoryDesktop();
  }

  // User on mobile

  @Test(groups = {MOBILE})
  @Execute(asUser = User.USER, onWikia = MercuryWikis.DISCUSSIONS_MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanChangeCategory() {
    changeCategoryMobile();
  }

  @Test(groups = {MOBILE})
  @Execute(asUser = User.USER, onWikia = MercuryWikis.DISCUSSIONS_MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanNotEditCategory() {
    cannotEditCategoryMobile();
  }

  // User on desktop

  @Test(groups = {DESKTOP})
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanChangeCategory() {
    canChangeCategoryDesktop();
  }

  @Test(groups = {DESKTOP})
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanNotEditCategory() {
    cannotEditCategoryDesktop();
  }

  // Discussions Administrator on mobile

  @Test(groups = {MOBILE})
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR, onWikia = MercuryWikis.DISCUSSIONS_MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void discussionsAdministratorOnMobileCanNotEditGeneralCategory() {
    CategoryPill.Data postCategory = setUp(MercuryWikis.DISCUSSIONS_MOBILE);
    final PostsListPage page = new PostsListPage().open(siteId);
    final CategoriesFieldset categoriesFieldset = page.getFiltersPopOver().click().getCategoriesFieldset();
    try {
      assertTrue(categoriesFieldset.canEdit(), SHOULD_EDIT_CATEGORIES_MESSAGE);
      assertFalse(categoriesFieldset.clickEdit().canEditGeneralCategory(),
        GENERAL_CATEGORY_SHOULD_BE_NOT_EDITABLE_MESSAGE);
    } finally {
      cleanUp(postCategory);
    }
  }

  @Test(groups = {MOBILE})
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR, onWikia = MercuryWikis.DISCUSSIONS_MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void discussionsAdministratorOnMobileCanAddCategory() {
    final PostsListPage page = new PostsListPage().open(siteId);
    final String categoryName = createUniqueCategoryName();
    final CategoriesFieldset categoriesFieldset = addCategory(
        page.getFiltersPopOver().click().getCategoriesFieldset(),
        categoryName);
    page.waitForPageReload();
    CategoryPill.Data newCategory = categoriesFieldset.findCategoryOrElseThrow(categoryName);
    try {
      assertTrue(categoriesFieldset.hasCategory(categoryName), 
        String.format(CATEGORY_SHOULD_BE_VISILBE_IN_LIST_MESSAGE, categoryName));
      assertTrue(isCategoryIn(page.getPostsCreatorMobile(), categoryName), 
        String.format(CATEGORY_SHOULD_BE_VISIBLE_IN_CREATOR_MESSAGE, categoryName));
    } finally {
      cleanUp(newCategory);
    }
  }

  @Test(groups = {MOBILE})
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR, onWikia = MercuryWikis.DISCUSSIONS_MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void discussionsAdministratorOnMobileCanEditCategory() {
    CategoryPill.Data editableCategory = setUp(MercuryWikis.DISCUSSIONS_MOBILE);
    final PostsListPage page = new PostsListPage().open(siteId);
    final String editedName = createUniqueCategoryName();
    CategoriesFieldset categoriesFieldset = page.getFiltersPopOver().click()
        .getCategoriesFieldset().clickEdit()
        .renameMobile(editableCategory.getName(), editedName)
        .clickApproveButton();
    page.waitForPageReload();
    CategoryPill.Data editedCategory = categoriesFieldset.findCategoryOrElseThrow(editedName);
    try {
      assertTrue(categoriesFieldset.hasCategory(editedName),
        String.format(CATEGORY_SHOULD_BE_VISILBE_IN_LIST_MESSAGE, editedName));
      assertTrue(isCategoryIn(page.getPostsCreatorMobile(), editedName),
        String.format(CATEGORY_SHOULD_BE_VISIBLE_IN_CREATOR_MESSAGE, editedName));
    } finally {
      cleanUp(editedCategory);
    }
  }

  @Test(groups = {MOBILE})
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR, onWikia = MercuryWikis.DISCUSSIONS_MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void discussionsAdministratorOnMobileCanNotAddMoreThanTenCategories() {
    deleteCategoriesMobile();
    ArrayList<CategoryPill.Data> categoriesAdded = setUp(MercuryWikis.DISCUSSIONS_MOBILE,
      MAX_NUMBER_OF_CATEGORIES - 1);
    final PostsListPage page = new PostsListPage().open(siteId);
    final String newCategoryName = createUniqueCategoryName();
    CategoriesFieldset categoriesFieldset = addCategory(
      page.getFiltersPopOver().click().getCategoriesFieldset(),
      newCategoryName);
    try {
      assertEquals(categoriesFieldset.getInfoMessageText(), 
        CATEGORIES_LIMIT_REACHED_INFO_MESSAGE, INFOR_MESSAGE_SHOULD_APPEAR_MESSAGE);
    } finally {
      cleanUp(categoriesAdded);
    }
  }

  @Test(groups = {MOBILE})
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR, onWikia = MercuryWikis.DISCUSSIONS_MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void discussionsAdministratorOnMobileCanRemoveCategory() {
    CategoryPill.Data data = setUp(MercuryWikis.DISCUSSIONS_MOBILE);
    final String temporaryCategoryName = createUniqueCategoryName();
    final PostsListPage page = new PostsListPage().open(siteId);
    final CategoriesFieldset categoriesFieldset = page
      .getFiltersPopOver()
      .click()
      .getCategoriesFieldset();
    canRemoveCategories(page, temporaryCategoryName, categoriesFieldset, data);
  }

  // Discussions Administrator on desktop

  @Test(groups = {DESKTOP})
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void discussionsAdministratorOnDesktopCanNotEditAllAndGeneralCategories() {
    final PostsListPage page = new PostsListPage().open(siteId);
    final CategoriesFieldset categoriesFieldset = page.getCategories();
    categoriesFieldset.clickEdit();
    assertFalse(categoriesFieldset.canEditAllCategory(), 
      ALL_CATEGORY_SHOULD_NOT_BE_EDITABLE_MESSAGE);
    assertFalse(categoriesFieldset.canEditGeneralCategory(), 
      GENERAL_CATEGORY_SHOULD_BE_NOT_EDITABLE_MESSAGE);
  }

  @Test(groups = {DESKTOP})
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void discussionsAdministratorOnDesktopCanAddCategory() {
    final PostsListPage page = new PostsListPage().open(siteId);
    final String categoryName = createUniqueCategoryName();
    final CategoriesFieldset categoriesFieldset = addCategory(page.getCategories(), categoryName);
    page.waitForPageReload();
    final CategoryPill.Data data = categoriesFieldset.findCategoryOrElseThrow(categoryName);
    try {
      assertTrue(categoriesFieldset.hasCategory(categoryName),
        String.format(CATEGORY_SHOULD_BE_VISILBE_IN_LIST_MESSAGE, categoryName));
      assertTrue(isCategoryIn(page.getPostsCreatorDesktop(), categoryName),
        String.format(CATEGORY_SHOULD_BE_VISIBLE_IN_CREATOR_MESSAGE, categoryName));
    } finally {
      removeCategoryRemotely(siteId, data);
    }
  }

  @Test(groups = {DESKTOP})
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void discussionsAdministratorOnDesktopCanEditCategory() {
    CategoryPill.Data editableCategory = setUp();
    final PostsListPage page = new PostsListPage().open(siteId);
    final String newCategoryName = createUniqueCategoryName();
    CategoriesFieldset categoriesFieldset = page
      .getCategories()
      .renameDesktop(editableCategory.getName(), newCategoryName)
      .clickApproveButton();
    page.waitForPageReload();
    CategoryPill.Data editedCategory = categoriesFieldset.findCategoryOrElseThrow(newCategoryName);
    try {
      assertTrue(categoriesFieldset.hasCategory(newCategoryName),
        String.format(CATEGORY_SHOULD_BE_VISILBE_IN_LIST_MESSAGE, newCategoryName));
      assertTrue(isCategoryIn(page.getPostsCreatorDesktop(), newCategoryName),
        String.format(CATEGORY_SHOULD_BE_VISIBLE_IN_CREATOR_MESSAGE, newCategoryName));
    } finally {
      cleanUp(editedCategory);
    }
  }

  @Test(groups = {DESKTOP})
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void discussionsAdministratorOnDesktopCanNotAddMoreThanTenCategories() {
    deleteCategoriesDesktop();
    ArrayList<CategoryPill.Data> categoriesAdded = setUp(MAX_NUMBER_OF_CATEGORIES - 1);
    final PostsListPage page = new PostsListPage().open(siteId);
    final String newCategoryName = createUniqueCategoryName();
    CategoriesFieldset categoriesFieldset = addCategory(page.getCategories(), newCategoryName);
    try {
      assertEquals(categoriesFieldset.getInfoMessageText(), CATEGORIES_LIMIT_REACHED_INFO_MESSAGE,
        INFOR_MESSAGE_SHOULD_APPEAR_MESSAGE);
    } finally {
      cleanUp(categoriesAdded);
    }
  }

  @Test(groups = {DESKTOP})
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void discussionsAdministratorOnDesktopCanRemoveCategory() {
    CategoryPill.Data data = setUp();
    final String temporaryCategoryName = createUniqueCategoryName();
    final PostsListPage page = new PostsListPage().open(siteId);
    final CategoriesFieldset categoriesFieldset = page.getCategories();
    canRemoveCategories(page, temporaryCategoryName, categoriesFieldset, data);
  }

  // test methods body

  private void canChangeCategoryDesktop() {
    CategoryPill.Data postCategory = setUp();
    final PostsListPage page = new PostsListPage().open(siteId);
    openPageAndSelectCategoryOnDesktop(page, postCategory.getName());
    assertCategoryVisibleAndCleanUp(page, postCategory);
  }

  private void changeCategoryMobile() {
    CategoryPill.Data postCategory = setUp(MercuryWikis.DISCUSSIONS_MOBILE);
    final PostsListPage page = new PostsListPage().open(siteId);
    openPageAndSelectCategoryOnMobile(page, postCategory.getName());
    assertCategoryVisibleAndCleanUp(page, postCategory);
  }

  private void assertCategoryVisibleAndCleanUp(PostsListPage page, CategoryPill.Data postCategory) {
    final boolean isCategoryVisible = postsOnPageAreOnlyFromOneCategory(page,
      postCategory.getName());
    try {
      assertTrue(isCategoryVisible, String.format(CATEGORY_SHOULD_BE_VISIBLE_MESSAGE,
        postCategory.getName()));
    } finally {
      cleanUp(postCategory);
    }
  }

  private void cannotEditCategoryDesktop() {
    CategoryPill.Data postCategory = setUp();
    final PostsListPage page = new PostsListPage().open(siteId);
    try {
      assertFalse(canEditCategoriesOnDesktop(page), CATEGORIES_NOT_EDITABLE_MESSAGE);
    } finally {
      cleanUp(postCategory);
    }
  }

  private void cannotEditCategoryMobile() {
    CategoryPill.Data postCategory = setUp(MercuryWikis.DISCUSSIONS_MOBILE);
    final PostsListPage page = new PostsListPage().open(siteId);
    try {
      assertFalse(canEditCategoriesOnMobile(page), CATEGORIES_NOT_EDITABLE_MESSAGE);
    } finally {
      cleanUp(postCategory);
    }
  }

  private void canRemoveCategories(PostsListPage page, String temporaryCategoryName,
    CategoriesFieldset categoriesFieldset, CategoryPill.Data data) {
    addAndRemoveTemporaryCategory(page, temporaryCategoryName, categoriesFieldset);
    assertFalse(categoriesFieldset.findCategoryWith(temporaryCategoryName).isPresent(),
      TEMPORARY_CATEGORY_SHOULD_NOT_BE_ADDED_MESSAGE);
    removeCategory(data, page, categoriesFieldset);
    assertFalse(categoriesFieldset.findCategoryWith(data.getName()).isPresent(),
      CATEGORY_SHOULD_BE_REMOVED_MESSAGE);
  }

  // helpers

  private void openPageAndSelectCategoryOnMobile(PostsListPage page, String categoryName) {
    final FiltersPopOver filtersPopOver = page.getFiltersPopOver().click();
    filtersPopOver.getCategoriesFieldset().clickCategoryWith(categoryName);
    filtersPopOver.clickApplyButton();
    page.waitForPageReloadWith(categoryName);
  }

  private void openPageAndSelectCategoryOnDesktop(PostsListPage page, String categoryName) {
    page.getCategories().clickCategoryWith(categoryName);
    page.waitForPageReloadWith(categoryName);
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
    return page.getCategories().canEdit();
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
    DiscussionsCategoryOperations.using(User.STAFF).deleteCategory(siteId, data);
  }

  private CategoryPill.Data addCategoryRemotely(String siteId, String categoryName) {
    return DiscussionsCategoryOperations
      .using(User.STAFF)
      .createCategory(categoryName, siteId);
  }

  /**
   * Removes category `data` on page, moves existing posts within that category to GENERAL_CATEGORY_NAME
   * @param data category to be removed
   * @param page page on which category removal is executed
   * @param categoriesFieldset categories where category to be removed exists
   */
  private void removeCategory(CategoryPill.Data data, PostsListPage page, CategoriesFieldset categoriesFieldset) {
    categoriesFieldset.clickEdit()
        .removeCategory(data.getName())
        .clickPill(GENERAL_CATEGORY_NAME)
        .clickConfirmButton()
        .clickApproveButton();
    page.waitForPageReload();
  }

  private void addAndRemoveTemporaryCategory(PostsListPage page, String temporaryCategoryName, CategoriesFieldset categoriesFieldset) {
    categoriesFieldset.clickEdit()
        .addCategory(temporaryCategoryName)
        .removeTemporaryCategory(temporaryCategoryName)
        .clickApproveButton();
    page.waitForPageReload();
  }
}
