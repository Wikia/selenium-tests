package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
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
    MercuryArticlePageObject article =
        base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_COMMENTS_TEST_ARTICLE);
    article.clickCommentsHeader();
    article.verifyCommentsAreUncollapsed();
  }

  // CT02
  @Test(groups = {"MercuryCommentsTests_002", "MercuryCommentsTests", "Mercury"})
  public void MercuryCommentsTests_002_25CommentsPerPage() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    MercuryArticlePageObject article =
        base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_COMMENTS_TEST_ARTICLE);
    article.clickCommentsHeader();
    article.verifyCommentsPerPage(25);
  }
  
  // CT03
  @Test(groups = {"MercuryCommentsTests_003", "MercuryCommentsTests", "Mercury"})
  public void MercuryCommentsTests_003_NextAndPreviousPageAreVisible() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    MercuryArticlePageObject article =
        base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_COMMENTS_TEST_ARTICLE);
    article.clickCommentsHeader();
    article.verifyNextAndPreviousPageAreVisible();
  }
  
  // CT04
  @Test(groups = {"MercuryCommentsTest_004", "MercuryCommentsTests", "Mercury"})
  public void MercuryCommentsTests_004_ClickViewReplyWillExpandReplies() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    MercuryArticlePageObject article =
        base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_COMMENTS_TEST_ARTICLE);
    article.clickCommentsHeader();
    article.clickViewReplies();
    article.verifyRepliesAreExpanded();
  }

  // CT05
  @Test(groups = {"MercuryCommentsTest_005", "MercuryCommentsTests", "Mercury"})
  public void MercuryCommentsTests_005_CheckCommentElements() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    MercuryArticlePageObject article =
        base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_COMMENTS_TEST_ARTICLE);
    article.clickCommentsHeader();
    article.verifyCommentsElements();
  }
  
  // CT06
  @Test(groups = {"MercuryCommentsTest_006", "MercuryCommentsTests", "Mercury"})
  public void MercuryCommentsTests_006_CommentsCounterIsCorrect() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    MercuryArticlePageObject article =
        base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_COMMENTS_TEST_ARTICLE);
    article.clickCommentsHeader();
    article.verifyCommentsCounterIsCorrect();
  }
  
  // CT07
  @Test(groups = {"MercuryCommentsTests_007", "MercuryCommentsTests", "Mercury"})
  public void MercuryCommentsTests_007_RepliesCounterIsCorrect() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    MercuryArticlePageObject article =
        base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_COMMENTS_TEST_ARTICLE);
    article.clickCommentsHeader();
    article.verifyRepliesCounterIsCorrect(1);
  }
  
  // CT08
  @Test(groups = {"MercuryCommentsTests_008", "MercuryCommentsTests", "Mercury"})
  public void MercuryCommentsTests_008_TapOnUserRedirectToUserPage() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    MercuryArticlePageObject article =
        base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_COMMENTS_TEST_ARTICLE);
    article.clickCommentsHeader();
    article.verifyTapOnUserRedirectToUserPage(0);
  }
  
  // CT09
  @Test(groups = {"MercuryCommentsTests_009", "MercuryCommentsTests", "Mercury"})
  public void MercuryCommentsTests_009_ImagesAndVideosAreDisplayed() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    MercuryArticlePageObject article =
        base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_COMMENTS_TEST_ARTICLE);
    article.clickCommentsHeader();
    article.verifyMediaInComments(MercuryArticlePageObject.MEDIA_TYPE_VIDEO, 1);
    article.verifyMediaInComments(MercuryArticlePageObject.MEDIA_TYPE_IMAGE, 3);
  }
  
  // CT10
  @Test(groups = {"MercuryCommentsTests_010", "MercuryCommentsTests", "Mercury"})
  public void MercuryCommentsTests_010_ChevronRotatesWhenTapped() {
    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    MercuryArticlePageObject article =
        base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_COMMENTS_TEST_ARTICLE);
    article.verifyChevronRotatesWhenTapped();
  }
}
