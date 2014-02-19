/**
 *
 */
package com.wikia.webdriver.TestCases.FollowingTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.SignUp.UserProfilePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialFollowPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Watch.WatchPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.Blog.BlogPageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class FollowBlogTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();
	String blogTitle;

	@Test(groups = "FollowBlog")
	public void FollowBlog_001_setup() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		UserProfilePageObject userProfile = base.openProfilePage(credentials.userName, wikiURL);
		userProfile.clickOnBlogTab();
		BlogPageObject blogPage = userProfile.openFirstPost();
		blogTitle = blogPage.getBlogName();
		WatchPageObject watch = blogPage.unfollowBlogPage();
		watch.confirmWatchUnwatch();
		blogPage.verifyPageUnfollowed();
	}

	@Test(groups = "FollowBlog", dependsOnMethods={"FollowBlog_001_setup"})
	public void FollowBlog_002_follow() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		BlogPageObject blog = base.openBlogByName(wikiURL, blogTitle, credentials.userName);
		blog.follow();
	}

	@Test(groups = {"FollowBlog", "Follow"}, dependsOnMethods={"FollowBlog_002_follow"})
	public void FollowBlog_003_verify() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		SpecialFollowPageObject follow = new SpecialFollowPageObject(driver, wikiURL);
		follow.verifyFollowedBlog(credentials.userName, blogTitle);
	}

}
