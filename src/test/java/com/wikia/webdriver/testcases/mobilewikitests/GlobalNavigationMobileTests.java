package com.wikia.webdriver.testcases.mobilewikitests;

import com.wikia.webdriver.common.contentpatterns.MobileSubpages;
import com.wikia.webdriver.common.contentpatterns.MobileWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.GlobalNavigationMobile;
import com.wikia.webdriver.elements.mercury.pages.ArticlePage;
import org.testng.annotations.Test;

@Test(groups = "Mercury_TopBar")
@Execute(onWikia = MobileWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class GlobalNavigationMobileTests extends NewTestTemplate {

  @Test(groups = "mercury_topbar_topBarIsAlwaysVisible")
  public void mercury_topbar_topBarIsAlwaysVisible() {
    GlobalNavigationMobile globalNavigationMobile =
        new ArticlePage()
            .open(MobileSubpages.MAIN_PAGE)
            .getGlobalNavigationMobile();

    Assertion.assertTrue(globalNavigationMobile.isNavigationBarVisible());
    Assertion.assertTrue(globalNavigationMobile.isLogoVisible());
    Assertion.assertTrue(globalNavigationMobile.isSearchIconVisible());

    driver.executeScript("window.scrollTo(100, document.body.scrollHeight)");

    Assertion.assertTrue(globalNavigationMobile.isNavigationBarVisible());
    Assertion.assertTrue(globalNavigationMobile.isLogoVisible());
    Assertion.assertTrue(globalNavigationMobile.isSearchIconVisible());
  }

  @Test(groups = "mercury_topbar_closeButtonAppears")
  public void mercury_topbar_closeButtonAppears() {
    GlobalNavigationMobile globalNavigationMobile =
        new ArticlePage()
            .open(MobileSubpages.MAIN_PAGE)
            .getGlobalNavigationMobile();

    globalNavigationMobile.openSearch();
    Assertion.assertTrue(globalNavigationMobile.isCloseIconVisible());

    globalNavigationMobile.clickCloseButton();
    Assertion.assertTrue(globalNavigationMobile.isSearchIconVisible());
  }

  @Test(groups = "mercury_topbar_wikiaLogoRedirectsToFandomPage")
  public void mercury_topbar_wikiaLogoRedirectsToFandomPage() {
    GlobalNavigationMobile globalNavigationMobile =
        new ArticlePage()
            .open(MobileSubpages.MAIN_PAGE)
            .getGlobalNavigationMobile();

    globalNavigationMobile.clickFandomLogo();

    Assertion.assertTrue(globalNavigationMobile.getCurrentUrl().contains("fandom.wikia.com"));
  }
}
