package com.wikia.webdriver.testcases.interactivemapstests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.InteractiveMapsContent;
import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.CreatePinTypesComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapsPageObject;

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
		pinTypeModal.selectFileToUpload(PageContent.SMALLFILE, "Small image");
		pinTypeModal.verifyErrorExists();
		pinTypeModal.selectFileToUpload(PageContent.BROKENEXTENSIONFILE, "Image with wrong extension");
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
