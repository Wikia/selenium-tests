package com.wikia.webdriver.testcases.mobilewikitests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.Navigation;
import com.wikia.webdriver.elements.mercury.components.TopBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

public abstract class NavigationTests extends NewTestTemplate {

  public void mobileWiki_navigation_openAndCloseNavigationAndItsSubMenu(WikiBasePageObject page) {
    TopBar topBar = page.getTopBar();

    topBar
        .openNavigation()
        .clickWikisMenuLink()
        .closeNavigation();

    Assertion.assertTrue(topBar.isSearchIconVisible());
  }

  public void mobileWiki_navigation_navigationOnEnglishWiki(WikiBasePageObject page) {
    Navigation navigation = page.getTopBar().openNavigation();

    Assertion.assertTrue(navigation.areInterntionalHubLinksVisible());
    Assertion.assertTrue(navigation.isVideoHubLinkVisible());
  }

  public void mobileWiki_navigation_navigationOnNonEnglishWiki(WikiBasePageObject page) {
    Navigation navigation = page.getTopBar().openNavigation();

    Assertion.assertTrue(navigation.areInterntionalHubLinksVisible());
    Assertion.assertFalse(navigation.isVideoHubLinkVisible());
  }
}
