package com.wikia.webdriver.TestCases.LightboxTests;

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
 * 3. Open lightbox from Special:NewFiles page,
 * 4. Open lightbox from Special:UnusedFiles page
 * 5. Open lightbox from Special:UnusedVideos page
 * 6. Open lightbox from Special:UncategorizedFiles page
 * 7. Open lightbox from Special:MostLinkedFiles page
 * 8. Open lightbox from article image and verify social buttons
 */
public class LightboxTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();

	@Test(groups = {"Lightbox", "Lightobox001"})
	public void LightboxTest_001_latestPhotos() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openRandomArticle(wikiURL);
		LatestPhotosComponentObject latestPhotos = new LatestPhotosComponentObject(driver);
		LightboxComponentObject lightbox = latestPhotos.openLightboxForImage(0);
		lightbox.verifyLightboxPopup();
	}

	@Test(groups = {"Lightbox", "Lightobox002"})
	public void LightboxTest_002_releatedVideo() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openRandomArticle(wikiURL);
		RelatedVideoComponentObject relatedVideos = new RelatedVideoComponentObject(driver);
		LightboxComponentObject lightbox = relatedVideos.openLightboxForVideo(0);
		lightbox.verifyLightboxPopup();
	}

	@Test(groups = {"Lightbox", "Lightobox003"})
	public void LightboxTest_003_unusedFiles() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		SpecialUnusedFilesPageObject unusedFiles = base.openSpecialUnusedFilesPage(wikiURL);
		LightboxComponentObject lightbox = unusedFiles.openLightboxForGridImage(0);
		lightbox.verifyLightboxPopup();
	}

	@Test(groups = {"Lightbox", "Lightobox004"})
	public void LightboxTest_004_unusedVideos() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		SpecialUnusedVideosPageObject unusedFiles = base.openSpecialUnusedVideosPage(wikiURL);
		LightboxComponentObject lightbox = unusedFiles.openLightboxForGridVideo(0);
		lightbox.verifyLightboxPopup();
	}

	@Test(groups = {"Lightbox", "Lightobox005"})
	public void LightboxTest_005_uncategorizedFiles() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		SpecialUncategorizedFilesPageObject unusedFiles = base.openSpecialUncategorizedFilesPage(wikiURL);
		LightboxComponentObject lightbox = unusedFiles.openLightboxForGridImage(0);
		lightbox.verifyLightboxPopup();
	}

	@Test(groups = {"Lightbox", "Lightobox006"})
	public void LightboxTest_006_mostLinkedFiles() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		SpecialMostLinkedFilesPageObject unusedFiles = base.openSpecialMostLinkedFilesPage(wikiURL);
		LightboxComponentObject lightbox = unusedFiles.openLightboxForGridImage(0);
		lightbox.verifyLightboxPopup();
	}

	@Test(groups = {"Lightbox", "Lightobox007"})
	public void LightboxTest_007_verifyExistenceAndURLsOfSocialButtons() {
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
		lightbox.closeLightbox();
	}
}
