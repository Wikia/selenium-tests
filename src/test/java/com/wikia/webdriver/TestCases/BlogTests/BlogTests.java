package com.wikia.webdriver.TestCases.BlogTests;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialCreateBlogPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.BlogPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;
import org.testng.annotations.Test;

public class BlogTests extends TestTemplate{


	String blogPostContent = PageContent.blogContent;
	String blogPostNamePrefix = PageContent.blogPostNamePrefix;
	String commentBlogPost = PageContent.blogComment;
	String commentBlogPostEdited = PageContent.blogCommentEdit;

	@Test(groups = {"BlogTests_001", "BlogTests"})
	public void BlogTests_001_CreateBlogPost(){
		WikiArticlePageObject home = new WikiArticlePageObject(driver, Global.DOMAIN, "");
		home.openWikiPage();
		String blogPostTitle = blogPostNamePrefix + home.getTimeStamp();

		CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);
		BlogPageObject blog = new BlogPageObject(driver, Global.DOMAIN, blogPostTitle);
		blog.openBlogPage(Properties.userName);
		SpecialCreateBlogPageObject createBlogPage = blog.clickOnCreateBlogPost();

		createBlogPage.typeBlogPostTitle(blogPostTitle);
		createBlogPage.clickOk();
		createBlogPage.typeInContent(blogPostContent);
		createBlogPage.clickOnPublishButton();

