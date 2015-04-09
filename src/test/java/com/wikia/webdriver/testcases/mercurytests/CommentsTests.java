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
  public void optInMercury() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
    BasePageObject.turnOnMercurySkin(driver, wikiURL);
  }

  private boolean failTest = false;

  private static final String MEDIA_TYPE_VIDEO = "Video";
  private static final String MEDIA_TYPE_IMAGE = "Image";
  private static final int NUMBER_OF_COMMENTS_PER_PAGE = 25;

  // CT01
  @Test(groups = {"MercuryCommentsTest_001", "MercuryCommentsTests", "Mercury"})
  public void MercuryCommentsTest_001_CommentsListAvatarUsernameTimeStampContent() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_COMMENTS_TEST_ARTICLE);
    CommentsPageObject comments = new CommentsPageObject(driver);
    Assertion.assertTrue(comments.isCommmentsListCollapsed(), "Comments are expanded");
    PageObjectLogging.log("Comments list", "is collapsed", true);
    comments.clickCommentsHeader();
    Assertion.assertFalse(comments.isCommmentsListCollapsed(), "Comments are collapsed");
    PageObjectLogging.log("Comments list", "is expanded", true);
    if (comments.getNumberOfCommentsPerPage() == NUMBER_OF_COMMENTS_PER_PAGE) {
      PageObjectLogging.log("Number of comments per page", "is correct", true);
    } else {
      PageObjectLogging.log("Number of comments per page", "is incorrect", false);
      failTest = true;
    }
    if (comments.isUserAvatarInComment(0)) {
      PageObjectLogging.log("User avatar", "is displayed", true);
    } else {
      PageObjectLogging.log("User avatar", "is not displayed", false);
      failTest = true;
    }
    if (comments.isUserUsernameInComment(0)) {
      PageObjectLogging.log("User username", "is displayed", true);
    } else {
      PageObjectLogging.log("User username", "is not displayed", false);
      failTest = true;
    }
    if (comments.isTimeStampInComment(0)) {
      PageObjectLogging.log("Time stamp", "is displayed", true);
    } else {
      PageObjectLogging.log("Time stamp", "is not displayed", false);
      failTest = true;
    }
    if (comments.isContentInComment(0)) {
      PageObjectLogging.log("Comment content", "is displayed", true);
    } else {
      PageObjectLogging.log("Comment content", "is not displayed", false);
      failTest = true;
    }
    base.failTest(failTest);
  }

  // CT02
  @Test(groups = {"MercuryCommentsTest_002", "MercuryCommentsTests", "Mercury"})
  public void MercuryCommentsTest_002_CommentsCounterNextPreviousButton() {
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
    if (numberOfComments == 0) {
      PageObjectLogging.log("Comments counter", "is correct", true);
    } else {
      PageObjectLogging.log("Comments counter", "There are "
                                                + numberOfComments + " untracked comments", false);
      failTest = true;
    }
    Assertion.assertTrue(comments.isPreviousCommentPageButtonDisplayed(),
                         "Previous page button isn't displayed");
    PageObjectLogging.log("Previous page button", "is displayed", true);
    comments.clickPreviousCommentPageButton();
    comments.waitMilliseconds(2500, "Wait after click on 'Previous page' button");
    if (comments.isPreviousCommentPageButtonDisplayed()) {
      PageObjectLogging.log("Previous page button", "is displayed", false);
      failTest = true;
    } else {
      PageObjectLogging.log("Previous page button", "is not displayed", true);
    }
    base.failTest(failTest);
  }

  // CT03
  @Test(groups = {"MercuryCommentsTest_003", "MercuryCommentsTests", "Mercury"})
  public void MercuryCommentsTest_003_RepliesListCounter() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_COMMENTS_TEST_ARTICLE);
    CommentsPageObject comments = new CommentsPageObject(driver);
    comments.clickCommentsHeader();
    Assertion.assertFalse(comments.isRepliesListExpanded(0), "Replies list is expanded");
    PageObjectLogging.log("Replies list", "is collapsed", true);
    comments.clickViewReplies(0);
    Assertion.assertTrue(comments.isRepliesListExpanded(0), "Replies list is collapsed");
    PageObjectLogging.log("Replies list", "is expanded", true);
    if (comments.getNumberOfRepliesFromHeader(0) == comments.getNumberOfRepliesFromList(0)) {
      PageObjectLogging.log("Replies counter", "is correct", true);
    } else {
      PageObjectLogging.log("Replies counter", "is incorrect", false);
      failTest = true;
    }
    base.failTest(failTest);
  }

  // CT04
  @Test(groups = {"MercuryCommentsTest_004", "MercuryCommentsTests", "Mercury"})
  public void MercuryCommentsTest_004_TapOnUserRedirect() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_COMMENTS_TEST_ARTICLE);
    CommentsPageObject comments = new CommentsPageObject(driver);
    comments.clickCommentsHeader();
    String username = comments.getUserUsername(0);
    comments.clickOnUsername(0);
    if (username.equals(comments.getUsernameFromUrl())) {
      PageObjectLogging.log("Url", "match pattern /wiki/User:", true);
    } else {
      PageObjectLogging.log("Url", "does not match pattern /wiki/User:", false);
      failTest = true;
    }
    base.failTest(failTest);
  }

  // CT05
  @Test(groups = {"MercuryCommentsTest_005", "MercuryCommentsTests", "Mercury"})
  public void MercuryCommentsTest_005_ImagesAndVideosAreDisplayed() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_COMMENTS_TEST_ARTICLE);
    CommentsPageObject comments = new CommentsPageObject(driver);
    comments.clickCommentsHeader();
    if (comments.isMediaThumbnailInComment(MEDIA_TYPE_VIDEO, 1)) {
      PageObjectLogging.log("Video thumbnail", "is displayed", true);
    } else {
      PageObjectLogging.log("Video thumbnail", "is not displayed", false);
      failTest = true;
    }
    if (comments.isMediaLinkInComment(MEDIA_TYPE_VIDEO, 1)) {
      PageObjectLogging.log("Video link", "is present", true);
    } else {
      PageObjectLogging.log("Video link", "is not present", false);
      failTest = true;
    }
    if (comments.isMediaThumbnailInComment(MEDIA_TYPE_IMAGE, 3)) {
      PageObjectLogging.log("Image thumbnail", "is displayed", true);
    } else {
      PageObjectLogging.log("Image thumbnail", "is not displayed", false);
      failTest = true;
    }
    if (comments.isMediaLinkInComment(MEDIA_TYPE_IMAGE, 3)) {
      PageObjectLogging.log("Image link", "is present", true);
    } else {
      PageObjectLogging.log("Image link", "is not present", false);
      failTest = true;
    }
    base.failTest(failTest);
  }
}
