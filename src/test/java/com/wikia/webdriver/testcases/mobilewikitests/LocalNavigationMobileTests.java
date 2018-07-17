package com.wikia.webdriver.testcases.mobilewikitests;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;

import org.testng.annotations.Test;

@Test(groups = "Mobile_LocalNavigation")
@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class LocalNavigationMobileTests extends NewTestTemplate {

  @Test
  public void localNavIsVisible() {

  }

  @Test
  public void clickOnCommunityNameTakesUserToWikiMainpage() {

  }

  @Test
  public void clickOnDiscussionsIconTakesUserToDiscussions() {

  }

  @Test
  public void clickOnLocalNavEntrypointOpensLocalMenu() {

  }

}
