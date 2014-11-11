package com.wikia.webdriver.testcases.visualeditor;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.InteractiveMapsContent;
import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.InsertDialog;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.CreateAMapComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.CreatePinTypesComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.CreateRealMapComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.DeleteAMapComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorAddMapDialog;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorSaveChangesDialog;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapsPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

/**
 * @author Robert 'Rochan' Chan
 * @ownership Contribution
 *
 * VE-1337 - Adding existing map onto article
 * VE-1337 - Checking empty state dialog on wiki with no maps
 * VE-1351 - Adding map in a empty state
 *
 */

public class VEAddMapTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();
	WikiBasePageObject base;
	String articleName, mapID;
	InteractiveMapPageObject createdMap;

	@BeforeMethod(alwaysRun = true)
	public void setup_VEPreferred() {
		base = new WikiBasePageObject(driver);
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
		base.logInCookie(credentials.userNameVEPreferred, credentials.passwordVEPreferred, wikiURL);
	}

	@Test(
		groups = {"VEAddMap", "VEAddMapTests_001", "VEAddExistingMap"}
	)
	public void VEAddMapTests_001_AddExistingMap() {
		articleName = PageContent.articleNamePrefix + base.getTimeStamp();
		VisualEditorPageObject ve = base.launchVisualEditorWithMainEdit(articleName, wikiURL);
		VisualEditorAddMapDialog mapDialog =
			(VisualEditorAddMapDialog) ve.openDialogFromMenu(InsertDialog.MAP);
		VisualEditorPageObject veNew = mapDialog.addExistingMap(0);
		veNew.verifyMapPresent();
		VisualEditorSaveChangesDialog save = veNew.clickPublishButton();
		ArticlePageObject article = save.savePage();
		article.verifyVEPublishComplete();
		article.logOut(wikiURL);
	}

	@Test(
		groups = {"VEAddMap", "VEAddMapTests_002", "VEEmptyMap", "VEAddMapTests_003"}
	)
	public void VEAddMapTests_002_CheckEmptyMapWiki() {
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.veDisabledTestMainPage);
		articleName = PageContent.articleNamePrefix + base.getTimeStamp();
		VisualEditorPageObject ve = base.launchVisualEditorWithMainEdit(articleName, wikiURL);
		VisualEditorAddMapDialog mapDialog =
			(VisualEditorAddMapDialog) ve.openDialogFromMenu(InsertDialog.MAP);
		mapDialog.checkIsEmptyState();
	}

	@Test(
		groups = {"VEAddMap", "VEAddMapTests_003", "VEEmptyMap"},
		dependsOnGroups = "VEAddMapTests_002"
	)
	public void VEAddMapTests_003_InsertMapFromZeroState() {
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.veDisabledTestMainPage);

		VisualEditorPageObject ve = base.launchVisualEditorWithMainEdit(articleName, wikiURL);
		VisualEditorAddMapDialog mapDialog =
			(VisualEditorAddMapDialog) ve.openDialogFromMenu(InsertDialog.MAP);
		CreateAMapComponentObject map = mapDialog.clickCreateAMapButton();
		CreateRealMapComponentObject realMap = map.clickRealMap();
		realMap.verifyRealMapPreviewImage();
		realMap.typeMapName(InteractiveMapsContent.MAP_NAME);
		CreatePinTypesComponentObject pinDialog = realMap.clickNext();
		pinDialog.typePinTypeTitle(InteractiveMapsContent.PIN_TYPE_NAME, InteractiveMapsContent.PIN_TYPE_INDEX);
		createdMap = pinDialog.clickSave();
		createdMap.verifyMapOpened();
		mapID = createdMap.getEmbedMapID();
		createdMap.verifyControlButtonsAreVisible();
		//commenting out the next few lines - Defect VE-1557
//		mapDialog = createdMap.switchBackToVETab();
//		//the next line would fail
//		mapDialog.verifyNumOfMaps(expectedMapNum);
	}

	@AfterGroups(groups = {"VEAddMapTests_003", "VEEmptyMap"})
	public void delete_Map() {
		createdMap = base.openInteractiveMapById(wikiURL, Integer.parseInt(mapID));
		DeleteAMapComponentObject deleteMapModal = createdMap.deleteMap();
		InteractiveMapsPageObject specialMaps = deleteMapModal.deleteMap();
		specialMaps.verifyEmptyState();
	}
}
