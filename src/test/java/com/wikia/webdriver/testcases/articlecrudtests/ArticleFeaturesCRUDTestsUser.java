package com.wikia.webdriver.testcases.articlecrudtests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.dataprovider.ArticleFeaturesCRUDDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.addphoto.AddPhotoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.addtable.TableBuilderComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.addtable.TableBuilderComponentObject.Alignment;
import com.wikia.webdriver.pageobjectsfactory.componentobject.addtable.TableBuilderComponentObject.Headers;
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
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject.Components;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

@Test(groups = {"ArticleFeaturesCRUDUser"})
public class ArticleFeaturesCRUDTestsUser extends NewTestTemplate {

  private int additionalPropertyValue = 10;

  @Test(groups = {"ArticleFeaturesCRUDUser_001", "Smoke"})
  @Execute(asUser = User.USER)
  public void addGallery() {
    new ArticleContent().clear();

    VisualEditModePageObject visualEditMode = new VisualEditModePageObject(driver).open();
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
    visualEditMode.submitArticle().verifyGallery();
  }

  @Test(groups = {"Smoke"})
  @Execute(asUser = User.USER)
  public void modifyGallery() {
    new ArticleContent().push("<gallery position=\"right\" columns=\"1\" spacing=\"medium\">\n"
                              + "Image010.jpg\n" + "Image009.jpg\n" + "</gallery>");

    VisualEditModePageObject visualEditMode = new VisualEditModePageObject(driver).open();
    GalleryBuilderComponentObject galleryBuiler =
        (GalleryBuilderComponentObject) visualEditMode.modifyComponent(Components.GALLERY);
    AddPhotoComponentObject galleryAddPhoto = galleryBuiler.clickAddPhoto();
    galleryAddPhoto.search("image");
    galleryAddPhoto.choosePhotos(2);
    galleryAddPhoto.clickSelect();
    galleryBuiler.adjustPosition(PositionsGallery.RIGHT);
    galleryBuiler.adjustColumns("3");
    galleryBuiler.adjustSpacing(SpacingGallery.MEDIUM);
    galleryBuiler.adjustOrientation(Orientation.PORTRAIT);
    galleryBuiler.clickFinish();
    visualEditMode.verifyGallery();
    visualEditMode.submitArticle().verifyGallery();
  }

  @Test(groups = {"ArticleFeaturesCRUDUser_002"})
  @Execute(asUser = User.USER)
  public void deleteGallery() {
    new ArticleContent().push("<gallery position=\"right\" columns=\"2\" spacing=\"medium\">\n"
                              + "Image010.jpg\n" + "Image009.jpg\n" + "Image008.jpg\n"
                              + "Image007.jpg\n"
                              + "</gallery>");

    VisualEditModePageObject visualEditMode = new VisualEditModePageObject(driver).open();
    visualEditMode.removeComponent(Components.GALLERY);
    visualEditMode.verifyComponentRemoved(Components.GALLERY);
  }

