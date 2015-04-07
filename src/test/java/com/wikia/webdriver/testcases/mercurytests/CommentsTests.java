package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryArticles;
import com.wikia.webdriver.common.core.Assertion;
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
    comments.clickCommentsHeader();
    Assertion.assertFalse(comments.isCommmentsListCollapsed(), "Comments are collapsed");
    Assertion.assertNumber(NUMBER_OF_COMMENTS_PER_PAGE, comments.getNumberOfCommentsPerPage(),
                           "Number of comments per page");
    Assertion.assertTrue(comments.isUserAvatarInComment(0), "User avatar isn't displayed");
    Assertion.assertTrue(comments.isUserUsernameInComment(0), "User username isn't displayed");
    Assertion.assertTrue(comments.isTimeStampInComment(0), "Time stamp isn't displayed");
    Assertion.assertTrue(comments.isContentInComment(0), "Content isn't displayed");
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
    while (comments.isNextCommentPageButtonDisplayed()) {
      numberOfComments -= comments.getNumberOfAllCommentsOnPage();
      comments.clickNextCommentPageButton();
      comments.waitMilliseconds(2500, "Wait after click on 'Next page' button");
    }
    numberOfComments -= comments.getNumberOfAllCommentsOnPage();
    Assertion.assertTrue(numberOfComments == 0, "There are "
                                                + numberOfComments + " untracked comments");
    Assertion.assertTrue(comments.isPreviousCommentPageButtonDisplayed(),
                         "Previous page button isn't displayed");
    comments.clickPreviousCommentPageButton();
    comments.waitMilliseconds(2500, "Wait after click on 'Previous page' button");
    Assertion.assertFalse(comments.isPreviousCommentPageButtonDisplayed(),
                          "Previous page button is displayed");
  }

  // CT03
  @Test(groups = {"MercuryCommentsTest_003", "MercuryCommentsTests", "Mercury"})
  public void MercuryCommentsTest_003_RepliesListCounter() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_COMMENTS_TEST_ARTICLE);
    CommentsPageObject comments = new CommentsPageObject(driver);
    comments.clickCommentsHeader();
    comments.clickViewReplies(0);
    Assertion.assertTrue(comments.isRepliesListExpanded(0), "Replies list is collapsed");
    Assertion.assertTrue(
        comments.getNumberOfRepliesFromHeader(0) == comments.getNumberOfRepliesFromList(0),
        "Replies counter doesn't work");
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
    Assertion
        .assertTrue(username.equals(comments.getUsernameFromUrl()), "Username in url is wrong");
  }

  // CT05
  @Test(groups = {"MercuryCommentsTest_005", "MercuryCommentsTests", "Mercury"})
  public void MercuryCommentsTest_005_ImagesAndVideosAreDisplayed() {
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryArticles.MERCURY_COMMENTS_TEST_ARTICLE);
    CommentsPageObject comments = new CommentsPageObject(driver);
    comments.clickCommentsHeader();
    Assertion.assertTrue(
        comments.isMediaThumbnailInComment(MEDIA_TYPE_VIDEO, 1),
        "Video thumbnail isn't displayed");
    Assertion
        .assertTrue(comments.isMediaLinkInComment(MEDIA_TYPE_VIDEO, 1), "Video doesn't have link");
    Assertion.assertTrue(comments.isMediaThumbnailInComment(MEDIA_TYPE_IMAGE, 3),
                         "Image thumbnail isn't displayed");
    Assertion
        .assertTrue(comments.isMediaLinkInComment(MEDIA_TYPE_IMAGE, 3), "Image doesn't have link");
  }
}
