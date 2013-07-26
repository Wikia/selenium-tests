package com.wikia.webdriver.TestCases.BlogTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.DataProvider.ArticleDataProvider;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticleActions.DeleteArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticleActions.RenameArticlePageObject;
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

	@Test(groups = { "BlogTests_001", "BlogTests", "Blog"})
	public void BlogTests_001_addFromProfile() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		String blogTitle = PageContent.blogPostNamePrefix + base.getTimeStamp();
		String blogContent = PageContent.blogContent + base.getTimeStamp();
		UserProfilePageObject userProfile = base.navigateToProfilePage(credentials.userName, wikiURL);
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
			groups = { "BlogTests_002", "BlogTests", "Blog"})
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

	@Test(groups = { "BlogTests_003", "BlogTests", "Blog"})
	public void BlogTests_003_editFromProfile() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		String blogContent = PageContent.blogContent + base.getTimeStamp();
		UserProfilePageObject userProfile = base.navigateToProfilePage(credentials.userName, wikiURL);
		userProfile.clickOnBlogTab();
		BlogPageObject blogPage = userProfile.openFirstPost();
		String blogTitle = blogPage.getBlogName();
		VisualEditModePageObject visualEditMode = blogPage.editArticleUsingDropdown();
		visualEditMode.addContent(blogContent);
		visualEditMode.submitArticle();
		blogPage.verifyBlogTitle(blogTitle);
		blogPage.verifyContent(blogContent);
	}

	@Test(groups = { "BlogTests_004", "BlogTests", "Blog"})
	public void BlogTests_004_deleteUndelete() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		UserProfilePageObject userProfile = base.navigateToProfilePage(credentials.userName, wikiURL);
		userProfile.clickOnBlogTab();
		BlogPageObject blogPage = userProfile.openFirstPost();
		String blogTitle = blogPage.getBlogName();
		DeleteArticlePageObject deletePage = blogPage.deleteArticleUsingDropdown();
		deletePage.submitDeletion();
		SpecialRestorePageObject restore = base.undeleteArticle();
		restore.giveReason(blogPage.getTimeStamp());
		restore.restorePage();
		blogPage.verifyBlogTitle(blogTitle);
		blogPage.verifyNotificationMessage();
	}

	@Test(groups = { "BlogTests_005", "BlogTests", "Blog"})
	public void BlogTests_005_move() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		String blogTitleMove = PageContent.blogPostNamePrefix + base.getTimeStamp();
		UserProfilePageObject userProfile = base.navigateToProfilePage(credentials.userName, wikiURL);
		userProfile.clickOnBlogTab();
		BlogPageObject blogPage = userProfile.openFirstPost();
		RenameArticlePageObject renamePage = blogPage.renameArticleUsingDropdown();
		renamePage.rename(credentials.userNameStaff+"/"+blogTitleMove);
		blogPage.verifyBlogTitle(blogTitleMove);
		blogPage.verifyNotificationMessage();
	}
}
