package com.wikia.webdriver.TestCases.VideoTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoAddComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoOptionsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.LightboxPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;

public class VetProvidersTests extends TestTemplate{



			private String pageName;

			@Test(groups={"ArticleVideo_003", "ArticleVideo"})
			public void ArticleVideo003_VerifyingImagesPositionWikiText()
			{
				WikiArticlePageObject article = new WikiArticlePageObject(driver);
				article.openWikiPage();
				SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
				login.logInCookie(Properties.userName2, Properties.password2);
				article.refreshPage();
				pageName = "QAarticle"+article.getTimeStamp();
				article.openWikiPage();
				WikiArticleEditMode edit = article.createNewArticle(pageName, 1);
				edit.deleteArticleContent();
				PhotoAddComponentObject photoAddPhoto = edit.clickPhotoButton();
				PhotoOptionsComponentObject photoOptions = photoAddPhoto.addPhotoFromWiki("image", 1);
				photoOptions.setCaption(PageContent.caption);
				photoOptions.adjustAlignment(1);
				photoOptions.clickAddPhoto();
				edit.clickOnSourceButton();
				edit.verifyWikiTextInSourceMode("left");
				edit.clickOnVisualButton();
				edit.verifyLeftAlignmentIsSelected();
				edit.clickOnPublishButton();
				article.verifyImageOnThePage();
			}

			@Test(groups={"ArticleVideo_004", "ArticleVideo"})
			public void ArticleVideo004_Lightbox_VerifyExistenceAndURLsOfSocialButtons()
			{
				WikiArticlePageObject article = new WikiArticlePageObject(driver);
				article.openWikiPage();
				SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
				login.logInCookie(Properties.userName2, Properties.password2);
				//wiki.refreshPage();
				pageName = "QAarticle"+article.getTimeStamp();
				article.openWikiPage();
				WikiArticleEditMode edit = article.createNewArticle(pageName, 1);
				edit.deleteArticleContent();
				edit.clickPhotoButton();
				edit.addImageForLightboxTesting();
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
