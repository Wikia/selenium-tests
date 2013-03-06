package com.wikia.webdriver.TestCases.ArticleCRUDTests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Gallery.GalleryAddPhotoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Gallery.GalleryBuilderComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoAddComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoOptionsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetOptionsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.LightboxPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.FileDetailsPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;

public class ArticleFeaturesCRUDTestsAdmin extends TestTemplate
{
	
	@Test(groups={"ArticleFeaturesCRUDAdmin_001", "ArticleFeaturesCRUDAdmin", "Smoke"}) 
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving	
	// Test Case 007  Adding galleries to an article in edit mode 
	public void ArticleCRUDAdmin_001_AddingGallery()
	{
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
		GalleryBuilderComponentObject galleryBuiler = edit.clickGallery();
		GalleryAddPhotoComponentObject galleryAddPhoto = galleryBuiler.clickAddPhoto();
		galleryAddPhoto.search("image");
		galleryAddPhoto.choosePhotos(4);
		galleryBuiler = galleryAddPhoto.clickSelect();
		galleryBuiler.adjustPosition("Center");
		galleryBuiler.adjustColumns("2");
		galleryBuiler.adjustSpacing("Small");
		galleryBuiler.adjustOrientation(3);
		galleryBuiler.clickFinish();
		edit.verifyObjectInEditMode("gallery");
		edit.clickOnPreviewButton();
		edit.verifyTheObjectOnThePreview("gallery");
		WikiArticlePageObject article = edit.clickOnPublishButtonPreview();
		article.verifyObjectOnThePage("gallery");
	}
	
	@Test(groups={"ArticleFeaturesCRUDAdmin_002", "ArticleFeaturesCRUDAdmin"}) 
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving	
	// Test Case 007  Adding galleries to an article in edit mode
	public void ArticleCRUDAdmin_002_ModifyGallery()
	{
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
		GalleryBuilderComponentObject galleryBuiler = edit.clickGallery();
		GalleryAddPhotoComponentObject galleryAddPhoto = galleryBuiler.clickAddPhoto();
		galleryAddPhoto.search("image");
		galleryAddPhoto.choosePhotos(4);
		galleryBuiler = galleryAddPhoto.clickSelect();
		galleryBuiler.adjustPosition("Center");
		galleryBuiler.adjustColumns("2");
		galleryBuiler.adjustSpacing("Small");
		galleryBuiler.adjustOrientation(3);
		galleryBuiler.clickFinish();
		edit.verifyObjectInEditMode("gallery");
		edit.clickOnPreviewButton();
		edit.verifyTheObjectOnThePreview("gallery");
		WikiArticlePageObject article = edit.clickOnPublishButtonPreview();
		article.verifyObjectOnThePage("gallery");
		article.verifyGalleryPosion("center");
		edit = article.edit();
		galleryBuiler = edit.clickModifyButtonGallery(); 
		galleryAddPhoto = galleryBuiler.clickAddPhoto();
		galleryAddPhoto.search("image");
		galleryAddPhoto.choosePhotos(4);
		galleryBuiler = galleryAddPhoto.clickSelect();
		galleryBuiler.adjustPosition("Right");
		galleryBuiler.adjustColumns("3");
		galleryBuiler.adjustSpacing("Medium");
		galleryBuiler.adjustOrientation(3);
		galleryBuiler.clickFinish();
		edit.verifyObjectInEditMode("gallery");
		article = edit.clickOnPublishButton();
		article.verifyGalleryPosion("right");
	}
	
