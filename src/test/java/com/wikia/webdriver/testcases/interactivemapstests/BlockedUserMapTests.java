package com.wikia.webdriver.testcases.interactivemapstests;

import com.wikia.webdriver.common.contentpatterns.InteractiveMapsContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.*;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapsPageObject;
import org.testng.annotations.Test;

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
	public void BlockedUserMapTests_002_VerifyUserCannotAddPin_QAART_476() {
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
