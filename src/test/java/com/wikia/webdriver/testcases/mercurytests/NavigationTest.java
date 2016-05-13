package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.components.Navigation;
import com.wikia.webdriver.elements.mercury.components.TopBar;
import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class NavigationTest extends NewTestTemplate {

  private TopBar topBar;
  private Navigation navigation;

  private void init() {
    this.topBar = new TopBar(driver);
    this.navigation = new Navigation(driver);

    new Navigate(driver).toPage(MercurySubpages.MAIN_PAGE);
  }

  @Test(groups = "mercury_navigation_openAndCloseNavigationAndItsSubMenu")
  public void mercury_navigation_openAndCloseNavigationAndItsSubMenu() {
    init();

    topBar.openNavigation();
    navigation.openSubMenu(1);
    navigation.closeSubMenu();
    topBar.clickCloseButton();
  }

  @Test(groups = "mercury_navigation_resetNavigationState")
  public void mercury_navigation_resetNavigationState() {
    init();

    topBar.openNavigation();
    Assertion.assertTrue(navigation.isMainHeaderVisible());
    navigation.openSubMenu(1);
    Assertion.assertTrue(navigation.isBackButtonVisible());
    topBar.clickCloseButton();

    topBar.openNavigation();
    Assertion.assertTrue(navigation.isMainHeaderVisible());
  }

  @Test(groups = "mercury_navigation_backButton")
  public void mercury_navigation_backButton() {
    init();

    topBar.openNavigation();
    Assertion.assertTrue(navigation.isMainHeaderVisible());
    navigation.openSubMenu(1);
    Assertion.assertTrue(navigation.isBackButtonVisible());
    navigation.clickBackButton();
    Assertion.assertTrue(navigation.isMainHeaderVisible());
  }

  @Test(groups = "mercury_navigation_navigationOnEnglishWiki")
  public void mercury_navigation_navigationOnEnglishWiki() {
    init();

    topBar.openNavigation();
    Assertion.assertTrue(navigation.areHubLinksVisible());
  }

  @Execute(onWikia = MercuryWikis.DE_WIKI)
  @Test(groups = "mercury_navigation_navigationOnNonEnglishWiki")
  public void mercury_navigation_navigationOnNonEnglishWiki() {
    init();

    topBar.openNavigation();
    Assertion.assertFalse(navigation.areHubLinksVisible());
  }
}
