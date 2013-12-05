package com.wikia.webdriver.TestCases.MessageWall;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.SourceModeContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.MiniEditor.MiniEditorComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.MiniEditor.MiniEditorPreviewComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.MessageWall.NewMessageWall;
import com.wikia.webdriver.PageObjectsFactory.PageObject.MessageWall.NewMessageWallCloseRemoveThreadPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Block.SpecialBlockListPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Block.SpecialBlockPageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 * 1. Write and edit message,
 * 2. Write and remove message,
 * 3. Write and close message,,
 * 4. Write and quote message,
 * 5. Write and preview message,
 * 6. Write and reply message,
 */
public class MessageWallTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(groups = {"MessageWall_001", "MessageWall", "Smoke3"})
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
		MiniEditorPreviewComponentObject preview = wall.preview();
		preview.verifyTextContent(message);
		preview.publish();
		wall.verifyMessageText(title, message, credentials.userName);
	}

	@Test(groups = {"MessageWall_006", "MessageWall"})
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

	/**
	 * DAR-985 bug prevention test case
	 * details fogbugz: https://wikia.fogbugz.com/default.asp?31293,
	 * details jira:    https://wikia-inc.atlassian.net/browse/DAR-985
	 * 1. Go to a messageWall and add unClosedDivComment
	 * 2. refresh the page
	 * 3. make sure that reply area avatar doesn't appear by default
	 */
	@Test(groups = {"MessageWall_007", "MessageWall"})
	public void MessageWall_007_unclosedTagPost() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		NewMessageWall wall = base.openMessageWall(credentials.userName11, wikiURL);
		wall.triggerMessageArea();
		String title = PageContent.messageWallTitlePrefix+ wall.getTimeStamp();
		wall.clickSourceModeButton();
		wall.writeSourceMode(SourceModeContent.unclosedDivComment);
		wall.writeTitle(title);
		wall.submit();
		wall.verifyMessageTitle(title);
		wall.refreshPage();
		wall.verifyReplyAreaAvatarNotVisible();
	}

	/**
	 * DAR-2133 bug prevention test case
	 * details jira:    https://wikia-inc.atlassian.net/browse/DAR-2133
	 * pre: test makes sure that QATestsBlockedUser is blocked on tested wikia.
	 * 1. user QATestsBlockedUser is allowed to post on his own MessageWall
	 * 2. as QATestsBlockedUser go to his messageWall
	 * 3. QATestsBlockedUser should be able to post on his MessageWall
	 * 4. QATestsBlockedUser should be able to respond on his MessageWall
	 */
	//@Test(
	//		enabled = false, //fix blocking user IP together with his username
	//		groups = {"MessageWall_008", "MessageWall"}
	//)
	public void MessageWall_008_blockedUserPostsOnHisWall() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		SpecialBlockListPageObject blockListPage = base.openSpecialBlockListPage(wikiURL);
		boolean isUserBlocked = blockListPage.isUserBlocked(credentials.userNameBlockedAccount);
		if (!isUserBlocked) {
			base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
			SpecialBlockPageObject blockPage = base.openSpecialBlockPage(wikiURL);
			blockPage.typeInUserName(credentials.userNameBlockedAccount);
			blockPage.typeExpiration("10 year");
			blockPage.typeReason("block QATestsBlockedUser");
			blockPage.clickBlockButton();
		}
		base.logInCookie(credentials.userNameBlockedAccount, credentials.passwordBlockedAccount, wikiURL);
		NewMessageWall wall = base.openMessageWall(credentials.userNameBlockedAccount, wikiURL);
		MiniEditorComponentObject mini = wall.triggerMessageArea();
		String message = PageContent.messageWallMessagePrefix + wall.getTimeStamp();
		String title = PageContent.messageWallTitlePrefix+ wall.getTimeStamp();
		mini.switchAndWrite(message);
		wall.writeTitle(title);
		wall.submit();
		wall.verifyMessageText(title, message, credentials.userNameBlockedAccount);
		MiniEditorComponentObject miniReply = wall.triggerReplyMessageArea();
		String reply = PageContent.messageWallQuotePrefix + wall.getTimeStamp();
		miniReply.switchAndQuoteMessageWall(reply);
		wall.submitQuote();
		wall.verifyQuote(reply);
	}
}
