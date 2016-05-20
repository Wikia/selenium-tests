package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.components.Navigation;
import com.wikia.webdriver.elements.mercury.components.TopBar;
import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class TopBarTests extends NewTestTemplate {

  private TopBar topBar;
  private Navigation navigation;

  private void init() {
    this.topBar = new TopBar(driver);
    this.navigation = new Navigation(driver);

    new Navigate().toPage(MercurySubpages.MAIN_PAGE);
  }

  @Test(groups = "mercury_topbar_topBarIsAlwaysVisible")
  public void mercury_topbar_topBarIsAlwaysVisible() {
    init();

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
    init();

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
    init();

    topBar.openSearch();
    Assertion.assertTrue(topBar.isCloseIconVisible());
    Assertion.assertTrue(topBar.isHamburgerIconVisible());

    topBar.openNavigation();
    Assertion.assertTrue(topBar.isCloseIconVisible());
    Assertion.assertTrue(topBar.isSearchIconVisible());
  }

  @Test(groups = "mercury_topbar_fandomBarIsVisibleOnEnglishCommunity")
  public void mercury_topbar_fandomBarIsVisibleOnEnglishCommunity() {
    init();

    Assertion.assertTrue(topBar.isFandomBarVisible());
  }

  @Execute(onWikia = MercuryWikis.DE_WIKI)
  @Test(groups = "mercury_topbar_fandomBarIsNotVisibleOnNonEnglishCommunity")
  public void mercury_topbar_fandomBarIsNotVisibleOnNonEnglishCommunity() {
    init();

    Assertion.assertFalse(topBar.isFandomBarVisible());
  }

  @Test(groups = "mercury_topbar_wikiaLogoRedirectsToFandomPage")
  public void mercury_topbar_wikiaLogoRedirectsToFandomPage() {
    init();

    Assertion.assertTrue(topBar.isLogoVisible());
    topBar.clickWikiaLogo();
    Assertion.assertTrue(topBar.getCurrentUrl().contains("www.wikia.com/fandom"));
  }
}
