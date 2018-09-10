package com.wikia.webdriver.testcases.desktop.mcfootertests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.DontRun;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RunOnly;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.communities.mobile.pages.discussions.DiscussionsPage;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mcfooter.DiscussionCard;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mcfooter.MixedContentFooter;
import com.wikia.webdriver.pageobjectsfactory.pageobject.UserProfilePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.testng.annotations.Test;

@Test(groups = {"NonEnAnonMixedContentFooter"})
@Execute(onWikia = "gameofthrones", language = "de", asUser = User.ANONYMOUS)
public class NonEnAnonMixedContentFooterTests extends NewTestTemplate {

  @DontRun(language = "szl")
  @Test
  public void isMCFooterPresentOnNonENwiki() {
    MixedContentFooter mcFooter = new MixedContentFooter().openWikiMainPage().scrollToMCFooter();

    Assertion.assertTrue(mcFooter.isMCFooterPresent());
  }

  @RunOnly(language = "szl")
  @Execute(onWikia = "mediawiki119")
  @Test
  public void isMCFooterPresentOnNonENwikiSzl() {
    MixedContentFooter mcFooter = new MixedContentFooter().openWikiMainPage().scrollToMCFooter();

    Assertion.assertTrue(mcFooter.isMCFooterPresent());
  }

  @DontRun(language = "szl")
  @Test
  public void exploreWikisCardIsPresentOnNonENwiki() {
    MixedContentFooter mcFooter = new MixedContentFooter().openWikiMainPage().scrollToMCFooter();

    Assertion.assertTrue(mcFooter.isExploreWikisCardPresent());
  }

  @RunOnly(language = "szl")
  @Execute(onWikia = "mediawiki119")
  @Test
  public void exploreWikisCardIsPresentOnNonENwikiSzl() {
    MixedContentFooter mcFooter = new MixedContentFooter().openWikiMainPage().scrollToMCFooter();

    Assertion.assertTrue(mcFooter.isExploreWikisCardPresent());
  }

  @DontRun(language = "szl")
  @Test
  public void discussionsCardIsPresentOnNonENwiki() {
    DiscussionCard discussionCard = new MixedContentFooter().openWikiMainPage()
        .scrollToMCFooter()
        .getDiscussionsCard();

    Assertion.assertTrue(discussionCard.isDiscussionsCardPresent());
  }

  @RunOnly(language = "szl")
  @Execute(onWikia = "mediawiki119")
  @Test
  public void discussionsCardIsPresentOnNonENwikiSzl() {
    DiscussionCard discussionCard = new MixedContentFooter().openWikiMainPage()
        .scrollToMCFooter()
        .getDiscussionsCard();

    Assertion.assertTrue(discussionCard.isDiscussionsCardPresent());
  }

  @DontRun(language = "szl")
  @Test
  @Execute(onWikia = "nonenwikiwithemptydiscussions", language = "es")
  public void discussionsCardIsPresentOnNonENwikiWithEmptyDiscussions() {
    DiscussionCard discussionsCard = new MixedContentFooter().openWikiMainPage()
        .scrollToMCFooter()
        .getDiscussionsCard();

    Assertion.assertTrue(discussionsCard.isDiscussionsCardPresent());
  }

  @RunOnly(language = "szl")
  @Test
  @Execute(onWikia = "qatestdiscussionsnoforum")
  public void discussionsCardIsPresentOnNonENwikiWithEmptyDiscussionsSzl() {
    DiscussionCard discussionsCard = new MixedContentFooter().openWikiMainPage()
        .scrollToMCFooter()
        .getDiscussionsCard();

    Assertion.assertTrue(discussionsCard.isDiscussionsCardPresent());
  }

  @DontRun(language = "szl")
  @Test
  @Execute(onWikia = "nonenwikiwithoutdiscussions", language = "es")
  public void discussionsCardIsNotPresentOnNonENwikiWithoutDiscussions() {
    DiscussionCard discussionCard = new MixedContentFooter().openWikiMainPage()
        .scrollToMCFooter()
        .getDiscussionsCard();

    Assertion.assertTrue(discussionCard.isDiscussionsCardNotPresent());
  }

