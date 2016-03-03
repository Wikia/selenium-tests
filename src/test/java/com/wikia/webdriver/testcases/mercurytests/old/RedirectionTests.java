package com.wikia.webdriver.testcases.mercurytests.old;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.skin.Skin;
import com.wikia.webdriver.common.skin.SkinHelper;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.old.MercuryFooterComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.article.ArticleNavigationComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.article.OasisFooterComponentObject;

import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class RedirectionTests extends NewTestTemplate {

  private Navigate navigate;

  private void redirectFromFullSiteToOasis() {
    init();
    navigate.toPage(MercurySubpages.MAIN_PAGE);

    new MercuryFooterComponentObject(driver).clickFullSiteLink();
    new ArticleNavigationComponentObject(driver).clickRandomArticle();

    Assertion.assertTrue(new SkinHelper(driver).isSkin(Skin.OASIS));
  }

  private void init() {
    this.navigate = new Navigate(driver);
  }

  @Test(groups = "MercuryRedirectionTest_001")
  public void MercuryRedirectionTest_001_RedirectFromWWW() {
    String navigateUrl = UrlBuilder.getUrlForPageWithWWW(MercurySubpages.MAIN_PAGE);
    String expectedUrl = UrlBuilder.getUrlForPage(MercurySubpages.MAIN_PAGE);

    driver.get("http://" + navigateUrl);

    String currentUrl = driver.getCurrentUrl();
    Assertion.assertTrue(currentUrl.contains(expectedUrl) && !currentUrl.contains("www."));
  }

  @Test(groups = "MercuryRedirectionTest_002")
  public void MercuryRedirectionTest_002_RedirectFromFullSiteToOasisAsAnon() {
    redirectFromFullSiteToOasis();
  }

  @Test(groups = "MercuryRedirectionTest_003", enabled = false)
  @Execute(asUser = User.USER)
  @RelatedIssue(issueID = "XW-1131")
  public void MercuryRedirectionTest_003_RedirectFromFullSiteToOasisAsLoggedInUser() {
    redirectFromFullSiteToOasis();
  }

  @Test(groups = "MercuryRedirectionTest_004")
  public void MercuryRedirectionTest_004_RedirectFromFullSiteToMobile() {
    init();
    navigate.toPage(MercurySubpages.MAIN_PAGE);

    new MercuryFooterComponentObject(driver).clickFullSiteLink();
    new OasisFooterComponentObject(driver).clickMobileSiteLink();

    Assertion.assertTrue(new SkinHelper(driver).isSkin(Skin.MERCURY));
  }
}
