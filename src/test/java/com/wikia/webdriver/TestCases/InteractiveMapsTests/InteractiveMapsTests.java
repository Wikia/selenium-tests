package com.wikia.webdriver.TestCases.InteractiveMapsTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.AddPinComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.CreateACustomMapComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.CreateAMapComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.CreatePinTypesComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.CreateRealMapComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.TemplateComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapsPageObject;

/**
 * Author: Rodrigo Molinero Gomez
 * @author: Lukasz Jedrzejczak
 * Date: 20.06.14
 * Time: 16:53
    - Special:Maps page
	IM01: Creating a custom new map based on new image upload
	IM02: Create a custom new map based on existing template
	IM03: Create a real map flow
	IM04: Click on a map and verify correct redirect and URL
	IM05: Click create a map button as anon and make sure log in modal is displayed
	
	- PIN Creation - Add a Pin modal
	IM06: Click Add a pin and verify following elements: Title, Associated article, Select pin type, Description, 
          Add pin button, Cancel button, Close button, Delete PIN button
	IM07: Create A Pin, modify its values and verify they are correctly saved when reopening modal through Edit link
	
	-Edit PIN Types Modal       
	IM08: Check image error validation for small size, big size and non-image extension
	IM09: Verify clicking "Add another pin type" link will display a new line and fields for adding new information  
	IM10: Verify saving any new data will actually update the data for that pin edited
	IM11: All pin types and their images appear with their associated name when opening Edit PIN Types modal
    
    - Random Test Cases
    IM12: Verify possibility of embedding wikia map in other wikia pages. Verify following elements in map modal: PIN description when
          clicking, zoom, add/edit features for pin types and pins, embed map button, filters box 
    IM13: Test template search works correctly for unexisting and existing templates        
	IM14: Verify following behaviours in "Filters" left hand side column:
		All categories are checked and user clicks on single category: We uncheck clicked category and "All categories".
		All categories but one are checked and user clicks on unchecked one: We check clicked category and "All categories".
		All categories are checked (so "All categories" is checked too) and user clicks "All categories": We uncheck all categories and "All categories".
		Some categories are checked (so "All categories" is unchecked) and user clicks "All categories": We check all categories and "All categories".		
	IM15: Verify embed map code dialog works correctly
	*/

public class InteractiveMapsTests extends NewTestTemplate{

	Credentials credentials = config.getCredentials();
	int selectedTemplateIndex = 1;
	int selectedMapIndex = 0;
	
	//move to other class:
	private final String mapName = "RMG";
	private final String pinTypeName = "RMG";
	private final String pinName = "RMG";
	private String templateName;
	private final String associatedArticleName = "Slid";


