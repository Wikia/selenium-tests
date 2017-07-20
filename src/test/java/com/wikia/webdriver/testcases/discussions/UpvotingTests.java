package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.core.helpers.Emulator;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.Post;
import com.wikia.webdriver.elements.mercury.components.discussions.common.Reply;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostDetailsPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;

@Execute(onWikia = MercuryWikis.DISCUSSIONS_1)
@Test(groups = {"discussions-upvoting"})
public class UpvotingTests extends NewTestTemplate {
  
  /**
   * ANONS ON MOBILE SECTION
   */

  @Test(groups = "discussions-anonUserOnMobileCanNotVoteForPostDetails")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotVoteForPostDetails() {
    postDetailsUpvoteButtonClickDoesntAddAnUpvote();
  }

  @Test(groups = "discussions-anonUserOnMobileCanNotVoteForFirstReply")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotVoteForFirstReply() {
    firstReplyUpvoteButtonClickDoesntAddAnUpvote();
  }

  @Test(groups = "discussions-anonUserOnMobileCanNotUpvote")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotUpvote() {
    postListUpvoteButtonClickDoesntAddAnUpvote();
  }

  /**
   * ANONS ON DESKTOP SECTION
   */

  @Test(groups = "discussions-anonUserOnDesktopCanNotVoteForPostDetails")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonUserOnDesktopCanNotVoteForPostDetails() {
    postDetailsUpvoteButtonClickDoesntAddAnUpvote();
  }

  @Test(groups = "discussions-anonUserOnDesktopCanNotVoteForFirstReply")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonUserOnDesktopCanNotVoteForFirstReply() {
    firstReplyUpvoteButtonClickDoesntAddAnUpvote();
  }

  @Test(groups = "discussions-anonUserOnDesktopCanNotUpvote")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonUserOnDesktopCanNotUpvote() {
    postListUpvoteButtonClickDoesntAddAnUpvote();
  }

  /**
   * LOGGED IN USERS ON MOBILE SECTION
   */

  @Test(groups = "discussions-loggedInUserOnMobileCanVoteForFirstReply")
  @Execute(asUser = User.USER_3)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void loggedInUserOnMobileCanVoteForFirstReply() {
    firstReplyUpvoteButtonClickAddsAnUpvoteAndSecondClickRemovesTheUpvote();
  }

  @Test(groups = "discussions-loggedInUserOnMobileCanUpvote")
  @Execute(asUser = User.USER_3)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void registeredUserOnMobileCanUpvote() {
    postListUpvoteButtonClickAddsAnUpvoteAndSecondClickRemovesTheUpvote();
  }

  /**
   * LOGGED IN USERS ON DESKTOP SECTION
   */

  @Test(groups = "discussions-loggedInUserOnDesktopCanVoteForFirstReply")
  @Execute(asUser = User.USER_3)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void loggedInUserOnDesktopCanVoteForFirstReply() {
    firstReplyUpvoteButtonClickAddsAnUpvoteAndSecondClickRemovesTheUpvote();
  }

  @Test(groups = "discussions-loggedInUserOnDesktopCanUpvote")
  @Execute(asUser = User.USER_3)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void registeredUserOnDesktopCanUpvote() {
    postListUpvoteButtonClickAddsAnUpvoteAndSecondClickRemovesTheUpvote();
  }

  /**
   * TESTING METHODS SECTION
   */

  private void postListUpvoteButtonClickAddsAnUpvoteAndSecondClickRemovesTheUpvote() {
    Post post = findPosts();
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

  private Post findPosts() {
    return new PostsListPage().open().getPost();
  }
  
  private void postListUpvoteButtonClickDoesntAddAnUpvote() {
    Post post = findPosts();
    int replyIndex = 0;
    post.isUpvoteButtonVisible(replyIndex);
    String firstVoteCount = post.getVoteCount(replyIndex);
    post.clickUpvoteButton(replyIndex);
    post.waitForVoteCountChangeTimeLagToPass();
    String secondVoteCount = post.getVoteCount(replyIndex);

    Assertion.assertEquals(firstVoteCount, secondVoteCount);
  }

  private void firstReplyUpvoteButtonClickAddsAnUpvoteAndSecondClickRemovesTheUpvote() {
    Reply reply = new PostDetailsPage().openDefaultPost().getReply();
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
    Post post = new PostDetailsPage().openDefaultPost().getPost();
    post.isUpvoteButtonVisible();
    String firstVoteCount = post.getPostDetailsVoteCount();
    post.clickPostDetailsUpvoteButton();
    post.waitForVoteCountChangeTimeLagToPass();
    String secondVoteCount = post.getPostDetailsVoteCount();

    Assertion.assertEquals(firstVoteCount, secondVoteCount);
  }

  private void firstReplyUpvoteButtonClickDoesntAddAnUpvote() {
    Reply reply = new PostDetailsPage().openDefaultPost().getReply();
    int replyIndex = 0;
    reply.isReplyUpvoteButtonVisible(replyIndex);
    String firstVoteCount = reply.getReplyVoteCount(replyIndex);
    reply.clickReplyUpvoteButton(replyIndex);
    reply.waitForVoteCountChangeTimeLagToPass();
    String secondVoteCount = reply.getReplyVoteCount(replyIndex);

    Assertion.assertEquals(firstVoteCount, secondVoteCount);
  }
}
