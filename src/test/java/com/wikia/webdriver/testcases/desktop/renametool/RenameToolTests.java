package com.wikia.webdriver.testcases.desktop.renametool;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.api.UserRegistration;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.SignUpUser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.renametool.ConfirmationModalPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.renametool.HelpPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.renametool.SpecialRenameUserPage;
import java.time.LocalDate;
import java.util.UUID;
import org.joda.time.DateTime;
import org.testng.annotations.Test;

@SuppressWarnings("SpellCheckingInspection")
@InBrowser(browser = Browser.CHROME)
@Execute(onWikia = "communitytest")
@Test(groups = "renameTool")
public class RenameToolTests extends NewTestTemplate {

  @Test
  @Execute(asUser = User.QARENAME)
  public void userProvidesCorrectNewNameDoesntClickUnderstandCheckbox() {
    SpecialRenameUserPage renameUserPage = new SpecialRenameUserPage().open()
        .fillFormData("ChesskyTest", "ChesskyTest", "q")
        .submitChange();

    Assertion.assertEquals(
        renameUserPage.getErrorMessage(),
        "You must understand the consequences of changing your username. Please click the proper checkbox."
    );
  }

  @Test
  @Execute(asUser = User.QARENAME)
  public void userProvidesInCorrectNewNameDoesClickUnderstandCheckbox() {
    SpecialRenameUserPage renameUserPage = new SpecialRenameUserPage().open()
        .fillFormData("Chessky>Test", "Chessky>Test", "q")
        .agreeToTermsAndConditions()
        .submitChange();

    Assertion.assertEquals(renameUserPage.getErrorMessage(),
                           "This username contains non-alphanumeric characters."
    );
  }

  @Test
  @Execute(asUser = User.QARENAME)
  public void userProvidesNoNewUserNameErrorIsShown() {
    SpecialRenameUserPage renameUserPage = new SpecialRenameUserPage().open()
        .fillFormData("", "", "q")
        .submitChange();

    Assertion.assertEquals(renameUserPage.getErrorMessage(), "This username is blank.");
  }

  @Test
  @Execute(asUser = User.QARENAMEDALREADY)
  public void userAlreadyRenamedMessageShowed() {
    SpecialRenameUserPage renameUserPage = new SpecialRenameUserPage().open();

    Assertion.assertStringContains(renameUserPage.getPageText(),
                                   "This account has already been " + "renamed. As our "
    );
  }

  @Test
  @Execute(asUser = User.QARENAME)
  public void goToHelpPage() {
    SpecialRenameUserPage renameUserPage = new SpecialRenameUserPage().open();
    HelpPage helpPage = renameUserPage.goToHelpPage();

    Assertion.assertEquals(helpPage.getHeaderText(), "Help:Rename my account");
  }

  @Test
  @Execute(asUser = User.QARENAME)
  public void confirmationModalDeclineRedirectionToRenameTool() {
    SpecialRenameUserPage renameUserPage = new SpecialRenameUserPage().open()
        .fillFormData("ChesskyTest", "ChesskyTest", User.QARENAME.getPassword())
        .agreeToTermsAndConditions()
        .submitChange();
    new ConfirmationModalPage().reject();

    Assertion.assertEquals(renameUserPage.getHeaderText(), "Change a user's name");
  }

  @Test
  public void newUserCreateAndRenameDone() {
    Credentials credentials = new Credentials();
    String timestamp = Long.toString(DateTime.now().getMillis());
    String hashstamp = UUID.randomUUID().toString().replace("-", "0");
    SignUpUser user = new SignUpUser("Q" + timestamp,
                                     credentials.email,
                                     "aaaa",
                                     LocalDate.of(1993, 3, 19)
    );
    UserRegistration.registerUserEmailConfirmed(user);
    new WikiBasePageObject().loginAs(user.getUsername(), wikiURL);

    SpecialRenameUserPage renameUserPage = new SpecialRenameUserPage().open()
        .fillFormData("N" + hashstamp, "N" + hashstamp, user.getPassword())
        .agreeToTermsAndConditions()
        .submitChange();
    new ConfirmationModalPage().accept();

    Assertion.assertEquals(renameUserPage.getSuccessBoxMessage(),
                           "Rename process is in progress. The "
                           + "rest will be done in background. You will be notified via e-mail when it is completed."
    );
  }

  @Test
  @Execute(onWikia = "communitytest")
  public void newUserCreateEditProfileAndRenameDone() {
    Credentials credentials = new Credentials();
    String timestamp = Long.toString(DateTime.now().getMillis());
    SignUpUser user = new SignUpUser("QARename Usęr" + timestamp,
                                     credentials.email,
                                     "aaaa",
                                     LocalDate.of(1993, 3, 19)
    );
    UserRegistration.registerUserEmailConfirmed(user);

    new WikiBasePageObject().loginAs(user.getUsername(), wikiURL);

    new ArticleContent().push("aaa", String.format("User:%s", user.getUsername()));

    SpecialRenameUserPage renameUserPage = new SpecialRenameUserPage().open()
        .fillFormData("NewUser Nąmę" + timestamp, "NewUser Nąmę" + timestamp, user.getPassword())
        .agreeToTermsAndConditions()
        .submitChange();
    new ConfirmationModalPage().accept();

    Assertion.assertEquals(renameUserPage.getSuccessBoxMessage(),
                           "Rename process is in progress. The "
                           + "rest will be done in background. You will be notified via e-mail when it is completed."
    );
  }

  @Test
  public void phalanxBlocksForbiddenPhrase() {
    String newName = "With all due respect Fuck You sir";
    String expectedError = String.format("Phrase \"%s\" is globally blocked by Phalanx. See the list of blocks here.",
                                         newName
    );
    checkInvalidUserRenameFlow(newName, expectedError);
  }

  @Test
  public void antiSpoofBlocksForbiddenPhrase() {

    String newName = "MACbre";
    String antiSpoofError = String.format("AntiSpoof warning - there is already a username similar to \"%s\".",
                                          newName
    );
    checkInvalidUserRenameFlow(newName, antiSpoofError);
  }

  @Test
  public void antiSpoofBlocksEmoticonPhrase() {
    String newName = "Chessky☠☠☠";
    String antiSpoofError = String.format("AntiSpoof warning - there is already a username similar to \"%s\".",
                                          newName
    );
    checkInvalidUserRenameFlow(newName, antiSpoofError);
  }

  private void checkInvalidUserRenameFlow(String newName, String errorMessage) {
    Credentials credentials = new Credentials();
    String timestamp = Long.toString(DateTime.now().getMillis());
    SignUpUser user = new SignUpUser("QArenamuser" + timestamp,
                                     credentials.email,
                                     "aaaa",
                                     LocalDate.of(1993, 3, 19)
    );
    UserRegistration.registerUserEmailConfirmed(user);

    new WikiBasePageObject().loginAs(user.getUsername(), wikiURL);

    SpecialRenameUserPage renameUserPage = new SpecialRenameUserPage().open()
        .fillFormData(newName, newName, user.getPassword())
        .agreeToTermsAndConditions()
        .submitChange();
    new ConfirmationModalPage().accept();

    Assertion.assertEquals(renameUserPage.getErrorMessage(), errorMessage);
  }
}
