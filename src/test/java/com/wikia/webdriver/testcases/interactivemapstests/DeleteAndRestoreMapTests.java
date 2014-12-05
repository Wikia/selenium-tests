package com.wikia.webdriver.testcases.interactivemapstests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.InteractiveMapsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.DeleteAMapComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapsPageObject;
/**
 * @author: Lukasz Nowak
 * @ownership: Mobile Web
 */

public class DeleteAndRestoreMapTests extends NewTestTemplate{

	Credentials credentials = config.getCredentials();

	@Test(groups = {"DeleteAndRestoreMapTests_001", "DeleteAndRestoreMapTests", "InteractiveMaps"})
	public void DeleteAndRestoreMapTests_001_DeleteMapAsAMapOwner() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapPageObject selectedMap = base.openInteractiveMapById(wikiURL, InteractiveMapsContent.MAP_TO_DELETE_AND_RESTORE[0]);
		DeleteAMapComponentObject deleteMapModal = selectedMap.deleteMap();
		InteractiveMapsPageObject specialMap = deleteMapModal.deleteMap();
		selectedMap = base.openInteractiveMapById(wikiURL, InteractiveMapsContent.MAP_TO_DELETE_AND_RESTORE[0]);
	}

	@Test(groups = {"DeleteAndRestoreMapTests_002", "DeleteAndRestoreMapTests", "InteractiveMaps"})
	public void DeleteAndRestoreMapTests_002_RestoreMapAsAMapOwner() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapPageObject selectedMap = base.openInteractiveMapById(wikiURL, InteractiveMapsContent.MAP_TO_DELETE_AND_RESTORE[0]);
		selectedMap.verifyMapOpened();
		selectedMap.restoreMap();
	}

	@Test(groups = {"DeleteAndRestoreMapTests_003", "DeleteAndRestoreMapTests", "InteractiveMaps"})
	public void DeleteAndRestoreMapTests_003_DeleteMapByNotOwner() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName2, credentials.password2, wikiURL);
		InteractiveMapPageObject selectedMap = base.openInteractiveMapById(wikiURL, InteractiveMapsContent.MAP_TO_DELETE_AND_RESTORE[1]);
		DeleteAMapComponentObject deleteMapModal = selectedMap.deleteMap();
		deleteMapModal.clickDeleteMap();
		Assertion.assertEquals(InteractiveMapsContent.MAP_DELETE_ERROR, deleteMapModal.getDeleteMapError());
	}

	@Test(groups = {"DeleteAndRestoreMapTests_004", "DeleteAndRestoreMapTests", "InteractiveMaps"})
	public void DeleteAndRestoreMapTests_004_StaffUserCanDeleteMap() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		InteractiveMapPageObject selectedMap = base.openInteractiveMapById(wikiURL, InteractiveMapsContent.MAP_TO_DELETE_AND_RESTORE[2]);
		selectedMap.verifyMapOpened();
		DeleteAMapComponentObject deleteMapModal = selectedMap.deleteMap();
		InteractiveMapsPageObject specialMap = deleteMapModal.deleteMap();
	}

	@Test(groups = {"DeleteAndRestoreMapTests_005", "DeleteAndRestoreMapTests", "InteractiveMaps"})
	public void DeleteAndRestoreMapTests_005_StaffUserCanRestoreMap() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		InteractiveMapPageObject selectedMap = base.openInteractiveMapById(wikiURL, InteractiveMapsContent.MAP_TO_DELETE_AND_RESTORE[2]);
		selectedMap.verifyMapOpened();
		selectedMap.restoreMap();
	}

	@Test(groups = {"DeleteAndRestoreMapTests_006", "DeleteAndRestoreMapTests", "InteractiveMaps"})
	public void DeleteAndRestoreMapTests_006_SysOpCanDeleteMap() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameSysop, credentials.passwordSysop, wikiURL);
		InteractiveMapPageObject selectedMap = base.openInteractiveMapById(wikiURL, InteractiveMapsContent.MAP_TO_DELETE_AND_RESTORE[3]);
		selectedMap.verifyMapOpened();
		DeleteAMapComponentObject deleteMapModal = selectedMap.deleteMap();
		InteractiveMapsPageObject specialMap = deleteMapModal.deleteMap();
	}

	@Test(groups = {"DeleteAndRestoreMapTests_007", "DeleteAndRestoreMapTests", "InteractiveMaps"})
	public void DeleteAndRestoreMapTests_007_SysOpCanRestoreMap() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameSysop, credentials.passwordSysop, wikiURL);
		InteractiveMapPageObject selectedMap = base.openInteractiveMapById(wikiURL, InteractiveMapsContent.MAP_TO_DELETE_AND_RESTORE[3]);
		selectedMap.verifyMapOpened();
		selectedMap.restoreMap();
	}
}
