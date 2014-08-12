package com.wikia.webdriver.TestCases.InteractiveMapsTests;


import org.testng.annotations.Test;
import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.InteractiveMapsContent;
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
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;


/**
 * Author: Rodrigo Molinero Gomez
 * @author: Lukasz Jedrzejczak
 * @author: Lukasz Nowak
 * Date: 20.06.14
 * Time: 16:53
    - Special:Maps page
	IM01: Creating a custom new map based on new image upload
	IM02: Create a custom new map based on existing template
	IM03: Create a real map flow
	IM04: Click on a map and verify correct redirect and URL
	IM05: Click create a map button as anon and make sure log in modal is displayed
	
	- PIN Creation - Add a Pin modal
	IM06: Verify it is possible to add a pin to the map and that add pin dialog has all required elements
	IM07: Verify removing a suggestion of article with image will remove image from placeholder
	
	-Edit PIN Types Modal       
	IM08: Check image error validation for small size and non-image extension
	IM09: Verify clicking "Add another pin type" link will display a new line and fields for adding new information  
	IM10: Verify saving any new data will actually update the data for that pin edited => Before it contact with Lukasz Nowak
    
    - Other Test Cases
    IM11: Verify possibility of embedding wikia map in other wikia pages. 
    IM12: Verify following elements in map modal when a map is embedded in a wikia page: PIN description when
          clicking, zoom, add/edit features for pin types and pins, embed map button, filters box. Verify there is no branding footer 
    IM13: Test template search works correctly for unexisting and existing templates        
	IM14: Verify following behaviours in "Filters" left hand side column:
		All categories are checked and user clicks on single category: We uncheck clicked category and "All categories".
		All categories but one are checked and user clicks on unchecked one: We check clicked category and "All categories".
		All categories are checked (so "All categories" is checked too) and user clicks "All categories": We uncheck all categories and "All categories".
		Some categories are checked (so "All categories" is unchecked) and user clicks "All categories": We check all categories and "All categories".		
	IM15: Verify embed map code dialog works correctly from Special:Map page
	IM16: Embed a map outside of wikia and verify there is a branding footer, zoom in/out options and filters box collapsibility
	*/

public class InteractiveMapsTests extends NewTestTemplate{

	Credentials credentials = config.getCredentials();
	
