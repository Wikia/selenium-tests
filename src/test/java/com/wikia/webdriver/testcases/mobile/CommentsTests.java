package com.wikia.webdriver.testcases.mobile;

import com.wikia.webdriver.common.contentpatterns.MobileSubpages;
import com.wikia.webdriver.common.contentpatterns.MobileWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.communities.mobile.pages.CommentsPageObject;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

/*
 * Tests for wikis article's comment for mobile view
 */

@Test(groups = "Mercury_Comments")
@Execute(onWikia = MobileWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class CommentsTests extends NewTestTemplate {

  private static final String MEDIA_TYPE_VIDEO = "Video";
  private static final String MEDIA_TYPE_IMAGE = "Image";
  private static final int NUMBER_OF_COMMENTS_PER_PAGE = 25;
  private static final int COMMENT_NUMBER_WITH_VIDEO = 0;
  private static final int COMMENT_NUMBER_WITH_IMAGE = 1;

  private static final By OASIS_BODY = By.cssSelector("body.skin-desktop");

  private CommentsPageObject comments;

  @Test(groups = "mercury_comments_containsAvatarUsernameTimestampAndContent")
  public void mercury_comments_containsAvatarUsernameTimestampAndContent() {
    this.comments = new CommentsPageObject(driver);
    new Navigate().toPage(MobileSubpages.COMMENTS);

    Assertion.assertTrue(comments.isCommentsListCollapsed(), "Comments are expanded");

    Log.log("Comments list", "is collapsed", true);

    comments.clickCommentsHeader();
    comments.waitForCommentsToLoad();

    Assertion.assertFalse(comments.isCommentsListCollapsed(), "Comments are collapsed");

    Log.log("Comments list", "is expanded", true);

    boolean result = comments.getNumberOfCommentsPerPage() == NUMBER_OF_COMMENTS_PER_PAGE;
    Log.log("Number of comments per page", "is correct", "is incorrect",
            result);

    result = comments.isUserAvatarInComment(0);
    Log.log("User avatar", "is displayed","is not displayed", result);

    result = comments.isUserUsernameInComment(0);
    Log.log("User username","is displayed","is not displayed", result);

    result = comments.isTimeStampInComment(0);
    Log.log("Time stamp", "is displayed", "is not displayed", result);

    result = comments.isContentInComment(0);
    Log.log("Comment content","is displayed","is not displayed", result);
  }

  @Test(groups = "mercury_comments_containsCounterNextButtonAndPreviousButton", enabled = false)
  @RelatedIssue(issueID = "DAT-4316")
  public void mercury_comments_containsCounterNextButtonAndPreviousButton() {
    this.comments = new CommentsPageObject(driver);
    new Navigate().toPage(MobileSubpages.COMMENTS);

    comments.clickCommentsHeader();
    comments.waitForCommentsToLoad();
    int numberOfComments = comments.getNumberOfCommentsFromHeader();

    Assertion.assertTrue((numberOfComments - comments.getNumberOfRepliesOnThatPage()) > 25,
        "There is less than 25 on that page");

    Assertion.assertTrue(comments.isNextCommentPageButtonDisplayed(), "Next page button isn't displayed");

    Log.log("Next page button","is displayed",true);

    while (comments.isNextCommentPageButtonDisplayed()) {
      numberOfComments -= comments.getNumberOfAllCommentsOnPage();
      comments.clickNextCommentPageButton();
      new Wait(driver).forXMilliseconds(2500);
    }

    numberOfComments -= comments.getNumberOfAllCommentsOnPage();

    boolean result = numberOfComments == 0;
    Log.log("Comments counter", "is correct",
        "There are " + numberOfComments + " untracked comments", result);

    Assertion.assertTrue(comments.isPreviousCommentPageButtonDisplayed(),
        "Previous page button isn't displayed");

    Log.log("Previous page button", "is displayed", true);

    comments.clickPreviousCommentPageButton();
    new Wait(driver).forXMilliseconds(2500);

    result = !comments.isPreviousCommentPageButtonDisplayed();
    Log.log("Previous page button", "is not displayed","is displayed",
            result);
  }

  @Test(groups = "mercury_comments_repliesCounterCountsCorrect")
  public void mercury_comments_repliesCounterCountsCorrect() {
    this.comments = new CommentsPageObject(driver);
    new Navigate().toPage(MobileSubpages.COMMENTS);

    comments.clickCommentsHeader();
    comments.waitForCommentsToLoad();

    Assertion.assertFalse(comments.isRepliesListExpanded(), "Replies list is expanded");

    Log.log("Replies list", "is collapsed", true);

    int counter = comments.getNumberOfRepliesFromHeader(0);
    int repliesCount = comments.getNumberOfRepliesFromList(0);
    boolean result = counter == repliesCount;
    Log.log("Replies counter", "is correct", "is incorrect", result);

    comments.clickViewReplies(0);

    Assertion.assertTrue(comments.isRepliesListExpanded(), "Replies list is collapsed");

    Log.log("Replies list", "is expanded", true);
  }

  @Test(groups = "mercury_comments_tapOnUsernameRedirectsToUserPage", enabled = false)
  @RelatedIssue(issueID = "XW-5188")
  public void mercury_comments_tapOnUsernameRedirectsToUserPage() {
    this.comments = new CommentsPageObject(driver);
    new Navigate().toPage(MobileSubpages.COMMENTS);

    comments.clickCommentsHeader();
    comments.waitForCommentsToLoad();
    String username = comments.getUserUsername(0);
    comments.clickOnUsername(0);
    new Wait(driver).forElementVisible(OASIS_BODY);

    boolean result = username.equals(comments.getUsernameFromUrl());
    Log.log("Url", "match pattern /wiki/User:",
            "does not match pattern /wiki/User:", result);
  }

  @Test(groups = "mercury_comments_imagesAndVideosAreDisplayedCorrectly")
  public void mercury_comments_imagesAndVideosAreDisplayedCorrectly() {
    this.comments = new CommentsPageObject(driver);
    new Navigate().toPage(MobileSubpages.COMMENTS);

    comments.clickCommentsHeader();
    comments.waitForCommentsToLoad();

    boolean result = comments.isMediaThumbnailInComment(MEDIA_TYPE_VIDEO, COMMENT_NUMBER_WITH_VIDEO);
    Log.log("Video thumbnail", "is displayed", "is not displayed", result);

    result = comments.isMediaLinkInComment(MEDIA_TYPE_VIDEO, COMMENT_NUMBER_WITH_VIDEO);
    Log.log("Video link", "is present", "is not present", result);

    result = comments.isMediaThumbnailInComment(MEDIA_TYPE_IMAGE, COMMENT_NUMBER_WITH_IMAGE);
    Log.log("Image thumbnail", "is displayed", "is not displayed", result);

    result = comments.isMediaLinkInComment(MEDIA_TYPE_IMAGE, COMMENT_NUMBER_WITH_IMAGE);
    Log.log("Image link", "is present", "is not present", result);
  }
}
