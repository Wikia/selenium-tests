package com.wikia.webdriver.testcases.desktop.navigation.localnav;

import com.wikia.webdriver.common.contentpatterns.MobileWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.navigation.desktopNav.local.CommunityHeaderDesktop;
import com.wikia.webdriver.elements.oasis.components.globalshortcuts.ActionExplorerModal;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.AddMediaModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.CreateArticleModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.oasis.MainPage;

import org.testng.annotations.Test;

public class CommunityHeaderTests extends NewTestTemplate {

  @Test(groups = {"CommunityHeaderTests"})
  public void wordmarkShouldLinkToMainPage() {
    MainPage mainPage = new CommunityHeaderDesktop().clickWordmark();

    Assertion.assertTrue(mainPage.isMainPage());
  }

  @Test(groups = {"CommunityHeaderTests"})
  public void wikiNameShouldLinkToMainPage() {
    MainPage mainPage = new CommunityHeaderDesktop().clickWikiName();

    Assertion.assertTrue(mainPage.isMainPage());
  }

  @Test(groups = {"CommunityHeaderTests"})
  @Execute(onWikia = MobileWikis.DE_WIKI, language = "de")
  public void wikiNameOnNonEnglishWikiShouldLinkToMainPage() {
    MainPage mainPage = new CommunityHeaderDesktop().clickWikiName();

    Assertion.assertTrue(mainPage.isMainPage());
  }

  @Test(groups = {"CommunityHeaderTests"})
  @Execute(asUser = User.ANONYMOUS)
  public void testAnonWikiButtons() {
    CreateArticleModalComponentObject modal = new CommunityHeaderDesktop().clickAddNewPage();

    Assertion.assertTrue(modal.isCreateNewArticleModalVisible());
  }

  @Test(groups = {"CommunityHeaderTests"})
  @Execute(asUser = User.USER)
  public void testLoggedInWikiButtons() {
    CommunityHeaderDesktop communityHeader = new CommunityHeaderDesktop();

    communityHeader.clickWikiActivity();
    Assertion.assertTrue(driver.getCurrentUrl().contains("Special:WikiActivity"));

    CreateArticleModalComponentObject addNewPageModal = communityHeader.clickAddNewPage();
    Assertion.assertTrue(addNewPageModal.isCreateNewArticleModalVisible());
    addNewPageModal.close();

    communityHeader.openMoreToolsDropdown().clickMoreAddImageLink();
    Assertion.assertTrue(driver.getCurrentUrl().contains("Special:Upload"));

    AddMediaModalComponentObject addVideoModal = communityHeader.openMoreToolsDropdown()
        .clickMoreAddVideoLink();
    Assertion.assertTrue(driver.getCurrentUrl().contains("Special:Videos"));
    Assertion.assertTrue(addVideoModal.isVideoModalVisible());

    addVideoModal.closeAddVideoModal();

    communityHeader.openMoreToolsDropdown().clickMoreRecentChanges();
    Assertion.assertTrue(driver.getCurrentUrl().contains("Special:RecentChanges"));

    ActionExplorerModal actionExplorerModal = communityHeader.openMoreToolsDropdown()
        .clickMoreAllShortcuts();
    Assertion.assertTrue(actionExplorerModal.isVisible());
  }

  @Test(groups = {"CommunityHeaderTests"})
  @Execute(asUser = User.MW119_ADMINISTRATOR)
  public void testAdminWikiButtons() {
    CommunityHeaderDesktop communityHeader = new CommunityHeaderDesktop();

    communityHeader.clickWikiActivity();
    Assertion.assertTrue(driver.getCurrentUrl().contains("Special:WikiActivity"));

    communityHeader.clickAdminDashboard();
    Assertion.assertTrue(driver.getCurrentUrl().contains("Special:AdminDashboard"));

    CreateArticleModalComponentObject addNewPageModal = communityHeader.clickAddNewPage();
    Assertion.assertTrue(addNewPageModal.isCreateNewArticleModalVisible());
    addNewPageModal.close();

    communityHeader.openMoreToolsDropdown().clickMoreAddImageLink();
    Assertion.assertTrue(driver.getCurrentUrl().contains("Special:Upload"));

    AddMediaModalComponentObject addVideoModal = communityHeader.openMoreToolsDropdown()
        .clickMoreAddVideoLink();
    Assertion.assertTrue(driver.getCurrentUrl().contains("Special:Videos"));
    Assertion.assertTrue(addVideoModal.isVideoModalVisible());

    addVideoModal.closeAddVideoModal();

    communityHeader.openMoreToolsDropdown().clickMoreRecentChanges();
    Assertion.assertTrue(driver.getCurrentUrl().contains("Special:RecentChanges"));

    ActionExplorerModal actionExplorerModal = communityHeader.openMoreToolsDropdown()
        .clickMoreAllShortcuts();
    Assertion.assertTrue(actionExplorerModal.isVisible());
  }

