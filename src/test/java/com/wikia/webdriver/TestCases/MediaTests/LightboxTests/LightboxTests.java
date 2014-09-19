package com.wikia.webdriver.TestCases.MediaTests.LightboxTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Lightbox.LightboxComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoAddComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoOptionsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.RightRail.LatestPhotosComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.FilePage.FilePagePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.GalleryBoxes.SpecialMostLinkedFilesPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.GalleryBoxes.SpecialUncategorizedFilesPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.GalleryBoxes.SpecialUnusedFilesPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.GalleryBoxes.SpecialUnusedVideosPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialNewFilesPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialVideosPageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 * @author Saipetch Kongkatong
 *
 * 1. Open lightbox from latest photo,
 * 3. Open lightbox from Special:UnusedFiles page
 * 4. Open lightbox from Special:UnusedVideos page
 * 5. Open lightbox from Special:UncategorizedFiles page
 * 6. Open lightbox from Special:MostLinkedFiles page
 * 7. Open lightbox from article image and verify social buttons
 * 8. Open lightbox from article image and verify carousel
 * 9. Open lightbox from Special:Videos and verify video
 * 10. Open lightbox from Special:Videos, verify title url and verify file page (logged-in user)
 * 11. Open lightbox from Special:NewFiles, verify title url and verify file page (logged-in user)
 */
