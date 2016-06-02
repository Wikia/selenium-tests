package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.discussions.PostDetailsPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.discussions.PostsListPage;

import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.DISCUSSIONS_AUTO)
public class Upvoting extends NewTestTemplate {

  private static final String DESKTOP_RESOLUTION = "1920x1080";
  private static final String MOBILE_RESOLUTION = "600x800";

  /**
   * ANONS ON MOBILE SECTION
   */

  @Test(groups = "discussions-anonUserOnMobileCanNotVoteForPostDetails", enabled = false)
  @RelatedIssue(issueID = "SOC-2596")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void anonUserOnMobileCanNotVoteForPostDetails() {
    postDetailsUpvoteButtonClickDoenstAddAnUpvote();
  }

  @Test(groups = "discussions-anonUserOnMobileCanNotVoteForFirstReply")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void anonUserOnMobileCanNotVoteForFirstReply() {
    firstReplyUpvoteButtonClickDoenstAddAnUpvote();
  }

  @Test(groups = "discussions-anonUserOnMobileCanNotUpvote")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void anonUserOnMobileCanNotUpvote() {
    postListUpvoteButtonClickDoenstAddAnUpvote();
  }

  /**
   * ANONS ON DESKTOP SECTION
   */

  @Test(groups = "discussions-anonUserOnDesktopCanNotVoteForPostDetails", enabled = false)
  @RelatedIssue(issueID = "SOC-2596")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanNotVoteForPostDetails() {
    postDetailsUpvoteButtonClickDoenstAddAnUpvote();
  }

  @Test(groups = "discussions-anonUserOnDesktopCanNotVoteForFirstReply")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanNotVoteForFirstReply() {
    firstReplyUpvoteButtonClickDoenstAddAnUpvote();
  }

  @Test(groups = "discussions-anonUserOnDesktopCanNotUpvote")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanNotUpvote() {
    postListUpvoteButtonClickDoenstAddAnUpvote();
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

  @Test(groups = "discussions-loggedInUserOnMobileCanUpvote", enabled = false)
  @RelatedIssue(issueID = "SOC-2596")
  @Execute(asUser = User.USER_3)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void loggedInUserOnMobileCanUpvote() {
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

  @Test(groups = "discussions-loggedInUserOnDesktopCanUpvote", enabled = false)
  @RelatedIssue(issueID = "SOC-2596")
  @Execute(asUser = User.USER_3)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void loggedInUserOnDesktopCanUpvote() {
    postListUpvoteButtonClickAddsAnUpvoteAndSecondClickRemovesTheUpvote();
  }

  /**
   * TESTING METHODS SECTION
   */

  private void postListUpvoteButtonClickAddsAnUpvoteAndSecondClickRemovesTheUpvote() {
    PostsListPage postList = new PostsListPage(driver).open();
    int replyIndex = 0;
    postList.isUpvoteButtonVisible(replyIndex);
    String firstVoteCount = postList.getVoteCount(replyIndex);
    postList.clickUpvoteButton(replyIndex);
    postList.waitForVoteCountToChange(replyIndex, firstVoteCount);
    String secondVoteCount = postList.getVoteCount(replyIndex);
    Assertion.assertNotEquals(firstVoteCount, secondVoteCount);
    postList.clickUpvoteButton(replyIndex);
    postList.waitForVoteCountToChange(replyIndex, secondVoteCount);
    String thirdVoteCount = postList.getVoteCount(replyIndex);
    Assertion.assertEquals(firstVoteCount, thirdVoteCount);
  }

  private void postListUpvoteButtonClickDoenstAddAnUpvote() {
    PostsListPage postList = new PostsListPage(driver).open();
    int replyIndex = 0;
    postList.isUpvoteButtonVisible(replyIndex);
    String firstVoteCount = postList.getVoteCount(replyIndex);
    postList.clickUpvoteButton(replyIndex);
    postList.waitForVoteCountChangeTimeLagToPass();
    String secondVoteCount = postList.getVoteCount(replyIndex);
    Assertion.assertEquals(firstVoteCount, secondVoteCount);
  }

  private void firstReplyUpvoteButtonClickAddsAnUpvoteAndSecondClickRemovesTheUpvote() {
    PostDetailsPage postDetails = new PostDetailsPage(driver).open();
    int replyIndex = 0;
    postDetails.isReplyUpvoteButtonVisible(replyIndex);
    String firstVoteCount = postDetails.getReplyVoteCount(replyIndex);
    postDetails.clickReplyUpvoteButton(replyIndex);
    postDetails.waitForReplyVoteCountToChange(replyIndex, firstVoteCount);
    String secondVoteCount = postDetails.getReplyVoteCount(replyIndex);
    Assertion.assertNotEquals(firstVoteCount, secondVoteCount);
    postDetails.clickReplyUpvoteButton(replyIndex);
    postDetails.waitForReplyVoteCountToChange(replyIndex, secondVoteCount);
    String thirdVoteCount = postDetails.getReplyVoteCount(replyIndex);
    Assertion.assertEquals(firstVoteCount, thirdVoteCount);
  }

  private void postDetailsUpvoteButtonClickDoenstAddAnUpvote() {
    PostDetailsPage postDetails = new PostDetailsPage(driver).open();
    postDetails.isUpvoteButtonVisible();
    String firstVoteCount = postDetails.getPostDetailsVoteCount();
    postDetails.clickPostDetailsUpvoteButton();
    postDetails.waitForVoteCountChangeTimeLagToPass();
    String secondVoteCount = postDetails.getPostDetailsVoteCount();
    Assertion.assertEquals(firstVoteCount, secondVoteCount);
  }

  private void firstReplyUpvoteButtonClickDoenstAddAnUpvote() {
    PostDetailsPage postDetails = new PostDetailsPage(driver).open();
    int replyIndex = 0;
    postDetails.isReplyUpvoteButtonVisible(replyIndex);
    String firstVoteCount = postDetails.getReplyVoteCount(replyIndex);
    postDetails.clickReplyUpvoteButton(replyIndex);
    postDetails.waitForVoteCountChangeTimeLagToPass();
    String secondVoteCount = postDetails.getReplyVoteCount(replyIndex);
    Assertion.assertEquals(firstVoteCount, secondVoteCount);
  }
}
