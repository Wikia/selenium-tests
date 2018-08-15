package com.wikia.webdriver.testcases.mobile;

import com.wikia.webdriver.common.contentpatterns.MobileSubpages;
import com.wikia.webdriver.common.contentpatterns.MobileWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.ContentLoader;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.communities.mobile.components.navigation.global.GlobalNavigationMobile;
import com.wikia.webdriver.elements.communities.mobile.pages.ArticlePageObject;
import org.testng.annotations.Test;

/*
 * Tests for wiki article's content for mobile view
 */

@Test(groups = "Mercury_Article")
@Execute(onWikia = MobileWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class ArticlePageTests extends NewTestTemplate {

  private GlobalNavigationMobile globalNavigationMobile;
  private Navigate navigate;

  private static final String MAIN_PAGE_CONTENT =
          ContentLoader.loadWikiTextContent("Mercury_MainPage");
  private static final String LINKED_IMAGES_CONTENT =
          ContentLoader.loadWikiTextContent("Mercury_LinkedImages");
  private static final String GALLERY_CONTENT =
          ContentLoader.loadWikiTextContent("Mercury_Gallery");

  private void init() {
    this.globalNavigationMobile = new GlobalNavigationMobile();
    this.navigate = new Navigate();
  }

  @Test(groups = "articleWikiaLogoTopContributorsSectionAndFooterElementsAreVisible")
  public void articleWikiaLogoTopContributorsSectionAndFooterElementsAreVisible() {
    new ArticleContent().push(MAIN_PAGE_CONTENT, "Main_Page");

    init();
    ArticlePageObject articlePage = new ArticlePageObject(driver);
    navigate.toPageByPath("/wiki/Main_Page");

    Assertion.assertTrue(articlePage.isWikiaLogoVisible());
    Assertion.assertTrue(articlePage.isSearchButtonVisible());
    Assertion.assertTrue(articlePage.isTopContributorsSectionVisible());
    Assertion.assertTrue(articlePage.isTopContributorsThumbVisible(0));
    Assertion.assertTrue(articlePage.isFooterVisible());
    Assertion.assertTrue(articlePage.isFooterLogoVisible());
  }

  @Test(groups = "articleLinksInTopContributorsSectionRedirectsToUserPage")
  public void articleLinksInTopContributorsSectionRedirectsToUserPage() {
    new ArticleContent().push(MAIN_PAGE_CONTENT, "Main_Page");

    init();
    ArticlePageObject articlePage = new ArticlePageObject(driver);
    navigate.toPageByPath("/wiki/Main_Page");

    articlePage.clickNthTopContributorAvatar(0);

    Assertion.assertTrue(articlePage.getCurrentUrl().contains("/wiki/User:"));
  }

  @Test(groups = "articleLinkedImagesRedirectToCorrespondingUrl")
  public void articleLinkedImagesRedirectToCorrespondingUrl() {
    new ArticleContent().push(LINKED_IMAGES_CONTENT, "LinkedImages");
    new ArticleContent().push(GALLERY_CONTENT, "Gallery");

    init();
    ArticlePageObject articlePage = new ArticlePageObject(driver);
    navigate.toPageByPath("/wiki/LinkedImages");

    String oldUrl = driver.getCurrentUrl();
    articlePage.clickOnImage(0);
    articlePage.waitForPageReload();

    Assertion.assertFalse(driver.getCurrentUrl().equals(oldUrl));
  }

  //test disabled as it needs to be adjusted when new local nav tests will be created
  @Test(groups = "articleNavigateToArticlesWithColonAndQuestionMark", enabled = false)
  public void articleNavigateToArticlesWithColonAndQuestionMark() {
    new ArticleContent().push("Article about colon [[Question?mark?question]]", "Colon:colon:colon");
    new ArticleContent().push("Article about question mark [[Colon:colon:colon]]", "Question?mark?question");

    init();
    ArticlePageObject article = new ArticlePageObject(driver);

    String encodedQuestionMarkUrl = "/wiki/Question%3Fmark%3Fquestion";
    String encodedColonUrl = "/wiki/Colon%3Acolon%3Acolon";

    Log.warning(
        "Info",
        "Accessing article directly through URL"
    );

    navigate.toPageByPath(encodedColonUrl);

    Assertion.assertTrue(driver.getCurrentUrl().contains(encodedColonUrl));
    Assertion.assertTrue(MobileSubpages.COLON.toLowerCase().contains(article.getArticleTitle().toLowerCase()));

    navigate.toPageByPath(encodedQuestionMarkUrl);

    Assertion.assertTrue(driver.getCurrentUrl().contains(encodedQuestionMarkUrl));
    Assertion.assertTrue(MobileSubpages.QUESTION_MARK.toLowerCase().contains(article.getArticleTitle().toLowerCase()));

    Log.warning(
        "Info",
        "Accessing article through link in content"
    );

    article.clickOnAnchorInContent(0);
    article.waitForPageReload();

    Assertion.assertFalse(driver.getCurrentUrl().contains(encodedColonUrl));
    Assertion.assertTrue(MobileSubpages.COLON.toLowerCase().contains(article.getArticleTitle().toLowerCase()));

    article.clickOnAnchorInContent(0);
    article.waitForPageReload();

    Assertion.assertTrue(driver.getCurrentUrl().contains(encodedQuestionMarkUrl));
    Assertion.assertTrue(MobileSubpages.QUESTION_MARK.toLowerCase().contains(article.getArticleTitle().toLowerCase()));

    Log.warning("Info", "Accessing article through link in navigation side");

    //globalNavigationMobile.openNavigation();
    //navigation.openSubMenu(3);
    //navigation.openPageLink(5);

    Assertion.assertFalse(driver.getCurrentUrl().contains(encodedColonUrl));
    Assertion.assertTrue(MobileSubpages.COLON.toLowerCase().contains(article.getArticleTitle().toLowerCase()));

    //globalNavigationMobile.openNavigation();
    //navigation.openSubMenu(3);
    //navigation.openPageLink(4);

    Assertion.assertTrue(driver.getCurrentUrl().contains(encodedQuestionMarkUrl));
    Assertion.assertTrue(MobileSubpages.QUESTION_MARK.toLowerCase().contains(article.getArticleTitle().toLowerCase()));

    Log.warning("Info", "Accessing article through link in search result");

    globalNavigationMobile.openSearch().navigateToPage(MobileSubpages.COLON);

    Assertion.assertTrue(driver.getCurrentUrl().contains(encodedColonUrl));
    Assertion.assertTrue(MobileSubpages.COLON.toLowerCase().contains(article.getArticleTitle().toLowerCase()));

    globalNavigationMobile.openSearch().navigateToPage(MobileSubpages.QUESTION_MARK);

    Assertion.assertTrue(driver.getCurrentUrl().contains(encodedQuestionMarkUrl));
    Assertion.assertTrue(MobileSubpages.QUESTION_MARK.toLowerCase().contains(article.getArticleTitle().toLowerCase()));
  }
}