  @RunOnly(language = "szl")
  @Test
  @Execute(onWikia = "qatestnodiscussionsnoforum")
  public void discussionsCardIsNotPresentOnNonENwikiWithoutDiscussionsSzl() {
    DiscussionCard discussionCard = new MixedContentFooter().openWikiMainPage()
        .scrollToMCFooter()
        .getDiscussionsCard();

    Assertion.assertTrue(discussionCard.isDiscussionsCardNotPresent());
  }

  @DontRun(language = "szl")
  @Test
  public void moreOfWikiArticlesCardIsPresentOnNonENwiki() {
    MixedContentFooter mcFooter = new MixedContentFooter().openWikiMainPage().scrollToMCFooter();

    Assertion.assertTrue(mcFooter.isMoreOfWikiArticlesCardPresent());
  }

  @DontRun(language = "szl")
  @Test
  public void countNoOfArticlesInMCFooterWithDiscussionsAndWithMoreOfWikiArticles() {
    MixedContentFooter mcFooter = new MixedContentFooter().openWikiMainPage().scrollToMCFooter();

    Assertion.assertEquals(mcFooter.countArticleCards(), 11);
  }

  @RunOnly(language = "szl")
  @Execute(onWikia = "mediawiki119")
  @Test
  public void countNoOfArticlesInMCFooterWithDiscussionsAndWithMoreOfWikiArticlesSzl() {
    MixedContentFooter mcFooter = new MixedContentFooter().openWikiMainPage().scrollToMCFooter();

    Assertion.assertEquals(mcFooter.countArticleCards(), 12);
  }

  @DontRun(language = "szl")
  @Test
  @Execute(onWikia = "gta", language = "es")
  public void countNoOfArticlesInMCFooterWithoutDiscussionsAndWithMoreOfWikiArticles() {
    MixedContentFooter mcFooter = new MixedContentFooter().openWikiMainPage().scrollToMCFooter();

    Assertion.assertEquals(mcFooter.countArticleCards(), 13);
  }

  @RunOnly(language = "szl")
  @Test
  @Execute(onWikia = "qatestnodiscussionsnoforum")
  public void countNoOfArticlesInMCFooterWithoutDiscussionsAndWithMoreOfWikiArticlesSzl() {
    MixedContentFooter mcFooter = new MixedContentFooter().openWikiMainPage().scrollToMCFooter();

    Assertion.assertEquals(mcFooter.countArticleCards(), 14);
  }

  @DontRun(language = "szl")
  @Test
  @Execute(onWikia = "nonenwikiwithemptydiscussions", language = "es")
  public void countNoOfArticlesInMCFooterWithDiscussionsAndWithoutMoreOfWikiArticles() {
    MixedContentFooter mcFooter = new MixedContentFooter().openWikiMainPage().scrollToMCFooter();

    Assertion.assertEquals(mcFooter.countArticleCards(), 12);
  }

  @RunOnly(language = "szl")
  @Test
  @Execute(onWikia = "mediawiki119")
  public void countNoOfArticlesInMCFooterWithDiscussionsAndWithoutMoreOfWikiArticlesSzl() {
    MixedContentFooter mcFooter = new MixedContentFooter().openWikiMainPage().scrollToMCFooter();

    Assertion.assertEquals(mcFooter.countArticleCards(), 12);
  }

  @DontRun(language = "szl")
  @Test
  public void countNoOfArticlesInExploreCard() {
    MixedContentFooter mcFooter = new MixedContentFooter().openWikiMainPage().scrollToMCFooter();

    Assertion.assertEquals(mcFooter.countArticlesInExploreCard(), 3);
  }

  @RunOnly(language = "szl")
  @Execute(onWikia = "mediawiki119")
  @Test
  public void countNoOfArticlesInExploreCardSzl() {
    MixedContentFooter mcFooter = new MixedContentFooter().openWikiMainPage().scrollToMCFooter();

    Assertion.assertEquals(mcFooter.countArticlesInExploreCard(), 3);
  }

  @DontRun(language = "szl")
  @Test
  void isUserTakenToDiscussionsAfterClickOnViewAll() {
    DiscussionsPage discussions = new MixedContentFooter().openWikiMainPage()
        .scrollToMCFooter()
        .getDiscussionsCard()
        .scrollToDiscussions()
        .clickOnViewAllLinkInDiscussions();

    Assertion.assertTrue(discussions.isDiscussionsPresent());
  }

