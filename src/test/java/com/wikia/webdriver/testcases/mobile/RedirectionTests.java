package com.wikia.webdriver.testcases.mobile;

import com.wikia.webdriver.common.contentpatterns.MobileSubpages;
import com.wikia.webdriver.common.contentpatterns.MobileWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.url.UrlChecker;
import com.wikia.webdriver.common.templates.NewTestTemplate;

import org.testng.annotations.Test;

@Test(groups = "Mercury_Redirection")
@Execute(onWikia = MobileWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class RedirectionTests extends NewTestTemplate {

  @Test(groups = "redirection_navigateToPageWithWWWAndBeRedirectedToPageWithoutWWW")
  public void redirection_navigateToPageWithWWWAndBeRedirectedToPageWithoutWWW() {
    UrlChecker checker = new UrlChecker();
    String navigateUrl = urlBuilder.getUrlForWikiPageWithWWW(MobileSubpages.MAIN_PAGE);
    String expectedUrl = urlBuilder.getUrlForWikiPage(MobileSubpages.MAIN_PAGE);

    driver.get(navigateUrl);

    String currentUrl = driver.getCurrentUrl();
    Assertion.assertEquals(checker.getProtocolRelativeURL(currentUrl),
            checker.getProtocolRelativeURL(expectedUrl));
    Assertion.assertStringNotContains(currentUrl, "www");
  }
}
