package com.wikia.webdriver.TestCases.ArticleCRUDTests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.LightboxPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticleEditMode;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;

public class ArticleCRUDTestsAdmin extends TestTemplate{
	
	private String pageName;
	
	/*
	 * TestCase002
	 * Open random wiki page as logged in user
	 * Click edit drop-down
	 * Verify available edit options for logged in user (history and rename)
	 */
	@Test(groups={"ArticleCRUDAdmin_002", "ArticleCRUDAdmin"})
	public void ArticleCRUDAdmin_002_VerifyEditDropDown()
	{
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		String cookieName = CommonFunctions.logInCookie(Properties.userName, Properties.password);
		wiki.openRandomArticle();
		wiki.clickEditDropDown();
		wiki.verifyEditDropDownLoggedInUser();
		CommonFunctions.logoutCookie(cookieName);
	}
	
	/*
	 * TestCase003
	 * Open random wiki page as admin user
	 * Click edit drop-down
	 * Verify available edit options for admin user (history, rename, protect, delete)
	 */
	@Test(groups={"ArticleCRUDAdmin_003", "ArticleCRUDAdmin"})
	public void ArticleCRUDAdmin_003_VerifyEditDropDown()
	{
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		String cookieName = CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		wiki.openRandomArticle();
		wiki.clickEditDropDown();
		wiki.verifyEditDropDownAdmin();
		CommonFunctions.logoutCookie(cookieName);
	}
	
	/*
	 * TestCase004
	 * Create article as admin user with following names:
	 * 	normal: QAarticle
	 * 	non-latin: 這是文章的名字在中國
	 * 	long: QAVeryLongArticleNameQAVeryLongArticleNameQAVeryLongArticleNameQAVeryLongArticleNameQAVeryLongArticleNameQAVeryLongArticleName
	 * 	with slash: QA/article
	 * 	with underscore: QA_article
	 * 	made from digits:123123123123
	 * Delete article
	 */
			
	@DataProvider
	private static final Object[][] getArticleName()
	{
		return new Object[][]
				{
					{"QAarticle"},
					{"QAVeryLongArticleNameQAVeryLongArticleNameQAVeryLongArticleNameQAVeryLongArticleNameQAVeryLongArticleNameQAVeryLongArticleName"},
					{"QA/article"},
					{"QA_article"},
					{"123123123123"}
				};
	}	
	
	@Test(dataProvider="getArticleName", groups={"ArticleCRUDAdmin_004", "ArticleCRUDAdmin", "Smoke"})
	public void ArticleCRUDAdmin_004_CreateArticle(String articleName)
	{
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		pageName = articleName+wiki.getTimeStamp();
		wiki.openWikiPage();
		String cookieName = CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewArticle(pageName, 1);
		edit.deleteArticleContent();
//		edit.clickOnVisualButton();
		edit.typeInContent(PageContent.articleText);
		WikiArticlePageObject article = edit.clickOnPublishButton();
		article.verifyPageTitle(pageName);
		article.verifyArticleText(PageContent.articleText);
		CommonFunctions.logoutCookie(cookieName);
	}
	/*
	 * TestCase005
	 * Create article as admin user
	 * Edit article
	 * Delete article
	 */
	@Test(groups={"ArticleCRUDAdmin_005", "ArticleCRUDAdmin", "Smoke"})
	public void ArticleCRUDAdmin_005_CreateEditArticle()
	{
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		pageName = PageContent.articleNamePrefix+wiki.getTimeStamp();
		wiki.openWikiPage();
		String cookieName = CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewArticle(pageName, 1);
		edit.deleteArticleContent();
		edit.typeInContent(PageContent.articleText);
		WikiArticlePageObject article = edit.clickOnPublishButton();
		article.verifyPageTitle(pageName);
		article.verifyArticleText(PageContent.articleText);
		edit = article.clickEditButton(pageName);
		edit.deleteArticleContent();
		edit.typeInContent(PageContent.articleTextEdit);
		article = edit.clickOnPublishButton();
		article.verifyPageTitle(pageName);
		article.verifyArticleText(PageContent.articleTextEdit);
		CommonFunctions.logoutCookie(cookieName);
	}
	
