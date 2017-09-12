package com.wikia.webdriver.testcases.mcfootertests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mcfooter.MixedContentFooter;

import org.testng.annotations.Test;

@Test (groups = {"EnAnonMixedContentFooter"})
@Execute(onWikia = "gameofthrones", asUser = User.ANONYMOUS)
public class EnAnonMixedContentFooterTests extends NewTestTemplate{

  @Test
   public void mcFooterIsPresentOnENwiki() {
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage().scrollToMCFooter();
    Assertion.assertTrue(mcFooter.isMCFooterPresent());
  }

  @Test
   public void exploreWikisCardIsPresentOnENwiki(){
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage().scrollToMCFooter();
    Assertion.assertTrue(mcFooter.isExploreWikisCardPresent());
  }

  @Test
  public void discussionsCardIsPresentOnENwikiWithDiscussions(){
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage().scrollToMCFooter();
    Assertion.assertTrue(mcFooter.isDiscussionsCardPresent());
  }

  @Test
  @Execute(onWikia = "mcfwithoutmoreofwikiarticles")
  public void discussionsCardIsPresentOnENwikiWithEmptyDiscussions(){
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage().scrollToMCFooter();
    Assertion.assertTrue(mcFooter.isDiscussionsCardPresent());
  }

  @Test
  @Execute(onWikia = "glee")
  public void discussionsCardIsNotPresentOnENwikiWithoutDiscussions(){
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage().scrollToMCFooter();
    Assertion.assertTrue(mcFooter.isDiscussionsCardNotPresent());
  }

  @Test
  public void moreOfWikiArticlesCardIsPresentOnENwiki() {
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage().scrollToMCFooter();
    Assertion.assertTrue(mcFooter.isMoreOfWikiArticlesCardPresent());
  }

  @Test
  public void countNoOfArticlesInMCFooterWithDiscussionsAndWithMoreOfWikiArticles(){
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage().scrollToMCFooter();
    Assertion.assertEquals(mcFooter.countArticleCards(), 17);
  }

  @Test
  @Execute(onWikia = "glee")
  public void countNoOfArticlesInMCFooterWithoutDiscussionsAndWithMoreOfWikiArticles(){
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage().scrollToMCFooter();
    Assertion.assertEquals(mcFooter.countArticleCards(), 19);
  }

  @Test
  @Execute(onWikia = "mcfwithoutmoreofwikiarticles")
  public void countNoOfArticlesInMCFooterWithDiscussionsAndWithoutMoreOfWikiArticles(){
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage().scrollToMCFooter();
    Assertion.assertEquals(mcFooter.countArticleCards(), 18);
  }

}
