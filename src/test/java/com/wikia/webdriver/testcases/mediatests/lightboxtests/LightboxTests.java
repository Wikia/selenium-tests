package com.wikia.webdriver.testcases.mediatests.lightboxtests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.lightbox.LightboxComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoAddComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoOptionsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.rightrail.LatestPhotosComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialNewFilesPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialVideosPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.filepage.FilePagePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.galleryboxes.SpecialMostLinkedFilesPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.galleryboxes.SpecialUncategorizedFilesPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.galleryboxes.SpecialUnusedFilesPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.galleryboxes.SpecialUnusedVideosPageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 * @author Saipetch Kongkatong 
 * 1. Open lightbox from Special:UnusedFiles page 
 * 2. Open lightbox from Special:UnusedVideos page 
 * 3. Open lightbox from Special:UncategorizedFiles page 
 * 4. Open lightbox from Special:MostLinkedFiles page
 * 5. Open lightbox from article image and verify social buttons
 * 6. Open lightbox from article image and verify carousel 
 * 7. Open lightbox from Special:Videos and verify video
 * 
 * 8. Open lightbox from Special:Videos, verify title url 
 * and verify file page (logged-in user) 
 * 
 * 9. Open lightbox from Special:NewFiles, verify title url and
 *  verify file page (logged-in user)
 */
public class LightboxTests extends NewTestTemplate {

  Credentials credentials = config.getCredentials();

  @Test(groups = {"LightboxTest", "LightboxTest_001", "Media"})
  public void LightboxTest_001_unusedFiles() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    SpecialUnusedFilesPageObject unusedFiles = base.openSpecialUnusedFilesPage(wikiURL);
    LightboxComponentObject lightbox = unusedFiles.openLightboxForGridImage(0);
    lightbox.verifyLightboxPopup();
  }

  @Test(groups = {"LightboxTest", "LightboxTest_002", "Media"})
  public void LightboxTest_002_unusedVideos() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    SpecialUnusedVideosPageObject unusedFiles = base.openSpecialUnusedVideosPage(wikiURL);
    LightboxComponentObject lightbox = unusedFiles.openLightboxForGridVideo(0);
    lightbox.verifyLightboxPopup();
  }

  @Test(groups = {"LightboxTest", "LightboxTest_003", "Media"})
  public void LightboxTest_003_uncategorizedFiles() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    SpecialUncategorizedFilesPageObject unusedFiles =
        base.openSpecialUncategorizedFilesPage(wikiURL);
    LightboxComponentObject lightbox = unusedFiles.openLightboxForGridImage(0);
    lightbox.verifyLightboxPopup();
  }

  @Test(groups = {"LightboxTest", "LightboxTest_004", "Media"})
  public void LightboxTest_004_mostLinkedFiles() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    SpecialMostLinkedFilesPageObject unusedFiles = base.openSpecialMostLinkedFilesPage(wikiURL);
    LightboxComponentObject lightbox = unusedFiles.openLightboxForGridImage(0);
    lightbox.verifyLightboxPopup();
  }

  @Test(groups = {"LightboxTest", "LightboxTest_005", "Media"})
  public void LightboxTest_005_verifyExistenceAndURLsOfSocialButtons() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    ArticlePageObject article = base.openRandomArticle(wikiURL);
    VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
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

  @Test(groups = {"LightboxTest", "Lightbox_006", "Media"})
  public void LightboxTest_006_verifyCarousel() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    SpecialVideosPageObject specialVideos = base.openSpecialVideoPage(wikiURL);
    LightboxComponentObject lightbox = specialVideos.openLightboxForGridVideo(0);
    lightbox.clickPinButton();
    lightbox.clickCarouselRight();
    lightbox.clickCarouselLeft();
    lightbox.verifyCarouselLeftDisabled();
  }

  @Test(groups = {"LightboxTest", "LightboxTest_007", "Media"})
  public void LightboxTest_007_specialVideo() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    SpecialVideosPageObject specialVideos = base.openSpecialVideoPage(wikiURL);
    LightboxComponentObject lightbox = specialVideos.openLightboxForGridVideo(0);
    lightbox.verifyLightboxPopup();
    lightbox.verifyLightboxVideo();
  }

  @Test(groups = {"LightboxTest", "LightboxTest_008", "Media"})
  /**
   * Open lightbox from Special:Videos, verify title url, verify More Info button and verify file page (logged-in user)
   */
  public void LightboxTest_008_filepage_video() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    SpecialVideosPageObject specialVideos = base.openSpecialVideoPage(wikiURL);

    int itemNumber = 0;
    LightboxComponentObject lightbox = specialVideos.openLightboxForGridVideo(itemNumber);
    lightbox.verifyLightboxPopup();
    lightbox.verifyLightboxVideo();
    // lightbox.verifyTitleUrl(fileUrl);
    // lightbox.verifyMoreInfoUrl(fileUrl);
    FilePagePageObject filePage = lightbox.clickTitle();
    filePage.verifyTabsExistVideo();
    filePage.verifyEmbeddedVideoIsPresent();
    filePage.verifyVideoAutoplay(true);
  }

  @Test(groups = {"LightboxTest", "LightboxTest_009", "Media"})
  /**
   * Open lightbox from Special:NewFiles, verify title url, verify More Info button and verify file page (logged-in user)
   */
  public void LightboxTest_009_filepage_image() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    SpecialNewFilesPageObject specialNewFiles = base.openSpecialNewFiles(wikiURL);

    int itemNumber = 0;

    LightboxComponentObject lightbox = specialNewFiles.openLightbox(itemNumber);
    lightbox.verifyLightboxPopup();
    lightbox.verifyLightboxImage();
    // lightbox.verifyTitleUrl(fileUrl);
    // lightbox.verifyMoreInfoUrl(fileUrl);
    FilePagePageObject filePage = lightbox.clickTitle();
    filePage.verifyTabsExistImage();
  }

}
