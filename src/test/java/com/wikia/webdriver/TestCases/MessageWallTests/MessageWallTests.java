package com.wikia.webdriver.TestCases.MessageWallTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.MessageWall.MessageWallHistoryPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.MessageWall.MessageWallPageObject;

public class MessageWallTests extends NewTestTemplate
{
        Credentials credentials = config.getCredentials();

        String timeStamp;
        String title;
        String message;
        String titleEdit;
        String messageEdit;
        String Externallink;
        String Internallink;
        String sourceMessage;

	@Test(groups = { "MessageWall001", "MessageWall" })
	public void MessageWall_001_WriteMessage() {
		MessageWallPageObject wall = new MessageWallPageObject(driver);
		timeStamp = wall.getTimeStamp();
		title = PageContent.messageWallTitlePrefix + timeStamp;
		message = PageContent.messageWallMessagePrefix + timeStamp;
		wall.logInCookie(credentials.userName, credentials.password, wikiURL);
		wall.openMessageWall(credentials.userName, wikiURL);
		wall.writeMessage(title, message);
		wall.clickPostButton();
		wall.verifyPostedMessageWithTitle(title, message);
	}

	@Test(groups = { "MessageWall002", "MessageWall" })
	public void MessageWall_002_WriteMessageNoTitle() {

		MessageWallPageObject wall = new MessageWallPageObject(driver);
		timeStamp = wall.getTimeStamp();
		message = PageContent.messageWallMessagePrefix + timeStamp;
                wall.logInCookie(credentials.userName, credentials.password, wikiURL);
		wall.openMessageWall(credentials.userName, wikiURL);
		wall.writeMessageNoTitle(message);
		wall.clickPostNotitleButton();
		wall.verifyPostedMessageWithoutTitle(credentials.userName, message);
	}

	@Test(groups = { "MessageWall003", "MessageWall" })
	public void MessageWall_003_WriteMessageImage() {

		MessageWallPageObject wall = new MessageWallPageObject(driver);
		timeStamp = wall.getTimeStamp();
		title = PageContent.messageWallTitlePrefix + timeStamp;
		wall.logInCookie(credentials.userName, credentials.password, wikiURL);
		wall.openMessageWall(credentials.userName, wikiURL);
		wall.writeMessageImage(title);
		wall.clickPostButton();
		wall.verifyPostedMessageImage(title);
	}

	@Test(groups = { "MessageWall004", "MessageWall" })
	public void MessageWall_004_WriteMessageVideo() {

		MessageWallPageObject wall = new MessageWallPageObject(driver);
		timeStamp = wall.getTimeStamp();
		title = PageContent.messageWallTitlePrefix + timeStamp;
		wall.logInCookie(credentials.userName, credentials.password, wikiURL);
		wall.openMessageWall(credentials.userName, wikiURL);
		wall.writeMessageVideo(title, VideoContent.youtubeVideoURL3);
		wall.clickPostButton();
		wall.verifyPostedMessageVideo(title);
	}

	@Test(groups = { "MessageWall005", "MessageWall" })
	public void MessageWall_005_WriteAndEditMessageWithoutTitle() {

		MessageWallPageObject wall = new MessageWallPageObject(driver);
		timeStamp = wall.getTimeStamp();
		message = PageContent.messageWallMessagePrefix + timeStamp;
		titleEdit = PageContent.messageWallTitleEditPrefix + timeStamp;
		messageEdit = PageContent.messageWallMessageEditPrefix + timeStamp;
		wall.logInCookie(credentials.userName, credentials.password, wikiURL);
		wall.openMessageWall(credentials.userName, wikiURL);
		wall.writeMessageNoTitle(message);
		wall.clickPostNotitleButton();
		wall.verifyPostedMessageWithoutTitle(credentials.userName, message);
		wall.editMessage(titleEdit, messageEdit);
		wall.verifyPostedMessageWithTitle(titleEdit, messageEdit);
	}

