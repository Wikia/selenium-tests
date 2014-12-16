package com.wikia.webdriver.testcases.blogtests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.dataprovider.ArticleDataProvider;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.DeletePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.RenamePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.UserProfilePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialCreatePagePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialRestorePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.blog.BlogPageObject;

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
		String blogTitle = PageContent.BLOG_POST_NAME_PREFIX + base.getTimeStamp();
		String blogContent = PageContent.BLOG_CONTENT + base.getTimeStamp();
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
		String blogContent = PageContent.BLOG_CONTENT + base.getTimeStamp();
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
		String blogContent = PageContent.BLOG_CONTENT + base.getTimeStamp();
		UserProfilePageObject userProfile = base.openProfilePage(credentials.userName, wikiURL);
		userProfile.clickOnBlogTab();
		BlogPageObject blogPage = userProfile.openFirstPost();
		String blogTitle = blogPage.getBlogName();
		VisualEditModePageObject visualEditMode = blogPage.openCKModeWithMainEditButton();
		visualEditMode.addContent(blogContent);
		visualEditMode.submitArticle();
		blogPage.verifyBlogTitle(blogTitle);
		blogPage.verifyContent(blogContent);
	}

	@Test(groups = { "BlogTests_004", "BlogTests"})
	public void BlogTests_004_deleteUndelete_QAART_479() {
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
		blogPage.verifyNotificationMessage();
		blogPage.verifyBlogTitle(blogTitle);
	}

	@Test(groups = { "BlogTests_005", "BlogTests"})
	public void BlogTests_005_move() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		String blogTitleMove = PageContent.BLOG_POST_NAME_PREFIX + base.getTimeStamp();
		UserProfilePageObject userProfile = base.openProfilePage(credentials.userName, wikiURL);
		userProfile.clickOnBlogTab();
		BlogPageObject blogPage = userProfile.openFirstPost();
		RenamePageObject renamePage = blogPage.renameUsingDropdown();
		renamePage.rename(credentials.userNameStaff+"/"+blogTitleMove);
		blogPage.verifyBlogTitle(blogTitleMove);
		blogPage.verifyNotificationMessage();
	}
}
