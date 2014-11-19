package com.wikia.webdriver.TestCases.BlogTests;

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
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialCreatePagePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.Blog.BlogPageObject;

public class BlogFeaturesTests extends NewTestTemplate{

	Credentials credentials = config.getCredentials();

	@Test(groups={"BlogFeatures_001", "BlogFeaturesTests"})
	public void BlogFeatures_001_AddingGallery(){
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		SpecialCreatePagePageObject createPage = base.openSpecialCreateBlogPage(wikiURL);
		String blogPostTitle = PageContent.blogPostNamePrefix + createPage.getTimeStamp();
		VisualEditModePageObject blogEdit = createPage.populateTitleField(blogPostTitle);
		GalleryBuilderComponentObject galleryBuiler = blogEdit.clickGalleryButton();
		AddPhotoComponentObject galleryAddPhoto = galleryBuiler.clickAddPhoto();
		galleryAddPhoto.search("image");
		galleryAddPhoto.choosePhotos(4);
		galleryAddPhoto.clickSelect();
		galleryBuiler.adjustPosition(PositionsGallery.CENTER);
		galleryBuiler.adjustColumns("2");
		galleryBuiler.adjustSpacing(SpacingGallery.SMALL);
		galleryBuiler.adjustOrientation(Orientation.LANDSCAPE);
		galleryBuiler.clickFinish();
		blogEdit.verifyGallery();
		BlogPageObject blogPage = blogEdit.submitBlog();
		blogPage.verifyGallery();
	}

	@Test(groups={"BlogFeatures_002", "BlogFeaturesTests"})
	public void BlogFeatures_002_AddingSlideshow(){
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		SpecialCreatePagePageObject createPage = base.openSpecialCreateBlogPage(wikiURL);
		String blogPostTitle = PageContent.blogPostNamePrefix + createPage.getTimeStamp();
		VisualEditModePageObject blogEdit = createPage.populateTitleField(blogPostTitle);
		SlideshowBuilderComponentObject slideshowBuilder = blogEdit.clickSlideshowButton();
		AddPhotoComponentObject slideshowAddPhoto = slideshowBuilder.clickAddPhoto();
		slideshowAddPhoto.search("image");
		slideshowAddPhoto.choosePhotos(4);
		slideshowAddPhoto.clickSelect();
		slideshowBuilder.adjustPosition(Positions.CENTER);
		slideshowBuilder.clickFinish();
		blogEdit.verifySlideshow();
		BlogPageObject blogPage = blogEdit.submitBlog();
		blogPage.verifySlideshow();
	}

	@Test(groups={"BlogFeatures_003", "BlogFeaturesTests"})
	public void BlogFeatures_003_AddingSlider(){
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		SpecialCreatePagePageObject createPage = base.openSpecialCreateBlogPage(wikiURL);
		String blogPostTitle = PageContent.blogPostNamePrefix + createPage.getTimeStamp();
		VisualEditModePageObject blogEdit = createPage.populateTitleField(blogPostTitle);
		SliderBuilderComponentObject sliderBuilder = blogEdit.clickSliderButton();
		sliderBuilder.selectMenuPosition(MenuPositions.VERTICAL);
		AddPhotoComponentObject sliderAddPhoto = sliderBuilder.clickAddPhoto();
		sliderAddPhoto.search("image");
		sliderAddPhoto.choosePhotos(4);
		sliderAddPhoto.clickSelect();
		sliderBuilder.clickFinish();
		blogEdit.verifySlider();
		BlogPageObject blogPage = blogEdit.submitBlog();
		blogPage.verifySlider();
	}

	@Test(groups={"BlogFeatures_004", "BlogFeaturesTests", "Media"})
	public void BlogFeatures_004_AddingVideo(){
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		SpecialCreatePagePageObject createPage = base.openSpecialCreateBlogPage(wikiURL);
		String blogPostTitle = PageContent.blogPostNamePrefix + createPage.getTimeStamp();
		VisualEditModePageObject blogEdit = createPage.populateTitleField(blogPostTitle);
		VetAddVideoComponentObject vetAddVideo = blogEdit.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddVideo.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.setCaption(PageContent.caption);
		vetOptions.submit();
		blogEdit.verifyVideo();
		BlogPageObject blogPage = blogEdit.submitBlog();
		blogPage.verifyVideo();
	}

	@Test(groups={"BlogFeatures_005", "BlogFeaturesTests"})
	public void BlogFeatures_005_AddingImage(){
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		SpecialCreatePagePageObject createPage = base.openSpecialCreateBlogPage(wikiURL);
		String blogPostTitle = PageContent.blogPostNamePrefix + createPage.getTimeStamp();
		VisualEditModePageObject blogEdit = createPage.populateTitleField(blogPostTitle);
		PhotoAddComponentObject photoAddPhoto = blogEdit.clickPhotoButton();
		PhotoOptionsComponentObject photoOptions = photoAddPhoto.addPhotoFromWiki("image", 1);
		photoOptions.setCaption(PageContent.caption);
		photoOptions.clickAddPhoto();
		blogEdit.verifyPhoto();
		BlogPageObject blogPage = blogEdit.submitBlog();
		blogPage.verifyPhoto();
	}
}