	@Test(groups = { "MessageWall006", "MessageWall" })
	public void MessageWall_006_WriteMessagePreview() {

		MessageWallPageObject wall = new MessageWallPageObject(driver);
		timeStamp = wall.getTimeStamp();
		title = PageContent.messageWallTitlePrefix + timeStamp;
		message = PageContent.messageWallMessagePrefix + timeStamp;
		wall.logInCookie(credentials.userName, credentials.password, wikiURL);
		wall.openMessageWall(credentials.userName, wikiURL);
		wall.writeMessage(title, message);
		wall.clickPreviewButton();
		wall.clickPublishButton();
		wall.verifyPostedMessageWithTitle(title, message);
	}

	@Test(groups = { "MessageWall007", "MessageWall" })
	public void MessageWall_007_WriteMessageWithInternalLink() {
		MessageWallPageObject wall = new MessageWallPageObject(driver);
		timeStamp = wall.getTimeStamp();
		title = PageContent.messageWallTitlePrefix + timeStamp;
		Internallink = PageContent.internalLink;
		wall.logInCookie(credentials.userName, credentials.password, wikiURL);
		wall.openMessageWall(credentials.userName, wikiURL);
		wall.writeMessageWithInternalLink(Internallink, title);
		wall.clickPostButton();
		wall.verifyPostedMessageWithLinks(Internallink);
	}

	@Test(groups = { "MessageWall008", "MessageWall" })
	public void MessageWall_008_RemoveMessage() {
		MessageWallPageObject wall = new MessageWallPageObject(driver);
		timeStamp = wall.getTimeStamp();
		title = PageContent.messageWallTitlePrefix + timeStamp;
		message = PageContent.messageWallMessagePrefix + timeStamp;
		wall.logInCookie(credentials.userName, credentials.password, wikiURL);
		wall.openMessageWall(credentials.userName, wikiURL);
		wall.writeMessage(title, message);
		wall.clickPostButton();
		wall.verifyPostedMessageWithTitle(title, message);
		wall.removeMessage("reason");
	}

	@Test(groups = { "MessageWall009", "MessageWall" })
	public void MessageWall_009_WriteAndEditMessage() {
		MessageWallPageObject wall = new MessageWallPageObject(driver);
		timeStamp = wall.getTimeStamp();
		title = PageContent.messageWallTitlePrefix + timeStamp;
		message = PageContent.messageWallMessagePrefix + timeStamp;
		titleEdit = PageContent.messageWallTitleEditPrefix + timeStamp;
		messageEdit = PageContent.messageWallMessageEditPrefix + timeStamp;
		wall.logInCookie(credentials.userName, credentials.password, wikiURL);
		wall.openMessageWall(credentials.userName, wikiURL);
		wall.writeMessage(title, message);
		wall.clickPostButton();
		wall.verifyPostedMessageWithTitle(title, message);
		wall.editMessage(titleEdit, messageEdit);
		wall.verifyPostedMessageWithTitle(titleEdit, messageEdit);
	}

	@Test(groups = { "MessageWall011", "MessageWall" })
	public void MessageWall_011_WriteNonLatinMessage() {

		MessageWallPageObject wall = new MessageWallPageObject(driver);
		timeStamp = wall.getTimeStamp();
		title = PageContent.messageWallTitlePrefix + timeStamp;
		message = PageContent.messageWallMessageNonLatinPrefix + timeStamp;
		wall.logInCookie(credentials.userName, credentials.password, wikiURL);
		wall.openMessageWall(credentials.userName, wikiURL);
		wall.writeMessage(title, message);
		wall.clickPostButton();
		wall.verifyPostedMessageWithTitle(title, message);
	}

	@Test(groups = { "MessageWall012", "MessageWall" })
	public void MessageWall_012_WriteItalicMessage() {
		MessageWallPageObject wall = new MessageWallPageObject(driver);
		timeStamp = wall.getTimeStamp();
		title = PageContent.messageWallTitlePrefix + timeStamp;
		message = PageContent.messageWallMessagePrefix + timeStamp;
		wall.logInCookie(credentials.userName, credentials.password, wikiURL);
		wall.openMessageWall(credentials.userName, wikiURL);
		wall.writeItalicMessage(title, message);
		wall.clickPostButton();
		wall.verifyPostedItalicMessageWithTitle(title, message);
	}

