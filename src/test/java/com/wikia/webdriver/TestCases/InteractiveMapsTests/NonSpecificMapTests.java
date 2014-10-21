package com.wikia.webdriver.TestCases.InteractiveMapsTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.InteractiveMapsContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.CreateACustomMapComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.CreateAMapComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.TemplateComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapsPageObject;

/**
 * @author: Rodrigo Molinero Gomez
 * @author: Lukasz Jedrzejczak
 * @author: Lukasz Nowak
 * @ownership: Mobile Web
 * 
 *             - Special:Map and SpecialMaps Page Tests IM04: Click on a map and
 *             verify correct redirect and URL v IM05: Click create a map button
 *             as anon and make sure log in modal is displayed v IM13: Test
 *             template search works correctly for unexisting and existing
 *             templates v IM20: Verify zoom in and zoom out of map v IM22:
 *             required elements from page are displayed: create new map link,
 *             list of maps (max 10),pagination IM23 : Click Create a map button
 *             and check that Learn more link redirects to maps.wikia.com IM26:
 *             Verify name must beset when creating a new custom map IM29:
 *             Verify that map is visible for anon IM44: Verify Create Map
 *             Button in Contribute dropdown
 */

public class NonSpecificMapTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(groups = { "InteractiveMaps_004", "NonSpecificMapTests", "InteractiveMaps" })
	public void InteractiveMaps_004_ClickMapAndVerifyCorrectRedirect() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		String mapUrl = specialMap.getMapLink(InteractiveMapsContent.selectedMapIndex);
		String mapTitle = specialMap.getMapTitle(InteractiveMapsContent.selectedMapIndex);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
		selectedMap.verifyMapOpened();
		selectedMap.verifyURL(mapUrl);
		selectedMap.verifyCreatedMapTitle(mapTitle);
	}

	@Test(groups = { "InteractiveMaps_005", "NonSpecificMapTests", "InteractiveMaps" })
	public void InteractiveMaps_005_VerifyLoginModalWhenAnon() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logOut(wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		CreateAMapComponentObject map = specialMap.clickCreateAMap();
		map.verifyLoginModal();
	}

	@Test(groups = { "InteractiveMaps_013", "NonSpecificMapTests", "InteractiveMaps" })
	public void InteractiveMaps_013_VerifyTemplateSearch() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		CreateAMapComponentObject createMapDialog = specialMap.clickCreateAMap();
		CreateACustomMapComponentObject customMapDialog = createMapDialog.clickCustomMap();
		customMapDialog.typeSearchTile(InteractiveMapsContent.templateNameToSearchShouldNotBeFound);
		customMapDialog.verifyErrorExists();
		customMapDialog.clearSearchTitle();
		customMapDialog.typeSearchTile(InteractiveMapsContent.templateNameToSearchShouldBeFound);
		customMapDialog.verifyTemplateListElementVisible(0);
	}

	@Test(groups = { "InteractiveMaps_020", "NonSpecificMapTests", "InteractiveMaps" })
	public void InteractiveMaps_020_VerifyMapZoomOptions() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
		selectedMap.verifyMapOpened();
		selectedMap.clickZoomOutButton();
		selectedMap.clickZoomInButton();
	}

	@Test(groups = { "InteractiveMaps_022", "NonSpecificMapTests", "InteractiveMaps" })
	public void InteractiveMaps_022_VerifyMapListElements() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		specialMap.verifyAmountMapOnTheList();
		specialMap.verifyCreateMapButtonExists();
		specialMap.verifyCorrectPagination();
	}

	@Test(groups = { "InteractiveMaps_023", "NonSpecificMapTests", "InteractiveMaps" })
	public void InteractiveMaps_023_VerifyLearnMoreLink() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		CreateAMapComponentObject createMapModal = specialMap.clickCreateAMap();
		createMapModal.verifyLearnMoreLinkRedirect(InteractiveMapsContent.learnMoreLink);
	}

	@Test(groups = { "InteractiveMaps_026", "NonSpecificMapTests", "InteractiveMaps" })
	public void InteractiveMaps_026_VerifyCreateCustomMapErrors() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		CreateAMapComponentObject createMap = specialMap.clickCreateAMap();
		CreateACustomMapComponentObject customMap = createMap.clickCustomMap();
		TemplateComponentObject templateMap = customMap.selectTemplate(InteractiveMapsContent.selectedTemplateIndex);
		templateMap.clickNext();
		templateMap.verifyErrorExists();
	}

	@Test(groups = { "InteractiveMaps_029", "NonSpecificMapTests", "InteractiveMaps" })
	public void InteractiveMaps_029_VerifyMapIsDisplayedForAnons() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logOut(wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
		selectedMap.verifyMapOpened();
	}

	@Test(groups = { "InteractiveMaps_044", "NonSpecificMapTests", "InteractiveMaps" })
	public void InteractiveMaps_044_VerifyCreateMapButtonUnderContribution() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMaps = base.openSpecialInteractiveMaps(wikiURL);
		CreateAMapComponentObject createMap = specialMaps.clickCreateAMapUnderContributeButton();
		createMap.verifyRealMapAndCustomMapButtons();
	}
	
	@Test(groups = { "InteractiveMaps_048", "NonSpecificMapTests", "InteractiveMaps" })
	public void InteractiveMaps_048_VerifyFragmentContentTagVisibility() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.openEscapedFragmentMap(wikiURL, InteractiveMapsContent.escapedFragmentMapId);
		selectedMap.verifyEscapedFragmentMetaTag();
	}
	
	@Test(groups = { "InteractiveMaps_049", "NonSpecificMapTests", "InteractiveMaps" })
	public void InteractiveMaps_049_VerifyEscapedFragmentPageContent() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.openEscapedFragmentMap(wikiURL, InteractiveMapsContent.escapedFragmentMapId);
		selectedMap.verifyPoiCategoryTitle();
		selectedMap.verifyPoiPointTitle();
		selectedMap.verifyPoiPointDescription();
	}
}
