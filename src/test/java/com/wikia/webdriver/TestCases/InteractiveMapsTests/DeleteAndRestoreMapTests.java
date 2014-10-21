package com.wikia.webdriver.TestCases.InteractiveMapsTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.InteractiveMapsContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.DeleteAMapComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapsPageObject;
/**
 * @author: Lukasz Nowak
 * @ownership: Mobile Web
 */

public class DeleteAndRestoreMapTests extends NewTestTemplate{

	Credentials credentials = config.getCredentials();

	@Test(groups = {"DeleteAndRestoreMapTests_001", "InteractiveMaps"})
	public void DeleteAndRestoreMapTestsMapTests_001_DeleteMapAsAMapOwner() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapPageObject selectedMap = base.openInteractiveMapById(wikiURL, InteractiveMapsContent.mapToDeleteAndRestore);
		String deletedMapId = selectedMap.getEmbedMapID();
		DeleteAMapComponentObject deleteMapModal = selectedMap.deleteMap();
		InteractiveMapsPageObject specialMap = deleteMapModal.deleteMap();
		specialMap.verifyMapDeletedMessage();
		selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
		String openMapId = selectedMap.getEmbedMapID();
		DeleteAMapComponentObject.verifyMapWasDeleted(deletedMapId, openMapId);
	}

	@Test(groups = {"DeleteAndRestoreMapTests_002", "InteractiveMaps"})
	public void DeleteAndRestoreMapTests_002_RestoreMapAsAMapOwner() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapPageObject selectedMap = base.openInteractiveMapById(wikiURL, InteractiveMapsContent.mapToDeleteAndRestore);
		selectedMap.verifyMapOpened();
		selectedMap.verifyMapDeletedMsg();
		selectedMap.restoreMap();
	}

	@Test(groups = {"DeleteAndRestoreMapTests_003", "InteractiveMaps"})
	public void DeleteAndRestoreMapTests_003_DeleteMapByNotOwner() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName2, credentials.password2, wikiURL);
		InteractiveMapPageObject selectedMap = base.openInteractiveMapById(wikiURL, InteractiveMapsContent.mapToDeleteAndRestore);
		String tryDeleteMapId = selectedMap.getEmbedMapID();
		DeleteAMapComponentObject deleteMapModal = selectedMap.deleteMap();
		deleteMapModal.clickDeleteMap();
		deleteMapModal.verifyDeleteMapError();
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
		String openSecondMapId = selectedMap.getEmbedMapID();
		selectedMap.verifyMapOpened();
		selectedMap.verifyOpenMapId(openSecondMapId, tryDeleteMapId);
	}

	@Test(groups = {"DeleteAndRestoreMapTests_004", "InteractiveMaps"})
	public void DeleteAndRestoreMapTests_004_StaffUserCanDeleteMap() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		InteractiveMapPageObject selectedMap = base.openInteractiveMapById(wikiURL, InteractiveMapsContent.mapToDeleteAndRestore);
		selectedMap.verifyMapOpened();
		String deletedMapId = selectedMap.getEmbedMapID();
		DeleteAMapComponentObject deleteMapModal = selectedMap.deleteMap();
		InteractiveMapsPageObject specialMap = deleteMapModal.deleteMap();
		specialMap.verifyMapDeletedMessage();
		selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
		String openMapId = selectedMap.getEmbedMapID();
		DeleteAMapComponentObject.verifyMapWasDeleted(deletedMapId, openMapId);
	}

	@Test(groups = {"DeleteAndRestoreMapTests_005", "InteractiveMaps"})
	public void DeleteAndRestoreMapTests_005_StaffUserCanRestoreMap() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		InteractiveMapPageObject selectedMap = base.openInteractiveMapById(wikiURL, InteractiveMapsContent.mapToDeleteAndRestore);
		selectedMap.verifyMapOpened();
		selectedMap.verifyMapDeletedMsg();
		selectedMap.restoreMap();
		selectedMap.verifyMapDeletedMsgNotVisible();
	}

	@Test(groups = {"DeleteAndRestoreMapTests_006", "InteractiveMaps"})
	public void DeleteAndRestoreMapTests_006_SysOpCanDeleteMap() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameSysop, credentials.passwordSysop, wikiURL);
		InteractiveMapPageObject selectedMap = base.openInteractiveMapById(wikiURL, InteractiveMapsContent.mapToDeleteAndRestore);
		selectedMap.verifyMapOpened();
		String deletedMapId = selectedMap.getEmbedMapID();
		DeleteAMapComponentObject deleteMapModal = selectedMap.deleteMap();
		InteractiveMapsPageObject specialMap = deleteMapModal.deleteMap();
		specialMap.verifyMapDeletedMessage();
		selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
		String openMapId = selectedMap.getEmbedMapID();
		DeleteAMapComponentObject.verifyMapWasDeleted(deletedMapId, openMapId);
	}

	@Test(groups = {"DeleteAndRestoreMapTests_007", "InteractiveMaps"})
	public void DeleteAndRestoreMapTests_007_SysOpCanRestoreMap() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameSysop, credentials.passwordSysop, wikiURL);
		InteractiveMapPageObject selectedMap = base.openInteractiveMapById(wikiURL, InteractiveMapsContent.mapToDeleteAndRestore);
		selectedMap.verifyMapOpened();
		selectedMap.verifyMapDeletedMsg();
		selectedMap.restoreMap();
		selectedMap.verifyMapDeletedMsgNotVisible();
	}
}
