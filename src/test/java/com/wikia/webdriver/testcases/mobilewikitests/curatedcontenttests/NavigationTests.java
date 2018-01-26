package com.wikia.webdriver.testcases.mobilewikitests.curatedcontenttests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.contentpatterns.WikiTextContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.TestContext;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.Flaky;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.url.UrlChecker;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.old.ArticlePageObject;
import com.wikia.webdriver.elements.mercury.old.curatedcontent.CuratedContentPageObject;
import org.testng.annotations.Test;

@Test(groups = "Mercury_CuratedNavigation")
@Execute(onWikia = MercuryWikis.MERCURY_CC)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class NavigationTests extends NewTestTemplate {

  private CuratedContentPageObject curatedContent;
  private Navigate navigate;
  private ArticlePageObject article;

  private void init() {
    this.curatedContent = new CuratedContentPageObject();
    this.navigate = new Navigate();
    this.article = new ArticlePageObject(driver);
  }

  @Test(groups = "MercuryCuratedNavigationTest_001")
  @Flaky
  public void mercuryCuratedNavigationTest_001_navigateThroughCategory() {
    init();

    navigate.toPageByPath(MercurySubpages.CC_MAIN_PAGE);

    curatedContent.clickOnCuratedContentElementByIndex(1);
    curatedContent.waitForPageReload();

    UrlChecker.isPathContainedInCurrentUrl(driver, "/wiki/Category:");
  }

  @Test(groups = "MercuryCuratedNavigationTest_002")
  @Flaky
  public void mercuryCuratedNavigationTest_002_navigateThroughSection() {
    init();

    navigate.toPageByPath(MercurySubpages.CC_MAIN_PAGE);

    curatedContent.clickOnCuratedContentElementByIndex(0);

    curatedContent
        .isTitleVisible()
        .isLinkToMainPageVisible()
        .isSectionVisible()
        .isCuratedContentItemVisibleByIndex(1);

    UrlChecker.isPathContainedInCurrentUrl(
        driver, MercurySubpages.CC_MAIN_PAGE);

    Assertion.assertTrue(false);
  }

  @Test(groups = "MercuryCuratedNavigationTest_003")
  @Flaky
  public void mercuryCuratedNavigationTest_003_redirectToExistingArticle() {
    init();

    String redirectDestination = "RedirectDestination1";

    // if value of redirect is changed, test may fail because of icache - after purge it will pass
    // curl -X PURGE http://staging.icache.service.sjc.consul/wikia.php?controller=MercuryApi&method=getPage&title=NavigationTestsMercuryCuratedNavigationTest_003_redirectToExistingArticle
    String redirect = WikiTextContent.REDIRECT + " " +
                      WikiTextContent.INTERNAL_LINK_OPENING +
                      redirectDestination +
                      WikiTextContent.INTERNAL_LINK_CLOSING;

    new ArticleContent().push(redirect);
    new ArticleContent().push("just dummy text", redirectDestination);

    navigate.toPage(TestContext.getCurrentMethodName());

    Assertion.assertTrue(redirectDestination.toLowerCase()
                             .contains(article.getArticleTitle().toLowerCase()));
  }
}
