/**
 *
 */
package com.wikia.webdriver.TestCases.FollowingTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialFollowPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Watch.WatchPageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class FollowArticleTests extends NewTestTemplate{

	Credentials credentials = config.getCredentials();
	String articleName;

	@Test
	public void FollowArticle_001_setup() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		articleName = article.getArticleName();
		WatchPageObject watch = article.unfollowArticle(wikiURL);
		watch.confirmWatchUnwatch();
		article.verifyPageUnfollowed();
	}

	@Test(dependsOnMethods={"FollowArticle_001_setup"})
	public void FollowArticle_002_follow() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		article.follow();
	}

	@Test(groups = {"FollowArticle", "Follow"}, dependsOnMethods={"FollowArticle_002_follow"})
	public void FollowArticle_003_verify() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		SpecialFollowPageObject follow = new SpecialFollowPageObject(driver, wikiURL);
		follow.verifyFollowedArticle(articleName);
	}
}