  @RunOnly(language = "szl")
  @Test
  void isUserTakenToDiscussionsAfterClickOnViewAllSzl() {
    DiscussionsPage discussions = new MixedContentFooter().openWikiMainPage()
        .scrollToMCFooter()
        .getDiscussionsCard()
        .scrollToDiscussions()
        .clickOnViewAllLinkInDiscussions();

    Assertion.assertTrue(discussions.isDiscussionsPresent());
  }

  @DontRun(language = "szl")
  @Test
  public void userIsTakenToUserProfileAfterClickOnAvatar() {
    DiscussionCard discussionCard = new MixedContentFooter().openWikiMainPage()
        .scrollToMCFooter()
        .getDiscussionsCard()
        .scrollToDiscussions();

    String username = discussionCard.getUsername().replaceAll(" •.*$", "");

    UserProfilePage userProfilePage = discussionCard.clickUserAvatar();

    Assertion.assertEquals(userProfilePage.getUserName(), username);
  }

  @RunOnly(language = "szl")
  @Execute(onWikia = "mediawiki119")
  @Test
  public void userIsTakenToUserProfileAfterClickOnAvatarSzl() {
    DiscussionCard discussionCard = new MixedContentFooter().openWikiMainPage()
        .scrollToMCFooter()
        .getDiscussionsCard()
        .scrollToDiscussions();

    String username = discussionCard.getUsername().replaceAll(" •.*$", "");

    UserProfilePage userProfilePage = discussionCard.clickUserAvatar();

    Assertion.assertEquals(userProfilePage.getUserName(), username);
  }

  @DontRun(language = "szl")
  @Test
  public void userIsTakenToDiscussionsPostViewAfterClickOnPost() {
    new MixedContentFooter().openWikiMainPage()
        .scrollToMCFooter()
        .getDiscussionsCard()
        .scrollToDiscussions()
        .clickDiscussionsPost();

    String url = driver.getCurrentUrl();
    Assertion.assertTrue(url.contains(".wikia.com/d/"));
  }

  @RunOnly(language = "szl")
  @Execute(onWikia = "mediawiki119")
  @Test
  public void userIsTakenToDiscussionsPostViewAfterClickOnPostSzl() {
    new MixedContentFooter().openWikiMainPage()
        .scrollToMCFooter()
        .getDiscussionsCard()
        .scrollToDiscussions()
        .clickDiscussionsPost();

    Assertion.assertTrue(driver.getCurrentUrl().contains(".wikia.com/szl/d/"));
  }

  @DontRun(language = "szl")
  @Test
  @Execute(onWikia = "enwikiwithemptydiscussions")
  public void zeroStateAppearsInDiscussionsWithoutPosts() {
    DiscussionCard discussionCard = new MixedContentFooter().openWikiMainPage()
        .scrollToMCFooter()
        .getDiscussionsCard();

    Assertion.assertTrue(discussionCard.isZeroState());
  }

  @RunOnly(language = "szl")
  @Execute(onWikia = "qatestdiscussionsnoforum")
  @Test
  public void zeroStateAppearsInDiscussionsWithoutPostsSzl() {
    DiscussionCard discussionCard = new MixedContentFooter().openWikiMainPage()
        .scrollToMCFooter()
        .getDiscussionsCard();

    Assertion.assertTrue(discussionCard.isZeroState());
  }

  @DontRun(language = "szl")
  @Test
  public void userIsTakenToWikiArticleAfterClickOnWikiArticleCard() {
    MixedContentFooter mcf = new MixedContentFooter().openWikiMainPage();

    String urlMainPage = driver.getCurrentUrl();

    ArticlePageObject article = mcf.scrollToMCFooter().clickWikiArticlecard();

    article.waitForPageLoad();

    String urlArticlePage = mcf.getCurrentUrl();

    Assertion.assertNotEquals(urlMainPage, urlArticlePage);
  }

  @RunOnly(language = "szl")
  @Execute(onWikia = "mediawiki119")
  @Test
  public void userIsTakenToWikiArticleAfterClickOnWikiArticleCardSzl() {
    MixedContentFooter mcf = new MixedContentFooter().openWikiMainPage();

    String urlMainPage = driver.getCurrentUrl();

    ArticlePageObject article = mcf.scrollToMCFooter().clickWikiArticlecard();

    article.waitForPageLoad();

    String urlArticlePage = mcf.getCurrentUrl();

    Assertion.assertNotEquals(urlMainPage, urlArticlePage);
  }
}
