package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.TopBar;
import com.wikia.webdriver.elements.mercury.pages.ArticlePage;
import org.testng.annotations.Test;

@Test(groups = "Mercury_TopBar")
@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class TopBarTests extends NewTestTemplate {

  @Test(groups = "mercury_topbar_topBarIsAlwaysVisible")
  public void mercury_topbar_topBarIsAlwaysVisible() {
    TopBar topBar =
        new ArticlePage()
            .open(MercurySubpages.MAIN_PAGE)
            .getTopBar();

    Assertion.assertTrue(topBar.isNavigationBarVisible());
    Assertion.assertTrue(topBar.isLogoVisible());
    Assertion.assertTrue(topBar.isHamburgerIconVisible());
    Assertion.assertTrue(topBar.isSearchIconVisible());

    driver.executeScript("window.scrollTo(100, document.body.scrollHeight)");

    Assertion.assertTrue(topBar.isNavigationBarVisible());
    Assertion.assertTrue(topBar.isLogoVisible());
    Assertion.assertTrue(topBar.isHamburgerIconVisible());
    Assertion.assertTrue(topBar.isSearchIconVisible());
  }

  @Test(groups = "mercury_topbar_closeButtonAppears")
  public void mercury_topbar_closeButtonAppears() {
    TopBar topBar =
        new ArticlePage()
            .open(MercurySubpages.MAIN_PAGE)
            .getTopBar();

    topBar.openSearch();
    Assertion.assertTrue(topBar.isCloseIconVisible());

    topBar.clickCloseButton();
    Assertion.assertTrue(topBar.isSearchIconVisible());

    topBar.openNavigation();
    Assertion.assertTrue(topBar.isCloseIconVisible());

    topBar.clickCloseButton();
    Assertion.assertTrue(topBar.isHamburgerIconVisible());
  }

  @Test(groups = "mercury_topbar_switchBetweenSearchAndNavigation")
  public void mercury_topbar_switchBetweenSearchAndNavigation() {
    TopBar topBar =
        new ArticlePage()
            .open(MercurySubpages.MAIN_PAGE)
            .getTopBar();

    topBar.openSearch();
    Assertion.assertTrue(topBar.isCloseIconVisible());
    Assertion.assertTrue(topBar.isHamburgerIconVisible());

    topBar.openNavigation();
    Assertion.assertTrue(topBar.isCloseIconVisible());
    Assertion.assertTrue(topBar.isSearchIconVisible());
  }

  @Test(groups = "mercury_topbar_wikiaLogoRedirectsToFandomPage")
  public void mercury_topbar_wikiaLogoRedirectsToFandomPage() {
    TopBar topBar =
        new ArticlePage()
            .open(MercurySubpages.MAIN_PAGE)
            .getTopBar();

    topBar.clickWikiaLogo();

    Assertion.assertTrue(topBar.getCurrentUrl().contains("fandom.wikia.com"));
  }
}
