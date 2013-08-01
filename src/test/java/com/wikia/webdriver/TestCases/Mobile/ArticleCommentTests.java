package com.wikia.webdriver.TestCases.Mobile;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import org.testng.annotations.Test;

import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileBasePageObject;

public class ArticleCommentTests extends NewTestTemplate{

	String timeStamp;

	@Test(groups={"articleCommentTest_001", "mobile"})
	public void ArticleCommentTest_001_PostComment(){
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome(wikiURL);
		MobileArticlePageObject article = mobile.openRandomPage();
		timeStamp = article.getTimeStamp();
		article.addComment(PageContent.commentText2+timeStamp);
	}

	@Test(groups={"articleCommentTest_002", "mobile"})
	public void ArticleCommentTest_002_PostCommentReply(){
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome(wikiURL);
		MobileArticlePageObject article = mobile.openRandomPage();
		timeStamp = article.getTimeStamp();
		article.addComment(PageContent.commentText2+timeStamp);
		article.addReply(PageContent.replyText2+timeStamp);
	}

	@Test(groups={"articleCommentTest_003", "mobile"})
	public void ArticleCommentTest_003_CommentPagination(){
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome(wikiURL);
		MobileArticlePageObject article = mobile.openCommentsWithPagination(wikiURL);
		article.verifyPagination();
	}
}
