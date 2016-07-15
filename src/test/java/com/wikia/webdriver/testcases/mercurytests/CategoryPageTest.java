package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.MercuryCategoriesDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.pages.ArticlePage;
import com.wikia.webdriver.elements.mercury.pages.CategoryPage;

import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class CategoryPageTest extends NewTestTemplate {

  @Test(groups = "mercury_category_navigateToCategoryPageWithoutArticleAndWithMembersFromUrl")
  public void mercury_category_navigateToCategoryPageWithoutArticleAndWithMembersFromUrl() {
    new CategoryPage().navigateToPageWithoutArticleAndWithMembersFromUrl();
  }

  @Test(groups = "mercury_category_navigateToCategoryPageWithArticleAndWithMembersFromUrl")
  public void mercury_category_navigateToCategoryPageWithArticleAndWithMembersFromUrl() {
    new CategoryPage().navigateToPageWithArticleAndWithMembersFromUrl();
  }

  @Test(groups = "mercury_category_navigateToCategoryPageWithArticleAndWithoutMembersFromUrl")
  public void mercury_category_navigateToCategoryPageWithArticleAndWithoutMembersFromUrl() {
    new CategoryPage().navigateToPageWithArticleAndWithoutMembersFromUrl();
  }

  @Test(groups = "mercury_category_navigateToCategoryPageWithArticleAndWithoutMembersFromLink")
  public void mercury_category_navigateToCategoryPageFromLinkInArticle() {
    new ArticlePage()
        .openPageWithLinkToCategoryPage()
        .openCategoryPageFromLink();
  }

  @Test(groups = "mercury_category_navigateThroughSection")
  public void mercury_category_navigateThroughSection() {
    new CategoryPage()
        .navigateToPageWithArticleAndWithMembersFromUrl()
        .loadMoreMembersForSection("C")
        .loadPreviousMembersForSection("C");
  }

  @Test(groups = "mercury_category_navigateToCategoryMemberPage")
  public void mercury_category_navigateToCategoryMemberPage() {
    new CategoryPage()
        .navigateToPageWithArticleAndWithMembersFromUrl()
        .navigateToCategoryMemberPage("Category test 001");
  }

  @Test(
          groups = "mercury_category_title_present",
          dataProvider = "getCategoriesWithTitles",
          dataProviderClass = MercuryCategoriesDataProvider.class
  )
  public void mercury_category_title_present(String pageUrl, String expectedTitle) {
    Navigate navigate = new Navigate(driver);
    navigate.toPage(pageUrl);

    CategoryPage categoryPage = new CategoryPage();

    Assertion.assertEquals(categoryPage.getPageTitle(), expectedTitle);
    Assertion.assertEquals(categoryPage.getPageSubtitle(), "Category Page");
  }
}
