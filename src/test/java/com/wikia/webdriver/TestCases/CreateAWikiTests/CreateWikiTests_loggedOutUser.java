package com.wikia.webdriver.TestCases.CreateAWikiTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.CreateWikiMessages;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiLogInSignUpPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiPageObjectStep1;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiPageObjectStep2;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiPageObjectStep3;

/**
 *
 * @author Karol 'kkarolk' Kujawiak
 * 1. Create wiki as logged out user
 * 2. Try to create wiki with wrong user password
 * 3. Try to create wiki with blank user password
 * 4. Try to create wiki with blank user name
 * 5. Try to create wiki with blank user name
 * 6. Try to create wiki with invalid user name
 *
 */
public class CreateWikiTests_loggedOutUser extends NewTestTemplate{

	Credentials credentials = config.getCredentials();

	@Test(groups = {"CNW", "CreateNewWikiLoggedOut_001"})
	public void CreateNewWiki_001_loggedOutUser() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		CreateNewWikiPageObjectStep1 cnw1 = base.openSpecialCreateNewWikiPage(wikiCorporateURL);
		cnw1.typeInWikiName(cnw1.getWikiName());
		cnw1.verifySuccessIcon();
		CreateNewWikiLogInSignUpPageObject cnwLogin = cnw1.submitToLogInSignUp();
		cnwLogin.typeInUserName(credentials.userName);
		cnwLogin.typeInPassword(credentials.password);
		CreateNewWikiPageObjectStep2 cnw2 = cnwLogin.submitLogin();
		cnw2.selectCategory(CreateWikiMessages.wikiCategory);
		CreateNewWikiPageObjectStep3 cnw3 = cnw2.submit();
		cnw3.selectThemeByName(CreateWikiMessages.wikiTheme);
		ArticlePageObject article = cnw3.submit();
		article.closeNewWikiCongratulationsLightBox();
		article.verifyUserLoggedIn(credentials.userName);
	}

	@Test(groups = {"CNW", "CreateNewWikiLoggedOut_002"})
	public void CreateNewWiki_002_wrongPassword() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		CreateNewWikiPageObjectStep1 cnw1 = base.openSpecialCreateNewWikiPage(wikiCorporateURL);
		cnw1.typeInWikiName(cnw1.getWikiName());
		cnw1.verifySuccessIcon();
		CreateNewWikiLogInSignUpPageObject cnwLogin = cnw1.submitToLogInSignUp();
		cnwLogin.typeInUserName(credentials.userName);
		cnwLogin.typeInPassword(credentials.password + "1");
		cnwLogin.submitLogin();
		cnwLogin.verifyInvalidPasswordValidation();
	}

	@Test(groups = {"CNW", "CreateNewWikiLoggedOut_003"})
	public void CreateNewWiki_003_blankPassword() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		CreateNewWikiPageObjectStep1 cnw1 = base.openSpecialCreateNewWikiPage(wikiCorporateURL);
		cnw1.typeInWikiName(cnw1.getWikiName());
		cnw1.verifySuccessIcon();
		CreateNewWikiLogInSignUpPageObject cnwLogin = cnw1.submitToLogInSignUp();
		cnwLogin.typeInUserName(credentials.userName);
		cnwLogin.submitLogin();
		cnwLogin.verifyBlankPasswordValidation();
	}

	@Test(groups = {"CNW", "CreateNewWikiLoggedOut_004"})
	public void CreateNewWiki_004_blankUserName() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		CreateNewWikiPageObjectStep1 cnw1 = base.openSpecialCreateNewWikiPage(wikiCorporateURL);
		cnw1.typeInWikiName(cnw1.getWikiName());
		cnw1.verifySuccessIcon();
		CreateNewWikiLogInSignUpPageObject cnwLogin = cnw1.submitToLogInSignUp();
		cnwLogin.submitLogin();
		cnwLogin.verifyEmptyUserNameValidation();
	}

	@Test(groups = {"CNW", "CreateNewWikiLoggedOut_005"})
	public void CreateNewWiki_005_invalidUserName() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		CreateNewWikiPageObjectStep1 cnw1 = base.openSpecialCreateNewWikiPage(wikiCorporateURL);
		cnw1.typeInWikiName(cnw1.getWikiName());
		cnw1.verifySuccessIcon();
		CreateNewWikiLogInSignUpPageObject cnwLogin = cnw1.submitToLogInSignUp();
		cnwLogin.typeInUserName(credentials.userName + "1");
		cnwLogin.submitLogin();
		cnwLogin.verifyInvalidUserNameValidation();
	}
}
