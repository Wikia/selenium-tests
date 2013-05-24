package com.wikia.webdriver.TestCases.BlogTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.SignUp.UserProfilePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Blog.SpecialCreateBlogListingPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Blog.SpecialCreateBlogPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.Blog.BlogPageObject;

public class BlogTests extends TestTemplate{
	
	String blogPostTitle;
	
	@Test(groups = { "BlogTests_001", "BlogTests", "Blog"})
	public void BlogTests_001_CreateBlogPost(){
		CommonFunctions.logOut(driver);
		WikiArticlePageObject home = new WikiArticlePageObject(driver);
		home.openWikiPage(); 
		blogPostTitle = PageContent.blogPostNamePrefix+home.getTimeStamp(); 
		CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);	
		UserProfilePageObject userProfile = home.navigateToProfilePage(Global.DOMAIN, Properties.userName);
		userProfile.clickOnBlogTab();
		SpecialCreateBlogPageObject createBlogPage = userProfile.clickOnCreateBlogPost();
		createBlogPage.typeBlogPostTitle(blogPostTitle);
		createBlogPage.clickOk();
		createBlogPage.typeInContent(PageContent.blogContent);
		BlogPageObject blogPage = createBlogPage.clickOnPublishButton();
		blogPage.verifyArticleText(PageContent.blogContent);
		blogPage.verifyPageTitle(blogPostTitle);
		blogPage.verifyUsernameFieldPresent(Properties.userName);
		blogPage.categories_verifyCategoryPresent("Blog posts");	
	}
	
	@Test(groups = { "BlogTests_002", "BlogTests", "Blog"})
	public void BlogTests_002_EditBlogPost(){
		CommonFunctions.logOut(driver);
		WikiArticlePageObject home = new WikiArticlePageObject(driver);
		home.openWikiPage();
		blogPostTitle = PageContent.blogPostNamePrefix+home.getTimeStamp();
		CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);	
		UserProfilePageObject userProfile = home.navigateToProfilePage(Global.DOMAIN, Properties.userName);
		userProfile.clickOnBlogTab();
		SpecialCreateBlogPageObject createBlogPage = userProfile.clickOnCreateBlogPost();
		createBlogPage.typeBlogPostTitle(blogPostTitle);
		createBlogPage.clickOk();
		createBlogPage.typeInContent(PageContent.blogContent);
		BlogPageObject blogPage = createBlogPage.clickOnPublishButton();
		blogPage.verifyArticleText(PageContent.blogContent);
		blogPage.verifyPageTitle(blogPostTitle);
		blogPage.verifyUsernameFieldPresent(Properties.userName);
		blogPage.categories_verifyCategoryPresent("Blog posts");	
		createBlogPage = blogPage.editBlog();
		createBlogPage.deleteArticleContent();
		createBlogPage.typeInContent(PageContent.blogContentEdit);
		blogPage = createBlogPage.clickOnPublishButton();
		blogPage.verifyArticleText(PageContent.blogContentEdit);
	}

	@Test(groups = { "BlogTests_003", "BlogTests", "Blog"})
	public void BlogTests_003_DeleteBlogPost(){
		CommonFunctions.logOut(driver);
		WikiArticlePageObject home = new WikiArticlePageObject(driver);
		home.openWikiPage();
		blogPostTitle = PageContent.blogPostNamePrefix+home.getTimeStamp();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff, driver);	
		UserProfilePageObject userProfile = home.navigateToProfilePage(Global.DOMAIN, Properties.userNameStaff);
		userProfile.clickOnBlogTab();
		SpecialCreateBlogPageObject createBlogPage = userProfile.clickOnCreateBlogPost();
		createBlogPage.typeBlogPostTitle(blogPostTitle);
		createBlogPage.clickOk();
		createBlogPage.typeInContent(PageContent.blogContent);
		BlogPageObject blogPage = createBlogPage.clickOnPublishButton();
		blogPage.verifyArticleText(PageContent.blogContent);
		blogPage.verifyPageTitle(blogPostTitle);
		blogPage.verifyUsernameFieldPresent(Properties.userNameStaff);
		blogPage.categories_verifyCategoryPresent("Blog posts");	
		blogPage.deleteBlogPost(blogPostTitle);
	}
	
	@Test(groups = { "BlogTests_004", "BlogTests", "Blog"})
	public void BlogTests_004_DeleteUndeleteBlogPost(){
		CommonFunctions.logOut(driver);
		WikiArticlePageObject home = new WikiArticlePageObject(driver);
		home.openWikiPage();
		blogPostTitle = PageContent.blogPostNamePrefix+home.getTimeStamp();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff, driver);	
		UserProfilePageObject userProfile = home.navigateToProfilePage(Global.DOMAIN, Properties.userNameStaff);
		userProfile.clickOnBlogTab();
		SpecialCreateBlogPageObject createBlogPage = userProfile.clickOnCreateBlogPost();
		createBlogPage.typeBlogPostTitle(blogPostTitle);
		createBlogPage.clickOk();
		createBlogPage.typeInContent(PageContent.blogContent);
		BlogPageObject blogPage = createBlogPage.clickOnPublishButton();
		blogPage.verifyArticleText(PageContent.blogContent);
		blogPage.verifyPageTitle(blogPostTitle);
		blogPage.verifyUsernameFieldPresent(Properties.userNameStaff);
		blogPage.categories_verifyCategoryPresent("Blog posts");	
		blogPage.deleteBlogPost(blogPostTitle);
		blogPage.undeleteArticle();
	}
	
	@Test(groups = { "BlogTests_005", "BlogTests", "Blog"})
	public void BlogTests_005_PostReply(){
		CommonFunctions.logOut(driver);
		WikiArticlePageObject home = new WikiArticlePageObject(driver);
		home.openWikiPage();
		blogPostTitle = PageContent.blogPostNamePrefix+home.getTimeStamp();
		CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);	
		UserProfilePageObject userProfile = home.navigateToProfilePage(Global.DOMAIN, Properties.userName);
		userProfile.clickOnBlogTab();
		SpecialCreateBlogPageObject createBlogPage = userProfile.clickOnCreateBlogPost();
		createBlogPage.typeBlogPostTitle(blogPostTitle);
		createBlogPage.clickOk();
		createBlogPage.typeInContent(PageContent.blogContent);
		BlogPageObject blogPage = createBlogPage.clickOnPublishButton();
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
		CommonFunctions.logOut(driver);
		WikiArticlePageObject home = new WikiArticlePageObject(driver);
		home.openWikiPage();
		blogPostTitle = PageContent.blogPostNamePrefix+home.getTimeStamp();
		CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);	
		UserProfilePageObject userProfile = home.navigateToProfilePage(Global.DOMAIN, Properties.userName);
		userProfile.clickOnBlogTab();
		SpecialCreateBlogPageObject createBlogPage = userProfile.clickOnCreateBlogPost();
		createBlogPage.typeBlogPostTitle(blogPostTitle);
		createBlogPage.clickOk();
		createBlogPage.typeInContent(PageContent.blogContent);
		BlogPageObject blogPage = createBlogPage.clickOnPublishButton();
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
		CommonFunctions.logOut(driver);
		WikiArticlePageObject home = new WikiArticlePageObject(driver);
		home.openWikiPage();
		blogPostTitle = PageContent.blogPostNamePrefix+home.getTimeStamp();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff, driver);	
		UserProfilePageObject userProfile = home.navigateToProfilePage(Global.DOMAIN, Properties.userNameStaff);
		userProfile.clickOnBlogTab();
		SpecialCreateBlogPageObject createBlogPage = userProfile.clickOnCreateBlogPost();
		createBlogPage.typeBlogPostTitle(blogPostTitle);
		createBlogPage.clickOk();
		createBlogPage.typeInContent(PageContent.blogContent);
		BlogPageObject blogPage = createBlogPage.clickOnPublishButton();
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
	
	//test case for https://wikia.fogbugz.com/default.asp?95165
	@Test(groups = { "BlogTests_008", "BlogTests", "Blog"})
	public void BlogTests_008_PostReplyReply(){
		CommonFunctions.logOut(driver);
		WikiArticlePageObject home = new WikiArticlePageObject(driver);
		home.openWikiPage();
		blogPostTitle = PageContent.blogPostNamePrefix+home.getTimeStamp();
		CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);	
		UserProfilePageObject userProfile = home.navigateToProfilePage(Global.DOMAIN, Properties.userName);
		userProfile.clickOnBlogTab();
		SpecialCreateBlogPageObject createBlogPage = userProfile.clickOnCreateBlogPost();
		createBlogPage.typeBlogPostTitle(blogPostTitle);
		createBlogPage.clickOk();
		createBlogPage.typeInContent(PageContent.blogContent);
		BlogPageObject blogPage = createBlogPage.clickOnPublishButton();
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
