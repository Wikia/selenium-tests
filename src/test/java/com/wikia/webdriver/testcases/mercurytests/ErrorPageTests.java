package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.pages.ErrorPage;

import org.testng.annotations.Test;
@Test(groups = "Mercury_ErrorPage")
@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(browser = Browser.CHROME, emulator = Emulator.NEXUS_5X)
public class ErrorPageTests extends NewTestTemplate {

  @Test(groups = "mercury_errorPage_navigateFromErrorPageToArticlePageUsingGlobalNav")
  public void mercury_errorPage_navigateFromErrorPageToArticlePageUsingGlobalNav() {
    new ErrorPage()
        .navigateToErrorPageFromUrl()
        .openNavigationByClickOnLink()
        .openSubMenu(1)
        .openPageLink(0);
  }
}
