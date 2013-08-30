package com.wikia.webdriver.TestCases.VideoTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.MiniEditor.MiniEditorComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetOptionsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.MessageWall.MessageWallPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;

/*
 * Documentation:
 * https://docs.google.com/a/wikia-inc.com/spreadsheet/ccc?key=0AtG89yMxyGSadEtPY28ydDB4czkydXNmMkJVQ2NGR0E#gid=7
 */

public class VetAddingVideoTests extends TestTemplate {

	String desiredVideoName;

	@Test(groups = {"VetTests011", "VetTests", "VetAddVideo"})
	public void Vet_Tests_009_CommentsProvider() {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		article.openRandomArticle();
		article.triggerCommentArea();
		MiniEditorComponentObject mini = new MiniEditorComponentObject(driver);
		VetAddVideoComponentObject vetAddingVideo = mini.clickAddVideo();
		VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByUrl(VideoContent.youtubeVideoURL2);
		vetOptions.setCaption(PageContent.caption);
		vetOptions.submit();
		mini.verifyVideoMiniEditor();
		article.clickSubmitButton();
		article.verifyCommentVideo(VideoContent.youtubeVideoURL2name);
	}

	@Test(groups = {"VetTests012", "VetTests", "VetAddVideo"})
	public void Vet_Tests_010_CommentsLibrary() {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		article.openRandomArticle();
		article.triggerCommentArea();
		MiniEditorComponentObject mini = new MiniEditorComponentObject(driver);
		VetAddVideoComponentObject vetAddingVideo = mini.clickAddVideo();
		VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByQuery(VideoContent.wikiaVideoQuery, 0);
		vetOptions.setCaption(PageContent.caption);
		desiredVideoName = vetOptions.getVideoName();
		vetOptions.submit();
		mini.verifyVideoMiniEditor();
		article.clickSubmitButton();
		article.verifyCommentVideo(desiredVideoName);
	}

	@Test(groups = {"VetTests013", "VetTests", "VetAddVideo"})
	public void Vet_Tests_011_MessageWallProvider() {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		MessageWallPageObject wall = new MessageWallPageObject(driver);
		String timeStamp = wall.getTimeStamp();
		String title = PageContent.messageWallTitlePrefix + timeStamp;
		wall.openWikiPage();
		login.logInCookie(Properties.userName, Properties.password);
		wall.openMessageWall(Properties.userName);
		MiniEditorComponentObject mini = new MiniEditorComponentObject(driver);
		wall.writeMessage(title, "");
		VetAddVideoComponentObject vetAddingVideo = mini.clickAddVideo();
		VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByUrl(VideoContent.youtubeVideoURL2);
		vetOptions.setCaption(PageContent.caption);
		vetOptions.submit();
		mini.verifyVideoMiniEditor();
		wall.clickPostButton();
		wall.verifyPostedMessageVideo(title);
	}

	@Test(groups = {"VetTests014", "VetTests", "VetAddVideo"})
	public void Vet_Tests_012_MessageWallLibrary() {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		MessageWallPageObject wall = new MessageWallPageObject(driver);
		String timeStamp = wall.getTimeStamp();
		String title = PageContent.messageWallTitlePrefix + timeStamp;
		wall.openWikiPage();
		login.logInCookie(Properties.userName, Properties.password);
		wall.openMessageWall(Properties.userName);
		MiniEditorComponentObject mini = new MiniEditorComponentObject(driver);
		wall.writeMessage(title, "");
		VetAddVideoComponentObject vetAddingVideo = mini.clickAddVideo();
		VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByQuery(VideoContent.wikiaVideoQuery, 0);
		vetOptions.setCaption(PageContent.caption);
		vetOptions.submit();
		mini.verifyVideoMiniEditor();
		wall.clickPostButton();
		wall.verifyPostedMessageVideo(title);
	}
}
