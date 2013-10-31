package com.wikia.webdriver.TestCases.LoginTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.ModalWindows.AddMediaModalComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoAddComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialNewFilesPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialVideosPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;

/**
 *
 * @author Bogna 'bognix' Knychala
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class ForcedLoginTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(groups = {"ForcedLogin_001_newFile", "ForcedLogin"})
	public void ForcedLogin_001_newFile () {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		SpecialNewFilesPageObject specialPage = base.openSpecialNewFiles(wikiURL);
		specialPage.verifySpecialPage();
		specialPage.addPhoto();
		specialPage.verifyModalLoginAppeared();
		specialPage.logInViaModal(credentials.userName, credentials.password);

		AddMediaModalComponentObject modal = new AddMediaModalComponentObject(driver);
		modal.closeAddPhotoModal();

		specialPage.verifyUserLoggedIn(credentials.userName);
	}

	@Test(groups = {"ForcedLogin_002_video", "ForcedLogin"})
	public void ForcedLogin_002_video () {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		SpecialVideosPageObject specialPage = base.openSpecialVideoPage(wikiURL);
		specialPage.clickAddAVideo();
		specialPage.verifyModalLoginAppeared();
		specialPage.logInViaModal(credentials.userName, credentials.password);

		AddMediaModalComponentObject modal = new AddMediaModalComponentObject(driver);
		modal.closeAddVideoModal();

		specialPage.verifyUserLoggedIn(credentials.userName);
	}

	@Test(groups = {"ForcedLogin_003_loginRequired", "ForcedLogin"})
	public void ForcedLogin_003_loginRequired () {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openSpecialUpload(wikiURL);
		base.verifyLoginReguiredMessage();
		SpecialUserLoginPageObject special = base.clickLoginOnSpecialPage();
		special.login(credentials.userName, credentials.password);
		special.verifyUserLoggedIn(credentials.userName);
		special.verifyURLcontains(URLsContent.specialUpload);
	}

	@Test(groups = {"ForcedLogin_004_notLoggedIn", "ForcedLogin"})
	public void ForcedLogin_004_notLoggedIn () {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openWikiPage();
		base.openSpecialWatchListPage(wikiURL);
		base.verifyNotLoggedInMessage();
		base.clickLoginOnSpecialPage();
		SpecialUserLoginPageObject special = new SpecialUserLoginPageObject(driver);
		special.login(credentials.userName, credentials.password);
		special.verifyUserLoggedIn(credentials.userName);
		special.verifyURLcontains(URLsContent.specialWatchList);
	}

	@Test(groups = {"ForcedLogin_005_addMedia", "ForcedLogin"})
	public void ForcedLogin_005_addMedia () {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		String articleName = PageContent.articleNamePrefix + base.getTimeStamp();
		VisualEditModePageObject edit = base.goToArticleEditPage(wikiURL, articleName);
		edit.clickPhotoButton();
		edit.logInViaModal(credentials.userName, credentials.password);
		edit.verifyUserLoggedIn(credentials.userName);
		edit.verifyURLcontains(articleName);
		edit.verifyURLcontains(URLsContent.actionEditParameter);
		PhotoAddComponentObject addPhoto = edit.clickPhotoButton();
		addPhoto.verifyAddPhotoModal();

	}

	@Test(groups = {"ForcedLogin_006_rail", "ForcedLogin", "Smoke2"})
	public void ForcedLogin_006_rail () {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		article.clickAddRelatedVideo();
		article.logInViaModal(credentials.userName, credentials.password);
		VetAddVideoComponentObject vetAddingVideo = new VetAddVideoComponentObject(driver);
		vetAddingVideo.verifyAddVideoModal();
		vetAddingVideo.clickCloseButton();
		article.verifyUserLoggedIn(credentials.userName);
	}
}
