package com.wikia.webdriver.TestCases.BlogTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Gallery.GalleryAddPhotoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Gallery.GalleryBuilderComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoAddComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoOptionsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetOptionsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialCreateBlogPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.BlogPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;

public class BlogFeaturesTests extends TestTemplate{

	@Test(groups={"BlogFeatures_001", "BlogTests"})
	public void BlogFeatures_001_AddingGallery(){
		CommonFunctions.logOut(driver);
		SpecialCreateBlogPageObject blogEdit = new SpecialCreateBlogPageObject(driver, Global.DOMAIN, "");
		blogEdit.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password);
		String blogPostTitle = PageContent.blogPostNamePrefix + blogEdit.getTimeStamp(); 
		blogEdit = blogEdit.createBlogFormUrl(blogPostTitle);
		GalleryBuilderComponentObject galleryBuiler = blogEdit.clickGallery();
		GalleryAddPhotoComponentObject galleryAddPhoto = galleryBuiler.clickAddPhoto();
		galleryAddPhoto.search("image");
		galleryAddPhoto.choosePhotos(4);
		galleryBuiler = galleryAddPhoto.clickSelect();
		galleryBuiler.adjustPosition("Center");
		galleryBuiler.adjustColumns("2");
		galleryBuiler.adjustSpacing("Small");
		galleryBuiler.adjustOrientation(3);
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
		blogEdit.clickOnAddObjectButton("Slideshow");
		blogEdit.waitForObjectModalAndClickAddAphoto("GallerySlideshow");
		blogEdit.searchImageInLightBox("image");
		blogEdit.galleryCheckImageInputs(4);
		blogEdit.galleryClickOnSelectButton();
		blogEdit.gallerySetPositionSlideshow("Center");
		blogEdit.galleryClickOnFinishButton();
		blogEdit.verifyObjectInEditMode("slideshow");
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
		blogEdit.clickOnAddObjectButton("Slider");
		blogEdit.waitForObjectModalAndClickAddAphoto("GallerySlider");
		blogEdit.searchImageInLightBox("image");
		blogEdit.galleryCheckImageInputs(4);
		blogEdit.galleryClickOnSelectButton();
		blogEdit.gallerySetSliderPosition(2);
		blogEdit.galleryClickOnFinishButton();
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
//		blogEdit.clickOnAddObjectButton("Image");
//		blogEdit.waitForModalAndClickAddThisPhoto();
//		blogEdit.typePhotoCaption(PageContent.caption);
//		blogEdit.clickOnAddPhotoButton2();
		blogEdit.verifyThatThePhotoAppears(PageContent.caption);
		BlogPageObject blog = blogEdit.clickOnPublishButton();
		blog.verifyImageOnThePage();
	}
}
