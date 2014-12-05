package com.wikia.webdriver.testcases.mobile;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileBasePageObject;
/*
 * @author PMG
 *
 * Below test cases are executed against mobileregressiontesting wikiName with CHROMEMOBILE browser
 * 1. Verify that you are able to post a comment
 * 2. Verify that you are able to post a reply comment
 * 3. Verify that pagination of comments is correct
 * 4. Verify that comments counter is updated correctly
 * 5. Verify that when user go to input comment field - this field will expand
 */
public class MobileArticleCommentTests extends NewTestTemplate {

	String timeStamp;

	@Test(groups={"MobileArticleComment_001", "MobileArticleComment", "Mobile"})
	public void MobileArticleComment_001_PostComment() {
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome(wikiURL);
		MobileArticlePageObject article = mobile.openRandomPage();
		timeStamp = article.getTimeStamp();
		article.addComment(PageContent.COMMENT_TEXT_NON_LATIN + timeStamp);
	}

	@Test(groups={"MobileArticleComment_002", "MobileArticleComment", "Mobile"})
	public void MobileArticleComment_002_PostCommentReply() {
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome(wikiURL);
		MobileArticlePageObject article = mobile.openRandomPage();
		timeStamp = article.getTimeStamp();
		article.addComment(PageContent.COMMENT_TEXT_NON_LATIN + timeStamp);
		article.addReply(PageContent.REPLY_TEXT2 + timeStamp);
	}

	@Test(groups={"MobileArticleComment_003", "MobileArticleComment", "Mobile"})
	public void MobileArticleComment_003_CommentPagination() {
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome(wikiURL);
		MobileArticlePageObject article = mobile.openCommentsWithPagination(wikiURL);
		article.showCommentsSection();
		String firstComment = article.getCommentInnerText();
		article.clickLoadMoreButton();
		article.verifyFirstCommentsNotEquals(firstComment);
		article.clickLoadPreviousButton();
		article.verifyFirstCommentsEquals(firstComment);
	}

	@Test(groups={"MobileArticleComment_004", "MobileArticleComment", "Mobile"})
	public void MobileArticleComment_004_numberOfComments() {
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome(wikiURL);
		MobileArticlePageObject article = mobile.openRandomPage();
		timeStamp = article.getTimeStamp();
		article.verifyNumberOfComments(PageContent.COMMENT_TEXT2 + timeStamp);
	}

	@Test(groups={"MobileArticleComment_005", "MobileArticleComment", "Mobile"})
	public void MobileArticleComment_005_expandInput() {
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome(wikiURL);
		MobileArticlePageObject article = mobile.openRandomPage();
		timeStamp = article.getTimeStamp();
		article.verifyInputExpand(PageContent.COMMENT_TEXT2 + timeStamp);
	}
}
