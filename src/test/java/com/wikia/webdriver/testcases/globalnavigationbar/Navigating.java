package com.wikia.webdriver.testcases.globalnavigationbar;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.globalnav.GlobalNavigation;
import org.testng.annotations.Test;

@Test(groups = {"globalnavigationbar", "globalnavigationbarNavigating"})
public class Navigating extends NewTestTemplate {

  private final String FANDOM_URL = "http://www.wikia.com/fandom";

  @Execute(onWikia = "muppet")
  @Test(groups = {"fandomLogoClickOnEnCommunityOpensFandomWikia"})
  public void logoClickOnEnglishCommunityOpensFandom() {
    HomePage homePage = new HomePage();
    GlobalNavigation globalNav = homePage.getGlobalNavigation();

    Assertion.assertTrue(globalNav.isFandomLogoVisible(), "Fandom logo not visible");

    homePage = globalNav.clickWikiaLogo();

    Assertion.assertEquals(homePage.getCurrentUrl(), FANDOM_URL);
  }
}
