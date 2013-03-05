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
//		CommonFunctions.logOut(Properties.userNameStaff, driver);
//		CommonFunctions.logIn(Properties.userNameStaff, Properties.passwordStaff);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		String cookieName = CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
//		edit.clickOnVisualButton();
		edit.clickOnAddObjectButton("Gallery");
		edit.waitForObjectModalAndClickAddAphoto("Gallery");
		edit.searchImageInLightBox("image");
		edit.galleryCheckImageInputs(4);
		edit.galleryClickOnSelectButton();
		edit.gallerySetPositionGallery("Center");//error!!!
		edit.gallerySetPhotoOrientation(2);
		edit.galleryClickOnFinishButton();
		edit.verifyObjectInEditMode("gallery");
		edit.clickOnPreviewButton();
		edit.verifyTheObjectOnThePreview("gallery");
		WikiArticlePageObject article = edit.clickOnPublishButtonPreview();
		article.verifyObjectOnThePage("gallery");
		CommonFunctions.logoutCookie(cookieName);
	}
	
	@Test(groups={"ArticleFeaturesCRUDAdmin_002", "ArticleFeaturesCRUDAdmin"}) 
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving	
	// Test Case 007  Adding galleries to an article in edit mode
	public void ArticleCRUDAdmin_002_ModifyGallery()
	{
//		CommonFunctions.logOut(Properties.userNameStaff, driver);
//		CommonFunctions.logIn(Properties.userNameStaff, Properties.passwordStaff);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		String cookieName = CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
//		edit.clickOnVisualButton();
		edit.clickOnAddObjectButton("Gallery");
		edit.waitForObjectModalAndClickAddAphoto("Gallery");
		edit.searchImageInLightBox("image");
		edit.galleryCheckImageInputs(4);
		edit.galleryClickOnSelectButton();
		edit.gallerySetPositionGallery("Center");//error!!!
		edit.gallerySetPhotoOrientation(2);
		edit.galleryClickOnFinishButton();
		edit.verifyObjectInEditMode("gallery");
		edit.clickOnPreviewButton();
		edit.verifyTheObjectOnThePreview("gallery");
		WikiArticlePageObject article = edit.clickOnPublishButtonPreview();
		article.verifyObjectOnThePage("gallery");
		article.verifyGalleryPosion("center");
		edit = article.edit();
		edit.clickModifyButtonGallery();
		edit.waitForObjectModalAndClickAddAphoto("Gallery");
		edit.searchImageInLightBox("image");
		edit.galleryCheckImageInputs(8);
		edit.galleryClickOnSelectButton();
		edit.gallerySetPositionGallery("Right");
		edit.gallerySetPhotoOrientation(3);
		edit.galleryClickOnFinishButton();
		edit.verifyObjectInEditMode("gallery");
		article = edit.clickOnPublishButton();
		article.verifyGalleryPosion("right");
		CommonFunctions.logoutCookie(cookieName);
	}
	
	@Test(groups={"ArticleFeaturesCRUDAdmin_003", "ArticleFeaturesCRUDAdmin"}) 
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving	
	// Test Case 007  Adding galleries to an article in edit mode
	public void ArticleCRUDAdmin_003_DeleteGallery()
	{
//		CommonFunctions.logOut(Properties.userNameStaff, driver);
//		CommonFunctions.logIn(Properties.userNameStaff, Properties.passwordStaff);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);		
		wiki.openWikiPage();
		String cookieName = CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
//		edit.clickOnVisualButton();
		edit.clickOnAddObjectButton("Gallery");
		edit.waitForObjectModalAndClickAddAphoto("Gallery");
		edit.searchImageInLightBox("image");
		edit.galleryCheckImageInputs(4);
		edit.galleryClickOnSelectButton();
		edit.gallerySetPositionGallery("Center");//error!!!
		edit.gallerySetPhotoOrientation(2);
		edit.galleryClickOnFinishButton();
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
		CommonFunctions.logoutCookie(cookieName);
	}
	
	@Test(groups={"ArticleFeaturesCRUDAdmin_004", "ArticleFeaturesCRUDAdmin", "Smoke"})
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving	
	// Test Case 008 Adding slideshows to an article in edit mode
	public void ArticleCRUDAdmin_004_AddingSlideshow()
	{
//		CommonFunctions.logOut(Properties.userNameStaff, driver);
//		CommonFunctions.logIn(Properties.userNameStaff, Properties.passwordStaff);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);		
		wiki.openWikiPage();
		String cookieName = CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
//		edit.clickOnVisualButton();
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
		CommonFunctions.logoutCookie(cookieName);
	}
	
	@Test(groups={"ArticleFeaturesCRUDAdmin_009", "ArticleFeaturesCRUDAdmin"})
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving	
	// Test Case 008 Adding slideshows to an article in edit mode
	public void ArticleCRUDAdmin_005_ModifySlideshow()
	{
//		CommonFunctions.logOut(Properties.userNameStaff, driver);
//		CommonFunctions.logIn(Properties.userNameStaff, Properties.passwordStaff);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);	
		wiki.openWikiPage();
		String cookieName = CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
//		edit.clickOnVisualButton();
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
		CommonFunctions.logoutCookie(cookieName);
	}
	
	@Test(groups={"ArticleFeaturesCRUDAdmin_006", "ArticleFeaturesCRUDAdmin"})
	public void ArticleCRUDAdmin_006_DeleteSlideshow()
	{
//		CommonFunctions.logOut(Properties.userNameStaff, driver);
//		CommonFunctions.logIn(Properties.userNameStaff, Properties.passwordStaff);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		String cookieName = CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
//		edit.clickOnVisualButton();
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
		CommonFunctions.logoutCookie(cookieName);
	}
	
	
	
	@Test(groups={"ArticleFeaturesCRUDAdmin_007", "ArticleFeaturesCRUDAdmin", "Smoke"})
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving	
	// Test Case 009 Adding sliders to an article in edit mode
	public void ArticleCRUDAdmin_007_AddingSlider()
	{
//		CommonFunctions.logOut(Properties.userNameStaff, driver);
//		CommonFunctions.logIn(Properties.userNameStaff, Properties.passwordStaff);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);		
		wiki.openWikiPage();
		String cookieName = CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
//		edit.clickOnVisualButton();
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
		CommonFunctions.logoutCookie(cookieName);	
	}
	
	
	@Test(groups={"ArticleFeaturesCRUDAdmin_008", "ArticleFeaturesCRUDAdmin"})
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving	
	// Test Case 009 Adding sliders to an article in edit mode
	public void ArticleCRUDAdmin_008_ModifySlider()
	{
//		CommonFunctions.logOut(Properties.userNameStaff, driver);
//		CommonFunctions.logIn(Properties.userNameStaff, Properties.passwordStaff);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);		
		wiki.openWikiPage();
		String cookieName = CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
//		edit.clickOnVisualButton();
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
		CommonFunctions.logoutCookie(cookieName);	
	}
	
	@Test(groups={"ArticleFeaturesCRUDAdmin_009", "ArticleFeaturesCRUDAdmin"})
	public void ArticleCRUDAdmin_009_DeleteSlider()
	{
//		CommonFunctions.logOut(Properties.userNameStaff, driver);
//		CommonFunctions.logIn(Properties.userNameStaff, Properties.passwordStaff);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		String cookieName = CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
//		edit.clickOnVisualButton();
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
		CommonFunctions.logoutCookie(cookieName);
	}
	
	@Test(groups={"ArticleFeaturesCRUDAdmin_010", "ArticleFeaturesCRUDAdmin", "Smoke"})
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving	
	// Test Case 010 Adding videos to an article in edit mode
	public void ArticleCRUDAdmin_010_AddingVideo()
	{
//		CommonFunctions.logOut(Properties.userNameStaff, driver);
//		CommonFunctions.logIn(Properties.userNameStaff, Properties.passwordStaff);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);		
		wiki.openWikiPage();
		String cookieName = CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
//		edit.clickOnVisualButton();
		edit.clickOnAddObjectButton("Video");
		edit.waitForVideoModalAndTypeVideoURL(VideoContent.youtubeVideoURL);
		edit.clickVideoNextButton();
		edit.waitForVideoDialog();
		edit.typeVideoCaption(PageContent.caption);
		edit.clickAddAvideo();
		edit.waitForSuccesDialogAndReturnToEditing();
		edit.verifyVideoInEditMode(PageContent.caption);
		edit.clickOnPreviewButton();
		edit.verifyTheVideoOnThePreview();
		WikiArticlePageObject article = edit.clickOnPublishButtonInPreviewMode();
		article.verifyVideoOnThePage();
		CommonFunctions.logoutCookie(cookieName);
	}	
	
	@Test(groups={"ArticleFeaturesCRUDAdmin_011", "ArticleFeaturesCRUDAdmin"})
	public void ArticleCRUDAdmin_011_ModifyVideo()
	{
//		CommonFunctions.logOut(Properties.userNameStaff, driver);
//		CommonFunctions.logIn(Properties.userNameStaff, Properties.passwordStaff);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		String cookieName = CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
//		edit.clickOnVisualButton();
		edit.clickOnAddObjectButton("Video");
		edit.waitForVideoModalAndTypeVideoURL(VideoContent.youtubeVideoURL);
		edit.clickVideoNextButton();
		edit.waitForVideoDialog();
		edit.typeVideoCaption(PageContent.caption);
		edit.clickAddAvideo();
		edit.waitForSuccesDialogAndReturnToEditing();
		edit.verifyVideoInEditMode(PageContent.caption);
		edit.clickOnPreviewButton();
		edit.verifyTheVideoOnThePreview();
		WikiArticlePageObject article = edit.clickOnPublishButtonInPreviewMode();
		article.verifyVideoOnThePage();
		edit = article.edit();
		edit.clickModifyButtonVideo();
//		edit.waitForVideoDialog();
		edit.typeVideoCaption(PageContent.caption2);
		edit.clickAddAvideo();
		edit.verifyVideoInEditMode(PageContent.caption2);
		article = edit.clickOnPublishButton();
		article.verifyVideoOnThePage();
		CommonFunctions.logoutCookie(cookieName);
	}
	
	@Test(groups={"ArticleFeaturesCRUDAdmin_012", "ArticleFeaturesCRUDAdmin"})
	public void ArticleCRUDAdmin_012_DeleteVideo()
	{
//		CommonFunctions.logOut(Properties.userNameStaff, driver);
//		CommonFunctions.logIn(Properties.userNameStaff, Properties.passwordStaff);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);	
		wiki.openWikiPage();
		String cookieName = CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
//		edit.clickOnVisualButton();
		edit.clickOnAddObjectButton("Video");
		edit.waitForVideoModalAndTypeVideoURL(VideoContent.youtubeVideoURL);
		edit.clickVideoNextButton();
		edit.waitForVideoDialog();
		edit.typeVideoCaption(PageContent.caption);
		edit.clickAddAvideo();
		edit.waitForSuccesDialogAndReturnToEditing();
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
		CommonFunctions.logoutCookie(cookieName);
	}
	
	@Test(groups={"ArticleFeaturesCRUDAdmin_013", "ArticleFeaturesCRUDAdmin", "Smoke"}) 
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving
	// Test Case 004 Adding images to an article in edit mode
	public void ArticleCRUDAdmin_013_AddingImage()
	{
//		CommonFunctions.logOut(Properties.userNameStaff, driver);
//		CommonFunctions.logIn(Properties.userNameStaff, Properties.passwordStaff);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		String cookieName = CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
//		edit.clickOnVisualButton();
		edit.clickOnAddObjectButton("Image");
		edit.waitForModalAndClickAddThisPhoto();
		edit.typePhotoCaption(PageContent.caption);
		edit.clickOnAddPhotoButton2();
		edit.verifyThatThePhotoAppears(PageContent.caption);
		edit.clickOnPreviewButton();
		edit.verifyTheImageOnThePreview();
		edit.verifyTheCaptionOnThePreview(PageContent.caption);
		WikiArticlePageObject article = edit.clickOnPublishButtonInPreviewMode();
		article.verifyImageOnThePage();
		CommonFunctions.logoutCookie(cookieName);
	}
	
	@Test(groups={"ArticleFeaturesCRUDAdmin_014", "ArticleFeaturesCRUDAdmin"})
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving
	// Test Case 005 Modifying images in an article in edit mode
	public void ArticleCRUDAdmin_014_ModifyImage()
	{
//		CommonFunctions.logOut(Properties.userNameStaff, driver);
//		CommonFunctions.logIn(Properties.userNameStaff, Properties.passwordStaff);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);	
		wiki.openWikiPage();
		String cookieName = CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
//		edit.clickOnVisualButton();
		edit.clickOnAddObjectButton("Image");
		edit.waitForModalAndClickAddThisPhoto();
		edit.typePhotoCaption(PageContent.caption);
		edit.clickOnAddPhotoButton2();
		edit.verifyThatThePhotoAppears(PageContent.caption);
		edit.clickModifyButtonImage(PageContent.caption);
		edit.typePhotoCaption(PageContent.caption2);
		edit.clickOnAddPhotoButton2();
		edit.verifyThatThePhotoAppears(PageContent.caption2);
		edit.clickOnPreviewButton();
		edit.verifyTheImageOnThePreview();
		edit.verifyTheCaptionOnThePreview(PageContent.caption2);
		WikiArticlePageObject article = edit.clickOnPublishButtonInPreviewMode();
		article.verifyImageOnThePage();
		CommonFunctions.logoutCookie(cookieName);
	}
	
	@Test(groups={"ArticleFeaturesCRUDAdmin_015", "ArticleFeaturesCRUDAdmin"})
