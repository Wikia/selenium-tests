package com.wikia.webdriver.TestCases.InteractiveMapsTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.InteractiveMapsContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.AddPinComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.CreatePinTypesComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.EmbedMapComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.DabbletComPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapsPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;

/**
 * @author: Rodrigo Molinero Gomez
 * @author: Lukasz Jedrzejczak
 * @author: Lukasz Nowak
 * @ownership: Mobile Web IM11: Verify possibility of embedding wikia map in
 *             other wikia pages. v IM12: Verify following elements in map modal
 *             when a map is embedded in a wikia page: PIN description when
 *             clicking, zoom, add/edit features for pin types and pins, embed
 *             map button, filters box. Verify there is no branding footer. v
 *             IM18: Verify embed map code dialog works correctly from
 *             Special:Map page v IM19: Embed a map outside of wikia and verify
 *             there is a branding footer, zoom in/out options and filters box
 *             collapsibility
 * 
 */

public class EmbedMapTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

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

	@Test(groups = { "InteractiveMaps_036", "InteractiveMapTests", "InteractiveMaps" })
	public void InteractiveMaps_036_VerifyEmbedMapContributeModals() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ArticlePageObject article = new ArticlePageObject(driver);
		article.openArticleByName(wikiURL, InteractiveMapsContent.embedMapArticleName);
		EmbedMapComponentObject embedMapDialog = article.clickViewEmbedMap();
		AddPinComponentObject addPinModal = embedMapDialog.placePinInMap();
		addPinModal.verifyPinTitleFieldIsDisplayed();
		addPinModal.verifyDescriptionFieldIsDisplayed();
		addPinModal.clickCancelButton();
		CreatePinTypesComponentObject pinTypesDialog = embedMapDialog.clickEditPinTypesButton();
		pinTypesDialog.verifyPinTypesDialog();
	}

	@Test(groups = { "InteractiveMaps_037", "InteractiveMapTests", "InteractiveMaps" })
	public void InteractiveMaps_037_VerifyEmbedMapAddPinType() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ArticlePageObject article = new ArticlePageObject(driver);
		article.openArticleByName(wikiURL, InteractiveMapsContent.embedMapArticleName);
		EmbedMapComponentObject embedMapDialog = article.clickViewEmbedMap();
		CreatePinTypesComponentObject pinTypesDialog = embedMapDialog.clickEditPinTypesButton();
		pinTypesDialog.verifyPinTypesDialog();
		pinTypesDialog.deletePinTypes();
		pinTypesDialog.typeManyPinTypeTitle(InteractiveMapsContent.pinTypeName, 4);
		pinTypesDialog.clickSave();
		embedMapDialog.verifyPinTypeExists(InteractiveMapsContent.pinTypeName);
	}
}
