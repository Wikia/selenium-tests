package com.wikia.webdriver.testcases.fandom;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.helpers.FandomUser;
import com.wikia.webdriver.common.templates.fandom.FandomTestTemplate;
import com.wikia.webdriver.elements.fandom.pages.AdminLoginPage;

@Test(groups = {"Fandom"})
public class AdminPageTest extends FandomTestTemplate {

  @Test
  public void adminCanLogIn() {
    new AdminLoginPage().open().getLoginBox().login(FandomUser.EDITOR);
  }
}
