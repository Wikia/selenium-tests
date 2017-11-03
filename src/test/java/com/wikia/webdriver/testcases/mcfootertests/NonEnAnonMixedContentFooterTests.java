package com.wikia.webdriver.testcases.mcfootertests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.pages.discussions.DiscussionsPage;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mcfooter.MixedContentFooter;
import com.wikia.webdriver.pageobjectsfactory.pageobject.UserProfilePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.testng.annotations.Test;

@Test(groups = {"NonEnAnonMixedContentFooter"})
@Execute(onWikia = "de.gta", asUser = User.ANONYMOUS)
public class NonEnAnonMixedContentFooterTests extends NewTestTemplate{

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
    Assertion.assertTrue(mcFooter.isDiscussionsCardPresent());
  }

  @Test
  @Execute(onWikia = "es.nonenwikiwithemptydiscussions")
  public void discussionsCardIsPresentOnNonENwikiWithEmptyDiscussions(){
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage().scrollToMCFooter();
    Assertion.assertTrue(mcFooter.isDiscussionsCardPresent());
  }

  @Test
  @Execute(onWikia = "es.nonenwikiwithoutdiscussions")
  public void discussionsCardIsNotPresentOnNonENwikiWithoutDiscussions(){
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage().scrollToMCFooter();
    Assertion.assertTrue(mcFooter.isDiscussionsCardNotPresent());
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
    mcFooter.clickOnViewAllLinkInDiscussions();
    Assertion.assertTrue(mcFooter.isDiscussions());
  }

  @Test
  public void userIsTakenToUserProfileAfterClickOnAvatar() {
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage()
        .scrollToMCFooter();
    String username = mcFooter.getUsername()
        .replaceAll(" •.*$", "");
    mcFooter.clickUserAvatar();
    Assertion.assertEquals(new UserProfilePage().getUserName(), username);
  }

  @Test
  public void userIsTakenToDiscussionsPostViewAfterClickOnPost() {
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage()
        .scrollToMCFooter();
    mcFooter.clickDiscussionsPost();
    Assertion.assertEquals(new DiscussionsPage().getUrl(), mcFooter.getCurrentUrl() );
  }

  @Test
  @Execute(onWikia = "enwikiwithemptydiscussions")
  public void zeroStateAppearsInDiscussionsWithoutPosts() {
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage()
        .scrollToMCFooter();
    Assertion.assertTrue(mcFooter.isZeroState());
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
