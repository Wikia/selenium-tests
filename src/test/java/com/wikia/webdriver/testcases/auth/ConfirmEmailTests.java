package com.wikia.webdriver.testcases.auth;

import static com.wikia.webdriver.testcases.auth.SignupTests.createNewUser;
import static org.testng.Assert.assertTrue;

import com.wikia.webdriver.common.core.EmailUtils;
import com.wikia.webdriver.common.core.helpers.SignUpUser;
import com.wikia.webdriver.common.core.helpers.UserWithEmail;
import com.wikia.webdriver.common.core.helpers.UserWithEmailFactory;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.pages.ArticlePage;
import com.wikia.webdriver.elements.oasis.components.notifications.NotificationType;
import com.wikia.webdriver.pageobjectsfactory.componentobject.global_navitagtion.NavigationBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.UserProfilePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.DetachedRegisterPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ConfirmEmailTests extends NewTestTemplate {

  private UserWithEmail user = UserWithEmailFactory.getEmailOnlyUserForConfirmation();

  @BeforeMethod
  @AfterMethod
  private void cleanUpEmails() {
    EmailUtils.deleteAllEmails(user.getEmail(), user.getEmailPassword());
  }

  @Test
  public void testEmailConfirmationFlow() {
    SignUpUser newUser =  createNewUser(user);
    new DetachedRegisterPage(new NavigationBar().clickOnRegister()).signUp(newUser);
    ArticlePage articlePage = new ArticlePage();
    articlePage.verifyUserLoggedIn(newUser.getUsername());

    assertTrue(articlePage.isNotificationPresent(NotificationType.WARN,
        "Your email address hasn't been confirmed."));
    UserProfilePage page = confirmEmailForUser(user);
    assertTrue(page.isNotificationPresent(NotificationType.CONFIRM,
        "Your email has been confirmed."));
  }

  private UserProfilePage confirmEmailForUser(UserWithEmail user) {
    String confirmationLink = BasePageObject.getEmailConfirmationLink(user.getEmail(), user.getEmailPassword());
    UserProfilePage page = new UserProfilePage();
    page.openWikiPage(confirmationLink);
    return page;
  }

}
