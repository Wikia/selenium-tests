package com.wikia.webdriver.testcases.mcfootertests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mcfooter.MixedContentFooter;

import org.testng.annotations.Test;

@Test(groups = {"NonEnLoggedInUserMixedContentFooter"})
@Execute(onWikia = "de.gta", asUser = User.USER)

public class NonEnLoggedInUserMixedContentFooterTests extends NewTestTemplate{

  @Test
  public void isMCFooterPresentOnNonENwiki() {
    MixedContentFooter MCFooter = new MixedContentFooter(driver);
    MCFooter.openWikiMainPage().scrollToFooter();
    Assertion.assertTrue(MCFooter.isMcfooterPresent());
  }

  @Test
  public void exploreWikisCardIsPresentOnNonENwiki(){
    MixedContentFooter MCFooter = new MixedContentFooter(driver);
    MCFooter.openWikiMainPage().scrollToFooter();
    Assertion.assertTrue(MCFooter.isExploreWikisCardPresent());
  }

  @Test
  public void discussionsCardIsPresentOnNonENwiki(){
    MixedContentFooter MCFooter = new MixedContentFooter(driver);
    MCFooter.openWikiMainPage().scrollToFooter();
    Assertion.assertTrue(MCFooter.isDiscussionsCardPresent());
  }

  @Test
  @Execute(onWikia = "es.serowiec",asUser = User.USER)
  public void discussionsCardIsPresentOnNonENwikiWithEmptyDiscussions(){
    MixedContentFooter MCFooter = new MixedContentFooter(driver);
    MCFooter.openWikiMainPage().scrollToFooter();
    Assertion.assertTrue(MCFooter.isDiscussionsCardPresent());
  }

  @Test
  @Execute(onWikia = "es.gta",asUser = User.USER)
  public void discussionsCardIsNotPresentOnNonENwikiWithoutDiscussions(){
    MixedContentFooter MCFooter = new MixedContentFooter(driver);
    MCFooter.openWikiMainPage().scrollToFooter();
    Assertion.assertTrue(MCFooter.isDiscussionsCardNotPresent());
  }

  @Test
  public void moreOfWikiArticlesCardIsPresentOnNonENwiki() {
    MixedContentFooter MCFooter = new MixedContentFooter(driver);
    MCFooter.openWikiMainPage().scrollToFooter();
    Assertion.assertTrue(MCFooter.isMoreOfWikiArticlesCardPresent());
  }

  @Test
  public void countNoOfArticlesInMcFooterWithDiscussionsAndWithMoreOfWikiArticles(){
    MixedContentFooter MCFooter = new MixedContentFooter(driver);
    MCFooter.openWikiMainPage().scrollToFooter();
    Assertion.assertEquals(MCFooter.countArticleCards(), 11);
  }

  @Test
  @Execute(onWikia = "es.gta",asUser = User.USER)
  public void countNoOfArticlesInMcFooterWithoutDiscussionsAndWithMoreOfWikiArticles(){
    MixedContentFooter MCFooter = new MixedContentFooter(driver);
    MCFooter.openWikiMainPage().scrollToFooter();
    Assertion.assertEquals(MCFooter.countArticleCards(), 13);
  }

  @Test
  @Execute(onWikia = "es.serowiec",asUser = User.USER)
  public void countNoOfArticlesInMcFooterWithDiscussionsAndWithoutMoreOfWikiArticles(){
    MixedContentFooter MCFooter = new MixedContentFooter(driver);
    MCFooter.openWikiMainPage().scrollToFooter();
    Assertion.assertEquals(MCFooter.countArticleCards(), 12);
  }


}
