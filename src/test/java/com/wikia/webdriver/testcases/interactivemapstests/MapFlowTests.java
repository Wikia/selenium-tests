package com.wikia.webdriver.testcases.interactivemapstests;

import com.wikia.webdriver.common.contentpatterns.InteractiveMapsContent;
import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.*;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapsPageObject;
import org.testng.annotations.Test;

/**
 * @author: Rodrigo Molinero Gomez
 * @author: Lukasz Jedrzejczak
 * @author: Lukasz Nowak
 * @ownership: Mobile Web
 */

public class MapFlowTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(groups = {"MapFlowTests_001", "MapFlowTests", "InteractiveMaps"}, enabled = false)
	public void MapFlowTests_001_CreateCustomMapNewImageUpload() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		CreateAMapComponentObject map = specialMap.clickCreateAMap();
		CreateACustomMapComponentObject customMap = map.clickCustomMap();
		TemplateComponentObject template = customMap.selectFileToUpload(PageContent.FILE);
		template.verifyTemplateImagePreview();
		template.typeMapName(InteractiveMapsContent.MAP_NAME);
		InteractiveMapsContent.TEMPLATE_NAME = base.getTimeStamp();
		template.typeTemplateName(InteractiveMapsContent.TEMPLATE_NAME);
		CreatePinTypesComponentObject pinDialog = template.clickNext();
		pinDialog.typePinTypeTitle(InteractiveMapsContent.PIN_TYPE_NAME, InteractiveMapsContent.PIN_TYPE_INDEX);
		InteractiveMapPageObject createdMap = pinDialog.clickSave();
		createdMap.verifyCreatedMapTitle(InteractiveMapsContent.MAP_NAME);
		createdMap.verifyMapOpened();
		createdMap.verifyCreatedPinTypesForNewMap();
		createdMap.verifyControlButtonsAreVisible();
	}

	@Test(groups = {"MapFlowTests_002", "MapFlowTests", "InteractiveMaps"})
	public void MapFlowTests_002_CreateCustomMapWithExistingTemplate() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		CreateAMapComponentObject map = specialMap.clickCreateAMap();
		CreateACustomMapComponentObject customMap = map.clickCustomMap();
		String selectedImageName = customMap.getSelectedTemplateImageName(InteractiveMapsContent.SELECTED_TEMPLATE_INDEX);
		TemplateComponentObject template = customMap.selectTemplate(InteractiveMapsContent.SELECTED_TEMPLATE_INDEX);
		template.verifyTemplateImage(selectedImageName);
		template.typeMapName(InteractiveMapsContent.MAP_NAME);
		CreatePinTypesComponentObject pinDialog = template.clickNext();
		pinDialog.typePinTypeTitle(InteractiveMapsContent.PIN_TYPE_NAME, InteractiveMapsContent.PIN_TYPE_INDEX);
		InteractiveMapPageObject createdMap = pinDialog.clickSave();
		createdMap.verifyCreatedMapTitle(InteractiveMapsContent.MAP_NAME);
		createdMap.verifyMapOpened();
		createdMap.verifyCreatedPinTypesForNewMap();
		createdMap.verifyControlButtonsAreVisible();
	}

	@Test(groups = {"MapFlowTests_003", "MapFlowTests", "InteractiveMaps"})
	public void MapFlowTests_003_CreateRealMap() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		CreateAMapComponentObject map = specialMap.clickCreateAMap();
		CreateRealMapComponentObject realMap = map.clickRealMap();
		realMap.verifyRealMapPreviewImage();
		realMap.typeMapName(InteractiveMapsContent.MAP_NAME);
		CreatePinTypesComponentObject pinDialog = realMap.clickNext();
		pinDialog.typePinTypeTitle(InteractiveMapsContent.PIN_TYPE_NAME, InteractiveMapsContent.PIN_TYPE_INDEX);
		InteractiveMapPageObject createdMap = pinDialog.clickSave();
		createdMap.verifyMapOpened();
		createdMap.verifyControlButtonsAreVisible();
	}

	@Test(groups = {"MapFlowTests_004", "MapFlowTests", "InteractiveMaps"})
	public void MapFlowTests_004_VerifyBackButtonWorksCorrectly() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		CreateAMapComponentObject map = specialMap.clickCreateAMap();
		CreateACustomMapComponentObject customMap = map.clickCustomMap();
		TemplateComponentObject template = customMap.selectTemplate(InteractiveMapsContent.SELECTED_TEMPLATE_INDEX);
		template.verifyTemplateImagePreview();
		customMap = template.clickBack();
		customMap.verifyTemplateListElementVisible(InteractiveMapsContent.SELECTED_TEMPLATE_INDEX);
		map = customMap.clickBack();
		map.verifyRealMapAndCustomMapButtons();
		CreateRealMapComponentObject realMap = map.clickRealMap();
		realMap.verifyRealMapPreviewImage();
		map = realMap.clickBack();
		map.verifyRealMapAndCustomMapButtons();
	}

	@Test(groups = {"MapFlowTests_005", "MapFlowTests", "InteractiveMaps"})
	public void MapFlowTests_005_VerifyCloseButtonsInCreationMapFlow() {
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
