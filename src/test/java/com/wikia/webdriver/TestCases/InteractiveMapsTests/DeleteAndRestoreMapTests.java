package com.wikia.webdriver.TestCases.InteractiveMapsTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.InteractiveMapsContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.DeleteAMapComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapsPageObject;
import com.wikia.webdriver.TestCases.InteractiveMapsTests.PinMapTests;
/**
 * @author: Lukasz Nowak
 * @ownership: Mobile Web    
 */

public class DeleteAndRestoreMapTests extends NewTestTemplate{

	Credentials credentials = config.getCredentials();

	@Test(groups = {"DeleteAndRestoreMapTests_001", "InteractiveMaps"})
	public void DeleteAndRestoreMapTests_001_VerifyDeleteMapByMapOwner() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
		String deletedMapId = selectedMap.getEmbedMapID();
		DeleteAMapComponentObject deleteMapModal = selectedMap.deleteMap();
		specialMap = deleteMapModal.deleteMap();
		specialMap.verifyMapDeletedMessage();
		selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
		String openMapId = selectedMap.getEmbedMapID();
		DeleteAMapComponentObject.verifyMapWasDeleted(deletedMapId, openMapId);
	}

	@Test(groups = {"DeleteAndRestoreMapTests_002", "InteractiveMaps"})
	public void DeleteAndRestoreMapTests_002_VerifyDeleteMapByNotOwner() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName2, credentials.password2, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
		String tryDeleteMapId = selectedMap.getEmbedMapID();
		DeleteAMapComponentObject deleteMapModal = selectedMap.deleteMap();
		deleteMapModal.clickDeleteMap();
		deleteMapModal.verifyDeleteMapError();
		specialMap = base.openSpecialInteractiveMaps(wikiURL);
		selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
		String openSecondMapId = selectedMap.getEmbedMapID();
		selectedMap.verifyMapOpened();
		selectedMap.verifyOpenMapId(openSecondMapId, tryDeleteMapId);
	}

	@Test(groups = {"DeleteAndRestoreMapTests_003", "InteractiveMaps"})
	public void DeleteAndRestoreMapTests_003_VerifyRestoreMapByOwner() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
		selectedMap.verifyMapOpened();
		String deletedMapId = selectedMap.getEmbedMapID();
		DeleteAMapComponentObject deleteMapModal = selectedMap.deleteMap();
		specialMap = deleteMapModal.deleteMap();
		specialMap.verifyMapDeletedMessage();
		selectedMap = specialMap.openInteractiveMapById(wikiURL, Integer.parseInt(deletedMapId));
		selectedMap.restoreMap();
	}

	@Test(groups = {"DeleteAndRestoreMapTests_004", "InteractiveMaps"}, enabled=false)
	public void DeleteAndRestoreMapTests_004_VerifyStaffUserCanDeleteAndRestoreMap() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
		selectedMap.verifyMapOpened();
		String deletedMapId = selectedMap.getEmbedMapID();
		DeleteAMapComponentObject deleteMapModal = selectedMap.deleteMap();
		specialMap = deleteMapModal.deleteMap();
		specialMap.verifyMapDeletedMessage();
		selectedMap = specialMap.openInteractiveMapById(wikiURL, Integer.parseInt(deletedMapId));
		selectedMap.restoreMap();
	}
}
