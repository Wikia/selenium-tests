package com.wikia.webdriver.testcases.mobilewikitests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;

import org.testng.annotations.Test;
@Test(groups = "Mercury_Redirection")
@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class RedirectionTests extends NewTestTemplate {

  @Test(groups = "mercury_redirection_navigateToPageWithWWWAndBeRedirectedToPageWithoutWWW")
  public void mercury_redirection_navigateToPageWithWWWAndBeRedirectedToPageWithoutWWW() {
    String navigateUrl = urlBuilder.getUrlForPageWithWWW(MercurySubpages.MAIN_PAGE);
    String expectedUrl = urlBuilder.getUrlForPage(MercurySubpages.MAIN_PAGE);

    driver.get(navigateUrl);

    String currentUrl = driver.getCurrentUrl();
    Assertion.assertTrue(currentUrl.contains(expectedUrl) && !currentUrl.contains("www."));
  }
}
