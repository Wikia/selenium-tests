package com.wikia.webdriver.TestCases.MessageWall;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.MiniEditor.MiniEditorComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.PreviewEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.MessageWall.NewMessageWall;
import com.wikia.webdriver.PageObjectsFactory.PageObject.MessageWall.NewMessageWallCloseRemoveThreadPageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 * 1. Write message, edit
 * 2. Write message, remove,
 * 3. Write message, close,
 * 4. Write message, quote,
 * 5. Write message, preview
 * 6. Write message, reply
 *
 */
public class MessageWallTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(groups = {"MessageWall_001", "MessageWall"})
	public void MessageWall_001_writeEdit() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		NewMessageWall wall = base.openMessageWall(credentials.userName, wikiURL);
		MiniEditorComponentObject mini = wall.triggerMessageArea();
		String message = PageContent.messageWallMessagePrefix + wall.getTimeStamp();
		String title = PageContent.messageWallTitlePrefix+ wall.getTimeStamp();
		mini.switchAndWrite(message);
		wall.writeTitle(title);
		wall.submit();
		wall.verifyMessageText(title, message, credentials.userName);
		wall.triggerEditMessageArea();
		String messageEdit = PageContent.messageWallMessageEditPrefix + wall.getTimeStamp();
		mini.switchAndEditMessageWall(messageEdit);
		wall.submitEdition();
		wall.verifyMessageEditText(title, messageEdit, credentials.userName);
	}

	@Test(groups = {"MessageWall_002", "MessageWall"})
	public void MessageWall_002_writeRemove() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		NewMessageWall wall = base.openMessageWall(credentials.userName, wikiURL);
		MiniEditorComponentObject mini = wall.triggerMessageArea();
		String message = PageContent.messageWallMessagePrefix + wall.getTimeStamp();
		String title = PageContent.messageWallTitlePrefix+ wall.getTimeStamp();
		mini.switchAndWrite(message);
		wall.writeTitle(title);
		wall.submit();
		wall.verifyMessageText(title, message, credentials.userName);
		NewMessageWallCloseRemoveThreadPageObject remove = wall.clickRemoveThread();
		remove.closeRemoveThread("adss");
		wall.verifyThreadRemoved();
	}

	@Test(groups = {"MessageWall_003", "MessageWall"})
	public void MessageWall_003_writeClose() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		NewMessageWall wall = base.openMessageWall(credentials.userNameStaff, wikiURL);
		MiniEditorComponentObject mini = wall.triggerMessageArea();
		String message = PageContent.messageWallMessagePrefix + wall.getTimeStamp();
		String title = PageContent.messageWallTitlePrefix+ wall.getTimeStamp();
		mini.switchAndWrite(message);
		wall.writeTitle(title);
		wall.submit();
		wall.verifyMessageText(title, message, credentials.userNameStaff);
		NewMessageWallCloseRemoveThreadPageObject remove = wall.clickCloseThread();
		String reason = PageContent.closeReason + wall.getTimeStamp();
		remove.closeRemoveThread(reason);
		wall.verifyThreadClosed(credentials.userNameStaff, reason, title);
		wall.clickReopenThread();
		wall.verifyThreadReopened();
	}

	@Test(groups = {"MessageWall_004", "MessageWall"})
	public void MessageWall_004_writeQuote() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		NewMessageWall wall = base.openMessageWall(credentials.userNameStaff, wikiURL);
		MiniEditorComponentObject mini = wall.triggerMessageArea();
		String message = PageContent.messageWallMessagePrefix + wall.getTimeStamp();
		String title = PageContent.messageWallTitlePrefix+ wall.getTimeStamp();
		mini.switchAndWrite(message);
		wall.writeTitle(title);
		wall.submit();
		wall.verifyMessageText(title, message, credentials.userNameStaff);
		MiniEditorComponentObject miniQuote = wall.clickQuoteButton();
		String quote = PageContent.messageWallQuotePrefix + wall.getTimeStamp();
		miniQuote.switchAndQuoteMessageWall(quote);
		wall.submitQuote();
		wall.verifyQuote(quote);
	}

	@Test(groups = {"MessageWall_005", "MessageWall"})
	public void MessageWall_005_writePreview() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		NewMessageWall wall = base.openMessageWall(credentials.userName, wikiURL);
		MiniEditorComponentObject mini = wall.triggerMessageArea();
		String message = PageContent.messageWallMessagePrefix + wall.getTimeStamp();
		String title = PageContent.messageWallTitlePrefix+ wall.getTimeStamp();
		mini.switchAndWrite(message);
		wall.writeTitle(title);
		PreviewEditModePageObject preview = wall.preview();
		preview.verifyTextContent(message);
		preview.publish();
		wall.verifyMessageText(title, message, credentials.userName);
	}

	@Test(groups = {"MessageWall_005", "MessageWall"})
	public void MessageWall_006_writeReply() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		NewMessageWall wall = base.openMessageWall(credentials.userName, wikiURL);
		MiniEditorComponentObject mini = wall.triggerMessageArea();
		String message = PageContent.messageWallMessagePrefix + wall.getTimeStamp();
		String title = PageContent.messageWallTitlePrefix+ wall.getTimeStamp();
		mini.switchAndWrite(message);
		wall.writeTitle(title);
		wall.submit();
		wall.verifyMessageText(title, message, credentials.userName);
		MiniEditorComponentObject miniReply = wall.triggerReplyMessageArea();
		String reply = PageContent.messageWallQuotePrefix + wall.getTimeStamp();
		miniReply.switchAndQuoteMessageWall(reply);;
		wall.submitQuote();
		wall.verifyQuote(reply);
	}
}
