package com.wikia.webdriver.testcases.desktop.mcfootertests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mcfooter.DiscussionCard;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mcfooter.MixedContentFooter;
import com.wikia.webdriver.pageobjectsfactory.pageobject.UserProfilePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.testng.annotations.Test;

@Test(groups = {"NonEnAnonMixedContentFooter"})
@Execute(onWikia = "elderscrolls", language = "de", asUser = User.ANONYMOUS)
public class NonEnAnonMixedContentFooterTests extends NewTestTemplate {

  @Test
  public void isMCFooterPresentOnNonENwiki() {
    MixedContentFooter mcFooter = new MixedContentFooter().openWikiMainPage().scrollToMCFooter();

    Assertion.assertTrue(mcFooter.isMCFooterPresent());
  }

  @Test
  public void exploreWikisCardIsPresentOnNonENwiki() {
    MixedContentFooter mcFooter = new MixedContentFooter().openWikiMainPage().scrollToMCFooter();

    Assertion.assertTrue(mcFooter.isExploreWikisCardPresent());
  }

  @Test
  public void discussionsCardIsPresentOnNonENwiki() {
    DiscussionCard discussionCard = new MixedContentFooter().openWikiMainPage()
        .scrollToMCFooter()
        .getDiscussionsCard();

    Assertion.assertTrue(discussionCard.isDiscussionsCardPresent());
  }

  @Test
  @Execute(onWikia = "nonenwikiwithemptydiscussions", language = "es")
  public void discussionsCardIsPresentOnNonENwikiWithEmptyDiscussions() {
    DiscussionCard discussionsCard = new MixedContentFooter().openWikiMainPage()
        .scrollToMCFooter()
        .getDiscussionsCard();

    Assertion.assertTrue(discussionsCard.isDiscussionsCardPresent());
  }

  @Test
  @Execute(onWikia = "nonenwikiwithoutdiscussions", language = "es")
  public void discussionsCardIsNotPresentOnNonENwikiWithoutDiscussions() {
    DiscussionCard discussionCard = new MixedContentFooter().openWikiMainPage()
        .scrollToMCFooter()
        .getDiscussionsCard();

    Assertion.assertTrue(discussionCard.isDiscussionsCardNotPresent());
  }

  @Test
  public void moreOfWikiArticlesCardIsPresentOnNonENwiki() {
    MixedContentFooter mcFooter = new MixedContentFooter().openWikiMainPage().scrollToMCFooter();

    Assertion.assertTrue(mcFooter.isMoreOfWikiArticlesCardPresent());
  }

  @Test
  public void countNoOfArticlesInMCFooterWithDiscussionsAndWithMoreOfWikiArticles() {
    MixedContentFooter mcFooter = new MixedContentFooter().openWikiMainPage().scrollToMCFooter();

    Assertion.assertEquals(mcFooter.countArticleCards(), 11);
  }

  @Test
  @Execute(onWikia = "elderscrolls", language = "es")
  public void countNoOfArticlesInMCFooterWithoutDiscussionsAndWithMoreOfWikiArticles() {
    MixedContentFooter mcFooter = new MixedContentFooter().openWikiMainPage().scrollToMCFooter();

    Assertion.assertEquals(mcFooter.countArticleCards(), 13);
  }

  @Test
  @Execute(onWikia = "nonenwikiwithemptydiscussions", language = "es")
  public void countNoOfArticlesInMCFooterWithDiscussionsAndWithoutMoreOfWikiArticles() {
    MixedContentFooter mcFooter = new MixedContentFooter().openWikiMainPage().scrollToMCFooter();

    Assertion.assertEquals(mcFooter.countArticleCards(), 12);
  }

  @Test
  public void countNoOfArticlesInExploreCard() {
    MixedContentFooter mcFooter = new MixedContentFooter().openWikiMainPage().scrollToMCFooter();

    Assertion.assertEquals(mcFooter.countArticlesInExploreCard(), 3);
  }

  @Test
  void isUserTakenToDiscussionsAfterClickOnViewAll() {
    new MixedContentFooter().openWikiMainPage()
        .scrollToMCFooter()
        .getDiscussionsCard()
        .scrollToDiscussions()
        .clickOnViewAllLinkInDiscussions();

    Assertion.assertStringContains(driver.getCurrentUrl(), String.format("%s/%s/f", Configuration.getEnvType().getDomain(), Configuration.getWikiLanguage()));
  }

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

  @Test
  public void userIsTakenToDiscussionsPostViewAfterClickOnPost() {
    new MixedContentFooter().openWikiMainPage()
        .scrollToMCFooter()
        .getDiscussionsCard()
        .scrollToDiscussions()
        .clickDiscussionsPost();

    Assertion.assertStringContains(driver.getCurrentUrl(), String.format("%s/%s/f/p/", Configuration.getEnvType().getDomain(), Configuration.getWikiLanguage()));
  }

  @Test
  @Execute(onWikia = "nonenwikiwithemptydiscussions", language = "es")
  public void zeroStateAppearsInDiscussionsWithoutPosts() {
    DiscussionCard discussionCard = new MixedContentFooter().openWikiMainPage()
        .scrollToMCFooter()
        .getDiscussionsCard();

    Assertion.assertTrue(discussionCard.isZeroState());
  }

  @Test
  public void userIsTakenToWikiArticleAfterClickOnWikiArticleCard() {
    MixedContentFooter mcf = new MixedContentFooter().openWikiMainPage();

    String urlMainPage = driver.getCurrentUrl();

    ArticlePageObject article = mcf.scrollToMCFooter().clickWikiArticlecard();

    article.waitForPageLoad();

    String urlArticlePage = mcf.getCurrentUrl();

    Assertion.assertNotEquals(urlMainPage, urlArticlePage);
  }
}
