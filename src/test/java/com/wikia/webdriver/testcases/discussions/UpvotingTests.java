package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.Post;
import com.wikia.webdriver.elements.mercury.components.discussions.common.Reply;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostDetailsPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;

import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.DISCUSSIONS_AUTO)
public class UpvotingTests extends NewTestTemplate {

  private static final String DESKTOP_RESOLUTION = "1920x1080";
  private static final String MOBILE_RESOLUTION = "600x800";

  /**
   * ANONS ON MOBILE SECTION
   */

  @Test(groups = "discussions-anonUserOnMobileCanNotVoteForPostDetails")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void anonUserOnMobileCanNotVoteForPostDetails() {
    postDetailsUpvoteButtonClickDoesntAddAnUpvote();
  }

  @Test(groups = "discussions-anonUserOnMobileCanNotVoteForFirstReply")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void anonUserOnMobileCanNotVoteForFirstReply() {
    firstReplyUpvoteButtonClickDoesntAddAnUpvote();
  }

  @Test(groups = "discussions-anonUserOnMobileCanNotUpvote")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void anonUserOnMobileCanNotUpvote() {
    postListUpvoteButtonClickDoesntAddAnUpvote();
  }

  /**
   * ANONS ON DESKTOP SECTION
   */

  @Test(groups = "discussions-anonUserOnDesktopCanNotVoteForPostDetails")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanNotVoteForPostDetails() {
    postDetailsUpvoteButtonClickDoesntAddAnUpvote();
  }

  @Test(groups = "discussions-anonUserOnDesktopCanNotVoteForFirstReply")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanNotVoteForFirstReply() {
    firstReplyUpvoteButtonClickDoesntAddAnUpvote();
  }

  @Test(groups = "discussions-anonUserOnDesktopCanNotUpvote")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanNotUpvote() {
    postListUpvoteButtonClickDoesntAddAnUpvote();
  }

  /**
   * LOGGED IN USERS ON MOBILE SECTION
   */

  @Test(groups = "discussions-loggedInUserOnMobileCanVoteForFirstReply")
  @Execute(asUser = User.USER_3)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void loggedInUserOnMobileCanVoteForFirstReply() {
    firstReplyUpvoteButtonClickAddsAnUpvoteAndSecondClickRemovesTheUpvote();
  }

  @Test(groups = "discussions-loggedInUserOnMobileCanUpvote")
  @Execute(asUser = User.USER_3)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void registeredUserOnMobileCanUpvote() {
    postListUpvoteButtonClickAddsAnUpvoteAndSecondClickRemovesTheUpvote();
  }

  /**
   * LOGGED IN USERS ON DESKTOP SECTION
   */

  @Test(groups = "discussions-loggedInUserOnDesktopCanVoteForFirstReply")
  @Execute(asUser = User.USER_3)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void loggedInUserOnDesktopCanVoteForFirstReply() {
    firstReplyUpvoteButtonClickAddsAnUpvoteAndSecondClickRemovesTheUpvote();
  }

  @Test(groups = "discussions-loggedInUserOnDesktopCanUpvote")
  @Execute(asUser = User.USER_3)
  @InBrowser(browserSize = DESKTOP_RESOLUTION, browser = Browser.FIREFOX)
  public void registeredUserOnDesktopCanUpvote() {
    postListUpvoteButtonClickAddsAnUpvoteAndSecondClickRemovesTheUpvote();
  }

  /**
   * TESTING METHODS SECTION
   */

  private void postListUpvoteButtonClickAddsAnUpvoteAndSecondClickRemovesTheUpvote() {
    Post post = new PostsListPage().open().getPost();
    int postIndex = 0;
    post.isUpvoteButtonVisible(postIndex);
    String firstVoteCount = post.getVoteCount(postIndex);
    post.clickUpvoteButton(postIndex);
    post.waitForVoteCountToChange(postIndex, firstVoteCount);
    String secondVoteCount = post.getVoteCount(postIndex);

    Assertion.assertNotEquals(firstVoteCount, secondVoteCount);

    post.clickUpvoteButton(postIndex);
    post.waitForVoteCountToChange(postIndex, secondVoteCount);
    String thirdVoteCount = post.getVoteCount(postIndex);

    Assertion.assertEquals(firstVoteCount, thirdVoteCount);
  }

  private void postListUpvoteButtonClickDoesntAddAnUpvote() {
    Post post = new PostsListPage().open().getPost();
    int replyIndex = 0;
    post.isUpvoteButtonVisible(replyIndex);
    String firstVoteCount = post.getVoteCount(replyIndex);
    post.clickUpvoteButton(replyIndex);
    post.waitForVoteCountChangeTimeLagToPass();
    String secondVoteCount = post.getVoteCount(replyIndex);

    Assertion.assertEquals(firstVoteCount, secondVoteCount);
  }

  private void firstReplyUpvoteButtonClickAddsAnUpvoteAndSecondClickRemovesTheUpvote() {
    Reply reply = new PostDetailsPage().open().getReply();
    int replyIndex = 0;
    reply.isReplyUpvoteButtonVisible(replyIndex);
    String firstVoteCount = reply.getReplyVoteCount(replyIndex);
    reply.clickReplyUpvoteButton(replyIndex);
    reply.waitForReplyVoteCountToChange(replyIndex, firstVoteCount);
    String secondVoteCount = reply.getReplyVoteCount(replyIndex);

    Assertion.assertNotEquals(firstVoteCount, secondVoteCount);

    reply.clickReplyUpvoteButton(replyIndex);
    reply.waitForReplyVoteCountToChange(replyIndex, secondVoteCount);
    String thirdVoteCount = reply.getReplyVoteCount(replyIndex);

    Assertion.assertEquals(firstVoteCount, thirdVoteCount);
  }

  private void postDetailsUpvoteButtonClickDoesntAddAnUpvote() {
    Post post = new PostDetailsPage().open().getPost();
    post.isUpvoteButtonVisible();
    String firstVoteCount = post.getPostDetailsVoteCount();
    post.clickPostDetailsUpvoteButton();
    post.waitForVoteCountChangeTimeLagToPass();
    String secondVoteCount = post.getPostDetailsVoteCount();

    Assertion.assertEquals(firstVoteCount, secondVoteCount);
  }

  private void firstReplyUpvoteButtonClickDoesntAddAnUpvote() {
    Reply reply = new PostDetailsPage().open().getReply();
    int replyIndex = 0;
    reply.isReplyUpvoteButtonVisible(replyIndex);
    String firstVoteCount = reply.getReplyVoteCount(replyIndex);
    reply.clickReplyUpvoteButton(replyIndex);
    reply.waitForVoteCountChangeTimeLagToPass();
    String secondVoteCount = reply.getReplyVoteCount(replyIndex);

    Assertion.assertEquals(firstVoteCount, secondVoteCount);
  }
}
