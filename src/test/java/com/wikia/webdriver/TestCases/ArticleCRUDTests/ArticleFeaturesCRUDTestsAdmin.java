package com.wikia.webdriver.TestCases.ArticleCRUDTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
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
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;

public class ArticleFeaturesCRUDTestsAdmin extends TestTemplate
{

	@Test(groups={"ArticleFeaturesCRUDAdmin_001", "ArticleFeaturesCRUDAdmin", "Smoke"})
	public void ArticleCRUDAdmin_001_AddingGallery()
	{
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();

		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
		WikiArticleEditMode edit = article.edit();
		edit.deleteArticleContent();
		GalleryBuilderComponentObject galleryBuiler = edit.clickGalleryButton();
		AddPhotoComponentObject galleryAddPhoto = galleryBuiler.clickAddPhoto();
		galleryAddPhoto.search("image");
		galleryAddPhoto.choosePhotos(4);
		galleryAddPhoto.clickSelect();
		galleryBuiler.adjustPosition("Center");
		galleryBuiler.adjustColumns("2");
		galleryBuiler.adjustSpacing("Small");
		galleryBuiler.adjustOrientation(Orientation.landscape);
		galleryBuiler.clickFinish();
		edit.verifyObjectInEditMode("gallery");
		edit.clickOnPreviewButton();
		edit.verifyTheObjectOnThePreview("gallery");
		edit.clickOnPublishButtonPreview();
		article.verifyObjectOnThePage("gallery");
		galleryBuiler = article.clickAddPhotoToGallery();
		galleryAddPhoto = galleryBuiler.clickAddPhoto();
		galleryAddPhoto.search("image");
		galleryAddPhoto.choosePhotos(2);
		galleryAddPhoto.clickSelect();
		galleryBuiler.adjustPosition("Center");
		galleryBuiler.adjustColumns("2");
		galleryBuiler.adjustSpacing("Small");
		galleryBuiler.adjustOrientation(Orientation.landscape);
		galleryBuiler.clickFinish();
		article.verifyObjectOnThePage("gallery");
	}

	@Test(groups={"ArticleFeaturesCRUDAdmin_002", "ArticleFeaturesCRUDAdmin"})
	public void ArticleCRUDAdmin_002_ModifyGallery()
	{
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();

		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
		WikiArticleEditMode edit = article.edit();
		edit.deleteArticleContent();
		GalleryBuilderComponentObject galleryBuiler = edit.clickGalleryButton();
		AddPhotoComponentObject galleryAddPhoto = galleryBuiler.clickAddPhoto();
		galleryAddPhoto.search("image");
		galleryAddPhoto.choosePhotos(4);
		galleryAddPhoto.clickSelect();
		galleryBuiler.adjustPosition("Center");
		galleryBuiler.adjustColumns("2");
		galleryBuiler.adjustSpacing("Small");
		galleryBuiler.adjustOrientation(Orientation.landscape);
		galleryBuiler.clickFinish();
		edit.verifyObjectInEditMode("gallery");
		edit.clickOnPreviewButton();
		edit.verifyTheObjectOnThePreview("gallery");
		edit.clickOnPublishButtonPreview();
		article.verifyObjectOnThePage("gallery");
		article.verifyGalleryPosion("center");
		article.edit();
		galleryBuiler = edit.clickModifyButtonGallery();
		galleryAddPhoto = galleryBuiler.clickAddPhoto();
		galleryAddPhoto.search("image");
		galleryAddPhoto.choosePhotos(4);
		galleryAddPhoto.clickSelect();
		galleryBuiler.adjustPosition("Right");
		galleryBuiler.adjustColumns("3");
		galleryBuiler.adjustSpacing("Medium");
		galleryBuiler.adjustOrientation(Orientation.landscape);
		galleryBuiler.clickFinish();
		edit.verifyObjectInEditMode("gallery");
		edit.clickOnPublishButton();
		article.verifyGalleryPosion("right");
	}

