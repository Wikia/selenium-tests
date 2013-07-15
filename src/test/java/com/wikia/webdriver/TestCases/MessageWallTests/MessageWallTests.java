package com.wikia.webdriver.TestCases.MessageWallTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.MessageWall.MessageWallHistoryPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.MessageWall.MessageWallPageObject;

public class MessageWallTests extends TestTemplate
{
//	protected String url = "http://www.youtube.com/watch?v=LQjkDW3UPVk";

	String timeStamp;
	private String title;
	private String message;
	private String titleEdit;
	private String messageEdit;
	private String Externallink;
	private String Internallink;
	private String sourceMessage;

	@Test(groups = { "MessageWall001", "MessageWall" })
	public void MessageWall_001_WriteMessage() {

		MessageWallPageObject wall = new MessageWallPageObject(driver);
		timeStamp = wall.getTimeStamp();
		title = PageContent.messageWallTitlePrefix + timeStamp;
		message = PageContent.messageWallMessagePrefix + timeStamp;
		wall.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password);
		wall.openMessageWall(Properties.userName);
		wall.writeMessage(title, message);
		wall.clickPostButton();
		wall.verifyPostedMessageWithTitle(title, message);
		CommonFunctions.logOut(driver);
	}

	@Test(groups = { "MessageWall002", "MessageWall" })
	public void MessageWall_002_WriteMessageNoTitle() {

		MessageWallPageObject wall = new MessageWallPageObject(driver);
		timeStamp = wall.getTimeStamp();
		message = PageContent.messageWallMessagePrefix + timeStamp;
		wall.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password);
		wall.openMessageWall(Properties.userName);
		wall.writeMessageNoTitle(message);
		wall.clickPostNotitleButton();
		wall.verifyPostedMessageWithoutTitle(Properties.userName, message);
		CommonFunctions.logOut(driver);
	}

	@Test(groups = { "MessageWall003", "MessageWall" })
	public void MessageWall_003_WriteMessageImage() {

		MessageWallPageObject wall = new MessageWallPageObject(driver);
		timeStamp = wall.getTimeStamp();
		title = PageContent.messageWallTitlePrefix + timeStamp;
		wall.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password);
		wall.openMessageWall(Properties.userName);
		wall.writeMessageImage(title);
		wall.clickPostButton();
		wall.verifyPostedMessageImage(title);
		CommonFunctions.logOut(driver);
	}

	@Test(groups = { "MessageWall004", "MessageWall" })
	public void MessageWall_004_WriteMessageVideo() {

		MessageWallPageObject wall = new MessageWallPageObject(driver);
		timeStamp = wall.getTimeStamp();
		title = PageContent.messageWallTitlePrefix + timeStamp;
		wall.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password);
		wall.openMessageWall(Properties.userName);
		wall.writeMessageVideo(title, VideoContent.youtubeVideoURL3);
		wall.clickPostButton();
		wall.verifyPostedMessageVideo(title);
		CommonFunctions.logOut(driver);
	}

	@Test(groups = { "MessageWall005", "MessageWall" })
	public void MessageWall_005_WriteAndEditMessageWithoutTitle() {

		MessageWallPageObject wall = new MessageWallPageObject(driver);
		timeStamp = wall.getTimeStamp();
		message = PageContent.messageWallMessagePrefix + timeStamp;
		titleEdit = PageContent.messageWallTitleEditPrefix + timeStamp;
		messageEdit = PageContent.messageWallMessageEditPrefix + timeStamp;
		wall.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password);
		wall.openMessageWall(Properties.userName);
		wall.writeMessageNoTitle(message);
		wall.clickPostNotitleButton();
		wall.verifyPostedMessageWithoutTitle(Properties.userName, message);
		wall.editMessage(titleEdit, messageEdit);
		wall.verifyPostedMessageWithTitle(titleEdit, messageEdit);
		CommonFunctions.logOut(driver);
		
	}

	@Test(groups = { "MessageWall006", "MessageWall" })
	public void MessageWall_006_WriteMessagePreview() {

		MessageWallPageObject wall = new MessageWallPageObject(driver);
		timeStamp = wall.getTimeStamp();
		title = PageContent.messageWallTitlePrefix + timeStamp;
		message = PageContent.messageWallMessagePrefix + timeStamp;
		wall.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password);
		wall.openMessageWall(Properties.userName);
		wall.writeMessage(title, message);
		wall.clickPreviewButton();
		wall.clickPublishButton();
		wall.verifyPostedMessageWithTitle(title, message);
		CommonFunctions.logOut(driver);
	}

	@Test(groups = { "MessageWall007", "MessageWall" })
	public void MessageWall_007_WriteMessageWithLink() {

		MessageWallPageObject wall = new MessageWallPageObject(driver);
		timeStamp = wall.getTimeStamp();
		title = PageContent.messageWallTitlePrefix + timeStamp;
		Internallink = PageContent.internalLink;
		Externallink = PageContent.externalLink;
		wall.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password);
		wall.openMessageWall(Properties.userName);
		wall.writeMessageWithLink(Internallink, Externallink, title);
		wall.clickPostButton();
		wall.verifyPostedMessageWithLinks(Internallink, Externallink);
		CommonFunctions.logOut(driver);
	}

	@Test(groups = { "MessageWall008", "MessageWall" })
	public void MessageWall_008_RemoveMessage() {

		MessageWallPageObject wall = new MessageWallPageObject(driver);
		timeStamp = wall.getTimeStamp();
		title = PageContent.messageWallTitlePrefix + timeStamp;
		message = PageContent.messageWallMessagePrefix + timeStamp;
		wall.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password);
		wall.openMessageWall(Properties.userName);
		wall.writeMessage(title, message);
		wall.clickPostButton();
		wall.verifyPostedMessageWithTitle(title, message);
		wall.removeMessage(PageContent.messageWallCloseReopenReason);
		CommonFunctions.logOut(driver);
	}

	@Test(groups = { "MessageWall009", "MessageWall" })
	public void MessageWall_009_WriteAndEditMessage() {

		MessageWallPageObject wall = new MessageWallPageObject(driver);
		timeStamp = wall.getTimeStamp();
		title = PageContent.messageWallTitlePrefix + timeStamp;
		message = PageContent.messageWallMessagePrefix + timeStamp;
		titleEdit = PageContent.messageWallTitleEditPrefix + timeStamp;
		messageEdit = PageContent.messageWallMessageEditPrefix + timeStamp;
		wall.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password);
		wall.openMessageWall(Properties.userName);
		wall.writeMessage(title, message);
		wall.clickPostButton();
		wall.verifyPostedMessageWithTitle(title, message);
		wall.editMessage(titleEdit, messageEdit);
		wall.verifyPostedMessageWithTitle(titleEdit, messageEdit);
		CommonFunctions.logOut(driver);

	}

	@Test(groups = { "MessageWall011", "MessageWall" })
	public void MessageWall_011_WriteNonLatinMessage() {

		MessageWallPageObject wall = new MessageWallPageObject(driver);
		timeStamp = wall.getTimeStamp();
		title = PageContent.messageWallTitlePrefix + timeStamp;
		message = PageContent.messageWallMessageNonLatinPrefix + timeStamp;
		wall.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password);
		wall.openMessageWall(Properties.userName);
		wall.writeMessage(title, message);
		wall.clickPostButton();
		wall.verifyPostedMessageWithTitle(title, message);
		CommonFunctions.logOut(driver);
	}

	@Test(groups = { "MessageWall012", "MessageWall" })
	public void MessageWall_012_WriteItalicMessage() {
		MessageWallPageObject wall = new MessageWallPageObject(driver);
		timeStamp = wall.getTimeStamp();
		title = PageContent.messageWallTitlePrefix + timeStamp;
		message = PageContent.messageWallMessagePrefix + timeStamp;
		wall.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password);
		wall.openMessageWall(Properties.userName);
		wall.writeItalicMessage(title, message);
		wall.clickPostButton();
		wall.verifyPostedItalicMessageWithTitle(title, message);
		CommonFunctions.logOut(driver);
	}

	@Test(groups = { "MessageWall013", "MessageWall" })
	public void MessageWall_013_WriteMessageSourceMode() {

		MessageWallPageObject wall = new MessageWallPageObject(driver);
		timeStamp = wall.getTimeStamp();
		title = PageContent.messageWallTitlePrefix + timeStamp;
		sourceMessage = "'''bold" + timeStamp + "'''";
		message = "bold" + timeStamp;
		wall.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password);
		wall.openMessageWall(Properties.userName);
		wall.writeMessageSourceMode(title, sourceMessage);
		wall.clickPostButton();
		wall.verifyPostedBoldMessageWithTitle(title, message);
		CommonFunctions.logOut(driver);
	}

	@Test(groups = { "MessageWall014", "MessageWall" })
	public void MessageWall_014_CheckHistory() {
		MessageWallPageObject wall = new MessageWallPageObject(driver);
		timeStamp = wall.getTimeStamp();
		title = PageContent.messageWallTitlePrefix + timeStamp;
		message = PageContent.messageWallMessagePrefix + timeStamp;
		wall.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password);
		wall.openMessageWall(Properties.userName);
		wall.writeMessage(title, message);
		wall.clickPostButton();
		wall.verifyPostedMessageWithTitle(title, message);
		MessageWallHistoryPageObject threadHistory = wall.openHistory();
		threadHistory.verifyThreadHistory();
		threadHistory.verifyThreadHistoryElements();
		wall = threadHistory.navigateBackToMessageWall();
		CommonFunctions.logOut(driver);
	}

	@Test(groups = { "MessageWall015", "MessageWall" })
	public void MessageWall_015_WriteBoldMessage() {

		MessageWallPageObject wall = new MessageWallPageObject(driver);
		timeStamp = wall.getTimeStamp();
		title = PageContent.messageWallTitlePrefix + timeStamp;
		message = "bold" + timeStamp;
		wall.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password);
		wall.openMessageWall(Properties.userName);
		wall.writeBoldMessage(title, message);
		wall.clickPostButton();
		wall.verifyPostedBoldMessageWithTitle(title, message);
		CommonFunctions.logOut(driver);

	}

	@Test(groups = {"MessageWall016", "MessageWall" })
	public void MessageWall_016_CloseThread() {

		MessageWallPageObject wall = new MessageWallPageObject(driver);
		timeStamp = wall.getTimeStamp();
		title = PageContent.messageWallTitlePrefix + timeStamp;
		message = PageContent.messageWallMessagePrefix + timeStamp;
		wall.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		wall.openMessageWall(Properties.userNameStaff);
		wall.writeMessage(title, message);
		wall.clickPostButton();
		wall.verifyPostedMessageWithTitle(title, message);
		wall.closeThread(PageContent.messageWallCloseReopenReason);
		CommonFunctions.logOut(driver);
	}

	@Test(groups = {"MessageWall017", "MessageWall" })
	public void MessageWall_017_CloseAndReopenThread() {

		MessageWallPageObject wall = new MessageWallPageObject(driver);
		timeStamp = wall.getTimeStamp();
		title = PageContent.messageWallTitlePrefix + timeStamp;
		message = PageContent.messageWallMessagePrefix + timeStamp;
		wall.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		wall.openMessageWall(Properties.userNameStaff);
		wall.writeMessage(title, message);
		wall.clickPostButton();
		wall.verifyPostedMessageWithTitle(title, message);
		wall.closeThread(PageContent.messageWallCloseReopenReason);
		wall.reopenThread();
		CommonFunctions.logOut(driver);
	}
}
