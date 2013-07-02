package com.wikia.webdriver.TestCases.SpecialPagesTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.SignUp.UserProfilePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialFollowPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialNewFilesPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialVideosPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Blog.SpecialCreateBlogListingPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Blog.SpecialCreateBlogPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.Blog.BlogPageObject;

public class Following extends TestTemplate{

	String pageName, blogPostTitle;
	
	@Test(groups = {"Follow001", "Follow"})
	public void follow001_Article(){
		CommonFunctions.logOut(driver);
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		article.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password);
		String name = article.followRandomArticle();
		SpecialFollowPageObject follow = new SpecialFollowPageObject(driver);
		follow.openFollowingPage();
		follow.verifyFollowedArticle(name);
		article.unfollowArticle(name);
	}
	
	@Test(groups = {"Follow002", "Follow"})
	public void follow002_Blog(){
		CommonFunctions.logOut(driver);
		WikiArticlePageObject home = new WikiArticlePageObject(driver);
		home.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password);
		BlogPageObject blog = new BlogPageObject(driver);
		blog.openBlogPage(Properties.userName);
		blog.followBlogPage(Properties.userName);
		SpecialFollowPageObject follow = new SpecialFollowPageObject(driver);
		follow.openFollowingPage();
		follow.verifyFollowedBlog(Properties.userName);
		blog.unfollowBlogPage(Properties.userName);
	}
	
	@Test(groups = {"Follow003", "Follow"})
	public void follow003_BlogPosts(){
		CommonFunctions.logOut(driver);
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		article.openWikiPage(); 
		String blogPostTitle = PageContent.blogPostNamePrefix+article.getTimeStamp(); 
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
		blogPage.followBlogPostPage(Properties.userName, blogPostTitle);
		SpecialFollowPageObject follow = new SpecialFollowPageObject(driver);
		follow.openFollowingPage();
		follow.verifyFollowedBlogPost(blogPostTitle);
	}
	
	@Test(groups = {"Follow004", "Follow"})
	public void follow004_Photos(){
		CommonFunctions.logOut(driver);
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		article.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password);
		SpecialNewFilesPageObject special = article.openSpecialNewFiles();
		String image = special.followRandomImage();
		SpecialFollowPageObject follow = new SpecialFollowPageObject(driver);
		follow.openFollowingPage();
		follow.verifyFollowedImageVideo(image);
		special.unfollowImage(image);
	}
	
	@Test(groups = {"Follow005", "Follow"})
	public void follow005_Videos(){
		CommonFunctions.logOut(driver);
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		article.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password);
		SpecialVideosPageObject video = new SpecialVideosPageObject(driver);
		video.openSpecialVideoPage();
		String videoName = video.followRandomVideo();
		SpecialFollowPageObject follow = new SpecialFollowPageObject(driver);
		follow.openFollowingPage();
		follow.verifyFollowedImageVideo(videoName);
		video.unfollowVideo(videoName);
	}
	
	
	@Test(groups = {"Follow006", "Follow"})
	public void follow006_FollowBlogListing(){
		
		SpecialCreateBlogListingPageObject blogList = new SpecialCreateBlogListingPageObject(driver);
		blogList.openRandomArticleByUrl();
		CommonFunctions.logInCookie(Properties.userName, Properties.password);
		blogPostTitle = PageContent.blogListName+blogList.getTimeStamp();
		blogList
			.openCreateBlogListingPage()
			.typeTitle(blogPostTitle)
			.clickSavePageButton()
			.verifyBlogListPage(blogPostTitle)
			.followBlogListingPage(blogPostTitle);
		SpecialFollowPageObject follow = new SpecialFollowPageObject(driver);
		follow.openFollowingPage();
		follow.verifyFollowedBlogPost(blogPostTitle);
		
	}
}
