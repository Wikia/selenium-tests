package com.wikia.webdriver.testcases.communityheadertests;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.DontRun;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RunOnly;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.CommunityHeader;
import com.wikia.webdriver.elements.oasis.components.globalshortcuts.ActionExplorerModal;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.AddMediaModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.CreateArticleModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.oasis.MainPage;

import org.testng.annotations.Test;

public class CommunityHeaderTests extends NewTestTemplate {

  @Test(groups = {"CommunityHeaderTests"})
  public void wordmarkShouldLinkToMainPage() {
    MainPage mainPage = new CommunityHeader().clickWordmark();

    Assertion.assertTrue(mainPage.isMainPage());
  }

  @Test(groups = {"CommunityHeaderTests"})
  public void wikiNameShouldLinkToMainPage() {
    MainPage mainPage = new CommunityHeader().clickWikiName();

    Assertion.assertTrue(mainPage.isMainPage());
  }

  @Test(groups = {"CommunityHeaderTests"})
  @Execute(onWikia = MercuryWikis.DE_WIKI, language = "de")
  public void wikiNameOnNonEnglishWikiShouldLinkToMainPage() {
    MainPage mainPage = new CommunityHeader().clickWikiName();

    Assertion.assertTrue(mainPage.isMainPage());
  }

  @Test(groups = {"CommunityHeaderTests"})
  @Execute(asUser = User.ANONYMOUS)
  public void testAnonWikiButtons() {
    CreateArticleModalComponentObject modal = new CommunityHeader().clickAddNewPage();

    Assertion.assertTrue(modal.isCreateNewArticleModalVisible());
  }

  @Test(groups = {"CommunityHeaderTests"})
  @Execute(asUser = User.USER)
  public void testLoggedInWikiButtons() {
    CommunityHeader communityHeader = new CommunityHeader();

    communityHeader.clickWikiActivity();
    Assertion.assertStringContains(driver.getCurrentUrl(), "Special:WikiActivity");

    CreateArticleModalComponentObject addNewPageModal = communityHeader.clickAddNewPage();
    Assertion.assertTrue(addNewPageModal.isCreateNewArticleModalVisible());
    addNewPageModal.close();

    communityHeader.openMoreToolsDropdown().clickMoreAddImageLink();
    Assertion.assertStringContains(driver.getCurrentUrl(), "Special:Upload");

    AddMediaModalComponentObject addVideoModal = communityHeader.openMoreToolsDropdown()
        .clickMoreAddVideoLink();
    Assertion.assertStringContains(driver.getCurrentUrl(), "Special:Videos");
    Assertion.assertTrue(addVideoModal.isVideoModalVisible());

    addVideoModal.closeAddVideoModal();

    communityHeader.openMoreToolsDropdown().clickMoreRecentChanges();
    Assertion.assertStringContains(driver.getCurrentUrl(), "Special:RecentChanges");

    ActionExplorerModal actionExplorerModal = communityHeader.openMoreToolsDropdown()
        .clickMoreAllShortcuts();
    Assertion.assertTrue(actionExplorerModal.isVisible());
  }

