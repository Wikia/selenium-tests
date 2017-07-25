package com.wikia.webdriver.testcases.articlecrudtests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.core.TestContext;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.helpers.User;
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
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject.Components;
import org.testng.annotations.Test;

@Test(groups = {"ArticleFeaturesCRUDUser"})
public class ArticleFeaturesCRUDTestsUser extends NewTestTemplate {

  private int additionalPropertyValue = 10;

  @Test(groups = {"ArticleFeaturesCRUDUser_001", "Smoke"})
  @Execute(asUser = User.USER)
  public void addGallery() {
    new ArticleContent().clear();

    VisualEditModePageObject visualEditMode = new VisualEditModePageObject().open();
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

    VisualEditModePageObject visualEditMode = new VisualEditModePageObject().open();
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

    VisualEditModePageObject visualEditMode = new VisualEditModePageObject().open();
    visualEditMode.removeComponent(Components.GALLERY);
    visualEditMode.verifyComponentRemoved(Components.GALLERY);
  }

  @Test(groups = {"ArticleFeaturesCRUDUser_003"})
  @Execute(asUser = User.USER)
  public void addSlideshow() {
    new ArticleContent().clear();

    VisualEditModePageObject visualEditMode = new VisualEditModePageObject().open();
    SlideshowBuilderComponentObject slideshowBuilder = visualEditMode.clickSlideshowButton();
    AddPhotoComponentObject slideshowAddPhoto = slideshowBuilder.clickAddPhoto();
    slideshowAddPhoto.search("image");
    slideshowAddPhoto.choosePhotos(4);
    slideshowAddPhoto.clickSelect();
    slideshowBuilder.adjustPosition(Positions.CENTER);
    slideshowBuilder.clickFinish();
    visualEditMode.verifySlideshow();
    visualEditMode.submitArticle().verifySlideshow();
  }

  @Test(groups = {"ArticleFeaturesCRUDUser_003"})
  @Execute(asUser = User.USER)
  public void modifySlideshow() {
    new ArticleContent().push(
        "<gallery type=\"slideshow\">\nImage010.jpg\nImage009.jpg\nImage008.jpg\nImage007.jpg\n"
        + "Image010.jpg\nImage009.jpg\nImage008.jpg\nImage007.jpg\nImage006.jpg\nImage005.jpg\n"
        + "Image004.jpg\nImage003.jpg\n</gallery>");

    VisualEditModePageObject visualEditor = new VisualEditModePageObject().open();
    SlideshowBuilderComponentObject
        slideshowBuilder =
        (SlideshowBuilderComponentObject) visualEditor.modifyComponent(
            Components.SLIDESHOW);
    AddPhotoComponentObject slideshowAddPhoto = slideshowBuilder.clickAddPhoto();
    slideshowAddPhoto.search("image");
    slideshowAddPhoto.choosePhotos(8);
    slideshowAddPhoto.clickSelect();
    slideshowBuilder.adjustPosition(Positions.RIGHT);
    slideshowBuilder.clickFinish();
    visualEditor.verifySlideshow();
    visualEditor.submitArticle().verifySlideshow();
  }

  @Test(groups = {"ArticleFeaturesCRUDUser_004"})
  @Execute(asUser = User.USER)
  public void deleteSlideshow() {
    new ArticleContent().push(
        "<gallery type=\"slideshow\">\nImage010.jpg\nImage009.jpg\nImage008.jpg\nImage007.jpg\n"
        + "Image010.jpg\nImage009.jpg\nImage008.jpg\nImage007.jpg\nImage006.jpg\nImage005.jpg\n"
        + "Image004.jpg\nImage003.jpg\n</gallery>");

    VisualEditModePageObject visualEditMode = new VisualEditModePageObject().open();
    visualEditMode.removeComponent(Components.SLIDESHOW);
    visualEditMode.verifyComponentRemoved(Components.SLIDESHOW);
  }

  @Test(groups = {"ArticleFeaturesCRUDUser_005"})
  @Execute(asUser = User.USER)
  public void addSlider() {
    new ArticleContent().clear();

    VisualEditModePageObject visualEditMode = new VisualEditModePageObject().open();
    SliderBuilderComponentObject sliderBuilder = visualEditMode.clickSliderButton();
    sliderBuilder.selectMenuPosition(MenuPositions.VERTICAL);
    AddPhotoComponentObject sliderAddPhoto = sliderBuilder.clickAddPhoto();
    sliderAddPhoto.search("image");
    sliderAddPhoto.choosePhotos(4);
    sliderAddPhoto.clickSelect();
    sliderBuilder.clickFinish();
    visualEditMode.verifySlider();
    visualEditMode.submitArticle().verifySlider();
  }