	@Test(groups={"ArticleFeaturesCRUDAdmin_003", "ArticleFeaturesCRUDAdmin"})
	public void ArticleCRUDAdmin_003_DeleteGallery()
	{
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();

		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
		WikiArticleEditMode edit = article.edit();
		edit.deleteArticleContent();
		GalleryBuilderComponentObject galleryBuiler = edit.clickGalleryButton();
		AddPhotoComponentObject galleryAddPhoto = galleryBuiler.clickAddPhoto();
		galleryAddPhoto.search("image");
		galleryAddPhoto.choosePhotos(4);
		galleryAddPhoto.clickSelect();
		galleryBuiler.adjustPosition("Center");
		galleryBuiler.adjustColumns("2");
		galleryBuiler.adjustSpacing("Small");
		galleryBuiler.adjustOrientation(Orientation.landscape);
		galleryBuiler.clickFinish();
		edit.verifyObjectInEditMode("gallery");
		edit.clickOnPreviewButton();
		edit.verifyTheObjectOnThePreview("gallery");
		edit.clickOnPublishButtonPreview();
		article.verifyObjectOnThePage("gallery");
		article.verifyGalleryPosion("center");
		article.edit();
		edit.clickRemoveButtonGallery();
		edit.clickOkButton();
		edit.verifyTheGalleryNotOnTheArticleEditMode();
		edit.clickOnPublishButton();
		article.verifyGalleryNotOnThePage();
	}

	@Test(groups={"ArticleFeaturesCRUDAdmin_004", "ArticleFeaturesCRUDAdmin", "Smoke"})
	public void ArticleCRUDAdmin_004_AddingSlideshow()
	{
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();

		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
		WikiArticleEditMode edit = article.edit();
		edit.deleteArticleContent();
		SlideshowBuilderComponentObject slideshowBuilder = edit.clickSlideshowButton();
		AddPhotoComponentObject slideshowAddPhoto = slideshowBuilder.clickAddPhoto();
		slideshowAddPhoto.search("image");
		slideshowAddPhoto.choosePhotos(4);
		slideshowAddPhoto.clickSelect();
		slideshowBuilder.adjustPosition(Positions.Center);
		slideshowBuilder.clickFinish();
		edit.verifyObjectInEditMode("slideshow");
		edit.clickOnPreviewButton();
		edit.verifyTheObjectOnThePreview("slideshow");
		edit.clickOnPublishButtonInPreviewMode();
		article.verifyObjectOnThePage("slideshow");
		slideshowBuilder = article.clickAddPhotoToSlideshow();
		slideshowAddPhoto = slideshowBuilder.clickAddPhoto();
		slideshowAddPhoto.search("image");
		slideshowAddPhoto.choosePhotos(4);
		slideshowAddPhoto.clickSelect();
		slideshowBuilder.adjustPosition(Positions.Center);
		slideshowBuilder.clickFinish();
		article.verifyObjectOnThePage("slideshow");
	}

	@Test(groups={"ArticleFeaturesCRUDAdmin_009", "ArticleFeaturesCRUDAdmin"})
	public void ArticleCRUDAdmin_005_ModifySlideshow()
	{
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();

		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
		WikiArticleEditMode edit = article.edit();
		edit.deleteArticleContent();
		SlideshowBuilderComponentObject slideshowBuilder = edit.clickSlideshowButton();
		AddPhotoComponentObject slideshowAddPhoto = slideshowBuilder.clickAddPhoto();
		slideshowAddPhoto.search("image");
		slideshowAddPhoto.choosePhotos(4);
		slideshowAddPhoto.clickSelect();
		slideshowBuilder.adjustPosition(Positions.Center);
		slideshowBuilder.clickFinish();
		edit.verifyObjectInEditMode("slideshow");
		edit.clickOnPreviewButton();
		edit.verifyTheObjectOnThePreview("slideshow");
		edit.clickOnPublishButtonInPreviewMode();
		article.verifyObjectOnThePage("slideshow");
		article.verifySlideshowPosition("center");
		edit = article.edit();
		slideshowBuilder = edit.clickModifyButtonSlideshow();;
		slideshowAddPhoto = slideshowBuilder.clickAddPhoto();
		slideshowAddPhoto.search("image");
		slideshowAddPhoto.choosePhotos(8);
		slideshowAddPhoto.clickSelect();
		slideshowBuilder.adjustPosition(Positions.Right);
		slideshowBuilder.clickFinish();
		edit.verifyObjectInEditMode("slideshow");
		article = edit.clickOnPublishButton();
		article.verifyObjectOnThePage("slideshow");
		article.verifySlideshowPosition("right");
	}

