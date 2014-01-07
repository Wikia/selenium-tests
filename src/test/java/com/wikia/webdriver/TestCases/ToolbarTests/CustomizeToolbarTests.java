package com.wikia.webdriver.TestCases.ToolbarTests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Toolbars.CustomizedToolbarComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

/**
 *
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class CustomizeToolbarTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();

	CustomizedToolbarComponentObject toolbar;

	//search queries
	String searchQueryEdit = "e";
	String searchQueryDoubleRedirects = "Do";
	String searchQueryUploadPhoto = "Up";

	//tools
	String toolEdit = "Edit";
	String toolDoubleRedirects = "Double redirects";
	String toolUploadPhoto = "Upload photo";
	String toolMore = "more…";
	String toolFollow = "Follow";
	String toolFollowing = "Following";

	String editSuffix = "123";

	@BeforeClass(alwaysRun = true)
	public void Login() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName2, credentials.password2, wikiURL);
	}

	@BeforeMethod(alwaysRun = true)
	public void LoginAndCreateToolbar() {
		toolbar = new CustomizedToolbarComponentObject(
				driver);
		toolbar.refreshPage();
	}

	@Test(groups = { "CustomizeToolbar001", "Toolbar", "CustomizeToolbar",
			"Smoke4" })
	// https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Customize_Toolbar_Adding
	public void CustomizeToolbar_001_adding() {
		toolbar.clickCustomize();
		toolbar.clickResetDefaults();
		toolbar.searchTool(searchQueryEdit);
		toolbar.clickSearchSuggestion(toolEdit);
		toolbar.verifyToolOnList(toolEdit);
		toolbar.clickSave();
		toolbar.verifyToolOnToolbar(toolEdit);
	}

	@Test(groups = { "CustomizeToolbar002", "Toolbar", "CustomizeToolbar" })
	// https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Customize_Toolbar_Editing
	public void CustomizeToolbar002_Editing() {
		toolbar.clickCustomize();
		toolbar.clickResetDefaults();
		toolbar.searchTool(searchQueryEdit);
		toolbar.clickSearchSuggestion(toolEdit);
		toolbar.verifyToolOnList(toolEdit);
		toolbar.clickRename(toolEdit);
		toolbar.typeNewName(toolEdit + editSuffix);
		toolbar.clickSaveNewName();
		toolbar.verifyToolOnList(toolEdit + editSuffix);
		toolbar.clickSave();
		toolbar.verifyToolOnToolbar(toolEdit + editSuffix);
	}

	@Test(groups = { "CustomizeToolbar003", "Toolbar", "CustomizeToolbar" })
	// https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Customize_Toolbar_Deleting
	public void CustomizeToolbar003_Deleteing() {
		toolbar.clickCustomize();
		toolbar.clickResetDefaults();
		toolbar.searchTool(searchQueryEdit);
		toolbar.clickSearchSuggestion(toolEdit);
		toolbar.verifyToolOnList(toolEdit);
		toolbar.clickRemove(toolEdit);
		toolbar.clickSave();
		toolbar.verifyToolRemoved(toolEdit);
	}

	@Test(groups = { "CustomizeToolbar004", "Toolbar", "CustomizeToolbar" })
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

	@Test(groups = { "CustomizeToolbar005", "Toolbar", "CustomizeToolbar" })
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

	@Test(groups = { "CustomizeToolbar006", "Toolbar", "CustomizeToolbar" })
	// https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Customize_Toolbar_Buttons_actions
	public void CustomizeToolbar006_ButtonsActions() {
		toolbar.refreshPage();
		toolbar.unfollowIfFollowed();
		toolbar.verifyToolOnToolbar(toolFollow);
		toolbar.clickOnTool("follow");
		toolbar.verifyFollowMessage();
		toolbar.verifyFollowedToolbar();
		toolbar.verifyToolOnToolbar(toolFollowing);
		toolbar.clickOnTool("follow");
		toolbar.verifyFollowMessage();
		toolbar.verifyUnfollowed();
		toolbar.verifyToolOnToolbar(toolFollow);
		toolbar.clickCustomize();
		toolbar.clickResetDefaults();
		toolbar.clickSave();
	}

	@Test(groups = { "CustomizeToolbar007", "Toolbar", "CustomizeToolbar" })
	public void CustomizeToolbar007_MoreButton() {
		toolbar.clickCustomize();
		toolbar.clickResetDefaults();
		toolbar.addManyItems(toolEdit, 20);
		toolbar.clickSave();
		setWindowSize(800, 600, driver);
		toolbar.verifyToolOnToolbar(toolMore);
		toolbar.openMoreMenu();
		toolbar.verifyToolInMoreTool(toolEdit);
	}
}