	@Test(groups = {"InteractiveMaps_001", "InteractiveMapTests", "InteractiveMaps"})
	public void InteractiveMaps_001_CreateCustomMapNewImageUpload() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		CreateAMapComponentObject map = specialMap.clickCreateAMap();
		CreateACustomMapComponentObject customMap = map.clickCustomMap();
		TemplateComponentObject template = customMap.selectFileToUpload(PageContent.file);
		template.verifyTemplateImagePreview();
		template.typeMapName(mapName);
		templateName = base.getTimeStamp();
		template.typeTemplateName(templateName);
		CreatePinTypesComponentObject pinDialog = template.clickNext();
		pinDialog.typePinTypeTitle(pinTypeName);
		InteractiveMapPageObject createdMap = pinDialog.clickSave();
		createdMap.verifyCreatedMapTitle(mapName);
		createdMap.verifyMapOpened();
		createdMap.verifyCreatedPins(pinTypeName);
	}

	@Test(groups = {"InteractiveMaps_002", "InteractiveMapTests", "InteractiveMaps"})
	public void InteractiveMaps_002_CreateCustomMapWithExistingTemplate() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		CreateAMapComponentObject map = specialMap.clickCreateAMap();
		CreateACustomMapComponentObject customMap = map.clickCustomMap();
		String selectedImageName = customMap.getSelectedTemplateImageName(selectedTemplateIndex);
		TemplateComponentObject template = customMap.selectTemplate(selectedTemplateIndex);
		template.verifyTemplateImage(selectedImageName);
		template.typeMapName(mapName);
		CreatePinTypesComponentObject pinDialog = template.clickNext();
		pinDialog.typePinTypeTitle(pinTypeName);
		InteractiveMapPageObject createdMap = pinDialog.clickSave();
		createdMap.verifyCreatedMapTitle(mapName);
		createdMap.verifyMapOpened();
		createdMap.verifyCreatedPins(pinTypeName);
	}
	
	@Test(groups = {"InteractiveMaps_003", "InteractiveMapTests", "InteractiveMaps"})
	public void InteractiveMaps_003_CreateRealMap() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		CreateAMapComponentObject map = specialMap.clickCreateAMap();
		CreateRealMapComponentObject realMap = map.clickRealMap();	
		realMap.verifyRealMapPreviewImage();
		realMap.typeMapName(mapName);
		CreatePinTypesComponentObject pinDialog = realMap.clickNext();
		pinDialog.typePinTypeTitle(pinTypeName);
		InteractiveMapPageObject createdMap = pinDialog.clickSave();
		createdMap.verifyMapOpened();
	}

	@Test(groups = {"InteractiveMaps_004", "InteractiveMapTests", "InteractiveMaps"})
	public void InteractiveMaps_004_ClickMapAndVerifyCorrectRedirect() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		String mapUrl = specialMap.getMapLink(selectedMapIndex);
		String mapTitle = specialMap.getMapTitle(selectedMapIndex);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(selectedMapIndex);
		selectedMap.verifyMapOpened();
		selectedMap.verifyURL(mapUrl);
		selectedMap.verifyCreatedMapTitle(mapTitle);
	}

	@Test(groups = {"InteractiveMaps_005", "InteractiveMapTests", "InteractiveMaps"})
	public void InteractiveMaps_005_VerifyLoginModalWhenAnon() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logOut(wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		CreateAMapComponentObject map = specialMap.clickCreateAMap();
		map.verifyLoginModal();
	}
	
	@Test(groups = {"InteractiveMaps_006", "InteractiveMapTests", "InteractiveMaps"})
	public void InteractiveMaps_006_CreatePin() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(0);
		AddPinComponentObject pinDialog = selectedMap.placePinInMap();
		pinDialog.verifyPinTitleFieldIsDisplayed();
		pinDialog.verifyAssociatedArticleFieldIsDisplayed();
		pinDialog.verifyAssociatedArticleImagePlaceholderIsDisplayed();
		pinDialog.verifyPinCategorySelectorIsDisplayed();
		pinDialog.verifyDescriptionFieldIsDisplayed();
		selectedMap = pinDialog.clickCancelButton();
	}
	
	@Test(groups = {"InteractiveMaps_007", "InteractiveMapTests", "InteractiveMaps"})
	public void InteractiveMaps_007_VerifySuggestionsAndAssociatedImage() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(0);
		AddPinComponentObject pinDialog = selectedMap.placePinInMap();
		pinDialog.verifyPinTitleFieldIsDisplayed();
		pinDialog.typePinName(pinName);
		String placeholderSrc = pinDialog.getAssociatedArticleImageSrc();
		pinDialog.typeAssociatedArticle(associatedArticleName);
		pinDialog.clickSugggestion(0);
		pinDialog.verifyAssociatedImageIsVisible(placeholderSrc);
		pinDialog.verifyImage();
		

	
//		pinDialog.selectPinCategory();
//		pinDialog.typeDescription();
//		selectedMap = pinDialog.clickSaveButton();
//		pinDialog = selectedMap.clickEditPin();
//		pinDialog.verifyPinTitle();
//		pinDialog.verifyAssociatedArticle();
//		pinDialog.verifyAssociatedArticleImage();
//		pinDialog.verifySelectedPinCategory();
//		pinDialog.verifyDescription();
	}
	
	@Test(groups = {"InteractiveMaps_015", "InteractiveMapTests", "InteractiveMaps"})
	public void InteractiveMaps_015_VerifyEmbedMapCodeButton() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(selectedMapIndex);
		selectedMap.verifyMapOpened();
		selectedMap.clickEmbedMapCodeButton();
		selectedMap.verifyEmbedMapDialog();
		selectedMap.clickEmbedMapCodeButton(InteractiveMapPageObject.embedMapDialogButtons.small);
		selectedMap.verifyEmbedMapCode(InteractiveMapPageObject.embedMapDialogButtons.small);
		selectedMap.clickEmbedMapCodeButton(InteractiveMapPageObject.embedMapDialogButtons.medium);
		selectedMap.verifyEmbedMapCode(InteractiveMapPageObject.embedMapDialogButtons.medium);
		selectedMap.clickEmbedMapCodeButton(InteractiveMapPageObject.embedMapDialogButtons.large);
		selectedMap.verifyEmbedMapCode(InteractiveMapPageObject.embedMapDialogButtons.large);
	}
}