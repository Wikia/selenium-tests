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
	- Special Map page
	IM06: Create a PIN and verify following elements: title, description, article link, thumbnail, edit link, more link 
	      when description is long
	IM07: 
	- Create PIN Types Modal      
	IM06: Create PIN Types modal: Check image error validation for small size, big size and non-image extension
	IM07: Verify clicking "Add another pin type" link will display a new line and fields for adding new information  
	-Edit PIN Types Modal 
	IM08: Verify saving any new data will actually update the data for that pin edited
	IM09: All pin types and their images appear with their associated name when opening Edit PIN Types modal
	- PIN Creation - Add a Pin modal
	IM10: Verify following elements appear: Pin name field, Description,  Associated article, Pin category drowdown,
          Submit button, Cancel button, Image upload button
    IM11: Verify form with values for pin name, description, associated article and pin type will display those results once 
          user clicks submit button
    - PIN Editing - Edit Pin modal
    IM12: Check existence of following elements in the modal: Title, Associated article, Select pin type, Description, 
          Add pin button, Cancel button, Close button, Delete PIN button
    IM13: Verify data is correctly updated after saving
    - Random Test Cases
    IM14: Verify possibility of embedding wikia map in other pages. Verify following elements in map: PIN description when
          clicking, zoom, no add/edit features, extra bar at the bottom with Wikia wordmark          
	IM15: Verify embed map code dialog works correctly
*/ 

public class InteractiveMapsTests extends NewTestTemplate{

	Credentials credentials = config.getCredentials();
	int selectedTemplateIndex = 1;
	int selectedMapIndex = 0;
	
	//move to other class:
	private final String mapName = "RMG";
	private final String pinTypeName = "RMG";
	private String templateName;


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
		pinDialog.verifyAssociatedArticleFieldIsDisplayed();
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