  @DontRun(language = "szl")
  @Test(groups = {"CommunityHeaderTests"})
  @Execute(asUser = User.MW119_ADMINISTRATOR)
  public void testAdminWikiButtons() {
    CommunityHeader communityHeader = new CommunityHeader();

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

  @DontRun(language = "szl")
  @Test(groups = {"CommunityHeaderTests"})
  public void testExploreMenuLinks() {
    CommunityHeader communityHeader = new CommunityHeader();

    communityHeader.openExploreMenu().clickExploreWikiActivityLink();

    Assertion.assertStringContains(driver.getCurrentUrl(),"Special:WikiActivity");

    communityHeader.openExploreMenu().clickExploreCommunityLink();

    Assertion.assertStringContains(driver.getCurrentUrl(),"Special:Community");

    communityHeader.openExploreMenu().clickExploreVideosLink();

    Assertion.assertStringContains(driver.getCurrentUrl(),"Special:Videos");

    communityHeader.openExploreMenu().clickExploreImagesLink();

    Assertion.assertStringContains(driver.getCurrentUrl(),"Special:Images");

    communityHeader.openExploreMenu().clickExploreRandomLink();

    Assertion.assertTrue(driver.getCurrentUrl()
                             .matches(".*\\.wikia\\.com/wiki/(?!Special:Images).*"));
  }

  @RunOnly(language = "szl")
  @Test(groups = {"CommunityHeaderTests"})
  public void testExploreMenuLinksSzl() {
    CommunityHeader communityHeader = new CommunityHeader();

    communityHeader.openExploreMenu().clickExploreWikiActivityLink();

    Assertion.assertStringContains(driver.getCurrentUrl(),"Specjalna:WikiActivity");

    communityHeader.openExploreMenu().clickExploreCommunityLink();

    Assertion.assertStringContains(driver.getCurrentUrl(),"Specjalna:Spo%C5%82eczno%C5%9B%C4%87");

    communityHeader.openExploreMenu().clickExploreVideosLink();

    Assertion.assertStringContains(driver.getCurrentUrl(),"Specjalna:Filmy");

    communityHeader.openExploreMenu().clickExploreImagesLink();

    Assertion.assertStringContains(driver.getCurrentUrl(),"Specjalna:Obrazy");

    communityHeader.openExploreMenu().clickExploreRandomLink();

    Assertion.assertTrue(driver.getCurrentUrl()
        .matches(".*\\.wikia\\.com/szl/wiki/(?!Specjalna:Obrazy).*"));
  }

  @DontRun(language = "szl")
  @Test(groups = {"CommunityHeaderTests"})
  @Execute(onWikia = "qatestdiscussionsnoforum")
  public void testDiscussLinkOnWikiWithDiscussionsWithoutForum() {
    new CommunityHeader().clickDiscussLink();

    Assertion.assertStringContains(driver.getCurrentUrl(),"wikia.com/d/f");
  }

  @RunOnly(language = "szl")
  @Test(groups = {"CommunityHeaderTests"})
  @Execute(onWikia = "qatestdiscussionsnoforum")
  public void testDiscussLinkOnWikiWithDiscussionsWithoutForumSzl() {
    new CommunityHeader().clickDiscussLink();

    Assertion.assertStringContains(driver.getCurrentUrl(),"wikia.com/szl/d/f");
  }

  /**
   * @prerequisites: use wiki with enabled forum and discussions
   */
  @DontRun(language = "szl")
  @Test(groups = {"CommunityHeaderTests"})
  @Execute(onWikia = "qatestdiscussiosandforum")
  public void testDiscussLinkOnWikiWithDiscussionsAndForum() {
    CommunityHeader communityHeader = new CommunityHeader();

    communityHeader.openExploreMenu().clickExploreForumLink();
    Assertion.assertStringContains(driver.getCurrentUrl(),"Special:Forum");

    communityHeader.clickDiscussLink();
    Assertion.assertStringContains(driver.getCurrentUrl(),"wikia.com/d/f");
  }

  /**
   * @prerequisites: use wiki with enabled forum and discussions
   */
  @RunOnly(language = "szl")
  @Test(groups = {"CommunityHeaderTests"})
  @Execute(onWikia = "qatestdiscussiosandforum")
  public void testDiscussLinkOnWikiWithDiscussionsAndForumSzl() {
    CommunityHeader communityHeader = new CommunityHeader();

    communityHeader.openExploreMenu().clickExploreForumLink();
    Assertion.assertStringContains(driver.getCurrentUrl(),"Specjalna:Forum");

    communityHeader.clickDiscussLink();
    Assertion.assertStringContains(driver.getCurrentUrl(),"wikia.com/szl/d/f");
  }

  @DontRun(language = "szl")
  @Test(groups = {"CommunityHeaderTests"})
  @Execute(onWikia = "qatestforumnodiscussions")
  public void testDiscussLinkOnWikiWithNoDiscussionsAndWithForum() {
    new CommunityHeader().clickDiscussLink();

    Assertion.assertStringContains(driver.getCurrentUrl(),"Special:Forum");
  }

  @RunOnly(language = "szl")
  @Test(groups = {"CommunityHeaderTests"})
  @Execute(onWikia = "qatestforumnodiscussions")
  public void testDiscussLinkOnWikiWithNoDiscussionsAndWithForumSzl() {
    new CommunityHeader().clickDiscussLink();

    Assertion.assertStringContains(driver.getCurrentUrl(),"Specjalna:Forum");
  }

  @Test(groups = {"CommunityHeaderTests"})
  @Execute(onWikia = "qatestnodiscussionsnoforum")
  public void testNoDiscussLinkOnWikiWithNoDiscussionsAndWithNoForum() {
    CommunityHeader communityHeader = new CommunityHeader();

    Assertion.assertFalse(communityHeader.isDiscussLinkDisplayed());

    communityHeader.openExploreMenu();
    Assertion.assertFalse(communityHeader.isExploreForumLinkDisplayed());
  }

  /**
   * @prerequisites: set privileges in wgGroupPermissionsLocal variable in Wiki Factory
   */
  @Test(groups = {"CommunityHeaderTests"})
  @Execute(onWikia = "testprivatewiki", asUser = User.ANONYMOUS)
  public void testCommunityHeaderNotVisibleOnPrivateWiki() {
    CommunityHeader communityHeader = new CommunityHeader();

    Assertion.assertFalse(communityHeader.isVisible(), "Community header is not visible");
  }
}
