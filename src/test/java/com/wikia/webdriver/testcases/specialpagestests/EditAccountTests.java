package com.wikia.webdriver.testcases.specialpagestests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.urlbuilder.UrlBuilder;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.editaccount.EditAccount;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.login.SpecialUserLoginPageObject;
import org.testng.annotations.Test;

/**
 * @author Karol 'kkarolk' Kujawiak
 *         <p/>
 *         1. Close user account,
 *         2. Verify user account closed,
 *         3. Reopen user account,
 *         4. Verify user account reopened
 */
public class EditAccountTests extends NewTestTemplate {

	private String testedWiki;

	Credentials credentials = config.getCredentials();

	public EditAccountTests() {
		super();
		UrlBuilder urlBuilder = new UrlBuilder(config.getEnv());
		testedWiki = urlBuilder.getUrlForWiki("community");
	}

	@Test(groups = "EditAccountTest")
	public void EditAccount_001_closeAccount() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		EditAccount editAccount = new EditAccount(driver, testedWiki, credentials.userNameClosedAccount);
		editAccount.closeAccount(PageContent.CAPTION);
		editAccount.verifyAccountClosedMessage();
	}

	@Test(groups = "EditAccountTest", dependsOnMethods = "EditAccount_001_closeAccount")
	public void EditAccount_002_verifyAccountClosed() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		SpecialUserLoginPageObject login = base.openSpecialUserLoginOnWiki(wikiURL);
		login.login(credentials.userNameClosedAccount, credentials.passwordClosedAccount);
		login.verifyClosedAccountMessage();
	}

	@Test(groups = "EditAccountTest", dependsOnMethods = "EditAccount_002_verifyAccountClosed")
	public void EditAccount_003_reopenAccount() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		EditAccount editAccount = new EditAccount(driver, testedWiki, credentials.userNameClosedAccount);
		editAccount.reopenAccount(credentials.passwordClosedAccount);
		editAccount.verifyAccountReopenedMessage();
	}

	@Test(groups = {"EditAccountTest", "EditAccountTest_001"}, dependsOnMethods = "EditAccount_003_reopenAccount")
	public void EditAccount_004_verifyAccountReopened() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		SpecialUserLoginPageObject login = base.openSpecialUserLoginOnWiki(wikiURL);
		login.loginAndVerify(credentials.userNameClosedAccount, credentials.passwordClosedAccount, wikiURL);
	}
}
