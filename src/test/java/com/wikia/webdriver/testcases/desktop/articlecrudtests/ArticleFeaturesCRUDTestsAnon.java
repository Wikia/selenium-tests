package com.wikia.webdriver.testcases.desktop.articlecrudtests;

import static com.wikia.webdriver.common.core.Assertion.assertTrue;

import com.wikia.webdriver.common.core.classifiers.*;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.DetachedRegisterPage;

import org.testng.annotations.Test;

@Test(groups = {Team.SUS, View.DESKTOP, Feature.ADVANCED_FEATURES, EndUser.ANON})
public class ArticleFeaturesCRUDTestsAnon extends NewTestTemplate {

  private static final String articleName = "ArticleFeaturesCRUDAnon";

  public void ArticleCRUDAnonymous_001_AddingImage() {
    ArticlePageObject article = new ArticlePageObject().open(articleName);
    VisualEditModePageObject visualEditMode = article.navigateToArticleEditPage();
    visualEditMode.clickPhotoButton();

    DetachedRegisterPage auth = new DetachedRegisterPage();
    assertTrue(auth.isDisplayed());
  }

  public void ArticleCRUDAnonymous_002_AddingGallery() {
    ArticlePageObject article = new ArticlePageObject().open(articleName);
    VisualEditModePageObject visualEditMode = article.navigateToArticleEditPage();
    visualEditMode.clickGalleryButton();

    DetachedRegisterPage auth = new DetachedRegisterPage();
    assertTrue(auth.isDisplayed());
  }

  public void ArticleCRUDAnonymous_003_AddingSlideshow() {
    ArticlePageObject article = new ArticlePageObject().open(articleName);
    VisualEditModePageObject visualEditMode = article.navigateToArticleEditPage();
    visualEditMode.clickSlideshowButton();

    DetachedRegisterPage auth = new DetachedRegisterPage();
    assertTrue(auth.isDisplayed());
  }

  public void ArticleCRUDAnonymous_004_AddingSlider() {
    ArticlePageObject article = new ArticlePageObject().open(articleName);
    VisualEditModePageObject visualEditMode = article.navigateToArticleEditPage();
    visualEditMode.clickSliderButton();

    DetachedRegisterPage auth = new DetachedRegisterPage();
    assertTrue(auth.isDisplayed());
  }

  public void ArticleCRUDAnonymous_005_AddingVideo() {
    ArticlePageObject article = new ArticlePageObject().open(articleName);
    VisualEditModePageObject visualEditMode = article.navigateToArticleEditPage();
    visualEditMode.clickVideoButton();

    DetachedRegisterPage auth = new DetachedRegisterPage();
    assertTrue(auth.isDisplayed());
  }
}