		blog.verifyArticleText(blogPostContent);
		blog.verifyPageTitle(blogPostTitle);
		blog.verifyUsernameFieldPresent(Properties.userName);
		blog.categories_verifyCategoryPresent("Blog posts");
	}
	
	@Test(groups = {"BlogTests_002", "BlogTests"})
	public void BlogTests_002_EditBlogPost(){
		WikiArticlePageObject home = new WikiArticlePageObject(driver, Global.DOMAIN, "");
		home.openWikiPage();
		String blogPostTitle = blogPostNamePrefix + home.getTimeStamp();

		CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);
		BlogPageObject blog = new BlogPageObject(driver, Global.DOMAIN, blogPostTitle);
		blog.openBlogPage(Properties.userName);
		SpecialCreateBlogPageObject createBlogPage = blog.clickOnCreateBlogPost();

		createBlogPage.typeBlogPostTitle(blogPostTitle);
		createBlogPage.clickOk();
		createBlogPage.typeInContent(blogPostContent);
		createBlogPage.clickOnPublishButton();

		blog.verifyArticleText(blogPostContent);
		blog.verifyPageTitle(blogPostTitle);
		blog.verifyUsernameFieldPresent(Properties.userName);
		blog.categories_verifyCategoryPresent("Blog posts");
		blog.editBlog();

		createBlogPage.deleteArticleContent();
		createBlogPage.typeInContent(PageContent.blogContentEdit);
		createBlogPage.clickOnPublishButton();

		blog.verifyArticleText(PageContent.blogContentEdit);
	}

	@Test(groups = {"BlogTests_003", "BlogTests"})
	public void BlogTests_003_DeleteBlogPost(){
		WikiArticlePageObject home = new WikiArticlePageObject(driver, Global.DOMAIN, "");
		home.openWikiPage();
		String blogPostTitle = blogPostNamePrefix + home.getTimeStamp();

		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff, driver);
		BlogPageObject blog = new BlogPageObject(driver, Global.DOMAIN, blogPostTitle);
		blog.openBlogPage(Properties.userNameStaff);
		SpecialCreateBlogPageObject createBlogPage = blog.clickOnCreateBlogPost();

		createBlogPage.typeBlogPostTitle(blogPostTitle);
		createBlogPage.clickOk();
		createBlogPage.typeInContent(blogPostContent);
		createBlogPage.clickOnPublishButton();

		blog.verifyArticleText(blogPostContent);
		blog.verifyPageTitle(blogPostTitle);
		blog.verifyUsernameFieldPresent(Properties.userNameStaff);
		blog.categories_verifyCategoryPresent("Blog posts");
		blog.deleteBlogPost(blogPostTitle);
	}

	@Test(groups = {"BlogTests_004", "BlogTests"})
	public void BlogTests_004_DeleteUndeleteBlogPost(){
		WikiArticlePageObject home = new WikiArticlePageObject(driver, Global.DOMAIN, "");
		home.openWikiPage();
		String blogPostTitle = blogPostNamePrefix + home.getTimeStamp();

		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff, driver);
		BlogPageObject blog = new BlogPageObject(driver, Global.DOMAIN, blogPostTitle);
		blog.openBlogPage(Properties.userNameStaff);
		SpecialCreateBlogPageObject createBlogPage = blog.clickOnCreateBlogPost();

		createBlogPage.typeBlogPostTitle(blogPostTitle);
		createBlogPage.clickOk();
		createBlogPage.typeInContent(blogPostContent);
		createBlogPage.clickOnPublishButton();

		blog.verifyArticleText(blogPostContent);
		blog.verifyPageTitle(blogPostTitle);
		blog.verifyUsernameFieldPresent(Properties.userNameStaff);
		blog.categories_verifyCategoryPresent("Blog posts");

		blog.deleteBlogPost(blogPostTitle);
		blog.undeleteArticle();
	}

	@Test(groups = {"BlogTests_005", "BlogTests"})
	public void BlogTests_005_PostReply(){
		WikiArticlePageObject home = new WikiArticlePageObject(driver, Global.DOMAIN, "");
		home.openWikiPage();
		String blogPostTitle = blogPostNamePrefix+home.getTimeStamp();

		CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);
		BlogPageObject blog = new BlogPageObject(driver, Global.DOMAIN, blogPostTitle);
		blog.openBlogPage(Properties.userName);
		SpecialCreateBlogPageObject createBlogPage = blog.clickOnCreateBlogPost();

		createBlogPage.typeBlogPostTitle(blogPostTitle);
		createBlogPage.clickOk();
		createBlogPage.typeInContent(blogPostContent);
		createBlogPage.clickOnPublishButton();

		blog.verifyArticleText(blogPostContent);
		blog.verifyPageTitle(blogPostTitle);
		blog.verifyUsernameFieldPresent(Properties.userName);
		blog.categories_verifyCategoryPresent("Blog posts");

		blog.triggerCommentArea();
		blog.writeOnCommentArea(commentBlogPost);
		blog.clickSubmitButton();
		blog.verifyCommentText(commentBlogPost, Properties.userName);
	}

	@Test(groups = {"BlogTests_006", "BlogTests"})
	public void BlogTests_006_PostReplyEdit(){
		WikiArticlePageObject home = new WikiArticlePageObject(driver, Global.DOMAIN, "");
		home.openWikiPage();
		String blogPostTitle = blogPostNamePrefix + home.getTimeStamp();

		CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);
		BlogPageObject blog = new BlogPageObject(driver, Global.DOMAIN, blogPostTitle);
		blog.openBlogPage(Properties.userName);
		SpecialCreateBlogPageObject createBlogPage = blog.clickOnCreateBlogPost();

		createBlogPage.typeBlogPostTitle(blogPostTitle);
		createBlogPage.clickOk();
		createBlogPage.typeInContent(blogPostContent);
		createBlogPage.clickOnPublishButton();

		blog.verifyArticleText(blogPostContent);
		blog.verifyPageTitle(blogPostTitle);
		blog.verifyUsernameFieldPresent(Properties.userName);
		blog.categories_verifyCategoryPresent("Blog posts");
		blog.triggerCommentArea();

		blog.writeOnCommentArea(commentBlogPost);
		blog.clickSubmitButton();
		blog.verifyCommentText(commentBlogPost, Properties.userName);
		blog.editComment(commentBlogPost);
		blog.writeOnCommentArea(commentBlogPostEdited);
		blog.clickSubmitButton(Properties.userName);
		blog.verifyCommentText(commentBlogPostEdited, Properties.userName);
	}

	@Test(groups = {"BlogTests_007", "BlogTests"})
	public void BlogTests_007_PostReplyDelete(){
		WikiArticlePageObject home = new WikiArticlePageObject(driver, Global.DOMAIN, "");
		home.openWikiPage();
		String blogPostTitle = blogPostNamePrefix + home.getTimeStamp();

		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff, driver);
		BlogPageObject blog = new BlogPageObject(driver, Global.DOMAIN, blogPostTitle);
		blog.openBlogPage(Properties.userNameStaff);
		SpecialCreateBlogPageObject createBlogPage = blog.clickOnCreateBlogPost();

		createBlogPage.typeBlogPostTitle(blogPostTitle);
		createBlogPage.clickOk();
		createBlogPage.typeInContent(blogPostContent);
		createBlogPage.clickOnPublishButton();

		blog.verifyArticleText(blogPostContent);
		blog.verifyPageTitle(blogPostTitle);
		blog.verifyUsernameFieldPresent(Properties.userNameStaff);
		blog.categories_verifyCategoryPresent("Blog posts");
		blog.triggerCommentArea();
		blog.writeOnCommentArea(commentBlogPost);
		blog.clickSubmitButton();
		blog.verifyCommentText(commentBlogPost, Properties.userNameStaff);
		blog.deleteComment(commentBlogPost);
	}

	//test case for https://wikia.fogbugz.com/default.asp?95165
	@Test(groups = {"BlogTests_008", "BlogTests"})
	public void BlogTests_008_PostReplyReply(){
		WikiArticlePageObject home = new WikiArticlePageObject(driver, Global.DOMAIN, "");
		home.openWikiPage();
		String blogPostTitle = blogPostNamePrefix + home.getTimeStamp();

		CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);
		BlogPageObject blog = new BlogPageObject(driver, Global.DOMAIN, blogPostTitle);
		blog.openBlogPage(Properties.userName);
		SpecialCreateBlogPageObject createBlogPage = blog.clickOnCreateBlogPost();

		createBlogPage.typeBlogPostTitle(blogPostTitle);
		createBlogPage.clickOk();
		createBlogPage.typeInContent(PageContent.blogContent);
		createBlogPage.clickOnPublishButton();

		blog.verifyArticleText(PageContent.blogContent);
		blog.verifyPageTitle(blogPostTitle);
		blog.verifyUsernameFieldPresent(Properties.userName);
		blog.categories_verifyCategoryPresent("Blog posts");
		blog.triggerCommentArea();
		blog.writeOnCommentArea(PageContent.blogComment);
		blog.clickSubmitButton();
		blog.verifyCommentText(PageContent.blogComment, Properties.userName);
		blog.replyComment(PageContent.blogComment, PageContent.blogCommentReply);
	}
}
