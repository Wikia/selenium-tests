package com.wikia.webdriver.TestCases.VideoTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.MiniEditor.MiniEditorComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetOptionsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.MessageWall.MessageWallPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.MessageWall.NewMessageWall;

/*
 * Documentation:
 * https://docs.google.com/a/wikia-inc.com/spreadsheet/ccc?key=0AtG89yMxyGSadEtPY28ydDB4czkydXNmMkJVQ2NGR0E#gid=7
 */

public class VetAddingVideoTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(groups = {"VetAddVideo_001", "VetTests", "VetAddVideo"})
	public void VetAddVideo_001_MessageWallProvider() {
		WikiBasePageObject base = new WikiBasePageObject(driver);

		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		NewMessageWall wall = base.openMessageWall(credentials.userName, wikiURL);
		String message = PageContent.messageWallMessagePrefix + wall.getTimeStamp();
		String title = PageContent.messageWallTitlePrefix+ wall.getTimeStamp();


		MiniEditorComponentObject mini = wall.triggerMessageArea();
		wall.clickBoldButton();
		mini.switchAndWrite(message);
		wall.writeTitle(title);
//		wall.submit();

		VetAddVideoComponentObject vetAddingVideo = mini.clickAddVideo();
		VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByUrl(VideoContent.youtubeVideoURL2);
		vetOptions.setCaption(PageContent.caption);
		vetOptions.submit();
		mini.verifyVideoMiniEditor();
		wall.submit();
		wall.verifyMessageText(title, message, credentials.userName);
	}

	@Test(groups = {"VetAddVideo_002", "VetTests", "VetAddVideo"})
	public void VetAddVideo_002_MessageWallLibrary() {
		MessageWallPageObject wall = new MessageWallPageObject(driver);
		String timeStamp = wall.getTimeStamp();
		String title = PageContent.messageWallTitlePrefix + timeStamp;
		wall.openWikiPage();
		wall.logInCookie(credentials.userName, credentials.password, wikiURL);
		wall.openMessageWall(credentials.userName);
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