	@Test(groups = { "MessageWall013", "MessageWall" })
	public void MessageWall_013_WriteMessageSourceMode() {
		MessageWallPageObject wall = new MessageWallPageObject(driver);
		timeStamp = wall.getTimeStamp();
		title = PageContent.messageWallTitlePrefix + timeStamp;
		sourceMessage = "'''bold" + timeStamp + "'''";
		message = "bold" + timeStamp;
		wall.logInCookie(credentials.userName, credentials.password, wikiURL);
		wall.openMessageWall(credentials.userName, wikiURL);
		wall.writeMessageSourceMode(title, sourceMessage);
		wall.clickPostButton();
		wall.verifyPostedBoldMessageWithTitle(title, message);
	}

	@Test(groups = { "MessageWall014", "MessageWall" })
	public void MessageWall_014_CheckHistory() {
		MessageWallPageObject wall = new MessageWallPageObject(driver);
		timeStamp = wall.getTimeStamp();
		title = PageContent.messageWallTitlePrefix + timeStamp;
		message = PageContent.messageWallMessagePrefix + timeStamp;
		wall.logInCookie(credentials.userName, credentials.password, wikiURL);
		wall.openMessageWall(credentials.userName, wikiURL);
		wall.writeMessage(title, message);
		wall.clickPostButton();
		wall.verifyPostedMessageWithTitle(title, message);
		MessageWallHistoryPageObject threadHistory = wall.openHistory();
		threadHistory.verifyThreadHistory();
		threadHistory.verifyThreadHistoryElements();
		threadHistory.navigateBackToMessageWall();
	}

	@Test(groups = { "MessageWall015", "MessageWall" })
	public void MessageWall_015_WriteBoldMessage() {

		MessageWallPageObject wall = new MessageWallPageObject(driver);
		timeStamp = wall.getTimeStamp();
		title = PageContent.messageWallTitlePrefix + timeStamp;
		message = PageContent.messageWallMessagePrefix + timeStamp;
		wall.logInCookie(credentials.userName, credentials.password, wikiURL);
		wall.openMessageWall(credentials.userName, wikiURL);
		wall.writeBoldMessage(title, message);
		wall.clickPostButton();
		wall.verifyPostedBoldMessageWithTitle(title, message);
	}

        @Test(groups = { "MessageWall016", "MessageWall" })
	public void MessageWall_016_WriteMessageWithExternalLink() {
		MessageWallPageObject wall = new MessageWallPageObject(driver);
		timeStamp = wall.getTimeStamp();
		title = PageContent.messageWallTitlePrefix + timeStamp;
		Externallink = PageContent.externalLink;
		wall.logInCookie(credentials.userName, credentials.password, wikiURL);
		wall.openMessageWall(credentials.userName, wikiURL);
		wall.writeMessageWithExternalLink(Externallink, title);
		wall.clickPostButton();
		wall.verifyPostedMessageWithLinks(Externallink);
	}
        
        @Test(groups = {"MessageWall017", "MessageWall" })
        public void MessageWall_017_CloseThread() {
                MessageWallPageObject wall = new MessageWallPageObject(driver);
		timeStamp = wall.getTimeStamp();
		title = PageContent.messageWallTitlePrefix + timeStamp;
		message = PageContent.messageWallMessagePrefix + timeStamp;
                wall.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		wall.openMessageWall(credentials.userName, wikiURL);
		wall.writeMessage(title, message);
		wall.clickPostButton();
		wall.verifyPostedMessageWithTitle(title, message);
		wall.closeThread(PageContent.messageWallCloseReopenReason);
                wall.verifyClosedThread();
                wall.reopenThread();
                wall.verifyReopenThread();
        }

}
