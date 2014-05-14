package com.wikia.webdriver.TestCases.HubsTests;

import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.PageObjectsFactory.PageObject.HomePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.HubBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject.HubName;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialManageWikiaHome;

/**
 *
 * @author Karol 'kkarolk' Kujawiak
 * @author Michal 'justptT' Nowierski
 * @author Robert 'rochan' Chan
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
	private final String corpWikiName = "corp";

	@Test(enabled = false, dataProvider = "provideHub", groups = { "HubsTests001", "Hubs" , "Smoke4"})
	public void HubsTest001_verifyMosaicSliderShowsImagesOnHover(HubName hubName) {
		HomePageObject home = new HomePageObject(driver);
		HubBasePageObject hub = home.openHubByUrl(hubName, wikiCorporateURL);
		hub.verifyMosaicSliderImages();

		hub.mosaicSliderHoverOverImage(4);
		String currentLargeImageDescription = hub.mosaicSliderGetCurrentLargeImageDescription();

		hub.mosaicSliderHoverOverImage(3);
		hub.mosaicSliderVerifyLargeImageDescriptionDifferent(currentLargeImageDescription);
		currentLargeImageDescription =  hub.mosaicSliderGetCurrentLargeImageDescription();

		hub.mosaicSliderHoverOverImage(2);
		hub.mosaicSliderVerifyLargeImageDescriptionDifferent(currentLargeImageDescription);
		currentLargeImageDescription =  hub.mosaicSliderGetCurrentLargeImageDescription();

		hub.mosaicSliderHoverOverImage(1);
		hub.mosaicSliderVerifyLargeImageDescriptionDifferent(currentLargeImageDescription);
		currentLargeImageDescription =  hub.mosaicSliderGetCurrentLargeImageDescription();

		hub.mosaicSliderHoverOverImage(0);
		hub.mosaicSliderVerifyLargeImageDescriptionDifferent(currentLargeImageDescription);
	}


	@Test(dataProvider = "provideHub", groups = { "HubsTests002", "Hubs"})
	/**
	 *  verify that from community module has its elements
	 */
	public void HubsTest002_verifyFromCommunityModuleHasItsElements(HubName hubName) {
		HomePageObject home = new HomePageObject(driver);
		HubBasePageObject hub= home.openHubByUrl(hubName, wikiCorporateURL);
		hub.verifyFromModuleHasImages();
		hub.verifyFromModuleHasHeadline();
		hub.verifyFromModuleHasUserAndWikiField();
		hub.verifyFromModuleHasQuatation();
	}

	@Test(dataProvider = "provideHub", groups = { "HubsTests003", "Hubs"})
	/**
	 * click on 'Get Promoted' button and verify if modal appears and if its fields/buttons are working properly
	 */
	public void HubsTest003_VerifyArticleSuggestionWorksProperly(HubName hubName) {
		HomePageObject home = new HomePageObject(driver);
		home.logInCookie(credentials.userName2, credentials.password2);
		HubBasePageObject hub = home.openHubByUrl(hubName, wikiCorporateURL);
		hub.clickGetPromoted();
		hub.verifySuggestAVideoOrArticleModalAppeared();
		hub.verifySuggestAVideoOrArticleModalTopic();
		hub.verifySuggestVideoOrArticleButtonNotClickable();
		hub.suggestArticleTypeIntoWhatVideoField(hub.getTimeStamp());
		hub.suggestArticleTypeIntoWhyCoolField(hub.getTimeStamp());
		hub.verifySuggestVideoOrArticleButtonClickable();
		hub.closeSuggestAVideoOrArticleByXButton();
		hub.verifySuggestAVideoOrArticleModalDisappeared();
		hub.clickGetPromoted();
		hub.verifySuggestAVideoOrArticleModalAppeared();
		hub.verifySuggestAVideoOrArticleModalTopic();
		hub.verifySuggestVideoOrArticleButtonNotClickable();
		hub.closeSuggestAVideoOrArticleCancelButton();
		hub.verifySuggestAVideoOrArticleModalDisappeared();
	}

	/**
	 * skipped due "promoted wikis" feature
	 */
	@Test(enabled = false, groups = { "HubsTests004", "Hubs"})
	public void HubsTests004_VerifyCorporateSlotCollection() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff);
		String wikiCorpSetupURL = urlBuilder.getUrlForWiki(corpWikiName);
		SpecialManageWikiaHome manageWikia = base.openSpecialManageWikiaHomePage(wikiCorpSetupURL);
		HashMap<String, Integer> slotDesiredSetup = manageWikia.getSlotSetup();
		HomePageObject home = base.openCorporateHomePage(wikiCorporateURL);
		HashMap<String, Integer> slotCurrentSetup = home.getVisualizationWikisSetup();
		home.verifyVisualizationURLs(slotDesiredSetup, slotCurrentSetup);
	}

	@Test(groups = { "HubsTests005", "Hubs", "new" })
	/**
	 * Verify that each language drop down  goes to the correct page
	 */
	public void HubsTest005_VerifyLanguagesSelection() {
		HomePageObject home = new HomePageObject(driver);
		home.openCorporateHomePage(wikiCorporateURL);
		home.verifyLanguageDropdownURLs();
	}

	/**
	 * Verify that links in Global Navigation are working
	 */
	@Test(enabled = false, dataProvider = "provideHub", groups = { "HubsTests006", "Hubs" })
	public void HubsTest006_VerifyLinkInGlobalNavigation(HubName hubName) {
		HomePageObject home = new HomePageObject(driver);
		home.openCorporateHomePage(wikiCorporateURL);
		HubBasePageObject hub = new HubBasePageObject(driver);
		hub.clickGlobalNavLink(hubName);
		hub.verifyHubTitle(hubName);
		hub.verifyHubUrl(hubName);
	}

	/**
	 * Verify that links in WikiaBar are working
	 */
	@Test(enabled = false, dataProvider = "provideHub", groups = { "HubsTests007", "Hubs" })
	public void HubsTest007_VerifyLinkInWikiaBar(HubName hubName) {
		HomePageObject home = new HomePageObject(driver);
		home.openCorporateHomePage(wikiCorporateURL);
		HubBasePageObject hub = new HubBasePageObject(driver);
		hub.clickWikiaBarLink(hubName);
		hub.verifyHubTitle(hubName);
		hub.verifyHubUrl(hubName);
	}
}
