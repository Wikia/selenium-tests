package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.MercurySEODataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.components.Head;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class HTMLTitleTests extends NewTestTemplate {

  private Head head;
  private Navigate navigate;

  @BeforeMethod(alwaysRun = true)
  private void init() {
    this.head = new Head();
    this.navigate = new Navigate(driver);
  }

  @Test(
          groups = "mercury_htmlTitleSet",
          dataProviderClass = MercurySEODataProvider.class,
          dataProvider = "htmlTitles"
  )
  public void mercury_htmlTitleSet(Page page, String expectedTitle) {
    navigate.toUrl(page.getUrl());
    String actualTitle = head.getDocumentTitle();

    Assertion.assertEquals(actualTitle, getFullTitle(expectedTitle));
  }

  private String getFullTitle(String title) {
    String env = Configuration.getEnv();

    switch (env) {
      case "prod":
        return title;
      case "stable":
        return "stable-s1 - " + title;
      case "preview":
        return "staging-s1 - " + title;
      case "verify":
        return "staging-s2 - " + title;
      default:
        return env + " - " + title;
    }
  }
}
