package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.VUAP;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

public class TestVUAP extends TemplateNoFirstLoad {
  private static final Dimension DESKTOP_SIZE = new Dimension(1920, 1080);

  @Test(
          groups = "AdsUapDesktop"
  )
  public void testVideoPlayFlow() {
    Page page = new Page("project43", "SyntheticTests/VUAP");
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);

    VUAP vuap = new VUAP(driver, ads, "TOP_LEADERBOARD");
    vuap.play();

    vuap.waitForVideoPlayerVisible();
    vuap.waitForVideoPlayerHidden();
  }
}
