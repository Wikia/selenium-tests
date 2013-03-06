package com.wikia.webdriver.TestCases.VideoTests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.LightboxPageObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetOptionsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.FileDetailsPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;

public class VetProvidersTests extends TestTemplate{
		
	//Rodrigo Testing TCs
			@DataProvider
			private static final Object[][] provideVideo()
			{
				return new Object[][] 
				{
//						{"http://www.twitch.tv/girlsgonegaming", "Girlsgonegaming playing Minecraft"},
					    {"http://www.youtube.com/watch?v=p7R-X1CXiI8&feature=g-vrec", "Christopher Hitchens VS John And Tom Metzger"},
					    {"http://www.dailymotion.com/video/xqyly1_dni-ostrowca-koncert-kakadu_music", "Dni Ostrowca - Koncert Kakadu"},
					    {"http://www.metacafe.com/watch/9111307/the_master_movie_review_indiana_jones_blu_ray_collection_review_breakin_it_down/", "The Master Movie Review Indiana Jones Blu-ray Collection Review - Breakin' It Down"},
					    {"http://www.viddler.com/v/27dbe690", "Lawn mower beer train gets pulled over"},
					    {"http://vimeo.com/channels/staffpicks/50238512", "Berlin hyper-lapse"},
					    {"http://www.5min.com/Video/Getting-Out-of-a-Defensive-Position-in-Pool-516993703", "Getting Out of a Defensive Position in Pool"},
					    {"http://www.hulu.com/watch/401167", "The New Rachel (Glee)"},
						{"http://www.myvideo.de/watch/8653744/Exklusive_7_Minuten_aus_This_Ain_t_California", "Exklusive 7 Minuten aus This Ain't California"},
					    {"http://www.gamestar.de/videos/sport,12/landwirtschafts-simulator-2013,67641.html", "Landwirtschafts-Simulator 2013 - Fahrzeug-Trailer"},
					    //screenplay from video.wikia
					    {"http://video.wikia.com/wiki/File:The_Muppets_(2011)_-_Featurette_Behind_The_Scenes_-_Ok_Go_Video", "The Muppets (2011) - Featurette Behind The Scenes - Ok Go Video"},
					    //ign from video.wikia
					    {"http://video.wikia.com/wiki/File:IGN_Live_Tomb_Raider_Demo_-_E3_2012", "IGN Live Tomb Raider Demo - E3 2012"},
					    //realgravity form video.wikia
					    {"http://video.wikia.com/wiki/File:Good-Looking_Gamer_Girlfriend_Episode_16:_Snow!_by_xRpMx13_(Modern_Warfare_3_Gameplay/Commentary)", "Good-Looking Gamer Girlfriend Episode 16: Snow! by xRpMx13 (Modern Warfare 3 Gameplay/Commentary)"},
				};
			}
			
			@Test(dataProvider="provideVideo", groups={"VetProvidersTests001", "VetTests"}) 
			public void ArticleVideo001_AddingProviderVideosVET(String videoURL, String name)
			{
				PageObjectLogging.log("", videoURL, true);
				CommonFunctions.logOut(driver);
				WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
				wiki.openWikiPage();
				CommonFunctions.logInCookie(Properties.userName2, Properties.password2);
				wiki.refreshPage();
				WikiArticleEditMode edit = wiki.createNewDefaultArticle();
				edit.deleteArticleContent();
				edit.clickOnVisualButton();
				VetAddVideoComponentObject vetAddingVideo = edit.clickVideoButton();
				VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByUrl(VideoContent.youtubeVideoURL);
				vetOptions.setCaption(PageContent.caption);
				vetOptions.submit();
				edit.verifyVideoInEditMode(PageContent.caption);
				WikiArticlePageObject article = edit.clickOnPublishButton();
				article.verifyVideoOnThePage();
				FileDetailsPageObject fileDetails = article.clickVideoDetailsButton();
				fileDetails.verifyEmbeddedVideoIsPresent();	
				fileDetails.verifythumbnailIsPresent();
			}
			
			@Test(dataProvider="provideVideo", groups={"VetProvidersTests002", "VetTests"}) 
			public void ArticleVideo002_AddingProviderVideosRVModule(String videoUrl, String name)
			{
				PageObjectLogging.log("", videoUrl, true);
				CommonFunctions.logOut(driver);
				WikiArticlePageObject wiki = new WikiArticlePageObject(driver, Global.DOMAIN, "");
				wiki.openWikiPage();
				CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
				wiki.openArticle("MediaWiki:RelatedVideosGlobalList");
				WikiArticleEditMode RVmoduleMessageEdit = wiki.edit();		
				RVmoduleMessageEdit.deleteUnwantedVideoFromMessage(name);
				wiki = RVmoduleMessageEdit.clickOnPublishButton();
				wiki.openRandomArticleByUrl();
				VetAddVideoComponentObject vetAddingVideo = wiki.clickOnAddVideoRVModule();
				vetAddingVideo.addVideoByUrl(videoUrl);
				wiki.verifyVideoAddedToRVModule(name);
			}
}
