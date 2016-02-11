package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.BrowserType;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.Category;

import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(browser = BrowserType.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class CategoryTest extends NewTestTemplate {

  private Category category;

  private void init() {
    this.category = new Category(driver);

    new Navigate(driver).toPage(MercurySubpages.MAIN_PAGE);
  }

  @Test(groups = "mercury_category_expandAndNavigateToCategoryPage")
  public void mercury_category_expandAndNavigateToCategoryPage() {
    init();

    category.toggle();
    category.openCategoryPage(0);
  }
}
