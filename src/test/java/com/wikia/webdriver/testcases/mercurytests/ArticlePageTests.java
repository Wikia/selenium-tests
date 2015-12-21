package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.helpers.Browser;
import com.wikia.webdriver.common.core.helpers.Device;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.NavigationSideComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.ArticlePageObject;

import org.openqa.selenium.WebDriverException;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Execute(
    onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING,
    onDevice = Device.GOOGLE_NEXUS_5,
    browser = Browser.CHROME
)
public class ArticlePageTests extends NewTestTemplate {

  private static final String[]
      FOOTER_ELEMENTS =
      {"Games", "Movies", "TV", "Comics", "Music", "Books", "Lifestyle", "Full site", "Licensing",
       "Privacy Policy", "Feedback"};

  @Test(groups = "MercuryArticleTest_001")
  public void MercuryArticleTest_001_Logo_Search_TopContributors_FooterElements() {
    ArticlePageObject articlePage = new ArticlePageObject(driver);
    articlePage.openMercuryArticleByName(wikiURL, MercurySubpages.MAIN_PAGE);

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

  @Test(groups = "MercuryArticleTest_002")
  public void MercuryArticleTest_002_TapContributorRedirectToUserPage() {
    ArticlePageObject articlePage = new ArticlePageObject(driver);
    articlePage.openArticleOnWikiByNameWithCbAndNoAds(wikiURL, MercurySubpages.MAIN_PAGE);

    articlePage.clickTopContributor(0);

    boolean result = articlePage.isUrlContainingUserPage();
    PageObjectLogging.log(
        "Url",
        "match pattern /wiki/User:",
        "does not match pattern /wiki/User:",
        result
    );
  }

  @Test(groups = "MercuryArticleTest_003")
  public void MercuryArticleTest_003_SingleLinkedImageRedirect() {
    ArticlePageObject articlePage = new ArticlePageObject(driver);
    articlePage.openMercuryArticleByName(wikiURL, MercurySubpages.LINKED_IMAGES);

    String oldUrl = driver.getCurrentUrl();
    articlePage.clickOnImage(0);
    articlePage.waitForLoadingOverlayToDisappear();

    boolean result = !driver.getCurrentUrl().equals(oldUrl);
    PageObjectLogging.log(
        "Redirection",
        "works",
        "does not work",
        result
    );
  }

  @Test(groups = "MercuryArticleTest_004")
  public void MercuryArticleTest_004_CategoryListCollapsed_CategoryListExpanded() {
    ArticlePageObject articlePage = new ArticlePageObject(driver);
    articlePage.openArticleOnWikiByNameWithCbAndNoAds(wikiURL, MercurySubpages.MAIN_PAGE);

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
    articlePage.waitForWikiaMobileToBeLoaded();

    result = articlePage.isUrlContainingCategoryPage();
    PageObjectLogging.log(
        "Url",
        "match pattern /wiki/Category:",
        "does not match pattern /wiki/Category:",
        result
    );
  }

  @Test(groups = "MercuryArticleTest_005")
  @RelatedIssue(issueID = "XW-659")
  public void MercuryArticleTest_005_NavigateToArticleWithColonAndQuestionMark() {
    ArticlePageObject article = new ArticlePageObject(driver);
    NavigationSideComponentObject nav = new NavigationSideComponentObject(driver);

    String encodedQuestionMarkUrl, encodedColonUrl;

    try {
      encodedColonUrl = URLEncoder.encode(MercurySubpages.COLON, "UTF-8");
      encodedQuestionMarkUrl = URLEncoder.encode(MercurySubpages.QUESTION_MARK, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      throw new WebDriverException("Wrong URL encoding");
    }

    PageObjectLogging.logWarning(
        "Info",
        "Accessing article directly through URL"
    );

    article.openMercuryArticleByName(wikiURL, encodedColonUrl);

    boolean result = driver.getCurrentUrl().contains(encodedColonUrl);
    PageObjectLogging.log(
        "URL for colon",
        "is encoded",
        "is not encoded",
        result
    );

    result = article.getArticleTitle().toLowerCase().equals(MercurySubpages.COLON.toLowerCase());
    PageObjectLogging.log(
        "Article title for colon",
        "is correct",
        "is not correct",
        result
    );

    article.openMercuryArticleByName(wikiURL, encodedQuestionMarkUrl);

    result = driver.getCurrentUrl().contains(encodedQuestionMarkUrl);
    PageObjectLogging.log(
        "URL for question mark",
        "is encoded",
        "is not encoded",
        result
    );

    result =
        article.getArticleTitle().toLowerCase().equals(MercurySubpages.QUESTION_MARK.toLowerCase());
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
    article.waitForLoadingOverlayToDisappear();

    result = !driver.getCurrentUrl().contains(encodedColonUrl);
    PageObjectLogging.log(
        "URL for colon",
        "is not encoded",
        "is encoded",
        result
    );

    result = article.getArticleTitle().toLowerCase().equals(MercurySubpages.COLON.toLowerCase());
    PageObjectLogging.log(
        "Article title for colon",
        "is correct",
        "is not correct",
        result
    );

    article.clickOnAnchorInContent(0);
    article.waitForLoadingOverlayToDisappear();

    result = driver.getCurrentUrl().contains(encodedQuestionMarkUrl);
    PageObjectLogging.log(
        "URL for question mark",
        "is encoded",
        "is not encoded",
        result
    );

    result =
        article.getArticleTitle().toLowerCase().equals(MercurySubpages.QUESTION_MARK.toLowerCase());
    PageObjectLogging.log(
        "Article title for question mark",
        "is correct",
        "is not correct",
        result
    );

    PageObjectLogging.logWarning("Info", "Accessing article through link in navigation side");

    nav.clickSearchButton();
    nav.clickNavListElement(3);
    nav.clickNavListElement(6);
    article.waitForLoadingOverlayToDisappear();

    result = !driver.getCurrentUrl().contains(encodedColonUrl);
    PageObjectLogging.log(
        "URL for colon",
        "is not encoded",
        "is encoded",
        result
    );

    result = article.getArticleTitle().toLowerCase().equals(MercurySubpages.COLON.toLowerCase());
    PageObjectLogging.log(
        "Article title for colon",
        "is correct",
        "is not correct",
        result
    );

    nav.clickSearchButton();
    nav.clickNavListElement(3);
    nav.clickNavListElement(5);
    article.waitForLoadingOverlayToDisappear();

    result = driver.getCurrentUrl().contains(encodedQuestionMarkUrl);
    PageObjectLogging.log(
        "URL for question mark",
        "is encoded",
        "is not encoded",
        result
    );

    result =
        article.getArticleTitle().toLowerCase().equals(MercurySubpages.QUESTION_MARK.toLowerCase());
    PageObjectLogging.log(
        "Article title for question mark",
        "is correct",
        "is not correct",
        result
    );

    PageObjectLogging.logWarning("Info", "Accessing article through link in search result");

    nav.clickSearchButton();
    nav.clickSearchField();
    nav.typeInSearchField(MercurySubpages.COLON.substring(0, 4));
    nav.clickSuggestion(0);
    article.waitForLoadingOverlayToDisappear();

    result = driver.getCurrentUrl().contains(encodedColonUrl);
    PageObjectLogging.log(
        "URL for colon",
        "is encoded",
        "is not encoded",
        result
    );

    result = article.getArticleTitle().toLowerCase().equals(MercurySubpages.COLON.toLowerCase());
    PageObjectLogging.log(
        "Article title for colon",
        "is correct",
        "is not correct",
        result
    );

    nav.clickSearchButton();
    nav.clickSearchField();
    nav.typeInSearchField(MercurySubpages.QUESTION_MARK.substring(0, 4));
    nav.clickSuggestion(0);
    article.waitForLoadingOverlayToDisappear();

    result = driver.getCurrentUrl().contains(encodedQuestionMarkUrl);
    PageObjectLogging.log(
        "URL for question mark",
        "is encoded",
        "is not encoded",
        result
    );

    result =
        article.getArticleTitle().toLowerCase().equals(MercurySubpages.QUESTION_MARK.toLowerCase());
    PageObjectLogging.log(
        "Article title for question mark",
        "is correct",
        "is not correct",
        result
    );
  }
}
