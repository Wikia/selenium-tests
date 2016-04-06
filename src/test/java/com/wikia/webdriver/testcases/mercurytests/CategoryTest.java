package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.components.Category;
import com.wikia.webdriver.elements.mercury.pages.CategoryPage;

import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class CategoryTest extends NewTestTemplate {

  private Category category;

  private void init() {
    this.category = new Category(driver);

    new Navigate(driver).toPage(MercurySubpages.ARTICLE_WITH_CATEGORY_COMPONENT);
  }

  @Test(groups = "mercury_category_expandAndNavigateToCategoryPageWithArticle")
  public void mercury_category_expandAndNavigateToCategoryPageWithArticle() {
    init();
    CategoryPage categoryPage = new CategoryPage();

    category
        .toggle()
        .openCategoryPage(MercurySubpages.CATEGORY_WITH_ARTICLE_AND_WITH_MEMBERS);

    categoryPage
        .articleContainerIsVisible()
        .noMembersMessageIsVisible();
  }

  @Test(groups = "mercury_category_expandAndNavigateToCategoryPageWithoutArticle")
  public void mercury_category_expandAndNavigateToCategoryPageWithoutArticle() {
    init();
    CategoryPage categoryPage = new CategoryPage();

    category
        .toggle()
        .openCategoryPage(MercurySubpages.CATEGORY_WITHOUT_ARTICLE_AND_WITH_MEMBERS);

    categoryPage
        .articleContainerIsNotPresent()
        .categorySectionsContainerIsVisible();
  }
}
