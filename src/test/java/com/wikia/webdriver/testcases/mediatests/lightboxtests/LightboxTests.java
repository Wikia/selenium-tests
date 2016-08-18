package com.wikia.webdriver.testcases.mediatests.lightboxtests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.lightbox.LightboxComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoAddComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoOptionsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialNewFilesPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialVideosPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.filepage.FilePagePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.galleryboxes.SpecialMostLinkedFilesPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.galleryboxes.SpecialUncategorizedFilesPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.galleryboxes.SpecialUnusedFilesPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.galleryboxes.SpecialUnusedVideosPage;

/**
 * 1. Open lightbox from Special:UnusedFiles page 2. Open lightbox from Special:UnusedVideos page 3.
 * Open lightbox from Special:UncategorizedFiles page 4. Open lightbox from Special:MostLinkedFiles
 * page 5. Open lightbox from article image and verify social buttons 6. Open lightbox from article
 * image and verify carousel 7. Open lightbox from Special:Videos and verify video 8. Open lightbox
 * from Special:Videos, verify title url and verify file page (logged-in user) 9. Open lightbox from
 * Special:NewFiles, verify title url and verify file page (logged-in user)
 */
@Test(groups = {"LightboxTest", "Media"})
public class LightboxTests extends NewTestTemplate {

  private static final String BROWSER_SIZE = "1400x720";
  Credentials credentials = Configuration.getCredentials();

  @Test(groups = "LightboxTest_001")
  @InBrowser(browser = Browser.FIREFOX, browserSize = BROWSER_SIZE)
  public void LightboxTest_001_unusedFiles() {
    LightboxComponentObject lightbox =
        new SpecialUnusedFilesPage().open().getGalleryGrid().openLightboxForGridImage(0);

    lightbox.verifyLightboxPopup();
  }

  @Test(groups = "LightboxTest_002")
  @InBrowser(browser = Browser.FIREFOX, browserSize = BROWSER_SIZE)
  public void LightboxTest_002_unusedVideos() {
    LightboxComponentObject lightbox =
        new SpecialUnusedVideosPage().open().getGalleryGrid().openLightboxForGridVideo(0);

    lightbox.verifyLightboxPopup();
  }

  @Test(groups = "LightboxTest_003")
  @InBrowser(browser = Browser.FIREFOX, browserSize = BROWSER_SIZE)
  public void LightboxTest_003_uncategorizedFiles() {
    LightboxComponentObject lightbox =
        new SpecialUncategorizedFilesPage().open().getGalleryGrid().openLightboxForGridImage(0);

    lightbox.verifyLightboxPopup();
  }

  @Test(groups = "LightboxTest_004")
  @InBrowser(browser = Browser.FIREFOX, browserSize = BROWSER_SIZE)
  public void LightboxTest_004_mostLinkedFiles() {
    LightboxComponentObject lightbox =
        new SpecialMostLinkedFilesPage().open().getGalleryGrid().openLightboxForGridImage(0);

    lightbox.verifyLightboxPopup();
  }

