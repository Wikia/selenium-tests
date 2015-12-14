package com.wikia.webdriver.testcases.specialpagestests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.editaccount.EditAccount;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.login.SpecialUserLoginPageObject;

import org.testng.annotations.Test;

public class EditAccountTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();
  UrlBuilder urlBuilder = new UrlBuilder(Configuration.getEnv());
  private String testedWiki = urlBuilder.getUrlForWiki("community");

  @Test(groups = "EditAccountTest")
  @Execute(asUser = User.STAFF)
  public void EditAccount_001_closeAccount() {
    EditAccount editAccount = new EditAccount(driver).navigateToSpecialEditAccount(testedWiki);

    editAccount.goToAccountManagement(credentials.userNameClosedAccount);
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
  @Execute(asUser = User.STAFF)
  public void EditAccount_003_reopenAccount() {
    EditAccount editAccount = new EditAccount(driver).navigateToSpecialEditAccount(testedWiki);

    editAccount.goToAccountManagement(credentials.userNameClosedAccount);
    editAccount.reopenAccount(credentials.passwordClosedAccount);
    editAccount.verifyAccountReopenedMessage();
  }

  @Test(groups = {"EditAccountTest", "EditAccountTest_001"},
      dependsOnMethods = "EditAccount_003_reopenAccount")
  public void EditAccount_004_verifyAccountReopened() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    SpecialUserLoginPageObject login = base.openSpecialUserLoginOnWiki(wikiURL);
    login.loginAndVerify(credentials.userNameClosedAccount, credentials.passwordClosedAccount,
        wikiURL);
  }
}
