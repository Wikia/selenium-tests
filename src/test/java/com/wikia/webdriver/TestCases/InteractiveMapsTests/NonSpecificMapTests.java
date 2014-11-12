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
 */

public class NonSpecificMapTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(groups = { "NonSpecificMapTests_001", "NonSpecificMapTests", "InteractiveMaps" })
	public void NonSpecificMapTests_001_ClickMapAndVerifyCorrectRedirect() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		String mapUrl = specialMap.getMapLink(InteractiveMapsContent.SELECTED_MAP_INDEX);
		String mapTitle = specialMap.getMapTitle(InteractiveMapsContent.SELECTED_MAP_INDEX);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.SELECTED_MAP_INDEX);
		selectedMap.verifyMapOpened();
		selectedMap.verifyURL(mapUrl);
		selectedMap.verifyCreatedMapTitle(mapTitle);
	}

	@Test(groups = { "NonSpecificMapTests_002", "NonSpecificMapTests", "InteractiveMaps" })
	public void NonSpecificMapTests_002_VerifyLoginModalWhenAnon() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logOut(wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		CreateAMapComponentObject map = specialMap.clickCreateAMap();
		map.verifyLoginModal();
	}

	@Test(groups = { "NonSpecificMapTests_003", "NonSpecificMapTests", "InteractiveMaps" })
	public void NonSpecificMapTests_003_VerifyTemplateSearch() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		CreateAMapComponentObject createMapDialog = specialMap.clickCreateAMap();
		CreateACustomMapComponentObject customMapDialog = createMapDialog.clickCustomMap();
		customMapDialog.typeSearchTile(InteractiveMapsContent.TEMPLATE_NAME_TO_SEARCH_SHOULD_NOT_BE_FOUND);
		customMapDialog.verifyErrorExists();
		customMapDialog.clearSearchTitle();
		customMapDialog.typeSearchTile(InteractiveMapsContent.TEMPLATE_NAME_TO_SEARCH_SHOULD_BE_FOUND);
		customMapDialog.verifyTemplateListElementVisible(0);
	}

	@Test(groups = { "NonSpecificMapTests_004", "NonSpecificMapTests", "InteractiveMaps" })
	public void NonSpecificMapTests_004_VerifyMapZoomOptions() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.SELECTED_MAP_INDEX);
		selectedMap.verifyMapOpened();
		selectedMap.clickZoomOutButton();
		selectedMap.clickZoomInButton();
	}

	@Test(groups = { "NonSpecificMapTests_005", "NonSpecificMapTests", "InteractiveMaps" })
	public void NonSpecificMapTests_005_VerifyMapListElements() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		specialMap.verifyAmountMapOnTheList();
		specialMap.verifyCreateMapButtonExists();
		specialMap.verifyCorrectPagination();
	}

	@Test(groups = { "NonSpecificMapTests_006", "NonSpecificMapTests", "InteractiveMaps" })
	public void NonSpecificMapTests_006_VerifyLearnMoreLink() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		CreateAMapComponentObject createMapModal = specialMap.clickCreateAMap();
		createMapModal.verifyLearnMoreLinkRedirect(InteractiveMapsContent.LEARN_MORE_LINK);
	}

	@Test(groups = { "NonSpecificMapTests_007", "NonSpecificMapTests", "InteractiveMaps" })
	public void NonSpecificMapTests_007_VerifyCreateCustomMapErrors() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		CreateAMapComponentObject createMap = specialMap.clickCreateAMap();
		CreateACustomMapComponentObject customMap = createMap.clickCustomMap();
		TemplateComponentObject templateMap = customMap.selectTemplate(InteractiveMapsContent.SELECTED_TEMPLATE_INDEX);
		templateMap.clickNext();
		templateMap.verifyErrorExists();
	}

	@Test(groups = { "NonSpecificMapTests_008", "NonSpecificMapTests", "InteractiveMaps" })
	public void NonSpecificMapTests_008_VerifyMapIsDisplayedForAnons() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logOut(wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.SELECTED_MAP_INDEX);
		selectedMap.verifyMapOpened();
	}

	@Test(groups = { "NonSpecificMapTests_009", "NonSpecificMapTests", "InteractiveMaps" })
	public void NonSpecificMapTests_009_VerifyCreateMapButtonUnderContribution() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMaps = base.openSpecialInteractiveMaps(wikiURL);
		CreateAMapComponentObject createMap = specialMaps.clickCreateAMapUnderContributeButton();
		createMap.verifyRealMapAndCustomMapButtons();
	}
	
	@Test(groups = { "NonSpecificMapTests_010", "NonSpecificMapTests", "InteractiveMaps" })
	public void NonSpecificMapTests_010_VerifyFragmentContentTagVisibility() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.openEscapedFragmentMap(wikiURL, InteractiveMapsContent.ESCAPED_FRAGMENT_MAP_ID);
		selectedMap.verifyEscapedFragmentMetaTag();
	}
	
	@Test(groups = { "NonSpecificMapTests_011", "NonSpecificMapTests", "InteractiveMaps" })
	public void NonSpecificMapTests_011_VerifyEscapedFragmentPageContent() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.openEscapedFragmentMap(wikiURL, InteractiveMapsContent.ESCAPED_FRAGMENT_MAP_ID);
		selectedMap.verifyPoiCategoryTitle();
		selectedMap.verifyPoiPointTitle();
		selectedMap.verifyPoiPointDescription();
	}
}
