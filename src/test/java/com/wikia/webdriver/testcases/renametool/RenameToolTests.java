package com.wikia.webdriver.testcases.renametool;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.renametool.ConfirmationModalPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.renametool.HelpPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.renametool.SpecialRenameUserPage;

import org.testng.annotations.Test;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@InBrowser(browser = Browser.CHROME)
@Execute(onWikia = "community")
@Test(groups = "renameTool")
public class RenameToolTests extends NewTestTemplate {

  @Test(groups = {"renameTool_01"})
  public void UserProvidesNoNewUserName_ErrorIsShown() {
    SpecialRenameUserPage renameUserPage = new SpecialRenameUserPage(driver)
        .open()
        .fillFormData("", "")
        .submitChange();
    new ConfirmationModalPage(driver).confirm();
    Assertion.assertEquals(renameUserPage.getErrorMessage(), "Some error message");
  }

  @Test(groups = {"renameTool_01"})
  @Execute(asUser = User.SUS_STAFF)
  @RelatedIssue(issueID = "SUS-123", comment = "Ten test nie działą")
  public void UserAlreadyRenamedMessageShowed() {
    SpecialRenameUserPage renameUserPage = new SpecialRenameUserPage(driver)
        .open();

    Assertion.assertStringContains(renameUserPage.getContentText(), "This account has already "
                                                                    + "been renamed.");
  }

  @Test(groups = {"renameTool_01"})
  @Execute(asUser = User.STAFF)
  @RelatedIssue(issueID = "SUS-123", comment = "Ten test nie działą")
  public void GoToHelpPage() {
    SpecialRenameUserPage renameUserPage = new SpecialRenameUserPage(driver)
        .open();
    HelpPage helpPage = renameUserPage.goToHelpPage();

    Assertion.assertEquals(helpPage.isHelpPageHeaderPresent(), true);
  }

  @Test(groups = {"renameTool_01"})
  @Execute(asUser = User.LUKAS)
  public void ConfirmationModalDecline_RedirectionToRenameTool() {
    SpecialRenameUserPage renameUserPage = new SpecialRenameUserPage(driver)
        .open()
        .fillFormData("NewUserName", "NewUserName", User.LUKAS.getPassword())
        .agreeToTermsAndConditions()
        .submitChange();
    new ConfirmationModalPage(driver).reject();
    Assertion.assertEquals(renameUserPage.getHeaderText(), "Change a user's name");
  }

  @Test(groups = {"renameTool_01"})
  @Execute(asUser = User.LUKAS)
  public void ConfirmationModalConfirm_CheckForConfirmationBox() {
    String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    SpecialRenameUserPage renameUserPage = new SpecialRenameUserPage(driver)
        .open()
        .fillFormData("NewUserName_" + timestamp, "NewUserName_" + timestamp, User.LUKAS
            .getPassword())
        .agreeToTermsAndConditions();
//        .submitChange();
    new ConfirmationModalPage(driver).accept();
    Assertion.assertEquals(renameUserPage.getHeaderText(), "Rename process is in progress. The "
                                                           + "rest will be done in background. You will be notified via e-mail when it is completed.");
  }
}
