package com.wikia.webdriver.TestCases.BlogTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.SignUp.UserProfilePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Blog.SpecialCreateBlogPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.Blog.BlogPageObject;

public class BlogReplyTests extends TestTemplate{

	/*
	 *
	 */

	public BlogReplyTests() {
	}

	String blogPostTitle;

	@Test(groups = { "BlogTests_005", "BlogTests", "Blog"})
	public void BlogTests_005_PostReply(){
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		blogPostTitle = PageContent.blogPostNamePrefix + wiki.getTimeStamp();
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userName, Properties.password);
		UserProfilePageObject userProfile = new UserProfilePageObject(driver);
		userProfile.navigateToProfilePage(Properties.userName);
		userProfile.clickOnBlogTab();
		SpecialCreateBlogPageObject createBlogPage = userProfile.clickOnCreateBlogPost();
		createBlogPage.typeBlogPostTitle(blogPostTitle);
		createBlogPage.clickOk();
		createBlogPage.typeInContent(PageContent.blogContent);
		BlogPageObject blogPage = createBlogPage.clickOnPublishBlogPostButton();
		blogPage.verifyArticleText(PageContent.blogContent);
		blogPage.verifyPageTitle(blogPostTitle);
		blogPage.verifyUsernameFieldPresent(Properties.userName);
		blogPage.categories_verifyCategoryPresent("Blog posts");
		blogPage.triggerCommentArea();
		blogPage.writeOnCommentArea(PageContent.blogComment);
		blogPage.clickSubmitButton();
		blogPage.verifyCommentText(PageContent.blogComment, Properties.userName);
	}

	@Test(groups = { "BlogTests_006", "BlogTests", "Blog"})
	public void BlogTests_006_PostReplyEdit(){
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		blogPostTitle = PageContent.blogPostNamePrefix + wiki.getTimeStamp();
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userName, Properties.password);
		UserProfilePageObject userProfile = new UserProfilePageObject(driver);
		userProfile.navigateToProfilePage(Properties.userName);
		userProfile.clickOnBlogTab();
		SpecialCreateBlogPageObject createBlogPage = userProfile.clickOnCreateBlogPost();
		createBlogPage.typeBlogPostTitle(blogPostTitle);
		createBlogPage.clickOk();
		createBlogPage.typeInContent(PageContent.blogContent);
		BlogPageObject blogPage = createBlogPage.clickOnPublishBlogPostButton();
		blogPage.verifyArticleText(PageContent.blogContent);
		blogPage.verifyPageTitle(blogPostTitle);
		blogPage.verifyUsernameFieldPresent(Properties.userName);
		blogPage.categories_verifyCategoryPresent("Blog posts");
		blogPage.triggerCommentArea();
		blogPage.writeOnCommentArea(PageContent.blogComment);
		blogPage.clickSubmitButton();
		blogPage.verifyCommentText(PageContent.blogComment, Properties.userName);
		blogPage.editComment(PageContent.blogComment);
		blogPage.writeOnCommentArea(PageContent.blogCommentEdit);
		blogPage.clickSubmitButton(Properties.userName);
		blogPage.verifyCommentText(PageContent.blogCommentEdit, Properties.userName);
	}

	@Test(groups = { "BlogTests_007", "BlogTests", "Blog"})
	public void BlogTests_007_PostReplyDelete(){
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		blogPostTitle = PageContent.blogPostNamePrefix + wiki.getTimeStamp();
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		UserProfilePageObject userProfile = new UserProfilePageObject(driver);
		userProfile.navigateToProfilePage(Properties.userNameStaff);
		userProfile.clickOnBlogTab();
		SpecialCreateBlogPageObject createBlogPage = userProfile.clickOnCreateBlogPost();
		createBlogPage.typeBlogPostTitle(blogPostTitle);
		createBlogPage.clickOk();
		createBlogPage.typeInContent(PageContent.blogContent);
		BlogPageObject blogPage = createBlogPage.clickOnPublishBlogPostButton();
		blogPage.verifyArticleText(PageContent.blogContent);
		blogPage.verifyPageTitle(blogPostTitle);
		blogPage.verifyUsernameFieldPresent(Properties.userNameStaff);
		blogPage.categories_verifyCategoryPresent("Blog posts");
		blogPage.triggerCommentArea();
		blogPage.writeOnCommentArea(PageContent.blogComment);
		blogPage.clickSubmitButton();
		blogPage.verifyCommentText(PageContent.blogComment, Properties.userNameStaff);
		blogPage.deleteComment(PageContent.blogComment);
	}

	@Test(groups = { "BlogTests_008", "BlogTests", "Blog"})
	public void BlogTests_008_PostReplyReply(){
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		blogPostTitle = PageContent.blogPostNamePrefix + wiki.getTimeStamp();
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userName, Properties.password);
		UserProfilePageObject userProfile = new UserProfilePageObject(driver);
		userProfile.navigateToProfilePage(Properties.userName);
		userProfile.clickOnBlogTab();
		SpecialCreateBlogPageObject createBlogPage = userProfile.clickOnCreateBlogPost();
		createBlogPage.typeBlogPostTitle(blogPostTitle);
		createBlogPage.clickOk();
		createBlogPage.typeInContent(PageContent.blogContent);
		BlogPageObject blogPage = createBlogPage.clickOnPublishBlogPostButton();
		blogPage.verifyArticleText(PageContent.blogContent);
		blogPage.verifyPageTitle(blogPostTitle);
		blogPage.verifyUsernameFieldPresent(Properties.userName);
		blogPage.categories_verifyCategoryPresent("Blog posts");
		blogPage.triggerCommentArea();
		blogPage.writeOnCommentArea(PageContent.blogComment);
		blogPage.clickSubmitButton();
		blogPage.verifyCommentText(PageContent.blogComment, Properties.userName);
		blogPage.replyComment(PageContent.blogComment, PageContent.blogCommentReply);
	}

}
