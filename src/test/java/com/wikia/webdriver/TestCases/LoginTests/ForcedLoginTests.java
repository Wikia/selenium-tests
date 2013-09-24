package com.wikia.webdriver.TestCases.LoginTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.MiniEditor.MiniEditorComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.ModalWindows.AddMediaModalComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialNewFilesPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialVideosPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class ForcedLoginTests extends TestTemplate{

	private String newFiles = URLsContent.specialNewFiles;
	private String newVideo = URLsContent.specialNewVideo;
	private String upload = URLsContent.specialUpload;
	private String watchList = URLsContent.specialWatchList;

	@Test(groups = {"ForcedLogin_001_newFile", "ForcedLogin"})
	public void ForcedLogin_001_newFile () {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);

		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openWikiPage();
		base.openSpecialPage(newFiles);
		SpecialNewFilesPageObject specialPage = new SpecialNewFilesPageObject(driver);
		specialPage.verifySpecialPage();
		specialPage.addPhoto();
		specialPage.verifyModalLoginAppeared();
		specialPage.logInViaModal(Properties.userName, Properties.password);

		AddMediaModalComponentObject modal = new AddMediaModalComponentObject(driver);
		modal.closeAddPhotoModal();

		specialPage.verifyUserLoggedIn(Properties.userName);

		login.logOut(driver);
	}

	@Test(groups = {"ForcedLogin_002_video", "ForcedLogin"})
	public void ForcedLogin_002_video () {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);

		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openWikiPage();
		base.openSpecialPage(newVideo);
		SpecialVideosPageObject specialPage = new SpecialVideosPageObject(driver);
		specialPage.clickAddAVideo();
		specialPage.verifyModalLoginAppeared();
		specialPage.logInViaModal(Properties.userName, Properties.password);

		AddMediaModalComponentObject modal = new AddMediaModalComponentObject(driver);
		modal.closeAddVideoModal();

		specialPage.verifyUserLoggedIn(Properties.userName);

		login.logOut(driver);
	}

	@Test(groups = {"ForcedLogin_003_loginRequired", "ForcedLogin"})
	public void ForcedLogin_003_loginRequired () {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);

		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openWikiPage();
		base.openSpecialPage(upload);
		base.verifyLoginReguiredMessage();
		base.clickLoginOnSpecialPage();
		SpecialUserLoginPageObject special = new SpecialUserLoginPageObject(driver);
		special.login(Properties.userName, Properties.password);
		special.verifyUserLoggedIn(Properties.userName);
		special.verifySpecialPageRedirection(upload);

		login.logOut(driver);
	}

	@Test(groups = {"ForcedLogin_004_notLoggedIn", "ForcedLogin"})
	public void ForcedLogin_004_notLoggedIn () {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);

		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openWikiPage();
		base.openSpecialPage(watchList);
		base.verifyNotLoggedInMessage();
		base.clickLoginOnSpecialPage();
		SpecialUserLoginPageObject special = new SpecialUserLoginPageObject(driver);
		special.login(Properties.userName, Properties.password);
		special.verifyUserLoggedIn(Properties.userName);
		special.verifySpecialPageRedirection(watchList);

		login.logOut(driver);
	}

	@Test(groups = {"ForcedLogin_005_addMedia", "ForcedLogin"})
	public void ForcedLogin_005_addMedia () {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);

		WikiArticlePageObject base = new WikiArticlePageObject(driver);
		base.openWikiPage();
		WikiArticlePageObject article = base.openRandomArticle();
		article.edit();
		MiniEditorComponentObject miniEditor = new MiniEditorComponentObject(driver);
		miniEditor.clickAddImage();
		base.logInViaModal(Properties.userName, Properties.password);
		base.verifyUserLoggedIn(Properties.userName);
		miniEditor.waitForEditorReady();
		login.logOut(driver);
	}

	@Test(groups = {"ForcedLogin_006_rail", "ForcedLogin"})
	public void ForcedLogin_006_rail () {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);

		WikiArticlePageObject base = new WikiArticlePageObject(driver);
		base.openWikiPage();
		WikiArticlePageObject article = base.openRandomArticle();
		article.clickAddVideoFromRail();
		article.logInViaModal(Properties.userName, Properties.password);

		AddMediaModalComponentObject modal = new AddMediaModalComponentObject(driver);
		modal.closeAddVideoModal();

		base.verifyUserLoggedIn(Properties.userName);

		login.logOut(driver);
	}
}
