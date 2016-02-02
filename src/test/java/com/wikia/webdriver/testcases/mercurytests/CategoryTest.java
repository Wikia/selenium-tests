package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.helpers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.Category;

import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class CategoryTest extends NewTestTemplate {

  private Navigate navigate;
  private Category category;

  private void init() {
    this.navigate = new Navigate(driver);
    this.category = new Category(driver);
  }

  @Test(groups = "mercury-category-001")
  public void category_001_openNavigate() {
    init();

    navigate.toPage(MercurySubpages.MAIN_PAGE);
    category.toggle();
    category.openLink(0);
  }
}
