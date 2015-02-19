package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.CommentsPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @authors: Rodrigo Gomez, Łukasz Nowak, Tomasz Napieralski
 * @ownership: Mobile Web
 */

public class CommentsTests extends NewTestTemplate {

  Credentials credentials = config.getCredentials();

  @BeforeMethod(alwaysRun = true)
  public void optInMercury() {
    MercuryContent.turnOnMercurySkin(driver, wikiURL);
  }

  // CT01
  @Test(groups = {"MercuryCommentsTests_001", "MercuryCommentsTests", "Mercury"})
  public void MercuryCommentsTests_001_ClickingCommentsWillUncollapseComments() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_COMMENTS_TEST_ARTICLE);
    CommentsPageObject comments = new CommentsPageObject(driver);
    comments.clickCommentsHeader();
    Assertion.assertFalse(comments.isCommmentsListCollapsed(), "Comments are collapsed");
  }

  // CT02
  @Test(groups = {"MercuryCommentsTests_002", "MercuryCommentsTests", "Mercury"})
  public void MercuryCommentsTests_002_25CommentsPerPage() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_COMMENTS_TEST_ARTICLE);
    CommentsPageObject comments = new CommentsPageObject(driver);
    comments.clickCommentsHeader();
    Assertion.assertNumber(25, comments.getNumberOfCommentsPerPage(), "Number of comments per page");
  }
  
  // CT03
  @Test(groups = {"MercuryCommentsTests_003", "MercuryCommentsTests", "Mercury"})
  public void MercuryCommentsTests_003_NextAndPreviousPageAreVisible() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_COMMENTS_TEST_ARTICLE);
    CommentsPageObject comments = new CommentsPageObject(driver);
    comments.clickCommentsHeader();
    int numberOfComments = comments.getNumberOfCommentsFromHeader();
    Assertion.assertTrue((numberOfComments - comments.getNumberOfRepliesOnThatPage()) > 25, "There is less than 25 on that page");
    Assertion.assertTrue(comments.isNextCommentPageButtonDisplayed(), "Next page button isn't displayed");
    comments.clickNextCommentPageButton();
    Assertion.assertTrue(comments.isPreviousCommentPageButtonDisplayed(),
                         "Previous page button isn't displayed");
    comments.clickPreviousCommentPageButton();
    Assertion.assertFalse(comments.isPreviousCommentPageButtonDisplayed(),
                          "Previous page button is displayed");
  }
  
  // CT04
  @Test(groups = {"MercuryCommentsTest_004", "MercuryCommentsTests", "Mercury"})
  public void MercuryCommentsTests_004_ClickViewReplyWillExpandReplies() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_COMMENTS_TEST_ARTICLE);
    CommentsPageObject comments = new CommentsPageObject(driver);
    comments.clickCommentsHeader();
    comments.clickViewReplies(0);
    Assertion.assertTrue(comments.isRepliesListExpanded(0), "Replies list is collapsed");
  }

  // CT05
  @Test(groups = {"MercuryCommentsTest_005", "MercuryCommentsTests", "Mercury"})
  public void MercuryCommentsTests_005_CheckCommentElements() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_COMMENTS_TEST_ARTICLE);
    CommentsPageObject comments = new CommentsPageObject(driver);
    comments.clickCommentsHeader();
    Assertion.assertTrue(comments.isUserAvatarInComment(0), "User avatar isn't displayed");
    Assertion.assertTrue(comments.isUserUsernameInComment(0), "User username isn't displayed");
    Assertion.assertTrue(comments.isTimeStampInComment(0), "Time stamp isn't displayed");
    Assertion.assertTrue(comments.isContentInComment(0), "Content isn't displayed");
  }
  
  // CT06
  @Test(groups = {"MercuryCommentsTest_006", "MercuryCommentsTests", "Mercury"})
  public void MercuryCommentsTests_006_CommentsCounterIsCorrect() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_COMMENTS_TEST_ARTICLE);
    CommentsPageObject comments = new CommentsPageObject(driver);
    comments.clickCommentsHeader();
    int numberOfComments = comments.getNumberOfCommentsFromHeader();
    while (comments.isNextCommentPageButtonDisplayed()) {
      numberOfComments -= comments.getNumberOfAllCommentsOnPage();
      comments.clickNextCommentPageButton();
    }
    numberOfComments -= comments.getNumberOfAllCommentsOnPage();
    Assertion.assertTrue(numberOfComments == 0, "There are "
                                                + numberOfComments + " untracked comments");
  }
  
  // CT07
  @Test(groups = {"MercuryCommentsTests_007", "MercuryCommentsTests", "Mercury"})
  public void MercuryCommentsTests_007_RepliesCounterIsCorrect() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_COMMENTS_TEST_ARTICLE);
    CommentsPageObject comments = new CommentsPageObject(driver);
    comments.clickCommentsHeader();
    Assertion.assertTrue(comments.getNumberOfRepliesFromHeader(0) == comments.getNumberOfRepliesFromList(0), "Replies counter doesn't work");
  }
  
  // CT08
  @Test(groups = {"MercuryCommentsTests_008", "MercuryCommentsTests", "Mercury"})
  public void MercuryCommentsTests_008_TapOnUserRedirectToUserPage() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_COMMENTS_TEST_ARTICLE);
    CommentsPageObject comments = new CommentsPageObject(driver);
    comments.clickCommentsHeader();
    String username = comments.getUserUsername(0);
    comments.clickOnUsername(0);
    Assertion.assertTrue(username.equals(comments.getUsernameFromUrl()), "Username in url is wrong");
  }
  
  // CT09
  @Test(groups = {"MercuryCommentsTests_009", "MercuryCommentsTests", "Mercury"})
  public void MercuryCommentsTests_009_ImagesAndVideosAreDisplayed() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_COMMENTS_TEST_ARTICLE);
    CommentsPageObject comments = new CommentsPageObject(driver);
    comments.clickCommentsHeader();
    Assertion.assertTrue(
        comments.isMediaThumbnailInComment(MercuryArticlePageObject.MEDIA_TYPE_VIDEO, 1),
        "Video thumbnail isn't displayed");
    Assertion.assertTrue(comments.isMediaLinkInComment(MercuryArticlePageObject.MEDIA_TYPE_VIDEO, 1), "Video doesn't have link");
    Assertion.assertTrue(comments.isMediaThumbnailInComment(
        MercuryArticlePageObject.MEDIA_TYPE_IMAGE, 3), "Image thumbnail isn't displayed");
    Assertion.assertTrue(comments.isMediaLinkInComment(MercuryArticlePageObject.MEDIA_TYPE_IMAGE, 3), "Image doesn't have link");
  }
  
  // CT10
  @Test(groups = {"MercuryCommentsTests_010", "MercuryCommentsTests", "Mercury"})
  public void MercuryCommentsTests_010_ChevronRotatesWhenTapped() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_COMMENTS_TEST_ARTICLE);
    CommentsPageObject comments = new CommentsPageObject(driver);
    Assertion.assertTrue(comments.isChevronCollapsed(), "Chevron isn't collapsed");
    comments.clickCommentsHeader();
    Assertion.assertFalse(comments.isChevronCollapsed(), "Chevron is collapsed");
  }
}
