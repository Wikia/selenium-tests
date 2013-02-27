package com.wikia.webdriver.TestCases.BlogTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
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
		blogEdit.clickOnAddObjectButton("Gallery");
		blogEdit.waitForObjectModalAndClickAddAphoto("Gallery");
		blogEdit.searchImageInLightBox("image");
		blogEdit.galleryCheckImageInputs(4);
		blogEdit.galleryClickOnSelectButton();
		blogEdit.gallerySetPositionGallery("Center");//error!!!
		blogEdit.gallerySetPhotoOrientation(2);
		blogEdit.galleryClickOnFinishButton();
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
		blogEdit.clickOnAddObjectButton("Video");
		blogEdit.waitForVideoModalAndTypeVideoURL(VideoContent.youtubeVideoURL);
		blogEdit.clickVideoNextButton();
		blogEdit.waitForVideoDialog();
		blogEdit.typeVideoCaption(PageContent.caption);
		blogEdit.clickAddAvideo();
		blogEdit.waitForSuccesDialogAndReturnToEditing();
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
		blogEdit.clickOnAddObjectButton("Image");
		blogEdit.waitForModalAndClickAddThisPhoto();
		blogEdit.typePhotoCaption(PageContent.caption);
		blogEdit.clickOnAddPhotoButton2();
		blogEdit.verifyThatThePhotoAppears(PageContent.caption);
		BlogPageObject blog = blogEdit.clickOnPublishButton();
		blog.verifyImageOnThePage();
	}
}
