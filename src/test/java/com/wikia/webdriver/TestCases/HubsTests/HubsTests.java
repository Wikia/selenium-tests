package com.wikia.webdriver.TestCases.HubsTests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjects.PageObject.HomePageObject;
import com.wikia.webdriver.PageObjects.PageObject.HubBasePageObject;
import com.wikia.webdriver.PageObjects.PageObject.Hubs.EntertainmentHubPageObject;
import com.wikia.webdriver.PageObjects.PageObject.Hubs.LifestyleHubPageObject;
import com.wikia.webdriver.PageObjects.PageObject.Hubs.VideoGamesHubPageObject;

public class HubsTests extends TestTemplate {

	private static final VideoGamesHubPageObject VGHub = null;
	private static final LifestyleHubPageObject LHub = null;
	private static final EntertainmentHubPageObject EHub = null;
	private static HomePageObject home = null;

	@DataProvider
	private static final Object[][] provideHub() {
		return new Object[][] {
				{ VGHub, "VideoGamesHub", Global.LIVE_DOMAIN + "Video_Games" },
				{ EHub, "EntertainmentHub",
						Global.LIVE_DOMAIN + "Entertainment" },
				{ LHub, "LifestyleHub", Global.LIVE_DOMAIN + "Lifestyle" } };
	}

	@Test(dataProvider = "provideHub", groups = { "HubsTests001", "Hubs" })
	// https://internal.wikia-inc.com/wiki/Hubs/QA/Hubs_Test_Cases#Module_1_.28Mosaic_Slider.29_Test_Cases
	// The test covers underscored steps from test case documentation - see link
	// above
	public void HubsTest001_verifyMosaicSliderShowsImagesOnHover(HubBasePageObject Hub, String HubName, String HubURL) {
		home = new HomePageObject(driver);
		home.openHomePage();
		Hub = home.OpenHub(HubName);
		Hub.verifyURL(HubURL);
		Hub.MosaicSliderVerifyHasImages();
		Hub.MosaicSliderHoverOverImage(5);
		String CurrentLargeImageDescription = Hub
				.MosaicSliderGetCurrentLargeImageDescription();
		Hub.MosaicSliderHoverOverImage(4);
		CurrentLargeImageDescription = Hub
				.MosaicSliderVerifyLargeImageChangeAndGetCurrentDescription(CurrentLargeImageDescription);
		Hub.MosaicSliderHoverOverImage(3);
		CurrentLargeImageDescription = Hub
				.MosaicSliderVerifyLargeImageChangeAndGetCurrentDescription(CurrentLargeImageDescription);
		Hub.MosaicSliderHoverOverImage(2);
		CurrentLargeImageDescription = Hub
				.MosaicSliderVerifyLargeImageChangeAndGetCurrentDescription(CurrentLargeImageDescription);
		Hub.MosaicSliderHoverOverImage(1);
		CurrentLargeImageDescription = Hub
				.MosaicSliderVerifyLargeImageChangeAndGetCurrentDescription(CurrentLargeImageDescription);
		home = Hub.BackToHomePage();
	}

	@Test(dataProvider = "provideHub", groups = { "HubsTests002", "Hubs" })
	// https://internal.wikia-inc.com/wiki/Hubs/QA/Hubs_Test_Cases#Module_2_.28News_Tabs.29_Test_Cases
	// The test covers underscored steps from test case documentation - see link
	// above
	public void HubsTest002_verifyNewsTabsWorkProperly(HubBasePageObject Hub, String HubName, String HubURL) {
		home = new HomePageObject(driver);
		home.openHomePage();
		Hub = home.OpenHub(HubName);
		Hub.verifyURL(HubURL);
		Hub.VerifyNewsTabsPresence(1);
		Hub.ClickOnNewsTab(3);
		Hub.VerifyNewsTabsPresence(3);
		Hub.ClickOnNewsTab(2);
		Hub.VerifyNewsTabsPresence(2);
		Hub.ClickOnNewsTab(1);
		Hub.VerifyNewsTabsPresence(1);
		home = Hub.BackToHomePage();
	}

	@Test(dataProvider = "provideHub", groups = { "HubsTests003", "Hubs", "new" })
	// https://internal.wikia-inc.com/wiki/Hubs/QA/Hubs_Test_Cases#Module_3_.28Videos_Module.29_Test_Cases
	// The test covers underscored steps from test case documentation - see link
	// above
	public void HubsTest003_verifyRelatedVideosScrolling(HubBasePageObject Hub, String HubName, String HubURL) {
		home = new HomePageObject(driver);
		home.openHomePage();
		Hub = home.OpenHub(HubName);
		Hub.verifyURL(HubURL);
		Hub.VerifyRelatedVideosPresence();
		Hub.RelatedVideosScrollRight();
		Hub.VerifyRelatedVideosPresence();
		Hub.RelatedVideosScrollRight();
		Hub.VerifyRelatedVideosPresence();
		Hub.RelatedVideosScrollLeft();
		Hub.VerifyRelatedVideosPresence();
		Hub.RelatedVideosScrollLeft();
		Hub.VerifyRelatedVideosPresence();
	}

