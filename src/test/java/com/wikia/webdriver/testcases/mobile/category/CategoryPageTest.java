package com.wikia.webdriver.testcases.mobile.category;

import com.wikia.webdriver.common.contentpatterns.MobileWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.TestContext;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.communities.mobile.pages.ArticlePage;
import com.wikia.webdriver.elements.communities.mobile.pages.CategoryPage;

import org.testng.annotations.Test;

@Test(groups = "Mercury_Category")
@Execute(onWikia = MobileWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class CategoryPageTest extends NewTestTemplate {

  @Test(groups = "mercury_category_navigateToCategoryPageWithoutArticleAndWithMembersFromUrl")
  public void mercury_category_navigateToCategoryPageWithoutArticleAndWithMembersFromUrl() {
    final String categoryName = String.format("Category:%s", TestContext.getCurrentMethodName());
    ArticleContent articleContent = new ArticleContent();

    articleContent.push(String.format("[[%s]]", categoryName), String.format("A-%s", categoryName));
    articleContent.push(String.format("[[%s]]", categoryName), String.format("B-%s", categoryName));
    articleContent.push(String.format("[[%s]]", categoryName), String.format("C-%s", categoryName));

    CategoryPage category = new CategoryPage().open(categoryName);

    Assertion.assertEquals("", category.getArticleContent());
    Assertion.assertTrue(category.categoryMembersContainerIsVisible());
    Assertion.assertTrue(category.hasCategoryMembers());
  }

  @Test(groups = "mercury_category_navigateToCategoryPageWithArticleAndWithMembersFromUrl")
  public void mercury_category_navigateToCategoryPageWithArticleAndWithMembersFromUrl() {
    final String categoryName = String.format("Category:%s", TestContext.getCurrentMethodName());
    ArticleContent articleContent = new ArticleContent();

    articleContent.push("some irrelevant content of category article", categoryName);

    articleContent.push(String.format("[[%s]]", categoryName), String.format("A-%s", categoryName));
    articleContent.push(String.format("[[%s]]", categoryName), String.format("B-%s", categoryName));
    articleContent.push(String.format("[[%s]]", categoryName), String.format("C-%s", categoryName));

    CategoryPage category = new CategoryPage().open(categoryName);

    Assertion.assertFalse("".equals(category.getArticleContent()));
    Assertion.assertTrue(category.categoryMembersContainerIsVisible());
    Assertion.assertTrue(category.hasCategoryMembers());
  }

  @Test(groups = "mercury_category_navigateToCategoryPageWithArticleAndWithoutMembersFromUrl")
  public void mercury_category_navigateToCategoryPageWithArticleAndWithoutMembersFromUrl() {
    final String categoryName = String.format("Category:%s", TestContext.getCurrentMethodName());

    new ArticleContent().push("some irrelevant content of category article", categoryName);
    CategoryPage category = new CategoryPage().open(categoryName);

    Assertion.assertEquals(404, category.getURLStatus(category.getCurrentUrl()));
  }

  @Test(groups = "mercury_category_navigateToCategoryPageFromLinkInArticle")
  public void mercury_category_navigateToCategoryPageFromLinkInArticle() {
    final String categoryName = String.format("Category:%s", TestContext.getCurrentMethodName());

    new ArticleContent().push(String.format("[[%s]]", categoryName));

    CategoryPage categoryPage = new ArticlePage().open().openCategoryPageFromCategoriesDropdown();

    Assertion.assertTrue(categoryPage.hasCategoryMembers());
  }

  @Test(groups = "mercury_category_navigateToCategoryMemberPage")
  public void mercury_category_navigateToCategoryMemberPage() {
    final String categoryName = String.format("Category:%s", TestContext.getCurrentMethodName());
    final String articleContent = "Test article content";

    new ArticleContent().push(String.format("%s [[%s]]", articleContent, categoryName));

    ArticlePage article = new CategoryPage().open(categoryName).navigateToCategoryMemberPage();

    Assertion.assertEquals(articleContent, article.getArticleContent());
  }

  @Test(groups = "mercury_category_testPagination")
  public void mercury_category_testPagination() throws InterruptedException {
    // This test assumes that there are more than 200 articles created and with category like one below.
    // Staging env should have fixture of this articles to create them everytime it is rebuilded

    final String categoryName = "Category:PaginationTests";
    ArticleContent articleContent = new ArticleContent();

    articleContent.push("some irrelevant content of category article", categoryName);

    // execute this loop only once on staging and then create fixture
//    for (int i = 0; i < 300; ++i) {
//      articleContent.push(String.format("aa [[%s]]", categoryName), String.format("%d-%s", i, categoryName));
//    }

    CategoryPage categoryPage = new CategoryPage().open(categoryName);

    Assertion.assertTrue(categoryPage.hasCategoryMembers());
    Assertion.assertTrue(categoryPage.nextButtonIsVisible());

    categoryPage.clickNextButton();

    Assertion.assertTrue(categoryPage.hasCategoryMembers());
    Assertion.assertTrue(categoryPage.previousButtonIsVisible());

    // Wait for scroll animation to be finished.
    // categoryPage.previousButtonIsVisible() scrolls to element but jQuery animation scrolls back to somewhere in the middle of a page.
    Thread.sleep(1000);

    categoryPage.clickPreviousButton();

    Assertion.assertTrue(categoryPage.hasCategoryMembers());
    Assertion.assertTrue(categoryPage.nextButtonIsVisible());
  }
}
