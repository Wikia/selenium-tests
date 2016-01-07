package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.discussions.PostDetailsPage;

import org.testng.annotations.Test;

public class PostDetailsUpvote extends NewTestTemplate {

  private static final String DESKTOP_RESOLUTION = "1366x768";
  private static final String MOBILE_RESOLUTION = "600x800";

  /**
   * LOGGED IN USERS ON MOBILE SECTION
   */

  @Test
  @Execute(asUser = User.USER_3)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void loggedInUserOnMobileCanSeePostDetailsList() {
    postDetailsUpvoteButtonClickAddsAnUpvoteAndSecondClickRemovesTheUpvote();
  }

  @Test
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void AnonymousUserOnMobileCanNotVoteForPostDetails() {
    postDetailsUpvoteButtonClickDoenstAddAnUpvote();
  }

  @Test
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void AnonymousUserOnMobileCanNotVoteForFirstReply() {
    firstReplyUpvoteButtonClickDoenstAddAnUpvote();
  }

  /**
   * LOGGED IN USERS ON DESKTOP SECTION
   */

  @Test
  @Execute(asUser = User.USER_3)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void loggedInUserOnDesktopCanSeePostDetailsList() {
    postDetailsUpvoteButtonClickAddsAnUpvoteAndSecondClickRemovesTheUpvote();
  }

  @Test
  @Execute(asUser = User.USER_3)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void loggedInUserOnMobileCanVoteForFirstReply() {
    firstReplyUpvoteButtonClickAddsAnUpvoteAndSecondClickRemovesTheUpvote();
  }

  /**
   * LOGGED IN USERS ON DESKTOP SECTION
   */

  @Test
  @Execute(asUser = User.USER_3)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void loggedInUserOnDesktopCanVoteForFirstReply() {
    firstReplyUpvoteButtonClickAddsAnUpvoteAndSecondClickRemovesTheUpvote();
  }

  /**
   * TESTING METHODS SECTION
   */

  public void postDetailsUpvoteButtonClickAddsAnUpvoteAndSecondClickRemovesTheUpvote() {
    PostDetailsPage postDetails = new PostDetailsPage(driver).open();
    postDetails.isUpvoteButtonVisible();
    String firstVoteCount = postDetails.getPostDetailsVoteCount();
    postDetails.clickPostDetailsUpvoteButton();
    postDetails.waitForPostDetailsVoteCountToChange(firstVoteCount);
    String secondVoteCount = postDetails.getPostDetailsVoteCount();
    Assertion.assertNotEquals(firstVoteCount, secondVoteCount);
    postDetails.clickPostDetailsUpvoteButton();
    postDetails.waitForPostDetailsVoteCountToChange(secondVoteCount);
    String thirdVoteCount = postDetails.getPostDetailsVoteCount();
    Assertion.assertEquals(firstVoteCount, thirdVoteCount);
  }

  public void firstReplyUpvoteButtonClickAddsAnUpvoteAndSecondClickRemovesTheUpvote() {
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

  public void postDetailsUpvoteButtonClickDoenstAddAnUpvote() {
    PostDetailsPage postDetails = new PostDetailsPage(driver).open();
    postDetails.isUpvoteButtonVisible();
    String firstVoteCount = postDetails.getPostDetailsVoteCount();
    postDetails.clickPostDetailsUpvoteButton();
    postDetails.waitForVoteCountChangeTimeLagToPass();
    String secondVoteCount = postDetails.getPostDetailsVoteCount();
    Assertion.assertEquals(firstVoteCount, secondVoteCount);
  }

  public void firstReplyUpvoteButtonClickDoenstAddAnUpvote() {
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
