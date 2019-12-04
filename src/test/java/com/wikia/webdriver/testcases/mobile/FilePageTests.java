package com.wikia.webdriver.testcases.mobile;

import com.wikia.webdriver.common.contentpatterns.MobileWikis;
import com.wikia.webdriver.common.core.AlertHandler;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.communities.mobile.pages.ArticlePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.FilePageObject;

import org.testng.annotations.Test;

@Test(groups = "MobileWiki_FilePage")
@Execute(onWikia = MobileWikis.MERCURY_AUTOMATION_TESTING, asUser = User.USER)
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class FilePageTests extends NewTestTemplate {

  @Test
  public void snippetIsNotAffectedByXSS() {
    new ArticlePage().open("File:Mala-mi.jpg");
    FilePageObject filePage = new FilePageObject();

    Assertion.assertTrue(filePage.doesSnippetContainXSS());

    Assertion.assertFalse(AlertHandler.isAlertPresent(driver));
  }
}
