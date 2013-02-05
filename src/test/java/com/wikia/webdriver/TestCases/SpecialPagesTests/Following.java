package com.wikia.webdriver.TestCases.SpecialPagesTests;

import org.openqa.selenium.remote.server.handler.html5.GetLocalStorageItem;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.SignUp.UserProfilePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialFollowPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialVideosPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.BlogPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.SpecialCreateBlogPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.SpecialNewFilesPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;

public class Following extends TestTemplate{

	String pageName;
	
	@Test(groups = {"follow001", "follow"})
	public void follow001_Article(){
		CommonFunctions.logOut(driver);
		WikiArticlePageObject article = new WikiArticlePageObject(driver, Global.DOMAIN, "");
		article.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password);
		String name = article.followRandomArticle();
		SpecialFollowPageObject follow = new SpecialFollowPageObject(driver);
		follow.openFollowingPage();
		follow.verifyFollowedArticle(name);
		article.unfollowArticle(name);
	}
	
	@Test(groups = {"follow002", "follow"})
	public void follow002_Blog(){
		CommonFunctions.logOut(driver);
		WikiArticlePageObject home = new WikiArticlePageObject(driver, Global.DOMAIN, "");
		home.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password);
		BlogPageObject blog = new BlogPageObject(driver, Global.DOMAIN, "");
		blog.openBlogPage(Properties.userName);
		blog.followBlogPage(Properties.userName);
		SpecialFollowPageObject follow = new SpecialFollowPageObject(driver);
		follow.openFollowingPage();
		follow.verifyFollowedBlog(Properties.userName);
		blog.unfollowBlogPage(Properties.userName);
	}
	
	@Test(groups = {"follow003", "follow"})
	public void follow003_BlogPosts(){
		CommonFunctions.logOut(driver);
		WikiArticlePageObject home = new WikiArticlePageObject(driver, Global.DOMAIN, "");
		home.openWikiPage(); 
		String blogPostTitle = PageContent.blogPostNamePrefix+home.getTimeStamp(); 
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
		blogPage.followBlogPostPage(Properties.userName, blogPostTitle);
		SpecialFollowPageObject follow = new SpecialFollowPageObject(driver);
		follow.openFollowingPage();
		follow.verifyFollowedBlogPost(blogPostTitle);
	}
	
	@Test(groups = {"follow004", "follow"})
	public void follow004_Photos(){
		CommonFunctions.logOut(driver);
		WikiArticlePageObject article = new WikiArticlePageObject(driver, Global.DOMAIN, "");
		article.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password);
		SpecialNewFilesPageObject special = article.openSpecialNewFiles();
		String image = special.followRandomImage();
		SpecialFollowPageObject follow = new SpecialFollowPageObject(driver);
		follow.openFollowingPage();
		follow.verifyFollowedImageVideo(image);
		special.unfollowImage(image);
	}
	
	@Test(groups = {"follow005", "follow"})
	public void follow005_Videos(){
		CommonFunctions.logOut(driver);
		WikiArticlePageObject article = new WikiArticlePageObject(driver, Global.DOMAIN, "");
		article.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password);
		SpecialVideosPageObject video = new SpecialVideosPageObject(driver, Global.DOMAIN);
		video.openSpecialVideoPage();
		String[] arr = video.followRandomVideo();
		SpecialFollowPageObject follow = new SpecialFollowPageObject(driver);
		follow.openFollowingPage();
		follow.verifyFollowedImageVideo(arr[1]);
		video.unfollowVideo(arr[0]);
	}
}
