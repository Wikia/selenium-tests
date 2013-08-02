package com.wikia.webdriver.TestCases.ArticleCRUDTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.AddPhoto.AddPhotoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Gallery.GalleryBuilderComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Gallery.GalleryBuilderComponentObject.Orientation;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Gallery.GalleryBuilderComponentObject.PositionsGallery;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Gallery.GalleryBuilderComponentObject.SpacingGallery;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoAddComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoOptionsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Slider.SliderBuilderComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Slider.SliderBuilderComponentObject.MenuPositions;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Slideshow.SlideshowBuilderComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Slideshow.SlideshowBuilderComponentObject.Positions;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetOptionsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject.Components;

/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class ArticleFeaturesCRUDTestsUser extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(groups={"ArticleFeaturesCRUDUser_001", "ArticleFeaturesCRUDUser", "Smoke"})
	public void ArticleFeaturesCRUDUser_001_addModifyGallery() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
		visualEditMode.clearContent();
		GalleryBuilderComponentObject galleryBuiler = visualEditMode.clickGalleryButton();
		AddPhotoComponentObject galleryAddPhoto = galleryBuiler.clickAddPhoto();
		galleryAddPhoto.search("image");
		galleryAddPhoto.choosePhotos(4);
		galleryAddPhoto.clickSelect();
		galleryBuiler.adjustPosition(PositionsGallery.center);
		galleryBuiler.adjustColumns("2");
		galleryBuiler.adjustSpacing(SpacingGallery.small);
		galleryBuiler.adjustOrientation(Orientation.landscape);
		galleryBuiler.clickFinish();
		visualEditMode.verifyGallery();
		visualEditMode.submitArticle();
		article.verifyGallery();

		article.editArticleUsingDropdown();
		visualEditMode.modifyComponent(Components.Gallery);
		galleryBuiler.clickAddPhoto();
		galleryAddPhoto.search("image");
		galleryAddPhoto.choosePhotos(2);
		galleryAddPhoto.clickSelect();
		galleryBuiler.adjustPosition(PositionsGallery.right);
		galleryBuiler.adjustColumns("3");
		galleryBuiler.adjustSpacing(SpacingGallery.medium);
		galleryBuiler.adjustOrientation(Orientation.portrait);
		galleryBuiler.clickFinish();
		visualEditMode.verifyGallery();
		visualEditMode.submitArticle();
		article.verifyGallery();
	}

	@Test(groups={"ArticleFeaturesCRUDUser_002", "ArticleFeaturesCRUDUser"})
	public void ArticleFeaturesCRUDUser_002_addDeleteGallery() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
		visualEditMode.clearContent();
		GalleryBuilderComponentObject galleryBuiler = visualEditMode.clickGalleryButton();
		AddPhotoComponentObject galleryAddPhoto = galleryBuiler.clickAddPhoto();
		galleryAddPhoto.search("image");
		galleryAddPhoto.choosePhotos(4);
		galleryAddPhoto.clickSelect();
		galleryBuiler.adjustPosition(PositionsGallery.center);
		galleryBuiler.adjustColumns("2");
		galleryBuiler.adjustSpacing(SpacingGallery.small);
		galleryBuiler.adjustOrientation(Orientation.landscape);
		galleryBuiler.clickFinish();
		visualEditMode.verifyGallery();
		visualEditMode.submitArticle();
		article.verifyGallery();

		article.editArticleUsingDropdown();
		visualEditMode.removeComponent(Components.Gallery);
		visualEditMode.verifyComponentRemoved(Components.Gallery);
	}

	@Test(groups={"ArticleFeaturesCRUDUser_003", "ArticleFeaturesCRUDUser", "Smoke"})
	public void ArticleFeaturesCRUDUser_003_addModifySlideshow() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
		visualEditMode.clearContent();
		SlideshowBuilderComponentObject slideshowBuilder = visualEditMode.clickSlideshowButton();
		AddPhotoComponentObject slideshowAddPhoto = slideshowBuilder.clickAddPhoto();
		slideshowAddPhoto.search("image");
		slideshowAddPhoto.choosePhotos(4);
		slideshowAddPhoto.clickSelect();
		slideshowBuilder.adjustPosition(Positions.Center);
		slideshowBuilder.clickFinish();
		visualEditMode.verifySlideshow();
		visualEditMode.submitArticle();
		article.verifySlideshow();

		article.editArticleUsingDropdown();
		visualEditMode.modifyComponent(Components.Slideshow);
		slideshowBuilder.clickAddPhoto();
		slideshowAddPhoto.search("image");
		slideshowAddPhoto.choosePhotos(8);
		slideshowAddPhoto.clickSelect();
		slideshowBuilder.adjustPosition(Positions.Right);
		slideshowBuilder.clickFinish();
		visualEditMode.verifySlideshow();
		visualEditMode.submitArticle();
		article.verifySlideshow();
	}

	@Test(groups={"ArticleFeaturesCRUDUser_004", "ArticleFeaturesCRUDUser"})
	public void ArticleFeaturesCRUDUser_004_addDeleteSlideshow() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
		visualEditMode.clearContent();
		SlideshowBuilderComponentObject slideshowBuilder = visualEditMode.clickSlideshowButton();
		AddPhotoComponentObject slideshowAddPhoto = slideshowBuilder.clickAddPhoto();
		slideshowAddPhoto.search("image");
		slideshowAddPhoto.choosePhotos(4);
		slideshowAddPhoto.clickSelect();
		slideshowBuilder.adjustPosition(Positions.Center);
		slideshowBuilder.clickFinish();
		visualEditMode.verifySlideshow();
		visualEditMode.submitArticle();
		article.verifySlideshow();

		article.editArticleUsingDropdown();
		visualEditMode.removeComponent(Components.Slideshow);
		visualEditMode.verifyComponentRemoved(Components.Slideshow);
	}

	@Test(groups={"ArticleFeaturesCRUDUser_005", "ArticleFeaturesCRUDUser", "Smoke"})
	public void ArticleFeaturesCRUDUser_005_addModifySlider() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
		visualEditMode.clearContent();
		SliderBuilderComponentObject sliderBuilder = visualEditMode.clickSliderButton();
		sliderBuilder.selectMenuPosition(MenuPositions.Vertical);
		AddPhotoComponentObject sliderAddPhoto = sliderBuilder.clickAddPhoto();
		sliderAddPhoto.search("image");
		sliderAddPhoto.choosePhotos(4);
		sliderAddPhoto.clickSelect();
		sliderBuilder.clickFinish();
		visualEditMode.verifySlider();
		visualEditMode.submitArticle();
		article.verifySlider();

		article.editArticleUsingDropdown();
		visualEditMode.modifyComponent(Components.Slider);
		sliderBuilder.selectMenuPosition(MenuPositions.Horizontal);
		sliderAddPhoto = sliderBuilder.clickAddPhoto();
		sliderAddPhoto.search("image");
		sliderAddPhoto.choosePhotos(8);
		sliderAddPhoto.clickSelect();
		sliderBuilder.clickFinish();
		visualEditMode.verifySlider();
		visualEditMode.submitArticle();
		article.verifySlider();
	}

	@Test(groups={"ArticleFeaturesCRUDUser_006", "ArticleFeaturesCRUDUser"})
	public void ArticleFeaturesCRUDUser_006_addDeleteSlider() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
		visualEditMode.clearContent();
		SliderBuilderComponentObject sliderBuilder = visualEditMode.clickSliderButton();
		sliderBuilder.selectMenuPosition(MenuPositions.Vertical);
		AddPhotoComponentObject sliderAddPhoto = sliderBuilder.clickAddPhoto();
		sliderAddPhoto.search("image");
		sliderAddPhoto.choosePhotos(4);
		sliderAddPhoto.clickSelect();
		sliderBuilder.clickFinish();
		visualEditMode.verifySlider();
		visualEditMode.submitArticle();
		article.verifySlider();

		article.editArticleUsingDropdown();
		visualEditMode.removeComponent(Components.Slider);
		visualEditMode.verifyComponentRemoved(Components.Slider);
	}

	@Test(groups={"ArticleFeaturesCRUDUser_007", "ArticleFeaturesCRUDUser", "Smoke"})
	public void ArticleFeaturesCRUDUser_007_addModifyVideo() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
		visualEditMode.clearContent();
		VetAddVideoComponentObject vetAddVideo = visualEditMode.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddVideo.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.setCaption(PageContent.caption);
		vetOptions.submit();
		visualEditMode.verifyVideo();
		visualEditMode.submitArticle();
		article.verifyVideo();
		article.editArticleUsingDropdown();
		visualEditMode.modifyComponent(Components.Video);
		vetOptions.setCaption(PageContent.caption2);
		vetOptions.update();
		visualEditMode.verifyVideo();
		visualEditMode.submitArticle();
		article.verifyVideo();
	}

	@Test(groups={"ArticleFeaturesCRUDUser_008", "ArticleFeaturesCRUDUser"})
	public void ArticleFeaturesCRUDUser_008_addDeleteVideo() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
		visualEditMode.clearContent();
		VetAddVideoComponentObject vetAddVideo = visualEditMode.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddVideo.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.setCaption(PageContent.caption);
		vetOptions.submit();
		visualEditMode.verifyVideo();
		visualEditMode.submitArticle();
		article.verifyVideo();
		article.editArticleUsingDropdown();
		visualEditMode.modifyComponent(Components.Video);
		vetOptions.setCaption(PageContent.caption2);
		vetOptions.update();
		visualEditMode.removeComponent(Components.Video);
		visualEditMode.verifyComponentRemoved(Components.Video);
	}

	@Test(groups={"ArticleFeaturesCRUDUser_009", "ArticleFeaturesCRUDUser", "Smoke"})
	public void ArticleFeaturesCRUDUser_009_addingModifyImage() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
		visualEditMode.clearContent();
		PhotoAddComponentObject photoAddPhoto = visualEditMode.clickPhotoButton();
		PhotoOptionsComponentObject photoOptions = photoAddPhoto.addPhotoFromWiki("image", 1);
		photoOptions.setCaption(PageContent.caption);
		photoOptions.clickAddPhoto();
		visualEditMode.verifyPhoto();
		visualEditMode.submitArticle();
		article.editArticleUsingDropdown();
		visualEditMode.modifyComponent(Components.Photo);
		photoOptions.setCaption(PageContent.caption2);
		photoOptions.clickAddPhoto();
		visualEditMode.verifyPhoto();
		visualEditMode.submitArticle();
		article.verifyPhoto();
	}

	@Test(groups={"ArticleFeaturesCRUDUser_010", "ArticleFeaturesCRUDUser"})
	public void ArticleFeaturesCRUDUser_010_addDeleteImage() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
		visualEditMode.clearContent();
		PhotoAddComponentObject photoAddPhoto = visualEditMode.clickPhotoButton();
		PhotoOptionsComponentObject photoOptions = photoAddPhoto.addPhotoFromWiki("image", 1);
		photoOptions.setCaption(PageContent.caption);
		photoOptions.clickAddPhoto();
		visualEditMode.verifyPhoto();
		visualEditMode.submitArticle();
		article.editArticleUsingDropdown();
		visualEditMode.removeComponent(Components.Photo);
		visualEditMode.verifyComponentRemoved(Components.Photo);
	}
}