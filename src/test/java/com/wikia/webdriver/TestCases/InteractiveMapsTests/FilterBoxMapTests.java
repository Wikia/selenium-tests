package com.wikia.webdriver.TestCases.InteractiveMapsTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.InteractiveMapsContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapsPageObject;

/**
 * @author: Rodrigo Molinero Gomez
 * @author: Lukasz Jedrzejczak
 * @author: Lukasz Nowak
 * @ownership: Mobile Web
 * 
 */

public class FilterBoxMapTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(groups = { "InteractiveMaps_014", "FilterBoxMapTests", "InteractiveMaps" })
	public void InteractiveMaps_014_VerifyUncheckedSingleCategory() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
		selectedMap.verifyMapOpened();
		selectedMap.clickOnSingleEnabledCategory();
		selectedMap.verifyAllPinTypesIsUncheck();
	}

	@Test(groups = { "InteractiveMaps_015", "FilterBoxMapTests", "InteractiveMaps" })
	public void InteractiveMaps_015_VerifyClickOnUncheckedCategory() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
		selectedMap.verifyMapOpened();
		selectedMap.clickOnSingleEnabledCategory();
		selectedMap.verifyAllPinTypesIsUncheck();
		selectedMap.clickOnSingleDisabledCategory();
		selectedMap.verifyAllPinTypesIsCheck();
	}

	@Test(groups = { "InteractiveMaps_016", "FilterBoxMapTests", "InteractiveMaps" })
	public void InteractiveMaps_016_VerifyClickAllCategoriesUncheckCategories() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
		selectedMap.verifyMapOpened();
		selectedMap.clickOnAllPinTypes();
		selectedMap.verifyAllPinTypesIsUncheck();
		selectedMap.verifyPinTypesAreUncheck();
	}

	@Test(groups = { "InteractiveMaps_017", "FilterBoxMapTests", "InteractiveMaps" })
	public void InteractiveMaps_017_VerifyClickAllCategoriesCheckAllPinTypes() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
		selectedMap.verifyMapOpened();
		selectedMap.clickOnSingleEnabledCategory();
		selectedMap.verifyAllPinTypesIsUncheck();
		selectedMap.clickOnAllPinTypes();
		selectedMap.verifyAllPinTypesIsCheck();
		selectedMap.verifyPinTypesAreCheck();
	}
}
