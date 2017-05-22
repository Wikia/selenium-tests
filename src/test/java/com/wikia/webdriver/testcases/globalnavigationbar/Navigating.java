package com.wikia.webdriver.testcases.globalnavigationbar;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.globalnav.GlobalNavigation;
import org.testng.annotations.Test;

@Test(groups = {"globalnavigationbar", "globalnavigationbarNavigating"})
public class Navigating extends NewTestTemplate {

  @Test(groups = {"fandomLogoClickOnEnCommunityOpensFandomWikia"})
  public void logoClickOnEnglishCommunityOpensFandom() {
    HomePage homePage = new HomePage();
    GlobalNavigation globalNav = homePage.getGlobalNavigation();

    Assertion.assertTrue(globalNav.isFandomLogoVisible(), "Fandom logo not visible");

    homePage = globalNav.clickFandomLogo();

    Assertion.assertEquals(homePage.getCurrentUrl(), urlBuilder.getUrlForPage("fandom", "/"));
  }
}
