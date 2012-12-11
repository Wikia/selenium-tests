package com.wikia.webdriver.TestCases.Blogs;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjects.PageObject.SignUp.UserProfilePageObject;
import com.wikia.webdriver.PageObjects.PageObject.WikiPage.BlogPageObject;
import com.wikia.webdriver.PageObjects.PageObject.WikiPage.SpecialCreateBlogPageObject;
import com.wikia.webdriver.PageObjects.PageObject.WikiPage.WikiArticlePageObject;

public class BlogTests extends TestTemplate{

	@Test(groups = { "BlogTests_001", "BlogTests" })
	public void blogTests_001_CreateBlogPost() 
	{
		WikiArticlePageObject home = new WikiArticlePageObject(driver, Global.DOMAIN, "");
		home.openWikiPage();
		String BlogPostTitle = "blogPost"+home.getTimeStamp(); 
		String BlogContent = "blogContent";
		CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);	
		UserProfilePageObject userProfile = home.navigateToProfilePage(Global.DOMAIN, Properties.userName);
		userProfile.clickOnBlogTab();
		SpecialCreateBlogPageObject createBlogPage = userProfile.clickOnCreateBlogPost();
		createBlogPage.typeBlogPostTitle(BlogPostTitle);
		createBlogPage.clickOk();
		createBlogPage.typeInContent(BlogContent);
		BlogPageObject blogPage = createBlogPage.clickOnPublishButton();
		blogPage.verifyArticleText(BlogContent);
		blogPage.verifyPageTitle(BlogPostTitle);
		blogPage.verifyUsernameFieldPresent(Properties.userName);
		blogPage.categories_verifyCategoryPresent("Blog posts");
		
	}
}
