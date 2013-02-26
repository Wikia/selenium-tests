package com.wikia.webdriver.TestCases.ToolbarTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.CustomizedToolbar.CustomizedToolbarComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;

public class CustomizeToolbarTests extends TestTemplate{
	private String wikiArticle = "QAautoPage";
	
	@Test(groups = {"CustomizeToolbar001", "Toolbar"}) 
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Customize_Toolbar_Adding
	public void CustomizeToolbar001_Adding()
	{
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName2, Properties.password2);
		wiki.openArticle(wikiArticle);
		CustomizedToolbarComponentObject toolbar = new CustomizedToolbarComponentObject(driver);
		toolbar.showToolbar();
		toolbar.customizeToolbar_ClickCustomize();
		toolbar.customizeToolbar_ClickOnResetDefaults();
		toolbar.customizeToolbar_TypeIntoFindATool("e");
		toolbar.customizeToolbar_ClickOnFoundTool("Edit");
		toolbar.customizeToolbar_VerifyToolOnToolbarList("Edit");
		toolbar.customizeToolbar_ClickOnSaveButton();
		toolbar.customizeToolbar_VerifyToolOnToolbar("Edit");
		
	}
	
	@Test(groups = {"CustomizeToolbar002", "Toolbar"}) 
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Customize_Toolbar_Editing
	public void CustomizeToolbar002_Editing()
	{
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName2, Properties.password2);
		wiki.openArticle(wikiArticle);
		CustomizedToolbarComponentObject toolbar = new CustomizedToolbarComponentObject(driver);
		toolbar.showToolbar();
		toolbar.customizeToolbar_ClickCustomize();
		toolbar.customizeToolbar_ClickOnResetDefaults();
		toolbar.customizeToolbar_TypeIntoFindATool("e");
		toolbar.customizeToolbar_ClickOnFoundTool("Edit");
		toolbar.customizeToolbar_VerifyToolOnToolbarList("Edit");
		toolbar.customizeToolbar_ClickOnToolRenameButton("Edit");
		toolbar.customizeToolbar_TypeIntoRenameItemDialog("Edit123");
		toolbar.customizeToolbar_saveInRenameItemDialog();
		toolbar.customizeToolbar_VerifyToolOnToolbarList("Edit123");
		toolbar.customizeToolbar_ClickOnSaveButton();
		toolbar.customizeToolbar_VerifyToolOnToolbar("Edit123");
				
	}
	
	@Test(groups = {"CustomizeToolbar004", "Toolbar"}) 
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Customize_Toolbar_Deleting
	public void CustomizeToolbar004_Deleteing()
	{
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName2, Properties.password2);
		wiki.openArticle(wikiArticle);
		CustomizedToolbarComponentObject toolbar = new CustomizedToolbarComponentObject(driver);
		toolbar.showToolbar();
		toolbar.customizeToolbar_ClickCustomize();
		toolbar.customizeToolbar_ClickOnResetDefaults();
		toolbar.customizeToolbar_TypeIntoFindATool("e");
		toolbar.customizeToolbar_ClickOnFoundTool("Edit");
		toolbar.customizeToolbar_VerifyToolOnToolbarList("Edit");
		toolbar.customizeToolbar_ClickOnToolRemoveButton("Edit");
		toolbar.customizeToolbar_ClickOnSaveButton();
		toolbar.customizeToolbar_VerifyToolNotOnToolbar("Edit");
	}
	
	@Test(groups = {"CustomizeToolbar005", "Toolbar"}) 
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Customize_Toolbar_Finding
	public void CustomizeToolbar005_Finding()
	{
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName2, Properties.password2);
		wiki.openArticle(wikiArticle);
		CustomizedToolbarComponentObject toolbar = new CustomizedToolbarComponentObject(driver);
		toolbar.showToolbar();
		toolbar.customizeToolbar_ClickCustomize();
		toolbar.customizeToolbar_ClickOnResetDefaults();
		toolbar.customizeToolbar_TypeIntoFindATool("Up");
		toolbar.customizeToolbar_ClickOnFoundTool("Upload photo");
		toolbar.customizeToolbar_VerifyToolOnToolbarList("Upload photo");
		toolbar.customizeToolbar_ClickOnSaveButton();
		toolbar.customizeToolbar_VerifyToolOnToolbar("Upload photo");
	}
	
	@Test(groups = {"CustomizeToolbar006", "Toolbar"}) 
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Customize_Toolbar_Reset_Defaults
	public void CustomizeToolbar006_ResetDefaults()
	{
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName2, Properties.password2);
		wiki.openArticle(wikiArticle);
		CustomizedToolbarComponentObject toolbar = new CustomizedToolbarComponentObject(driver);
		toolbar.showToolbar();
		toolbar.customizeToolbar_ClickCustomize();
		toolbar.customizeToolbar_ClickOnResetDefaults();
		toolbar.customizeToolbar_TypeIntoFindATool("Up");
		toolbar.customizeToolbar_ClickOnFoundTool("Upload photo");
		toolbar.customizeToolbar_VerifyToolOnToolbarList("Upload photo");
		toolbar.customizeToolbar_ClickOnSaveButton();
		toolbar.customizeToolbar_VerifyToolOnToolbar("Upload photo");
		toolbar.customizeToolbar_ClickCustomize();
		toolbar.customizeToolbar_ClickOnResetDefaults();
		toolbar.customizeToolbar_ClickOnSaveButton();
		toolbar.customizeToolbar_VerifyToolNotOnToolbar("Upload photo");
	}
	
	@Test(groups = {"CustomizeToolbar007", "Toolbar"}) 
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Customize_Toolbar_Buttons_actions
	public void CustomizeToolbar007_ButtonsActions()
	{
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName2, Properties.password2);
		wiki.openArticle(wikiArticle);
		CustomizedToolbarComponentObject toolbar = new CustomizedToolbarComponentObject(driver);
		toolbar.showToolbar();
		toolbar.customizeToolbar_UnfollowIfPageIsFollowed();
		toolbar.customizeToolbar_VerifyToolOnToolbar("Follow");
		toolbar.customizeToolbar_ClickOnTool("follow");
		toolbar.customizeToolbar_VerifyPageWatchlistStatusMessage();
		toolbar.customizeToolbar_VerifyPageFollowed();
		toolbar.customizeToolbar_VerifyToolOnToolbar("Following");
		toolbar.customizeToolbar_ClickOnTool("follow");
		toolbar.customizeToolbar_VerifyPageWatchlistStatusMessage();
		toolbar.customizeToolbar_VerifyPageUnfollowed();
		toolbar.customizeToolbar_VerifyToolOnToolbar("Follow");
		toolbar.customizeToolbar_ClickCustomize();
		toolbar.customizeToolbar_ClickOnResetDefaults();
		toolbar.customizeToolbar_ClickOnSaveButton();
	}
	
}
