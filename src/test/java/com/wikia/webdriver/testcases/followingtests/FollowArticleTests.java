/**
 *
 */
package com.wikia.webdriver.testcases.followingtests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.annotations.ExecuteAs;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialFollowPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.watch.WatchPageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class FollowArticleTests extends NewTestTemplate {
  String articleName;

  @Test(groups = "FollowArticle")
  @ExecuteAs(user = User.USER)
  public void FollowArticle_001_setup() {
    ArticlePageObject article = new ArticlePageObject(driver).openRandomArticle(wikiURL);
    articleName = article.getArticleName();
    WatchPageObject watch = article.unfollowArticle(wikiURL);
    watch.confirmWatchUnwatch();
    article.verifyPageUnfollowed();
  }

  @Test(groups = "FollowArticle", dependsOnMethods = {"FollowArticle_001_setup"})
  @ExecuteAs(user = User.USER)
  public void FollowArticle_002_follow() {
    ArticlePageObject article =
        new ArticlePageObject(driver).openArticleByName(wikiURL, articleName);
    article.follow();
  }

  @Test(groups = {"FollowArticle", "Follow"}, dependsOnMethods = {"FollowArticle_002_follow"})
  @ExecuteAs(user = User.USER)
  public void FollowArticle_003_verify() {
    SpecialFollowPageObject follow = new SpecialFollowPageObject(driver, wikiURL);
    follow.verifyFollowedArticle(articleName);
  }
}
