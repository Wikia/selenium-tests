package com.wikia.webdriver.TestCases.ArticleCRUDTests;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.DataProvider.ArticleDataProvider;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoAddComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoOptionsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.LightboxPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;
import org.testng.annotations.Test;

public class ArticleCRUDTestsAdmin extends TestTemplate {

	@Test(groups={"ArticleCRUDAdmin_002", "ArticleCRUDAdmin"})
	public void ArticleCRUDAdmin_002_VerifyEditDropDown()
	{
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
		article.clickEditDropDown();
		article.verifyEditDropDownLoggedInUser();
	}

	@Test(groups={"ArticleCRUDAdmin_003", "ArticleCRUDAdmin"})
	public void ArticleCRUDAdmin_003_VerifyEditDropDown()
	{
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
		article.clickEditDropDown();
		article.verifyEditDropDownAdmin();
	}

	@Test(
		dataProviderClass=ArticleDataProvider.class,
		dataProvider="getArticleName",
		groups={"ArticleCRUDAdmin_004", "ArticleCRUDAdmin", "Smoke"}
	)
	public void ArticleCRUDAdmin_004_CreateArticle(String articleName)
	{
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		articleName += wiki.getTimeStamp();

		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
		WikiArticleEditMode edit = article.createNewArticle(articleName, 1);
		edit.typeInContent(PageContent.articleText + wiki.getTimeStamp());
		edit.clickOnPublishButton();
		article.verifyPageTitle(articleName);
		article.verifyArticleText(PageContent.articleText);
	}

	@Test(groups={"ArticleCRUDAdmin_005", "ArticleCRUDAdmin", "Smoke"})
	public void ArticleCRUDAdmin_005_EditArticle()
	{
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
		WikiArticleEditMode edit = article.clickEditButton();
		edit.deleteArticleContent();
		edit.typeInContent(PageContent.articleTextEdit);
		edit.clickOnPublishButton();
		article.verifyPageTitle(article.getPageName());
		article.verifyArticleText(PageContent.articleTextEdit);
	}

	@Test(groups={"ArticleCRUDAdmin_006", "ArticleCRUDAdmin", "Smoke"})
	public void ArticleCRUDAdmin_006_CreateArticleComment()
	{
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
		article.openRandomArticle();
		article.triggerCommentArea();
		article.writeOnCommentArea(PageContent.commentText);
		article.clickSubmitButton();
		article.verifyCommentText(PageContent.commentText, Properties.userNameStaff);
	}

	@Test(groups={"ArticleCRUDAdmin_007", "ArticleCRUDAdmin"})
	public void ArticleCRUDAdmin_007_CreateArticleEditComment()
	{
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
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

	@Test(groups={"ArticleCRUDAdmin_008", "ArticleCRUDAdmin"})
	public void ArticleCRUDAdmin_008_CreateArticleUndeleteDelete()
	{
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
		String articleName = article.getPageName();
		String articleContent = article.getArticleContent();
		article.deleteArticle(articleName);
		article.undeleteArticle();
		article.openArticle(articleName);
		article.verifyPageTitle(articleName);
		article.verifyArticleText(articleContent);
	}

	@Test(groups={"ArticleCRUDAdmin_009", "ArticleCRUDAdmin"})
	public void ArticleCRUDAdmin_009_CreateArticleMoveDelete()
	{
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
		String articleContent = article.getArticleContent();
		article.renameArticleAndVerify(article.getPageName(), article.getPageName()+"moved");
		article.verifyPageTitle(article.getPageName()+"moved");
		article.verifyArticleText(articleContent);
	}

	@Test(groups={"ArticleCRUDAdmin_010", "ArticleCRUDAdmin"})
	public void ArticleCRUDAdmin_010_CreateArticleCommentReply()
	{
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
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
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
		WikiArticleEditMode edit = article.createNewDefaultArticle();
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
		edit.clickOnPublishButton();
		article.verifyImageOnThePage();
	}

	@Test(groups={"ArticleCRUDAdmin_012", "ArticleCRUDAdmin"})
	public void ArticleCRUDAdmin_012_LightboxVerifyExistenceAndURLsOfSocialButtons()
	{
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		WikiArticleEditMode edit = article.createNewDefaultArticle();
		PhotoAddComponentObject photoAddPhoto = edit.clickPhotoButton();
		PhotoOptionsComponentObject photoOptions = photoAddPhoto.addPhotoFromWiki("image", 1);
		photoOptions.setCaption(PageContent.caption);
		photoOptions.clickAddPhoto();
		edit.verifyThatThePhotoAppears(PageContent.caption);
		edit.clickOnPublishButton();
		article.verifyImageOnThePage();
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
