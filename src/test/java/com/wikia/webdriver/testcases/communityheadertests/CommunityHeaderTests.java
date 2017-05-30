package com.wikia.webdriver.testcases.communityheadertests;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.oasis.CommunityHeader;
import com.wikia.webdriver.pageobjectsfactory.pageobject.oasis.MainPage;

import junit.framework.Assert;
import org.testng.annotations.Test;

@Test(groups = {"CommunityHeaderTests"})
public class CommunityHeaderTests extends NewTestTemplate {
  // TODO Test cases:
  // - disscuss link logic
  //    - for wiki with only forum enabled
  //    - for wiki without forum or discussions


  public void wordmarkShouldLinkToMainPage() {
    MainPage mainPage = new CommunityHeader().clickWordmark();

    Assert.assertTrue(mainPage.isMainPage());
  }

  public void wikiNameShouldLinkToMainPage() {
    MainPage mainPage = new CommunityHeader().clickWikiName();

    Assert.assertTrue(mainPage.isMainPage());
  }

  @Execute(asUser = User.ANONYMOUS)
  public void testAnonWikiButtons() {
    new CommunityHeader().clickAddNewPage();

    Assert.assertTrue(driver.getCurrentUrl().contains("Special:CreatePage"));
  }

  @Execute(asUser = User.USER)
  public void testLoggedInWikiButtons() {
    CommunityHeader communityHeader = new CommunityHeader();

    communityHeader.clickWikiActivity();
    Assert.assertTrue(driver.getCurrentUrl().contains("Special:WikiActivity"));

    communityHeader.clickAddNewPage();
    Assert.assertTrue(driver.getCurrentUrl().contains("Special:CreatePage"));
  }

  @Execute(asUser = User.MW119_ADMINISTRATOR)
  public void testAdminWikiButtons() {
    CommunityHeader communityHeader = new CommunityHeader();

    communityHeader.clickWikiActivity();
    Assert.assertTrue(driver.getCurrentUrl().contains("Special:WikiActivity"));

    communityHeader.clickAdminDashboard();
    Assert.assertTrue(driver.getCurrentUrl().contains("Special:AdminDashboard"));

    communityHeader.clickAddNewPage();
    Assert.assertTrue(driver.getCurrentUrl().contains("Special:CreatePage"));
  }

  public void testExploreMenuLinks() {
    CommunityHeader communityHeader = new CommunityHeader();

    communityHeader
        .openExploreMenu()
        .clickExploreWikiActivityLink();

    Assert.assertTrue(driver.getCurrentUrl().contains("Special:WikiActivity"));

    communityHeader
        .openExploreMenu()
        .clickExploreCommunityLink();

    Assert.assertTrue(driver.getCurrentUrl().contains("Special:Community"));

    communityHeader
        .openExploreMenu()
        .clickExploreVideosLink();

    Assert.assertTrue(driver.getCurrentUrl().contains("Special:Videos"));

    communityHeader
        .openExploreMenu()
        .clickExploreImagesLink();

    Assert.assertTrue(driver.getCurrentUrl().contains("Special:Images"));

    communityHeader
        .openExploreMenu()
        .clickExploreRandomLink();

    Assert.assertTrue(driver.getCurrentUrl().matches(".*\\.wikia\\.com/wiki/(?!Special:Images).*"));

  }

  @Execute(onWikia = "qatestdiscussionsnoforum")
  public void testDiscussLinkOnWikiWithDiscussionsWithoutForum() {
    new CommunityHeader().clickDiscussLink();

    Assert.assertTrue(driver.getCurrentUrl().contains("wikia.com/d/f"));
  }

  @Execute(onWikia = "qatestdiscussiosandforum")
  public void testDiscussLinkOnWikiWithDiscussionsAndForum() {
    CommunityHeader communityHeader = new CommunityHeader();

    communityHeader
        .openExploreMenu()
        .clickExploreForumLink();
    Assert.assertTrue(driver.getCurrentUrl().contains("Special:Forum"));

    communityHeader.clickDiscussLink();
    Assert.assertTrue(driver.getCurrentUrl().contains("wikia.com/d/f"));
  }
}
