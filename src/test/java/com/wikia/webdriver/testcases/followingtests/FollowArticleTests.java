/**
 *
 */
package com.wikia.webdriver.testcases.followingtests;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialFollowPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.watch.WatchPageObject;

import org.testng.annotations.Test;

/**
 * @author Karol 'kkarolk' Kujawiak
 * @ownership Content X-Wing
 */
public class FollowArticleTests extends NewTestTemplate {

  String articleName;

  @Test(groups = "FollowArticle")
  @Execute(asUser = User.USER)
  public void FollowArticle_001_setup() {
    ArticlePageObject article = new ArticlePageObject(driver).openRandomArticle(wikiURL);
    articleName = article.getArticleName();
    WatchPageObject watch = article.unfollowArticle(wikiURL);
    watch.confirmWatchUnwatch();
    article.verifyPageUnfollowed();
  }

  @Test(groups = "FollowArticle", dependsOnMethods = {"FollowArticle_001_setup"})
  @Execute(asUser = User.USER)
  public void FollowArticle_002_follow() {
    ArticlePageObject article =
        new ArticlePageObject(driver).open(articleName);
    article.follow();
  }

  @Test(groups = {"FollowArticle", "Follow"}, dependsOnMethods = {"FollowArticle_002_follow"})
  @Execute(asUser = User.USER)
  public void FollowArticle_003_verify() {
    SpecialFollowPageObject follow = new SpecialFollowPageObject(driver, wikiURL);
    follow.verifyFollowedArticle(articleName);
  }
}
