package com.wikia.webdriver.testcases.mercurytests.old.curatedcontenttests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.contentpatterns.WikiTextContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.url.UrlChecker;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.components.Loading;
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
  private Loading loading;
  private ArticlePageObject article;

  private void init() {
    this.curatedContent = new CuratedContentPageObject();
    this.navigate = new Navigate();
    this.loading = new Loading(driver);
    this.article = new ArticlePageObject(driver);
  }

  @Test(groups = "MercuryCuratedNavigationTest_001")
  public void MercuryCuratedNavigationTest_001_navigateThroughCategory() {
    init();

    navigate.toPage(MercurySubpages.CC_MAIN_PAGE);

    curatedContent.clickOnCuratedContentElementByIndex(1);
    loading.handleAsyncPageReload();

    UrlChecker.isPathContainedInCurrentUrl(driver, "/wiki/Category:");
  }

  @Test(groups = "MercuryCuratedNavigationTest_002")
  public void MercuryCuratedNavigationTest_002_navigateThroughSection() {
    init();

    navigate.toPage(MercurySubpages.CC_MAIN_PAGE);

    curatedContent.clickOnCuratedContentElementByIndex(0);

    curatedContent
        .isTitleVisible()
        .isLinkToMainPageVisible()
        .isSectionVisible()
        .isCuratedContentItemVisibleByIndex(1);

    UrlChecker.isPathContainedInCurrentUrl(
        driver, MercurySubpages.CC_MAIN_PAGE);
  }

  @Test(groups = "MercuryCuratedNavigationTest_003")
  public void MercuryCuratedNavigationTest_003_redirectToExistingArticle() {
    init();

    String redirect = WikiTextContent.REDIRECT +
                      WikiTextContent.INTERNAL_LINK_OPENING +
                      MercurySubpages.CC_REDIRECT_DESTINATION +
                      WikiTextContent.INTERNAL_LINK_CLOSING;

    new ArticleContent().push(redirect, MercurySubpages.CC_REDIRECT_SOURCE_1);

    navigate.toPage(MercurySubpages.CC_REDIRECT_SOURCE_1);

    Assertion.assertTrue(MercurySubpages.CC_REDIRECT_DESTINATION.toLowerCase()
                             .contains(article.getArticleTitle().toLowerCase()));
  }
}
