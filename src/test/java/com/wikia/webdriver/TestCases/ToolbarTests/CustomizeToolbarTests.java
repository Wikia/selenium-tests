package com.wikia.webdriver.TestCases.ToolbarTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Toolbars.CustomizedToolbarComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;

public class CustomizeToolbarTests extends TestTemplate{
//	private String wikiArticle = "QAautoPage";

	@Test(groups = {"CustomizeToolbar001", "Toolbar", "CustomizeToolbar"})
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Customize_Toolbar_Adding
	public void CustomizeToolbar001_Adding()
	{
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		login.logInCookie(Properties.userName2, Properties.password2);
		wiki.openArticle(URLsContent.followingToolbarTest);
		CustomizedToolbarComponentObject toolbar = new CustomizedToolbarComponentObject(driver);
		toolbar.clickCustomize();
		toolbar.clickResetDefaults();
		toolbar.searchTool("e");
		toolbar.clickSearchSuggestion("Edit");
		toolbar.verifyToolOnList("Edit");
		toolbar.clickSave();
		toolbar.verifyToolOnToolbar("Edit");
	}

	@Test(groups = {"CustomizeToolbar002", "Toolbar", "CustomizeToolbar"})
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Customize_Toolbar_Editing
	public void CustomizeToolbar002_Editing()
	{
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		login.logInCookie(Properties.userName2, Properties.password2);
		wiki.openArticle(URLsContent.followingToolbarTest);
		CustomizedToolbarComponentObject toolbar = new CustomizedToolbarComponentObject(driver);
		toolbar.clickCustomize();
		toolbar.clickResetDefaults();
		toolbar.searchTool("e");
		toolbar.clickSearchSuggestion("Edit");
		toolbar.verifyToolOnList("Edit");
		toolbar.clickRename("Edit");
		toolbar.typeNewName("Edit123");
		toolbar.clickSaveNewName();
		toolbar.verifyToolOnList("Edit123");
		toolbar.clickSave();
		toolbar.verifyToolOnToolbar("Edit123");
	}

	@Test(groups = {"CustomizeToolbar003", "Toolbar", "CustomizeToolbar"})
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Customize_Toolbar_Deleting
	public void CustomizeToolbar003_Deleteing()
	{
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		login.logInCookie(Properties.userName2, Properties.password2);
		wiki.openArticle(URLsContent.followingToolbarTest);
		CustomizedToolbarComponentObject toolbar = new CustomizedToolbarComponentObject(driver);
		toolbar.clickCustomize();
		toolbar.clickResetDefaults();
		toolbar.searchTool("e");
		toolbar.clickSearchSuggestion("Edit");
		toolbar.verifyToolOnList("Edit");
		toolbar.clickRemove("Edit");
		toolbar.clickSave();
		toolbar.verifyToolRemoved("Edit");
	}

	@Test(groups = {"CustomizeToolbar004", "Toolbar", "CustomizeToolbar"})
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Customize_Toolbar_Finding
	public void CustomizeToolbar004_Finding()
	{
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		login.logInCookie(Properties.userName2, Properties.password2);
		wiki.openArticle(URLsContent.followingToolbarTest);
		CustomizedToolbarComponentObject toolbar = new CustomizedToolbarComponentObject(driver);
		toolbar.clickCustomize();
		toolbar.clickResetDefaults();
		toolbar.searchTool("Up");
		toolbar.clickSearchSuggestion("Upload photo");
		toolbar.verifyToolOnList("Upload photo");
		toolbar.clickSave();
		toolbar.verifyToolOnToolbar("Upload photo");
	}

	@Test(groups = {"CustomizeToolbar005", "Toolbar", "CustomizeToolbar"})
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Customize_Toolbar_Reset_Defaults
	public void CustomizeToolbar005_ResetDefaults()
	{
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		login.logInCookie(Properties.userName2, Properties.password2);
		wiki.openArticle(URLsContent.followingToolbarTest);
		CustomizedToolbarComponentObject toolbar = new CustomizedToolbarComponentObject(driver);
		toolbar.clickCustomize();
		toolbar.clickResetDefaults();
		toolbar.searchTool("Up");
		toolbar.clickSearchSuggestion("Upload photo");
		toolbar.verifyToolOnList("Upload photo");
		toolbar.clickSave();
		toolbar.verifyToolOnToolbar("Upload photo");
		toolbar.clickCustomize();
		toolbar.clickResetDefaults();
		toolbar.clickSave();
		toolbar.verifyToolRemoved("Upload photo");
	}

	@Test(groups = {"CustomizeToolbar006", "Toolbar", "CustomizeToolbar"})
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Customize_Toolbar_Buttons_actions
	public void CustomizeToolbar006_ButtonsActions()
	{
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		login.logInCookie(Properties.userName2, Properties.password2);
		wiki.openArticle(URLsContent.followingToolbarTest);
		CustomizedToolbarComponentObject toolbar = new CustomizedToolbarComponentObject(driver);
		toolbar.unfollowIfFollowed();
		toolbar.verifyToolOnToolbar("Follow");
		toolbar.clickOnTool("follow");
		toolbar.verifyFollowMessage();
		toolbar.verifyFollowedToolbar();
		toolbar.verifyToolOnToolbar("Following");
		toolbar.clickOnTool("follow");
		toolbar.verifyFollowMessage();
		toolbar.verifyUnfollowed();
		toolbar.verifyToolOnToolbar("Follow");
		toolbar.clickCustomize();
		toolbar.clickResetDefaults();
		toolbar.clickSave();
	}
}