  @Test(groups = {"CommunityHeaderTests"})
  public void testExploreMenuLinks() {
    CommunityHeaderDesktop communityHeader = new CommunityHeaderDesktop();

    communityHeader.openExploreMenu().clickExploreWikiActivityLink();

    Assertion.assertTrue(driver.getCurrentUrl().contains("Special:WikiActivity"));

    communityHeader.openExploreMenu().clickExploreCommunityLink();

    Assertion.assertTrue(driver.getCurrentUrl().contains("Special:Community"));

    communityHeader.openExploreMenu().clickExploreVideosLink();

    Assertion.assertTrue(driver.getCurrentUrl().contains("Special:Videos"));

    communityHeader.openExploreMenu().clickExploreImagesLink();

    Assertion.assertTrue(driver.getCurrentUrl().contains("Special:Images"));

    communityHeader.openExploreMenu().clickExploreRandomLink();

    Assertion.assertTrue(driver.getCurrentUrl()
                             .matches(".*\\.wikia\\.com/wiki/(?!Special:Images).*"));
  }

  @Test(groups = {"CommunityHeaderTests"})
  @Execute(onWikia = "qatestdiscussionsnoforum")
  public void testDiscussLinkOnWikiWithDiscussionsWithoutForum() {
    new CommunityHeaderDesktop().clickDiscussLink();

    Assertion.assertTrue(driver.getCurrentUrl().contains("wikia.com/d/f"));
  }

  @Test(groups = {"CommunityHeaderTests"})
  @Execute(onWikia = "qatestdiscussiosandforum")
  public void testDiscussLinkOnWikiWithDiscussionsAndForum() {
    CommunityHeaderDesktop communityHeader = new CommunityHeaderDesktop();

    communityHeader.openExploreMenu().clickExploreForumLink();
    Assertion.assertTrue(driver.getCurrentUrl().contains("Special:Forum"));

    communityHeader.clickDiscussLink();
    Assertion.assertTrue(driver.getCurrentUrl().contains("wikia.com/d/f"));
  }

  @Test(groups = {"CommunityHeaderTests"})
  @Execute(onWikia = "qatestforumnodiscussions")
  public void testDiscussLinkOnWikiWithNoDiscussionsAndWithForum() {
    new CommunityHeaderDesktop().clickDiscussLink();

    Assertion.assertTrue(driver.getCurrentUrl().contains("Special:Forum"));
  }

  @Test(groups = {"CommunityHeaderTests"})
  @Execute(onWikia = "qatestnodiscussionsnoforum")
  public void testNoDiscussLinkOnWikiWithNoDiscussionsAndWithNoForum() {
    CommunityHeaderDesktop communityHeader = new CommunityHeaderDesktop();

    Assertion.assertFalse(communityHeader.isDiscussLinkDisplayed());

    communityHeader.openExploreMenu();
    Assertion.assertFalse(communityHeader.isExploreForumLinkDisplayed());
  }

  @Test(groups = {"CommunityHeaderTests"})
  @Execute(onWikia = "testprivatewiki", asUser = User.ANONYMOUS)
  public void testCommunityHeaderNotVisibleOnPrivateWiki() {
    CommunityHeaderDesktop communityHeader = new CommunityHeaderDesktop();

    Assertion.assertFalse(communityHeader.isVisible());
  }
}
