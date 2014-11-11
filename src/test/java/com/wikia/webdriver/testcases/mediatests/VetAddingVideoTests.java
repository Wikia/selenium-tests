package com.wikia.webdriver.testcases.mediatests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetOptionsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.messagewall.NewMessageWall;

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
		String message = PageContent.messageWallMessagePrefix + wall.getTimeStamp();
		String title = PageContent.messageWallTitlePrefix+ wall.getTimeStamp();
		MiniEditorComponentObject mini = wall.triggerMessageArea();
		wall.clickBoldButton();
		mini.switchAndWrite(message);
		wall.writeTitle(title);
		VetAddVideoComponentObject vetAddingVideo = mini.clickAddVideo();
		VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByUrl(VideoContent.youtubeVideoURL2);
		vetOptions.setCaption(PageContent.caption);
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
		String message = PageContent.messageWallMessagePrefix + wall.getTimeStamp();
		String title = PageContent.messageWallTitlePrefix+ wall.getTimeStamp();
		MiniEditorComponentObject mini = wall.triggerMessageArea();
		wall.clickBoldButton();
		mini.switchAndWrite(message);
		wall.writeTitle(title);
		VetAddVideoComponentObject vetAddingVideo = mini.clickAddVideo();
		VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByQuery(VideoContent.wikiaVideoQuery, 0);
		vetOptions.setCaption(PageContent.caption);
		vetOptions.submit();
		mini.verifyVideoMiniEditor();
		wall.submit();
		wall.verifyPostedMessageVideo(title);
	}
}
