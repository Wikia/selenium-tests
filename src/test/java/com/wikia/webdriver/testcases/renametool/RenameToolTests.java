package com.wikia.webdriver.testcases.renametool;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.EmailUtils;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.api.UserRegistration;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.SignUpUser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.global_navitagtion.NavigationBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.DetachedRegisterPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.renametool.ConfirmationModalPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.renametool.HelpPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.renametool.SpecialRenameUserPage;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@InBrowser(browser = Browser.CHROME)
@Execute(onWikia = "community")
@Test(groups = "renameTool")
public class RenameToolTests extends NewTestTemplate {

  @Test(groups = {"renameTool_01"})
  @Execute(asUser = User.QARENAME)
  public void UserProvidesCorrectNewNameDoesntClickUnderstandCheckbox() {
    SpecialRenameUserPage renameUserPage = new SpecialRenameUserPage(driver)
        .open()
        .fillFormData("ChesskyTest", "ChesskyTest", "q")
        .submitChange();
    Assertion.assertEquals(renameUserPage.getErrorMessage(),
                           "You must understand the consequences of changing your username. Please click the proper checkbox.");
  }

  @Test(groups = {"renameTool_01"})
  @Execute(asUser = User.QARENAME)
  public void UserProvidesInCorrectNewNameDoesClickUnderstandCheckbox() {
    SpecialRenameUserPage renameUserPage = new SpecialRenameUserPage(driver)
        .open()
        .fillFormData("Chessky>Test", "Chessky>Test", "q")
        .agreeToTermsAndConditions()
        .submitChange();
    Assertion.assertEquals(renameUserPage.getErrorMessage(),
                           "This username contains non-alphanumeric characters.");
  }

  @Test(groups = {"renameTool_01"})
  @Execute(asUser = User.QARENAME)
  public void UserProvidesNoNewUserName_ErrorIsShown() {
    SpecialRenameUserPage renameUserPage = new SpecialRenameUserPage(driver)
        .open()
        .fillFormData("", "")
        .submitChange();
    new ConfirmationModalPage(driver).confirm();
    Assertion.assertEquals(renameUserPage.getErrorMessage(), "Some error message");
  }

  @Test(groups = {"renameTool_01"})
  @Execute(asUser = User.QARENAME)
  @RelatedIssue(issueID = "SUS-123", comment = "Ten test nie działą")
  public void UserAlreadyRenamedMessageShowed() {
    SpecialRenameUserPage renameUserPage = new SpecialRenameUserPage(driver)
        .open();

    Assertion.assertStringContains(renameUserPage.getContentText(), "This account has already "
                                                                    + "been renamed.");
  }

  @Test(groups = {"renameTool_01"})
  @Execute(asUser = User.QARENAME)
  @RelatedIssue(issueID = "SUS-123", comment = "Ten test nie działą")
  public void GoToHelpPage() {
    SpecialRenameUserPage renameUserPage = new SpecialRenameUserPage(driver)
        .open();
    HelpPage helpPage = renameUserPage.goToHelpPage();

    Assertion.assertEquals(helpPage.isHelpPageHeaderPresent(), true);
  }

  @Test(groups = {"renameTool_01"})
  @Execute(asUser = User.QARENAME)
  public void ConfirmationModalDecline_RedirectionToRenameTool() {
    SpecialRenameUserPage renameUserPage = new SpecialRenameUserPage(driver)
        .open()
        .fillFormData("ChesskyTest", "ChesskyTest", "q")
        .agreeToTermsAndConditions()
        .submitChange();
    new ConfirmationModalPage(driver).reject();
    Assertion.assertEquals(renameUserPage.getHeaderText(), "Change a user's name");
  }

  @Test(groups = {"renameTool_01"})
  public void ConfirmationModalConfirm_CheckForConfirmationBox() {
    String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    SpecialRenameUserPage renameUserPage = new SpecialRenameUserPage(driver)
        .open()
        .fillFormData("NewUserName_" + timestamp, "NewUserName_" + timestamp, User.QARENAME
            .getPassword())
        .agreeToTermsAndConditions();
//        .submitChange();
    new ConfirmationModalPage(driver).accept();
    Assertion.assertEquals(renameUserPage.getHeaderText(), "Rename process is in progress. The "
                                                           + "rest will be done in background. You will be notified via e-mail when it is completed.");
  }

  @Test(groups = {"renameTool_01"})
  public void NewUserCreateAndRenameDone() {
    Credentials credentials = new Credentials();
    String timestamp = Long.toString(DateTime.now().getMillis());
    SignUpUser
        user =
        new SignUpUser("QARenameUser" + timestamp, credentials.email, "aaaa",
                       LocalDate.of(1993, 3, 19));
    UserRegistration.registerUserEmailConfirmed(user);
    new WikiBasePageObject().loginAs(user.getUsername(),user.getPassword(),wikiURL);


    SpecialRenameUserPage renameUserPage = new SpecialRenameUserPage(driver)
        .open()
        .fillFormData("NewUserName" + timestamp, "NewUserName" + timestamp, user.getPassword())
        .agreeToTermsAndConditions()
        .submitChange();
    new ConfirmationModalPage(driver).accept();
    Assertion.assertEquals(renameUserPage.getSuccessBoxMessage(), "Rename process is in progress. The "
                                                           + "rest will be done in background. You will be notified via e-mail when it is completed.");
  }

}