	@Test(groups={"ArticleFeaturesCRUDAdmin_003", "ArticleFeaturesCRUDAdmin"}) 
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving	
	// Test Case 007  Adding galleries to an article in edit mode
	public void ArticleCRUDAdmin_003_DeleteGallery()
	{
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);		
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
		GalleryBuilderComponentObject galleryBuiler = edit.clickGallery();
		GalleryAddPhotoComponentObject galleryAddPhoto = galleryBuiler.clickAddPhoto();
		galleryAddPhoto.search("image");
		galleryAddPhoto.choosePhotos(4);
		galleryBuiler = galleryAddPhoto.clickSelect();
		galleryBuiler.adjustPosition("Center");
		galleryBuiler.adjustColumns("2");
		galleryBuiler.adjustSpacing("Small");
		galleryBuiler.adjustOrientation(3);
		galleryBuiler.clickFinish();
		edit.verifyObjectInEditMode("gallery");
		edit.clickOnPreviewButton();
		edit.verifyTheObjectOnThePreview("gallery");
		WikiArticlePageObject article = edit.clickOnPublishButtonPreview();
		article.verifyObjectOnThePage("gallery");
		article.verifyGalleryPosion("center");
		edit = article.edit();
		edit.clickRemoveButtonGallery();
		edit.clickOkButton();
		edit.verifyTheGalleryNotOnTheArticleEditMode();
		article = edit.clickOnPublishButton();
		article.verifyGalleryNotOnThePage();
	}
	
	@Test(groups={"ArticleFeaturesCRUDAdmin_004", "ArticleFeaturesCRUDAdmin", "Smoke"})
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving	
	// Test Case 008 Adding slideshows to an article in edit mode
	public void ArticleCRUDAdmin_004_AddingSlideshow()
	{
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);		
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
		edit.clickOnAddObjectButton("Slideshow");
		edit.waitForObjectModalAndClickAddAphoto("GallerySlideshow");
		edit.searchImageInLightBox("image");
		edit.galleryCheckImageInputs(4);
		edit.galleryClickOnSelectButton();
		edit.gallerySetPositionSlideshow("Center");
		edit.galleryClickOnFinishButton();
		edit.verifyObjectInEditMode("slideshow");
		edit.clickOnPreviewButton();
		edit.verifyTheObjectOnThePreview("slideshow");
		WikiArticlePageObject article = edit.clickOnPublishButtonInPreviewMode();
		article.verifyObjectOnThePage("slideshow");
	}
	
	@Test(groups={"ArticleFeaturesCRUDAdmin_009", "ArticleFeaturesCRUDAdmin"})
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving	
	// Test Case 008 Adding slideshows to an article in edit mode
	public void ArticleCRUDAdmin_005_ModifySlideshow()
	{
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);	
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
		edit.clickOnAddObjectButton("Slideshow");
		edit.waitForObjectModalAndClickAddAphoto("GallerySlideshow");
		edit.searchImageInLightBox("image");
		edit.galleryCheckImageInputs(4);
		edit.galleryClickOnSelectButton();
		edit.gallerySetPositionSlideshow("Center");
		edit.galleryClickOnFinishButton();
		edit.verifyObjectInEditMode("slideshow");
		edit.clickOnPreviewButton();
		edit.verifyTheObjectOnThePreview("slideshow");
		WikiArticlePageObject article = edit.clickOnPublishButtonInPreviewMode();
		article.verifyObjectOnThePage("slideshow");
		article.verifySlideshowPosition("center");
		edit = article.edit();
		edit.clickModifyButtonSlideshow();
		edit.waitForObjectModalAndClickAddAphoto("GallerySlideshow");
		edit.searchImageInLightBox("image");
		edit.galleryCheckImageInputs(8);
		edit.galleryClickOnSelectButton();
		edit.gallerySetPositionSlideshow("Right");
		edit.galleryClickOnFinishButton();
		edit.verifyObjectInEditMode("slideshow");
		article = edit.clickOnPublishButton();
		article.verifyObjectOnThePage("slideshow");
		article.verifySlideshowPosition("right");
	}
	
	@Test(groups={"ArticleFeaturesCRUDAdmin_006", "ArticleFeaturesCRUDAdmin"})
	public void ArticleCRUDAdmin_006_DeleteSlideshow()
	{
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
		edit.clickOnAddObjectButton("Slideshow");
		edit.waitForObjectModalAndClickAddAphoto("GallerySlideshow");
		edit.searchImageInLightBox("image");
		edit.galleryCheckImageInputs(4);
		edit.galleryClickOnSelectButton();
		edit.gallerySetPositionSlideshow("Center");
		edit.galleryClickOnFinishButton();
		edit.verifyObjectInEditMode("slideshow");
		edit.clickOnPreviewButton();
		edit.verifyTheObjectOnThePreview("slideshow");
		WikiArticlePageObject article = edit.clickOnPublishButtonInPreviewMode();
		article.verifyObjectOnThePage("slideshow");
		article.verifySlideshowPosition("center");
		edit = article.edit();
		edit.clickRemoveButtonSlideshow();
		edit.clickOkButton();
		edit.verifyTheSlideshowNotOnTheArticleEditMode();
		article = edit.clickOnPublishButton();
		article.verifySlideshowNotOnThePage();
	}
	
	
	
	@Test(groups={"ArticleFeaturesCRUDAdmin_007", "ArticleFeaturesCRUDAdmin", "Smoke"})
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving	
	// Test Case 009 Adding sliders to an article in edit mode
	public void ArticleCRUDAdmin_007_AddingSlider()
	{
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);		
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
		edit.clickOnAddObjectButton("Slider");
		edit.waitForObjectModalAndClickAddAphoto("GallerySlider");
		edit.searchImageInLightBox("image");
		edit.galleryCheckImageInputs(4);
		edit.galleryClickOnSelectButton();
		edit.gallerySetSliderPosition(2);
		edit.galleryClickOnFinishButton();
		edit.verifyObjectInEditMode("gallery-slider");
		edit.clickOnPreviewButton();
		edit.verifyTheObjectOnThePreview("slider");
		WikiArticlePageObject article = edit.clickOnPublishButtonInPreviewMode();
		article.verifyObjectOnThePage("slider");	
	}
	
	
	@Test(groups={"ArticleFeaturesCRUDAdmin_008", "ArticleFeaturesCRUDAdmin"})
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving	
	// Test Case 009 Adding sliders to an article in edit mode
	public void ArticleCRUDAdmin_008_ModifySlider()
	{
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);		
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
		edit.clickOnAddObjectButton("Slider");
		edit.waitForObjectModalAndClickAddAphoto("GallerySlider");
		edit.searchImageInLightBox("image");
		edit.galleryCheckImageInputs(4);
		edit.galleryClickOnSelectButton();
		edit.gallerySetSliderPosition(2);//Vertical
		edit.galleryClickOnFinishButton();
		edit.verifyObjectInEditMode("gallery-slider");
		edit.clickOnPreviewButton();
		edit.verifyTheObjectOnThePreview("slider");//publish 
		WikiArticlePageObject article = edit.clickOnPublishButtonInPreviewMode();
		article.verifyObjectOnThePage("slider");
		article.verifySliderThumbnailsPosition("vertical");
		edit = article.edit();
		edit.clickModifyButtonSlider();
		edit.waitForObjectModalAndClickAddAphoto("GallerySlider");
		edit.searchImageInLightBox("image");
		edit.galleryCheckImageInputs(8);
		edit.galleryClickOnSelectButton();
		edit.gallerySetSliderPosition(1);//Horizontal
		edit.galleryClickOnFinishButton();
		edit.verifyObjectInEditMode("gallery-slider");
		article = edit.clickOnPublishButton();
		article.verifyObjectOnThePage("slider");
		article.verifySliderThumbnailsPosition("horizontal");	
	}
	
	@Test(groups={"ArticleFeaturesCRUDAdmin_009", "ArticleFeaturesCRUDAdmin"})
	public void ArticleCRUDAdmin_009_DeleteSlider()
	{
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
		edit.clickOnAddObjectButton("Slider");
		edit.waitForObjectModalAndClickAddAphoto("GallerySlider");
		edit.searchImageInLightBox("image");
		edit.galleryCheckImageInputs(4);
		edit.galleryClickOnSelectButton();
		edit.gallerySetSliderPosition(2);//Vertical
		edit.galleryClickOnFinishButton();
		edit.verifyObjectInEditMode("gallery-slider");
		edit.clickOnPreviewButton();
		edit.verifyTheObjectOnThePreview("slider");//publish 
		WikiArticlePageObject article = edit.clickOnPublishButtonInPreviewMode();
		article.verifyObjectOnThePage("slider");
		article.verifySliderThumbnailsPosition("vertical");
		edit = article.edit();
		edit.clickRemoveButtonSlider();
		edit.clickOkButton();
		edit.verifyTheSliderNotOnTheArticleEditMode();
		article = edit.clickOnPublishButton();
		article.verifySlideshowNotOnThePage();
	}
	
	@Test(groups={"ArticleFeaturesCRUDAdmin_010", "ArticleFeaturesCRUDAdmin", "Smoke"})
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving	
	// Test Case 010 Adding videos to an article in edit mode
	public void ArticleCRUDAdmin_010_AddingVideo()
	{
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);		
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
		VetAddVideoComponentObject vetAddVideo = edit.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddVideo.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.setCaption(PageContent.caption);
		vetOptions.submit();
		edit.verifyVideoInEditMode(PageContent.caption);
		edit.clickOnPreviewButton();
		edit.verifyTheVideoOnThePreview();
		WikiArticlePageObject article = edit.clickOnPublishButtonInPreviewMode();
		article.verifyVideoOnThePage();
	}	
	
	@Test(groups={"ArticleFeaturesCRUDAdmin_011", "ArticleFeaturesCRUDAdmin"})
	public void ArticleCRUDAdmin_011_ModifyVideo()
	{
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
		VetAddVideoComponentObject vetAddVideo = edit.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddVideo.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.setCaption(PageContent.caption);
		vetOptions.submit();
		edit.verifyVideoInEditMode(PageContent.caption);
		edit.clickOnPreviewButton();
		edit.verifyTheVideoOnThePreview();
		WikiArticlePageObject article = edit.clickOnPublishButtonInPreviewMode();
		article.verifyVideoOnThePage();
		edit = article.edit();
		vetOptions = edit.clickModifyButtonVideo();
		vetOptions.setCaption(PageContent.caption2);
		vetOptions.update();
		edit.verifyVideoInEditMode(PageContent.caption2);
		article = edit.clickOnPublishButton();
		article.verifyVideoOnThePage();
	}
	
	@Test(groups={"ArticleFeaturesCRUDAdmin_012", "ArticleFeaturesCRUDAdmin"})
	public void ArticleCRUDAdmin_012_DeleteVideo()
	{
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);	
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
		VetAddVideoComponentObject vetAddVideo = edit.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddVideo.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.setCaption(PageContent.caption);
		vetOptions.submit();
		edit.verifyVideoInEditMode(PageContent.caption);
		edit.clickOnPreviewButton();
		edit.verifyTheVideoOnThePreview();
		WikiArticlePageObject article = edit.clickOnPublishButtonInPreviewMode();
		article.verifyVideoOnThePage();
		edit = article.edit();
		edit.clickRemoveButtonVideo();
		edit.clickOkButton();
		edit.verifyTheVideoNotOnTheArticleEditMode();
		article = edit.clickOnPublishButton();
		article.verifyVideoNotOnThePage();
	}
	
	@Test(groups={"ArticleFeaturesCRUDAdmin_013", "ArticleFeaturesCRUDAdmin", "Smoke"}) 
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving
	// Test Case 004 Adding images to an article in edit mode
	public void ArticleCRUDAdmin_013_AddingImage()
	{
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
		PhotoAddComponentObject photoAddPhoto = edit.clickPhotoButton();
		PhotoOptionsComponentObject photoOptions = photoAddPhoto.addPhotoFromWiki("image", 1);
		photoOptions.setCaption(PageContent.caption);
		photoOptions.clickAddPhoto();
		edit.verifyThatThePhotoAppears(PageContent.caption);
		edit.clickOnPreviewButton();
		edit.verifyTheImageOnThePreview();
		edit.verifyTheCaptionOnThePreview(PageContent.caption);
		WikiArticlePageObject article = edit.clickOnPublishButtonInPreviewMode();
		article.verifyImageOnThePage();
	}
	
	@Test(groups={"ArticleFeaturesCRUDAdmin_014", "ArticleFeaturesCRUDAdmin"})
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving
	// Test Case 005 Modifying images in an article in edit mode
	public void ArticleCRUDAdmin_014_ModifyImage()
	{
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);	
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
		PhotoAddComponentObject photoAddPhoto = edit.clickPhotoButton();
		PhotoOptionsComponentObject photoOptions = photoAddPhoto.addPhotoFromWiki("image", 1);
		photoOptions.setCaption(PageContent.caption);
		photoOptions.clickAddPhoto();
		edit.verifyThatThePhotoAppears(PageContent.caption);
		photoOptions = edit.clickModifyButtonImage(PageContent.caption);
		photoOptions.setCaption(PageContent.caption2);
		photoOptions.clickAddPhoto();
		edit.verifyThatThePhotoAppears(PageContent.caption2);
		edit.clickOnPreviewButton();
		edit.verifyTheImageOnThePreview();
		edit.verifyTheCaptionOnThePreview(PageContent.caption2);
		WikiArticlePageObject article = edit.clickOnPublishButtonInPreviewMode();
		article.verifyImageOnThePage();
	}
	
	@Test(groups={"ArticleFeaturesCRUDAdmin_015", "ArticleFeaturesCRUDAdmin"})
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving
	// Test Case 005 Modifying images in an article in edit mode
	public void ArticleCRUDAdmin_015_DeleteImage()
	{
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);		
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
		PhotoAddComponentObject photoAddPhoto = edit.clickPhotoButton();
		PhotoOptionsComponentObject photoOptions = photoAddPhoto.addPhotoFromWiki("image", 1);
		photoOptions.setCaption(PageContent.caption);
		photoOptions.clickAddPhoto();
		edit.verifyThatThePhotoAppears(PageContent.caption);
		WikiArticlePageObject article = edit.clickOnPublishButton();
		article.verifyImageOnThePage();
		edit = article.edit();
		edit.clickRemoveButtonImage(PageContent.caption);
		edit.clickOkButton();
		edit.verifyTheImageNotOnTheArticleEditMode();
		article = edit.clickOnPublishButton();
		article.verifyImageNotOnThePage();
	}	
	
