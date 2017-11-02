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
}
