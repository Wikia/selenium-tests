package com.wikia.webdriver.testcases.mediatests.lightboxtests;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.lightbox.LightboxComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialNewFilesPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialVideosPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.filepage.FilePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.galleryboxes.SpecialMostLinkedFilesPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.galleryboxes.SpecialUncategorizedFilesPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.galleryboxes.SpecialUnusedFilesPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.galleryboxes.SpecialUnusedVideosPage;
import org.testng.annotations.Test;

/**
 * 1. Open lightbox from Special:UnusedFiles page 2. Open lightbox from Special:UnusedVideos page 3.
 * Open lightbox from Special:UncategorizedFiles page 4. Open lightbox from Special:MostLinkedFiles
 * page 5. Open lightbox from article image and verify social buttons 6. Open lightbox from article
 * image and verify carousel 7. Open lightbox from Special:Videos and verify video 8. Open lightbox
 * from Special:Videos, verify title url and verify file page (logged-in user) 9. Open lightbox from
 * Special:NewFiles, verify title url and verify file page (logged-in user)
 */
@Test(groups = {"LightboxTest", "Media"})
@Execute(onWikia = "sustainingtest")
public class LightboxTests extends NewTestTemplate {

  @Test(groups = "LightboxTest_001")
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void UserCanOpenLightBoxOnUnusedFiles() {
    LightboxComponentObject lightbox =
        new SpecialUnusedFilesPage().open().getGalleryGrid().openLightboxForGridImage(0);

    lightbox.verifyLightboxPopup();
  }

  @Test(groups = "LightboxTest_002")
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void UserCanOpenLightBoxOnUnusedVideos() {
    LightboxComponentObject lightbox =
        new SpecialUnusedVideosPage().open().getGalleryGrid().openLightboxForGridVideo(0);

    lightbox.verifyLightboxPopup();
  }

  @Test(groups = "LightboxTest_003")
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void UserCanOpenLightBoxOnUncategorizedFiles() {
    LightboxComponentObject lightbox =
        new SpecialUncategorizedFilesPage().open().getGalleryGrid().openLightboxForGridImage(0);

    lightbox.verifyLightboxPopup();
  }

  @Test(groups = "LightboxTest_004")
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void UserCanOpenLightBoxOnMostLinkedFiles() {
    LightboxComponentObject lightbox =
        new SpecialMostLinkedFilesPage().open().getGalleryGrid().openLightboxForGridImage(0);

    lightbox.verifyLightboxPopup();
  }

  @Test(groups = "LightboxTest_005")
  @Execute(asUser = User.SUS_REGULAR_USER)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void UserCanShareAFileFromArticlePageOnSocialMedia() {
    new ArticleContent(User.SUS_STAFF).push("[[File:Image1.png|thumb|TestDataCaption1]]");

    ArticlePageObject article = new ArticlePageObject().open();
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
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void AnonCanUseCarousel() {
    WikiBasePageObject base = new WikiBasePageObject();
    SpecialVideosPageObject specialVideos = base.openSpecialVideoPage(wikiURL);
    LightboxComponentObject lightbox = specialVideos.openLightboxForGridVideo(0);
    lightbox.clickPinButton();
    lightbox.clickCarouselRight();
    lightbox.clickCarouselLeft();
    lightbox.verifyCarouselLeftDisabled();
  }

  @Test(groups = "LightboxTest_007")
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void AnonCanSeeLightboxOnSpecialVideos() {
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
  @Execute(asUser = User.SUS_REGULAR_USER, disableFlash = "false")
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void UserCanSeeLightboxOnSpecialVideos() {
    SpecialVideosPageObject specialVideos = new WikiBasePageObject().openSpecialVideoPage(wikiURL);

    LightboxComponentObject lightbox = specialVideos.openLightboxForGridVideo(0);
    lightbox.verifyLightboxPopup();
    lightbox.verifyLightboxVideo();
    FilePage filePage = lightbox.clickTitle();
    filePage.verifyTabsExistVideo();
    filePage.verifyEmbeddedVideoIsPresent();
  }

  /**
   * Open lightbox from Special:NewFiles, verify title url, verify More Info button and verify file
   * page (logged-in user)
   */
  @Test(groups = "LightboxTest_009")
  @Execute(asUser = User.SUS_REGULAR_USER)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void UserCanSeeLightboxOnSpecialNewFiles() {
    WikiBasePageObject base = new WikiBasePageObject();
    SpecialNewFilesPage specialNewFiles = base.openSpecialNewFiles(wikiURL);

    LightboxComponentObject lightbox = specialNewFiles.openLightbox(0);
    lightbox.verifyLightboxPopup();
    lightbox.verifyLightboxImage();
    // lightbox.verifyTitleUrl(fileUrl);
    // lightbox.verifyMoreInfoUrl(fileUrl);
    FilePage filePage = lightbox.clickTitle();
    filePage.verifyTabsExistImage();
  }
}
