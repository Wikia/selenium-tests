package com.wikia.webdriver.TestCases.LightboxTests;

import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialVideosPageObject;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Lightbox.LightboxComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoAddComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoOptionsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.RightRail.LatestPhotosComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.RightRail.RelatedVideoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.GalleryBoxes.SpecialMostLinkedFilesPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.GalleryBoxes.SpecialUncategorizedFilesPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.GalleryBoxes.SpecialUnusedFilesPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.GalleryBoxes.SpecialUnusedVideosPageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 * 1. Open lightbox from latest photo,
 * 2. Open lightbox from raletad video,
 * 3. Open lightbox from Special:UnusedFiles page
 * 4. Open lightbox from Special:UnusedVideos page
 * 5. Open lightbox from Special:UncategorizedFiles page
 * 6. Open lightbox from Special:MostLinkedFiles page
 * 7. Open lightbox from article image and verify social buttons
 * 8. Open lightbox from article image and verify carousel
 */

public class LightboxTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();

	@Test(groups = {"LightboxTest", "LightboxTest_001"})
	public void LightboxTest_001_latestPhotos() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openRandomArticle(wikiURL);
		LatestPhotosComponentObject latestPhotos = new LatestPhotosComponentObject(driver);
		LightboxComponentObject lightbox = latestPhotos.openLightboxForImage(0);
		lightbox.verifyLightboxPopup();
	}

	@Test(groups = {"LightboxTest", "LightboxTest_002"})
	public void LightboxTest_002_relatedVideo() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openRandomArticle(wikiURL);
		RelatedVideoComponentObject relatedVideos = new RelatedVideoComponentObject(driver);
		LightboxComponentObject lightbox = relatedVideos.openLightboxForVideo(0);
		lightbox.verifyLightboxPopup();
	}


	@Test(groups = {"LightboxTest", "LightboxTest_003"})
	public void LightboxTest_004_unusedFiles() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		SpecialUnusedFilesPageObject unusedFiles = base.openSpecialUnusedFilesPage(wikiURL);
		LightboxComponentObject lightbox = unusedFiles.openLightboxForGridImage(0);
		lightbox.verifyLightboxPopup();
	}

	@Test(groups = {"LightboxTest", "LightboxTest_004"})
	public void LightboxTest_005_unusedVideos() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		SpecialUnusedVideosPageObject unusedFiles = base.openSpecialUnusedVideosPage(wikiURL);
		LightboxComponentObject lightbox = unusedFiles.openLightboxForGridVideo(0);
		lightbox.verifyLightboxPopup();
	}

	@Test(groups = {"LightboxTest", "LightboxTest_005"})
	public void LightboxTest_006_uncategorizedFiles() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		SpecialUncategorizedFilesPageObject unusedFiles = base.openSpecialUncategorizedFilesPage(wikiURL);
		LightboxComponentObject lightbox = unusedFiles.openLightboxForGridImage(0);
		lightbox.verifyLightboxPopup();
	}

	@Test(groups = {"LightboxTest", "LightboxTest_006"})
	public void LightboxTest_007_mostLinkedFiles() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		SpecialMostLinkedFilesPageObject unusedFiles = base.openSpecialMostLinkedFilesPage(wikiURL);
		LightboxComponentObject lightbox = unusedFiles.openLightboxForGridImage(0);
		lightbox.verifyLightboxPopup();
	}

	@Test(groups = {"LightboxTest", "LightboxTest_007"})
	public void LightboxTest_008_verifyExistenceAndURLsOfSocialButtons() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff);
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
		lightbox.closeShareScreen();
		lightbox.closeLightbox();
	}

    @Test(groups = {"Lightbox", "Lightbox008"})
    public void LightboxTest_008_verifyCarousel() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		ArticlePageObject article = base.openLightboxArticle(wikiURL);
		LightboxComponentObject lightbox = article.clickThumbnailImage();
		lightbox.clickPinButton();
		lightbox.clickCarouselRight();
		lightbox.clickCarouselLeft();
		lightbox.verifyCarouselLeftDisabled();
		lightbox.closeLightbox();
    }

	@Test(groups = {"LightboxTest", "LightboxTest_009"})
	public void LightboxTest_009_specialVideo() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		SpecialVideosPageObject specialVideos = base.openSpecialVideoPage(wikiURL);
		LightboxComponentObject lightbox = specialVideos.openLightboxForGridVideo(0);
		lightbox.verifyLightboxPopup();
		lightbox.verifyLightboxVideo();
	}
}
