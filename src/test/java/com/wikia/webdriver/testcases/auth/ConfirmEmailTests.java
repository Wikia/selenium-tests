package com.wikia.webdriver.testcases.auth;

import static com.wikia.webdriver.testcases.auth.SignupTests.BIRTH_DATE;
import static com.wikia.webdriver.testcases.auth.SignupTests.PASS_PATTERN;
import static com.wikia.webdriver.testcases.auth.SignupTests.USERNAME_PATTERN;
import static com.wikia.webdriver.testcases.auth.SignupTests.getEmailAlias;

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
import java.time.Instant;
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
    SignUpUser newUser =  new SignUpUser(
        String.format(USERNAME_PATTERN, Instant.now().getEpochSecond()),
        getEmailAlias(user.getEmail()),
        String.format(PASS_PATTERN, Instant.now().getEpochSecond()),
        BIRTH_DATE
    );
    new DetachedRegisterPage(new NavigationBar().clickOnRegister()).signUp(newUser);
    new ArticlePage().isNotificationPresent(NotificationType.WARN,
        "Your email address hasn't been confirmed.");
    UserProfilePage page = confirmEmailForUser(user);
    page.isNotificationPresent(NotificationType.CONFIRM, "Your email has been confirmed.");
  }

  private UserProfilePage confirmEmailForUser(UserWithEmail user) {
    String confirmationLink = BasePageObject.getEmailConfirmationLink(user.getEmail(), user.getEmailPassword());
    UserProfilePage page = new UserProfilePage();
    page.openWikiPage(confirmationLink);
    return page;
  }

}
