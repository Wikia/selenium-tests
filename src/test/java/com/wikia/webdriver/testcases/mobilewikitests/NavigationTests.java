package com.wikia.webdriver.testcases.mobilewikitests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.Navigation;
import com.wikia.webdriver.elements.mercury.components.TopBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

public abstract class NavigationTests extends NewTestTemplate {

  void mercury_navigation_openAndCloseNavigationAndItsSubMenu(WikiBasePageObject page) {
    TopBar topBar = page.getTopBar();

    topBar
        .openNavigation()
        .openSubMenu(1)
        .closeSubMenu();

    topBar.clickCloseButton();

    Assertion.assertTrue(topBar.isHamburgerIconVisible());
  }

  void mercury_navigation_resetNavigationState(WikiBasePageObject page) {
    TopBar topBar = page.getTopBar();

    Navigation navigation = topBar.openNavigation();

    Assertion.assertTrue(navigation.isMainHeaderVisible());
    navigation.openSubMenu(1);

    Assertion.assertTrue(navigation.isBackButtonVisible());
    topBar.clickCloseButton();

    topBar.openNavigation();
    Assertion.assertTrue(navigation.isMainHeaderVisible());
  }

  void mercury_navigation_backButton(WikiBasePageObject page) {
    Navigation navigation = page.getTopBar().openNavigation();

    Assertion.assertTrue(navigation.isMainHeaderVisible());
    navigation.openSubMenu(1);

    Assertion.assertTrue(navigation.isBackButtonVisible());
    navigation.clickBackButton();

    Assertion.assertTrue(navigation.isMainHeaderVisible());
  }

  void mercury_navigation_navigationOnEnglishWiki(WikiBasePageObject page) {
    Navigation navigation = page.getTopBar().openNavigation();

    Assertion.assertTrue(navigation.areHubLinksVisible());
  }

  void mercury_navigation_navigationOnNonEnglishWiki(WikiBasePageObject page) {
    Navigation navigation = page.getTopBar().openNavigation();

    Assertion.assertFalse(navigation.areHubLinksVisible());
  }

  void mercury_navigation_navigationElementsUserLoggedIn(WikiBasePageObject page) {
    Navigation navigation = page.getTopBar().openNavigation();

    Assertion.assertTrue(navigation.isUserAvatarVisible());
    Assertion.assertTrue(navigation.isUserProfileLinkVisible());
    Assertion.assertTrue(navigation.isNavigationToUserProfileVisible());
  }

  void mercury_navigation_navigationElementsAnonymousUser(WikiBasePageObject page) {
    Navigation navigation = page.getTopBar().openNavigation();

    Assertion.assertTrue(navigation.isUserAvatarVisible());
    Assertion.assertFalse(navigation.isUserProfileLinkVisible());
    Assertion.assertFalse(navigation.isNavigationToUserProfileVisible());
    Assertion.assertEquals(navigation.getNavigationHeaderText(), "Sign In | Register");
  }
}
