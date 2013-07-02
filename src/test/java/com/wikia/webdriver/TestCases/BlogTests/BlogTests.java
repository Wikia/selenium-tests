package com.wikia.webdriver.TestCases.BlogTests;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.SignUp.UserProfilePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Blog.SpecialCreateBlogPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.Blog.BlogPageObject;
import org.testng.annotations.Test;

public class BlogTests extends TestTemplate{

	String blogPostTitle;

	@Test(groups = { "BlogTests_001", "BlogTests", "Blog"})
	public void BlogTests_001_CreateBlogPost(){
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		blogPostTitle = PageContent.blogPostNamePrefix + wiki.getTimeStamp();
		CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);
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
	}

	@Test(groups = { "BlogTests_002", "BlogTests", "Blog"})
	public void BlogTests_002_EditBlogPost(){
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		blogPostTitle = PageContent.blogPostNamePrefix + wiki.getTimeStamp();
		CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);
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
		createBlogPage = blogPage.editBlog();
		createBlogPage.deleteArticleContent();
		createBlogPage.typeInContent(PageContent.blogContentEdit);
		blogPage = createBlogPage.clickOnPublishBlogPostButton();
		blogPage.verifyArticleText(PageContent.blogContentEdit);
	}

	@Test(groups = { "BlogTests_003", "BlogTests", "Blog"})
	public void BlogTests_003_DeleteBlogPost(){
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		blogPostTitle = PageContent.blogPostNamePrefix+ wiki.getTimeStamp();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff, driver);
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
		blogPage.deleteBlogPost(blogPostTitle);
	}

	@Test(groups = { "BlogTests_004", "BlogTests", "Blog"})
	public void BlogTests_004_DeleteUndeleteBlogPost(){
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		blogPostTitle = PageContent.blogPostNamePrefix + wiki.getTimeStamp();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff, driver);
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
		blogPage.deleteBlogPost(blogPostTitle);
		blogPage.undeleteArticle();
	}

	@Test(groups = { "BlogTests_005", "BlogTests", "Blog"})
	public void BlogTests_005_PostReply(){
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		blogPostTitle = PageContent.blogPostNamePrefix + wiki.getTimeStamp();
		CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);
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
		CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);
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
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff, driver);
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
		CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);
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
