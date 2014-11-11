package com.wikia.webdriver.testcases.blogtests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.addphoto.AddPhotoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.gallery.GalleryBuilderComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.gallery.GalleryBuilderComponentObject.Orientation;
import com.wikia.webdriver.pageobjectsfactory.componentobject.gallery.GalleryBuilderComponentObject.PositionsGallery;
import com.wikia.webdriver.pageobjectsfactory.componentobject.gallery.GalleryBuilderComponentObject.SpacingGallery;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoAddComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoOptionsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.slider.SliderBuilderComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.slider.SliderBuilderComponentObject.MenuPositions;
import com.wikia.webdriver.pageobjectsfactory.componentobject.slideshow.SlideshowBuilderComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.slideshow.SlideshowBuilderComponentObject.Positions;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetOptionsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialCreatePagePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.blog.BlogPageObject;

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
		galleryBuiler.adjustPosition(PositionsGallery.center);
		galleryBuiler.adjustColumns("2");
		galleryBuiler.adjustSpacing(SpacingGallery.small);
		galleryBuiler.adjustOrientation(Orientation.landscape);
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
		slideshowBuilder.adjustPosition(Positions.Center);
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
		sliderBuilder.selectMenuPosition(MenuPositions.Vertical);
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
