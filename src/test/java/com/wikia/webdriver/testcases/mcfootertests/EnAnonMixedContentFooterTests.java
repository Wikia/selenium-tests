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
    MixedContentFooter MCFooter = new MixedContentFooter(driver);
    MCFooter.openWikiMainPage().scrollToFooter();
    Assertion.assertTrue(MCFooter.isMcfooterPresent());
  }

  @Test
   public void exploreWikisCardIsPresentOnENwiki(){
    MixedContentFooter MCFooter = new MixedContentFooter(driver);
    MCFooter.openWikiMainPage().scrollToFooter();
    Assertion.assertTrue(MCFooter.isExploreWikisCardPresent());
  }

  @Test
  public void discussionsCardIsPresentOnENwikiWithDiscussions(){
    MixedContentFooter MCFooter = new MixedContentFooter(driver);
    MCFooter.openWikiMainPage().scrollToFooter();
    Assertion.assertTrue(MCFooter.isDiscussionsCardPresent());
  }

  @Test
  @Execute(onWikia = "agas")
  public void discussionsCardIsPresentOnENwikiWithEmptyDiscussions(){
    MixedContentFooter MCFooter = new MixedContentFooter(driver);
    MCFooter.openWikiMainPage().scrollToFooter();
    Assertion.assertTrue(MCFooter.isDiscussionsCardPresent());
  }

  @Test
  @Execute(onWikia = "glee")
  public void discussionsCardIsNotPresentOnENwikiWithoutDiscussions(){
    MixedContentFooter MCFooter = new MixedContentFooter(driver);
    MCFooter.openWikiMainPage().scrollToFooter();
    Assertion.assertTrue(MCFooter.isDiscussionsCardNotPresent());
  }

  @Test
  public void moreOfWikiArticlesCardIsPresentOnENwiki() {
    MixedContentFooter MCFooter = new MixedContentFooter(driver);
    MCFooter.openWikiMainPage().scrollToFooter();
    Assertion.assertTrue(MCFooter.isMoreOfWikiArticlesCardPresent());
  }

  @Test
  public void countNoOfArticlesInMcFooterWithDiscussionsAndWithMoreOfWikiArticles(){
    MixedContentFooter MCFooter = new MixedContentFooter(driver);
    MCFooter.openWikiMainPage().scrollToFooter();
    Assertion.assertEquals(MCFooter.countArticleCards(), 17);
  }

  @Test
  @Execute(onWikia = "glee")
  public void countNoOfArticlesInMcFooterWithoutDiscussionsAndWithMoreOfWikiArticles(){
    MixedContentFooter MCFooter = new MixedContentFooter(driver);
    MCFooter.openWikiMainPage().scrollToFooter();
    Assertion.assertEquals(MCFooter.countArticleCards(), 19);
  }

  @Test
  @Execute(onWikia = "agas")
  public void countNoOfArticlesInMcFooterWithDiscussionsAndWithoutMoreOfWikiArticles(){
    MixedContentFooter MCFooter = new MixedContentFooter(driver);
    MCFooter.openWikiMainPage().scrollToFooter();
    Assertion.assertEquals(MCFooter.countArticleCards(), 18);
  }










}
