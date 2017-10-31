package com.wikia.webdriver.testcases.renametool;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.renametool.ConfirmationModalPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.renametool.SpecialRenameUserPage;
import org.testng.Assert;
import org.testng.annotations.Test;

@InBrowser(browser = Browser.CHROME)
@Execute(onWikia = "community", asUser = User.USER)
public class RenameToolTests extends NewTestTemplate {

  @Test(groups = "renameTool","renameTool_01")
  public void UserProvidesNoNewUserName_ErrorIsShown() {
    SpecialRenameUserPage renameUserPage = new SpecialRenameUserPage(driver)
            .open().fillFormData("","")
            .submitChange();
    new ConfirmationModalPage(driver).confirm();
    Assert.assertEquals(renameUserPage.getErrorMessage(),"Some error message");
  }
}
