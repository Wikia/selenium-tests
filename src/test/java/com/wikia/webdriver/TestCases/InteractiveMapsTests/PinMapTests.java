package com.wikia.webdriver.TestCases.InteractiveMapsTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.InteractiveMapsContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.AddPinComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapsPageObject;

/**
 * @author: Rodrigo Molinero Gomez
 * @author: Lukasz Jedrzejczak
 * @author: Lukasz Nowak
 * @ownership: Mobile Web IM06: Verify it is possible to add a pin to the map
 *             and that add pin dialog has all required elements v IM07: Verify
 *             removing a suggestion of article with image will remove image
 *             from placeholder v
 */

public class PinMapTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(groups = { "InteractiveMaps_006", "PinMapTests", "InteractiveMaps" })
	public void InteractiveMaps_006_VerifyPinModalContent() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
		selectedMap.verifyMapOpened();
		AddPinComponentObject pinDialog = selectedMap.placePinInMap();
		pinDialog.verifyPinTitleFieldIsDisplayed();
		pinDialog.verifyAssociatedArticleFieldIsDisplayed();
		pinDialog.verifyAssociatedArticleImagePlaceholderIsDisplayed();
		pinDialog.verifyPinCategorySelectorIsDisplayed();
		pinDialog.verifyDescriptionFieldIsDisplayed();
		selectedMap = pinDialog.clickCancelButton();
	}

	@Test(groups = { "InteractiveMaps_007", "PinMapTests", "InteractiveMaps" })
	public void InteractiveMaps_007_VerifySuggestionsAndAssociatedImage() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
		selectedMap.verifyMapOpened();
		AddPinComponentObject pinDialog = selectedMap.placePinInMap();
		pinDialog.verifyPinTitleFieldIsDisplayed();
		pinDialog.typePinName(InteractiveMapsContent.pinName);
		String placeholderSrc = pinDialog.getAssociatedArticleImageSrc();
		pinDialog.typeAssociatedArticle(InteractiveMapsContent.associatedArticleName);
		pinDialog.clickSuggestion(0);
		pinDialog.verifyAssociatedImageIsVisible(placeholderSrc);
		pinDialog.selectPinType();
		selectedMap = pinDialog.clickSaveButton();
		selectedMap.verifyPinPopupImageIsVisible();
	}

	@Test(groups = { "InteractiveMaps_024", "PinMapTests", "InteractiveMaps" })
	public void InteractiveMaps_024_VerifyPinCreationErrors() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
		selectedMap.verifyMapOpened();
		AddPinComponentObject addPinModal = selectedMap.placePinInMap();
		addPinModal.clickSaveButton();
		addPinModal.verifyErrorExists();
		addPinModal.typePinName(InteractiveMapsContent.pinDescription);
		addPinModal.clickSaveButton();
		addPinModal.verifyErrorExists();
		addPinModal.selectPinType();
		addPinModal.clickSaveButton();
		addPinModal.verifyErrorExists();
	}

	@Test(groups = { "InteractiveMaps_025", "PinMapTests", "InteractiveMaps" }, dependsOnMethods = "InteractiveMaps_021_VerifyChangePinData")
	public void InteractiveMaps_025_VerifyPopUpAfterClickPin() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
		selectedMap.verifyMapOpened();
		selectedMap.clickOnPin(0);
		selectedMap.verifyPopUpVisible();
	}

	@Test(groups = { "InteractiveMaps_027", "PinMapTests", "InteractiveMaps" }, dependsOnMethods = "InteractiveMaps_021_VerifyChangePinData")
	public void InteractiveMaps_027_VerifyDeletePin() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
		selectedMap.verifyMapOpened();
		selectedMap.clickOnFilterBoxTitle();
		selectedMap.clickOnPin(0);
		String pinName = selectedMap.getOpenPinName();
		selectedMap.verifyPopUpVisible();
		AddPinComponentObject editPinModal = selectedMap.clickOnEditPin();
		selectedMap = editPinModal.clickDeletePin();
		selectedMap.verifyPinNotExists(pinName);
	}

	@Test(groups = { "InteractiveMaps_021", "PinMapTests", "InteractiveMaps" })
	public void InteractiveMaps_021_VerifyChangePinData() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
		selectedMap.verifyMapOpened();
		selectedMap.clickOnFilterBoxTitle();
		AddPinComponentObject pinModal = selectedMap.placePinInMap();
		String pinTitle = base.getTimeStamp();
		String pinDescription = base.getTimeStamp() + base.getTimeStamp();
		pinModal.typePinName(pinTitle);
		pinModal.selectPinType();
		pinModal.typePinDescription(pinDescription);
		selectedMap = pinModal.clickSaveButton();
		pinModal = selectedMap.clickOnEditPin();
		pinModal.clearPinName();
		pinModal.typePinName(base.getTimeStamp() + base.getTimeStamp());
		pinModal.clearPinDescription();
		pinModal.typePinDescription(base.getTimeStamp());
		selectedMap = pinModal.clickSaveButton();
		selectedMap.verifyPinDataWasChanged(pinTitle, pinDescription);
	}
}
