package com.wikia.webdriver.testcases.toolbartests;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.toolbars.CustomizedToolbarComponentObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(groups = {"Toolbar", "CustomizeToolbar"})
public class CustomizeToolbarTests extends NewTestTemplate {

  private static final String BROWSER_SIZE = "800x600";

  CustomizedToolbarComponentObject toolbar;

  // search queries
  String searchQueryPreferences = "p";
  String searchQueryDoubleRedirects = "Do";
  String searchQueryUploadPhoto = "Up";

  // tools
  String toolPreferences = "Preferences";
  String toolDoubleRedirects = "Double redirects";
  String toolUploadPhoto = "Upload photo";
  String toolMore = "more…";
  String toolFollow = "Follow";
  String toolFollowing = "Following";

  String editSuffix = "123";

  @BeforeMethod(alwaysRun = true)
  public void LoginAndCreateToolbar() {
    toolbar = new CustomizedToolbarComponentObject();
    toolbar.openMainPage(wikiURL);
  }

  @Test(groups = {"CustomizeToolbar001", "Smoke4"})
  @Execute(asUser = User.USER_2)
  // https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Customize_Toolbar_Adding
  public void CustomizeToolbar_001_adding() {
    toolbar.clickCustomize();
    toolbar.clickResetDefaults();
    toolbar.searchTool(searchQueryPreferences);
    toolbar.clickSearchSuggestion(toolPreferences);
    toolbar.verifyToolOnList(toolPreferences);
    toolbar.clickSave();
    toolbar.verifyToolOnToolbar(toolPreferences);
  }

  @Test(groups = "CustomizeToolbar002")
  @Execute(asUser = User.USER_2)
    // https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Customize_Toolbar_Editing
  public void CustomizeToolbar002_Editing() {
    toolbar.clickCustomize();
    toolbar.clickResetDefaults();
    toolbar.searchTool(searchQueryPreferences);
    toolbar.clickSearchSuggestion(toolPreferences);
    toolbar.verifyToolOnList(toolPreferences);
    toolbar.clickRename(toolPreferences);
    toolbar.typeNewName(toolPreferences + editSuffix);
    toolbar.clickSaveNewName();
    toolbar.verifyToolOnList(toolPreferences + editSuffix);
    toolbar.clickSave();
    toolbar.verifyToolOnToolbar(toolPreferences + editSuffix);
  }

  @Test(groups = {"CustomizeToolbar003"})
  @Execute(asUser = User.USER_2)
  // https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Customize_Toolbar_Deleting
  public void CustomizeToolbar003_Deleteing() {
    toolbar.clickCustomize();
    toolbar.clickResetDefaults();
    toolbar.searchTool(searchQueryPreferences);
    toolbar.clickSearchSuggestion(toolPreferences);
    toolbar.verifyToolOnList(toolPreferences);
    toolbar.clickRemove(toolPreferences);
    toolbar.clickSave();
    toolbar.verifyToolRemoved(toolPreferences);
  }

  @Test(groups = {"CustomizeToolbar004"})
  @Execute(asUser = User.USER_2)
    // https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Customize_Toolbar_Finding
  public void CustomizeToolbar004_Finding() {
    toolbar.clickCustomize();
    toolbar.clickResetDefaults();
    toolbar.searchTool(searchQueryUploadPhoto);
    toolbar.clickSearchSuggestion(toolUploadPhoto);
    toolbar.verifyToolOnList(toolUploadPhoto);
    toolbar.clickSave();
    toolbar.verifyToolOnToolbar(toolUploadPhoto);
  }

  @Test(groups = {"CustomizeToolbar005"})
  @Execute(asUser = User.USER_2)
    // https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Customize_Toolbar_Reset_Defaults
  public void CustomizeToolbar005_ResetDefaults() {
    toolbar.clickCustomize();
    toolbar.clickResetDefaults();
    toolbar.searchTool(searchQueryDoubleRedirects);
    toolbar.clickSearchSuggestion(toolDoubleRedirects);
    toolbar.verifyToolOnList(toolDoubleRedirects);
    toolbar.clickSave();
    toolbar.verifyToolOnToolbar(toolDoubleRedirects);
    toolbar.refreshPage();
    toolbar.clickCustomize();
    toolbar.clickResetDefaults();
    toolbar.verifyToolNotOnList(toolDoubleRedirects);
    toolbar.clickSave();
    toolbar.verifyToolRemoved(toolDoubleRedirects);
  }

  @Test(groups = {"CustomizeToolbar006"})
  @Execute(asUser = User.USER_2)
  // https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Customize_Toolbar_Buttons_actions
  public void CustomizeToolbar006_ButtonsActions() {
    toolbar.unfollowIfFollowed();
    toolbar.verifyToolOnToolbar(toolFollow);
    toolbar.clickOnTool("follow");
    toolbar.verifyFollowMessage();
    toolbar.verifyFollowedToolbar();
    toolbar.verifyToolOnToolbar(toolFollowing);
    toolbar.waitForTenSecondsAndThinkPositivelyAboutLudwik();
    toolbar.clickOnTool("follow");
    toolbar.verifyFollowMessage();
    toolbar.verifyUnfollowed();
    toolbar.verifyToolOnToolbar(toolFollow);
    toolbar.clickCustomize();
    toolbar.clickResetDefaults();
    toolbar.clickSave();
  }

  @Test(groups = {"CustomizeToolbar007"})
  @Execute(asUser = User.USER_2)
  @InBrowser(browserSize = BROWSER_SIZE)
    public void CustomizeToolbar007_MoreButton() {
    toolbar.clickCustomize();
    toolbar.clickResetDefaults();
    toolbar.addManyItems(toolPreferences, 8);
    toolbar.clickSave();
    toolbar.waitForCustomizeToolbarModalToDisappear();
    toolbar.refreshPage();
    toolbar.verifyToolOnToolbar(toolMore);
    toolbar.openMoreMenu();
    toolbar.verifyToolInMoreTool(toolPreferences);
  }
}