//	@Test(groups={"ArticleFeaturesCRUDAdmin_016", "ArticleFeaturesCRUDAdmin"})//, "ArticleFeaturesCRUDAdmin"})
	public void ArticleCRUDAdmin_016_AddTable()
	{
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
		edit.clickOnAddTableButton();
		edit.verifyTableModal();
		edit.clickOKonTableModal();
		edit.verifyTableAppears();
		WikiArticlePageObject article = edit.clickOnPublishButton();
		article.VerifyTheTableOnThePage();	
	}	
	
//	@Test(groups={"ArticleFeaturesCRUDAdmin_017", "ArticleFeaturesCRUDAdmin"})//, "ArticleFeaturesCRUDAdmin"})
	public void ArticleCRUDAdmin_017_EditTable(){
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
		edit.clickOnAddTableButton();
		edit.verifyTableModal();
		edit.clickOKonTableModal();
		edit.verifyTableAppears();
		
		edit.tablePupulateCell(1,1, "first");
		edit.tablePupulateCell(1,2, "second");
		edit.tablePupulateCell(2,1, "third");
		edit.tablePupulateCell(2,2, "fourth");
		edit.tablePupulateCell(3,1, "fifth");
		edit.tablePupulateCell(3,2, "sixth");
		
		edit.tableCheckCellContent(1,1, "first");
		edit.tableCheckCellContent(1,2, "second");
		edit.tableCheckCellContent(2,1, "third");
		edit.tableCheckCellContent(2,2, "fourth");
		edit.tableCheckCellContent(3,1, "fifth");
		edit.tableCheckCellContent(3,2, "sixth");

		edit.tableRightClickOnCell(2, 2);
		edit.tableChooseFromContextMenu(3,2);
		edit.tableCheckTableRowsCount(4);
		WikiArticlePageObject article = edit.clickOnPublishButton();
		article.VerifyTheTableOnThePage();	
		}

//		@Test(groups={"ArticleFeaturesCRUDAdmin_018", "ArticleFeaturesCRUDAdmin"})//, "ArticleFeaturesCRUDAdmin"})
	public void ArticleCRUDAdmin_018_RemoveTable(){	
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
		edit.clickOnAddTableButton();
		edit.verifyTableModal();
		edit.clickOKonTableModal();
		edit.verifyTableAppears();
		edit.tableRightClickOnCell(2, 2);
		edit.tableChooseFromContextMenu(5,0);
		WikiArticlePageObject article = edit.clickOnPublishButton();	
		}
		
		
		
}