/**
 *
 */
package com.wikia.webdriver.testcases.followingtests;

import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialFollowPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.watch.WatchPageObject;

import org.testng.annotations.Test;

/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class FollowArticleTests extends NewTestTemplate {

  Credentials credentials = config.getCredentials();
  String articleName;

  @Test(groups = "FollowArticle")
  public void FollowArticle_001_setup() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName, credentials.password, wikiURL);
    ArticlePageObject article = base.openRandomArticle(wikiURL);
    articleName = article.getArticleName();
    WatchPageObject watch = article.unfollowArticle(wikiURL);
    watch.confirmWatchUnwatch();
    article.verifyPageUnfollowed();
  }

  @Test(groups = "FollowArticle", dependsOnMethods = {"FollowArticle_001_setup"})
  public void FollowArticle_002_follow() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName, credentials.password, wikiURL);
    ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
    article.follow();
  }

  @Test(groups = {"FollowArticle", "Follow"}, dependsOnMethods = {"FollowArticle_002_follow"})
  public void FollowArticle_003_verify() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName, credentials.password, wikiURL);
    SpecialFollowPageObject follow = new SpecialFollowPageObject(driver, wikiURL);
    follow.verifyFollowedArticle(articleName);
  }
}
