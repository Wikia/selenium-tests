package com.wikia.webdriver.testcases.mercurytests.old;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.helpers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.old.CommentsPageObject;

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

  @Test(groups = "MercuryCommentsTest_001")
  @RelatedIssue(issueID = "XW-654")
  public void MercuryCommentsTest_001_CommentsList_Avatar_Username_TimeStamp_Content() {
    CommentsPageObject comments = new CommentsPageObject(driver);
    comments.openMercuryArticleByName(wikiURL, MercurySubpages.COMMENTS);

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

  @Test(groups = "MercuryCommentsTest_002")
  public void MercuryCommentsTest_002_CommentsCounter_NextButton_PreviousButton() {
    CommentsPageObject comments = new CommentsPageObject(driver);
    comments.openMercuryArticleByName(wikiURL, MercurySubpages.COMMENTS);

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
      comments.waitMilliseconds(2500, "Wait after click on 'Next page' button");
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
    comments.waitMilliseconds(2500, "Wait after click on 'Previous page' button");

    result = !comments.isPreviousCommentPageButtonDisplayed();
    PageObjectLogging.log(
        "Previous page button",
        "is not displayed",
        "is displayed",
        result
    );
  }

  @Test(groups = "MercuryCommentsTest_003")
  public void MercuryCommentsTest_003_RepliesListCounter() {
    CommentsPageObject comments = new CommentsPageObject(driver);
    comments.openMercuryArticleByName(wikiURL, MercurySubpages.COMMENTS);

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

  @Test(groups = "MercuryCommentsTest_004")
  public void MercuryCommentsTest_004_TapOnUserRedirectToUserPage() {
    CommentsPageObject comments = new CommentsPageObject(driver);
    comments.openArticleOnWikiByNameWithCbAndNoAds(wikiURL, MercurySubpages.COMMENTS);

    comments.clickCommentsHeader();
    comments.waitForFirstCommentToBeVisible();
    String username = comments.getUserUsername(0);
    comments.clickOnUsername(0);
    comments.waitForWikiaMobileToBeLoaded();

    boolean result = username.equals(comments.getUsernameFromUrl());
    PageObjectLogging.log(
        "Url",
        "match pattern /wiki/User:",
        "does not match pattern /wiki/User:",
        result
    );
  }

  @Test(groups = "MercuryCommentsTest_005")
  public void MercuryCommentsTest_005_Images_Videos() {
    CommentsPageObject comments = new CommentsPageObject(driver);
    comments.openMercuryArticleByName(wikiURL, MercurySubpages.COMMENTS);

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