public class LightboxTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();

	@Test(groups = {"LightboxTest", "LightboxTest_001", "Media"})
	public void LightboxTest_001_latestPhotos() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openRandomArticle(wikiURL);
		LatestPhotosComponentObject latestPhotos = new LatestPhotosComponentObject(driver);
		LightboxComponentObject lightbox = latestPhotos.openLightboxForImage(0);
		lightbox.verifyLightboxPopup();
	}

	@Test(groups = {"LightboxTest", "LightboxTest_003", "Media"})
	public void LightboxTest_004_unusedFiles() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		SpecialUnusedFilesPageObject unusedFiles = base.openSpecialUnusedFilesPage(wikiURL);
		LightboxComponentObject lightbox = unusedFiles.openLightboxForGridImage(0);
		lightbox.verifyLightboxPopup();
	}

	@Test(groups = {"LightboxTest", "LightboxTest_004", "Media"})
	public void LightboxTest_005_unusedVideos() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		SpecialUnusedVideosPageObject unusedFiles = base.openSpecialUnusedVideosPage(wikiURL);
		LightboxComponentObject lightbox = unusedFiles.openLightboxForGridVideo(0);
		lightbox.verifyLightboxPopup();
	}

	@Test(groups = {"LightboxTest", "LightboxTest_005", "Media"})
	public void LightboxTest_006_uncategorizedFiles() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		SpecialUncategorizedFilesPageObject unusedFiles = base.openSpecialUncategorizedFilesPage(wikiURL);
		LightboxComponentObject lightbox = unusedFiles.openLightboxForGridImage(0);
		lightbox.verifyLightboxPopup();
	}

	@Test(groups = {"LightboxTest", "LightboxTest_006", "Media"})
	public void LightboxTest_007_mostLinkedFiles() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		SpecialMostLinkedFilesPageObject unusedFiles = base.openSpecialMostLinkedFilesPage(wikiURL);
		LightboxComponentObject lightbox = unusedFiles.openLightboxForGridImage(0);
		lightbox.verifyLightboxPopup();
	}

	@Test(groups = {"LightboxTest", "LightboxTest_007", "Media"})
	public void LightboxTest_008_verifyExistenceAndURLsOfSocialButtons() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
		visualEditMode.clearContent();
		PhotoAddComponentObject photoAddPhoto = visualEditMode.clickPhotoButton();
		PhotoOptionsComponentObject photoOptions = photoAddPhoto.addPhotoFromWiki("image", 1);
		photoOptions.setCaption(PageContent.caption);
		photoOptions.clickAddPhoto();
		visualEditMode.verifyPhoto();
		visualEditMode.submitArticle();
		article.verifyPhoto();
		LightboxComponentObject lightbox = article.clickThumbnailImage();
		lightbox.clickPinButton();
		lightbox.makeHeaderVisible(); // Assure header buttons are not invisible if not hovered over
		lightbox.clickShareButton();
		lightbox.verifyShareButtons();
		lightbox.clickFacebookShareButton();
		lightbox.verifyUrlInNewWindow(URLsContent.facebookDomain);
		lightbox.clickTwitterShareButton();
		lightbox.verifyUrlInNewWindow(URLsContent.twitterDomain);
		lightbox.clickStumbleUponShareButton();
		lightbox.verifyUrlInNewWindow(URLsContent.stumpleUponDomain);
		lightbox.clickRedditShareButton();
		lightbox.verifyUrlInNewWindow(URLsContent.redditDomain);
		lightbox.clickPlusOneShareButton();
		lightbox.verifyUrlInNewWindow(URLsContent.googleDomain);
		lightbox.clickCloseShareScreenButton();
		lightbox.verifyShareScreenClosed();
		lightbox.clickCloseButton();
		lightbox.verifyLightboxClosed();
	}

    @Test(groups = {"LightboxTest", "Lightbox_008", "Media"})
    public void LightboxTest_008_verifyCarousel() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		SpecialVideosPageObject specialVideos = base.openSpecialVideoPage(wikiURL);
		LightboxComponentObject lightbox = specialVideos.openLightboxForGridVideo(0);
		lightbox.clickPinButton();
		lightbox.clickCarouselRight();
		lightbox.clickCarouselLeft();
		lightbox.verifyCarouselLeftDisabled();
    }

	@Test(groups = {"LightboxTest", "LightboxTest_009", "Media"})
	public void LightboxTest_009_specialVideo() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		SpecialVideosPageObject specialVideos = base.openSpecialVideoPage(wikiURL);
		LightboxComponentObject lightbox = specialVideos.openLightboxForGridVideo(0);
		lightbox.verifyLightboxPopup();
		lightbox.verifyLightboxVideo();
	}

	@Test(groups = {"LightboxTest", "LightboxTest_010", "Media"})
	/**
	 * Open lightbox from Special:Videos, verify title url, verify More Info button and verify file page (logged-in user)
	 */
	public void LightboxTest_010_filepage_video() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		SpecialVideosPageObject specialVideos = base.openSpecialVideoPage(wikiURL);

		int itemNumber = 0;
		String fileUrl = specialVideos.getFileUrl(wikiURL, itemNumber);

		LightboxComponentObject lightbox = specialVideos.openLightboxForGridVideo(itemNumber);
		lightbox.verifyLightboxPopup();
		lightbox.verifyLightboxVideo();
		//lightbox.verifyTitleUrl(fileUrl);
		//lightbox.verifyMoreInfoUrl(fileUrl);
		FilePagePageObject filePage = lightbox.clickTitle();
		filePage.verifyTabsExistVideo();
		filePage.verifyEmbeddedVideoIsPresent();
		filePage.verifyVideoAutoplay(true);
	}

	@Test(groups = {"LightboxTest", "LightboxTest_011", "Media"})
	/**
	 * Open lightbox from Special:NewFiles, verify title url, verify More Info button and verify file page (logged-in user)
	 */
	public void LightboxTest_011_filepage_image() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		SpecialNewFilesPageObject specialNewFiles = base.openSpecialNewFiles(wikiURL);

		int itemNumber = 0;
		String fileUrl = specialNewFiles.getFileUrl(wikiURL, itemNumber);

		LightboxComponentObject lightbox = specialNewFiles.openLightbox(itemNumber);
		lightbox.verifyLightboxPopup();
		lightbox.verifyLightboxImage();
		//lightbox.verifyTitleUrl(fileUrl);
		//lightbox.verifyMoreInfoUrl(fileUrl);
		FilePagePageObject filePage = lightbox.clickTitle();
		filePage.verifyTabsExistImage();
	}

}
