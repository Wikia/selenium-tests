package com.wikia.webdriver.testcases.mobilewikitests;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.pages.MainPage;

import org.testng.annotations.Test;
@Test(groups = "Mercury_Category")
@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class CategoryTest extends NewTestTemplate {

  @Test(groups = "mercury_category_navigateToCategoryPageFromCategoryComponentOnMainPage")
  @RelatedIssue(issueID = "QAART-1044")
  public void mercury_category_navigateToCategoryPageFromCategoryComponentOnMainPage() {
    new MainPage()
        .openRegularMainPage()
        .useCategoryComponent()
        .toggleMenu()
        .openCategoryPage("Galleries");
  }
}
