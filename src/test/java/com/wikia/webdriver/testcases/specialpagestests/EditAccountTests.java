package com.wikia.webdriver.testcases.specialpagestests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.AttachedSignInPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.editaccount.EditAccount;

import org.testng.annotations.Test;

@Test(groups = "EditAccountTest", enabled = false)
public class EditAccountTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();
  UrlBuilder urlBuilder = new UrlBuilder(Configuration.getEnv());
  private String testedWiki = urlBuilder.getUrlForWiki("community");
  private String expectedErrorMessage = "We don't recognize these credentials. Try again or register a new account.";

  @Test(groups = {"EditAccountTest_001"})
  @Execute(asUser = User.STAFF)
  public void EditAccount_001_closeAccount() {
    EditAccount editAccount = new EditAccount(driver).navigateToSpecialEditAccount(testedWiki);

    editAccount.goToAccountManagement(credentials.userNameClosedAccount);
    editAccount.closeAccount(PageContent.CAPTION);
    editAccount.verifyAccountClosedMessage();
  }

  @Test(dependsOnMethods = "EditAccount_001_closeAccount")
  public void EditAccount_002_verifyAccountClosed() {
    WikiBasePageObject base = new WikiBasePageObject();

    AttachedSignInPage signInPage = base.openSpecialUserLogin(wikiURL);

    signInPage
        .login(credentials.userNameClosedAccount, credentials.passwordClosedAccount);

        .verifyErrorMessage(expectedErrorMessage);

  }

  @Test(dependsOnMethods = {"EditAccount_001_closeAccount","EditAccount_002_verifyAccountClosed"})
  @Execute(asUser = User.STAFF)
  public void EditAccount_003_reopenAccount() {
    EditAccount editAccount = new EditAccount(driver).navigateToSpecialEditAccount(testedWiki);

    editAccount.goToAccountManagement(credentials.userNameClosedAccount);
    editAccount.reopenAccount(credentials.passwordClosedAccount);
    editAccount.verifyAccountReopenedMessage();
  }

  @Test(groups = {"EditAccountTest_001"}, dependsOnMethods = {"EditAccount_001_closeAccount",
      "EditAccount_002_verifyAccountClosed", "EditAccount_003_reopenAccount"})
  public void EditAccount_004_verifyAccountReopened() {
    WikiBasePageObject base = new WikiBasePageObject();

    AttachedSignInPage signInPage = base.openSpecialUserLogin(wikiURL);

    signInPage
        .typeUsername(credentials.userNameClosedAccount)
        .typePassword(credentials.passwordClosedAccount)
        .clickSignInButton()
        .verifyUserLoggedIn(credentials.userNameClosedAccount);

  }
}