  @Test(groups = "LightboxTest_005")
  @Execute(asUser = User.STAFF)
  @InBrowser(browser = Browser.FIREFOX, browserSize = BROWSER_SIZE)
  public void LightboxTest_005_verifyExistenceAndURLsOfSocialButtons() {
    new ArticleContent().push(PageContent.ARTICLE_TEXT);

    ArticlePageObject article = new ArticlePageObject().open();
    VisualEditModePageObject visualEditMode = article.navigateToArticleEditPage();
    visualEditMode.clearContent();
    PhotoAddComponentObject photoAddPhoto = visualEditMode.clickPhotoButton();
    PhotoOptionsComponentObject photoOptions = photoAddPhoto.addPhotoFromWiki("image", 1);
    photoOptions.setCaption(PageContent.CAPTION);
    photoOptions.clickAddPhoto();
    visualEditMode.verifyPhoto();
    visualEditMode.submitArticle();
    article.verifyPhoto();
    LightboxComponentObject lightbox = article.clickThumbnailImage();
    lightbox.clickPinButton();
    lightbox.makeHeaderVisible(); // Assure header buttons are visible if not hovered over
    lightbox.clickShareButton();
    lightbox.verifyShareButtons();
    lightbox.clickFacebookShareButton();
    lightbox.verifyUrlInNewWindow(URLsContent.FACEBOOK_DOMAIN);
    lightbox.clickTwitterShareButton();
    lightbox.verifyUrlInNewWindow(URLsContent.TWITTER_DOMAIN);
    lightbox.clickStumbleUponShareButton();
    lightbox.verifyUrlInNewWindow(URLsContent.STUMPLEUPON_DOMAIN);
    lightbox.clickRedditShareButton();
    lightbox.verifyUrlInNewWindow(URLsContent.REDDIT_DOMAIN);
    lightbox.clickPlusOneShareButton();
    lightbox.verifyUrlInNewWindow(URLsContent.GOOGLE_DOMAIN);
    lightbox.clickCloseShareScreenButton();
    lightbox.verifyShareScreenClosed();
    lightbox.clickCloseButton();
    lightbox.verifyLightboxClosed();
  }

  @Test(groups = "Lightbox_006")
  @InBrowser(browser = Browser.FIREFOX, browserSize = BROWSER_SIZE)
  public void LightboxTest_006_verifyCarousel() {
    WikiBasePageObject base = new WikiBasePageObject();
    SpecialVideosPageObject specialVideos = base.openSpecialVideoPage(wikiURL);
    LightboxComponentObject lightbox = specialVideos.openLightboxForGridVideo(0);
    lightbox.clickPinButton();
    lightbox.clickCarouselRight();
    lightbox.clickCarouselLeft();
    lightbox.verifyCarouselLeftDisabled();
  }

  @Test(groups = "LightboxTest_007")
  @InBrowser(browser = Browser.FIREFOX, browserSize = BROWSER_SIZE)
  public void LightboxTest_007_specialVideo() {
    WikiBasePageObject base = new WikiBasePageObject();
    SpecialVideosPageObject specialVideos = base.openSpecialVideoPage(wikiURL);
    LightboxComponentObject lightbox = specialVideos.openLightboxForGridVideo(0);
    lightbox.verifyLightboxPopup();
    lightbox.verifyLightboxVideo();
  }

  /**
   * Open lightbox from Special:Videos, verify title url, verify More Info button and verify file
   * page (logged-in user)
   */
  @Test(groups = "LightboxTest_008")
  @Execute(asUser = User.USER, disableFlash = "false")
  @InBrowser(browser = Browser.FIREFOX, browserSize = BROWSER_SIZE)
  public void LightboxTest_008_filepage_video() {
    SpecialVideosPageObject specialVideos = new WikiBasePageObject().openSpecialVideoPage(wikiURL);

    LightboxComponentObject lightbox = specialVideos.openLightboxForGridVideo(0);
    lightbox.verifyLightboxPopup();
    lightbox.verifyLightboxVideo();
    FilePagePageObject filePage = lightbox.clickTitle();
    filePage.verifyTabsExistVideo();
    filePage.verifyEmbeddedVideoIsPresent();
  }

  /**
   * Open lightbox from Special:NewFiles, verify title url, verify More Info button and verify file
   * page (logged-in user)
   */
  @Test(groups = "LightboxTest_009")
  @RelatedIssue(issueID = "MAIN-6170", comment = "Test manually")
  @InBrowser(browser = Browser.FIREFOX, browserSize = BROWSER_SIZE)
  public void LightboxTest_009_filepage_image() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.loginAs(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    SpecialNewFilesPage specialNewFiles = base.openSpecialNewFiles(wikiURL);

    int itemNumber = 2;

    LightboxComponentObject lightbox = specialNewFiles.openLightbox(itemNumber);
    lightbox.verifyLightboxPopup();
    lightbox.verifyLightboxImage();
    // lightbox.verifyTitleUrl(fileUrl);
    // lightbox.verifyMoreInfoUrl(fileUrl);
    FilePagePageObject filePage = lightbox.clickTitle();
    filePage.verifyTabsExistImage();
  }
}
