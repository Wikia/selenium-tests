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
 *                              
 */

public class BlockedUserTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();
	
	@Test(groups = {"InteractiveMaps_038", "InteractiveMapTests", "InteractiveMaps"})
	public void InteractiveMaps_038_VerifyBlockedUsersCannotEditPinTypes() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameBlockedAccount, credentials.passwordBlockedAccount, wikiURL);
		InteractiveMapsPageObject specialMaps = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMaps.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
		CreatePinTypesComponentObject editPinTypes = selectedMap.clickEditPinTypesButton();
		editPinTypes.typeManyPinTypeTitle(InteractiveMapsContent.pinTypeName, 3);
		editPinTypes.clickSave();
		editPinTypes.verifyErrorExists();
	}
	
	@Test(groups = {"InteractiveMaps_039", "InteractiveMapTests", "InteractiveMaps"})
	public void InteractiveMaps_039_VerifyBlockedUsersCannotAddPin() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameBlockedAccount, credentials.passwordBlockedAccount, wikiURL);
		InteractiveMapsPageObject specialMaps = base.openSpecialInteractiveMaps(wikiURL);
		InteractiveMapPageObject selectedMap = specialMaps.clickMapWithIndex(InteractiveMapsContent.selectedMapIndex);
		AddPinComponentObject addPinModal = selectedMap.placePinInMap();
		addPinModal.typePinName(InteractiveMapsContent.pinName);
		addPinModal.typePinDescription(InteractiveMapsContent.pinDescription);
		addPinModal.selectPinType();
		addPinModal.clickSaveButton();
		addPinModal.verifyErrorExists();
	}
	
	@Test(groups = {"InteractiveMaps_040", "InteractiveMapTests", "InteractiveMaps"})
	public void InteractiveMaps_040_VerifyBlockedUsersCannotCreateRealMap() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameBlockedAccount, credentials.passwordBlockedAccount, wikiURL);
		InteractiveMapsPageObject specialMaps = base.openSpecialInteractiveMaps(wikiURL);
		CreateAMapComponentObject createMap = specialMaps.clickCreateAMap();
		CreateRealMapComponentObject realMap = createMap.clickRealMap();
		realMap.typeMapName(InteractiveMapsContent.mapName);
		realMap.clickNext();
		realMap.verifyErrorExists();
	}
	
	@Test(groups = {"InteractiveMaps_041", "InteractiveMapTests", "InteractiveMaps"})
	public void InteractiveMaps_041_VerifyBlockedUsersCannotCreateCustomMap() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameBlockedAccount, credentials.passwordBlockedAccount, wikiURL);
		InteractiveMapsPageObject specialMaps = base.openSpecialInteractiveMaps(wikiURL);
		CreateAMapComponentObject createMap = specialMaps.clickCreateAMap();
		CreateACustomMapComponentObject customMap = createMap.clickCustomMap();
		String selectedImageName = customMap.getSelectedTemplateImageName(InteractiveMapsContent.selectedTemplateIndex);
		TemplateComponentObject template = customMap.selectTemplate(InteractiveMapsContent.selectedTemplateIndex);
		template.verifyTemplateImage(selectedImageName);
		template.typeMapName(InteractiveMapsContent.mapName);
		CreatePinTypesComponentObject pinDialog = template.clickNext();
		template.verifyErrorExists();
	}
	
	@Test(groups = {"InteractiveMaps_042", "InteractiveMaps", "InteractiveMaps"})
	public void InteractiveMaps_042_VerifyBlockedUserCannotEditPinTypesOnEmbeddedMap() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameBlockedAccount, credentials.passwordBlockedAccount, wikiURL);
		ArticlePageObject article = new ArticlePageObject(driver);
		article.openArticleByName(wikiURL, InteractiveMapsContent.embedMapArticleName);
		EmbedMapComponentObject embedMapDialog = article.clickViewEmbedMap();
		CreatePinTypesComponentObject pinTypesDialog = embedMapDialog.clickEditPinTypesButton();
		pinTypesDialog.verifyPinTypesDialog();
		pinTypesDialog.typeManyPinTypeTitle(InteractiveMapsContent.pinTypeName, 4);
		pinTypesDialog.clickSave();
		pinTypesDialog.verifyErrorExists();
	}
	
	@Test(groups = {"InteractiveMaps_043", "InteractiveMaps", "InteractiveMaps"})
	public void InteractiveMaps_043_VerifyBlockedUserCannotAddPinOnEmbeddedMap() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameBlockedAccount, credentials.passwordBlockedAccount, wikiURL);
		ArticlePageObject article = new ArticlePageObject(driver);
		article.openArticleByName(wikiURL, InteractiveMapsContent.embedMapArticleName);
		EmbedMapComponentObject embedMapDialog = article.clickViewEmbedMap();
		AddPinComponentObject addPinModal = embedMapDialog.placePinInMap();
		addPinModal.typePinName(InteractiveMapsContent.pinName);
		addPinModal.typePinDescription(InteractiveMapsContent.pinDescription);
		addPinModal.selectPinType();
		addPinModal.clickSaveButton();
		addPinModal.verifyErrorExists();
	}
}