  @Test(groups = {"ArticleFeaturesCRUDUser_005"})
  @Execute(asUser = User.USER)
  public void modifySlider() {
    new ArticleContent().push("<gallery type=\"slider\" orientation=\"right\">\nImage010.jpg\n"
                              + "Image009.jpg\nImage008.jpg\nImage007.jpg\n</gallery>");

    VisualEditModePageObject visualEditor = new VisualEditModePageObject().open();
    SliderBuilderComponentObject
        sliderBuilder =
        (SliderBuilderComponentObject) visualEditor.modifyComponent(
            Components.SLIDER);
    sliderBuilder.selectMenuPosition(MenuPositions.HORIZONTAL);
    AddPhotoComponentObject sliderAddPhoto = sliderBuilder.clickAddPhoto();
    sliderAddPhoto.search("image");
    sliderAddPhoto.choosePhotos(8);
    sliderAddPhoto.clickSelect();
    sliderBuilder.clickFinish();
    visualEditor.verifySlider();
    visualEditor.submitArticle().verifySlider();
  }

  @Test(groups = {"ArticleFeaturesCRUDUser_006"})
  @Execute(asUser = User.USER)
  public void deleteSlider() {
    new ArticleContent().push("<gallery type=\"slider\" orientation=\"right\">\nImage010.jpg\n"
                              + "Image009.jpg\nImage008.jpg\nImage007.jpg\n</gallery>");

    VisualEditModePageObject visualEditor = new VisualEditModePageObject().open();
    visualEditor.removeComponent(Components.SLIDER);
    visualEditor.verifyComponentRemoved(Components.SLIDER);
  }

  @Test(groups = {"ArticleFeaturesCRUDUser_007", "Media"})
  @Execute(asUser = User.USER)
  @RelatedIssue(issueID = "XW-3797")
  public void addVideo() {
    new ArticleContent().clear();

    VisualEditModePageObject visualEditMode = new VisualEditModePageObject().open();
    VetAddVideoComponentObject vetAddVideo = visualEditMode.clickVideoButton();
    VetOptionsComponentObject vetOptions =
        vetAddVideo.addVideoByUrl(VideoContent.YOUTUBE_VIDEO_URL);
    vetOptions.setCaption(PageContent.CAPTION);
    vetOptions.submit();
    visualEditMode.verifyVideo();
    visualEditMode.submitArticle().verifyVideo();
  }

  @Test(groups = {"ArticleFeaturesCRUDUser_007", "Media"})
  @Execute(asUser = User.USER)
  public void modifyVideo() {
    new ArticleContent()
        .push(
            "[[File:Wikia University - How to Contact Wikia|thumb|right|335 px|QAWebdriverCaption]]");

    VisualEditModePageObject visualEditMode = new VisualEditModePageObject().open();
    VetOptionsComponentObject
        vetOptions =
        (VetOptionsComponentObject) visualEditMode.modifyComponent(Components.VIDEO);
    vetOptions.setCaption(PageContent.CAPTION2);
    vetOptions.update();
    visualEditMode.verifyVideo();
    visualEditMode.submitArticle().verifyVideo();
  }

  @Test(groups = {"ArticleFeaturesCRUDUser_008", "Smoke5", "Media"})
  @Execute(asUser = User.USER, onWikia = "mobileregressiontesting")
  public void deleteVideo() {
    new ArticleContent()
        .push(
            "[[File:Wikia University - How to Contact Wikia|thumb|right|335 px]]QAWebdriverCaption");

    VisualEditModePageObject visualEditMode = new VisualEditModePageObject().open();

    visualEditMode.removeComponent(Components.VIDEO);
    visualEditMode.verifyComponentRemoved(Components.VIDEO);
  }

  @Test(groups = {"ArticleFeaturesCRUDUser_009", "Smoke4"})
  @Execute(asUser = User.USER)
  public void modifyImage() {
    new ArticleContent().push("[[File:Image010.jpg|thumb|QAWebdriverCaption1]]");

    VisualEditModePageObject visualEditMode = new VisualEditModePageObject().open();
    visualEditMode.modifyComponent(Components.PHOTO);
    PhotoOptionsComponentObject photoOptions = new PhotoOptionsComponentObject(driver);
    photoOptions.setCaption(PageContent.CAPTION2);
    photoOptions.clickAddPhoto();
    visualEditMode.verifyPhoto();
    visualEditMode.submitArticle().verifyPhoto();
  }

