package com.wikia.webdriver.TestCases.BlogTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.DataProvider.ArticleDataProvider;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Actions.DeletePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Actions.RenamePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.SignUp.UserProfilePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialCreatePagePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialRestorePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.Blog.BlogPageObject;

public class BlogTests extends NewTestTemplate{

	Credentials credentials = config.getCredentials();

	/**
	 * @author Karol 'kkarolk' Kujawiak
	 *
	 * Test cases:
	 * 1. Create blog post using "Create blog post button" (one case)
	 * 2. Create blog post using "Special:CreateBlogPage" (data provider)
	 * 3. Edit existing blog post
	 * 4. Delete/Undelete existing blog post
	 * 5. Move existing blog post
	 */

	@Test(groups = { "BlogTests_001", "BlogTests", "Smoke1"})
	public void BlogTests_001_addFromProfile() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName4, credentials.password4, wikiURL);
		String blogTitle = PageContent.blogPostNamePrefix + base.getTimeStamp();
		String blogContent = PageContent.blogContent + base.getTimeStamp();
		UserProfilePageObject userProfile = base.openProfilePage(credentials.userName4, wikiURL);
		userProfile.clickOnBlogTab();
		SpecialCreatePagePageObject createBlogPage = userProfile.clickOnCreateBlogPost();
		VisualEditModePageObject visualEditMode = createBlogPage.populateTitleField(blogTitle);
		visualEditMode.addContent(blogContent);
		BlogPageObject blogPage = visualEditMode.submitBlog();
		blogPage.verifyBlogTitle(blogTitle);
		blogPage.verifyContent(blogContent);
	}

	@Test(
			dataProviderClass = ArticleDataProvider.class,
			dataProvider = "articleTitles",
			groups = { "BlogTests_002", "BlogTests"})
	public void BlogTests_002_addByUrl(String blogTitle) {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		String blogContent = PageContent.blogContent + base.getTimeStamp();
		blogTitle += blogTitle + base.getTimeStamp();
		SpecialCreatePagePageObject createBlogPage = base.openSpecialCreateBlogPage(wikiURL);
		VisualEditModePageObject visualEditMode = createBlogPage.populateTitleField(blogTitle);
		visualEditMode.addContent(blogContent);
		BlogPageObject blogPage = visualEditMode.submitBlog();
		blogPage.verifyBlogTitle(blogTitle);
		blogPage.verifyContent(blogContent);
	}

	@Test(groups = { "BlogTests_003", "BlogTests"})
	public void BlogTests_003_editFromProfile() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		String blogContent = PageContent.blogContent + base.getTimeStamp();
		UserProfilePageObject userProfile = base.openProfilePage(credentials.userName, wikiURL);
		userProfile.clickOnBlogTab();
		BlogPageObject blogPage = userProfile.openFirstPost();
		String blogTitle = blogPage.getBlogName();
		VisualEditModePageObject visualEditMode = blogPage.editArticleInRTEUsingDropdown();
		visualEditMode.addContent(blogContent);
		visualEditMode.submitArticle();
		blogPage.verifyBlogTitle(blogTitle);
		blogPage.verifyContent(blogContent);
	}

	@Test(groups = { "BlogTests_004", "BlogTests"})
	public void BlogTests_004_deleteUndelete() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		UserProfilePageObject userProfile = base.openProfilePage(credentials.userName4, wikiURL);
		userProfile.clickOnBlogTab();
		BlogPageObject blogPage = userProfile.openFirstPost();
		String blogTitle = blogPage.getBlogName();
		DeletePageObject deletePage = blogPage.deleteUsingDropdown();
		deletePage.submitDeletion();
		SpecialRestorePageObject restore = base.undeleteByFlashMessage();
		restore.giveReason(blogPage.getTimeStamp());
		restore.restorePage();
		blogPage.verifyBlogTitle(blogTitle);
		blogPage.verifyNotificationMessage();
	}

	@Test(groups = { "BlogTests_005", "BlogTests"})
	public void BlogTests_005_move() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		String blogTitleMove = PageContent.blogPostNamePrefix + base.getTimeStamp();
		UserProfilePageObject userProfile = base.openProfilePage(credentials.userName, wikiURL);
		userProfile.clickOnBlogTab();
		BlogPageObject blogPage = userProfile.openFirstPost();
		RenamePageObject renamePage = blogPage.renameUsingDropdown();
		renamePage.rename(credentials.userNameStaff+"/"+blogTitleMove);
		blogPage.verifyBlogTitle(blogTitleMove);
		blogPage.verifyNotificationMessage();
	}
}
