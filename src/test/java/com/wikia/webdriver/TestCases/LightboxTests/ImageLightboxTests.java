package com.wikia.webdriver.TestCases.LightboxTests;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoAddComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoOptionsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.LightboxPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Lightbox.LightboxComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

public class ImageLightboxTests extends TestTemplate{
	@Test(groups = {"Lightbox", "Lightobx001"})
	public void lightboxTest_001() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.openArticle(URLsContent.lightboxImageTest);
		LightboxComponentObject lightbox = new LightboxComponentObject(driver);
		lightbox.openLightbox();
		lightbox.verifyLightboxPopup();
	}
	@Test(groups={"Lightbox", "Lightobx002"})
	public void Lightbox_verifyExistenceAndURLsOfSocialButtons()
	{
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		WikiArticleEditMode edit = article.createNewDefaultArticle();
		PhotoAddComponentObject photoAddPhoto = edit.clickPhotoButton();
		PhotoOptionsComponentObject photoOptions = photoAddPhoto.addPhotoFromWiki("image", 1);
		photoOptions.setCaption(PageContent.caption);
		photoOptions.clickAddPhoto();
		edit.verifyThatThePhotoAppears(PageContent.caption);
		edit.clickOnPublishButton();
		article.verifyImageOnThePage();
		LightboxPageObject lightbox = article.clickThumbnailImage();
		lightbox.clickPinButton();
		lightbox.clickShareButton();
		lightbox.verifyShareButtons();
		lightbox.clickFacebookShareButton();
		lightbox.verifyFacebookWindow();
		lightbox.clickTwitterShareButton();
		lightbox.verifyTwitterWindow();
		lightbox.clickStumbleUponShareButton();
		lightbox.verifyStumbleUponWindow();
		lightbox.clickRedditShareButton();
		lightbox.verifyRedditWindow();
		lightbox.clickPlusOneShareButton();
		lightbox.verifyPlusOneWindow();
		lightbox.clickCloseButton();
	}
}