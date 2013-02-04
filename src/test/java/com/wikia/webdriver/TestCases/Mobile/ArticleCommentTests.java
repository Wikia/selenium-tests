package com.wikia.webdriver.TestCases.Mobile;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileBasePageObject;

public class ArticleCommentTests extends TestTemplate{

	String timeStamp;
	
	@Test(groups={"mobile"})
	public void ArticleCommentTest_001_PostComment(){
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome();
		MobileArticlePageObject article = mobile.openRandomPage();
		timeStamp = article.getTimeStamp();
		article.addComment("QAComment"+timeStamp);
		
	}
	
	@Test(groups={"mobile"})
	public void ArticleCommentTest_002_PostCommentReply(){
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome();
		MobileArticlePageObject article = mobile.openRandomPage();
		timeStamp = article.getTimeStamp();
		article.addComment("QAComment"+timeStamp);
		article.addReply("QAReply"+timeStamp);
	}
	
	@Test(groups={"mobile"})
	public void ArticleCommentTest_003_CommentPagination(){
		MobileBasePageObject mobile = new MobileBasePageObject(driver);
		mobile.openHome();
		MobileArticlePageObject article = mobile.openCommentsWithPagination();
		article.verifyPagination();
	}
}
