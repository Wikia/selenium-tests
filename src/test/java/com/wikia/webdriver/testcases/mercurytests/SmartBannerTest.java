package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.helpers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.SmartBanner;

import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.MLP)
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class SmartBannerTest extends NewTestTemplate {

  private SmartBanner smartBanner;

  private void init() {
    this.smartBanner = new SmartBanner(driver);

    new Navigate(driver).toPage("/wiki/" + MercurySubpages.MLP_MAIN_PAGE);
  }

  @Test(groups = "mercury-smart-banner-001")
  public void smartBanner_001_fixedPositionAndClose() {
    init();

    smartBanner.scrollDown();
    smartBanner.scrollUp();
    smartBanner.close();
  }
}
