package com.wikia.webdriver.testcases.mcfootertests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.pages.discussions.DiscussionsPage;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mcfooter.DiscussionsCardInMcFooter;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mcfooter.MixedContentFooter;
import com.wikia.webdriver.pageobjectsfactory.pageobject.UserProfilePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.testng.annotations.Test;

@Test(groups = {"NonEnLoggedInUserMixedContentFooter"})
@Execute(onWikia = "de.gta", asUser = User.USER)
public class NonEnLoggedInUserMixedContentFooterTests extends NewTestTemplate{

  @Test
  public void isMCFooterPresentOnNonENwiki() {
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage().scrollToMCFooter();
    Assertion.assertTrue(mcFooter.isMCFooterPresent());
  }

  @Test
  public void exploreWikisCardIsPresentOnNonENwiki(){
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage().scrollToMCFooter();
    Assertion.assertTrue(mcFooter.isExploreWikisCardPresent());
  }

  @Test
  public void discussionsCardIsPresentOnNonENwiki(){
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage().scrollToMCFooter();
    Assertion.assertTrue(new DiscussionsCardInMcFooter().isDiscussionsCardPresent());
  }

  @Test
  @Execute(onWikia = "es.nonenwikiwithemptydiscussions")
  public void discussionsCardIsPresentOnNonENwikiWithEmptyDiscussions(){
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage().scrollToMCFooter();
    DiscussionsCardInMcFooter discussions = new DiscussionsCardInMcFooter();
    Assertion.assertTrue(discussions.isDiscussionsCardPresent());
  }

  @Test
  @Execute(onWikia = "es.nonenwikiwithoutdiscussions")
  public void discussionsCardIsNotPresentOnNonENwikiWithoutDiscussions(){
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage().scrollToMCFooter();
    Assertion.assertTrue(new DiscussionsCardInMcFooter().isDiscussionsCardNotPresent());
  }

  @Test
  public void moreOfWikiArticlesCardIsPresentOnNonENwiki() {
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage().scrollToMCFooter();
    Assertion.assertTrue(mcFooter.isMoreOfWikiArticlesCardPresent());
  }

  @Test
  public void countNoOfArticlesInMCFooterWithDiscussionsAndWithMoreOfWikiArticles(){
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage().scrollToMCFooter();
    Assertion.assertEquals(mcFooter.countArticleCards(), 11);
  }

  @Test
  @Execute(onWikia = "es.gta")
  public void countNoOfArticlesInMCFooterWithoutDiscussionsAndWithMoreOfWikiArticles(){
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage().scrollToMCFooter();
    Assertion.assertEquals(mcFooter.countArticleCards(), 13);
  }

  @Test
  @Execute(onWikia = "es.nonenwikiwithemptydiscussions")
  public void countNoOfArticlesInMCFooterWithDiscussionsAndWithoutMoreOfWikiArticles(){
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage().scrollToMCFooter();
    Assertion.assertEquals(mcFooter.countArticleCards(), 12);
  }

  @Test
  public void countNoOfArticlesInExploreCard() {
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage().scrollToMCFooter();
    Assertion.assertEquals(mcFooter.countArticlesInExploreCard(), 3);
  }

  @Test void isUserTakenToDiscussionsAfterClickOnViewAll() {
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage().scrollToMCFooter();
    DiscussionsCardInMcFooter discussions = new DiscussionsCardInMcFooter();
    discussions.clickOnViewAllLinkInDiscussions();
    Assertion.assertTrue(discussions.isDiscussions());
  }

  @Test
  public void userIsTakenToUserProfileAfterClickOnAvatar() {
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage()
        .scrollToMCFooter();
    DiscussionsCardInMcFooter discussions = new DiscussionsCardInMcFooter();
    String username = discussions.getUsername()
        .replaceAll(" •.*$", "");
    discussions.clickUserAvatar();
    Assertion.assertEquals(new UserProfilePage().getUserName(), username);
  }

  @Test
  public void userIsTakenToDiscussionsPostViewAfterClickOnPost() {
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage()
        .scrollToMCFooter();
    DiscussionsCardInMcFooter discussions = new DiscussionsCardInMcFooter();
    discussions.clickDiscussionsPost();
    Assertion.assertEquals(new DiscussionsPage().getUrl(), mcFooter.getCurrentUrl() );
  }

  @Test
  @Execute(onWikia = "enwikiwithemptydiscussions")
  public void zeroStateAppearsInDiscussionsWithoutPosts() {
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage()
        .scrollToMCFooter();
    Assertion.assertTrue(new DiscussionsCardInMcFooter().isZeroState());
  }

  @Test
  public void userIsTakenToWikiArticleAfterClickOnWikiArticleCard() {
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage().scrollToMCFooter();
    mcFooter.clickWikiArticlecard();
    mcFooter.waitForPageLoad();
    String url = driver.getCurrentUrl();
    Assertion.assertTrue(url.contains(".wikia.com/wiki/"));
  }

  @Test
  public void userIsTakenToWikiArticleWithVideoAfterClickOnWikiVideoCard() {
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage().scrollToMCFooter();
    mcFooter.clickWikiVideoCard();
    mcFooter.waitForPageLoad();
    String url = driver.getCurrentUrl();
    Assertion.assertTrue(url.contains(".wikia.com/wiki/"));
    ArticlePageObject video = new ArticlePageObject();
    Assertion.assertTrue(video.isFeaturedVideo());
  }

}
