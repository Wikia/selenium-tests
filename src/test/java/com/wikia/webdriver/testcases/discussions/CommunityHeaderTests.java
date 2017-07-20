package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import com.wikia.webdriver.elements.common.CommunityHeader;
import com.wikia.webdriver.pageobjectsfactory.pageobject.UserProfilePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.oasis.MainPage;

import junit.framework.Assert;
import org.testng.annotations.Test;

public class CommunityHeaderTests extends NewTestTemplate {

  @Test(groups = {"discussions-CommunityHeaderTests"})
  public void wordmarkShouldLinkToMainPage() {
    new PostsListPage().open();
    MainPage mainPage = new CommunityHeader().clickWordmark();

    Assert.assertTrue(mainPage.isMainPage());
  }

  @Test(groups = {"discussions-CommunityHeaderTests"})
  public void wikiNameShouldLinkToMainPage() {
    new PostsListPage().open();
    MainPage mainPage = new CommunityHeader().clickWikiName();

    Assert.assertTrue(mainPage.isMainPage());
  }

  @Test(groups = {"discussions-CommunityHeaderTests"})
  public void avatarShouldLinkToUserPage() {
    new PostsListPage().open();
    CommunityHeader ch = new CommunityHeader();
    String username = ch.getUserNameFromAvatar(0);
    UserProfilePage userPage = ch.clickUserAvatar(0);

    Assert.assertEquals(userPage.getUserName(), username);
  }

  @Test(groups = {"discussions-CommunityHeaderTests"})
  public void testExploreMenuLinks() {
    new PostsListPage().open();
    CommunityHeader communityHeader = new CommunityHeader();

    communityHeader
        .openExploreMenu()
        .clickExploreWikiActivityLink();

    Assert.assertTrue(driver.getCurrentUrl().contains("Special:WikiActivity"));

    new PostsListPage().open();

    communityHeader
        .openExploreMenu()
        .clickExploreCommunityLink();

    Assert.assertTrue(driver.getCurrentUrl().contains("Special:Community"));

    new PostsListPage().open();

    communityHeader
        .openExploreMenu()
        .clickExploreVideosLink();

    Assert.assertTrue(driver.getCurrentUrl().contains("Special:Videos"));

    new PostsListPage().open();

    communityHeader
        .openExploreMenu()
        .clickExploreImagesLink();

    Assert.assertTrue(driver.getCurrentUrl().contains("Special:Images"));

    new PostsListPage().open();

    communityHeader
        .openExploreMenu()
        .clickExploreRandomLink();

    Assert.assertTrue(driver.getCurrentUrl().matches(".*\\.wikia\\.com/wiki/(?!Special:Images).*"));
  }
}
