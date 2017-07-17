package com.wikia.webdriver.testcases.followingtests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialFollowPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.watch.WatchPageObject;

@Test(groups = "FollowArticle")
public class FollowArticleTests extends NewTestTemplate {

  private static final String ARTICLE_NAME = "FollowArticleTests";

  @Test
  @Execute(asUser = User.FOLLOW_ARTICLE)
  public void unfollowArticleIfFollowed() {
    new ArticleContent().push(PageContent.ARTICLE_TEXT, ARTICLE_NAME);

    ArticlePageObject article = new ArticlePageObject().open(ARTICLE_NAME);
    WatchPageObject watch = article.unfollowArticle();
    watch.confirmWatchUnwatch();
    article.verifyPageUnfollowed();
  }

  @Test(dependsOnMethods = {"unfollowArticleIfFollowed"})
  @Execute(asUser = User.FOLLOW_ARTICLE)
  public void userCanSeeFollowedArticlesOnHisFollowingSpecialPage() {
    new ArticlePageObject().open(ARTICLE_NAME).follow();
    new SpecialFollowPageObject(driver).open().verifyFollowedArticle(ARTICLE_NAME);
  }
}
