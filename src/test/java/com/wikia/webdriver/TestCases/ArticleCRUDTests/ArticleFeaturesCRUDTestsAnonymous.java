package com.wikia.webdriver.TestCases.ArticleCRUDTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;

public class ArticleFeaturesCRUDTestsAnonymous extends TestTemplate
{

	@Test(groups={"ArticleFeatureCRUDAnonymous_001", "ArticleCRUDAnonymous"})
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving
	// Test Case 004 Adding images to an article in edit mode
	public void ArticleCRUDAnonymous_001_AddingImage()
	{
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		article.logOut(driver);
		article.openWikiPage();
		WikiArticleEditMode edit = article.createNewDefaultArticle();
		edit.deleteArticleContent();
		edit.clickPhotoButton();
		article.verifyModalLoginAppeared();
		article.logOut(driver);
	}

	@Test(groups={"ArticleFeatureCRUDAnonymous_002", "ArticleCRUDAnonymous"})
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving
	// Test Case 007  Adding galleries to an article in edit mode
	public void ArticleCRUDAnonymous_002_AddingGallery()
	{
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		article.logOut(driver);
		article.openWikiPage();
		WikiArticleEditMode edit = article.createNewDefaultArticle();
		edit.deleteArticleContent();
		edit.clickGalleryButton();
		article.verifyModalLoginAppeared();
		article.logOut(driver);
	}

	@Test(groups={"ArticleFeatureCRUDAnonymous_003", "ArticleCRUDAnonymous"})
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving
	// Test Case 008 Adding slideshows to an article in edit mode
	public void ArticleCRUDAnonymous_003_AddingSlideshow()
	{
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		article.logOut(driver);
		article.openWikiPage();
		WikiArticleEditMode edit = article.createNewDefaultArticle();
		edit.deleteArticleContent();
		edit.clickSlideshowButton();
		article.verifyModalLoginAppeared();
		article.logOut(driver);
	}

	@Test(groups={"ArticleFeatureCRUDAnonymous_004", "ArticleCRUDAnonymous"})
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving
	// Test Case 009 Adding sliders to an article in edit mode
	public void ArticleCRUDAnonymous_004_AddingSlider()
	{
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		article.logOut(driver);
		article.openWikiPage();
		WikiArticleEditMode edit = article.createNewDefaultArticle();
		edit.deleteArticleContent();
		edit.clickSliderButton();
		article.verifyModalLoginAppeared();
		article.logOut(driver);
	}

	@Test(groups={"ArticleFeatureCRUDAnonymous_005", "ArticleCRUDAnonymous"})
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving
	// Test Case 010 Adding videos to an article in edit mode
	public void ArticleCRUDAnonymous_005_AddingVideo()
	{
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		article.logOut(driver);
		article.openWikiPage();
		WikiArticleEditMode edit = article.createNewDefaultArticle();
		edit.deleteArticleContent();
		edit.clickVideoButton();
		article.verifyModalLoginAppeared();
		article.logOut(driver);
	}



}