  @Test(groups = {"ArticleFeaturesCRUDUser_010", "Smoke1"})
  @Execute(asUser = User.USER)
  @InBrowser(browserSize = "1024x720")
  public void addImage() {
    new ArticleContent().clear();

    VisualEditModePageObject visualEditMode = new VisualEditModePageObject().open();
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

    VisualEditModePageObject visualEditMode = new VisualEditModePageObject().open();
    visualEditMode.removeComponent(Components.PHOTO);
    visualEditMode.verifyComponentRemoved(Components.PHOTO);
  }

  @Test(dataProviderClass = ArticleFeaturesCRUDDataProvider.class,
      dataProvider = "getTableProperties", groups = {"ArticleFeaturesCRUDUser_011"})
  @Execute(asUser = User.USER)
  public void addingTable(int border, int width, int height,
                          int cellspacing, int cellpadding,
                          Alignment alignment) {
    new ArticleContent().clear();

    VisualEditModePageObject visualEditMode = new VisualEditModePageObject().open();
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
    ArticlePageObject article = visualEditMode.submitArticle();
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
  public void modifyTable(int border, int width, int height,
                          int cellspacing, int cellpadding,
                          Alignment alignment) {
    new ArticleContent()
        .push(String.format("{| border=\"%d\" cellpadding=\"%d\" cellspacing=\"%d\" "
                            + "class=\"article-table article-table-selected\" "
                            + "style=\"float: %s; height: %dpx; width: %dpx;\"\n"
                            + "! scope=\"row\"|\n|\n|-\n! scope=\"row\"|\n|\n"
                            + "|-\n! scope=\"row\"|\n|\n"
                            + "|}", border, cellpadding, cellspacing, alignment.getAlignment(),
                            height, width));

    VisualEditModePageObject visualEditMode = new VisualEditModePageObject().open();

    visualEditMode.clickPropertiesTableButton();
    TableBuilderComponentObject addTable = new TableBuilderComponentObject(driver);
    addTable.typeBorderSize(border + additionalPropertyValue);
    addTable.typeCellSpacing(cellspacing + additionalPropertyValue);
    addTable.typeCellPadding(cellpadding + additionalPropertyValue);
    addTable.submitTable();
    ArticlePageObject article = visualEditMode.submitArticle();
    article.verifyTableBorder(border + additionalPropertyValue);
    article.verifyTableCellspacing(cellspacing + additionalPropertyValue);
    article.verifyTableCellpadding(cellpadding + additionalPropertyValue);
  }

  @Test(dataProviderClass = ArticleFeaturesCRUDDataProvider.class,
      dataProvider = "getTableProperties", groups = {"ArticleFeaturesCRUDUser_013"})
  @Execute(asUser = User.USER)
  public void deleteTable(int border, int width, int height,
                          int cellspacing, int cellpadding,
                          Alignment alignment) {
    new ArticleContent()
        .push(String.format("{| border=\"%d\" cellpadding=\"%d\" cellspacing=\"%d\" "
                            + "class=\"article-table article-table-selected\" "
                            + "style=\"float: %s; height: %dpx; width: %dpx;\"\n"
                            + "! scope=\"row\"|\n|\n|-\n! scope=\"row\"|\n|\n"
                            + "|-\n! scope=\"row\"|\n|\n"
                            + "|}", border, cellpadding, cellspacing, alignment.getAlignment(),
                            height, width));

    VisualEditModePageObject visualEditMode = new VisualEditModePageObject().open();
    visualEditMode.clickDeleteTableButton();
    visualEditMode.submitArticle().verifyTableRemoved();
  }

  @Test(groups = {"ArticleFeaturesCRUDUser_014"})
  @Execute(asUser = User.USER)
  public void addingImagePlaceholder() {
    new ArticleContent().clear();

    new ArticlePageObject().open();
    VisualEditModePageObject visualEditMode =
        new WikiBasePageObject()
            .goToArticleDefaultContentEditPage(wikiURL, TestContext.getCurrentMethodName());
    ArticlePageObject article = visualEditMode.submitArticle();
    PhotoAddComponentObject photoAddPhoto = article.clickAddImagePlaceholder();
    PhotoOptionsComponentObject photoOptions = photoAddPhoto.addPhotoFromWiki("image", 2);
    photoOptions.setCaption(PageContent.CAPTION);
    photoOptions.clickAddPhoto();
    article.verifyPhoto();
  }
}
