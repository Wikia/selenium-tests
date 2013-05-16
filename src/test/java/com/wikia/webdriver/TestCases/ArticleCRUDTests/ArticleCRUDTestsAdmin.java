package com.wikia.webdriver.TestCases.ArticleCRUDTests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoAddComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoOptionsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.LightboxPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;

public class ArticleCRUDTestsAdmin extends TestTemplate{
		
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
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();		
		edit.deleteArticleContent();
		edit.typeInContent(PageContent.articleText);
		WikiArticlePageObject article = edit.clickOnPublishButton();
		article.verifyPageTitle(article.getPageName());
		article.verifyArticleText(PageContent.articleText);
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
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();	
		edit.deleteArticleContent();
		edit.typeInContent(PageContent.articleText);
		WikiArticlePageObject article = edit.clickOnPublishButton();
		article.verifyPageTitle(article.getPageName());
		article.verifyArticleText(PageContent.articleText);
		edit = article.clickEditButton(article.getPageName());
		edit.deleteArticleContent();
		edit.typeInContent(PageContent.articleTextEdit);
		article = edit.clickOnPublishButton();
		article.verifyPageTitle(article.getPageName());
		article.verifyArticleText(PageContent.articleTextEdit);
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
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = new WikiArticlePageObject(driver, Global.DOMAIN, "random");
		article.openRandomArticle();
		article.triggerCommentArea();
		article.writeOnCommentArea(PageContent.commentText);
		article.clickSubmitButton();
		article.verifyCommentText(PageContent.commentText, Properties.userNameStaff);
		article.deleteComment(PageContent.commentText);
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
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
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
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
		edit.typeInContent(PageContent.articleText);
		WikiArticlePageObject article = edit.clickOnPublishButton();
		article.verifyPageTitle(article.getPageName());
		article.verifyArticleText(PageContent.articleText);
		article.deleteArticle(article.getPageName());
		article.undeleteArticle();
		article.openArticle(article.getPageName());
		article.verifyPageTitle(article.getPageName());
		article.verifyArticleText(PageContent.articleText);
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
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
		edit.typeInContent(PageContent.articleText);
		WikiArticlePageObject article = edit.clickOnPublishButton();
		article.verifyPageTitle(article.getPageName());
		article.verifyArticleText(PageContent.articleText);
		article.renameArticleAndVerify(article.getPageName(), article.getPageName()+"moved");
		article.verifyPageTitle(article.getPageName()+"moved");
		article.verifyArticleText(PageContent.articleText);
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
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = new WikiArticlePageObject(driver, Global.DOMAIN, "random");
		article.openRandomArticle();
		article.triggerCommentArea();
		article.writeOnCommentArea(PageContent.commentText);
		article.clickSubmitButton();
		article.verifyCommentText(PageContent.commentText, Properties.userNameStaff);
		article.replyComment(PageContent.commentText, PageContent.replyText);
		article.deleteComment(PageContent.commentText);
	}
	
	@Test(groups={"ArticleCRUDAdmin_011", "ArticleCRUDAdmin"})
	public void ArticleCRUDAdmin_011_VerifyingImagesPositionWikiText()
	{
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName2, Properties.password2);
		wiki.refreshPage();
		wiki.openWikiPage();
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
		PhotoAddComponentObject photoAddPhoto = edit.clickPhotoButton();
		PhotoOptionsComponentObject photoOptions = photoAddPhoto.addPhotoFromWiki("image", 1);
		photoOptions.setCaption(PageContent.caption);
		photoOptions.adjustAlignment(1);
		photoOptions.clickAddPhoto();
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
		WikiArticlePageObject article = wiki.openArticle(URLsContent.lightboxImageTest);
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
