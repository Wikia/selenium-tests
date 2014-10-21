package com.wikia.webdriver.TestCases.InteractiveMapsTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.InteractiveMapsContent;
import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.CreateACustomMapComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.CreateAMapComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.CreatePinTypesComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.CreateRealMapComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.TemplateComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapsPageObject;

/**
 * @author: Rodrigo Molinero Gomez
 * @author: Lukasz Jedrzejczak
 * @author: Lukasz Nowak
 * @ownership: Mobile Web IM01: Creating a custom new map based on new image
 *             upload v IM02: Create a custom new map based on existing template
 *             v IM03: Create a real map flow v IM09: Verify back button works
 *             correctly on every dialog IM28: Make sure visibility of all close
 *             buttons through map creation flow
 */

public class MapFlowTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(groups = { "InteractiveMaps_001", "MapFlowTests", "InteractiveMaps" }, enabled = false)
	public void InteractiveMaps_001_CreateCustomMapNewImageUpload() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		CreateAMapComponentObject map = specialMap.clickCreateAMap();
		CreateACustomMapComponentObject customMap = map.clickCustomMap();
		TemplateComponentObject template = customMap.selectFileToUpload(PageContent.file);
		template.verifyTemplateImagePreview();
		template.typeMapName(InteractiveMapsContent.mapName);
		InteractiveMapsContent.templateName = base.getTimeStamp();
		template.typeTemplateName(InteractiveMapsContent.templateName);
		CreatePinTypesComponentObject pinDialog = template.clickNext();
		pinDialog.typePinTypeTitle(InteractiveMapsContent.pinTypeName, InteractiveMapsContent.pinTypeIndex);
		InteractiveMapPageObject createdMap = pinDialog.clickSave();
		createdMap.verifyCreatedMapTitle(InteractiveMapsContent.mapName);
		createdMap.verifyMapOpened();
		createdMap.verifyCreatedPinTypesForNewMap();
		createdMap.verifyControlButtonsAreVisible();
	}

	@Test(groups = { "InteractiveMaps_002", "MapFlowTests", "InteractiveMaps" })
	public void InteractiveMaps_002_CreateCustomMapWithExistingTemplate() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		CreateAMapComponentObject map = specialMap.clickCreateAMap();
		CreateACustomMapComponentObject customMap = map.clickCustomMap();
		String selectedImageName = customMap.getSelectedTemplateImageName(InteractiveMapsContent.selectedTemplateIndex);
		TemplateComponentObject template = customMap.selectTemplate(InteractiveMapsContent.selectedTemplateIndex);
		template.verifyTemplateImage(selectedImageName);
		template.typeMapName(InteractiveMapsContent.mapName);
		CreatePinTypesComponentObject pinDialog = template.clickNext();
		pinDialog.typePinTypeTitle(InteractiveMapsContent.pinTypeName, InteractiveMapsContent.pinTypeIndex);
		InteractiveMapPageObject createdMap = pinDialog.clickSave();
		createdMap.verifyCreatedMapTitle(InteractiveMapsContent.mapName);
		createdMap.verifyMapOpened();
		createdMap.verifyCreatedPinTypesForNewMap();
		createdMap.verifyControlButtonsAreVisible();
	}

	@Test(groups = { "InteractiveMaps_003", "MapFlowTests", "InteractiveMaps" })
	public void InteractiveMaps_003_CreateRealMap() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		CreateAMapComponentObject map = specialMap.clickCreateAMap();
		CreateRealMapComponentObject realMap = map.clickRealMap();
		realMap.verifyRealMapPreviewImage();
		realMap.typeMapName(InteractiveMapsContent.mapName);
		CreatePinTypesComponentObject pinDialog = realMap.clickNext();
		pinDialog.typePinTypeTitle(InteractiveMapsContent.pinTypeName, InteractiveMapsContent.pinTypeIndex);
		InteractiveMapPageObject createdMap = pinDialog.clickSave();
		createdMap.verifyMapOpened();
		createdMap.verifyControlButtonsAreVisible();
	}

	@Test(groups = { "InteractiveMaps_009", "MapFlowTests", "InteractiveMaps" })
	public void InteractiveMaps_009_VerifyBackButtonWorksCorrectly() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		CreateAMapComponentObject map = specialMap.clickCreateAMap();
		CreateACustomMapComponentObject customMap = map.clickCustomMap();
		TemplateComponentObject template = customMap.selectTemplate(InteractiveMapsContent.selectedTemplateIndex);
		template.verifyTemplateImagePreview();
		customMap = template.clickBack();
		customMap.verifyTemplateListElementVisible(InteractiveMapsContent.selectedTemplateIndex);
		map = customMap.clickBack();
		map.verifyRealMapAndCustomMapButtons();
		CreateRealMapComponentObject realMap = map.clickRealMap();
		realMap.verifyRealMapPreviewImage();
		map = realMap.clickBack();
		map.verifyRealMapAndCustomMapButtons();
	}

	@Test(groups = { "InteractiveMaps_028", "MapFlowTests", "InteractiveMaps" })
	public void InteractiveMaps_028_VerifyCloseButtonsInCreationMapFlow() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		CreateAMapComponentObject createMapModal = specialMap.clickCreateAMap();
		specialMap = createMapModal.clickCloseButton();
		specialMap.verifyCreateMapModalNotExists();
		createMapModal = specialMap.clickCreateAMap();
		CreateRealMapComponentObject realMapModal = createMapModal.clickRealMap();
		specialMap = realMapModal.clickClose();
		specialMap.verifyCreateMapModalNotExists();
		createMapModal = specialMap.clickCreateAMap();
		CreateACustomMapComponentObject customMapModal = createMapModal.clickCustomMap();
		customMapModal.clickCloseButton();
		specialMap.verifyCreateMapModalNotExists();
	}
}
