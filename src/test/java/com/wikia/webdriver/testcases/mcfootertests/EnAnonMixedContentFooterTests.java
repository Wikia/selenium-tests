package com.wikia.webdriver.testcases.mcfootertests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.pages.discussions.DiscussionsPage;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mcfooter.DiscussionCard;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mcfooter.MixedContentFooter;
import com.wikia.webdriver.pageobjectsfactory.pageobject.FandomPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.UserProfilePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.testng.annotations.Test;

@Test(groups = {"EnAnonMixedContentFooter"})
@Execute(onWikia = "serowiec", asUser = User.ANONYMOUS)
public class EnAnonMixedContentFooterTests extends NewTestTemplate {

  @Test
  public void mcFooterIsPresentOnENwiki() {
    MixedContentFooter mcFooter = new MixedContentFooter()
        .openWikiMainPage()
        .scrollToMCFooter();

    Assertion.assertTrue(mcFooter.isMCFooterPresent());
  }

  @Test
  public void exploreWikisCardIsPresentOnENwiki() {
    MixedContentFooter mcFooter = new MixedContentFooter()
        .openWikiMainPage()
        .scrollToMCFooter();

    Assertion.assertTrue(mcFooter.isExploreWikisCardPresent());
  }

  @Test
  public void discussionsCardIsPresentOnENwikiWithDiscussions() {
    DiscussionCard discussionCard = new MixedContentFooter()
        .openWikiMainPage()
        .scrollToMCFooter()
        .getDiscussionsCard();

    Assertion.assertTrue(discussionCard.isDiscussionsCardPresent());
  }

  @Test
  @Execute(onWikia = "mcfwithoutmoreofwikiarticles")
  public void discussionsCardIsPresentOnENwikiWithEmptyDiscussions() {
    DiscussionCard discussionCard = new MixedContentFooter()
        .openWikiMainPage()
        .scrollToMCFooter()
        .getDiscussionsCard();

    Assertion.assertTrue(discussionCard.isDiscussionsCardPresent());
  }

  @Test
  @Execute(onWikia = "enwikiwithoutdiscussions")
  public void discussionsCardIsNotPresentOnENwikiWithoutDiscussions() {
    DiscussionCard discussionCard = new MixedContentFooter().
        openWikiMainPage()
        .scrollToMCFooter()
        .getDiscussionsCard();

    Assertion.assertTrue(discussionCard.isDiscussionsCardNotPresent());
  }

  @Test
  public void moreOfWikiArticlesCardIsPresentOnENwiki() {
    MixedContentFooter mcFooter = new MixedContentFooter()
        .openWikiMainPage()
        .scrollToMCFooter();

    Assertion.assertTrue(mcFooter.isMoreOfWikiArticlesCardPresent());
  }

  @Test
  public void countNoOfArticlesInMCFooterWithDiscussionsAndWithMoreOfWikiArticles() {
    MixedContentFooter mcFooter = new MixedContentFooter()
        .openWikiMainPage()
        .scrollToMCFooter();

    Assertion.assertEquals(mcFooter.countArticleCards(), 17);
  }

  @Test
  @Execute(onWikia = "disney")
  //SHOULD BE RUN AT enwikiwithoutdiscussions.wikia.com ONCE 'More of..' will appear on this wiki
  public void countNoOfArticlesInMCFooterWithoutDiscussionsAndWithMoreOfWikiArticles() {
    MixedContentFooter mcFooter = new MixedContentFooter()
        .openWikiMainPage()
        .scrollToMCFooter();

    Assertion.assertEquals(mcFooter.countArticleCards(), 19);
  }

  @Test
  @Execute(onWikia = "mcfwithoutmoreofwikiarticles")
  public void countNoOfArticlesInMCFooterWithDiscussionsAndWithoutMoreOfWikiArticles() {
    MixedContentFooter mcFooter = new MixedContentFooter()
        .openWikiMainPage()
        .scrollToMCFooter();

    Assertion.assertEquals(mcFooter.countArticleCards(), 18);
  }

