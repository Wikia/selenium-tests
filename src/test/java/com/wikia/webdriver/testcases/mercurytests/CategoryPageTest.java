package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.pages.CategoryPageWithDescription;
import com.wikia.webdriver.elements.mercury.pages.CategoryPageWithDescriptionAndNoMembers;
import com.wikia.webdriver.elements.mercury.pages.CategoryPageWithoutDescription;

import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class CategoryPageTest extends NewTestTemplate {

  @Test(groups = "mercury_category_navigateToCategoryPageWithoutDescriptionFromUrl")
  public void mercury_category_navigateToCategoryPageWithoutDescriptionFromUrl() {
    new CategoryPageWithoutDescription().open();
  }

  @Test(groups = "mercury_category_navigateToCategoryPageWithDescriptionFromUrl")
  public void mercury_category_navigateToCategoryPageWithDescriptionFromUrl() {
    new CategoryPageWithDescription().open();
  }

  @Test(groups = "mercury_category_navigateToCategoryPageWithDescriptionAndNoMembersFromUrl")
  public void mercury_category_navigateToCategoryPageWithDescriptionAndNoMembersFromUrl() {
    new CategoryPageWithDescriptionAndNoMembers().open();
  }
}
