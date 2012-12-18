package com.wikia.webdriver.TestCases.ImageServingTests;

import org.testng.annotations.Test;

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
	private String file = "Image001.jpg";
	private String[] ListOfFiles = {"Image001.jpg","Image002.jpg", "Image003.jpg", "Image004.jpg", "Image005.jpg", "Image006.jpg", "Image007.jpg", "Image008.jpg", "Image009.jpg", "Image010.jpg"};
	private String wikiArticle = "QAautoPage";
	private String Caption = "QAcaption1";
	private String Caption2 = "QAcaption2";
	private String videoURL2 = "http://www.youtube.com/watch?v=TTchckhECwE";
	private String videoURL2name = "What is love (?) - on piano (Haddway)";
	
	
	@Test(groups = {"ImageServing001", "Smoke", "ImageServing"}) 
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving
	public void ImageServing001_SpecialNewFilesTest()
	{
		CommonFunctions.logOut(driver);
		CommonFunctions.logIn(Properties.userName2, Properties.password2);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		SpecialNewFilesPageObject wikiSpecialNF = wiki.OpenSpecialNewFiles();
		wikiSpecialNF.ClickOnAddaPhoto();
		wikiSpecialNF.ClickOnMoreOrFewerOptions();
		wikiSpecialNF.CheckIgnoreAnyWarnings();
		wikiSpecialNF.ClickOnMoreOrFewerOptions();
		wikiSpecialNF.TypeInFileToUploadPath(file);
		wikiSpecialNF.ClickOnUploadaPhoto();
		wikiSpecialNF.waitForFile(file);
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
		wikiSpecialU.TypeInFileToUploadPath(file);
//		wikiSpecialU.verifyFilePreviewAppeared(file);
		wikiSpecialU.CheckIgnoreAnyWarnings();
		FilePageObject filePage = wikiSpecialU.ClickOnUploadFile(file);
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
		wikiSpecialMU.TypeInFilesToUpload(ListOfFiles);
		wikiSpecialMU.CheckIgnoreAnyWarnings();
		wikiSpecialMU.ClickOnUploadFile();
		wikiSpecialMU.VerifySuccessfulUpload(ListOfFiles);
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
		RVmoduleMessageEdit.deleteUnwantedVideoFromMessage(videoURL2name);
		WikiArticlePageObject article = RVmoduleMessageEdit.clickOnPublishButton();
		// after deletion start testing
		article = article.OpenArticle(wikiArticle);
		article.verifyRVModulePresence();
		article.clickOnAddVideoRVModule();
		article.typeInVideoURL(videoURL2);
		article.clickOnRVModalAddButton();
//		article.WaitForProcessingToFinish();
		article.verifyVideoAddedToRVModule(videoURL2name);
		CommonFunctions.logOut(driver);
	}
}

