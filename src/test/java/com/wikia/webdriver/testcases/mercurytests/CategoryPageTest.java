package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.pages.CategoryPage;

import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class CategoryPageTest extends NewTestTemplate {

  @Test(groups = "mercury_category_navigateToCategoryPageWithoutArticleAndWithMembersFromUrl")
  public void mercury_category_navigateToCategoryPageWithoutArticleAndWithMembersFromUrl() {
    new CategoryPage()
        .navigateToPageWithoutArticleAndWithMembersFromUrl();
  }

  @Test(groups = "mercury_category_navigateToCategoryPageWithArticleAndWithMembersFromUrl")
  public void mercury_category_navigateToCategoryPageWithArticleAndWithMembersFromUrl() {
    new CategoryPage()
        .navigateToPageWithArticleAndWithMembersFromUrl();
  }

  @Test(groups = "mercury_category_navigateToCategoryPageWithArticleAndWithoutMembersFromUrl")
  public void mercury_category_navigateToCategoryPageWithArticleAndWithoutMembersFromUrl() {
    new CategoryPage()
        .navigateToPageWithArticleAndWithoutMembersFromUrl();
  }

  @Test(groups = "mercury_category_navigateToCategoryPageWithArticleAndWithoutMembersFromLink")
  public void mercury_category_navigateToCategoryPageWithArticleAndWithoutMembersFromLink() {
    new CategoryPage()
        .navigateToPageWithArticleAndNoMembersFromLinkInArticle();
  }

  @Test(groups = "mercury_category_navigateThroughSection")
  public void mercury_category_navigateThroughSection() {
    new CategoryPage()
        .navigateToPageWithArticleAndWithMembersFromUrl()
        .clickLoadMoreButton()
        .clickLoadPreviousButton();
  }
}
