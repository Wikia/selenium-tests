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
 * @ownership: Mobile Web
 */

public class EmbedMapTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(groups = { "EmbedMapTests_001", "EmbedMapTests", "InteractiveMaps" })
	public void EmbedMapTests_001_EmbedMapInWikiaPage() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.SELECTED_MAP_INDEX);
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

	@Test(groups = { "EmbedMapTests_002", "EmbedMapTests", "InteractiveMaps" })
	public void EmbedMapTests_002_VerifyEmbedMapElements() {
		ArticlePageObject article = new ArticlePageObject(driver);
		article.openArticleByName(wikiURL, InteractiveMapsContent.EMBED_MAP_ARTICLE_NAME);
		EmbedMapComponentObject embedMapDialog = article.clickViewEmbedMap();
		embedMapDialog.verifyEmbedMapModalOpened();
		embedMapDialog.verifyMapTitlePresented();
		embedMapDialog.verifyCloseButtonPresented();
		embedMapDialog.verifyMapElementsPresented();
		embedMapDialog.verifyBrandFooterNotVisible();
	}

	@Test(groups = { "EmbedMapTests_003", "EmbedMapTests", "InteractiveMaps" })
	public void EmbedMapTests_003_VerifyEmbedMapCodeButton() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.SELECTED_MAP_INDEX);
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

	@Test(groups = { "EmbedMapTests_004", "EmbedMapTests", "InteractiveMaps" })
	public void EmbedMapTests_004_VerifyEmbedMapOutsideWikia() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMap.clickMapWithIndex(InteractiveMapsContent.SELECTED_MAP_INDEX);
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

	@Test(groups = { "EmbedMapTests_005", "EmbedMapTests", "InteractiveMaps" })
	public void EmbedMapTests_005_VerifyEmbedMapContributeModals() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ArticlePageObject article = new ArticlePageObject(driver);
		article.openArticleByName(wikiURL, InteractiveMapsContent.EMBED_MAP_ARTICLE_NAME);
		EmbedMapComponentObject embedMapDialog = article.clickViewEmbedMap();
		AddPinComponentObject addPinModal = embedMapDialog.placePinInMap();
		addPinModal.verifyPinTitleFieldIsDisplayed();
		addPinModal.verifyDescriptionFieldIsDisplayed();
		addPinModal.clickCancelButton();
		CreatePinTypesComponentObject pinTypesDialog = embedMapDialog.clickEditPinTypesButton();
		pinTypesDialog.verifyPinTypesDialog();
	}

	@Test(groups = { "EmbedMapTests_006", "EmbedMapTests", "InteractiveMaps" })
	public void EmbedMapTests_006_VerifyEmbeddedMapAddPinType() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ArticlePageObject article = new ArticlePageObject(driver);
		article.openArticleByName(wikiURL, InteractiveMapsContent.EMBED_MAP_ARTICLE_NAME);
		EmbedMapComponentObject embedMapDialog = article.clickViewEmbedMap();
		CreatePinTypesComponentObject pinTypesDialog = embedMapDialog.clickEditPinTypesButton();
		pinTypesDialog.verifyPinTypesDialog();
		pinTypesDialog.deletePinTypes();
		pinTypesDialog.typeManyPinTypeTitle(InteractiveMapsContent.PIN_TYPE_NAME, 4);
		pinTypesDialog.clickSave();
		embedMapDialog.verifyPinTypeExists(InteractiveMapsContent.PIN_TYPE_NAME);
	}
}
