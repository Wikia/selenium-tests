/**
 *
 */
package com.wikia.webdriver.testcases.followingtests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.UserProfilePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialFollowPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.watch.WatchPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.blog.BlogPageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
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

	@Test(groups = "FollowBlog", dependsOnMethods = {"FollowBlog_001_setup"})
	public void FollowBlog_002_follow() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		BlogPageObject blog = base.openBlogByName(wikiURL, blogTitle, credentials.userName);
		blog.follow();
	}

	@Test(groups = {"FollowBlog", "Follow"}, dependsOnMethods = {"FollowBlog_002_follow"})
	public void FollowBlog_003_verify() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		SpecialFollowPageObject follow = new SpecialFollowPageObject(driver, wikiURL);
		follow.verifyFollowedBlog(credentials.userName, blogTitle);
	}

}
