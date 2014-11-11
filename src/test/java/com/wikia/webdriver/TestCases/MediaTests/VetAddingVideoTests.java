package com.wikia.webdriver.TestCases.MediaTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.MiniEditor.MiniEditorComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetOptionsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.MessageWall.NewMessageWall;

/*
 * Documentation:
 * https://docs.google.com/a/wikia-inc.com/spreadsheet/ccc?key=0AtG89yMxyGSadEtPY28ydDB4czkydXNmMkJVQ2NGR0E#gid=7
 */

public class VetAddingVideoTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(groups = {"VetAddVideo_001", "VetTests", "VetAddVideo", "Media"})
	public void VetAddVideo_001_MessageWallProvider() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		NewMessageWall wall = base.openMessageWall(credentials.userName, wikiURL);
		String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
		String title = PageContent.MESSAGE_WALL_TITLE_PREFIX+ wall.getTimeStamp();
		MiniEditorComponentObject mini = wall.triggerMessageArea();
		wall.clickBoldButton();
		mini.switchAndWrite(message);
		wall.writeTitle(title);
		VetAddVideoComponentObject vetAddingVideo = mini.clickAddVideo();
		VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByUrl(VideoContent.youtubeVideoURL2);
		vetOptions.setCaption(PageContent.CAPTION);
		vetOptions.submit();
		mini.verifyVideoMiniEditor();
		wall.submit();
		wall.verifyPostedMessageVideo(title);
	}

	@Test(groups = {"VetAddVideo_002", "VetTests", "VetAddVideo", "Media"})
	public void VetAddVideo_002_MessageWallLibrary() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		NewMessageWall wall = base.openMessageWall(credentials.userName, wikiURL);
		String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
		String title = PageContent.MESSAGE_WALL_TITLE_PREFIX+ wall.getTimeStamp();
		MiniEditorComponentObject mini = wall.triggerMessageArea();
		wall.clickBoldButton();
		mini.switchAndWrite(message);
		wall.writeTitle(title);
		VetAddVideoComponentObject vetAddingVideo = mini.clickAddVideo();
		VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByQuery(VideoContent.wikiaVideoQuery, 0);
		vetOptions.setCaption(PageContent.CAPTION);
		vetOptions.submit();
		mini.verifyVideoMiniEditor();
		wall.submit();
		wall.verifyPostedMessageVideo(title);
	}
}
