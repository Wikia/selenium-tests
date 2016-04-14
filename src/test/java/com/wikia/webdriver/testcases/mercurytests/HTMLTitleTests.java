package com.wikia.webdriver.testcases.mercurytests;


import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.MercurySEODataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.components.Head;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class HTMLTitleTests extends TemplateNoFirstLoad {

  private Head head;
  private Navigate navigate;

  @BeforeMethod(alwaysRun = true)
  private void init() {
    this.head = new Head();
    this.navigate = new Navigate(driver);
  }

  @Test(
          groups = "htmlTitleSet",
          dataProviderClass = MercurySEODataProvider.class,
          dataProvider = "htmlTitles"
  )
  public void htmlTitleSet(Page page, String expectedTitle) {
    navigate.toUrl(page.getUrl());
    String actualTitle = head.getDocumentTitle();

    Assertion.assertEquals(expectedTitle, actualTitle);
  }
}
