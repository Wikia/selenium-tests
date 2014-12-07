package com.wikia.webdriver.testcases.articlecrudtests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class ArticleFeaturesCRUDTestsAnon extends NewTestTemplate {

	@Test(groups={"ArticleFeatureCRUDAnonymous_001", "ArticleFeaturesCRUDAnon"})
	public void ArticleCRUDAnonymous_001_AddingImage() {
		ArticlePageObject article = new ArticlePageObject(driver);
		article.openRandomArticle(wikiURL);
		VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
		visualEditMode.clickPhotoButton();
		visualEditMode.verifyModalLoginAppeared();
	}

	@Test(groups={"ArticleFeatureCRUDAnonymous_002", "ArticleFeaturesCRUDAnon"})
	public void ArticleCRUDAnonymous_002_AddingGallery() {
		ArticlePageObject article = new ArticlePageObject(driver);
		article.openRandomArticle(wikiURL);
		VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
		visualEditMode.clickGalleryButton();
		visualEditMode.verifyModalLoginAppeared();
	}

	@Test(groups={"ArticleFeatureCRUDAnonymous_003", "ArticleFeaturesCRUDAnon"})
	public void ArticleCRUDAnonymous_003_AddingSlideshow() {
		ArticlePageObject article = new ArticlePageObject(driver);
		article.openRandomArticle(wikiURL);
		VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
		visualEditMode.clickSlideshowButton();
		visualEditMode.verifyModalLoginAppeared();
	}

	@Test(groups={"ArticleFeatureCRUDAnonymous_004", "ArticleFeaturesCRUDAnon"})
	public void ArticleCRUDAnonymous_004_AddingSlider() {
		ArticlePageObject article = new ArticlePageObject(driver);
		article.openRandomArticle(wikiURL);
		VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
		visualEditMode.clickSliderButton();
		visualEditMode.verifyModalLoginAppeared();
	}

	@Test(groups={"ArticleFeatureCRUDAnonymous_005", "ArticleFeaturesCRUDAnon"})
	public void ArticleCRUDAnonymous_005_AddingVideo() {
		ArticlePageObject article = new ArticlePageObject(driver);
		article.openRandomArticle(wikiURL);
		VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
		visualEditMode.clickVideoButton();
		visualEditMode.verifyModalLoginAppeared();
	}
}
