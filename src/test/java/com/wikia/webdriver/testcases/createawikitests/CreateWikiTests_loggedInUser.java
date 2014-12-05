package com.wikia.webdriver.testcases.createawikitests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.CreateWikiMessages;
import com.wikia.webdriver.common.contentpatterns.WikiFactoryVariablesProvider.WikiFactoryVariables;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki.CreateNewWikiPageObjectStep1;
import com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki.CreateNewWikiPageObjectStep2;
import com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki.CreateNewWikiPageObjectStep3;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialFactoryPageObject;


/**
 * @author Karol 'kkarolk' Kujawiak
 * 1. Create a wiki and delete
 * 2. Create a wiki for children
 * 3. Create a wiki with changed domain
 * 4. Try to create a wiki which name exists
 * 5. Try to create a wiki which name violates naming policy
 * 6. Try to create a wiki without category
 *
 */
public class CreateWikiTests_loggedInUser extends NewTestTemplate {

	String wikiName;
	String wikiDomain;
	Credentials credentials = config.getCredentials();

	@Test(groups = {"CNW", "CreateNewWikiLoggedIn_001"})
	public void CreateNewWiki_001_createDeleteWiki() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		CreateNewWikiPageObjectStep1 cnw1 = base.openSpecialCreateNewWikiPage(wikiCorporateURL);
		String wikiName = cnw1.getWikiName();
		cnw1.typeInWikiName(wikiName);
		cnw1.verifySuccessIcon();
		CreateNewWikiPageObjectStep2 cnw2 = cnw1.submit();
		cnw2.selectCategory(CreateWikiMessages.WIKI_CATEGORY);
		CreateNewWikiPageObjectStep3 cnw3 = cnw2.submit();
		cnw3.selectThemeByName(CreateWikiMessages.WIKI_THEME);
		ArticlePageObject article = cnw3.submit();
		article.verifyWikiTitleOnCongratualtionsLightBox(wikiName);
		article.closeNewWikiCongratulationsLightBox();
		article.verifyWikiTitleHeader(wikiName);
		article.verifyUserLoggedIn(credentials.userName);
		String newWikiURL = article.getWikiUrl();
		article.logOut(newWikiURL);
		article.logInCookie(credentials.userNameStaff, credentials.passwordStaff, newWikiURL);
		SpecialFactoryPageObject factory = article.openWikiFactoryPage(newWikiURL);
		factory.deleteWiki();
	}

	@Test(groups = {"CNW", "CreateNewWikiLoggedIn_002"})
	public void CreateNewWiki_002_createWikiForChildren() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		CreateNewWikiPageObjectStep1 cnw1 = base.openSpecialCreateNewWikiPage(wikiCorporateURL);
		wikiName = cnw1.getWikiName();
		cnw1.typeInWikiName(wikiName);
		cnw1.verifySuccessIcon();
		CreateNewWikiPageObjectStep2 cnw2 = cnw1.submit();
		cnw2.selectCategory(CreateWikiMessages.WIKI_CATEGORY);
		cnw2.selectAllAgesCheckbox();
		CreateNewWikiPageObjectStep3 cnw3 = cnw2.submit();
		cnw3.selectThemeByName(CreateWikiMessages.WIKI_THEME);
		ArticlePageObject article = cnw3.submit();
		article.closeNewWikiCongratulationsLightBox();
		article.verifyUserLoggedIn(credentials.userName);
		String newWikiURL = article.getWikiUrl();
		article.logOut(newWikiURL);
		article.logInCookie(credentials.userNameStaff, credentials.passwordStaff, newWikiURL);
		SpecialFactoryPageObject factory = article.openWikiFactoryPage(newWikiURL);
		factory.verifyVariableValue(
				WikiFactoryVariables.WG_WIKI_DIRECTED_AT_CHILDREN_BY_FOUNDER,
				"true"
		);
	}

	@Test(groups = {"CNW", "CreateNewWikiLoggedIn_003"})
	public void CreateNewWiki_003_createWikiChangedDomain() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		CreateNewWikiPageObjectStep1 cnw1 = base.openSpecialCreateNewWikiPage(wikiCorporateURL);
		wikiName = cnw1.getWikiName();
		wikiDomain = cnw1.getWikiName();
		cnw1.typeInWikiName(wikiName);
		cnw1.typeInWikiDomain(wikiDomain);
		cnw1.verifySuccessIcon();
		CreateNewWikiPageObjectStep2 cnw2 = cnw1.submit();
		cnw2.selectCategory(CreateWikiMessages.WIKI_CATEGORY);
		CreateNewWikiPageObjectStep3 cnw3 = cnw2.submit();
		cnw3.selectThemeByName(CreateWikiMessages.WIKI_THEME);
		ArticlePageObject article = cnw3.submit();
		article.closeNewWikiCongratulationsLightBox();
		article.verifyUserLoggedIn(credentials.userName);
		article.verifyURLcontains(wikiDomain);
	}

	@Test(groups = {"CNW", "CreateNewWikiLoggedIn_004"})
	public void CreateNewWiki_004_createWikiNameExists() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		CreateNewWikiPageObjectStep1 cnw1 = base.openSpecialCreateNewWikiPage(wikiCorporateURL);
		String wikiName = "muppets";
		cnw1.typeInWikiName(wikiName);
		cnw1.verifyOccupiedWikiAddress(wikiName);
	}

	@Test(groups = {"CNW", "CreateNewWikiLoggedIn_005"})
	public void CreateNewWiki_005_createWikiPolicyViolation() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		CreateNewWikiPageObjectStep1 cnw1 = base.openSpecialCreateNewWikiPage(wikiCorporateURL);
		String wikiName = "1234";
		cnw1.typeInWikiName(wikiName);
		cnw1.verifyIncorrectWikiName();
	}

	@Test(groups = {"CNW", "CreateNewWikiLoggedIn_006"})
	public void CreateNewWiki_006_createWikiNoCategory() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		CreateNewWikiPageObjectStep1 cnw1 = base.openSpecialCreateNewWikiPage(wikiCorporateURL);
		wikiName = cnw1.getWikiName();
		wikiDomain = cnw1.getWikiName();
		cnw1.typeInWikiName(wikiName);
		cnw1.typeInWikiDomain(wikiDomain);
		cnw1.verifySuccessIcon();
		CreateNewWikiPageObjectStep2 cnw2 = cnw1.submit();
		cnw2.submit();
		cnw2.verifyCategoryError();
	}
}
