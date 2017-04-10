package com.wikia.webdriver.testcases.followingtests;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.UserProfilePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialFollowPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.watch.WatchPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.blog.BlogPageObject;

import org.testng.annotations.Test;

public class FollowBlogTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();
  String blogTitle;

  @Test(groups = "FollowBlog")
  @Execute(asUser = User.USER)
  public void FollowBlog_001_setup() {
    WikiBasePageObject base = new WikiBasePageObject();
    UserProfilePageObject userProfile = base.openProfilePage(credentials.userName, wikiURL);
    userProfile.clickOnBlogTab();
    BlogPageObject blogPage = userProfile.openFirstPost();
    blogTitle = blogPage.getBlogName();
    WatchPageObject watch = blogPage.unfollowBlogPage();
    watch.confirmWatchUnwatch();
    blogPage.verifyPageUnfollowed();
  }

  @Test(groups = "FollowBlog", dependsOnMethods = {"FollowBlog_001_setup"})
  @Execute(asUser = User.USER)
  public void FollowBlog_002_follow() {
    WikiBasePageObject base = new WikiBasePageObject();
    BlogPageObject blog = base.openBlogByName(wikiURL, blogTitle, credentials.userName);
    blog.follow();
  }

  @Test(groups = {"FollowBlog", "Follow"}, dependsOnMethods = {"FollowBlog_002_follow"})
  @Execute(asUser = User.USER)
  public void FollowBlog_003_verify() {
    new SpecialFollowPageObject(driver).open().verifyFollowedBlog(credentials.userName, blogTitle);
  }

}
