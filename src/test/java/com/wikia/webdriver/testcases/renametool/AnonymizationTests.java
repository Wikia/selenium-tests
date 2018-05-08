package com.wikia.webdriver.testcases.renametool;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.EmailUtils;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.api.UserRegistration;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.SignUpUser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.messagewall.MessageWall;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.renametool.ConfirmationModalPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.renametool.HelpPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.renametool.SpecialRenameUserPage;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.UUID;

@SuppressWarnings("SpellCheckingInspection")
@InBrowser(browser = Browser.CHROME)
@Execute(onWikia = "communitytest")
@Test(groups = "anonymization")
public class AnonymizationTests extends NewTestTemplate {

  @Test
  public void anonyizationTest() {

    Credentials credentials = new Credentials();
    EmailUtils.deleteAllEmails(credentials.emailAnonymousUserTestWikia, credentials.emailAnonymousUserTestWikiaPassword);

    String timestamp = Long.toString(DateTime.now().getMillis());
    SignUpUser
        user = new SignUpUser("QARename Usęr" + timestamp, credentials.emailAnonymousUserTestWikia, "aaaa",
                              LocalDate.of(1993, 3, 19));
    UserRegistration.registerUserEmailConfirmed(user);

    new WikiBasePageObject().loginAs(user.getUsername(), user.getPassword(), wikiURL);

    MessageWall wall = new MessageWall().open(user.getUsername());
    MiniEditorComponentObject mini = wall.triggerMessageArea(true);
    String message = String.format("%s%s", PageContent.MESSAGE_WALL_MESSAGE_PREFIX, timestamp);
    String title = String.format("%s%s", PageContent.MESSAGE_WALL_TITLE_PREFIX, timestamp);
    mini.switchAndWrite(message);
    wall.setTitle(title);
    wall.submit();
    wall.verifyMessageText(title, message, user.getUsername());
    wall.triggerEditMessageArea();
    String
        messageEdit =
        PageContent.MESSAGE_WALL_MESSAGE_EDIT_PREFIX + Long.toString(DateTime.now().getMillis());
    mini.switchAndEditMessageWall(messageEdit);
    wall.submitEdition();
    wall.verifyMessageEditText(title, messageEdit, user.getUsername());


    try {
      Thread.sleep(15000);
    } catch (InterruptedException e) {
      PageObjectLogging.logError("Interruption during waiting for Message Wall background task",
                                 e);
    }

    String newName = "NewUser Nąmę" + timestamp;
    SpecialRenameUserPage renameUserPage = new SpecialRenameUserPage()
        .open()
        .fillFormData(newName, newName, user.getPassword())
        .agreeToTermsAndConditions()
        .submitChange();
    new ConfirmationModalPage().accept();
    Assertion.assertEquals(renameUserPage.getSuccessBoxMessage(),
                           "Rename process is in progress. The rest will be done in background. "
                           + "You will be notified via e-mail when it is completed.");
    wall.open(newName);
    String encodedUrl = renameUserPage.encodeToURL(newName);

    String expectedWallUrl = String.format("%s/wiki/Message_Wall:%s", urlBuilder.getUrlForWiki(),
                                           encodedUrl);

    Assertion.assertEquals(driver.getCurrentUrl(), expectedWallUrl);
    wall.verifyMessageEditTextRenameDone(title, message, newName);
    String theContent = EmailUtils.getFirstEmailContent(credentials.emailAnonymousUserTestWikia, credentials.emailAnonymousUserTestWikiaPassword, "Your username change on FANDOM is complete!");
  }


}