//	https://internal.wikia-inc.com/wiki/QA/Core_Features_and_Testing/Manual_Regression_Tests/Image_Serving
	// Test Case 005 Modifying images in an article in edit mode
	public void ArticleCRUDAdmin_015_DeleteImage()
	{
//		CommonFunctions.logOut(Properties.userNameStaff, driver);
//		CommonFunctions.logIn(Properties.userNameStaff, Properties.passwordStaff);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);		
		wiki.openWikiPage();
		String cookieName = CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
//		edit.clickOnVisualButton();
		edit.clickOnAddObjectButton("Image");
		edit.waitForModalAndClickAddThisPhoto();
		edit.typePhotoCaption(PageContent.caption);
		edit.clickOnAddPhotoButton2();
		edit.verifyThatThePhotoAppears(PageContent.caption);
		WikiArticlePageObject article = edit.clickOnPublishButton();
		article.verifyImageOnThePage();
		edit = article.edit();
		edit.clickRemoveButtonImage(PageContent.caption);
		edit.clickOkButton();
		edit.verifyTheImageNotOnTheArticleEditMode();
		article = edit.clickOnPublishButton();
		article.verifyImageNotOnThePage();
		CommonFunctions.logoutCookie(cookieName);
	}	
	
//	@Test(groups={"ArticleFeaturesCRUDAdmin_016", "ArticleFeaturesCRUDAdmin"})//, "ArticleFeaturesCRUDAdmin"})
	public void ArticleCRUDAdmin_016_AddTable()
	{
//		CommonFunctions.logOut(Properties.userNameStaff, driver);
//		CommonFunctions.logIn(Properties.userNameStaff, Properties.passwordStaff);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		String cookieName = CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
//		edit.clickOnVisualButton();
		edit.clickOnAddTableButton();
		edit.verifyTableModal();
		edit.clickOKonTableModal();
		edit.verifyTableAppears();
		WikiArticlePageObject article = edit.clickOnPublishButton();
		article.VerifyTheTableOnThePage();
		CommonFunctions.logoutCookie(cookieName);	
	}	
	
