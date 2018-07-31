package com.wikia.webdriver.testcases.mobile;

import com.wikia.webdriver.common.contentpatterns.MobileWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.skin.Skin;
import com.wikia.webdriver.common.skin.SkinHelper;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.communities.mobile.components.Footer;
import com.wikia.webdriver.elements.communities.mobile.pages.ArticlePage;

import org.testng.annotations.Test;

@Test(groups = "MobileWiki_GlobalFooter")
@Execute(onWikia = MobileWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class GlobalFooterTests extends NewTestTemplate {

  @Test
  public void userCanViewFullSiteAndReturnToMobile() {
    new ArticlePage().open();
    Footer footer = new Footer();
    footer.clickViewFullSiteLink();

    Assertion.assertTrue(new SkinHelper(driver).isSkin(Skin.OASIS));

    footer.clickViewMobileSite();

    Assertion.assertTrue(new SkinHelper(driver).isSkin(Skin.MOBILE_WIKI));
  }
}
