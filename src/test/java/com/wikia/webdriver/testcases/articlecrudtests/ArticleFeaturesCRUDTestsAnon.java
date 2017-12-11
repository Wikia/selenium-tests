package com.wikia.webdriver.testcases.articlecrudtests;

import static com.wikia.webdriver.common.core.Assertion.assertTrue;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.api.ForumBoardContent;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.oasis.components.comment.ArticleComment;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.DetachedRegisterPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.messagewall.MessageWall;
import com.wikia.webdriver.testcases.discussions.DiscussionsCreation;
import com.wikia.webdriver.testcases.mobilewikitests.CommentsTests;

import org.testng.annotations.Test;

public class ArticleFeaturesCRUDTestsAnon extends NewTestTemplate {

  private static final String articleName = "ArticleFeaturesCRUDAnon";

  @Test(groups = {"ArticleFeatureCRUDAnonymous_001", "ArticleFeaturesCRUDAnon"})
  public void ArticleCRUDAnonymous_001_AddingImage() {
    ArticlePageObject article = new ArticlePageObject().open(articleName);
    VisualEditModePageObject visualEditMode = article.navigateToArticleEditPage();
    visualEditMode.clickPhotoButton();

    DetachedRegisterPage auth = new DetachedRegisterPage();
    assertTrue(auth.isDisplayed());
  }

  @Test(groups = {"ArticleFeatureCRUDAnonymous_002", "ArticleFeaturesCRUDAnon"})
  public void ArticleCRUDAnonymous_002_AddingGallery() {
    ArticlePageObject article = new ArticlePageObject().open(articleName);
    VisualEditModePageObject visualEditMode = article.navigateToArticleEditPage();
    visualEditMode.clickGalleryButton();

    DetachedRegisterPage auth = new DetachedRegisterPage();
    assertTrue(auth.isDisplayed());
  }

  @Test(groups = {"ArticleFeatureCRUDAnonymous_003", "ArticleFeaturesCRUDAnon"})
  public void ArticleCRUDAnonymous_003_AddingSlideshow() {
    ArticlePageObject article = new ArticlePageObject().open(articleName);
    VisualEditModePageObject visualEditMode = article.navigateToArticleEditPage();
    visualEditMode.clickSlideshowButton();

    DetachedRegisterPage auth = new DetachedRegisterPage();
    assertTrue(auth.isDisplayed());
  }

  @Test(groups = {"ArticleFeatureCRUDAnonymous_004", "ArticleFeaturesCRUDAnon"})
  public void ArticleCRUDAnonymous_004_AddingSlider() {
    ArticlePageObject article = new ArticlePageObject().open(articleName);
    VisualEditModePageObject visualEditMode = article.navigateToArticleEditPage();
    visualEditMode.clickSliderButton();

    DetachedRegisterPage auth = new DetachedRegisterPage();
    assertTrue(auth.isDisplayed());
  }

  @Test(groups = {"ArticleFeatureCRUDAnonymous_005", "ArticleFeaturesCRUDAnon"})
  public void ArticleCRUDAnonymous_005_AddingVideo() {
    ArticlePageObject article = new ArticlePageObject().open(articleName);
    VisualEditModePageObject visualEditMode = article.navigateToArticleEditPage();
    visualEditMode.clickVideoButton();

    DetachedRegisterPage auth = new DetachedRegisterPage();
    assertTrue(auth.isDisplayed());
  }

  @Execute(onWikia = "ludwiktestwiki")
  @Test(groups = {"ArticleFeatureCRUDAnonymous_001", "ArticleFeaturesCRUDAnon"})
  public void TestVarious() {

    for (int i = 0; i < 30; i++) {
      new ArticleContent(User.USER_CTEST).push("Article_d",
                                               String.format("Article_title_%d", i));
      for (int j=0; j< 3; j++) {
        new ArticleContent(User.USER_CTEST).push(String.format("Article_%d", i+j),
                                                 String.format("Article_title_%d", i));
      }
    }
  }

}

