package com.wikia.webdriver.TestCases.InteractiveMapsTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.InteractiveMapsContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.AddPinComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.CreateACustomMapComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.CreateAMapComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.CreatePinTypesComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.CreateRealMapComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.EmbedMapComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.TemplateComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapsPageObject;

/**
 * @author: Rodrigo Molinero Gomez
 * @author: Lukasz Jedrzejczak
 * @author: Lukasz Nowak
 * @ownership: Mobile Web
 */

public class BlockedUserMapTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(groups = {"BlockedUserMapTests_001", "BlockedUserMapTests", "InteractiveMaps"})
	public void BlockedUserMapTests_001_VerifyBlockedUserCannotEditPinTypes() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameBlockedAccount, credentials.passwordBlockedAccount, wikiURL);
		InteractiveMapsPageObject specialMaps = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMaps.clickMapWithIndex(InteractiveMapsContent.SELECTED_MAP_INDEX);
		CreatePinTypesComponentObject editPinTypes = selectedMap.clickEditPinTypesButton();
		editPinTypes.typeManyPinTypeTitle(InteractiveMapsContent.PIN_TYPE_NAME, 3);
		editPinTypes.clickSave();
		editPinTypes.verifyErrorExists();
	}

	@Test(groups = {"BlockedUserMapTests_002", "BlockedUserMapTests", "InteractiveMaps"})
	public void BlockedUserMapTests_002_VerifyUserCannotAddPin() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameBlockedAccount, credentials.passwordBlockedAccount, wikiURL);
		InteractiveMapsPageObject specialMaps = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMaps.clickMapWithIndex(InteractiveMapsContent.SELECTED_MAP_INDEX);
		AddPinComponentObject addPinModal = selectedMap.placePinInMap();
		addPinModal.typePinName(InteractiveMapsContent.PIN_NAME);
		addPinModal.typePinDescription(InteractiveMapsContent.PIN_DESCRIPTION);
		addPinModal.selectPinType();
		addPinModal.clickSaveButton();
		addPinModal.verifyErrorExists();
	}

	@Test(groups = {"BlockedUserMapTests_003", "BlockedUserMapTests", "InteractiveMaps"})
	public void BlockedUserMapTests_003_VerifyUserCannotCreateRealMap() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameBlockedAccount, credentials.passwordBlockedAccount, wikiURL);
		InteractiveMapsPageObject specialMaps = base.openSpecialInteractiveMaps(wikiURL);
		CreateAMapComponentObject createMap = specialMaps.clickCreateAMap();
		CreateRealMapComponentObject realMap = createMap.clickRealMap();
		realMap.typeMapName(InteractiveMapsContent.MAP_NAME);
		realMap.clickNext();
		realMap.verifyErrorExists();
	}

	@Test(groups = {"BlockedUserMapTests_004", "BlockedUserMapTests", "InteractiveMaps"})
	public void BlockedUserMapTests_004_VerifyUserCannotCreateCustomMap() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameBlockedAccount, credentials.passwordBlockedAccount, wikiURL);
		InteractiveMapsPageObject specialMaps = base.openSpecialInteractiveMaps(wikiURL);
		CreateAMapComponentObject createMap = specialMaps.clickCreateAMap();
		CreateACustomMapComponentObject customMap = createMap.clickCustomMap();
		String selectedImageName = customMap.getSelectedTemplateImageName(InteractiveMapsContent.SELECTED_TEMPLATE_INDEX);
		TemplateComponentObject template = customMap.selectTemplate(InteractiveMapsContent.SELECTED_TEMPLATE_INDEX);
		template.verifyTemplateImage(selectedImageName);
		template.typeMapName(InteractiveMapsContent.MAP_NAME);
		CreatePinTypesComponentObject pinDialog = template.clickNext();
		template.verifyErrorExists();
	}

	@Test(groups = {"BlockedUserMapTests_005", "BlockedUserMapTests", "InteractiveMaps"})
	public void BlockedUserMapTests_005_VerifyUserCannotEditPinTypesOnEmbeddedMap() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameBlockedAccount, credentials.passwordBlockedAccount, wikiURL);
		ArticlePageObject article = new ArticlePageObject(driver);
		article.openArticleByName(wikiURL, InteractiveMapsContent.EMBED_MAP_ARTICLE_NAME);
		EmbedMapComponentObject embedMapDialog = article.clickViewEmbedMap();
		CreatePinTypesComponentObject pinTypesDialog = embedMapDialog.clickEditPinTypesButton();
		pinTypesDialog.verifyPinTypesDialog();
		pinTypesDialog.typeManyPinTypeTitle(InteractiveMapsContent.PIN_TYPE_NAME, 4);
		pinTypesDialog.clickSave();
		pinTypesDialog.verifyErrorExists();
	}

	@Test(groups = {"BlockedUserMapTests_006", "BlockedUserMapTests", "InteractiveMaps"})
	public void BlockedUserMapTests_006_VerifyUserCannotAddPinOnEmbeddedMap() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameBlockedAccount, credentials.passwordBlockedAccount, wikiURL);
		ArticlePageObject article = new ArticlePageObject(driver);
		article.openArticleByName(wikiURL, InteractiveMapsContent.EMBED_MAP_ARTICLE_NAME);
		EmbedMapComponentObject embedMapDialog = article.clickViewEmbedMap();
		AddPinComponentObject addPinModal = embedMapDialog.placePinInMap();
		addPinModal.typePinName(InteractiveMapsContent.PIN_NAME);
		addPinModal.typePinDescription(InteractiveMapsContent.PIN_DESCRIPTION);
		addPinModal.selectPinType();
		addPinModal.clickSaveButton();
		addPinModal.verifyErrorExists();
	}
}
