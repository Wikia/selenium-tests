package com.wikia.webdriver.TestCases.BlogTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.AddPhoto.AddPhotoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Gallery.GalleryBuilderComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Gallery.GalleryBuilderComponentObject.Orientation;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoAddComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoOptionsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Slider.SliderBuilderComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Slider.SliderBuilderComponentObject.MenuPositions;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Slideshow.SlideshowBuilderComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Slideshow.SlideshowBuilderComponentObject.Positions;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetOptionsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Blog.SpecialCreateBlogPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.Blog.BlogPageObject;

public class BlogFeaturesTests extends TestTemplate{

	@Test(groups={"BlogFeatures_001", "BlogTests"})
	public void BlogFeatures_001_AddingGallery(){
		CommonFunctions.logOut(driver);
		SpecialCreateBlogPageObject blogEdit = new SpecialCreateBlogPageObject(driver, Global.DOMAIN, "");
		blogEdit.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password);
		String blogPostTitle = PageContent.blogPostNamePrefix + blogEdit.getTimeStamp(); 
		blogEdit = blogEdit.createBlogFormUrl(blogPostTitle);
		GalleryBuilderComponentObject galleryBuiler = blogEdit.clickGalleryButton();
		AddPhotoComponentObject galleryAddPhoto = galleryBuiler.clickAddPhoto();
		galleryAddPhoto.search("image");
		galleryAddPhoto.choosePhotos(4);
		galleryAddPhoto.clickSelect();
		galleryBuiler.adjustPosition("Center");
		galleryBuiler.adjustColumns("2");
		galleryBuiler.adjustSpacing("Small");
		galleryBuiler.adjustOrientation(Orientation.landscape);
		galleryBuiler.clickFinish();
		blogEdit.verifyObjectInEditMode("gallery");
		BlogPageObject blog = blogEdit.clickOnPublishButton();
		blog.verifyObjectOnThePage("gallery");
	}
	
	@Test(groups={"BlogFeatures_002", "BlogTests"})
	public void BlogFeatures_002_AddingSlideshow(){
		CommonFunctions.logOut(driver);
		SpecialCreateBlogPageObject blogEdit = new SpecialCreateBlogPageObject(driver, Global.DOMAIN, "");
		blogEdit.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password);
		String blogPostTitle = PageContent.blogPostNamePrefix + blogEdit.getTimeStamp(); 
		blogEdit = blogEdit.createBlogFormUrl(blogPostTitle);
		SlideshowBuilderComponentObject slideshowBuilder = blogEdit.clickSlideshowButton();
		AddPhotoComponentObject slideshowAddPhoto = slideshowBuilder.clickAddPhoto();
		slideshowAddPhoto.search("image");
		slideshowAddPhoto.choosePhotos(4);
		slideshowAddPhoto.clickSelect();
		slideshowBuilder.adjustPosition(Positions.Center);
		slideshowBuilder.clickFinish();
		BlogPageObject blog = blogEdit.clickOnPublishButton();
		blog.verifyObjectOnThePage("slideshow");
		
	}
	
	@Test(groups={"BlogFeatures_003", "BlogTests"})
	public void BlogFeatures_003_AddingSlider(){
		CommonFunctions.logOut(driver);
		SpecialCreateBlogPageObject blogEdit = new SpecialCreateBlogPageObject(driver, Global.DOMAIN, "");
		blogEdit.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password);
		String blogPostTitle = PageContent.blogPostNamePrefix + blogEdit.getTimeStamp(); 
		blogEdit = blogEdit.createBlogFormUrl(blogPostTitle);
		SliderBuilderComponentObject sliderBuilder = blogEdit.clickSliderButton();
		sliderBuilder.selectMenuPosition(MenuPositions.Vertical);
		AddPhotoComponentObject sliderAddPhoto = sliderBuilder.clickAddPhoto();
		sliderAddPhoto.search("image");
		sliderAddPhoto.choosePhotos(4);
		sliderAddPhoto.clickSelect();
		sliderBuilder.clickFinish();
		blogEdit.verifyObjectInEditMode("gallery-slider");
		BlogPageObject blog = blogEdit.clickOnPublishButton();
		blog.verifyObjectOnThePage("slider");
	}
	
	@Test(groups={"BlogFeatures_004", "BlogTests"})
	public void BlogFeatures_004_AddingVideo(){
		CommonFunctions.logOut(driver);
		SpecialCreateBlogPageObject blogEdit = new SpecialCreateBlogPageObject(driver, Global.DOMAIN, "");
		blogEdit.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password);
		String blogPostTitle = PageContent.blogPostNamePrefix + blogEdit.getTimeStamp(); 
		blogEdit = blogEdit.createBlogFormUrl(blogPostTitle);
		VetAddVideoComponentObject vetAddVideo = blogEdit.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddVideo.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.setCaption(PageContent.caption);
		vetOptions.submit();
		blogEdit.verifyVideoInEditMode(PageContent.caption);
		BlogPageObject blog = blogEdit.clickOnPublishButton();
		blog.verifyVideoOnThePage();
	}

	@Test(groups={"BlogFeatures_005", "BlogTests"})
	public void BlogFeatures_005_AddingImage(){
		CommonFunctions.logOut(driver);
		SpecialCreateBlogPageObject blogEdit = new SpecialCreateBlogPageObject(driver, Global.DOMAIN, "");
		blogEdit.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password);
		String blogPostTitle = PageContent.blogPostNamePrefix + blogEdit.getTimeStamp(); 
		blogEdit = blogEdit.createBlogFormUrl(blogPostTitle);
		PhotoAddComponentObject photoAddPhoto = blogEdit.clickPhotoButton();
		PhotoOptionsComponentObject photoOptions = photoAddPhoto.addPhotoFromWiki("image", 1);
		photoOptions.setCaption(PageContent.caption);
		photoOptions.clickAddPhoto();
		blogEdit.verifyThatThePhotoAppears(PageContent.caption);
		BlogPageObject blog = blogEdit.clickOnPublishButton();
		blog.verifyImageOnThePage();
	}
}
