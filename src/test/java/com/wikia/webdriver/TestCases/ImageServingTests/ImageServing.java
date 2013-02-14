package com.wikia.webdriver.TestCases.ImageServingTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
//https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving
import com.wikia.webdriver.PageObjectsFactory.PageObject.FilePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.SpecialMultipleUploadPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialNewFilesPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.SpecialUploadPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticleEditMode;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;

public class ImageServing extends TestTemplate {
	
	
	@Test(groups = {"ImageServing001", "Smoke", "ImageServing"}) 
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving
	public void ImageServing001_SpecialNewFilesTest()
	{
		CommonFunctions.logOut(driver);
		CommonFunctions.logIn(Properties.userName2, Properties.password2);		
		SpecialNewFilesPageObject wikiSpecialNF = new SpecialNewFilesPageObject(driver);
		wikiSpecialNF = wikiSpecialNF.openSpecialNewFiles();
		wikiSpecialNF.addPhoto();
		wikiSpecialNF.clickOnMoreOrFewerOptions();
		wikiSpecialNF.checkIgnoreAnyWarnings();
		wikiSpecialNF.clickOnMoreOrFewerOptions();
		wikiSpecialNF.typeInFileToUploadPath(PageContent.file);
		wikiSpecialNF.clickOnUploadaPhoto();
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
		SpecialUploadPageObject wikiSpecialU = wiki.openSpecialUpload();
		wikiSpecialU.typeInFileToUploadPath(PageContent.file);
//		wikiSpecialU.verifyFilePreviewAppeared(file);
		wikiSpecialU.checkIgnoreAnyWarnings();
		FilePageObject filePage = wikiSpecialU.clickOnUploadFile(PageContent.file);
		filePage.verifyCorrectFilePage();
		CommonFunctions.logOut(driver);
	}
	@Test(groups = {"ImageServing003", "ImageServing"}) 
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving
	public void ImageServing003_SpecialMultipleUploadTest()
	{
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		CommonFunctions.logInCookie(Properties.userName, Properties.password);
		SpecialMultipleUploadPageObject wikiSpecialMU = wiki.openSpecialMultipleUpload();
		wikiSpecialMU.typeInFilesToUpload(PageContent.listOfFiles);
		wikiSpecialMU.typeInMultiUploadSummary(PageContent.caption);
		wikiSpecialMU.checkIgnoreAnyWarnings();
		wikiSpecialMU.clickOnUploadFile();
		wikiSpecialMU.verifySuccessfulUpload(PageContent.listOfFiles);
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
		WikiArticleEditMode rVmoduleMessageEdit = new WikiArticleEditMode(driver, Global.DOMAIN, "");		
		rVmoduleMessageEdit.editArticleByName("MediaWiki:RelatedVideosGlobalList");
		rVmoduleMessageEdit.deleteUnwantedVideoFromMessage(VideoContent.youtubeVideoURL2name);
		WikiArticlePageObject article = rVmoduleMessageEdit.clickOnPublishButton();
		// after deletion start testing
		article.openRandomArticleByUrl();
		article.verifyRVModulePresence();
		article.clickOnAddVideoRVModule();
		article.typeInVideoURL(VideoContent.youtubeVideoURL2);
		article.clickOnRVModalAddButton();
		article.verifyVideoAddedToRVModule(VideoContent.youtubeVideoURL2name);
		CommonFunctions.logOut(driver);
	}
}