	@Test(groups={"ArticleFeaturesCRUDAdmin_006", "ArticleFeaturesCRUDAdmin"})
	public void ArticleCRUDAdmin_006_DeleteSlideshow()
	{
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();

		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
		WikiArticleEditMode edit = article.edit();
		edit.deleteArticleContent();
		SlideshowBuilderComponentObject slideshowBuilder = edit.clickSlideshowButton();
		AddPhotoComponentObject slideshowAddPhoto = slideshowBuilder.clickAddPhoto();
		slideshowAddPhoto.search("image");
		slideshowAddPhoto.choosePhotos(4);
		slideshowAddPhoto.clickSelect();
		slideshowBuilder.adjustPosition(Positions.Center);
		slideshowBuilder.clickFinish();
		edit.verifyObjectInEditMode("slideshow");
		edit.clickOnPreviewButton();
		edit.verifyTheObjectOnThePreview("slideshow");
		edit.clickOnPublishButtonInPreviewMode();
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
	public void ArticleCRUDAdmin_007_AddingSlider()
	{
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();

		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
		WikiArticleEditMode edit = article.edit();
		edit.deleteArticleContent();
		SliderBuilderComponentObject sliderBuilder = edit.clickSliderButton();
		sliderBuilder.selectMenuPosition(MenuPositions.Vertical);
		AddPhotoComponentObject sliderAddPhoto = sliderBuilder.clickAddPhoto();
		sliderAddPhoto.search("image");
		sliderAddPhoto.choosePhotos(4);
		sliderAddPhoto.clickSelect();
		sliderBuilder.clickFinish();
		edit.verifyObjectInEditMode("gallery-slider");
		edit.clickOnPreviewButton();
		edit.verifyTheObjectOnThePreview("slider");
		edit.clickOnPublishButtonInPreviewMode();
		article.verifyObjectOnThePage("slider");
		article.verifySliderThumbnailsPosition("vertical");
	}

	@Test(groups={"ArticleFeaturesCRUDAdmin_008", "ArticleFeaturesCRUDAdmin"})
	public void ArticleCRUDAdmin_008_ModifySlider()
	{
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();

		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
		WikiArticleEditMode edit = article.edit();
		edit.deleteArticleContent();
		SliderBuilderComponentObject sliderBuilder = edit.clickSliderButton();
		sliderBuilder.selectMenuPosition(MenuPositions.Vertical);
		AddPhotoComponentObject sliderAddPhoto = sliderBuilder.clickAddPhoto();
		sliderAddPhoto.search("image");
		sliderAddPhoto.choosePhotos(4);
		sliderAddPhoto.clickSelect();
		sliderBuilder.clickFinish();
		edit.verifyObjectInEditMode("gallery-slider");
		edit.clickOnPreviewButton();
		edit.verifyTheObjectOnThePreview("slider");
		edit.clickOnPublishButtonInPreviewMode();
		article.verifyObjectOnThePage("slider");
		article.verifySliderThumbnailsPosition("vertical");
		edit = article.edit();
		sliderBuilder = edit.clickModifyButtonSlider();
		sliderBuilder.selectMenuPosition(MenuPositions.Horizontal);
		sliderAddPhoto = sliderBuilder.clickAddPhoto();
		sliderAddPhoto.search("image");
		sliderAddPhoto.choosePhotos(8);
		sliderAddPhoto.clickSelect();
		sliderBuilder.clickFinish();
		edit.verifyObjectInEditMode("gallery-slider");
		article = edit.clickOnPublishButton();
		article.verifyObjectOnThePage("slider");
		article.verifySliderThumbnailsPosition("horizontal");
	}

	@Test(groups={"ArticleFeaturesCRUDAdmin_009", "ArticleFeaturesCRUDAdmin"})
	public void ArticleCRUDAdmin_009_DeleteSlider()
	{
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();

		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
		WikiArticleEditMode edit = article.edit();
		edit.deleteArticleContent();
		SliderBuilderComponentObject sliderBuilder = edit.clickSliderButton();
		sliderBuilder.selectMenuPosition(MenuPositions.Vertical);
		AddPhotoComponentObject sliderAddPhoto = sliderBuilder.clickAddPhoto();
		sliderAddPhoto.search("image");
		sliderAddPhoto.choosePhotos(4);
		sliderAddPhoto.clickSelect();
		sliderBuilder.clickFinish();
		edit.verifyObjectInEditMode("gallery-slider");
		edit.clickOnPreviewButton();
		edit.verifyTheObjectOnThePreview("slider");
		edit.clickOnPublishButtonInPreviewMode();
		article.verifyObjectOnThePage("slider");
		article.verifySliderThumbnailsPosition("vertical");
		article.edit();
		edit.clickRemoveButtonSlider();
		edit.clickOkButton();
		edit.verifyTheSliderNotOnTheArticleEditMode();
		edit.clickOnPublishButton();
		article.verifySlideshowNotOnThePage();
	}

	@Test(groups={"ArticleFeaturesCRUDAdmin_010", "ArticleFeaturesCRUDAdmin", "Smoke"})
	public void ArticleCRUDAdmin_010_AddingVideo()
	{
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();

		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
		WikiArticleEditMode edit = article.edit();
		edit.deleteArticleContent();
		VetAddVideoComponentObject vetAddVideo = edit.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddVideo.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.setCaption(PageContent.caption);
		vetOptions.submit();
		edit.verifyVideoInEditMode(PageContent.caption);
		edit.clickOnPreviewButton();
		edit.verifyTheVideoOnThePreview();
		edit.clickOnPublishButtonInPreviewMode();
		article.verifyVideoOnThePage();
	}

	@Test(groups={"ArticleFeaturesCRUDAdmin_011", "ArticleFeaturesCRUDAdmin"})
	public void ArticleCRUDAdmin_011_ModifyVideo()
	{
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();

		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
		WikiArticleEditMode edit = article.edit();
		edit.deleteArticleContent();
		VetAddVideoComponentObject vetAddVideo = edit.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddVideo.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.setCaption(PageContent.caption);
		vetOptions.submit();
		edit.verifyVideoInEditMode(PageContent.caption);
		edit.clickOnPreviewButton();
		edit.verifyTheVideoOnThePreview();
		edit.clickOnPublishButtonInPreviewMode();
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
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();

		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
		WikiArticleEditMode edit = article.edit();
		edit.deleteArticleContent();
		VetAddVideoComponentObject vetAddVideo = edit.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddVideo.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.setCaption(PageContent.caption);
		vetOptions.submit();
		edit.verifyVideoInEditMode(PageContent.caption);
		edit.clickOnPreviewButton();
		edit.verifyTheVideoOnThePreview();
		edit.clickOnPublishButtonInPreviewMode();
		article.verifyVideoOnThePage();
		edit = article.edit();
		edit.clickRemoveButtonVideo();
		edit.clickOkButton();
		edit.verifyTheVideoNotOnTheArticleEditMode();
		article = edit.clickOnPublishButton();
		article.verifyVideoNotOnThePage();
	}

	@Test(groups={"ArticleFeaturesCRUDAdmin_013", "ArticleFeaturesCRUDAdmin", "Smoke"})
	public void ArticleCRUDAdmin_013_AddingImage()
	{
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();

		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
		WikiArticleEditMode edit = article.edit();
		edit.deleteArticleContent();
		PhotoAddComponentObject photoAddPhoto = edit.clickPhotoButton();
		PhotoOptionsComponentObject photoOptions = photoAddPhoto.addPhotoFromWiki("image", 1);
		photoOptions.setCaption(PageContent.caption);
		photoOptions.clickAddPhoto();
		edit.verifyThatThePhotoAppears(PageContent.caption);
		edit.clickOnPreviewButton();
		edit.verifyTheImageOnThePreview();
		edit.verifyTheCaptionOnThePreview(PageContent.caption);
		edit.clickOnPublishButtonInPreviewMode();
		article.verifyImageOnThePage();
	}

	@Test(groups={"ArticleFeaturesCRUDAdmin_014", "ArticleFeaturesCRUDAdmin"})
	public void ArticleCRUDAdmin_014_ModifyImage()
	{
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();

		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
		WikiArticleEditMode edit = article.edit();
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
		edit.clickOnPublishButtonInPreviewMode();
		article.verifyImageOnThePage();
	}

	@Test(groups={"ArticleFeaturesCRUDAdmin_015", "ArticleFeaturesCRUDAdmin"})
	public void ArticleCRUDAdmin_015_DeleteImage()
	{
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();

		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
		WikiArticleEditMode edit = article.edit();
		edit.deleteArticleContent();
		PhotoAddComponentObject photoAddPhoto = edit.clickPhotoButton();
		PhotoOptionsComponentObject photoOptions = photoAddPhoto.addPhotoFromWiki("image", 1);
		photoOptions.setCaption(PageContent.caption);
		photoOptions.clickAddPhoto();
		edit.verifyThatThePhotoAppears(PageContent.caption);
		edit.clickOnPublishButton();
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
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();

		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
		WikiArticleEditMode edit = article.edit();
		edit.deleteArticleContent();
		edit.clickOnAddTableButton();
		edit.verifyTableModal();
		edit.clickOKonTableModal();
		edit.verifyTableAppears();
		edit.clickOnPublishButton();
		article.VerifyTheTableOnThePage();
	}

//	@Test(groups={"ArticleFeaturesCRUDAdmin_017", "ArticleFeaturesCRUDAdmin"})//, "ArticleFeaturesCRUDAdmin"})
	public void ArticleCRUDAdmin_017_EditTable(){
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();

		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
		WikiArticleEditMode edit = article.edit();
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
		edit.clickOnPublishButton();
		article.VerifyTheTableOnThePage();
	}

//	@Test(groups={"ArticleFeaturesCRUDAdmin_018", "ArticleFeaturesCRUDAdmin"})//, "ArticleFeaturesCRUDAdmin"})
	public void ArticleCRUDAdmin_018_RemoveTable(){
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();

		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
		WikiArticleEditMode edit = article.edit();
		edit.deleteArticleContent();
		edit.clickOnAddTableButton();
		edit.verifyTableModal();
		edit.clickOKonTableModal();
		edit.verifyTableAppears();
		edit.tableRightClickOnCell(2, 2);
		edit.tableChooseFromContextMenu(5,0);
		edit.clickOnPublishButton();
	}

	@Test(groups={"ArticleFeaturesCRUDAdmin_018", "ArticleFeaturesCRUDAdmin"})
	public void ArticleCRUDAdmin_011_VerifyingImagesPositionWikiText()
	{
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		wiki.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
		WikiArticleEditMode edit = article.createNewDefaultArticle();
		edit.deleteArticleContent();
		PhotoAddComponentObject photoAddPhoto = edit.clickPhotoButton();
		PhotoOptionsComponentObject photoOptions = photoAddPhoto.addPhotoFromWiki("image", 1);
		photoOptions.setCaption(PageContent.caption);
		photoOptions.adjustAlignment(1);
		photoOptions.clickAddPhoto();
		edit.clickOnSourceButton();
		edit.verifyWikiTextInSourceMode("left");
		edit.clickOnVisualButton();
		edit.verifyLeftAlignmentIsSelected();
		edit.clickOnPublishButton();
		article.verifyImageOnThePage();
	}
}