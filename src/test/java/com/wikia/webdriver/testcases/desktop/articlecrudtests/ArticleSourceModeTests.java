package com.wikia.webdriver.testcases.desktop.articlecrudtests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.core.AlertHandler;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.DontRun;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RunOnly;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.helpers.User;
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
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import org.joda.time.DateTime;
import org.testng.annotations.Test;

import java.util.List;

@Execute(asUser = User.SUS_REGULAR_USER3, onWikia = "sustainingtest")
@Test(groups = {"RTE_extended"})
public class ArticleSourceModeTests extends NewTestTemplate {

  @Test(groups = {"RTE_extended_1", "RTE_extended_001"})
  public void RTE_001_Bold() {
    String articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    SourceEditModePageObject source = new SourceEditModePageObject().openArticle(articleName);
    source.clickBold();

    Assertion.assertEquals(source.getSourceContent(), "'''Bold text'''");
  }

  @Test(groups = {"RTE_extended_1", "RTE_extended_002"})
  public void RTE_002_Italic() {
    String articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    SourceEditModePageObject source = new SourceEditModePageObject().openArticle(articleName);
    source.clickItalic();

    Assertion.assertEquals(source.getSourceContent(), "''Italic text''");
  }

  @Test(groups = {"RTE_extended_1", "RTE_extended_003"})
  public void RTE_003_InternalLink() {
    String articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    SourceEditModePageObject source = new SourceEditModePageObject().openArticle(articleName);
    source.clickInternalLink();

    Assertion.assertEquals(source.getSourceContent(), "[[Link title]]");
  }

  @Test(groups = {"RTE_extended_1", "RTE_extended_004"})
  public void RTE_004_ExternalLink() {
    String articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    SourceEditModePageObject source = new SourceEditModePageObject().openArticle(articleName);
    source.clickExternalLink();

    Assertion.assertEquals(source.getSourceContent(), "[http://www.example.com link title]");
  }

  @Test(groups = {"RTE_extended_1", "RTE_extended_005"})
  public void RTE_005_HeadLine() {
    String articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    SourceEditModePageObject source = new SourceEditModePageObject().openArticle(articleName);
    source.clickLvl2Headline();

    Assertion.assertEquals(source.getSourceContent(), "\n== Headline text ==\n");
  }

  @DontRun(language = "szl")
  @Test(groups = {"RTE_extended_1", "RTE_extended_006"})
  public void RTE_006_EmbedFile() {
    String articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    SourceEditModePageObject source = new SourceEditModePageObject().openArticle(articleName);
    source.clickEmbedFile();

    Assertion.assertEquals(source.getSourceContent(), "[[File:Example.jpg]]");
  }

  @Test(groups = {"RTE_extended_1", "RTE_extended_007"})
  public void RTE_007_EmbedMedia() {
    String articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    SourceEditModePageObject source = new SourceEditModePageObject().openArticle(articleName);
    source.clickEmbedMedia();

    Assertion.assertEquals(source.getSourceContent(), "[[Media:Example.ogg]]");
  }

  @Test(groups = {"RTE_extended_1", "RTE_extended_008"})
  public void RTE_008_Math() {
    String articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    SourceEditModePageObject source = new SourceEditModePageObject().openArticle(articleName);
    source.clickMath();

    Assertion.assertEquals(source.getSourceContent(), "<math>Insert formula here</math>");
  }

  @Test(groups = {"RTE_extended_2", "RTE_extended_009"})
  public void RTE_009_Nowiki() {
    String articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    SourceEditModePageObject source = new SourceEditModePageObject().openArticle(articleName);
    source.clickNowiki();

    Assertion.assertEquals(source.getSourceContent(),
                           "<nowiki>Insert non-formatted text here</nowiki>"
    );
  }

  @Test(groups = {"RTE_extended_2", "RTE_extended_010"})
  public void RTE_010_Signature() {
    String articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    SourceEditModePageObject source = new SourceEditModePageObject().openArticle(articleName);
    source.clickSignature();

    Assertion.assertEquals(source.getSourceContent(), "--~~~~");
  }

  @Test(groups = {"RTE_extended_2", "RTE_extended_011"})
  public void RTE_011_HLine() {
    String articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    SourceEditModePageObject source = new SourceEditModePageObject().openArticle(articleName);
    source.clickHorizontalLine();

    Assertion.assertEquals(source.getSourceContent(), "\n----\n");
  }