	@Test(groups = {"InteractiveMaps_001", "InteractiveMapTests", "InteractiveMaps"})
	public void InteractiveMaps_001_CreateCustomMapNewImageUpload() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		CreateAMapComponentObject map = specialMap.clickCreateAMap();
		CreateACustomMapComponentObject customMap = map.clickCustomMap();
		TemplateComponentObject template = customMap.selectFileToUpload(PageContent.file);
		template.verifyTemplateImagePreview();
		template.typeMapName(InteractiveMapsContent.mapName);
		InteractiveMapsContent.templateName = base.getTimeStamp();
		template.typeTemplateName(InteractiveMapsContent.templateName);
		CreatePinTypesComponentObject pinDialog = template.clickNext();
		pinDialog.typePinTypeTitle(InteractiveMapsContent.pinTypeName,InteractiveMapsContent.pinTypeIndex);
		InteractiveMapPageObject createdMap = pinDialog.clickSave();
		createdMap.verifyCreatedMapTitle(InteractiveMapsContent.mapName);
		createdMap.verifyMapOpened();
		createdMap.verifyCreatedPins(InteractiveMapsContent.pinTypeName);
	}

	@Test(groups = {"InteractiveMaps_002", "InteractiveMapTests", "InteractiveMaps"})
	public void InteractiveMaps_002_CreateCustomMapWithExistingTemplate() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		CreateAMapComponentObject map = specialMap.clickCreateAMap();
		CreateACustomMapComponentObject customMap = map.clickCustomMap();
		String selectedImageName = customMap.getSelectedTemplateImageName(InteractiveMapsContent.selectedTemplateIndex);
		TemplateComponentObject template = customMap.selectTemplate(InteractiveMapsContent.selectedTemplateIndex);
		template.verifyTemplateImage(selectedImageName);
		template.typeMapName(InteractiveMapsContent.mapName);
		CreatePinTypesComponentObject pinDialog = template.clickNext();
		pinDialog.typePinTypeTitle(InteractiveMapsContent.pinTypeName,InteractiveMapsContent.pinTypeIndex);
		InteractiveMapPageObject createdMap = pinDialog.clickSave();
		createdMap.verifyCreatedMapTitle(InteractiveMapsContent.mapName);
		createdMap.verifyMapOpened();
		createdMap.verifyCreatedPins(InteractiveMapsContent.pinTypeName);
	}
	
	@Test(groups = {"InteractiveMaps_003", "InteractiveMapTests", "InteractiveMaps"})
	public void InteractiveMaps_003_CreateRealMap() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		CreateAMapComponentObject map = specialMap.clickCreateAMap();
		CreateRealMapComponentObject realMap = map.clickRealMap();	
		realMap.verifyRealMapPreviewImage();
		realMap.typeMapName(InteractiveMapsContent.mapName);
		CreatePinTypesComponentObject pinDialog = realMap.clickNext();
		pinDialog.typePinTypeTitle(InteractiveMapsContent.pinTypeName,InteractiveMapsContent.pinTypeIndex);
		InteractiveMapPageObject createdMap = pinDialog.clickSave();
		createdMap.verifyMapOpened();
	}

	@Test(groups = {"InteractiveMaps_004", "InteractiveMapTests", "InteractiveMaps"})
	public void InteractiveMaps_004_ClickMapAndVerifyCorrectRedirect() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		String mapUrl = specialMap.getMapLink(InteractiveMapsContent.selectedMapIndex);
		String mapTitle = specialMap.getMapTitle(InteractiveMapsContent.selectedMapIndex);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
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
	public void InteractiveMaps_006_VerifyPinModalContent() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
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
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
		selectedMap.verifyMapOpened();
		AddPinComponentObject pinDialog = selectedMap.placePinInMap();
		pinDialog.verifyPinTitleFieldIsDisplayed();
		pinDialog.typePinName(InteractiveMapsContent.pinName);
		String placeholderSrc = pinDialog.getAssociatedArticleImageSrc();
		pinDialog.typeAssociatedArticle(InteractiveMapsContent.associatedArticleName);
		pinDialog.clickSuggestion(0);
		pinDialog.verifyAssociatedImageIsVisible(placeholderSrc);
		pinDialog.verifyImage();
	}
		
	@Test(groups = {"InteractiveMaps_009", "InteractiveMapTests", "InteractiveMaps"})
	public void InteractiveMaps_009_VerifyClickingAddAnotherPinType() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
		selectedMap.verifyMapOpened();
		selectedMap.clickEditPinTypesButton();
		CreatePinTypesComponentObject pinTypesDialog = new CreatePinTypesComponentObject(driver); 
		pinTypesDialog.verifyPinTypesDialog();
		pinTypesDialog.savePinTypesListState();
		pinTypesDialog.clickAddAnotherPinType();
		pinTypesDialog.verifyAddAnotherPinType();
	}
	
	@Test(groups = {"InteractiveMaps_011","InteractiveMapTests","InteractiveMaps"})
	public void InteractiveMaps_011_VerifyEmbedMapInWikiaPage() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
		selectedMap.verifyMapOpened();
		String wikiEmbedCode = selectedMap.getEmbedMapWikiCode();
		String mapID = selectedMap.getEmbedMapID();
		WikiArticleEditMode EditMode = new WikiArticleEditMode(driver);
		EditMode.editArticleByName(InteractiveMapsContent.embedMapArticleName);
		EditMode.clickSourceButton();
		EditMode.clearSource();
		EditMode.verifySourceEditorContentIsEmpty();
		EditMode.typeContentInSourceMode(wikiEmbedCode);
		EditMode.clickOnPublish();
		EditMode.verifyEmbededMap(mapID);		
	}

	
	@Test(groups = {"InteractiveMaps_013","InteractiveMapTests","InteractiveMaps"})
	public void InteractiveMaps_013_VerifyTemplateSearch() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		CreateAMapComponentObject createMapDialog = specialMap.clickCreateAMap();
		CreateACustomMapComponentObject customMapDialog = createMapDialog.clickCustomMap();
		String test1 = "test";
		String test2 = "1234567890123123123123213";
		customMapDialog.typeSearchTile(test1);
		customMapDialog.verifyTemplateWasFound(test1);
		customMapDialog.clearSearchTitle();
		customMapDialog.typeSearchTile(test2);
		customMapDialog.verifyThereIsError();
	}
	
	@Test(groups = {"InteractiveMaps_014A","InteractiveMaps_014","InteractiveMapTests","InteractiveMaps"})
	public void InteractiveMaps_014A_VerifyUncheckedSingleCategory() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName,credentials.password,wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
		selectedMap.verifyMapOpened();
		selectedMap.clickOnSingleEnabledCategory();
		selectedMap.verifyAllPinTypesIsUncheck();
	}
	
	@Test(groups = {"InteractiveMaps_014B","InteractiveMaps_014","InteractiveMapTests","InteractiveMaps"})
	public void InteractiveMaps_014B_VerifyClickOnUncheckedCategory() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName,credentials.password,wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
		selectedMap.verifyMapOpened();
		selectedMap.clickOnSingleEnabledCategory();
		selectedMap.verifyAllPinTypesIsUncheck();
		selectedMap.clickOnSingleDisabledCategory();
		selectedMap.verifyAllPinTypesIsCheck();
	}
	
	@Test(groups = {"InteractiveMaps_014C","InteractiveMaps_014","InteractiveMapTests","InteractiveMaps"})
	public void InteractiveMaps_014C_VerifyClickAllCategoriesUncheckCategories() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName,credentials.password,wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
		selectedMap.verifyMapOpened();
		selectedMap.clickOnAllPinTypes();
		selectedMap.verifyAllPinTypesIsUncheck();
		selectedMap.verifyPinTypesAreUncheck();
	}
	
	@Test(groups = {"InteractiveMaps_014D","InteractiveMaps_014","InteractiveMapTests","InteractiveMaps"})
	public void InteractiveMaps_014D_VerifyClickAllCategoriesCheckAllPinTypes() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName,credentials.password,wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
		selectedMap.verifyMapOpened();
		selectedMap.clickOnSingleEnabledCategory();
		selectedMap.verifyAllPinTypesIsUncheck();
		selectedMap.clickOnAllPinTypes();
		selectedMap.verifyAllPinTypesIsCheck();
		selectedMap.verifyPinTypesAreCheck();
		selectedMap.verifyPinTypesAreUncheck();
	}
	
	@Test(groups = {"InteractiveMaps_015", "InteractiveMapTests", "InteractiveMaps"})
	public void InteractiveMaps_015_VerifyEmbedMapCodeButton() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
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
	
	@Test(groups = {"InteractiveMaps_016", "InteractiveMapTests", "InteractiveMaps"})
	public void InteractiveMaps_016_VerifyMapZoomOptions(){
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
		selectedMap.verifyMapOpened();
		selectedMap.clickZoomInButton();
		selectedMap.clickZoomOutButton();
	}
}