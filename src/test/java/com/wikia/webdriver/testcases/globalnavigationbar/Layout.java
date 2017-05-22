package com.wikia.webdriver.testcases.globalnavigationbar;

import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialWikiActivityPageObject;

import org.testng.annotations.Test;

@Test(groups = {"globalnavigationbar", "globalnavigationbarLayout"})
public class Layout extends NewTestTemplate {

  @Test(groups = {"globalNavigationBarIsFixedOnScroll"})
  public void globalNavigationBarIsFixedOnScroll() {
    SpecialWikiActivityPageObject wikiActivity = new SpecialWikiActivityPageObject(driver).open();
    wikiActivity.verifyGlobalNavigation();
    wikiActivity.scrollToFooter();
    wikiActivity.verifyGlobalNavigation();
  }
}
