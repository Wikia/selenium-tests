package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.discussions.PostDetailsPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.discussions.PostsListPage;

import org.testng.annotations.Test;

/**
 * @ownership Social Wikia
 */
@Execute(onWikia = MercuryWikis.MEDIAWIKI_119)
@Test(groups="discussions")
public class Upvoting extends NewTestTemplate {

  private static final String DESKTOP_RESOLUTION = "1366x768";
  private static final String MOBILE_RESOLUTION = "600x800";

  /**
   * ANONS ON MOBILE SECTION
   */

  @Test(groups = "discussions-anonUserOnMobileCanNotVoteForPostDetails")
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

  @Test(groups = "discussions-anonUserOnDesktopCanNotVoteForPostDetails")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanNotVoteForPostDetails() {
    postDetailsUpvoteButtonClickDoenstAddAnUpvote();
  }

  @Test(groups = "discussions-anonUserOnDesktopCanNotVoteForFirstReply")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanNotVoteForFirstReply() {
    firstReplyUpvoteButtonClickDoenstAddAnUpvote();
  }

  @Test(groups = "discussions-anonUserOnDesktopCanNotUpvote")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanNotUpvote() {
    postListUpvoteButtonClickDoenstAddAnUpvote();
  }

  /**
   * LOGGED IN USERS ON MOBILE SECTION
   */

  @Test(groups = "discussions-loggedInUserOnMobileCanSeePostDetailsList")
  @Execute(asUser = User.USER_3)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void loggedInUserOnMobileCanSeePostDetailsList() {
    postDetailsUpvoteButtonClickAddsAnUpvoteAndSecondClickRemovesTheUpvote();
  }

  @Test(groups = "discussions-loggedInUserOnMobileCanVoteForFirstReply")
  @Execute(asUser = User.USER_3)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void loggedInUserOnMobileCanVoteForFirstReply() {
    firstReplyUpvoteButtonClickAddsAnUpvoteAndSecondClickRemovesTheUpvote();
  }

  @Test(groups = "discussions-loggedInUserOnMobileCanUpvote")
  @Execute(asUser = User.USER_3)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void loggedInUserOnMobileCanUpvote() {
    postListUpvoteButtonClickAddsAnUpvoteAndSecondClickRemovesTheUpvote();
  }

  /**
   * LOGGED IN USERS ON DESKTOP SECTION
   */

  @Test(groups = "discussions-loggedInUserOnDesktopCanSeePostDetailsList")
  @Execute(asUser = User.USER_3)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void loggedInUserOnDesktopCanSeePostDetailsList() {
    postDetailsUpvoteButtonClickAddsAnUpvoteAndSecondClickRemovesTheUpvote();
  }

  @Test(groups = "discussions-loggedInUserOnDesktopCanVoteForFirstReply")
  @Execute(asUser = User.USER_3)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void loggedInUserOnDesktopCanVoteForFirstReply() {
    firstReplyUpvoteButtonClickAddsAnUpvoteAndSecondClickRemovesTheUpvote();
  }

  @Test(groups = "discussions-loggedInUserOnDesktopCanUpvote")
  @Execute(asUser = User.USER_3)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void loggedInUserOnDesktopCanUpvote() {
    postListUpvoteButtonClickAddsAnUpvoteAndSecondClickRemovesTheUpvote();
  }

  /**
   * TESTING METHODS SECTION
   */

  public void postListUpvoteButtonClickAddsAnUpvoteAndSecondClickRemovesTheUpvote() {
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

  public void postListUpvoteButtonClickDoenstAddAnUpvote() {
    PostsListPage postList = new PostsListPage(driver).open();
    int replyIndex = 0;
    postList.isUpvoteButtonVisible(replyIndex);
    String firstVoteCount = postList.getVoteCount(replyIndex);
    postList.clickUpvoteButton(replyIndex);
    postList.waitForVoteCountChangeTimeLagToPass();
    String secondVoteCount = postList.getVoteCount(replyIndex);
    Assertion.assertEquals(firstVoteCount, secondVoteCount);
  }

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