	/* 
	 * TestCase006
	 * Add article as admin
	 * Add comment
	 * Delete comment
	 * Delete article
	 */
	@Test(groups={"ArticleCRUDAdmin_006", "ArticleCRUDAdmin", "Smoke"})
	public void ArticleCRUDAdmin_006_CreateArticleComment()
	{
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		pageName = PageContent.articleNamePrefix+wiki.getTimeStamp();
		wiki.openWikiPage();
		String cookieName = CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = new WikiArticlePageObject(driver, Global.DOMAIN, "random");
		article.openRandomArticle();
		article.triggerCommentArea();
		article.writeOnCommentArea(PageContent.commentText);
		article.clickSubmitButton();
		article.verifyCommentText(PageContent.commentText, Properties.userNameStaff);
		article.deleteComment(PageContent.commentText);
		CommonFunctions.logoutCookie(cookieName);
	}
	/*
	 * TestCase007
	 * Add article
	 * Add comment
	 * Edit comment
	 * Delete comment
	 * Delete article
	 */
	@Test(groups={"ArticleCRUDAdmin_007", "ArticleCRUDAdmin"}) //P2 issue raised: https://wikia.fogbugz.com/default.asp?46789 article comments aren't visible in IE9
	public void ArticleCRUDAdmin_007_CreateArticleEditComment()
	{
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		pageName = PageContent.articleNamePrefix+wiki.getTimeStamp();
		wiki.openWikiPage();
		String cookieName = CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = new WikiArticlePageObject(driver, Global.DOMAIN, "random");
		article.openRandomArticle();
		article.triggerCommentArea();
		article.writeOnCommentArea(PageContent.commentText);
		article.clickSubmitButton();
		article.verifyCommentText(PageContent.commentText, Properties.userNameStaff);
		article.editComment(PageContent.commentText);
		article.writeOnCommentArea(PageContent.commentTextEdit);
		article.clickSubmitButton(Properties.userNameStaff);
		article.verifyCommentText(PageContent.commentTextEdit, Properties.userNameStaff);
		article.deleteComment(PageContent.commentTextEdit);
		CommonFunctions.logoutCookie(cookieName);
	}
	
	/*
	 * TestCase005
	 * Add article
	 * Delete article
	 * Undelete article
	 */
	
	@Test(groups={"ArticleCRUDAdmin_008", "ArticleCRUDAdmin"})
	public void ArticleCRUDAdmin_008_CreateArticleUndeleteDelete()
	{
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		pageName = PageContent.articleNamePrefix+wiki.getTimeStamp();
		wiki.openWikiPage();
		String cookieName = CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewArticle(pageName, 1);
		edit.deleteArticleContent();
//		edit.clickOnVisualButton();
		edit.typeInContent(PageContent.articleText);
		WikiArticlePageObject article = edit.clickOnPublishButton();
		article.verifyPageTitle(pageName);
		article.verifyArticleText(PageContent.articleText);
		article.deleteArticle(pageName);
		article.undeleteArticle();
		article.openArticle(pageName);
		article.verifyPageTitle(pageName);
		article.verifyArticleText(PageContent.articleText);
		CommonFunctions.logoutCookie(cookieName);
	}
	/*
	 * TestCase006
	 * Add article
	 * Move-rename article
	 * Delete article
	 */
	
