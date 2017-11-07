package com.wikia.webdriver.testcases.mcfootertests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.pages.discussions.DiscussionsPage;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mcfooter.DiscussionCard;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mcfooter.MixedContentFooter;
import com.wikia.webdriver.pageobjectsfactory.pageobject.UserProfilePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.testng.annotations.Test;

@Test(groups = {"NonEnAnonMixedContentFooter"})
@Execute(onWikia = "de.gta", asUser = User.ANONYMOUS)
public class NonEnAnonMixedContentFooterTests extends NewTestTemplate{

  @Test
  public void isMCFooterPresentOnNonENwiki() {
    MixedContentFooter mcFooter = new MixedContentFooter()
        .openWikiMainPage()
        .scrollToMCFooter();

    Assertion.assertTrue(mcFooter.isMCFooterPresent());
  }

  @Test
  public void exploreWikisCardIsPresentOnNonENwiki(){
    MixedContentFooter mcFooter = new MixedContentFooter()
        .openWikiMainPage()
        .scrollToMCFooter();

    Assertion.assertTrue(mcFooter.isExploreWikisCardPresent());
  }

  @Test
  public void discussionsCardIsPresentOnNonENwiki(){
    DiscussionCard discussionCard = new MixedContentFooter()
        .openWikiMainPage()
        .scrollToMCFooter()
        .getDiscussionsCard();

    Assertion.assertTrue(discussionCard.isDiscussionsCardPresent());
  }

  @Test
  @Execute(onWikia = "es.nonenwikiwithemptydiscussions")
  public void discussionsCardIsPresentOnNonENwikiWithEmptyDiscussions(){
    DiscussionCard discussionsCard = new MixedContentFooter()
        .openWikiMainPage()
        .scrollToMCFooter()
        .getDiscussionsCard();

    Assertion.assertTrue(discussionsCard.isDiscussionsCardPresent());
  }

  @Test
  @Execute(onWikia = "es.nonenwikiwithoutdiscussions")
  public void discussionsCardIsNotPresentOnNonENwikiWithoutDiscussions(){
    DiscussionCard discussionCard = new MixedContentFooter()
        .openWikiMainPage()
        .scrollToMCFooter()
        .getDiscussionsCard();

    Assertion.assertTrue(discussionCard.isDiscussionsCardNotPresent());
  }

  @Test
  public void moreOfWikiArticlesCardIsPresentOnNonENwiki() {
    MixedContentFooter mcFooter = new MixedContentFooter()
        .openWikiMainPage()
        .scrollToMCFooter();

    Assertion.assertTrue(mcFooter.isMoreOfWikiArticlesCardPresent());
  }

  @Test
  public void countNoOfArticlesInMCFooterWithDiscussionsAndWithMoreOfWikiArticles(){
    MixedContentFooter mcFooter = new MixedContentFooter()
        .openWikiMainPage()
        .scrollToMCFooter();

    Assertion.assertEquals(mcFooter.countArticleCards(), 11);
  }

  @Test
  @Execute(onWikia = "es.gta")
  public void countNoOfArticlesInMCFooterWithoutDiscussionsAndWithMoreOfWikiArticles(){
    MixedContentFooter mcFooter = new MixedContentFooter()
        .openWikiMainPage()
        .scrollToMCFooter();

    Assertion.assertEquals(mcFooter.countArticleCards(), 13);
  }

  @Test
  @Execute(onWikia = "es.nonenwikiwithemptydiscussions")
  public void countNoOfArticlesInMCFooterWithDiscussionsAndWithoutMoreOfWikiArticles(){
    MixedContentFooter mcFooter = new MixedContentFooter()
        .openWikiMainPage()
        .scrollToMCFooter();

    Assertion.assertEquals(mcFooter.countArticleCards(), 12);
  }

  @Test
  public void countNoOfArticlesInExploreCard() {
    MixedContentFooter mcFooter = new MixedContentFooter()
        .openWikiMainPage()
        .scrollToMCFooter();

    Assertion.assertEquals(mcFooter.countArticlesInExploreCard(), 3);
  }

  @Test void isUserTakenToDiscussionsAfterClickOnViewAll() {
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
    Assertion.assertEquals(discussionsPage.getUrl(), mcFooter.getCurrentUrl() );
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
  public void userIsTakenToWikiArticleAfterClickOnWikiArticleCard() {
    ArticlePageObject article = new MixedContentFooter()
        .openWikiMainPage()
        .scrollToMCFooter()
        .clickWikiArticlecard();

    article.waitForPageLoad();

    String url = driver.getCurrentUrl();
    // TODO: IMO this test will pass even if link does not work
    Assertion.assertTrue(url.contains(".wikia.com/wiki/"));
  }

  @Test
  public void userIsTakenToWikiArticleWithVideoAfterClickOnWikiVideoCard() {
    ArticlePageObject article = new MixedContentFooter()
        .openWikiMainPage()
        .scrollToMCFooter()
        .clickWikiVideoCard();

    article.waitForPageLoad();

    String url = driver.getCurrentUrl();
    // TODO: IMO this assertion will pass even if link does not work
    Assertion.assertTrue(url.contains(".wikia.com/wiki/"));

    ArticlePageObject video = new ArticlePageObject();
    Assertion.assertTrue(video.isFeaturedVideo());
  }

}
