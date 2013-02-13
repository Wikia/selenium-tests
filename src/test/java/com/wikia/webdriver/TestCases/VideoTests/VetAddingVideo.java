package com.wikia.webdriver.TestCases.VideoTests;

import org.apache.http.entity.ContentProducer;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetOptionsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialVideosPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticleEditMode;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;

// https://docs.google.com/a/wikia-inc.com/spreadsheet/ccc?key=0AtG89yMxyGSadEtPY28ydDB4czkydXNmMkJVQ2NGR0E#gid=7

public class VetAddingVideo extends TestTemplate {

	private String videoName, pageName; 
	
	@Test(groups = { "VetTests001", "VetTests" })
	public void Vet_Tests_001_SpecialVideosProvider() {
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);	
		SpecialVideosPageObject specialVideos = wiki.openSpecialVideoPage();
		VetAddVideoComponentObject vetAddingVideo = specialVideos.clickAddAVideo();
		vetAddingVideo.addVideoByUrl(VideoContent.youtubeVideoURL2);
		specialVideos.verifyVideoAdded(VideoContent.youtubeVideoURL2name);
	}
	
	@Test(groups = { "VetTests002", "VetTests" })
	public void Vet_Tests_002_SpecialVideosLibrary() {
		PageObjectLogging.log("", "ACTIVE BUG https://wikia.fogbugz.com/default.asp?97650", false);
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);	
		SpecialVideosPageObject specialVideos = wiki.openSpecialVideoPage();
		VetAddVideoComponentObject vetAddingVideo = specialVideos.clickAddAVideo();
		videoName = vetAddingVideo.addVideoByQuery(VideoContent.wikiaVideoQuery, 0);
		specialVideos.verifyVideoAdded(videoName);
	}
	
	@Test(groups = { "VetTests003", "VetTests" })
	public void Vet_Tests_003_RelatedVideosProvider() {
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode rVmoduleMessageEdit = new WikiArticleEditMode(driver, Global.DOMAIN, "");		
		rVmoduleMessageEdit.editArticleByName("MediaWiki:RelatedVideosGlobalList");
		rVmoduleMessageEdit.deleteUnwantedVideoFromMessage(VideoContent.youtubeVideoURL2name);
		WikiArticlePageObject article = rVmoduleMessageEdit.clickOnPublishButton();
		article.openRandomArticleByUrl();
		article.verifyRVModulePresence();
		VetAddVideoComponentObject vetAddingVideo = article.clickOnAddVideoRVModule();;
		vetAddingVideo.addVideoByUrl(VideoContent.youtubeVideoURL2);
		article.verifyVideoAddedToRVModule(VideoContent.youtubeVideoURL2name);
	}
	
	@Test(groups = { "VetTests004", "VetTests" })
	public void Vet_Tests_004_RelatedVideosLibrary() {
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode rVmoduleMessageEdit = new WikiArticleEditMode(driver, Global.DOMAIN, "");		
		rVmoduleMessageEdit.editArticleByName("MediaWiki:RelatedVideosGlobalList");
		rVmoduleMessageEdit.deleteUnwantedVideoFromMessage(VideoContent.youtubeVideoURL2name);
		WikiArticlePageObject article = rVmoduleMessageEdit.clickOnPublishButton();
		article.openRandomArticleByUrl();
		article.verifyRVModulePresence();
		VetAddVideoComponentObject vetAddingVideo = article.clickOnAddVideoRVModule();
		vetAddingVideo.addVideoByQuery(VideoContent.wikiaVideoQuery, 0);
		article.verifyVideoAddedToRVModule(VideoContent.wikiaVideoName);
	}
	
	@Test(groups = { "VetTests005", "VetTests" })
	public void Vet_Tests_005_ArticlePlaceholderPublishedPageProvider() {
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		pageName =PageContent.articleNamePrefix+wiki.getTimeStamp();
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewArticle(pageName, 1);
		WikiArticlePageObject article = edit.clickOnPublishButton();
		article.verifyPageTitle(pageName);
		VetAddVideoComponentObject vetAddingVideo = article.clickAddVideoPlaceholder();
		VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.submit();
		article.verifyVideoOnThePage();
	}
	
//	@Test(groups = { "VetTests006", "VetTests" })
//	public void Vet_Tests_006_ArticlePlaceholderPublishedPageLibrary() {
//	}
////		
	@Test(groups = { "VetTests007", "VetTests" })
	public void Vet_Tests_007_ArticlePlaceholderEditModePageProvider() {
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		pageName =PageContent.articleNamePrefix+wiki.getTimeStamp();
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewArticle(pageName, 1);
		VetAddVideoComponentObject vetAddingVideo = edit.clickModifyButtonVideoPlaceholder();
		VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.setCaption(PageContent.caption);
		vetOptions.submit();
		edit.verifyVideoInEditMode(PageContent.caption);
		WikiArticlePageObject article = edit.clickOnPublishButton();
		article.verifyVideoOnThePage();
	}
//	
//	@Test(groups = { "VetTests008", "VetTests" })
//	public void Vet_Tests_008_ArticlePlaceholderEditModePageLibrary() {}	
//	
//	@Test(groups = { "VetTests009", "VetTests" })
//	public void Vet_Tests_009_BlogProvider() {}
//	
//	@Test(groups = { "VetTests010", "VetTests" })
//	public void Vet_Tests_010_BlogLibrary() {}	
//	
//	@Test(groups = { "VetTests011", "VetTests" })
//	public void Vet_Tests_011_CommentsProvider() {}
//	
//	@Test(groups = { "VetTests012", "VetTests" })
//	public void Vet_Tests_012_CommentsLibrary() {}
//	
//	@Test(groups = { "VetTests013", "VetTests" })
//	public void Vet_Tests_013_MessageWallProvider() {}
//	
//	@Test(groups = { "VetTests014", "VetTests" })
//	public void Vet_Tests_014_MessageWallLibrary() {}
}
