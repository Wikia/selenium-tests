package com.wikia.webdriver.TestCases.ImageServingTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjects.PageObject.FilePageObject;
import com.wikia.webdriver.PageObjects.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjects.PageObject.WikiPage.SpecialMultipleUploadPageObject;
import com.wikia.webdriver.PageObjects.PageObject.WikiPage.SpecialNewFilesPageObject;
import com.wikia.webdriver.PageObjects.PageObject.WikiPage.SpecialUploadPageObject;
import com.wikia.webdriver.PageObjects.PageObject.WikiPage.WikiArticleEditMode;
import com.wikia.webdriver.PageObjects.PageObject.WikiPage.WikiArticlePageObject;
//https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving

public class ImageServing extends TestTemplate {
	
	
	@Test(groups = {"ImageServing001", "Smoke", "ImageServing"}) 
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving
	public void ImageServing001_SpecialNewFilesTest()
	{
		CommonFunctions.logOut(driver);
		CommonFunctions.logIn(Properties.userName2, Properties.password2);		
		SpecialNewFilesPageObject wikiSpecialNF = new SpecialNewFilesPageObject(driver, Global.DOMAIN);
		wikiSpecialNF = wikiSpecialNF.OpenSpecialNewFiles();
		wikiSpecialNF.ClickOnAddaPhoto();
		wikiSpecialNF.ClickOnMoreOrFewerOptions();
		wikiSpecialNF.CheckIgnoreAnyWarnings();
		wikiSpecialNF.ClickOnMoreOrFewerOptions();
		wikiSpecialNF.TypeInFileToUploadPath(PageContent.file);
		wikiSpecialNF.ClickOnUploadaPhoto();
		wikiSpecialNF.waitForFile(PageContent.file);
		CommonFunctions.logOut(driver);
	}
	
	@Test(groups = {"ImageServing002", "ImageServing"}) 
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving
	public void ImageServing002_SpecialUploadTest()
	{
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		CommonFunctions.logInCookie(Properties.userName2, Properties.password2);
		SpecialUploadPageObject wikiSpecialU = wiki.OpenSpecialUpload();
		wikiSpecialU.TypeInFileToUploadPath(PageContent.file);
//		wikiSpecialU.verifyFilePreviewAppeared(file);
		wikiSpecialU.CheckIgnoreAnyWarnings();
		FilePageObject filePage = wikiSpecialU.ClickOnUploadFile(PageContent.file);
		filePage.VerifyCorrectFilePage();
		CommonFunctions.logOut(driver);
	}
	@Test(groups = {"ImageServing003", "ImageServing"}) 
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving
	public void ImageServing003_SpecialMultipleUploadTest()
	{
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		CommonFunctions.logInCookie(Properties.userName, Properties.password);
		SpecialMultipleUploadPageObject wikiSpecialMU = wiki.OpenSpecialMultipleUpload();
		wikiSpecialMU.TypeInFilesToUpload(PageContent.listOfFiles);
		wikiSpecialMU.CheckIgnoreAnyWarnings();
		wikiSpecialMU.ClickOnUploadFile();
		wikiSpecialMU.VerifySuccessfulUpload(PageContent.listOfFiles);
		CommonFunctions.logOut(driver);
	}
	
	
	
	@Test(groups = {"ImageServing011", "ImageServing"}) 
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving	
	// Test Case 011 Adding related videos through Related Video (RV) module
	public void ImageServing011_AddingVideoThroughRV()
	{		
		CommonFunctions.logOut(driver);
		//delete the given video from RV module on QAAutopage using MediaWiki:RelatedVideosGlobalList (message article), by its name (videoURL2name variable)
//		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		CommonFunctions.logIn(Properties.userNameStaff, Properties.passwordStaff);
//		WikiArticlePageObject RVmoduleMessage = wiki.OpenArticle("MediaWiki:RelatedVideosGlobalList");
		WikiArticleEditMode RVmoduleMessageEdit = new WikiArticleEditMode(driver, Global.DOMAIN, "");		
		RVmoduleMessageEdit.editArticleByName("MediaWiki:RelatedVideosGlobalList");
		RVmoduleMessageEdit.deleteUnwantedVideoFromMessage(VideoContent.youtubeVideoURL2name);
		WikiArticlePageObject article = RVmoduleMessageEdit.clickOnPublishButton();
		// after deletion start testing
		article.openRandomArticle();
		article.verifyRVModulePresence();
		article.clickOnAddVideoRVModule();
		article.typeInVideoURL(VideoContent.youtubeVideoURL2);
		article.clickOnRVModalAddButton();
		article.verifyVideoAddedToRVModule(VideoContent.youtubeVideoURL2name);
		CommonFunctions.logOut(driver);
	}
}