	@Test(dataProvider = "provideHub", groups = { "HubsTests004", "Hubs", "new" })
	// https://internal.wikia-inc.com/wiki/Hubs/QA/Hubs_Test_Cases#Module_3_.28Videos_Module.29_Test_Cases
	// The test covers underscored steps from test case documentation - see link
	// above
	public void HubsTest004_verifyRelatedVideosAppearAfterScrolling(HubBasePageObject Hub, String HubName, String HubURL) {
		home = new HomePageObject(driver);
		home.openHomePage();
		Hub = home.OpenHub(HubName);
		Hub.verifyURL(HubURL);
		Hub.ClickOnRelatedVideo(1);
		Hub.VerifyVideoPlayerAppeared();
		Hub.Click_X_toCloseVideoPlayer();
		Hub.verifyVideoPlayerDisappeared();
		Hub.RelatedVideosScrollRight();
		Hub.VerifyRelatedVideosPresence();
		Hub.ClickOnRelatedVideo(2);
		Hub.VerifyVideoPlayerAppeared();
		Hub.Click_X_toCloseVideoPlayer();
		Hub.verifyVideoPlayerDisappeared();
	}

	@Test(dataProvider = "provideHub", groups = { "HubsTests005", "Hubs", "new" })
	// https://internal.wikia-inc.com/wiki/Hubs/QA/Hubs_Test_Cases#Module_3_.28Videos_Module.29_Test_Cases
	public void HubsTest005_suggestAVideoAndClickCancelToClose(HubBasePageObject Hub, String HubName, String HubURL) {
		home = new HomePageObject(driver);
		home.openHomePage();
		CommonFunctions.logInCookie(Properties.userName2, Properties.password2, driver);
		Hub = home.OpenHub(HubName);
		Hub.verifyURL(HubURL);
//		CommonFunctions.logIn(Properties.userName2, Properties.password2);
		Hub.ClickSuggestAVideo();
		Hub.VerifySuggestAVideoOrArticleModalAppeared();
		Hub.VerifySuggestAVideoOrArticleModalTopic("Suggest a Video");
		Hub.Click_Cancel_toCloseSuggestAVideoOrArticle();
		Hub.verifySuggestAVideoOrArticleModalDisappeared();
	}

	@Test(dataProvider = "provideHub", groups = { "HubsTests006", "Hubs", "new" })
	// https://internal.wikia-inc.com/wiki/Hubs/QA/Hubs_Test_Cases#Module_3_.28Videos_Module.29_Test_Cases
	public void HubsTest006_suggestAVideoAndClickXtoClose(HubBasePageObject Hub, String HubName, String HubURL) {
		home = new HomePageObject(driver);
		home.openHomePage();
		Hub = home.OpenHub(HubName);
		Hub.verifyURL(HubURL);
		CommonFunctions.logIn(Properties.userName2, Properties.password2);
		Hub.ClickSuggestAVideo();
		Hub.VerifySuggestAVideoOrArticleModalAppeared();
		Hub.VerifySuggestAVideoOrArticleModalTopic("Suggest a Video");
		Hub.Click_X_toCloseSuggestAVideoOrArticle();
		Hub.verifySuggestAVideoOrArticleModalDisappeared();
	}

	@Test(dataProvider = "provideHub", groups = { "HubsTests007", "Hubs", "new" })
	// https://internal.wikia-inc.com/wiki/Hubs/QA/Hubs_Test_Cases#Module_3_.28Videos_Module.29_Test_Cases
	public void HubsTest007_VerifyVideoSuggestionWorksProperly(HubBasePageObject Hub, String HubName, String HubURL) {
		home = new HomePageObject(driver);
		home.openHomePage();
		Hub = home.OpenHub(HubName);
		Hub.verifyURL(HubURL);
		CommonFunctions.logIn(Properties.userName2, Properties.password2);
		Hub.ClickSuggestAVideo();
		Hub.VerifySuggestAVideoOrArticleModalAppeared();
		Hub.VerifySuggestAVideoOrArticleModalTopic("Suggest a Video");
		Hub.VerifySuggestVideoOrArticleButtonNotClickable();
		Hub.SuggestVideoTypeIntoWhatVideoField("random text");
		Hub.SuggestVideoTypeIntoWhichWikiField("random text");
		Hub.VerifySuggestVideoOrArticleButtonClickable();
	}

	@Test(dataProvider = "provideHub", groups = { "HubsTests008", "Hubs", "new" })
	// https://internal.wikia-inc.com/wiki/Hubs/QA/Hubs_Test_Cases#Module_3_.28Videos_Module.29_Test_Cases
	public void HubsTest008_verifyFromCommunityModuleHasItsElements(HubBasePageObject Hub, String HubName, String HubURL) {
		home = new HomePageObject(driver);
		home.openHomePage();
		Hub = home.OpenHub(HubName);
		Hub.verifyURL(HubURL);
		Hub.verifyFromModuleHasImages();
		Hub.verifyFromModuleHasHeadline();
		Hub.verifyFromModuleHasUserAndWikiField();
		Hub.verifyFromModuleHasQuatation();
	}
	