//	@Test(groups={"ArticleFeaturesCRUDAdmin_017", "ArticleFeaturesCRUDAdmin"})//, "ArticleFeaturesCRUDAdmin"})
	public void ArticleCRUDAdmin_017_EditTable()
	{

//		CommonFunctions.logOut(Properties.userName, driver);
//		CommonFunctions.logIn(Properties.userNameStaff, Properties.passwordStaff);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		String cookieName = CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
//		edit.clickOnVisualButton();
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
//		CommonFunctions.logOut(Properties.userName2, driver);
		CommonFunctions.logoutCookie(cookieName);	
		}

//		@Test(groups={"ArticleFeaturesCRUDAdmin_018", "ArticleFeaturesCRUDAdmin"})//, "ArticleFeaturesCRUDAdmin"})
		public void ArticleCRUDAdmin_018_RemoveTable()
		{
//		CommonFunctions.logOut(Properties.userName, driver);
//		CommonFunctions.logIn(Properties.userNameStaff, Properties.passwordStaff);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		String cookieName = CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
//		edit.clickOnVisualButton();
		edit.clickOnAddTableButton();
		edit.verifyTableModal();
		edit.clickOKonTableModal();
		edit.verifyTableAppears();
		edit.tableRightClickOnCell(2, 2);
		edit.tableChooseFromContextMenu(5,0);
		WikiArticlePageObject article = edit.clickOnPublishButton();
//		article.VerifyTheTableOnThePage();
//		CommonFunctions.logOut(Properties.userName2, driver);
		CommonFunctions.logoutCookie(cookieName);	
		}
		
		
		
}