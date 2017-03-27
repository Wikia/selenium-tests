package com.wikia.webdriver.testcases.articlecrudtests;

import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.AuthPageContext;
import org.testng.annotations.Test;

public class ArticleFeaturesCRUDTestsAnon extends NewTestTemplate {

  private static final String articleName = "ArticleFeaturesCRUDAnon";

  @Test(groups = {"ArticleFeatureCRUDAnonymous_001", "ArticleFeaturesCRUDAnon"})
  public void ArticleCRUDAnonymous_001_AddingImage() {
    ArticlePageObject article = new ArticlePageObject().open(articleName);
    VisualEditModePageObject visualEditMode = article.navigateToArticleEditPage();
    visualEditMode.clickPhotoButton();

    AuthPageContext auth = new AuthPageContext();
    auth.isModalOpen();
  }

  @Test(groups = {"ArticleFeatureCRUDAnonymous_002", "ArticleFeaturesCRUDAnon"})
  public void ArticleCRUDAnonymous_002_AddingGallery() {
    ArticlePageObject article = new ArticlePageObject().open(articleName);
    VisualEditModePageObject visualEditMode = article.navigateToArticleEditPage();
    visualEditMode.clickGalleryButton();

    AuthPageContext auth = new AuthPageContext();
    auth.isModalOpen();
  }

  @Test(groups = {"ArticleFeatureCRUDAnonymous_003", "ArticleFeaturesCRUDAnon"})
  public void ArticleCRUDAnonymous_003_AddingSlideshow() {
    ArticlePageObject article = new ArticlePageObject().open(articleName);
    VisualEditModePageObject visualEditMode = article.navigateToArticleEditPage();
    visualEditMode.clickSlideshowButton();

    AuthPageContext auth = new AuthPageContext();
    auth.isModalOpen();
  }

  @Test(groups = {"ArticleFeatureCRUDAnonymous_004", "ArticleFeaturesCRUDAnon"})
  public void ArticleCRUDAnonymous_004_AddingSlider() {
    ArticlePageObject article = new ArticlePageObject().open(articleName);
    VisualEditModePageObject visualEditMode = article.navigateToArticleEditPage();
    visualEditMode.clickSliderButton();

    AuthPageContext auth = new AuthPageContext();
    auth.isModalOpen();
  }

  @Test(groups = {"ArticleFeatureCRUDAnonymous_005", "ArticleFeaturesCRUDAnon"})
  public void ArticleCRUDAnonymous_005_AddingVideo() {
    ArticlePageObject article = new ArticlePageObject().open(articleName);
    VisualEditModePageObject visualEditMode = article.navigateToArticleEditPage();
    visualEditMode.clickVideoButton();

    AuthPageContext auth = new AuthPageContext();
    auth.isModalOpen();
  }
}
