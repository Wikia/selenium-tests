package com.wikia.webdriver.TestCases.BlogTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.SignUp.UserProfilePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Blog.SpecialCreateBlogPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.Blog.BlogPageObject;

public class BlogTests extends NewTestTemplate{

	Credentials credentials = config.getCredentials();

	/*
	 * 1. Create blog post using "Create blog post button" (one case)
	 * 2. Create blog post using "Special:CreateBlogPage" (data provider)
	 * 3. Edit existing blog post
	 * 4. Delete/Undelete existing blog post
	 * 5. Naviagte to blog post using read more button
	 */

	@Test(groups = { "BlogTests_001", "BlogTests", "Blog"})
	public void BlogTests_001_CreateBlogPost(){
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage(wikiURL);
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(credentials.userName, credentials.password, wikiURL);
		UserProfilePageObject userProfile = new UserProfilePageObject(driver);
		userProfile.navigateToProfilePage(credentials.userName);
		userProfile.clickOnBlogTab();
		SpecialCreateBlogPageObject createBlogPage = userProfile.clickOnCreateBlogPost();
		createBlogPage.typeBlogPostTitle(createBlogPage.getBlogName());
		createBlogPage.clickOk();
		createBlogPage.typeInContent(PageContent.blogContent);
		BlogPageObject blogPage = createBlogPage.clickOnPublishBlogPostButton();
		blogPage.verifyArticleText(PageContent.blogContent);
		blogPage.verifyPageTitle(createBlogPage.getBlogName());
		blogPage.verifyUsernameFieldPresent(credentials.userName);
		blogPage.categories_verifyCategoryPresent("Blog posts");
	}

//	@Test(groups = { "BlogTests_002", "BlogTests", "Blog"})
//	public void BlogTests_002_EditBlogPost(){
//		WikiBasePageObject wiki = new WikiBasePageObject(driver);
//		wiki.openWikiPage();
//		blogPostTitle = PageContent.blogPostNamePrefix + wiki.getTimeStamp();
//		CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);
//		UserProfilePageObject userProfile = new UserProfilePageObject(driver);
//		userProfile.navigateToProfilePage(Properties.userName);
//		userProfile.clickOnBlogTab();
//		SpecialCreateBlogPageObject createBlogPage = userProfile.clickOnCreateBlogPost();
//		createBlogPage.typeBlogPostTitle(blogPostTitle);
//		createBlogPage.clickOk();
//		createBlogPage.typeInContent(PageContent.blogContent);
//		BlogPageObject blogPage = createBlogPage.clickOnPublishBlogPostButton();
//		blogPage.verifyArticleText(PageContent.blogContent);
//		blogPage.verifyPageTitle(blogPostTitle);
//		blogPage.verifyUsernameFieldPresent(Properties.userName);
//		blogPage.categories_verifyCategoryPresent("Blog posts");
//		createBlogPage = blogPage.editBlog();
//		createBlogPage.deleteArticleContent();
//		createBlogPage.typeInContent(PageContent.blogContentEdit);
//		blogPage = createBlogPage.clickOnPublishBlogPostButton();
//		blogPage.verifyArticleText(PageContent.blogContentEdit);
//	}
//
//	@Test(groups = { "BlogTests_003", "BlogTests", "Blog"})
//	public void BlogTests_003_DeleteBlogPost(){
//		WikiBasePageObject wiki = new WikiBasePageObject(driver);
//		wiki.openWikiPage();
//		blogPostTitle = PageContent.blogPostNamePrefix+ wiki.getTimeStamp();
//		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff, driver);
//		UserProfilePageObject userProfile = new UserProfilePageObject(driver);
//		userProfile.navigateToProfilePage(Properties.userNameStaff);
//		userProfile.clickOnBlogTab();
//		SpecialCreateBlogPageObject createBlogPage = userProfile.clickOnCreateBlogPost();
//		createBlogPage.typeBlogPostTitle(blogPostTitle);
//		createBlogPage.clickOk();
//		createBlogPage.typeInContent(PageContent.blogContent);
//		BlogPageObject blogPage = createBlogPage.clickOnPublishBlogPostButton();
//		blogPage.verifyArticleText(PageContent.blogContent);
//		blogPage.verifyPageTitle(blogPostTitle);
//		blogPage.verifyUsernameFieldPresent(Properties.userNameStaff);
//		blogPage.categories_verifyCategoryPresent("Blog posts");
//		blogPage.deleteBlogPost(blogPostTitle);
//	}
//
//	@Test(groups = { "BlogTests_004", "BlogTests", "Blog"})
//	public void BlogTests_004_DeleteUndeleteBlogPost(){
//		WikiBasePageObject wiki = new WikiBasePageObject(driver);
//		wiki.openWikiPage();
//		blogPostTitle = PageContent.blogPostNamePrefix + wiki.getTimeStamp();
//		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff, driver);
//		UserProfilePageObject userProfile = new UserProfilePageObject(driver);
//		userProfile.navigateToProfilePage(Properties.userNameStaff);
//		userProfile.clickOnBlogTab();
//		SpecialCreateBlogPageObject createBlogPage = userProfile.clickOnCreateBlogPost();
//		createBlogPage.typeBlogPostTitle(blogPostTitle);
//		createBlogPage.clickOk();
//		createBlogPage.typeInContent(PageContent.blogContent);
//		BlogPageObject blogPage = createBlogPage.clickOnPublishBlogPostButton();
//		blogPage.verifyArticleText(PageContent.blogContent);
//		blogPage.verifyPageTitle(blogPostTitle);
//		blogPage.verifyUsernameFieldPresent(Properties.userNameStaff);
//		blogPage.categories_verifyCategoryPresent("Blog posts");
//		blogPage.deleteBlogPost(blogPostTitle);
//		blogPage.undeleteArticle();
//	}

	}