	@Test(groups={"ArticleCRUDAdmin_009", "ArticleCRUDAdmin"})
	public void ArticleCRUDAdmin_009_CreateArticleMoveDelete()
	{
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		pageName = PageContent.articleNamePrefix+wiki.getTimeStamp();
		wiki.openWikiPage();
		String cookieName = CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewArticle(pageName, 1);
		edit.deleteArticleContent();
//		edit.clickOnVisualButton();
		edit.typeInContent(PageContent.articleText);
		WikiArticlePageObject article = edit.clickOnPublishButton();
		article.verifyPageTitle(pageName);
		article.verifyArticleText(PageContent.articleText);
		article.renameArticle(pageName, pageName+"moved");
		article.verifyPageTitle(pageName+"moved");
		article.verifyArticleText(PageContent.articleText);
		CommonFunctions.logoutCookie(cookieName);
	}
	
	/* 
	 * TestCase010
	 * Add article as admin
	 * Add comment
	 * Reply comment
	 * Delete comment
	 * Delete article
	 */
	@Test(groups={"ArticleCRUDAdmin_010", "ArticleCRUDAdmin"})
	public void ArticleCRUDAdmin_010_CreateArticleCommentReply()
	{

		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		pageName = PageContent.articleNamePrefix+wiki.getTimeStamp();
		wiki.openWikiPage();
		String cookieName = CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = new WikiArticlePageObject(driver, Global.DOMAIN, "random");
		article.openRandomArticle();
		article.triggerCommentArea();
		article.writeOnCommentArea(PageContent.commentText);
		article.clickSubmitButton();
		article.verifyCommentText(PageContent.commentText, Properties.userNameStaff);
		article.replyComment(PageContent.commentText, PageContent.replyText);
		article.deleteComment(PageContent.commentText);
		CommonFunctions.logoutCookie(cookieName);
	}
	
	@Test(groups={"ArticleCRUDAdmin_011", "ArticleCRUDAdmin"})
	public void ArticleCRUDAdmin_011_VerifyingImagesPositionWikiText()
	{
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName2, Properties.password2);
		wiki.refreshPage();
		pageName = "QAarticle"+wiki.getTimeStamp();
		wiki.openWikiPage();
		WikiArticleEditMode edit = wiki.createNewArticle(pageName, 1);
		edit.deleteArticleContent();
		edit.clickOnAddObjectButton("Image");
		edit.waitForModalAndClickAddThisPhoto();
		edit.typePhotoCaption(PageContent.caption);
		edit.clickImageLeftAlignment();
		edit.clickOnAddPhotoButton2();
		edit.clickOnSourceButton();
		edit.verifyWikiTextInSourceMode("left");					
		edit.clickOnVisualButton();				
		edit.verifyLeftAlignmentIsSelected();
		WikiArticlePageObject article = edit.clickOnPublishButton();
		article.verifyImageOnThePage();
	}
	
	@Test(groups={"ArticleCRUDAdmin_012", "ArticleCRUDAdmin"})
	public void ArticleCRUDAdmin_012_LightboxVerifyExistenceAndURLsOfSocialButtons()
	{
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName2, Properties.password2);
		wiki.refreshPage();
		pageName = "QAarticle"+wiki.getTimeStamp();
		wiki.openWikiPage();			
		WikiArticleEditMode edit = wiki.createNewArticle(pageName, 1);
		edit.deleteArticleContent();
		edit.clickOnAddObjectButton("Image");
		WikiArticlePageObject article = edit.addImageForLightboxTesting();
		LightboxPageObject lightbox = article.clickThumbnailImage();
		lightbox.clickPinButton();
		lightbox.clickShareButton();
		lightbox.verifyShareButtons();
		lightbox.clickFacebookShareButton();
		lightbox.verifyFacebookWindow();
		lightbox.clickTwitterShareButton();
		lightbox.verifyTwitterWindow();
		lightbox.clickStumbleUponShareButton();
		lightbox.verifyStumbleUponWindow();
		lightbox.clickRedditShareButton();
		lightbox.verifyRedditWindow();
		lightbox.clickPlusOneShareButton();
		lightbox.verifyPlusOneWindow();
		lightbox.clickCloseButton();
	}
}
