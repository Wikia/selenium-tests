package com.wikia.webdriver.testcases.renametool;

import static java.nio.charset.StandardCharsets.ISO_8859_1;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.api.UserRegistration;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.SignUpUser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.UserProfilePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.messagewall.MessageWall;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialCreatePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.renametool.ConfirmationModalPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.renametool.HelpPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.renametool.SpecialRenameUserPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.blog.BlogPage;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

import java.net.URLEncoder;
import java.time.LocalDate;

@InBrowser(browser = Browser.CHROME)
@Execute(onWikia = "communitytest")
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
        .fillFormData("", "", "q")
        .submitChange();
    Assertion.assertEquals(renameUserPage.getErrorMessage(), "This username is blank.");
  }

  @Test(groups = {"renameTool_01"})
  @Execute(asUser = User.QARENAMEDALREADY)
  public void UserAlreadyRenamedMessageShowed() {
    SpecialRenameUserPage renameUserPage = new SpecialRenameUserPage(driver)
        .open();

    Assertion.assertStringContains(renameUserPage.getPageText(), "This account has already been "
                                                                 + "renamed. As our ");
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
  public void NewUserCreateAndRenameDone() {
    Credentials credentials = new Credentials();
    String timestamp = Long.toString(DateTime.now().getMillis());
    SignUpUser
        user =
        new SignUpUser("QARenameUser" + timestamp, credentials.email, "aaaa",
                       LocalDate.of(1993, 3, 19));
    UserRegistration.registerUserEmailConfirmed(user);
    new WikiBasePageObject().loginAs(user.getUsername(), user.getPassword(), wikiURL);

    SpecialRenameUserPage renameUserPage = new SpecialRenameUserPage(driver)
        .open()
        .fillFormData("NewUserName" + timestamp, "NewUserName" + timestamp, user.getPassword())
        .agreeToTermsAndConditions()
        .submitChange();
    new ConfirmationModalPage(driver).accept();
    Assertion
        .assertEquals(renameUserPage.getSuccessBoxMessage(), "Rename process is in progress. The "
                                                             + "rest will be done in background. You will be notified via e-mail when it is completed.");
  }

  @Test(groups = {"renameTool_01"})
  @Execute(onWikia = "communitytest")
  public void NewUserCreateEditProfileAndRenameDone() {
    Credentials credentials = new Credentials();
    String timestamp = Long.toString(DateTime.now().getMillis());
    SignUpUser
        user =
        new SignUpUser("QARename Usęr" + timestamp, credentials.email, "aaaa",
                       LocalDate.of(1993, 3, 19));
    UserRegistration.registerUserEmailConfirmed(user);

    new WikiBasePageObject().loginAs(user.getUsername(), user.getPassword(), wikiURL);

    new ArticleContent().push("aaa", String.format("User:%s", user.getUsername()));

    UserProfilePage userProfilePage = new UserProfilePage()
        .open(user.getUsername());

    SpecialRenameUserPage renameUserPage = new SpecialRenameUserPage(driver)
        .open()
        .fillFormData("NewUser Nąmę" + timestamp, "NewUser Nąmę" + timestamp, user.getPassword())
        .agreeToTermsAndConditions()
        .submitChange();
    new ConfirmationModalPage(driver).accept();
    Assertion
        .assertEquals(renameUserPage.getSuccessBoxMessage(), "Rename process is in progress. The "
                                                             + "rest will be done in background. You will be notified via e-mail when it is completed.");
  }

  @Test(groups = {"renameTool_01"})
  public void NewUserCreateEditMessageWallAndRenameDone() {
    Credentials credentials = new Credentials();
    String timestamp = Long.toString(DateTime.now().getMillis());
    SignUpUser
        user =
        new SignUpUser("QARename Usęr" + timestamp, credentials.email, "aaaa",
                       LocalDate.of(1993, 3, 19));
    UserRegistration.registerUserEmailConfirmed(user);

    new WikiBasePageObject().loginAs(user.getUsername(), user.getPassword(), wikiURL);

    MessageWall wall = new MessageWall().open(user.getUsername());
    MiniEditorComponentObject mini = wall.triggerMessageArea();
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    mini.switchAndWrite(message);
    wall.setTitle(title);
    wall.submit();
    wall.verifyMessageText(title, message, user.getUsername());
    wall.triggerEditMessageArea();
    String messageEdit = PageContent.MESSAGE_WALL_MESSAGE_EDIT_PREFIX + wall.getTimeStamp();
    mini.switchAndEditMessageWall(messageEdit);
    wall.submitEdition();
    wall.verifyMessageEditText(title, messageEdit, user.getUsername());

    String newName = "NewUser Nąmę" + timestamp;
    SpecialRenameUserPage renameUserPage = new SpecialRenameUserPage(driver)
        .open()
        .fillFormData(newName, newName, user.getPassword())
        .agreeToTermsAndConditions()
        .submitChange();
    new ConfirmationModalPage(driver).accept();
    Assertion
        .assertEquals(renameUserPage.getSuccessBoxMessage(), "Rename process is in progress. The "
                                                             + "rest will be done in background. You will be notified via e-mail when it is completed.");
    wall.open(newName);
    String encodedUrl = renameUserPage.encodeToURL(newName);

    String expectedWallUrl = String.format("%s/wiki/Message_Wall:%s", urlBuilder.getUrlForWiki(),
                                           encodedUrl);

    Assertion.assertEquals(driver.getCurrentUrl(), expectedWallUrl);
    wall.verifyMessageEditTextRenameDone(title, message, newName);

  }
}
