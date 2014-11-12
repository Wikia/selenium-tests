package com.wikia.webdriver.TestCases.InteractiveMapsTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.InteractiveMapsContent;
import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.CreatePinTypesComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapsPageObject;

/**
 * @author: Rodrigo Molinero Gomez
 * @author: Lukasz Jedrzejczak
 * @author: Lukasz Nowak
 * @ownership: Mobile Web 
 */

public class PinTypeMapTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(groups = { "PinTypeMapTests_001", "PinTypeMapTests", "InteractiveMaps" })
	public void PinTypeMapTests_001_VerifyImageValidationInPinTypeModal() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.SELECTED_MAP_INDEX);
		selectedMap.verifyMapOpened();
		selectedMap.clickEditPinTypesButton();
		CreatePinTypesComponentObject pinTypeModal = new CreatePinTypesComponentObject(driver);
		pinTypeModal.verifyPinTypesDialog();
		pinTypeModal.selectFileToUpload(PageContent.smallFile, "Small image");
		pinTypeModal.verifyErrorExists();
		pinTypeModal.selectFileToUpload(PageContent.brokenExtensionFile, "Image with wrong extension");
		pinTypeModal.verifyErrorExists();
	}

	@Test(groups = { "PinTypeMapTests_002", "PinTypeMapTests", "InteractiveMaps" })
	public void PinTypeMapTests_002_VerifyClickingAddAnotherPinType() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.SELECTED_MAP_INDEX);
		selectedMap.verifyMapOpened();
		selectedMap.clickEditPinTypesButton();
		CreatePinTypesComponentObject pinTypesDialog = new CreatePinTypesComponentObject(driver);
		pinTypesDialog.verifyPinTypesDialog();
		pinTypesDialog.savePinTypesListState();
		pinTypesDialog.clickAddAnotherPinType();
		pinTypesDialog.verifyAddAnotherPinType();
	}
}
