package com.wikia.webdriver.testcases.mercurytests.old;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.core.helpers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.Loading;
import com.wikia.webdriver.elements.mercury.Navigation;
import com.wikia.webdriver.elements.mercury.TopBar;
import com.wikia.webdriver.elements.mercury.old.ArticlePageObject;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

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

  private static final By WIKIA_MOBILE_WIKI_TITLE = By.cssSelector("#wkWrdMrk");

  private static final String[] FOOTER_ELEMENTS =
      {"Games", "Movies", "TV", "Comics", "Music", "Books", "Lifestyle", "Full site", "Licensing",
       "Privacy Policy", "Feedback"};

  private void init() {
    this.topBar = new TopBar(driver);
    this.navigation = new Navigation(driver);
    this.navigate = new Navigate(driver);
    this.loading = new Loading(driver);
  }

  @Test(groups = "mercury-article-verifyWikiaLogoAndSearchButtonAndTopContributorsAndFooter")
  public void verifyWikiaLogoAndSearchButtonAndTopContributorsSectionAndFooterElements() {
    init();
    ArticlePageObject articlePage = new ArticlePageObject(driver);
    navigate.toPage(MercurySubpages.MAIN_PAGE);

    boolean result = articlePage.isWikiaLogoVisible();
    PageObjectLogging.log(
        "Wikia logo",
        "is visible",
        "is not visible",
        result
    );

    result = articlePage.isSearchButtonVisible();
    PageObjectLogging.log(
        "Search button",
        "is visible",
        "is not visible",
        result
    );

    result = articlePage.isTopContributorsSectionVisible();
    PageObjectLogging.log(
        "Top contributors section",
        "is visible",
        "is not visible",
        result
    );

    result = articlePage.isTopContributorsThumbVisible(0);
    PageObjectLogging.log(
        "Top contributors thumb",
        "is visible",
        "is not visible",
        result
    );

    result = articlePage.isFooterLogoVisible();
    PageObjectLogging.log(
        "Footer Wikia logo",
        "is visible",
        "is not visible",
        result
    );

    for (int i = 0; i < FOOTER_ELEMENTS.length; ++i) {
      result = articlePage.isElementInFooterVisible(FOOTER_ELEMENTS[i], i);
      PageObjectLogging.log(
          "Footer link " + FOOTER_ELEMENTS[i],
          "is visible",
          "is not visible",
          result
      );
    }
  }

  @Test(groups = "mercury-article-tapingOnContributorRedirectsToUserPage")
  public void tapingOnContributorRedirectsToUserPage() {
    init();
    ArticlePageObject articlePage = new ArticlePageObject(driver);
    navigate.toPage(MercurySubpages.MAIN_PAGE);

    articlePage.clickTopContributor(0);

    boolean result = articlePage.isUrlContainingUserPage();
    PageObjectLogging.log(
        "Url",
        "match pattern /wiki/User:",
        "does not match pattern /wiki/User:",
        result
    );
  }

  @Test(groups = "mercury-article-clickOnLinkedImageChangesTheUrl")
  public void clickOnLinkedImageChangesTheUrl() {
    init();
    ArticlePageObject articlePage = new ArticlePageObject(driver);
    navigate.toPage(MercurySubpages.LINKED_IMAGES);

    String oldUrl = driver.getCurrentUrl();
    articlePage.clickOnImage(0);
    loading.handleAsyncPageReload();

    boolean result = !driver.getCurrentUrl().equals(oldUrl);
    PageObjectLogging.log(
        "Redirection",
        "works",
        "does not work",
        result
    );
  }

  @Test(groups = "mercury-article-categoryListExpandsOnCategoryButtonClick")
  public void categoryListExpandsOnCategoryButtonClick() {
    init();
    ArticlePageObject articlePage = new ArticlePageObject(driver);
    navigate.toPage(MercurySubpages.MAIN_PAGE);

    Assertion.assertTrue(
        articlePage.isChevronCollapsed(),
        "Chevron isn't collapsed"
    );

    PageObjectLogging.log(
        "Category list",
        "is collapsed",
        true
    );

    articlePage.clickCategoryButton();

    boolean result = !articlePage.isChevronCollapsed();
    PageObjectLogging.log(
        "Category list",
        "is expanded",
        "is collapsed",
        result
    );

    articlePage.clickOnCategoryListElement(0);
    new Wait(driver).forElementVisible(WIKIA_MOBILE_WIKI_TITLE);

    result = articlePage.isUrlContainingCategoryPage();
    PageObjectLogging.log(
        "Url",
        "match pattern /wiki/Category:",
        "does not match pattern /wiki/Category:",
        result
    );
  }

  @Test(groups = "mercury-article-articleWithColonAndQuestionMarkOpens")
  public void articleWithColonAndQuestionMarkOpens() {
    init();
    ArticlePageObject article = new ArticlePageObject(driver);

    String encodedQuestionMarkUrl = "/wiki/Question%3Fmark%3Fquestion";
    String encodedColonUrl = "/wiki/Colon%3Acolon%3Acolon";

    PageObjectLogging.logWarning(
        "Info",
        "Accessing article directly through URL"
    );

    navigate.toPage(encodedColonUrl);

    boolean result = driver.getCurrentUrl().contains(encodedColonUrl);
    PageObjectLogging.log(
        "URL for colon",
        "is encoded",
        "is not encoded",
        result
    );

    result = MercurySubpages.COLON.toLowerCase().contains(article.getArticleTitle().toLowerCase());
    PageObjectLogging.log(
        "Article title for colon",
        "is correct",
        "is not correct",
        result
    );

    navigate.toPage(encodedQuestionMarkUrl);

    result = driver.getCurrentUrl().contains(encodedQuestionMarkUrl);
    PageObjectLogging.log(
        "URL for question mark",
        "is encoded",
        "is not encoded",
        result
    );

    result = MercurySubpages.QUESTION_MARK.toLowerCase()
        .contains(article.getArticleTitle().toLowerCase());
    PageObjectLogging.log(
        "Article title for question mark",
        "is correct",
        "is not correct",
        result
    );

    PageObjectLogging.logWarning(
        "Info",
        "Accessing article through link in content"
    );

    article.clickOnAnchorInContent(0);
    loading.handleAsyncPageReload();

    result = !driver.getCurrentUrl().contains(encodedColonUrl);
    PageObjectLogging.log(
        "URL for colon",
        "is not encoded",
        "is encoded",
        result
    );

    result = MercurySubpages.COLON.toLowerCase().contains(article.getArticleTitle().toLowerCase());
    PageObjectLogging.log(
        "Article title for colon",
        "is correct",
        "is not correct",
        result
    );

    article.clickOnAnchorInContent(0);
    loading.handleAsyncPageReload();

    result = driver.getCurrentUrl().contains(encodedQuestionMarkUrl);
    PageObjectLogging.log(
        "URL for question mark",
        "is encoded",
        "is not encoded",
        result
    );

    result = MercurySubpages.QUESTION_MARK.toLowerCase().contains(
        article.getArticleTitle().toLowerCase());
    PageObjectLogging.log(
        "Article title for question mark",
        "is correct",
        "is not correct",
        result
    );

    PageObjectLogging.logWarning("Info", "Accessing article through link in navigation side");

    topBar.openNavigation();
    navigation.openSubMenu(1);
    navigation.openSubMenu(2);
    navigation.openPageLink(5);

    result = !driver.getCurrentUrl().contains(encodedColonUrl);
    PageObjectLogging.log(
        "URL for colon",
        "is not encoded",
        "is encoded",
        result
    );

    result = MercurySubpages.COLON.toLowerCase().contains(article.getArticleTitle().toLowerCase());
    PageObjectLogging.log(
        "Article title for colon",
        "is correct",
        "is not correct",
        result
    );

    topBar.openNavigation();
    navigation.openSubMenu(1);
    navigation.openSubMenu(2);
    navigation.openPageLink(4);

    result = driver.getCurrentUrl().contains(encodedQuestionMarkUrl);
    PageObjectLogging.log(
        "URL for question mark",
        "is encoded",
        "is not encoded",
        result
    );

    result = MercurySubpages.QUESTION_MARK.toLowerCase().contains(
        article.getArticleTitle().toLowerCase());
    PageObjectLogging.log(
        "Article title for question mark",
        "is correct",
        "is not correct",
        result
    );

    PageObjectLogging.logWarning("Info", "Accessing article through link in search result");

    topBar.openNavigation();
    navigation.navigateToPage(MercurySubpages.COLON.substring(6));

    result = driver.getCurrentUrl().contains(encodedColonUrl);
    PageObjectLogging.log(
        "URL for colon",
        "is encoded",
        "is not encoded",
        result
    );

    result = MercurySubpages.COLON.toLowerCase().contains(article.getArticleTitle().toLowerCase());
    PageObjectLogging.log(
        "Article title for colon",
        "is correct",
        "is not correct",
        result
    );

    topBar.openNavigation();
    navigation.navigateToPage(MercurySubpages.QUESTION_MARK.substring(6));

    result = driver.getCurrentUrl().contains(encodedQuestionMarkUrl);
    PageObjectLogging.log(
        "URL for question mark",
        "is encoded",
        "is not encoded",
        result
    );

    result = MercurySubpages.QUESTION_MARK.toLowerCase().contains(
        article.getArticleTitle().toLowerCase());
    PageObjectLogging.log(
        "Article title for question mark",
        "is correct",
        "is not correct",
        result
    );
  }
}