  @Test
  public void countNoOfArticlesInExploreCard() {
    MixedContentFooter mcFooter = new MixedContentFooter()
        .openWikiMainPage()
        .scrollToMCFooter();

    Assertion.assertEquals(mcFooter.countArticlesInExploreCard(), 3);
  }

  @Test
  public void userIsTakenToDiscussionsAfterClickOnViewAll() {
    DiscussionsPage discussions = new MixedContentFooter()
        .openWikiMainPage()
        .scrollToMCFooter()
        .getDiscussionsCard()
        .clickOnViewAllLinkInDiscussions();

    Assertion.assertTrue(discussions.isDiscussions());
  }

  @Test
  public void userIsTakenToUserProfileAfterClickOnAvatar() {
    DiscussionCard discussions = new MixedContentFooter()
        .openWikiMainPage()
        .scrollToMCFooter()
        .getDiscussionsCard();

    String username = discussions.getUsername()
        .replaceAll(" •.*$", "");

    UserProfilePage profilePage = discussions.clickUserAvatar();

    Assertion.assertEquals(profilePage.getUserName(), username);
  }

  @Test
  public void userIsTakenToDiscussionsPostViewAfterClickOnPost() {
    new MixedContentFooter().openWikiMainPage()
        .scrollToMCFooter()
        .getDiscussionsCard()
        .clickDiscussionsPost();

    String url = driver.getCurrentUrl();
    Assertion.assertTrue(url.contains(".wikia.com/d/"));
  }

  @Test
  @Execute(onWikia = "enwikiwithemptydiscussions")
  public void zeroStateAppearsInDiscussionsWithoutPosts() {
    DiscussionCard discussionCard = new MixedContentFooter()
        .openWikiMainPage()
        .scrollToMCFooter()
        .getDiscussionsCard();

    Assertion.assertTrue(discussionCard.isZeroState());
  }

  @Test
  public void userIsTakenToFandomPageAfterClickOnFandomArticleCard() {
    new MixedContentFooter()
        .openWikiMainPage()
        .scrollToMCFooter()
        .clickFanomArticleCard()
        .waitForPageLoad();

    String url = driver.getCurrentUrl();
    Assertion.assertTrue(url.contains("fandom.wikia.com/articles/"));
  }

  @Test
  public void userIsTakenToWikiArticleAfterClickOnWikiArticleCard() {
    MixedContentFooter mcf = new MixedContentFooter()
        .openWikiMainPage();

    String urlMainPage = driver.getCurrentUrl();

    mcf.scrollToMCFooter()
        .clickWikiArticlecard()
        .waitForPageLoad();

    String urlArticle = driver.getCurrentUrl();

    Assertion.assertNotEquals(urlMainPage, urlArticle);
  }

  @Test
  public void userIsTakenToFandomArticleWithVideoAfterClickOnFandomVideoCard() {
    FandomPageObject fandomPage = new MixedContentFooter()
        .openWikiMainPage()
        .scrollToMCFooter()
        .clickFanomVideoCard();

    fandomPage.waitForPageLoad();

    String url = driver.getCurrentUrl();
    Assertion.assertTrue(url.contains("fandom.wikia.com/articles/"));

    Assertion.assertTrue(fandomPage.isFeaturedVideo());
  }

  @Test
  public void userIsTakenToWikiArticleWithVideoAfterClickOnWikiVideoCard() {
    MixedContentFooter mcf = new MixedContentFooter()
        .openWikiMainPage();

    String urlMainPage = driver.getCurrentUrl();

    ArticlePageObject article = mcf.scrollToMCFooter()
        .clickWikiVideoCard();

    article.waitForPageLoad();

    String urlArticlePage = driver.getCurrentUrl();
    Assertion.assertNotEquals(urlMainPage, urlArticlePage);
  }

}
