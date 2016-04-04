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
import com.wikia.webdriver.elements.mercury.pages.CategoryPageWithDescription;
import com.wikia.webdriver.elements.mercury.pages.CategoryPageWithoutDescription;

import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class CategoryTest extends NewTestTemplate {

  private Category category;

  private void init() {
    this.category = new Category(driver);

    new Navigate(driver).toPage(MercurySubpages.CATEGORY_TEST_PAGE);
  }

  @Test(groups = "mercury_category_expandAndNavigateToCategoryPageWithDescription")
  public void mercury_category_expandAndNavigateToCategoryPageWithDescription() {
    init();
    CategoryPageWithDescription categoryPage = new CategoryPageWithDescription();

    category
        .toggle()
        .openCategoryPage(MercurySubpages.CATEGORY_WITH_DESCRIPTION);

    categoryPage.check();
  }

  @Test(groups = "mercury_category_expandAndNavigateToCategoryPageWithoutDescription")
  public void mercury_category_expandAndNavigateToCategoryPageWithoutDescription() {
    init();
    CategoryPageWithoutDescription categoryPage = new CategoryPageWithoutDescription();

    category
        .toggle()
        .openCategoryPage(MercurySubpages.CATEGORY_WITHOUT_DESCRIPTION);

    categoryPage.check();
  }
}
