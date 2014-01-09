package com.wikia.webdriver.TestCases.HubsTests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.PageObjectsFactory.PageObject.HomePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.HomePageObject.HubName;
import com.wikia.webdriver.PageObjectsFactory.PageObject.HubBasePageObject;

/**
 *
 * @author Karol 'kkarolk' Kujawiak
 * @author Michal 'justptT' Nowierski
 */
public class HubsTests extends NewTestTemplateBeforeClass {

	@DataProvider
	private static final Object[][] provideHub() {
		return new Object[][] {
				{HubName.Video_Games},
				{HubName.Entertainment},
				{HubName.Lifestyle}
		};
	}

	Credentials credentials = config.getCredentials();

	@Test(dataProvider = "provideHub", groups = { "HubsTests001", "Hubs" , "Smoke4"})
	public void HubsTest001_verifyMosaicSliderShowsImagesOnHover(HubName hubName) {
		HomePageObject home = new HomePageObject(driver);
		HubBasePageObject hub = home.openHub(hubName, wikiCorporateURL);
		hub.verifyMosaicSliderImages();

		hub.mosaicSliderHoverOverImage(4);
		String currentLargeImageDescription = hub
				.mosaicSliderGetCurrentLargeImageDescription();

		hub.mosaicSliderHoverOverImage(3);
		hub.mosaicSliderVerifyLargeImageDescriptionNotEquals(currentLargeImageDescription);
		currentLargeImageDescription =  hub.mosaicSliderGetCurrentLargeImageDescription();

		hub.mosaicSliderHoverOverImage(2);
		hub.mosaicSliderVerifyLargeImageDescriptionNotEquals(currentLargeImageDescription);
		currentLargeImageDescription =  hub.mosaicSliderGetCurrentLargeImageDescription();

		hub.mosaicSliderHoverOverImage(1);
		hub.mosaicSliderVerifyLargeImageDescriptionNotEquals(currentLargeImageDescription);
		currentLargeImageDescription =  hub.mosaicSliderGetCurrentLargeImageDescription();

		hub.mosaicSliderHoverOverImage(0);
		hub.mosaicSliderVerifyLargeImageDescriptionNotEquals(currentLargeImageDescription);
	}


	@Test(dataProvider = "provideHub", groups = { "HubsTests008", "Hubs", "new" })
	/**
	 *  verify that from community module has its elements
	 */
	public void HubsTest002_verifyFromCommunityModuleHasItsElements(HubName hubName) {
		HomePageObject home = new HomePageObject(driver);
		HubBasePageObject hub= home.openHub(hubName, wikiCorporateURL);
		hub.verifyFromModuleHasImages();
		hub.verifyFromModuleHasHeadline();
		hub.verifyFromModuleHasUserAndWikiField();
		hub.verifyFromModuleHasQuatation();
	}

	@Test(dataProvider = "provideHub", groups = { "HubsTests011", "Hubs", "new" })
	/**
	 * click on 'Get Promoted' button and verify if modal appears and if its fields/buttons are working properly
	 */
	public void HubsTest003_VerifyArticleSuggestionWorksProperly(HubName hubName) {
		HomePageObject home = new HomePageObject(driver);
		home.logInCookie(credentials.userName2, credentials.password2);
		HubBasePageObject hub = home.openHub(hubName, wikiCorporateURL);
		hub.clickGetPromoted();
		hub.verifySuggestAVideoOrArticleModalAppeared();
		hub.verifySuggestAVideoOrArticleModalTopic();
		hub.verifySuggestVideoOrArticleButtonNotClickable();
		hub.suggestArticleTypeIntoWhatVideoField(hub.getTimeStamp());
		hub.suggestArticleTypeIntoWhyCoolField(hub.getTimeStamp());
		hub.verifySuggestVideoOrArticleButtonClickable();
		hub.click_X_toCloseSuggestAVideoOrArticle();
		hub.verifySuggestAVideoOrArticleModalDisappeared();
		hub.clickGetPromoted();
		hub.verifySuggestAVideoOrArticleModalAppeared();
		hub.verifySuggestAVideoOrArticleModalTopic();
		hub.verifySuggestVideoOrArticleButtonNotClickable();
		hub.click_Cancel_toCloseSuggestAVideoOrArticle();
		hub.verifySuggestAVideoOrArticleModalDisappeared();
	}
}
