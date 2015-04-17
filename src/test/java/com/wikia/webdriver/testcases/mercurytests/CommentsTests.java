package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryArticles;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.CommentsPageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * @authors: Rodrigo Gomez, Łukasz Nowak, Tomasz Napieralski
 * @ownership: Content - Mercury mobile
 */
public class CommentsTests extends NewTestTemplate {

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
  }

  private static final String MEDIA_TYPE_VIDEO = "Video";
  private static final String MEDIA_TYPE_IMAGE = "Image";
  private static final int NUMBER_OF_COMMENTS_PER_PAGE = 25;

  // CT01
  @Test(groups = {"MercuryCommentsTest_001", "MercuryCommentsTests", "Mercury"})
  public void MercuryCommentsTest_001_CommentsList_Avatar_Username_TimeStamp_Content() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_COMMENTS_TEST_ARTICLE);
    CommentsPageObject comments = new CommentsPageObject(driver);
    Assertion.assertTrue(comments.isCommentsListCollapsed(), "Comments are expanded");
    PageObjectLogging.log("Comments list", "is collapsed", true);
    comments.clickCommentsHeader();
    Assertion.assertFalse(comments.isCommentsListCollapsed(), "Comments are collapsed");
    PageObjectLogging.log("Comments list", "is expanded", true);
    PageObjectLogging.log("Number of comments per page", "is correct", "is incorrect",
                          comments.getNumberOfCommentsPerPage() == NUMBER_OF_COMMENTS_PER_PAGE);
    PageObjectLogging
        .log("User avatar", "is displayed", "is not displayed", comments.isUserAvatarInComment(0));
    PageObjectLogging.log("User username", "is displayed", "is not displayed",
                          comments.isUserUsernameInComment(0));
    PageObjectLogging
        .log("Time stamp", "is displayed", "is not displayed", comments.isTimeStampInComment(0));
    PageObjectLogging.log("Comment content", "is displayed", "is not displayed",
                          comments.isContentInComment(0));
  }

  // CT02
  @Test(groups = {"MercuryCommentsTest_002", "MercuryCommentsTests", "Mercury"})
  public void MercuryCommentsTest_002_CommentsCounter_NextButton_PreviousButton() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_COMMENTS_TEST_ARTICLE);
    CommentsPageObject comments = new CommentsPageObject(driver);
    comments.clickCommentsHeader();
    int numberOfComments = comments.getNumberOfCommentsFromHeader();
    Assertion.assertTrue((numberOfComments - comments.getNumberOfRepliesOnThatPage()) > 25,
                         "There is less than 25 on that page");
    Assertion.assertTrue(comments.isNextCommentPageButtonDisplayed(),
                         "Next page button isn't displayed");
    PageObjectLogging.log("Next page button", "is displayed", true);
    while (comments.isNextCommentPageButtonDisplayed()) {
      numberOfComments -= comments.getNumberOfAllCommentsOnPage();
      comments.clickNextCommentPageButton();
      comments.waitMilliseconds(2500, "Wait after click on 'Next page' button");
    }
    numberOfComments -= comments.getNumberOfAllCommentsOnPage();
    PageObjectLogging.log("Comments counter", "is correct", "There are "
                                                            + numberOfComments
                                                            + " untracked comments",
                          numberOfComments == 0);
    Assertion.assertTrue(comments.isPreviousCommentPageButtonDisplayed(),
                         "Previous page button isn't displayed");
    PageObjectLogging.log("Previous page button", "is displayed", true);
    comments.clickPreviousCommentPageButton();
    comments.waitMilliseconds(2500, "Wait after click on 'Previous page' button");
    PageObjectLogging.log("Previous page button", "is not displayed", "is displayed",
                          !comments.isPreviousCommentPageButtonDisplayed());
  }

  // CT03
  @Test(groups = {"MercuryCommentsTest_003", "MercuryCommentsTests", "Mercury"})
  public void MercuryCommentsTest_003_RepliesListCounter() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_COMMENTS_TEST_ARTICLE);
    CommentsPageObject comments = new CommentsPageObject(driver);
    comments.clickCommentsHeader();
    Assertion.assertFalse(comments.isRepliesListExpanded(), "Replies list is expanded");
    PageObjectLogging.log("Replies list", "is collapsed", true);
    comments.clickViewReplies(0);
    Assertion.assertTrue(comments.isRepliesListExpanded(), "Replies list is collapsed");
    PageObjectLogging.log("Replies list", "is expanded", true);
    PageObjectLogging.log("Replies counter", "is correct", "is incorrect",
                          comments.getNumberOfRepliesFromHeader(0) == comments
                              .getNumberOfRepliesFromList(0));
  }

  // CT04
  @Test(groups = {"MercuryCommentsTest_004", "MercuryCommentsTests", "Mercury"})
  public void MercuryCommentsTest_004_TapOnUserRedirectToUserPage() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_COMMENTS_TEST_ARTICLE);
    CommentsPageObject comments = new CommentsPageObject(driver);
    comments.clickCommentsHeader();
    String username = comments.getUserUsername(0);
    comments.clickOnUsername(0);
    PageObjectLogging.log("Url", "match pattern /wiki/User:", "does not match pattern /wiki/User:",
                          username.equals(comments.getUsernameFromUrl()));
  }

  // CT05
  @Test(groups = {"MercuryCommentsTest_005", "MercuryCommentsTests", "Mercury"})
  public void MercuryCommentsTest_005_Images_Videos() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_COMMENTS_TEST_ARTICLE);
    CommentsPageObject comments = new CommentsPageObject(driver);
    comments.clickCommentsHeader();
    PageObjectLogging.log("Video thumbnail", "is displayed", "is not displayed",
                          comments.isMediaThumbnailInComment(
                              MEDIA_TYPE_VIDEO, 1));
    PageObjectLogging.log("Video link", "is present", "is not present",
                          comments.isMediaLinkInComment(MEDIA_TYPE_VIDEO, 1));
    PageObjectLogging.log("Image thumbnail", "is displayed", "is not displayed",
                          comments.isMediaThumbnailInComment(MEDIA_TYPE_IMAGE, 3));
    PageObjectLogging.log("Image link", "is present", "is not present",
                          comments.isMediaLinkInComment(MEDIA_TYPE_IMAGE, 3));
  }
}