  @DontRun(language = "szl")
  @Test(groups = {"RTE_extended_2", "RTE_extended_012"})
  public void RTE_012_Photo() {
    String articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    SourceEditModePageObject source = new SourceEditModePageObject().openArticle(articleName);
    PhotoAddComponentObject photoAddPhoto = source.clickAddPhoto();
    PhotoOptionsComponentObject photoOptions = photoAddPhoto.addPhotoFromWiki("Default_Image001.png");
    photoOptions.setCaption(PageContent.CAPTION);
    photoOptions.clickAddPhoto();
    String photoName = photoAddPhoto.getPhotoName();

    Assertion.assertEquals(source.getSourceContent(),
                           String.format(PageContent.WIKI_TEXT_PHOTO.replace("%photoName%",
                                                                             photoName
                           ), PageContent.CAPTION)
    );
  }

  @Test(groups = {"RTE_extended_2", "RTE_extended_012A"})
  @RunOnly(language = "szl")
  @Execute(asUser = User.SUS_REGULAR_USER3, onWikia = "sustainingtest")
  public void RTE_012A_PhotoSzl() {
    String articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    SourceEditModePageObject source = new SourceEditModePageObject().openArticle(articleName);
    PhotoAddComponentObject photoAddPhoto = source.clickAddPhoto();
    PhotoOptionsComponentObject photoOptions = photoAddPhoto.addPhotoFromWiki("default_Image001.png");
    photoOptions.setCaption(PageContent.CAPTION);
    photoOptions.clickAddPhoto();
    String photoName = photoAddPhoto.getPhotoName();

    Assertion.assertEquals(source.getSourceContent(),
                           String.format("[[Plik:%photoName%|thumb|%s]]".replace("%photoName%",
                                                                                 photoName
                           ), PageContent.CAPTION)
    );
  }

  @Test(groups = {"RTE_extended_2", "RTE_extended_013"})
  public void RTE_013_Slideshow() {
    String articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    SourceEditModePageObject source = new SourceEditModePageObject().openArticle(articleName);
    source.clickAddGallery();
    source.verifyComponentSelector();
    SlideshowBuilderComponentObject
        slideshowBuilder
        = (SlideshowBuilderComponentObject) source.addComponent("slideshow");
    AddPhotoComponentObject slideshowAddPhoto = slideshowBuilder.clickAddPhoto();
    slideshowAddPhoto.search("image");
    List<String> photoNames = slideshowAddPhoto.choosePhotos(4);
    slideshowAddPhoto.clickSelect();
    slideshowBuilder.adjustPosition(Positions.CENTER);
    slideshowBuilder.clickFinish();

    Assertion.assertEquals(source.getSourceContent(),
                           "<gallery type=\"slideshow\" position=\"center\">\n" + photoNames.get(0)
                           + "\n" + photoNames.get(1) + "\n" + photoNames.get(2) + "\n" + photoNames
                               .get(3) + "\n</gallery>"
    );
  }

  @Test(groups = {"RTE_extended_2", "RTE_extended_014"})
  public void RTE_014_Gallery() {
    String articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    SourceEditModePageObject source = new SourceEditModePageObject().openArticle(articleName);
    source.clickAddGallery();
    source.verifyComponentSelector();
    GalleryBuilderComponentObject
        galleryBuiler
        = (GalleryBuilderComponentObject) source.addComponent("gallery");
    AddPhotoComponentObject galleryAddPhoto = galleryBuiler.clickAddPhoto();
    galleryAddPhoto.search("image");
    List<String> photoNames = galleryAddPhoto.choosePhotos(4);
    galleryAddPhoto.clickSelect();
    galleryBuiler.adjustPosition(PositionsGallery.CENTER);
    galleryBuiler.adjustColumns("2");
    galleryBuiler.adjustSpacing(SpacingGallery.SMALL);
    galleryBuiler.adjustOrientation(Orientation.LANDSCAPE);
    galleryBuiler.clickFinish();

    Assertion.assertEquals(source.getSourceContent(),
                           "<gallery position=\"center\" columns=\"2\" spacing=\"small\">\n"
                           + photoNames.get(0) + "\n" + photoNames.get(1) + "\n" + photoNames.get(2)
                           + "\n" + photoNames.get(3) + "\n</gallery>"
    );
  }

  @Test(groups = {"RTE_extended_3", "RTE_extended_015"})
  public void RTE_015_Slider() {
    String articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    SourceEditModePageObject source = new SourceEditModePageObject().openArticle(articleName);
    source.clickAddGallery();
    source.verifyComponentSelector();
    SliderBuilderComponentObject sliderBuilder = (SliderBuilderComponentObject) source.addComponent(
        "slider");
    sliderBuilder.selectMenuPosition(MenuPositions.VERTICAL);
    AddPhotoComponentObject sliderAddPhoto = sliderBuilder.clickAddPhoto();
    sliderAddPhoto.search("image");
    List<String> photoNames = sliderAddPhoto.choosePhotos(4);
    sliderAddPhoto.clickSelect();
    sliderBuilder.clickFinish();

    Assertion.assertEquals(source.getSourceContent(),
                           "<gallery type=\"slider\" orientation=\"right\">\n" + photoNames.get(0)
                           + "\n" + photoNames.get(1) + "\n" + photoNames.get(2) + "\n" + photoNames
                               .get(3) + "\n</gallery>"
    );
  }

