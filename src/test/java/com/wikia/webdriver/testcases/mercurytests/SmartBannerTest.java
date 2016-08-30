package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.components.SmartBanner;

import org.testng.annotations.Test;
@Test(groups = "Mercury_SmartBanner")
@Execute(onWikia = MercuryWikis.MLP)
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class SmartBannerTest extends NewTestTemplate {

  private SmartBanner smartBanner;

  private void init() {
    this.smartBanner = new SmartBanner(driver);

    new Navigate().toPage(MercurySubpages.MLP_MAIN_PAGE);
  }

  @Test(groups = "mercury_smartBanner_isNotVisibleOnScrollDown")
  public void mercury_smartBanner_isNotVisibleOnScrollDown() {
    init();

    smartBanner.scrollDown();
  }

  @Test(groups = "mercury_smartBanner_canBeClosed")
  public void mercury_smartBanner_canBeClosed() {
    init();

    smartBanner.scrollDown();
    smartBanner.scrollUp();
    smartBanner.close();
  }
}