  @Test(groups = {"ArticleFeaturesCRUDUser_003"})
  @Execute(asUser = User.USER)
  public void ArticleFeaturesCRUDUser_003_addModifySlideshow() {
    ArticlePageObject article = new ArticlePageObject(driver).open("addModifySlideshow");
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

  @Test(groups = {"ArticleFeaturesCRUDUser_004"})
  @Execute(asUser = User.USER)
  public void ArticleFeaturesCRUDUser_004_addDeleteSlideshow() {
    ArticlePageObject article = new ArticlePageObject(driver).openRandomArticle(wikiURL);
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

  @Test(groups = {"ArticleFeaturesCRUDUser_005"})
  @Execute(asUser = User.USER)
  public void ArticleFeaturesCRUDUser_005_addModifySlider() {
    ArticlePageObject article = new ArticlePageObject(driver).openRandomArticle(wikiURL);
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

  @Test(groups = {"ArticleFeaturesCRUDUser_006"})
  @Execute(asUser = User.USER)
  public void ArticleFeaturesCRUDUser_006_addDeleteSlider() {
    ArticlePageObject article = new ArticlePageObject(driver).openRandomArticle(wikiURL);
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

  @Test(groups = {"ArticleFeaturesCRUDUser_007", "Media"})
  @Execute(asUser = User.USER)
  public void ArticleFeaturesCRUDUser_007_addModifyVideo() {
    new ArticleContent().push(PageContent.ARTICLE_TEXT);

    ArticlePageObject article = new ArticlePageObject(driver).open();
    VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
    visualEditMode.clearContent();
    VetAddVideoComponentObject vetAddVideo = visualEditMode.clickVideoButton();
    VetOptionsComponentObject vetOptions =
        vetAddVideo.addVideoByUrl(VideoContent.YOUTUBE_VIDEO_URL);
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

  @Test(groups = {"ArticleFeaturesCRUDUser_008", "Smoke5", "Media"})
  @Execute(asUser = User.USER, onWikia = "mobileregressiontesting")
  public void ArticleFeaturesCRUDUser_008_addDeleteVideo() {
    new ArticleContent().clear();

    VisualEditModePageObject visualEditMode = new VisualEditModePageObject(driver).open();
    visualEditMode.clearContent();
    VetAddVideoComponentObject vetAddVideo = visualEditMode.clickVideoButton();
    VetOptionsComponentObject vetOptions =
        vetAddVideo.addVideoByUrl(VideoContent.YOUTUBE_VIDEO_URL);
    vetOptions.setCaption(PageContent.CAPTION);
    vetOptions.submit();
    visualEditMode.verifyVideo();
    ArticlePageObject article = visualEditMode.submitArticle();
    article.verifyVideo();
    article.editArticleInRTEUsingDropdown();
    visualEditMode.removeComponent(Components.VIDEO);
    visualEditMode.verifyComponentRemoved(Components.VIDEO);
  }

  @Test(groups = {"ArticleFeaturesCRUDUser_009", "Smoke4"})
  @Execute(asUser = User.USER)
  public void modifyImage() {
    new ArticleContent().push("[[File:Image010.jpg|thumb|QAWebdriverCaption1]]");

    VisualEditModePageObject visualEditMode = new VisualEditModePageObject(driver).open();
    visualEditMode.modifyComponent(Components.PHOTO);
    PhotoOptionsComponentObject photoOptions = new PhotoOptionsComponentObject(driver);
    photoOptions.setCaption(PageContent.CAPTION2);
    photoOptions.clickAddPhoto();
    visualEditMode.verifyPhoto();
    visualEditMode.submitArticle().verifyPhoto();
  }

  @Test(groups = {"ArticleFeaturesCRUDUser_010", "Smoke1"})
  @Execute(asUser = User.USER)
  public void addImage() {
    new ArticleContent().clear();

    VisualEditModePageObject visualEditMode = new VisualEditModePageObject(driver).open();
    PhotoAddComponentObject photoAddPhoto = visualEditMode.clickPhotoButton();
    PhotoOptionsComponentObject photoOptions = photoAddPhoto.addPhotoFromWiki("image", 1);
    photoOptions.setCaption(PageContent.CAPTION);
    photoOptions.clickAddPhoto();
    visualEditMode.verifyPhoto();
    visualEditMode.submitArticle();
  }

  @Test
  @Execute(asUser = User.USER)
  public void deleteImage() {
    new ArticleContent().push("[[File:Image009.jpg|thumb|QAWebdriverCaption1]]");

    VisualEditModePageObject visualEditMode = new VisualEditModePageObject(driver).open();
    visualEditMode.removeComponent(Components.PHOTO);
    visualEditMode.verifyComponentRemoved(Components.PHOTO);
  }

  @Test(dataProviderClass = ArticleFeaturesCRUDDataProvider.class,
      dataProvider = "getTableProperties", groups = {"ArticleFeaturesCRUDUser_011"})
  @Execute(asUser = User.USER)
  public void ArticleFeaturesCRUDUser_011_addingTable(int border, int width, int height,
                                                      int cellspacing, int cellpadding,
                                                      Alignment alignment) {
    ArticlePageObject article = new ArticlePageObject(driver).openRandomArticle(wikiURL);
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

  @Test(dataProviderClass = ArticleFeaturesCRUDDataProvider.class,
      dataProvider = "getTableProperties", groups = {"ArticleFeaturesCRUDUser_012"},
      enabled = false)
  @Execute(asUser = User.USER)
  public void ArticleFeaturesCRUDUser_012_modifyTable(int border, int width, int height,
                                                      int cellspacing, int cellpadding,
                                                      Alignment alignment) {
    ArticlePageObject article = new ArticlePageObject(driver).openRandomArticle(wikiURL);
    SourceEditModePageObject sourceEditMode = article.openCurrectArticleSourceMode();
    sourceEditMode.clearSource();
    String table =
        sourceEditMode.buildTablePropertiesContent(border, width, height, cellspacing, cellpadding,
                                                   alignment);
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

  @Test(dataProviderClass = ArticleFeaturesCRUDDataProvider.class,
      dataProvider = "getTableProperties", groups = {"ArticleFeaturesCRUDUser_013"})
  @Execute(asUser = User.USER)
  public void ArticleFeaturesCRUDUser_013_deleteTable(int border, int width, int height,
                                                      int cellspacing, int cellpadding,
                                                      Alignment alignment) {
    ArticlePageObject article = new ArticlePageObject(driver).openRandomArticle(wikiURL);
    SourceEditModePageObject sourceEditMode = article.openCurrectArticleSourceMode();
    sourceEditMode.clearSource();
    String table =
        sourceEditMode.buildTablePropertiesContent(border, width, height, cellspacing, cellpadding,
                                                   alignment);
    sourceEditMode.addContentInSourceMode(table);
    sourceEditMode.submitArticle();
    VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
    visualEditMode.clickDeleteTableButton();
    visualEditMode.submitArticle();
    article.verifyTableRemoved();
  }

  @Test(groups = {"ArticleFeaturesCRUDUser_014"})
  @Execute(asUser = User.USER)
  public void ArticleFeaturesCRUDUser_014_addingImagePlaceholder() {
    new ArticlePageObject(driver).openRandomArticle(wikiURL);
    String articleTitle = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    VisualEditModePageObject visualEditMode =
        new WikiBasePageObject(driver).goToArticleDefaultContentEditPage(wikiURL, articleTitle);
    ArticlePageObject article = visualEditMode.submitArticle();
    article.verifyArticleTitle(articleTitle);
    PhotoAddComponentObject photoAddPhoto = article.clickAddImagePlaceholder();
    PhotoOptionsComponentObject photoOptions = photoAddPhoto.addPhotoFromWiki("image", 2);
    photoOptions.setCaption(PageContent.CAPTION);
    photoOptions.clickAddPhoto();
    article.verifyPhoto();
  }
}
