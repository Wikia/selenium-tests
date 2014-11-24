package com.wikia.webdriver.TestCases.ArticleCRUDTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
import com.wikia.webdriver.Common.DataProvider.ArticleFeaturesCRUDDataProvider;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.AddPhoto.AddPhotoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.AddTable.TableBuilderComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.AddTable.TableBuilderComponentObject.Alignment;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.AddTable.TableBuilderComponentObject.Headers;
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
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.SourceEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject.Components;

/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class ArticleFeaturesCRUDTestsUser extends NewTestTemplate {

	Credentials credentials = config.getCredentials();
	private int additionalPropertyValue = 10;

	@Test(groups={"ArticleFeaturesCRUDUser_001", "ArticleFeaturesCRUDUser", "Smoke"})
	public void ArticleFeaturesCRUDUser_001_addModifyGallery() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
		visualEditMode.clearContent();
		GalleryBuilderComponentObject galleryBuiler = visualEditMode.clickGalleryButton();
		AddPhotoComponentObject galleryAddPhoto = galleryBuiler.clickAddPhoto();
		galleryAddPhoto.search("image");
		galleryAddPhoto.choosePhotos(4);
		galleryAddPhoto.clickSelect();
		galleryBuiler.adjustPosition(PositionsGallery.CENTER);
		galleryBuiler.adjustColumns("2");
		galleryBuiler.adjustSpacing(SpacingGallery.SMALL);
		galleryBuiler.adjustOrientation(Orientation.LANDSCAPE);
		galleryBuiler.clickFinish();
		visualEditMode.verifyGallery();
		visualEditMode.submitArticle();
		article.verifyGallery();

		article.editArticleInRTEUsingDropdown();
		visualEditMode.modifyComponent(Components.GALLERY);
		galleryBuiler.clickAddPhoto();
		galleryAddPhoto.search("image");
		galleryAddPhoto.choosePhotos(2);
		galleryAddPhoto.clickSelect();
		galleryBuiler.adjustPosition(PositionsGallery.RIGHT);
		galleryBuiler.adjustColumns("3");
		galleryBuiler.adjustSpacing(SpacingGallery.MEDIUM);
		galleryBuiler.adjustOrientation(Orientation.PORTRAIT);
		galleryBuiler.clickFinish();
		visualEditMode.verifyGallery();
		visualEditMode.submitArticle();
		article.verifyGallery();
	}

	@Test(groups={"ArticleFeaturesCRUDUser_002", "ArticleFeaturesCRUDUser"})
	public void ArticleFeaturesCRUDUser_002_addDeleteGallery() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
		visualEditMode.clearContent();
		GalleryBuilderComponentObject galleryBuiler = visualEditMode.clickGalleryButton();
		AddPhotoComponentObject galleryAddPhoto = galleryBuiler.clickAddPhoto();
		galleryAddPhoto.search("image");
		galleryAddPhoto.choosePhotos(4);
		galleryAddPhoto.clickSelect();
		galleryBuiler.adjustPosition(PositionsGallery.CENTER);
		galleryBuiler.adjustColumns("2");
		galleryBuiler.adjustSpacing(SpacingGallery.SMALL);
		galleryBuiler.adjustOrientation(Orientation.LANDSCAPE);
		galleryBuiler.clickFinish();
		visualEditMode.verifyGallery();
		visualEditMode.submitArticle();
		article.verifyGallery();

		article.editArticleInRTEUsingDropdown();
		visualEditMode.removeComponent(Components.GALLERY);
		visualEditMode.verifyComponentRemoved(Components.GALLERY);
	}

	@Test(groups={"ArticleFeaturesCRUDUser_003", "ArticleFeaturesCRUDUser"})
	public void ArticleFeaturesCRUDUser_003_addModifySlideshow() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
		visualEditMode.clearContent();
		SlideshowBuilderComponentObject slideshowBuilder = visualEditMode.clickSlideshowButton();
		AddPhotoComponentObject slideshowAddPhoto = slideshowBuilder.clickAddPhoto();
		slideshowAddPhoto.search("image");
		slideshowAddPhoto.choosePhotos(4);
		slideshowAddPhoto.clickSelect();
		slideshowBuilder.adjustPosition(Positions.CENTER);
		slideshowBuilder.clickFinish();
		visualEditMode.verifySlideshow();
		visualEditMode.submitArticle();
		article.verifySlideshow();

		article.editArticleInRTEUsingDropdown();
		visualEditMode.modifyComponent(Components.SLIDESHOW);
		slideshowBuilder.clickAddPhoto();
		slideshowAddPhoto.search("image");
		slideshowAddPhoto.choosePhotos(8);
		slideshowAddPhoto.clickSelect();
		slideshowBuilder.adjustPosition(Positions.RIGHT);
		slideshowBuilder.clickFinish();
		visualEditMode.verifySlideshow();
		visualEditMode.submitArticle();
		article.verifySlideshow();
	}

	@Test(groups={"ArticleFeaturesCRUDUser_004", "ArticleFeaturesCRUDUser"})
	public void ArticleFeaturesCRUDUser_004_addDeleteSlideshow() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
		visualEditMode.clearContent();
		SlideshowBuilderComponentObject slideshowBuilder = visualEditMode.clickSlideshowButton();
		AddPhotoComponentObject slideshowAddPhoto = slideshowBuilder.clickAddPhoto();
		slideshowAddPhoto.search("image");
		slideshowAddPhoto.choosePhotos(4);
		slideshowAddPhoto.clickSelect();
		slideshowBuilder.adjustPosition(Positions.CENTER);
		slideshowBuilder.clickFinish();
		visualEditMode.verifySlideshow();
		visualEditMode.submitArticle();
		article.verifySlideshow();

		article.editArticleInRTEUsingDropdown();
		visualEditMode.removeComponent(Components.SLIDESHOW);
		visualEditMode.verifyComponentRemoved(Components.SLIDESHOW);
	}

	@Test(groups={"ArticleFeaturesCRUDUser_005", "ArticleFeaturesCRUDUser"})
	public void ArticleFeaturesCRUDUser_005_addModifySlider() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
		visualEditMode.clearContent();
		SliderBuilderComponentObject sliderBuilder = visualEditMode.clickSliderButton();
		sliderBuilder.selectMenuPosition(MenuPositions.VERTICAL);
		AddPhotoComponentObject sliderAddPhoto = sliderBuilder.clickAddPhoto();
		sliderAddPhoto.search("image");
		sliderAddPhoto.choosePhotos(4);
		sliderAddPhoto.clickSelect();
		sliderBuilder.clickFinish();
		visualEditMode.verifySlider();
		visualEditMode.submitArticle();
		article.verifySlider();

		article.editArticleInRTEUsingDropdown();
		visualEditMode.modifyComponent(Components.SLIDER);
		sliderBuilder.selectMenuPosition(MenuPositions.HORIZONTAL);
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
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
		visualEditMode.clearContent();
		SliderBuilderComponentObject sliderBuilder = visualEditMode.clickSliderButton();
		sliderBuilder.selectMenuPosition(MenuPositions.VERTICAL);
		AddPhotoComponentObject sliderAddPhoto = sliderBuilder.clickAddPhoto();
		sliderAddPhoto.search("image");
		sliderAddPhoto.choosePhotos(4);
		sliderAddPhoto.clickSelect();
		sliderBuilder.clickFinish();
		visualEditMode.verifySlider();
		visualEditMode.submitArticle();
		article.verifySlider();

		article.editArticleInRTEUsingDropdown();
		visualEditMode.removeComponent(Components.SLIDER);
		visualEditMode.verifyComponentRemoved(Components.SLIDER);
	}

	@Test(groups={"ArticleFeaturesCRUDUser_007", "ArticleFeaturesCRUDUser", "Media"})
	public void ArticleFeaturesCRUDUser_007_addModifyVideo() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
		visualEditMode.clearContent();
		VetAddVideoComponentObject vetAddVideo = visualEditMode.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddVideo.addVideoByUrl(VideoContent.YOUTUBE_VIDEO_URL);
		vetOptions.setCaption(PageContent.CAPTION);
		vetOptions.submit();
		visualEditMode.verifyVideo();
		visualEditMode.submitArticle();
		article.verifyVideo();
		article.editArticleInRTEUsingDropdown();
		visualEditMode.modifyComponent(Components.VIDEO);
		vetOptions.setCaption(PageContent.CAPTION2);
		vetOptions.update();
		visualEditMode.verifyVideo();
		visualEditMode.submitArticle();
		article.verifyVideo();
	}

	@Test(groups={"ArticleFeaturesCRUDUser_008", "ArticleFeaturesCRUDUser", "Smoke5", "Media"})
	public void ArticleFeaturesCRUDUser_008_addDeleteVideo() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
		visualEditMode.clearContent();
		VetAddVideoComponentObject vetAddVideo = visualEditMode.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddVideo.addVideoByUrl(VideoContent.YOUTUBE_VIDEO_URL);
		vetOptions.setCaption(PageContent.CAPTION);
		vetOptions.submit();
		visualEditMode.verifyVideo();
		visualEditMode.submitArticle();
		article.verifyVideo();
		article.editArticleInRTEUsingDropdown();
		visualEditMode.removeComponent(Components.VIDEO);
		visualEditMode.verifyComponentRemoved(Components.VIDEO);
	}

	@Test(groups={"ArticleFeaturesCRUDUser_009", "ArticleFeaturesCRUDUser", "Smoke4"})
	public void ArticleFeaturesCRUDUser_009_addingModifyImage() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
		visualEditMode.clearContent();
		PhotoAddComponentObject photoAddPhoto = visualEditMode.clickPhotoButton();
		PhotoOptionsComponentObject photoOptions = photoAddPhoto.addPhotoFromWiki("image", 1);
		photoOptions.setCaption(PageContent.CAPTION);
		photoOptions.clickAddPhoto();
		visualEditMode.verifyPhoto();
		visualEditMode.submitArticle();
		article.editArticleInRTEUsingDropdown();
		visualEditMode.modifyComponent(Components.PHOTO);
		photoOptions.setCaption(PageContent.CAPTION2);
		photoOptions.clickAddPhoto();
		visualEditMode.verifyPhoto();
		visualEditMode.submitArticle();
		article.verifyPhoto();
	}

	@Test(groups={"ArticleFeaturesCRUDUser_010", "ArticleFeaturesCRUDUser", "Smoke1"})
	public void ArticleFeaturesCRUDUser_010_addDeleteImage() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
		visualEditMode.clearContent();
		PhotoAddComponentObject photoAddPhoto = visualEditMode.clickPhotoButton();
		PhotoOptionsComponentObject photoOptions = photoAddPhoto.addPhotoFromWiki("image", 1);
		photoOptions.setCaption(PageContent.CAPTION);
		photoOptions.clickAddPhoto();
		visualEditMode.verifyPhoto();
		visualEditMode.submitArticle();
		article.editArticleInRTEUsingDropdown();
		visualEditMode.removeComponent(Components.PHOTO);
		visualEditMode.verifyComponentRemoved(Components.PHOTO);
	}

	@Test(
		dataProviderClass=ArticleFeaturesCRUDDataProvider.class,
		dataProvider="getTableProperties",
		groups={"ArticleFeaturesCRUDUser_011", "ArticleFeaturesCRUDUser"}
	)
	public void ArticleFeaturesCRUDUser_011_addingTable(
		int border, int width, int height, int cellspacing, int cellpadding, Alignment alignment
	) {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
		visualEditMode.clearContent();
		TableBuilderComponentObject addTable = visualEditMode.clickAddTableButton();
		addTable.verifyAddTableLightbox();
		addTable.typeAmountOfRows(3);
		addTable.typeAmountOfColumns(2);
		addTable.selectHeader(Headers.FIRSTCOLUMN);
		addTable.typeBorderSize(border);
		addTable.selectAlignment(alignment);
		addTable.typeWidth(width);
		addTable.typeHeight(height);
		addTable.typeCellSpacing(cellspacing);
		addTable.typeCellPadding(cellpadding);
		addTable.submitTable();
		visualEditMode.submitArticle();
		article.verifyTableBorder(border);
		article.verifyTableCellspacing(cellspacing);
		article.verifyTableCellpadding(cellpadding);
		article.verifyTableAlignment(alignment);
		article.verifyTableSize(width, height);
	}

	@Test(
		dataProviderClass=ArticleFeaturesCRUDDataProvider.class,
		dataProvider="getTableProperties",
		groups={"ArticleFeaturesCRUDUser_012", "ArticleFeaturesCRUDUser"},
		enabled = false
	)
	public void ArticleFeaturesCRUDUser_012_modifyTable(
		int border, int width, int height, int cellspacing, int cellpadding, Alignment alignment
	) {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		SourceEditModePageObject sourceEditMode = article.openCurrectArticleSourceMode();
		sourceEditMode.clearSource();
		String table = sourceEditMode.buildTablePropertiesContent(
			border, width, height, cellspacing, cellpadding, alignment
		);
		sourceEditMode.addContent(table);
		sourceEditMode.submitArticle();
		article.verifyTableBorder(border);
		article.verifyTableCellspacing(cellspacing);
		article.verifyTableCellpadding(cellpadding);
		VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
		visualEditMode.clickPropertiesTableButton();
		TableBuilderComponentObject addTable = new TableBuilderComponentObject(driver);
		addTable.typeBorderSize(border + additionalPropertyValue);
		addTable.typeCellSpacing(cellspacing + additionalPropertyValue);
		addTable.typeCellPadding(cellpadding + additionalPropertyValue);
		addTable.submitTable();
		visualEditMode.submitArticle();
		article.verifyTableBorder(border + additionalPropertyValue);
		article.verifyTableCellspacing(cellspacing + additionalPropertyValue);
		article.verifyTableCellpadding(cellpadding + additionalPropertyValue);
	}

	@Test(
		dataProviderClass=ArticleFeaturesCRUDDataProvider.class,
		dataProvider="getTableProperties",
		groups={"ArticleFeaturesCRUDUser_013", "ArticleFeaturesCRUDUser"})
	public void ArticleFeaturesCRUDUser_013_deleteTable(
		int border, int width, int height, int cellspacing, int cellpadding, Alignment alignment
	) {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		SourceEditModePageObject sourceEditMode = article.openCurrectArticleSourceMode();
		sourceEditMode.clearSource();
		String table = sourceEditMode.buildTablePropertiesContent(
			border, width, height, cellspacing, cellpadding, alignment
		);
		sourceEditMode.addContent(table);
		sourceEditMode.submitArticle();
		VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
		visualEditMode.clickDeleteTableButton();
		visualEditMode.submitArticle();
		article.verifyTableRemoved();
	}

	@Test(groups={"ArticleFeaturesCRUDUser_014", "ArticleFeaturesCRUDUser"})
	public void ArticleFeaturesCRUDUser_014_addingImagePlaceholder_MAIN_1740() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		base.openRandomArticle(wikiURL);
		String articleTitle = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
		VisualEditModePageObject visualEditMode = base.goToArticleDefaultContentEditPage(wikiURL, articleTitle);
		ArticlePageObject article  = visualEditMode.submitArticle();
		article.verifyArticleTitle(articleTitle);
		PhotoAddComponentObject photoAddPhoto = article.clickAddImagePlaceholder();
		PhotoOptionsComponentObject photoOptions = photoAddPhoto.addPhotoFromWiki("image", 2);
		photoOptions.setCaption(PageContent.CAPTION);
		photoOptions.clickAddPhoto();
		article.verifyPhoto();
	}
}