	@Test(dataProvider = "provideHub", groups = { "HubsTests009", "Hubs", "new" })
	// https://internal.wikia-inc.com/wiki/Hubs/QA/Hubs_Test_Cases#Module_3_.28Videos_Module.29_Test_Cases
	public void HubsTest009_suggestAVideoAndClickCancelToClose(HubBasePageObject Hub, String HubName, String HubURL) {
		home = new HomePageObject(driver);
		home.openHomePage();
		Hub = home.OpenHub(HubName);
		Hub.verifyURL(HubURL);
		CommonFunctions.logIn(Properties.userName2, Properties.password2);
		Hub.ClickSuggestAnArticle();
		Hub.VerifySuggestAVideoOrArticleModalAppeared();
		Hub.VerifySuggestAVideoOrArticleModalTopic("Suggest an Article");
		Hub.Click_Cancel_toCloseSuggestAVideoOrArticle();
		Hub.verifySuggestAVideoOrArticleModalDisappeared();
	}
	
	@Test(dataProvider = "provideHub", groups = { "HubsTests010", "Hubs", "new" })
	// https://internal.wikia-inc.com/wiki/Hubs/QA/Hubs_Test_Cases#Module_3_.28Videos_Module.29_Test_Cases
	public void HubsTest010_suggestAVideoAndClickXtoClose(HubBasePageObject Hub, String HubName, String HubURL) {
		home = new HomePageObject(driver);
		home.openHomePage();
		Hub = home.OpenHub(HubName);
		Hub.verifyURL(HubURL);
		CommonFunctions.logIn(Properties.userName2, Properties.password2);
		Hub.ClickSuggestAnArticle();
		Hub.VerifySuggestAVideoOrArticleModalAppeared();
		Hub.VerifySuggestAVideoOrArticleModalTopic("Suggest an Article");
		Hub.Click_X_toCloseSuggestAVideoOrArticle();
		Hub.verifySuggestAVideoOrArticleModalDisappeared();
	}
	
	@Test(dataProvider = "provideHub", groups = { "HubsTests011", "Hubs", "new" })
	// https://internal.wikia-inc.com/wiki/Hubs/QA/Hubs_Test_Cases#Module_3_.28Videos_Module.29_Test_Cases
	public void HubsTest011_VerifyArticleSuggestionWorksProperly(HubBasePageObject Hub, String HubName, String HubURL) {
		home = new HomePageObject(driver);
		home.openHomePage();
		Hub = home.OpenHub(HubName);
		Hub.verifyURL(HubURL);
		CommonFunctions.logIn(Properties.userName2, Properties.password2);
		Hub.ClickSuggestAnArticle();
		Hub.VerifySuggestAVideoOrArticleModalAppeared();
		Hub.VerifySuggestAVideoOrArticleModalTopic("Suggest an Article");
		Hub.VerifySuggestVideoOrArticleButtonNotClickable();
		Hub.SuggestArticleTypeIntoWhatVideoField("random text");
		Hub.SuggestArticleTypeIntoWhyCoolField("random text");
		Hub.VerifySuggestVideoOrArticleButtonClickable();
	}

	@Test(dataProvider = "provideHub", groups = { "HubsTests012", "Hubs" })
	// https://internal.wikia-inc.com/wiki/Hubs/QA/Hubs_Test_Cases#Module_5_.28The_Pulse.29_Test_Cases
	public void HubsTest012_verifyThePulseModule(HubBasePageObject Hub, String HubName, String HubURL) {
		home = new HomePageObject(driver);
		home.openHomePage();
		Hub = home.OpenHub(HubName);
		Hub.verifyURL(HubURL);
		Hub.verifyPulseModuleAppears();
		Hub.verifyFacebookButtonAppears();
		Hub.verifyFacebookButtonIsClickable();
		Hub.verifyTwitterButtonAppears();
		Hub.verifyTwitterButtonIsClickable();
		Hub.verifyGoogleButtonAppears();
		Hub.verifyGoogleButtonIsClickable();
		Hub.verifyStatisticsAreDisplayed();
		Hub.verifyWikiaSearchFieldIsDisplayed();
	}

	@Test(dataProvider = "provideHub", groups = { "HubsTests013", "Hubs" })
	// https://internal.wikia-inc.com/wiki/Hubs/QA/Hubs_Test_Cases#Module_7_.28Top_Wikis.29_Test_Cases
	// The test covers underscored steps from test case documentation - see link
	// above
	public void HubsTest013_verifyTopWikisModule(HubBasePageObject Hub, String HubName, String HubURL) {
		home = new HomePageObject(driver);
		home.openHomePage();
		Hub = home.OpenHub(HubName);
		Hub.verifyURL(HubURL);
		Hub.verifyTopWikisModuleAppears();
		Hub.verifyWikisAreListedInTopWikisModule();
	}
}
