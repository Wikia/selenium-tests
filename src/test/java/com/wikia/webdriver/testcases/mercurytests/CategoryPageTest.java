package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.TestContext;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.pages.ArticlePage;
import com.wikia.webdriver.elements.mercury.pages.CategoryPage;

import junit.framework.Assert;
import org.testng.annotations.Test;

@Test(groups = "Mercury_Category")
@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
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

    Assert.assertEquals("", category.getArticleContent());
    Assert.assertTrue(category.categoryMembersContainerIsVisible());
    Assert.assertTrue(category.hasCategoryMembers());
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

    Assert.assertFalse("".equals(category.getArticleContent()));
    Assert.assertTrue(category.categoryMembersContainerIsVisible());
    Assert.assertTrue(category.hasCategoryMembers());
  }

  @Test(groups = "mercury_category_navigateToCategoryPageWithArticleAndWithoutMembersFromUrl")
  public void mercury_category_navigateToCategoryPageWithArticleAndWithoutMembersFromUrl() {
    final String categoryName = String.format("Category:%s", TestContext.getCurrentMethodName());

    new ArticleContent().push("some irrelevant content of category article", categoryName);
    CategoryPage category = new CategoryPage().open(categoryName);

    Assert.assertEquals(404, category.getURLStatus(category.getCurrentUrl()));
  }

  @Test(groups = "mercury_category_navigateToCategoryPageWithArticleAndWithoutMembersFromLink")
  public void mercury_category_navigateToCategoryPageFromLinkInArticle() {
    final String categoryName = String.format("Category:%s", TestContext.getCurrentMethodName());

    new ArticleContent().push(String.format("[[%s]]", categoryName));

    CategoryPage categoryPage = new ArticlePage()
        .open()
        .openCategoryPageFromLink();

    Assert.assertTrue(categoryPage.hasCategoryMembers());
  }

  @Test(groups = "mercury_category_navigateToCategoryMemberPage")
  public void mercury_category_navigateToCategoryMemberPage() {
    final String categoryName = String.format("Category:%s", TestContext.getCurrentMethodName());
    final String articleContent = "Test article content";

    new ArticleContent().push(String.format("%s [[%s]]", articleContent, categoryName));

    ArticlePage article = new CategoryPage()
        .open(categoryName)
        .navigateToCategoryMemberPage();

    Assert.assertEquals(articleContent, article.getArticleContent());
  }

  //TODO: test pagination
}
