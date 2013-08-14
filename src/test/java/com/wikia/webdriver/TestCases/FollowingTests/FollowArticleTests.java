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
	public void follow_setup() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		articleName = article.getArticleName();
		WatchPageObject watch = article.unfollowArticle(wikiURL);
		watch.confirm();
		article.verifyPageUnfollowed();
	}

	@Test(dependsOnMethods={"follow_setup"})
	public void follow_article() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		article.follow();
	}

	@Test(groups = {"FollowArticle", "Follow"}, dependsOnMethods={"follow_article"})
	public void follow_verification() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		SpecialFollowPageObject follow = new SpecialFollowPageObject(driver, wikiURL);
		follow.verifyFollowedArticle(articleName);
	}
}
