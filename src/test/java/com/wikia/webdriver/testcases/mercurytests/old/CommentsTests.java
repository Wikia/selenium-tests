package com.wikia.webdriver.testcases.mercurytests.old;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.core.helpers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.old.CommentsPageObject;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
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

  private static final By WIKIA_MOBILE_WIKI_TITLE = By.cssSelector("#wkWrdMrk");

  private CommentsPageObject comments;

  private void init() {
    this.comments = new CommentsPageObject(driver);

    new Navigate(driver).toPage(MercurySubpages.COMMENTS);
  }

  @Test(groups = "comments-containsAvatarAndUsernameAndTimestampAndContent")
  @RelatedIssue(issueID = "XW-654")
  public void containsAvatarAndUsernameAndTimestampAndContent() {
    init();

    Assertion.assertTrue(
        comments.isCommentsListCollapsed(),
        "Comments are expanded"
    );

    PageObjectLogging.log(
        "Comments list",
        "is collapsed",
        true
    );

    comments.clickCommentsHeader();
    comments.waitForFirstCommentToBeVisible();

    Assertion.assertFalse(
        comments.isCommentsListCollapsed(),
        "Comments are collapsed"
    );

    PageObjectLogging.log(
        "Comments list",
        "is expanded",
        true
    );

    boolean result = comments.getNumberOfCommentsPerPage() == NUMBER_OF_COMMENTS_PER_PAGE;
    PageObjectLogging.log(
        "Number of comments per page",
        "is correct",
        "is incorrect",
        result
    );

    result = comments.isUserAvatarInComment(0);
    PageObjectLogging.log(
        "User avatar",
        "is displayed",
        "is not displayed",
        result
    );

    result = comments.isUserUsernameInComment(0);
    PageObjectLogging.log(
        "User username",
        "is displayed",
        "is not displayed",
        result
    );

    result = comments.isTimeStampInComment(0);
    PageObjectLogging.log(
        "Time stamp",
        "is displayed",
        "is not displayed",
        result
    );

    result = comments.isContentInComment(0);
    PageObjectLogging.log(
        "Comment content",
        "is displayed",
        "is not displayed",
        result
    );
  }

  @Test(groups = "comments-containsCounterAndNextButtonAndPreviousButton")
  public void containsCounterAndNextButtonAndPreviousButton() {
    init();

    comments.clickCommentsHeader();
    comments.waitForFirstCommentToBeVisible();
    int numberOfComments = comments.getNumberOfCommentsFromHeader();

    Assertion.assertTrue(
        (numberOfComments - comments.getNumberOfRepliesOnThatPage()) > 25,
        "There is less than 25 on that page"
    );

    Assertion.assertTrue(
        comments.isNextCommentPageButtonDisplayed(),
        "Next page button isn't displayed"
    );

    PageObjectLogging.log(
        "Next page button",
        "is displayed",
        true
    );

    while (comments.isNextCommentPageButtonDisplayed()) {
      numberOfComments -= comments.getNumberOfAllCommentsOnPage();
      comments.clickNextCommentPageButton();
      new Wait(driver).forXMilliseconds(2500);
    }

    numberOfComments -= comments.getNumberOfAllCommentsOnPage();

    boolean result = numberOfComments == 0;
    PageObjectLogging.log(
        "Comments counter",
        "is correct",
        "There are " + numberOfComments + " untracked comments",
        result
    );

    Assertion.assertTrue(
        comments.isPreviousCommentPageButtonDisplayed(),
        "Previous page button isn't displayed"
    );

    PageObjectLogging.log(
        "Previous page button",
        "is displayed",
        true
    );

    comments.clickPreviousCommentPageButton();
    new Wait(driver).forXMilliseconds(2500);

    result = !comments.isPreviousCommentPageButtonDisplayed();
    PageObjectLogging.log(
        "Previous page button",
        "is not displayed",
        "is displayed",
        result
    );
  }

  @Test(groups = "comments-repliesCounterShowsTheSameNumberInHeaderAndList")
  public void repliesCounterShowsTheSameNumberInHeaderAndList() {
    init();

    comments.clickCommentsHeader();
    comments.waitForFirstCommentToBeVisible();

    Assertion.assertFalse(
        comments.isRepliesListExpanded(),
        "Replies list is expanded"
    );

    PageObjectLogging.log(
        "Replies list",
        "is collapsed",
        true
    );

    comments.clickViewReplies(0);

    Assertion.assertTrue(
        comments.isRepliesListExpanded(),
        "Replies list is collapsed"
    );

    PageObjectLogging.log(
        "Replies list",
        "is expanded",
        true
    );

    boolean result =
        comments.getNumberOfRepliesFromHeader(0) == comments.getNumberOfRepliesFromList(0);
    PageObjectLogging.log(
        "Replies counter",
        "is correct",
        "is incorrect",
        result
    );
  }

  @Test(groups = "comments-tapOnUserRedirectToUserPage")
  public void tapOnUserRedirectToUserPage() {
    init();

    comments.clickCommentsHeader();
    comments.waitForFirstCommentToBeVisible();
    String username = comments.getUserUsername(0);
    comments.clickOnUsername(0);
    new Wait(driver).forElementVisible(WIKIA_MOBILE_WIKI_TITLE);

    boolean result = username.equals(comments.getUsernameFromUrl());
    PageObjectLogging.log(
        "Url",
        "match pattern /wiki/User:",
        "does not match pattern /wiki/User:",
        result
    );
  }

  @Test(groups = "comments-openCommentAndVerifyVideoAndVideoLinkAndImageAndImageLink")
  public void openCommentAndVerifyVideoAndVideoLinkAndImageAndImageLink
      () {
    init();

    comments.clickCommentsHeader();
    comments.waitForFirstCommentToBeVisible();

    boolean result =
        comments.isMediaThumbnailInComment(MEDIA_TYPE_VIDEO, COMMENT_NUMBER_WITH_VIDEO);
    PageObjectLogging.log(
        "Video thumbnail",
        "is displayed",
        "is not displayed",
        result
    );

    result = comments.isMediaLinkInComment(MEDIA_TYPE_VIDEO, COMMENT_NUMBER_WITH_VIDEO);
    PageObjectLogging.log(
        "Video link",
        "is present",
        "is not present",
        result
    );

    result = comments.isMediaThumbnailInComment(MEDIA_TYPE_IMAGE, COMMENT_NUMBER_WITH_IMAGE);
    PageObjectLogging.log(
        "Image thumbnail",
        "is displayed",
        "is not displayed",
        result
    );

    result = comments.isMediaLinkInComment(MEDIA_TYPE_IMAGE, COMMENT_NUMBER_WITH_IMAGE);
    PageObjectLogging.log(
        "Image link",
        "is present",
        "is not present",
        result
    );
  }
}
