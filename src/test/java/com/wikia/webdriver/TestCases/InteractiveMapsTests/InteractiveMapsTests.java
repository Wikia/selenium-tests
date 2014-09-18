package com.wikia.webdriver.TestCases.InteractiveMapsTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.InteractiveMapsContent;
import com.wikia.webdriver.Common.ContentPatterns.PalantirContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.AddPinComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.CreateACustomMapComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.CreateAMapComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.CreatePinTypesComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.CreateRealMapComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.PalantirComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.TemplateComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.EmbedMapComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapsPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.DabbletComPageObject;

/**
 * @author: Rodrigo Molinero Gomez
 * @author: Lukasz Jedrzejczak
 * @author: Lukasz Nowak
 * @ownership: Mobile Web
 * 
 *             - Special:Maps page 
 *             IM01: Creating a custom new map based on new image upload v 
 *             IM02: Create a custom new map based on existing template v 
 *             IM03: Create a real map flow v 
 *             IM04: Click on a map and verify correct redirect and URL v
 *             IM05: Click create a map button as anon and make sure log in 
 *             modal is displayed v
 *             
 *             - PIN Creation - Add a Pin modal 
 *             IM06: Verify it is possible to  add a pin to the map and that 
 *             add pin dialog has all required elements v
 *             IM07: Verify removing a suggestion of article with image will
 *             remove image from placeholder v
 * 
 *             - Edit PIN Types Modal 
 *             IM08: Check image error validation for small size and non-image 
 *             extension v
 *             IM09: Verify back button works correctly on every dialog 
 *             IM10: Verify clicking "Add another pin type" link will display a 
 *             new line and fields for adding new information v                  
 * 
 *             - Other Test Cases 
 *             IM11: Verify possibility of embedding wikia map in other wikia pages. v
 *             IM12: Verify following elements in  map modal when a map is embedded in 
 *             a wikia page: PIN description when clicking, zoom, add/edit features for 
 *             pin types and pins, embed map button, filters box. Verify there is no 
 *             branding footer. v
 *             IM13: Test template search works correctly for unexisting and
 *             existing templates v 
 *             IM14: Verify following behaviours in "Filters" left hand side column: All
 *             categories are checked and user clicks on single category: We uncheck 
 *             clicked category and "All categories". v 
 *             IM15: All categories but one are checked and user clicks on unchecked one:
 *             We check clicked category and "All categories". v
 *             IM16: All categories are checked (so "All categories" is checked too) and
 *             user clicks "All categories": We uncheck all categories and "All categories" v
 *             IM!7: Some categories are checked (so "All categories" is unchecked)
 *             and user clicks "All categories": We check all categories and "All categories" v             
 *             IM18: Verify embed map code dialog works correctly from Special:Map page v
 *             IM19: Embed a map outside of wikia and verify there is a branding footer,
 *             zoom in/out options and filters box collapsibility
 *             
 *             - Added to this list by Łukasz Nowak 
 *             IM20: Verify zoom in and zoom out of map v             
 *             IM21: Verify pin data is correctly updated after saving v
 *             IM22: required elements from page are displayed: create new map link,
 *             list of maps (max 10), pagination 
 *             IM23 : Click Create a map button and check that Learn more link redirects
 *             to maps.wikia.com
 *             IM24: Verify the 3 possible errors possible when adding a pin to a map
 *             IM25: Verify clicking existing pin will open pop-up
 *             IM26: Verify name must be set when creating a new custom map
 *             IM27: Verify delete pin functionality works appropriately
 *             IM28: Make sure visibility of all close buttons through map creation flow  
 *             IM29: Verify that map is visible for anon 
 *             
 *             Test for Palantir App (Shadow of Mordor)
 *             IM30: Set player position and verify if poi appear on the map
 *             IM31: Set player position out of map boundaries and verify error. Verify that pin does not appear on map
 *             IM32: Set player position and after that remove player position. Verify that pin disappear.
 *             IM33: Set huge zoom level and verify error
 *             IM34: Update map position and verify respond
 *             IM35: Set decimal zoom value and verify error. Check that pin does not appear on map      
 *                              
 */

