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
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialMultipleUploadPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialNewFilesPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialUploadPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;

public class ImageServing extends TestTemplate {
	
	
	@Test(groups = {"ImageServing001", "Smoke", "ImageServing"}) 
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving
	public void ImageServing001_SpecialNewFilesTest()
	{
		CommonFunctions.logOut(driver);
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);
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
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		CommonFunctions.logInCookie(Properties.userName2, Properties.password2);
		SpecialUploadPageObject wikiSpecialU = wiki.openSpecialUpload();
		wikiSpecialU.typeInFileToUploadPath(PageContent.file);
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
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		CommonFunctions.logInCookie(Properties.userName, Properties.password);
		SpecialMultipleUploadPageObject wikiSpecialMU = wiki.openSpecialMultipleUpload();
		wikiSpecialMU.typeInFilesToUpload(PageContent.listOfFiles);
		wikiSpecialMU.typeInMultiUploadSummary(PageContent.caption);
		wikiSpecialMU.checkIgnoreAnyWarnings();
		wikiSpecialMU.clickOnUploadFile();
		wikiSpecialMU.verifySuccessfulUpload(PageContent.listOfFiles);
		CommonFunctions.logOut(driver);
	}
}

