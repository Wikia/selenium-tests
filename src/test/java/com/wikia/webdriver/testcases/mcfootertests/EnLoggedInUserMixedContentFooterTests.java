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

@Test(groups = {"EnLoggedInUserMixedContentFooter"})
@Execute(onWikia = "gameofthrones", asUser = User.USER)
public class EnLoggedInUserMixedContentFooterTests extends NewTestTemplate {

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
    DiscussionCard discussionCard = new MixedContentFooter()
        .openWikiMainPage()
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
    DiscussionCard discussionCard = new MixedContentFooter()
        .openWikiMainPage()
        .scrollToMCFooter()
        .getDiscussionsCard();

    String username = discussionCard.getUsername()
        .replaceAll(" •.*$", "");

    UserProfilePage userProfilePage = discussionCard.clickUserAvatar();
    Assertion.assertEquals(userProfilePage.getUserName(), username);
  }

  @Test
  public void userIsTakenToDiscussionsPostViewAfterClickOnPost() {
    MixedContentFooter mcFooter = new MixedContentFooter()
        .openWikiMainPage()
        .scrollToMCFooter();

    DiscussionsPage discussionsPage = mcFooter
        .getDiscussionsCard()
        .clickDiscussionsPost();

    // TODO: IMO this test will always pass
    Assertion.assertEquals(discussionsPage.getUrl(), mcFooter.getCurrentUrl());
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
    new MixedContentFooter()
        .openWikiMainPage()
        .scrollToMCFooter()
        .clickWikiArticlecard()
        .waitForPageLoad();

    String url = driver.getCurrentUrl();

    // TODO: this test will pass even if the link in the card does not work
    Assertion.assertTrue(url.contains(".wikia.com/wiki/"));
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
    ArticlePageObject article = new MixedContentFooter()
        .openWikiMainPage()
        .scrollToMCFooter()
        .clickWikiVideoCard();

    article.waitForPageLoad();

    String url = driver.getCurrentUrl();
    // TODO: This assertion is pointless as it will pass even if the link does not work
    Assertion.assertTrue(url.contains(".wikia.com/wiki/"));

    Assertion.assertTrue(article.isFeaturedVideo());
  }

}
