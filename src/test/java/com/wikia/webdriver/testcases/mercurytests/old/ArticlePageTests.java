package com.wikia.webdriver.testcases.mercurytests.old;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.ContentLoader;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.components.Loading;
import com.wikia.webdriver.elements.mercury.components.Navigation;
import com.wikia.webdriver.elements.mercury.components.TopBar;
import com.wikia.webdriver.elements.mercury.old.ArticlePageObject;

import org.testng.annotations.Test;

@Test(groups = "Mercury_Article")
@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class ArticlePageTests extends NewTestTemplate {

  private TopBar topBar;
  private Navigation navigation;
  private Navigate navigate;
  private Loading loading;

  private static final String MAIN_PAGE_CONTENT =
          ContentLoader.loadWikiTextContent("Mercury_MainPage");

  private static final String LINKED_IMAGES_CONTENT =
          ContentLoader.loadWikiTextContent("Mercury_LinkedImages");

  private void init() {
    this.topBar = new TopBar();
    this.navigation = new Navigation(driver);
    this.navigate = new Navigate();
    this.loading = new Loading(driver);
  }

  @Test(groups = "mercury_article_wikiaLogoTopContributorsSectionAndFooterElementsAreVisible")
  public void mercury_article_wikiaLogoTopContributorsSectionAndFooterElementsAreVisible() {
    new ArticleContent().push(MAIN_PAGE_CONTENT, "Main_Page");

    init();
    ArticlePageObject articlePage = new ArticlePageObject(driver);
    navigate.toPage("/wiki/Main_Page");

    Assertion.assertTrue(articlePage.isWikiaLogoVisible());
    Assertion.assertTrue(articlePage.isSearchButtonVisible());
    Assertion.assertTrue(articlePage.isTopContributorsSectionVisible());
    Assertion.assertTrue(articlePage.isTopContributorsThumbVisible(0));
    Assertion.assertTrue(articlePage.isFooterVisible());
    Assertion.assertTrue(articlePage.isFooterLogoVisible());
  }

  @Test(groups = "mercury_article_linksInTopContributorsSectionRedirectsToUserPage")
  public void mercury_article_linksInTopContributorsSectionRedirectsToUserPage() {
    new ArticleContent().push(MAIN_PAGE_CONTENT, "Main_Page");

    init();
    ArticlePageObject articlePage = new ArticlePageObject(driver);
    navigate.toPage("/wiki/Main_Page");

    articlePage.clickTopContributor(0);

    Assertion.assertTrue(articlePage.isUrlContainingUserPage());
  }

  @Test(groups = "mercury_article_linkedImagesRedirectToCorrespondingUrl")
  public void mercury_article_linkedImagesRedirectToCorrespondingUrl() {
    new ArticleContent().push(LINKED_IMAGES_CONTENT, "LinkedImages");

    init();
    ArticlePageObject articlePage = new ArticlePageObject(driver);
    navigate.toPage("/wiki/LinkedImages");

    String oldUrl = driver.getCurrentUrl();
    articlePage.clickOnImage(0);
    loading.handleAsyncPageReload();

    Assertion.assertFalse(driver.getCurrentUrl().equals(oldUrl));
  }

  @Test(groups = "mercury_article_navigateToArticlesWithColonAndQuestionMark")
  public void mercury_article_navigateToArticlesWithColonAndQuestionMark() {
    new ArticleContent().push("Article about colon [[Question?mark?question]]", "Colon:colon:colon");
    new ArticleContent().push("Article about question mark [[Colon:colon:colon]]", "Question?mark?question");

    init();
    ArticlePageObject article = new ArticlePageObject(driver);

    String encodedQuestionMarkUrl = "/wiki/Question%3Fmark%3Fquestion";
    String encodedColonUrl = "/wiki/Colon%3Acolon%3Acolon";

    PageObjectLogging.logWarning(
        "Info",
        "Accessing article directly through URL"
    );

    navigate.toPage(encodedColonUrl);

    Assertion.assertTrue(driver.getCurrentUrl().contains(encodedColonUrl));
    Assertion.assertTrue(MercurySubpages.COLON.toLowerCase().contains(article.getArticleTitle().toLowerCase()));

    navigate.toPage(encodedQuestionMarkUrl);

    Assertion.assertTrue(driver.getCurrentUrl().contains(encodedQuestionMarkUrl));
    Assertion.assertTrue(MercurySubpages.QUESTION_MARK.toLowerCase().contains(article.getArticleTitle().toLowerCase()));

    PageObjectLogging.logWarning(
        "Info",
        "Accessing article through link in content"
    );

    article.clickOnAnchorInContent(0);
    loading.handleAsyncPageReload();

    Assertion.assertFalse(driver.getCurrentUrl().contains(encodedColonUrl));
    Assertion.assertTrue(MercurySubpages.COLON.toLowerCase().contains(article.getArticleTitle().toLowerCase()));

    article.clickOnAnchorInContent(0);
    loading.handleAsyncPageReload();

    Assertion.assertTrue(driver.getCurrentUrl().contains(encodedQuestionMarkUrl));
    Assertion.assertTrue(MercurySubpages.QUESTION_MARK.toLowerCase().contains(article.getArticleTitle().toLowerCase()));

    PageObjectLogging.logWarning("Info", "Accessing article through link in navigation side");

    topBar.openNavigation();
    navigation.openSubMenu(3);
    navigation.openPageLink(5);

    Assertion.assertFalse(driver.getCurrentUrl().contains(encodedColonUrl));
    Assertion.assertTrue(MercurySubpages.COLON.toLowerCase().contains(article.getArticleTitle().toLowerCase()));

    topBar.openNavigation();
    navigation.openSubMenu(3);
    navigation.openPageLink(4);

    Assertion.assertTrue(driver.getCurrentUrl().contains(encodedQuestionMarkUrl));
    Assertion.assertTrue(MercurySubpages.QUESTION_MARK.toLowerCase().contains(article.getArticleTitle().toLowerCase()));

    PageObjectLogging.logWarning("Info", "Accessing article through link in search result");

    topBar.openSearch().navigateToPage(MercurySubpages.COLON.substring(6));

    Assertion.assertTrue(driver.getCurrentUrl().contains(encodedColonUrl));
    Assertion.assertTrue(MercurySubpages.COLON.toLowerCase().contains(article.getArticleTitle().toLowerCase()));

    topBar.openSearch().navigateToPage(MercurySubpages.QUESTION_MARK.substring(6));

    Assertion.assertTrue(driver.getCurrentUrl().contains(encodedQuestionMarkUrl));
    Assertion.assertTrue(MercurySubpages.QUESTION_MARK.toLowerCase().contains(article.getArticleTitle().toLowerCase()));
  }
}