public class InteractiveMapsTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(groups = { "InteractiveMaps_001", "InteractiveMapTests", "InteractiveMaps" }, enabled = false)
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
		pinDialog.typePinTypeTitle(InteractiveMapsContent.pinTypeName, InteractiveMapsContent.pinTypeIndex);
		InteractiveMapPageObject createdMap = pinDialog.clickSave();
		createdMap.verifyCreatedMapTitle(InteractiveMapsContent.mapName);
		createdMap.verifyMapOpened();
		createdMap.verifyCreatedPinTypesForNewMap();
		createdMap.verifyControButtonsAreVisible();
	}

	@Test(groups = { "InteractiveMaps_002", "InteractiveMapTests", "InteractiveMaps" })
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
		pinDialog.typePinTypeTitle(InteractiveMapsContent.pinTypeName, InteractiveMapsContent.pinTypeIndex);
		InteractiveMapPageObject createdMap = pinDialog.clickSave();
		createdMap.verifyCreatedMapTitle(InteractiveMapsContent.mapName);
		createdMap.verifyMapOpened();
		createdMap.verifyCreatedPinTypesForNewMap();
		createdMap.verifyControButtonsAreVisible();
	}

	@Test(groups = { "InteractiveMaps_003", "InteractiveMapTests", "InteractiveMaps" })
	public void InteractiveMaps_003_CreateRealMap() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		CreateAMapComponentObject map = specialMap.clickCreateAMap();
		CreateRealMapComponentObject realMap = map.clickRealMap();
		realMap.verifyRealMapPreviewImage();
		realMap.typeMapName(InteractiveMapsContent.mapName);
		CreatePinTypesComponentObject pinDialog = realMap.clickNext();
		pinDialog.typePinTypeTitle(InteractiveMapsContent.pinTypeName, InteractiveMapsContent.pinTypeIndex);
		InteractiveMapPageObject createdMap = pinDialog.clickSave();
		createdMap.verifyMapOpened();
		createdMap.verifyControButtonsAreVisible();
	}

	@Test(groups = { "InteractiveMaps_004", "InteractiveMapTests", "InteractiveMaps" })
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

	@Test(groups = { "InteractiveMaps_005", "InteractiveMapTests", "InteractiveMaps" })
	public void InteractiveMaps_005_VerifyLoginModalWhenAnon() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logOut(wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		CreateAMapComponentObject map = specialMap.clickCreateAMap();
		map.verifyLoginModal();
	}

	@Test(groups = { "InteractiveMaps_006", "InteractiveMapTests", "InteractiveMaps" })
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

	@Test(groups = { "InteractiveMaps_007", "InteractiveMapTests", "InteractiveMaps" })
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
	}

	@Test(groups = { "InteractiveMaps_008", "InteractiveMapTests", "InteractiveMaps" })
	public void InteractiveMaps_008_VerifyImageValidationInPinTypeModal() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
		selectedMap.verifyMapOpened();
		selectedMap.clickEditPinTypesButton();
		CreatePinTypesComponentObject pinTypeModal = new CreatePinTypesComponentObject(driver);
		pinTypeModal.verifyPinTypesDialog();
		pinTypeModal.selectFileToUpload(PageContent.smallFile, "Small image");
		pinTypeModal.verifyErrorsExist();
		pinTypeModal.selectFileToUpload(PageContent.brokenExtensionFile, "Image with wrong extension");
		pinTypeModal.verifyErrorsExist();
	}

	@Test(groups = { "InteractiveMaps_009", "InteractiveMapTests", "InteractiveMaps" })
	public void InteractiveMaps_009_VerifyBackButtonWorksCorrectly() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		CreateAMapComponentObject map = specialMap.clickCreateAMap();
		CreateACustomMapComponentObject customMap = map.clickCustomMap();
		TemplateComponentObject template = customMap.selectTemplate(InteractiveMapsContent.selectedTemplateIndex);
		template.verifyTemplateImagePreview();
		customMap = template.clickBack();
		customMap.verifyTemplateListElementVisible(InteractiveMapsContent.selectedTemplateIndex);
		map = customMap.clickBack();
		map.verifyRealMapAndCustomMapButtons();
		CreateRealMapComponentObject realMap = map.clickRealMap();
		realMap.verifyRealMapPreviewImage();
		map = realMap.clickBack();
		map.verifyRealMapAndCustomMapButtons();
	}

	@Test(groups = { "InteractiveMaps_010", "InteractiveMapTests", "InteractiveMaps" })
	public void InteractiveMaps_010_VerifyClickingAddAnotherPinType() {
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

	@Test(groups = { "InteractiveMaps_011", "InteractiveMapTests", "InteractiveMaps" })
	public void InteractiveMaps_011_VerifyEmbedMapInWikiaPage() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
		selectedMap.verifyMapOpened();
		String wikiEmbedCode = selectedMap.getEmbedMapWikiCode();
		String mapID = selectedMap.getEmbedMapID();
		WikiArticleEditMode EditMode = selectedMap.openEmbedMapPageEdit(wikiURL);
		EditMode.clickSourceButton();
		EditMode.clearSource();
		EditMode.verifySourceEditorContentIsEmpty();
		EditMode.typeContentInSourceMode(wikiEmbedCode);
		EditMode.clickOnPublish();
		EditMode.verifyEmbededMap(mapID);
	}

	@Test(groups = { "InteractiveMaps_012", "InteractiveMapTests", "InteractiveMaps" })
	public void InteractiveMaps_012_VerifyEmbedMapElements() {
		ArticlePageObject article = new ArticlePageObject(driver);
		article.openArticleByName(wikiURL, InteractiveMapsContent.embedMapArticleName);
		EmbedMapComponentObject embedMapDialog = article.clickViewEmbedMap();
		embedMapDialog.verifyEmbedMapModalOpened();
		embedMapDialog.verifyMapTitlePresented();
		embedMapDialog.verifyCloseButtonPresented();
		embedMapDialog.verifyMapElementsPresented();
		embedMapDialog.verifyBrandFooterNotVisible();
	}

	@Test(groups = { "InteractiveMaps_013", "InteractiveMapTests", "InteractiveMaps" })
	public void InteractiveMaps_013_VerifyTemplateSearch() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		CreateAMapComponentObject createMapDialog = specialMap.clickCreateAMap();
		CreateACustomMapComponentObject customMapDialog = createMapDialog.clickCustomMap();
		customMapDialog.typeSearchTile(InteractiveMapsContent.templateNameToSearchShouldNotBeFound);
		customMapDialog.verifyThereIsError();
		customMapDialog.clearSearchTitle();
		customMapDialog.typeSearchTile(InteractiveMapsContent.templateNameToSearchShouldBeFound);
		customMapDialog.verifyTemplateListElementVisible(0);
	}

	@Test(groups = { "InteractiveMaps_014", "InteractiveMapTests", "InteractiveMaps" })
	public void InteractiveMaps_014_VerifyUncheckedSingleCategory() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
		selectedMap.verifyMapOpened();
		selectedMap.clickOnSingleEnabledCategory();
		selectedMap.verifyAllPinTypesIsUncheck();
	}

	@Test(groups = { "InteractiveMaps_015", "InteractiveMapTests", "InteractiveMaps" })
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

	@Test(groups = { "InteractiveMaps_016", "InteractiveMapTests", "InteractiveMaps" })
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

	@Test(groups = { "InteractiveMaps_017", "InteractiveMapTests", "InteractiveMaps" })
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

	@Test(groups = { "InteractiveMaps_018", "InteractiveMapTests", "InteractiveMaps" })
	public void InteractiveMaps_018_VerifyEmbedMapCodeButton() {
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

	@Test(groups = { "InteractiveMaps_019", "InteractiveMapTests", "InteractiveMaps" })
	public void InteractiveMaps_019_VerifyEmbedMapOutsideWikia() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
		selectedMap.verifyMapOpened();
		selectedMap.clickEmbedMapCodeButton();
		selectedMap.verifyEmbedMapDialog();
		selectedMap.clickEmbedMapCodeButton(InteractiveMapPageObject.embedMapDialogButtons.small);
		String mapCode = selectedMap.getEmbedMapCode();
		System.out.println(mapCode);
		DabbletComPageObject outPage = new DabbletComPageObject(driver);
		outPage.openOutPage();
		outPage.typeHtmlCode(mapCode);
		outPage.verifyMapEmbed();
	}

	@Test(groups = { "InteractiveMaps_020", "InteractiveMapTests", "InteractiveMaps" })
	public void InteractiveMaps_020_VerifyMapZoomOptions() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
		selectedMap.verifyMapOpened();
		selectedMap.clickZoomOutButton();
		selectedMap.clickZoomInButton();
	}

	@Test(groups = { "InteractiveMaps_021", "InteractiveMapTests", "InteractiveMaps" })
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

	@Test(groups = { "InteractiveMaps_022", "InteractiveMapTests", "InteractiveMaps" })
	public void InteractiveMaps_022_VerifyMapListElements() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		specialMap.verifyAmountMapOnTheList();
		specialMap.verifyCreateMapButtonExist();
		specialMap.verifyCorrectPagination();
	}

	@Test(groups = { "InteractiveMaps_023", "InteractiveMapTests", "InteractiveMaps" })
	public void InteractiveMaps_023_VerifyLearnMoreLink() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		CreateAMapComponentObject createMapModal = specialMap.clickCreateAMap();
		createMapModal.verifyLearnMoreLinkRedirect(InteractiveMapsContent.learnMoreLink);
	}

	@Test(groups = { "InteractiveMaps_024", "InteractiveMapTests", "InteractiveMaps" })
	public void InteractiveMaps_024_VerifyPinCreationErrors() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
		selectedMap.verifyMapOpened();
		AddPinComponentObject addPinModal = selectedMap.placePinInMap();
		addPinModal.clickSaveButton();
		addPinModal.verifyErrorIsPresented();
		addPinModal.typePinName(InteractiveMapsContent.pinDescription);
		addPinModal.clickSaveButton();
		addPinModal.verifyErrorIsPresented();
		addPinModal.selectPinType();
		addPinModal.clickSaveButton();
		addPinModal.verifyErrorIsPresented();
	}

	@Test(groups = { "InteractiveMaps_025", "InteractiveMapTests", "InteractiveMaps" }, dependsOnMethods = "InteractiveMaps_021_VerifyChangePinData")
	public void InteractiveMaps_025_VerifyPopUpAfterClickPin() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
		selectedMap.verifyMapOpened();
		selectedMap.clickOnPin(0);
		selectedMap.verifyPopUpVisible();
	}

	@Test(groups = { "InteractiveMaps_026", "InteractiveMapTests", "InteractiveMaps" })
	public void InteractiveMaps_026_VerifyCreateCustomMapErrors() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		CreateAMapComponentObject createMap = specialMap.clickCreateAMap();
		CreateACustomMapComponentObject customMap = createMap.clickCustomMap();
		TemplateComponentObject templateMap = customMap.selectTemplate(InteractiveMapsContent.selectedTemplateIndex);
		templateMap.clickNext();
		templateMap.verifyErrorExist();
	}

	@Test(groups = { "InteractiveMaps_027", "InteractiveMapTests", "InteractiveMaps" }, dependsOnMethods = "InteractiveMaps_021_VerifyChangePinData")
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
		selectedMap.verifyPinNotExist(pinName);
	}

	@Test(groups = { "InteractiveMaps_028", "InteractiveMapTests", "InteractiveMaps" })
	public void InteractiveMaps_028_VerifyCloseButtonsInCreationMapFlow() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		CreateAMapComponentObject createMapModal = specialMap.clickCreateAMap();
		specialMap = createMapModal.clickCloseButton();
		specialMap.verifyCreateMapModalNotExist();
		createMapModal = specialMap.clickCreateAMap();
		CreateRealMapComponentObject realMapModal = createMapModal.clickRealMap();
		specialMap = realMapModal.clickClose();
		specialMap.verifyCreateMapModalNotExist();
		createMapModal = specialMap.clickCreateAMap();
		CreateACustomMapComponentObject customMapModal = createMapModal.clickCustomMap();
		customMapModal.clickCloseButton();
		specialMap.verifyCreateMapModalNotExist();
	}

	@Test(groups = {"InteractiveMaps_030", "PalantirTests", "InteractiveMaps"})
	public void InteractiveMaps_030_PalantirSetPlayerCorrectPosition() {
		InteractiveMapPageObject selectedMap =  new InteractiveMapPageObject(driver);
		selectedMap.openMap(wikiURL, 3);
		selectedMap.verifyMapOpened();
		PalantirComponentObject poi = new PalantirComponentObject(driver);
		PalantirContent handle = new PalantirContent();
		handle = poi.setAndVerifyPlayerPosition(-40, -10, 3, true);	
		poi.verifyCorrectPlayerPos(handle.success, handle.responseCode, handle.message);
		poi.verifyPoiAppearOnMap();
	}
	
	@Test(groups = {"InteractiveMaps_031", "PalantirTests", "InteractiveMaps"})
	public void InteractiveMaps_031_PalantirSetPlayerPositionOutOfMap() {
		InteractiveMapPageObject selectedMap =  new InteractiveMapPageObject(driver);
		selectedMap.openMap(wikiURL, 3);
		selectedMap.verifyMapOpened();
		PalantirComponentObject poi = new PalantirComponentObject(driver);
		PalantirContent handle = new PalantirContent();
		handle = poi.setAndVerifyPlayerPosition(9300, 15000, 3, true);	
		poi.verifyWrongPlayerPos(handle.success, handle.responseCode, handle.message);
		poi.verifyPoiNotAppearOnMap();
	}
	
	@Test(groups = {"InteractiveMaps_032", "PalantirTests", "InteractiveMaps"})
	public void InteractiveMaps_032_PalantirSetAndRemovePlayerPosition() {
		InteractiveMapPageObject selectedMap =  new InteractiveMapPageObject(driver);
		selectedMap.openMap(wikiURL, 3);
		selectedMap.verifyMapOpened();
		PalantirComponentObject poi = new PalantirComponentObject(driver);
		PalantirContent handle = new PalantirContent();
		handle = poi.setAndVerifyPlayerPosition(-40, -10, 3, true);
		poi.verifyCorrectPlayerPos(handle.success, handle.responseCode, handle.message);
		poi.verifyPoiAppearOnMap();
		handle = poi.deletePlayerPosition();
		poi.verifyPlayerPosDeleted(handle.success, handle.responseCode, handle.message);
		poi.verifyPoiNotAppearOnMap();		
	}
	
	@Test(groups = {"InteractiveMaps_033", "PalantirTests", "InteractiveMaps"})
	public void InteractiveMaps_033_PalantirSetHugeZoomVerifyError() {
		InteractiveMapPageObject selectedMap =  new InteractiveMapPageObject(driver);
		selectedMap.openMap(wikiURL, 3);
		selectedMap.verifyMapOpened();
		PalantirComponentObject poi = new PalantirComponentObject(driver);
		PalantirContent handle = new PalantirContent();
		handle = poi.setAndVerifyPlayerPosition(-40, -10, 3000, true);
		poi.verifyWrongZoomLevel(handle.success, handle.responseCode, handle.message);
		poi.verifyPoiNotAppearOnMap();
	}
	
	@Test(groups = {"InteractiveMaps_034", "PalantirTests", "InteractiveMaps"})
	public void InteractiveMaps_034_PalantirUpdateMapPosition() {
		InteractiveMapPageObject selectedMap =  new InteractiveMapPageObject(driver);
		selectedMap.openMap(wikiURL, 3);
		selectedMap.verifyMapOpened();
		PalantirComponentObject poi = new PalantirComponentObject(driver);
		PalantirContent handle = new PalantirContent();
		handle = poi.setAndVerifyPlayerPosition(-40, -10, 3, true);
		poi.verifyCorrectPlayerPos(handle.success, handle.responseCode, handle.message);
		handle = poi.updateMapPosition(-90, -10, 3);
		poi.verifyMapPositionUpdated(handle.success, handle.responseCode, handle.message);	
	}
	
	@Test(groups = {"InteractiveMaps_035", "PalantirTests", "InteractiveMaps"})
	public void InteractiveMaps_035_PalantirSetDecimalZoom() {
		InteractiveMapPageObject selectedMap =  new InteractiveMapPageObject(driver);
		selectedMap.openMap(wikiURL, 3);
		selectedMap.verifyMapOpened();
		PalantirComponentObject poi = new PalantirComponentObject(driver);
		PalantirContent handle = new PalantirContent();
		handle = poi.setAndVerifyPlayerPosition(-40, -10, 3.4, true);
		poi.verifyDecimalZoomLevel(handle.success, handle.responseCode, handle.message);
		poi.verifyPoiNotAppearOnMap();
	}
}
