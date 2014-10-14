package com.wikia.webdriver.TestCases.InteractiveMapsTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.InteractiveMapsContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.DeleteAMapComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapsPageObject;;

/**
 * @author: Lukasz Nowak
 * @ownership: Mobile Web    
 */

public class DeleteAndRestoreMapTests extends NewTestTemplate{

	Credentials credentials = config.getCredentials();

	@Test(groups = {"DeleteAndRestoreMapTests_001", "InteractiveMaps"}, , dependsOnMethods = "InteractiveMaps_022")
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
}
