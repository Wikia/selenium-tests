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
@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class SmartBannerTest extends NewTestTemplate {

  private SmartBanner smartBanner;

  private void init() {
    this.smartBanner = new SmartBanner(driver);

    new Navigate().toPageByPath(MercurySubpages.GALLERY);
  }

  @Test(groups = "mercury_smartBanner_isNotVisibleOnScrollDown", enabled = false)
  public void mercury_smartBanner_isNotVisibleOnScrollDown() {
    init();

    smartBanner.scrollDown();
  }

  @Test(groups = "mercury_smartBanner_canBeClosed", enabled = false)
  public void mercury_smartBanner_canBeClosed() {
    init();

    smartBanner.scrollDown();
    smartBanner.scrollUp();
    smartBanner.close();
  }
}