  @DontRun(language = "szl")
  @Test(groups = {"RTE_extended_3", "RTE_extended_016", "Media"})
  public void RTE_016_Video() {
    String articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    SourceEditModePageObject source = new SourceEditModePageObject().openArticle(articleName);
    VetAddVideoComponentObject vetAddingVideo = source.clickAddVideo();
    VetOptionsComponentObject
        vetOptions
        = vetAddingVideo.addVideoByUrl(VideoContent.YOUTUBE_VIDEO_URL);
    vetOptions.setCaption(PageContent.CAPTION);
    vetOptions.submit();
    source.checkSourceVideoContent(
        "[[" + VideoContent.YOUTUBE_VIDEO_WIKITEXT + PageContent.CAPTION + "]]");
  }

  @Test(groups = {"RTE_extended_3", "RTE_extended_017"})
  public void RTE_017_MoreMainTools() {
    String articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    SourceEditModePageObject source = new SourceEditModePageObject().openArticle(articleName);
    source.checkMainTools();
    source.submitArticle();
  }

  @Test(groups = {"RTE_extended_3", "RTE_extended_018"})
  public void RTE_018_MoreWikiMarkupTools() {
    String articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    SourceEditModePageObject source = new SourceEditModePageObject().openArticle(articleName);
    source.checkWikiMarkupTools();
    source.submitArticle();
  }

  @Test(groups = {"RTE_extended_4", "RTE_extended_019"})
  public void RTE_019_MoreSympolsTools() {
    String articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    SourceEditModePageObject source = new SourceEditModePageObject().openArticle(articleName);
    source.checkSymbolsTools();
    source.submitArticle();
  }

  @Test(groups = {"RTE_draft","draft_saving"})
  @Execute(asUser = User.SUS_REGULAR_USER3, onWikia = "draftsavetest")
  public void RTE_draft_save_discard_changes() {
    String articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    new ArticleContent(User.SUS_REGULAR_USER3).push("Published edit of article", articleName);
    SourceEditModePageObject source = new SourceEditModePageObject().openArticle(articleName);
    source.addContentInSourceMode("Unpublished edit of article");
    source.waitForDraftToBeSaved();
    driver.navigate().refresh();
    AlertHandler.acceptPopupWindow(driver,15);
    Assertion.assertStringContains(source.getBannerText(),"This is a draft that includes your unpublished changes. Keep up the great work!");
    source.setDiscardChanges();
    Assertion.assertStringNotContains(source.getSourceContent(),"Unpublished edit of article");
  }

  @Test(groups = {"RTE_draft","draft_saving"})
  @Execute(asUser = User.SUS_REGULAR_USER3, onWikia = "draftsavetest")
  public void RTE_draft_save_keep_changes() {
    String articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    new ArticleContent(User.SUS_REGULAR_USER3).push("Published edit of article", articleName);
    SourceEditModePageObject source = new SourceEditModePageObject().openArticle(articleName);
    source.addContentInSourceMode("Unpublished edit of article");
    source.waitForDraftToBeSaved();
    driver.navigate().refresh();
    AlertHandler.acceptPopupWindow(driver,15);
    Assertion.assertStringContains(source.getBannerText(),"This is a draft that includes your unpublished changes. Keep up the great work!");
    source.setKeepChanges();
    Assertion.assertStringContains(source.getSourceContent(), "Unpublished edit of article");
  }

  @Test(groups = {"RTE_draft","draft_saving"})
  @Execute(asUser = User.SUS_REGULAR_USER3, onWikia = "draftsavetest")
  public void RTE_draft_conflict_showChanges() {
    String articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    new ArticleContent(User.SUS_REGULAR_USER3).push("Published edit of article \n", articleName);
    SourceEditModePageObject source = new SourceEditModePageObject().openArticle(articleName);
    source.addContentInSourceMode("Unpublished edit of article");
    new ArticleContent(User.SUS_REGULAR_USER).push("Edit published by another user", articleName);
    source.waitForDraftToBeSaved();
    driver.navigate().refresh();
    AlertHandler.acceptPopupWindow(driver,15);
    Assertion.assertStringContains(source.getModalText(),"Someone else has changed this page");
    source.closeDraftNotification();
    Assertion.assertStringContains(source.getSourceContent(),"Unpublished edit of article");
    source.clickShowChanges();
    Assertion.assertStringContains(source.getDeletedText(), "Edit published by another user");
  }

}
