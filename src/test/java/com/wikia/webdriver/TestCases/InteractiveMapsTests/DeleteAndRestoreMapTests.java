package com.wikia.webdriver.TestCases.InteractiveMapsTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.InteractiveMapsContent;
import com.wikia.webdriver.Common.Core.Assertion;
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

	@Test(groups = {"DeleteAndRestoreMapTests_001", "DeleteAndRestoreMapTests", "InteractiveMaps"})
	public void DeleteAndRestoreMapTests_001_DeleteMapAsAMapOwner() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapPageObject selectedMap = base.openInteractiveMapById(wikiURL, InteractiveMapsContent.mapToDeleteAndRestore[0]);
		DeleteAMapComponentObject deleteMapModal = selectedMap.deleteMap();
		InteractiveMapsPageObject specialMap = deleteMapModal.deleteMap();
		selectedMap = base.openInteractiveMapById(wikiURL, InteractiveMapsContent.mapToDeleteAndRestore[0]);
	}

	@Test(groups = {"DeleteAndRestoreMapTests_002", "DeleteAndRestoreMapTests", "InteractiveMaps"})
	public void DeleteAndRestoreMapTests_002_RestoreMapAsAMapOwner() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapPageObject selectedMap = base.openInteractiveMapById(wikiURL, InteractiveMapsContent.mapToDeleteAndRestore[0]);
		selectedMap.verifyMapOpened();
		selectedMap.restoreMap();
	}

	@Test(groups = {"DeleteAndRestoreMapTests_003", "DeleteAndRestoreMapTests", "InteractiveMaps"})
	public void DeleteAndRestoreMapTests_003_DeleteMapByNotOwner() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName2, credentials.password2, wikiURL);
		InteractiveMapPageObject selectedMap = base.openInteractiveMapById(wikiURL, InteractiveMapsContent.mapToDeleteAndRestore[1]);
		DeleteAMapComponentObject deleteMapModal = selectedMap.deleteMap();
		deleteMapModal.clickDeleteMap();
		Assertion.assertEquals(InteractiveMapsContent.mapDeleteError, deleteMapModal.getDeleteMapError());
	}

	@Test(groups = {"DeleteAndRestoreMapTests_004", "DeleteAndRestoreMapTests", "InteractiveMaps"})
	public void DeleteAndRestoreMapTests_004_StaffUserCanDeleteMap() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		InteractiveMapPageObject selectedMap = base.openInteractiveMapById(wikiURL, InteractiveMapsContent.mapToDeleteAndRestore[2]);
		selectedMap.verifyMapOpened();
		DeleteAMapComponentObject deleteMapModal = selectedMap.deleteMap();
		InteractiveMapsPageObject specialMap = deleteMapModal.deleteMap();
	}

	@Test(groups = {"DeleteAndRestoreMapTests_005", "DeleteAndRestoreMapTests", "InteractiveMaps"})
	public void DeleteAndRestoreMapTests_005_StaffUserCanRestoreMap() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		InteractiveMapPageObject selectedMap = base.openInteractiveMapById(wikiURL, InteractiveMapsContent.mapToDeleteAndRestore[2]);
		selectedMap.verifyMapOpened();
		selectedMap.restoreMap();
	}

	@Test(groups = {"DeleteAndRestoreMapTests_006", "DeleteAndRestoreMapTests", "InteractiveMaps"})
	public void DeleteAndRestoreMapTests_006_SysOpCanDeleteMap() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameSysop, credentials.passwordSysop, wikiURL);
		InteractiveMapPageObject selectedMap = base.openInteractiveMapById(wikiURL, InteractiveMapsContent.mapToDeleteAndRestore[3]);
		selectedMap.verifyMapOpened();
		DeleteAMapComponentObject deleteMapModal = selectedMap.deleteMap();
		InteractiveMapsPageObject specialMap = deleteMapModal.deleteMap();
	}

	@Test(groups = {"DeleteAndRestoreMapTests_007", "DeleteAndRestoreMapTests", "InteractiveMaps"})
	public void DeleteAndRestoreMapTests_007_SysOpCanRestoreMap() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameSysop, credentials.passwordSysop, wikiURL);
		InteractiveMapPageObject selectedMap = base.openInteractiveMapById(wikiURL, InteractiveMapsContent.mapToDeleteAndRestore[3]);
		selectedMap.verifyMapOpened();
		selectedMap.restoreMap();
	}
}
