package com.wikia.webdriver.TestCases.ImageServingTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialMultipleUploadPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialNewFilesPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialUploadPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.FilePage.FilePagePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;
//https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving

public class ImageServing extends TestTemplate {

	@Test(groups = {"ImageServing001", "ImageServing", "Smoke3"})
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving
	public void ImageServing001_SpecialNewFilesTest()
	{
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
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
		login.logOut(driver);
	}

	@Test(groups = {"ImageServing002", "ImageServing"})
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving
	public void ImageServing002_SpecialUploadTest()
	{
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		login.logInCookie(Properties.userName2, Properties.password2);
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		SpecialUploadPageObject wikiSpecialU = wiki.openSpecialUpload();
		wikiSpecialU.typeInFileToUploadPath(PageContent.file);
		wikiSpecialU.checkIgnoreAnyWarnings();
		FilePagePageObject filePage = wikiSpecialU.clickOnUploadFile(PageContent.file);
		filePage.verifyCorrectFilePage();
		login.logOut(driver);
	}

	@Test(groups = {"ImageServing003", "ImageServing"})
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving
	public void ImageServing003_SpecialMultipleUploadTest()
	{
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		login.logInCookie(Properties.userName, Properties.password);
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		SpecialMultipleUploadPageObject wikiSpecialMU = wiki.openSpecialMultipleUpload();
		wikiSpecialMU.typeInFilesToUpload(PageContent.listOfFiles);
		wikiSpecialMU.typeInMultiUploadSummary(PageContent.caption);
		wikiSpecialMU.checkIgnoreAnyWarnings();
		wikiSpecialMU.clickOnUploadFile();
		wikiSpecialMU.verifySuccessfulUpload(PageContent.listOfFiles);
		login.logOut(driver);
	}
}

