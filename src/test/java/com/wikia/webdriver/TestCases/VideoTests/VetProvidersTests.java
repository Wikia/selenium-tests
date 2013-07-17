package com.wikia.webdriver.TestCases.VideoTests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoAddComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoOptionsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetOptionsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.LightboxPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.FileDetailsPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;

public class VetProvidersTests extends TestTemplate{

	//Rodrigo Testing TCs

			String pageName;

			@DataProvider
			private static final Object[][] provideVideo() {
				return new Object[][] {
					// Premium Provider Links
					// screenplay from video.wikia
					{
						"http://video.wikia.com/wiki/File:The_Muppets_(2011)_-_Featurette_Behind_The_Scenes_-_Ok_Go_Video",
						"The Muppets (2011) - Featurette Behind The Scenes - Ok Go Video"
					},
					// realgravity form video.wikia
					{
						"http://video.wikia.com/wiki/File:Good-Looking_Gamer_Girlfriend_Episode_16:_Snow!_by_xRpMx13_(Modern_Warfare_3_Gameplay/Commentary)",
						"Good-Looking Gamer Girlfriend Episode 16: Snow! by xRpMx13 (Modern Warfare 3 Gameplay/Commentary)"
					},
					// {"http://www.anyclip.com/movies/apollo-13/"},
					// {"http://uk.ign.com/videos/2013/05/18/the-last-of-us-is-beautiful-immersive-scary"},
					// {"http://movieclips.com/AfsQY-21-grams-movie-jack-returns-home/"},
					// {"http://www.ooyala.com/#ooid=dwM3FmOTqFWfsRDguLM7wijlltNJAJil"},
					//{ "http://www.twitch.tv/girlsgonegaming",
					//"Girlsgonegaming playing Minecraft" },

					// Non-Premium Provider Links
					{
						"http://blip.tv/q-tv/s-gus-unger-hamilton-and-gwil-sainsbury-in-studio-q-6587602",
						"Q with Jian Ghomeshi - ∆'s Gus Unger-Hamilton and Gwil Sainsbury in Studio Q"
					}, {
						"http://www.dailymotion.com/video/x101vdw_robert-pattison-y-kristen-stewart-se-dan-un-tiempo_people#.UZovsrWnqXw",
						"Robert Pattison y Kristen Stewart se dan un tiempo"
					},
					// {"http://on.aol.com/playlist/soccer-legend-david-beckham-retires-152618?icid=OnHomepageC2Wide_Trending_Tag"},
					{
						"http://www.gamestar.de/videos/action,9/batman-arkham-origins,70131.html",
						"Batman Arkham Origins - Erster Teaser-Trailer Batman vs. Deathstroke"
					},
					// {"http://www.gametrailers.com/videos/g504tl/next-xbox-xbox-360-memories-part-ii",
					// ""},
					{
						"http://www.hulu.com/watch/489169",
						"The Unnatural (Bob's Burgers)"
					}, {
						"http://www.metacafe.com/watch/10534054/fast_furious_6_favourite_stunts/",
						"Fast Furious 6 Favourite Stunts"
					}, {
						"http://www.myvideo.de/watch/9112478/Snowblind",
						"Snowblind"
					},
					// {"http://www.sevenload.com/videos/72-stunden-paris-50d84cfe4faad3551900000f"},
					// {"http://www.southparkstudios.com/clips/360885/oh-my-god-its-gordon-ramsay"},
					{
						"http://vimeo.com/channels/staffpicks/58024671",
						"Melt Yourself Down - Fix My Life"
					}, {
						"http://www.youtube.com/watch?v=d9r5_DDMMjY",
						"Top 5 Plays of the Night May 19th"
					}, {
						"http://www.viddler.com/v/f97fc5b0",
						"Times-Shamrock Communications Spotlight"
					},
				};
			}

			@Test(dataProvider="provideVideo", groups={"VetProvidersTests001", "VetTests", "VetProviders"})
			public void ArticleVideo001_AddingProviderVideosVET(String videoURL, String name)
			{
				PageObjectLogging.log("", videoURL, true);
				WikiArticlePageObject article = new WikiArticlePageObject(driver);
				article.openWikiPage();
				SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
				login.logInCookie(Properties.userName2, Properties.password2);
				article.refreshPage();
				WikiArticleEditMode edit = article.createNewDefaultArticle();
				edit.deleteArticleContent();
				edit.clickOnVisualButton();
				VetAddVideoComponentObject vetAddingVideo = edit.clickVideoButton();
				VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByUrl(VideoContent.youtubeVideoURL);
				vetOptions.setCaption(PageContent.caption);
				vetOptions.submit();
				edit.verifyVideoInEditMode(PageContent.caption);
				edit.clickOnPublishButton();
				article.verifyVideoOnThePage();
				FileDetailsPageObject fileDetails = article.clickVideoDetailsButton();
				fileDetails.verifyEmbeddedVideoIsPresent();
			}

			@Test(dataProvider="provideVideo", groups={"VetProvidersTests002", "VetTests", "VetProviders"})
			public void ArticleVideo002_AddingProviderVideosRVModule(String videoUrl, String name)
			{
				PageObjectLogging.log("", videoUrl, true);
				WikiArticlePageObject article = new WikiArticlePageObject(driver);
				article.openWikiPage();
				SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
				login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
				article.openArticle("MediaWiki:RelatedVideosGlobalList");
				WikiArticleEditMode RVmoduleMessageEdit = article.edit();
				RVmoduleMessageEdit.deleteUnwantedVideoFromMessage(name);
				RVmoduleMessageEdit.clickOnPublishButton();
				article.openRandomArticleByUrl();
				VetAddVideoComponentObject vetAddingVideo = article.clickOnAddVideoRVModule();
				vetAddingVideo.addVideoByUrl(videoUrl);
				article.verifyVideoAddedToRVModule(name);
			}

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
